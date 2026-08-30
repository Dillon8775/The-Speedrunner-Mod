package net.dillon.speedrunnermod.screen;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.network.ClientModPackets;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.platform.SpeedrunnerModPlatforms;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.feature.FeatureScreenCategory;
import net.dillon.speedrunnermod.screen.leaderboard.LeaderboardsIneligibleScreen;
import net.dillon.speedrunnermod.screen.option.AdvancedOptionsScreen;
import net.dillon.speedrunnermod.screen.option.ResetOptionsConfirmScreen;
import net.dillon.speedrunnermod.screen.option.RestartRequiredScreen;
import net.dillon.speedrunnermod.screen.option.WorldCreationOptionsScreen;
import net.dillon.speedrunnermod.screen.synced.MatchSettingsWithServerScreen;
import net.dillon.speedrunnermod.util.ModLinks;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.OptionsList;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.CommonColors;
import net.minecraft.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientConfigHandler;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;

/**
 * Used to create any {@code Speedrunner Mod} screens.
 */
public abstract class AbstractModScreen extends BaseModScreen {
    protected boolean alreadySettingToIneligibleScreen = false;
    protected File configFile; // This returns null unless the screen is an options screen
    protected final Screen parent;
    protected Button helpButton, saveButton, openOptionsFileButton, doneButton, matchSettingsWithServer;
    public Button resetOptionsButton;
    public ModButtonListWidget buttonList; // The list of all the buttons for a speedrunner mod screen, returns null if there is no need for a scrollable section
    protected final List<AbstractWidget> featureButtons = new ArrayList<>();

