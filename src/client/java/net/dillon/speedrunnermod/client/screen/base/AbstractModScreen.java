package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.client.screen.CustomButtonListWidget;
import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.util.ButtonSide;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.packet.ClientModPackets;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.gui.widget.OptionListWidget;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;

/**
 * Used to create any {@code Speedrunner Mod} screens.
 */
@Environment(EnvType.CLIENT)
public abstract class AbstractModScreen extends BaseModScreen {
    protected boolean alreadySettingToIneligibleScreen = false;
    protected File configFile; // This returns null unless the screen is an options screen
    protected final File configDirectory = new File(FabricLoader.getInstance().getConfigDir().toUri()); // The directory for the speedrunner mod's configuration file
    protected final Screen parent;
    protected ButtonWidget helpButton, saveButton, openOptionsFileButton, resetOptionsButton, openOptionsDirectoryButton, doneButton;
    public OptionListWidget optionList; // The list of all the options for a speedrunner mod screen, returns null if the screen is not an options screen
    protected CustomButtonListWidget buttonList; // The list of all the buttons for a speedrunner mod screen, returns null if there is no need for a scrollable section
    protected final List<ClickableWidget> featureButtons = new ArrayList<>();

    public AbstractModScreen(Screen parent, Text title) {
        super(parent, title);
        this.parent = parent;
    }

    @Override
    protected void init() {
        if (isOptionsScreen()) {
            this.optionList = this.addDrawableChild(new OptionListWidget(this.client, this.width, this));

            this.saveButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.SAVE, (button) -> {
                this.close();
            }).dimensions(this.getButtonsLeftSide(), this.getDoneButtonsHeight(), 100, 20).build());

