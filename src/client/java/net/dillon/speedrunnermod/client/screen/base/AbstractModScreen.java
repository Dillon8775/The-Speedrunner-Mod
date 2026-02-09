package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.client.screen.CustomButtonListWidget;
import net.dillon.speedrunnermod.client.screen.base.leaderboard.LeaderboardsIneligibleScreen;
import net.dillon.speedrunnermod.client.screen.base.option.ResetOptionsConfirmScreen;
import net.dillon.speedrunnermod.client.screen.base.option.RestartRequiredScreen;
import net.dillon.speedrunnermod.client.screen.base.synced.MatchSettingsWithServerScreen;
import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.options.AdvancedOptionsScreen;
import net.dillon.speedrunnermod.client.screen.options.FastWorldCreationOptionsScreen;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.packet.ClientModPackets;
import net.dillon.speedrunnermod.util.ModLinks;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.tooltip.Tooltip;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.input.KeyInput;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.util.InputUtil;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.lwjgl.glfw.GLFW;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientConfigHandler;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;

/**
 * Used to create any {@code Speedrunner Mod} screens.
 */
@Environment(EnvType.CLIENT)
public abstract class AbstractModScreen extends BaseModScreen {
    protected boolean alreadySettingToIneligibleScreen = false;
    protected File configFile; // This returns null unless the screen is an options screen
    protected final Screen parent;
    protected ButtonWidget helpButton, saveButton, openOptionsFileButton, doneButton, matchSettingsWithServer;
    public ButtonWidget resetOptionsButton;
    public CustomButtonListWidget buttonList; // The list of all the buttons for a speedrunner mod screen, returns null if there is no need for a scrollable section
    protected final List<ClickableWidget> featureButtons = new ArrayList<>();