    public AbstractModScreen(Screen parent, Component title) {
        super(parent, title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        if (!this.isOptionsScreen()) {
            this.initializeModButtonListWidget();
            this.buttonList.addAll(this.buttons());
            if (this.isCentered()) {
                int defaultY = this.buttonList.getY();
                int defaultHeight = this.buttonList.getHeight();
                int listHeight = Math.min(this.buttonList.getEntryContentHeight(), defaultHeight);
                int y = Math.max((this.height - listHeight) / 2, defaultY);

                this.buttonList.updateSizeAndPosition(this.width, listHeight, 0, y);
                this.buttonList.layoutButtons(true);
            }
        }
        this.addWidget(this.buttonList);
        if (isOptionsScreen()) {
            this.saveButton = this.addRenderableWidget(Button.builder(ModTexts.SAVE, (button) -> {
                this.onClose();
            }).bounds(this.getButtonsLeftSide(), this.getDoneButtonHeight(), 100, 20).build());

            this.openOptionsFileButton = this.addRenderableWidget(Button.builder(ModTexts.MENU_OPEN_OPTIONS_FILE, (button) -> {
                this.onClose();
                this.configFile = !Minecraft.getInstance().hasShiftDown() ? commonConfigHandler().getConfigFile() : clientConfigHandler().getConfigFile();
                Util.getPlatform().openFile(this.configFile);
            }).bounds(this.getButtonsMiddle(), this.getDoneButtonHeight(), 100, 20).build());

            this.resetOptionsButton = this.addRenderableWidget(Button.builder(ModTexts.RESET, (button) -> {
                this.minecraft.gui.setScreen(new ResetOptionsConfirmScreen(this.parent));
            }).bounds(this.getButtonsRightSide(), this.getDoneButtonHeight(), 100, 20).build());

            this.helpButton = this.addRenderableWidget(Button.builder(Texts.BLANK, (button) -> {
                this.openLink(ModLinks.MODRINTH, true);
            }).bounds(this.getButtonsRightSide() + 104, this.getDoneButtonHeight(), 20, 20).build());
            this.matchSettingsWithServer = this.addRenderableWidget(Button.builder(Texts.BLANK, (button) -> {
                this.minecraft.gui.setScreen(new MatchSettingsWithServerScreen(this.parent));
            }).bounds(this.getButtonsLeftSide() - 24, this.getDoneButtonHeight(), 20, 20).build());
            this.matchSettingsWithServer.active = this.isOnServer();
        } else {
            if ((this instanceof FeatureScreen featureScreen && featureScreen.featurePage.getCategory() != FeatureScreenCategory.FIRST_TIME_PLAYING && featureScreen.featurePage.getCategory() != FeatureScreenCategory.SECRET_DOOM_MODE) || !(this instanceof FeatureScreen)) {
                this.doneButton = this.addRenderableWidget(Button.builder(this.getDoneText(), (button) -> this.onClose()).bounds(this.width / 2 - 100, this.getDoneButtonHeight(), 200, 20).build());
            }
        }
        if (this.hasSearchField()) {
            int y = !this.isCentered() ? 10 : this.buttonList.getY() - 23;
            this.searchField = new EditBox(this.font, this.width / 2 + 17, y, 100, 15, null);
            this.searchField.setMaxLength(50);
            this.searchField.setHint(Component.translatable(isOptionsScreen() ? "speedrunnermod.search_field_options_screen.placeholder" : "speedrunnermod.search_field_features_screen.placeholder").withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.GRAY));
            this.addWidget(this.searchField);
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
        super.extractRenderState(context, mouseX, mouseY, delta);
        this.renderCustomText(context);

        if (this.shouldRenderVersionText()) {
            int textWidth = this.width - 20;
            int textHeight = this.height - 21;
            int imageWidth = this.width - (SpeedrunnerModPlatforms.getPlatform().logoWidth().getWidthModifier());
            int imageHeight = this.height - 26;
            context.centeredText(this.font, Component.literal(ModConstants.MOD_VERSION).withStyle(ChatFormatting.AQUA), textWidth, textHeight, CommonColors.WHITE);
            context.blit(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/gui/icon.png"), imageWidth, imageHeight, 0.0F, 0.0F, 18, 18, 18, 18);
            if (ModConstants.HAS_UPDATE) {
                ClientTasks.drawUpdateSprite(context, imageWidth - 3, imageHeight - 3);
            }
        }

        if (this.searchField != null) {
            this.searchField.extractWidgetRenderState(context, mouseX, mouseY, delta);
            this.search(!this.searchField.getValue().isEmpty());
        }
        this.lockOptionsAndRenderTooltips(context, mouseX, mouseY);

        if (this.shouldRenderTitleText()) {
            Component title = this.title;
            if (this instanceof FeatureScreen abstractFeatureScreen) {
                FeatureScreenCategory category = abstractFeatureScreen.featurePage.getCategory();
                String key = abstractFeatureScreen.featurePage.getKey().toLowerCase();
                title = FeatureScreen.featureTitleText(category, key);
            }

            int x = this.hasSearchField() ? this.width / 2 - 47 : this.width / 2;
            int y = !this.isCentered() ? 13 : this.buttonList.getY() - 19;
            context.centeredText(this.font, title, x, y, CommonColors.WHITE);
        }

        if (this.isOptionsScreen()) {
            context.blit(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/gui/question_mark.png"), helpButton.getX() + 2, helpButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
            context.blit(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/gui/sync.png"), matchSettingsWithServer.getX() + 2, matchSettingsWithServer.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
        }

        if (this.shouldRenderSpeedrunnerModTitle()) {
            int middle = this.width / 2 - 69;
            int height = 10;
            context.blit(RenderPipelines.GUI_TEXTURED, Identifier.parse("speedrunnermod:textures/gui/speedrunner_mod.png"), middle, height, 0.0F, 0.0F, 129, 16, 129, 16);
        }
        this.renderCustomObjects(context);
        if (!this.buttons().isEmpty() || this.shouldRenderTooltips()) {
            this.renderTooltips(context, mouseX, mouseY);
        }
    }

    /**
     * Renders tooltips on certain buttons.
     */
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (this.isOptionsScreen()) {
            if (this.saveButton.isHovered()) {
                this.renderBasicTooltip(ModTexts.SAVE_TOOLTIP, context, mouseX, mouseY);
            }
            if (this.openOptionsFileButton.isHovered()) {
                if (!Minecraft.getInstance().hasShiftDown()) {
                    this.renderBasicTooltip(ModTexts.OPEN_OPTIONS_FILE_TOOLTIP, context, mouseX, mouseY);
                } else {
                    this.renderBasicTooltip(ModTexts.OPEN_CLIENT_OPTIONS_FILE_TOOLTIP, context, mouseX, mouseY);
                }
            }
            if (this.helpButton.isHovered()) {
                this.renderBasicTooltip(ModTexts.HELP_TOOLTIP, context, mouseX, mouseY);
            }
            if (this.matchSettingsWithServer.isHovered()) {
                if (this.matchSettingsWithServer.active) {
                    this.renderBasicTooltip(Component.translatable("speedrunnermod.match_settings_with_server.tooltip"), context, mouseX, mouseY);
                } else {
                    this.renderBasicTooltip(Component.translatable("speedrunnermod.match_settings_with_server.must_be_on_server.tooltip"), context, mouseX, mouseY);
                }
            }
        }
    }

    /**
     * Searches buttons and filters them active/inactive.
     */
    private void search(boolean lock) {
        if (this.buttonList != null) {
            for (ModButtonListWidget.ModWidgetEntry entry : this.buttonList.children()) {
                for (AbstractWidget widget : entry.widgets) {
                    this.filter(widget, lock);
                }
            }
        }
    }

    /**
     * Grays out a button based on a search query.
     */
    private void filter(AbstractWidget widget, boolean lock) {
        String optionText = widget.getMessage().getString().toLowerCase();
        int colonIndex = optionText.indexOf(":");
        if (colonIndex > 0) {
            optionText = optionText.substring(0, optionText.indexOf(":")).toLowerCase();
        }
        widget.active = !lock || this.searchField.getValue().isEmpty() || optionText.contains(this.searchField.getValue().toLowerCase());
    }

    /**
     * Deactivates certain buttons based on certain boolean values, and renders the option's default tooltip and disabled tooltip.
     * <p>Do not call if {@code optionList} is {@code null.}</p>
     * @param option the boolean expression to determine if the option should be locked.
     *               <p>if {@code bl} is {@code false}, the specified option is locked.</p>
     * @param defaultTooltip the tooltip to render when the option is enabled/unlocked.
     * @param disabledTooltip the tooltip to render when the option is disabled/locked.
     */
    protected void lockOptionWithTooltip(
            AbstractWidget option,
            boolean bl,
            Component defaultTooltip,
            Component disabledTooltip,
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY
    ) throws NullPointerException {
        try {
            if (this.buttonList != null) {
                if (option == null) {
                    SpeedrunnerMod.LOGGER.error("No widget found with option: " + option.toString());
                } else {
                    if (this.searchField.getValue().isEmpty()) {
                        option.active = bl;
                    } else {
                        option.active = option.getMessage().getString().toLowerCase().contains(this.searchField.getValue().toLowerCase());
                        if (option.isHovered() && !bl) {
                            option.active = false;
                        }
                    }
                    if (option.isHovered()) {
                        renderBasicTooltip(bl ? defaultTooltip : disabledTooltip, graphics, mouseX, mouseY);
                    }
                }
            } else {
                throw new NullPointerException("\"optionList\" variable cannot be null on \"lockOption\" call.");
            }
        } catch (NullPointerException n) {
            this.minecraft.stop();
            n.printStackTrace();
        }
    }

    /**
     * Creates an option using a {@link OptionInstance}.
     */
    protected static AbstractWidget createOption(OptionInstance<?> option) {
        return option.createButton(Minecraft.getInstance().options);
    }

    /**
     * Sets the screen to the {@code parent} screen and resizes it correctly.
     */
    protected void setParentAndResize() {
        if (this.parent != null) {
            this.parent.resize(this.width, this.height);
            this.minecraft.gui.setScreen(this.parent);
        } else {
            super.onClose();
        }
    }

    /**
     * Iterate through all {@link FeatureScreen}s to add to the main feature screen lists.
     */
    protected void addButtonsIteratively(FeatureScreenCategory screenCategory) {
        this.featureButtons.clear();

        for (FeaturePage page : FeaturePage.values()) {
            if (page.getCategory() == screenCategory) {
                FeatureScreen screen = this.createFeatureScreen(page);

                this.featureButtons.add(Button.builder(
                        FeatureScreen.featureTitleText(screenCategory, page.getKey()), b -> this.minecraft.gui.setScreen(screen)
                ).build());
            }
        }
    }

    /**
     * Creates every feature screen based on its category.
     */
    private FeatureScreen createFeatureScreen(FeaturePage page) {
        return page.createScreen(this);
    }

    @Override
    public void onClose() {
        if (this.isOptionsScreen()) {
            saveAllChanges();
            boolean bl = this.minecraft.getSingleplayerServer() != null;
            boolean bl2 = this.minecraft.level != null;
            if (bl || bl2) {
                ClientModPackets.sendNewC2SOptions();
                if (bl2 && this instanceof WorldCreationOptionsScreen) {
                    ClientModPackets.syncFwc(this.minecraft, 0);
                }
            }

            LeaderboardsIneligibleScreen.needsRestart = false;
            LeaderboardsIneligibleScreen.needsRestartFromEnablingLeaderboardsMode = false;
            this.alreadySettingToIneligibleScreen = false;

            if (common().general.leaderboardsMode.getCurrentValue()) {
                if (Leaderboards.wasLeaderboardsModeChanged()) {
                    LeaderboardsIneligibleScreen.needsRestartFromEnablingLeaderboardsMode = true;
                }

                if (LeaderboardsIneligibleScreen.needsRestartFromEnablingLeaderboardsMode) {
                    this.minecraft.gui.setScreen(new LeaderboardsIneligibleScreen(this.parent));
                } else if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                    if (RestartRequiredScreen.needsRestart()) {
                        LeaderboardsIneligibleScreen.needsRestart = true;
                    }
                    this.alreadySettingToIneligibleScreen = true;
                    this.minecraft.gui.setScreen(new LeaderboardsIneligibleScreen(this.parent));
                } else if (!this.alreadySettingToIneligibleScreen && Leaderboards.wasLeaderboardsModeChanged() || RestartRequiredScreen.needsRestart()) {
                    this.minecraft.gui.setScreen(new RestartRequiredScreen(this.parent));
                } else {
                    this.setParentAndResize();
                }
            } else if (RestartRequiredScreen.needsRestart()) {
                this.minecraft.gui.setScreen(new RestartRequiredScreen(this.parent));
            } else {
                this.setParentAndResize();
            }
        } else {
            if (this.list != null) {
                this.list.applyUnsavedChanges();
            }
            this.setParentAndResize();
        }
    }

    /**
     * Refreshes the screen to allow the user to modify list options.
     */
    @Override
    public boolean keyPressed(KeyEvent input) {
        if (this.isOptionsScreen()) {
            if (this instanceof AdvancedOptionsScreen advancedOptionsScreen && !this.searchField.isFocused() && (hasADown() || hasXDown() || hasYDown() || hasZDown())) {
                double scrollY = advancedOptionsScreen.buttonList.scrollAmount();
                this.minecraft.gui.setScreen(new AdvancedOptionsScreen(this.parent));
                AbstractModScreen modScreen = (AdvancedOptionsScreen) Minecraft.getInstance().gui.screen();
                modScreen.buttonList.setScrollAmount(scrollY);
                return true;
            }
        }
        return super.keyPressed(input);
    }

    /**
     * Initializes the custom button list widget. Used similarly to an {@link OptionsList}, but for normal buttons. Also see {@link AbstractScrollableScreen} for the top Y.
     */
    protected void initializeModButtonListWidget() {
        this.buttonList = this.addRenderableWidget(new ModButtonListWidget(this.minecraft, this.width, this));
    }

    /**
     * The list of buttons to add.
     * @return {@code featureButtons list} (if it's not empty, for feature screen categories), otherwise returns an empty list.
     * <p>Override this method to create a screen with a {@link ModButtonListWidget}, and add the buttons to this list to display them.</p>
     * <p>Avoid using booleans to return different lists, doing so could result in crashes.</p>
     */
    protected List<AbstractWidget> buttons() {
        return !this.featureButtons.isEmpty() ? this.featureButtons : List.of();
    }

    /**
     * @return {@code true} if the player is on a server.
     */
    public boolean isOnServer() {
        return !this.minecraft.isLocalServer() && !(this.minecraft.getCurrentServer() == null);
    }

    /**
     * Returns the {@code "left side"} of a screen.
     */
    protected int getButtonsLeftSide() {
        return this.columns() == 3 ? this.width / 2 - 50 - 105 : this.columns() == 2 ? this.width / 2 - 155 : this.width / 2 - 160;
    }

    /**
     * Returns the {@code "middle" (or center)} of a screen.
     */
    protected int getButtonsMiddle() {
        return this.columns() == 2 ? this.width / 2 - 100 : this.width / 2 - 50;
    }

    /**
     * Returns the {@code "right side"} of a screen.
     */
    protected int getButtonsRightSide() {
        return this.columns() == 3 ? this.width / 2 - 50 + 105 : this.columns() == 2 ? this.getButtonsLeftSide() + 160 : this.width / 2 + 60;
    }

    /**
     * @return the height of buttons on a screen.
     * <p>To add another row of buttons, add {@code 24} to this variable.</p>
     * <p>For example, <pre>height += 24;</pre>
     */
    protected int getButtonsHeight() {
        return this.height / 6 - 12;
    }

    /**
     * @return the height for buttons on a custom screen.
     */
    protected int getCustomButtonsHeight() {
        return this.height / 6 + 126;
    }

    /**
     * @return the {@code "done"} buttons height, typically at the bottom of a screen.
     */
    protected int getDoneButtonHeight() {
        return this.height - 29;
    }

    /**
     * @return the text that should be displayed on the typical "Done" button.
     */
    protected Component getDoneText() {
        return CommonComponents.GUI_DONE;
    }

    /**
     * @return {@code true} if the screen should render tooltips.
     * <p>This is ignored if {@code buttons.size() > 0.}</p>
     */
    protected boolean shouldRenderTooltips() {
        return this.isOptionsScreen();
    }

    /**
     * @return {@code true} if the {@code A} key is being held down.
     */
    protected boolean hasADown() {
        return InputConstants.isKeyDown(InputConstants.KEY_A);
    }

    /**
     * @return {@code true} if the {@code X} key is being held down.
     */
    protected boolean hasXDown() {
        return InputConstants.isKeyDown(InputConstants.KEY_X);
    }

    /**
     * @return {@code true} if the {@code Y} key is being held down.
     */
    protected boolean hasYDown() {
        return InputConstants.isKeyDown(InputConstants.KEY_Y);
    }

    /**
     * @return {@code true} if the {@code Z} key is being held down.
     */
    protected boolean hasZDown() {
        return InputConstants.isKeyDown(InputConstants.KEY_Z);
    }

    /**
     * @return {@code true} if the screen should render the speedrunner mod title.
     */
    protected boolean shouldRenderSpeedrunnerModTitle(){
        return false;
    }

    /**
     * @return {@code true} if the screen should have a search field to search for features/options.
     */
    protected boolean hasSearchField() {
        return this.isOptionsScreen();
    }

    /**
     * Render custom text on a mod screen.
     * <p><b>Never</b> {@link Override} the {@code basic render method,} use this method instead.</p>
     */
    protected void renderCustomText(GuiGraphicsExtractor context) {
    }

    /**
     * Render custom objects on a mod screen.
     */
    protected void renderCustomObjects(GuiGraphicsExtractor context) {
    }

    /**
     * Renders all {@link OptionInstance} tooltips.
     */
    protected void lockOptionsAndRenderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
    }

    /**
     * @return if the screen should be centered.
     */
    public boolean isCentered() {
        return false;
    }

    /**
     * Returns the page ID of a screen. This is used to determined the refreshed screen.
     */
    public abstract String pageId();

    /**
     * Determines how many columns should be displayed on the screen.
     */
    protected abstract int columns();

    /**
     * Determines if the screen should render the "Version: v#.#" text.
     */
    protected abstract boolean shouldRenderVersionText();

    /**
     * Determines if the screen is an options screen.
     */
    public abstract boolean isOptionsScreen();

    /**
     * Determines if the screen should render the title text.
     */
    protected abstract boolean shouldRenderTitleText();
}