            this.configFile = configHandler().getConfigFile();
            this.openOptionsFileButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.MENU_OPEN_OPTIONS_FILE, (button) -> {
                this.close();
                Util.getOperatingSystem().open(this.configFile);
            }).dimensions(this.getButtonsMiddle(), this.getDoneButtonsHeight(), 100, 20).build());

            this.resetOptionsButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.RESET, (button) -> {
                this.client.setScreen(new ResetOptionsConfirmScreen(this.parent, false));
            }).dimensions(this.getButtonsRightSide(), this.getDoneButtonsHeight(), 100, 20).build());

            this.helpButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, (button) -> {
                this.openLink(ModLinks.OPTIONS_EXPLANATION, true);
            }).dimensions(this.getButtonsRightSide() + 104, this.getDoneButtonsHeight(), 20, 20).build());
            this.openOptionsDirectoryButton = this.addDrawableChild(ButtonWidget.builder(Text.literal("D."), (button) -> {
                this.close();
                Util.getOperatingSystem().open(this.configDirectory);
            }).dimensions(this.getButtonsRightSide() + 128, this.getDoneButtonsHeight(), 20, 20).build());
        } else {
            if (!this.buttons().isEmpty()) {
                this.initializeCustomButtonListWidget();
                this.buttonList.addAll(this.buttons());
                this.addSelectableChild(this.buttonList);
            }
            this.doneButton = this.addDrawableChild(ButtonWidget.builder(this.getDoneText(), (button) -> this.close()).dimensions(this.width / 2 - 100, this.getDoneButtonsHeight(), 200, 20).build());
        }
    }

    @Override
    public void close() {
        if (this.isOptionsScreen()) {
            saveAllChanges();
            if (this.client.world != null) {
                ClientModPackets.sendNewC2SOptions();
            }

            LeaderboardsIneligibleScreen.needsRestart = false;
            LeaderboardsIneligibleScreen.needsRestartFromEnablingLeaderboardsMode = false;
            this.alreadySettingToIneligibleScreen = false;

            if (options().main.leaderboardsMode) {
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

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        this.renderCustomText(context);

        if (this.shouldRenderVersionText()) {
            int leftSide = this.width / 2 - 155;
            int rightSide = leftSide + 160;
            int farRightSide = rightSide + 267;
            int height = this.height - 24;
            context.drawCenteredTextWithShadow(this.textRenderer, SpeedrunnerMod.VERSION, farRightSide, height, 16777215);
        }

        if (this.shouldRenderTitleText()) {
            context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 15, 16777215);
        }

        if (this.isOptionsScreen()) {
            context.drawTexture(RenderLayer::getGuiTextured, ofSpeedrunnerMod("textures/gui/question_mark.png"), helpButton.getX() + 2, helpButton.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
        }

        this.renderCustomObjects(context);
        this.renderOptionTooltips(context, mouseX, mouseY);
        if (!this.buttons().isEmpty() || this.shouldRenderTooltips()) {
            this.renderTooltips(context, mouseX, mouseY);
        }
    }

    /**
     * Initializes the custom button list widget. Used similarly to an {@link OptionListWidget}, but for normal buttons. Also see {@link AbstractScrollableScreen} for the top Y.
     */
    protected void initializeCustomButtonListWidget() {
        this.buttonList = this.addDrawableChild(new CustomButtonListWidget(this.client, this.width, this));
    }

    /**
     * Sets the screen to the {@code parent} screen and resizes it correctly.
     */
    protected void setParentAndResize() {
        this.parent.resize(this.client, this.width, this.height);
        this.client.setScreen(this.parent);
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
                this.renderBasicTooltip(ModTexts.OPEN_OPTIONS_FILE_TOOLTIP, context, mouseX, mouseY);
            }
            if (this.helpButton.isHovered()) {
                this.renderBasicTooltip(ModTexts.HELP_TOOLTIP, context, mouseX, mouseY);
            }
            if (this.openOptionsDirectoryButton.isHovered()) {
                this.renderBasicTooltip(ModTexts.DIRECTORY_TOOLTIP, context, mouseX, mouseY);
            }
        }
    }

    /**
     * Renders a tooltip for a {@link SimpleOption}. Only for simple options that can be activated/deactivated.
     */
    protected void renderOptionTooltip(int buttonListIndex, ButtonSide buttonSide, boolean bl, Text tooltipWhenBooleanIsTrue, Text tooltipWhenBooleanIsFalse, DrawContext context, int mouseX, int mouseY) {
        OptionListWidget.WidgetEntry widget = this.optionList.children().get(buttonListIndex);
        if (buttonSide.equals(ButtonSide.LARGE) || buttonSide.equals(ButtonSide.LEFT) ? widget.widgets.getFirst().isHovered() : widget.widgets.getLast().isHovered()) {
            if (bl) {
                this.renderBasicTooltip(tooltipWhenBooleanIsTrue, context, mouseX, mouseY);
            } else {
                this.renderBasicTooltip(tooltipWhenBooleanIsFalse, context, mouseX, mouseY);
            }
        }
    }

    /**
     * Iterate through all {@link AbstractFeatureScreen}s to add to the main feature screen lists.
     */
    @ChatGPT(Credit.FULL_CREDIT)
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

    /**
     * The list of buttons to add.
     * @return {@code featureButtons list} (if it's not empty, for feature screen categories), otherwise returns an empty list.
     * <p>Override this method to create a screen with a {@link CustomButtonListWidget}, and add the buttons to this list to display them.</p>
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
     * Deactivates certain buttons based on certain boolean values.
     * <p>Indexes go from top-down-left, then right side.
     * <p>Do not call if {@code optionList} is {@code null.}</p>
     */
    protected void deactivateOptionIf(int buttonListIndex, ButtonSide buttonSide, boolean option) {
        try {
            if (this.optionList != null) {
                for (int i = 0; i < this.optionList.children().size(); i++) {
                    OptionListWidget.WidgetEntry widget = this.optionList.children().get(i);
                    if (i == buttonListIndex && !option) {
                        widget.widgets.get(ButtonSide.buttonIndexes(buttonSide)).active = false;
                    }
                }
            } else {
                throw new NullPointerException();
            }
        } catch (NullPointerException n) {
            SpeedrunnerMod.error("\"optionList\" variable cannot be null on \"deactivateButtonIf\" call.");
            this.client.scheduleStop();
            n.printStackTrace();
        }
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
     * Returns the height of buttons on a screen.
     * <p>To add another row of buttons, add {@code 24} to this variable.</p>
     * <p>For example, <pre>height += 24;</pre>
     */
    protected int getButtonsHeight() {
        return this.height / 6 - 12;
    }

    /**
     * Returns the {@code "done"} buttons height, typically at the bottom of a screen.
     */
    protected int getDoneButtonsHeight() {
        return this.height - 29;
    }

    /**
     * Gets the text that should be displayed on the typical "Done" button.
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
     * Render custom text on a mod screen.
     * <p>NEVER {@link Override} the render basic method, use this method instead.</p>
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
    protected void renderOptionTooltips(DrawContext context, int mouseX, int mouseY) {
    }

    /**
     * Returns the page ID of a screen. This is used to determined the refreshed screen.
     * @return
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