    public AbstractModScreen(Screen parent, Text title) {
        super(parent, title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        if (!this.isOptionsScreen()) {
            this.initializeCustomButtonListWidget();
            this.buttonList.addAll(this.buttons());
        }
        this.addSelectableChild(this.buttonList);
        if (isOptionsScreen()) {
            this.saveButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.SAVE, (button) -> {
                this.close();
            }).dimensions(this.getButtonsLeftSide(), this.getDoneButtonHeight(), 100, 20).build());

            this.openOptionsFileButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_OPEN_OPTIONS_FILE, (button) -> {
                this.close();
                this.configFile = !MinecraftClient.getInstance().isShiftPressed() ? configHandler().getConfigFile() : clientConfigHandler().getConfigFile();
                Util.getOperatingSystem().open(this.configFile);
            }).dimensions(this.getButtonsMiddle(), this.getDoneButtonHeight(), 100, 20).build());

            this.resetOptionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.RESET, (button) -> {
                this.client.setScreen(new ResetOptionsConfirmScreen(this.parent, false));
            }).dimensions(this.getButtonsRightSide(), this.getDoneButtonHeight(), 100, 20).build());

            this.helpButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (button) -> {
                this.openLink(ModLinks.MODRINTH, true);
            }).dimensions(this.getButtonsRightSide() + 104, this.getDoneButtonHeight(), 20, 20).build());
            this.matchSettingsWithServer = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (button) -> {
                this.client.setScreen(new MatchSettingsWithServerScreen(this.parent));
            }).dimensions(this.getButtonsLeftSide() - 24, this.getDoneButtonHeight(), 20, 20).build());
            this.matchSettingsWithServer.active = this.isOnServer();
        } else {
            if ((this instanceof AbstractFeatureScreen featureScreen && featureScreen.getScreenCategory() != ScreenCategory.FIRST_TIME_PLAYING && featureScreen.getScreenCategory() != ScreenCategory.SECRET_DOOM_MODE) || !(this instanceof AbstractFeatureScreen)) {
                this.doneButton = this.addDrawableChild(ButtonWidget.builder(this.getDoneText(), (button) -> this.close()).dimensions(this.width / 2 - 100, this.getDoneButtonHeight(), 200, 20).build());
            }
        }
        if (this.hasSearchField()) {
            this.searchField = new TextFieldWidget(this.textRenderer, this.width / 2 + 15, 10, 90, 15, null);
            this.searchField.setMaxLength(50);
            this.searchField.setPlaceholder(Text.translatable(isOptionsScreen() ? "speedrunnermod.search_field_options_screen.placeholder" : "speedrunnermod.search_field_features_screen.placeholder").formatted(Formatting.ITALIC).formatted(Formatting.GRAY));
            this.addSelectableChild(this.searchField);
        }
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.renderCustomText(context);

        if (this.shouldRenderVersionText()) {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int farRightSide = rightSide + 267;
            int height = this.height - 24;
            context.drawCenteredTextWithShadow(this.textRenderer, SpeedrunnerMod.VERSION, farRightSide, height, Colors.WHITE);
        }

        if (this.searchField != null) {
            this.searchField.render(context, mouseX, mouseY, delta);
            this.search(!this.searchField.getText().isEmpty());
        }
        this.lockOptionsAndRenderTooltips(context, mouseX, mouseY);

        if (this.shouldRenderTitleText()) {
            context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.hasSearchField() ? this.width / 2 - 45 : this.width / 2, 13, Colors.WHITE);
        }

        if (this.isOptionsScreen()) {
            context.drawTexture(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/gui/question_mark.png"), helpButton.getX() + 2, helpButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
            context.drawTexture(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/gui/sync.png"), matchSettingsWithServer.getX() + 2, matchSettingsWithServer.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
        }

        if (this.shouldRenderSpeedrunnerModTitle()) {
            int middle = this.width / 2 - 69;
            int height = 10;
            context.drawTexture(RenderPipelines.GUI_TEXTURED, Identifier.of("speedrunnermod:textures/gui/speedrunner_mod.png"), middle, height, 0.0F, 0.0F, 129, 16, 129, 16);
        }
        this.renderCustomObjects(context);
        if (!this.buttons().isEmpty() || this.shouldRenderTooltips()) {
            this.renderTooltips(context, mouseX, mouseY);
        }
    }

    /**
     * Renders tooltips on certain buttons.
     */
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.isOptionsScreen()) {
            if (this.saveButton.isHovered()) {
                this.renderBasicTooltip(ModTexts.SAVE_TOOLTIP, context, mouseX, mouseY);
            }
            if (this.openOptionsFileButton.isHovered()) {
                if (!MinecraftClient.getInstance().isShiftPressed()) {
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
                    this.renderBasicTooltip(Text.translatable("speedrunnermod.match_settings_with_server.tooltip"), context, mouseX, mouseY);
                } else {
                    this.renderBasicTooltip(Text.translatable("speedrunnermod.match_settings_with_server.must_be_on_server.tooltip"), context, mouseX, mouseY);
                }
            }
        }
    }

    /**
     * Searches buttons and filters them active/inactive.
     */
    private void search(boolean lock) {
        if (this.buttonList != null) {
            for (CustomButtonListWidget.ModWidgetEntry entry : this.buttonList.children()) {
                for (ClickableWidget widget : entry.widgets) {
                    this.filter(widget, lock);
                }
            }
        }
    }

    /**
     * Grays out a button based on a search query.
     */
    private void filter(ClickableWidget widget, boolean lock) {
        String optionText = widget.getMessage().getString().toLowerCase();
        int colonIndex = optionText.indexOf(":");
        if (colonIndex > 0) {
            optionText = optionText.substring(0, optionText.indexOf(":")).toLowerCase();
        }
        widget.active = !lock || this.searchField.getText().isEmpty() || optionText.contains(this.searchField.getText().toLowerCase());
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
            SimpleOption<?> option,
            boolean bl,
            Text defaultTooltip,
            Text disabledTooltip
    ) throws NullPointerException {
        try {
            if (this.buttonList != null) {
                if (this.getSimpleOption(option) == null) {
                    SpeedrunnerMod.error("No widget found with option: " + option.toString());
                } else {
                    ClickableWidget widget = this.getSimpleOption(option);
                    if (this.searchField.getText().isEmpty()) {
                        widget.active = bl;
                    } else {
                        widget.active = widget.getMessage().getString().toLowerCase().contains(this.searchField.getText().toLowerCase());
                        if (widget.isHovered() && !bl) {
                            widget.active = false;
                        }
                    }
                    this.getSimpleOption(option).setTooltip(Tooltip.of(bl ? defaultTooltip : disabledTooltip));
                }
            } else {
                throw new NullPointerException("\"optionList\" variable cannot be null on \"lockOption\" call.");
            }
        } catch (NullPointerException n) {
            this.client.scheduleStop();
            n.printStackTrace();
        }
    }

    /**
     * @return the widget containing {@link SimpleOption} {@code (option).}
     */
    private ClickableWidget getSimpleOption(SimpleOption<?> option) {
        for (CustomButtonListWidget.ModWidgetEntry entry : this.buttonList.children()) {
            for (ClickableWidget widget : entry.widgets) {
                String messageText = widget.getMessage().getString();
                messageText = messageText.substring(0, messageText.indexOf(":"));
                if (messageText.equals(option.toString())) {
                    return widget;
                }
            }
        }

        return null;
    }

    /**
     * Sets the screen to the {@code parent} screen and resizes it correctly.
     */
    protected void setParentAndResize() {
        if (this.parent != null) {
            this.parent.resize(this.width, this.height);
            this.client.setScreen(this.parent);
        } else {
            super.close();
        }
    }

    /**
     * Iterate through all {@link AbstractFeatureScreen}s to add to the main feature screen lists.
     */
    protected void addButtonsIteratively(ScreenCategory screenCategory) {
        this.featureButtons.clear();
        int maxPageNumber = SpeedrunnerModClient.ALL_FEATURE_SCREENS.stream()
                .map(constructor -> constructor.apply(this.parent))
                .filter(screen -> screen.getScreenCategory() == screenCategory)
                .mapToInt(AbstractFeatureScreen::getPageNumber)
                .max()
                .orElse(0);

        for (int pageNum = 1; pageNum <= maxPageNumber; pageNum++) {
            for (Function<Screen, AbstractFeatureScreen> featureScreenConstructor : SpeedrunnerModClient.ALL_FEATURE_SCREENS) {
                AbstractFeatureScreen screen = featureScreenConstructor.apply(this.parent);
                if (screen.getScreenCategory() == screenCategory && screen.getPageNumber() == pageNum) {
                    this.featureButtons.add(ButtonWidget.builder(featureTitleText(screenCategory, screen.linesKey()), button -> {
                        this.client.setScreen(screen);
                    }).build());
                }
            }
        }
    }

    @Override
    public void close() {
        if (this.isOptionsScreen()) {
            saveAllChanges();
            boolean bl = this.client.getServer() != null;
            boolean bl2 = this.client.world != null;
            if (bl || bl2) {
                ClientModPackets.sendNewC2SOptions();
                if (bl2 && this instanceof FastWorldCreationOptionsScreen) {
                    ClientModPackets.syncFwc(this.client, 0);
                }
            }

            LeaderboardsIneligibleScreen.needsRestart = false;
            LeaderboardsIneligibleScreen.needsRestartFromEnablingLeaderboardsMode = false;
            this.alreadySettingToIneligibleScreen = false;

            if (options().main.leaderboardsMode.getCurrentValue()) {
                if (Leaderboards.wasLeaderboardsModeChanged()) {
                    LeaderboardsIneligibleScreen.needsRestartFromEnablingLeaderboardsMode = true;
                }

                if (LeaderboardsIneligibleScreen.needsRestartFromEnablingLeaderboardsMode) {
                    this.client.setScreen(new LeaderboardsIneligibleScreen(this.parent));
                } else if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                    if (RestartRequiredScreen.needsRestart()) {
                        LeaderboardsIneligibleScreen.needsRestart = true;
                    }
                    this.alreadySettingToIneligibleScreen = true;
                    this.client.setScreen(new LeaderboardsIneligibleScreen(this.parent));
                } else if (!this.alreadySettingToIneligibleScreen && Leaderboards.wasLeaderboardsModeChanged() || RestartRequiredScreen.needsRestart()) {
                    this.client.setScreen(new RestartRequiredScreen(this.parent));
                } else {
                    this.setParentAndResize();
                }
            } else if (RestartRequiredScreen.needsRestart()) {
                this.client.setScreen(new RestartRequiredScreen(this.parent));
            } else {
                this.setParentAndResize();
            }
        } else {
            if (this.body != null) {
                this.body.applyAllPendingValues();
            }
            this.setParentAndResize();
        }
    }

    /**
     * Refreshes the screen to allow the user to modify list options.
     */
    @Override
    public boolean keyPressed(KeyInput input) {
        if (this.isOptionsScreen()) {
            if (this instanceof AdvancedOptionsScreen advancedOptionsScreen && !this.searchField.isFocused() && (hasADown() || hasXDown() || hasYDown() || hasZDown())) {
                double scrollY = advancedOptionsScreen.buttonList.getScrollY();
                this.refreshScreen(this.pageId());
                AbstractModScreen modScreen = (AdvancedOptionsScreen)MinecraftClient.getInstance().currentScreen;
                modScreen.buttonList.setScrollY(scrollY);
                return true;
            }
        }
        return super.keyPressed(input);
    }

    /**
     * Initializes the custom button list widget. Used similarly to an {@link OptionListWidget}, but for normal buttons. Also see {@link AbstractScrollableScreen} for the top Y.
     */
    protected void initializeCustomButtonListWidget() {
        this.buttonList = this.addDrawableChild(new CustomButtonListWidget(this.client, this.width, this));
    }

    /**
     * The list of buttons to add.
     * @return {@code featureButtons list} (if it's not empty, for feature screen categories), otherwise returns an empty list.
     * <p>Override this method to create a screen with a {@link CustomButtonListWidget}, and add the buttons to this list to display them.</p>
     * <p>Avoid using booleans to return different lists, doing so could result in crashes.</p>
     */
    protected List<ClickableWidget> buttons() {
        return !this.featureButtons.isEmpty() ? this.featureButtons : List.of();
    }

    /**
     * Used to build title text.
     */
    private static Text featureTitleText(ScreenCategory category, String lang) {
        return Text.translatable("speedrunnermod.title.features." + category.toString().toLowerCase() + "." + lang);
    }

    /**
     * @return {@code true} if the player is on a server.
     */
    public boolean isOnServer() {
        return !this.client.isInSingleplayer() && !(this.client.getCurrentServerEntry() == null);
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
    protected Text getDoneText() {
        return ScreenTexts.DONE;
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
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow(), GLFW.GLFW_KEY_A);
    }

    /**
     * @return {@code true} if the {@code X} key is being held down.
     */
    protected boolean hasXDown() {
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow(), GLFW.GLFW_KEY_X);
    }

    /**
     * @return {@code true} if the {@code Y} key is being held down.
     */
    protected boolean hasYDown() {
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow(), GLFW.GLFW_KEY_Y);
    }

    /**
     * @return {@code true} if the {@code Z} key is being held down.
     */
    protected boolean hasZDown() {
        return InputUtil.isKeyPressed(MinecraftClient.getInstance().getWindow(), GLFW.GLFW_KEY_Z);
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
    protected void renderCustomText(DrawContext context) {
    }

    /**
     * Render custom objects on a mod screen.
     */
    protected void renderCustomObjects(DrawContext context) {
    }

    /**
     * Renders all {@link SimpleOption} tooltips.
     */
    protected void lockOptionsAndRenderTooltips(DrawContext context, int mouseX, int mouseY) {
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