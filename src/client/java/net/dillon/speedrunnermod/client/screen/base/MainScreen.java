package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.client.screen.base.leaderboard.LeaderboardsScreen;
import net.dillon.speedrunnermod.client.screen.base.misc.ExternalScreen;
import net.dillon.speedrunnermod.client.screen.base.misc.ResourcesScreen;
import net.dillon.speedrunnermod.client.screen.base.option.ModOptionsScreen;
import net.dillon.speedrunnermod.client.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.client.screen.feature.secretdoommode.AbstractSecretDoomModeScreen;
import net.dillon.speedrunnermod.client.screen.feature.secretdoommode.PageFive;
import net.dillon.speedrunnermod.client.screen.feature.secretdoommode.PageOne;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.CreditsScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * The {@code main screen} for the Speedrunner Mod, consisting of all the basic resources, such as options, external links, other mods, and more.
 */
@Environment(EnvType.CLIENT)
public class MainScreen extends AbstractModScreen {
    private ButtonWidget optionsButton, featuresButton, resourcesButton, externalButton, creditsButton, leaderboardsButton, doomModeButton;

    public MainScreen(Screen parent) {
        super(parent, ModTexts.TITLE);
    }

    @Override
    protected List<ClickableWidget> buttons() {
        return List.of(
                this.optionsButton,
                this.featuresButton,
                this.resourcesButton,
                this.externalButton,
                this.creditsButton,
                this.leaderboardsButton,
                this.doomModeButton
        );
    }

    @Override
    protected void init() {
        this.optionsButton = ButtonWidget.builder(Text.translatable("menu.options").formatted(getOptionsTextColor()), (button) -> {
            Leaderboards.getCurrentLeaderboardsMode();
            if (options().main.leaderboardsMode.getCurrentValue()) {
                Leaderboards.getCurrentOptions();
            }
            this.client.setScreen(new ModOptionsScreen(this));
        }).build();
        this.featuresButton = ButtonWidget.builder(ModTexts.MENU_FEATURES, (button) -> {
            this.client.setScreen(new FeaturesScreen(this));
        }).build();

        this.resourcesButton = ButtonWidget.builder(ModTexts.MENU_RESOURCES, (button) -> {
            this.client.setScreen(new ResourcesScreen(this));
        }).build();

        this.externalButton = ButtonWidget.builder(ModTexts.MENU_EXTERNAL, (button) -> {
            this.client.setScreen(new ExternalScreen(this));
        }).build();

        this.creditsButton = ButtonWidget.builder(ModTexts.MENU_CREDITS, (button) -> {
            this.client.setScreen(new CreditsScreen(hasShiftDown(), () -> this.client.setScreen(this)));
        }).build();

        this.leaderboardsButton = ButtonWidget.builder(ModTexts.MENU_LEADERBOARDS, (button) -> {
            this.client.setScreen(new LeaderboardsScreen(this));
        }).build();
        this.leaderboardsButton.active = false;

        this.doomModeButton = ButtonWidget.builder(ModTexts.MENU_DOOM_MODE, (button) -> {
            if (AbstractSecretDoomModeScreen.doomModeButtonAlreadyClicked > 0) {
                this.client.setScreen(new PageFive(this.parent));
            } else {
                this.client.setScreen(new PageOne(this.parent));
            }
        }).build();
        this.doomModeButton.visible = isDoomMode();

        super.init();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.optionsButton.isHovered()) {
            if (options().main.leaderboardsMode.getCurrentValue()) {
                if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                    this.renderBasicTooltip(ModTexts.MENU_OPTIONS_ACTION_NEEDED, context, mouseX, mouseY);
                } else {
                    this.renderBasicTooltip(ModTexts.MENU_OPTIONS_SAFE, context, mouseX, mouseY);
                }
            } else {
                this.renderBasicTooltip(ModTexts.MENU_OPTIONS_TOOLTIP, context, mouseX, mouseY);
            }
        }
        if (this.featuresButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_FEATURES_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.resourcesButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_RESOURCES_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.creditsButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_CREDITS_TOOLIP, context, mouseX, mouseY);
        }
        if (this.leaderboardsButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_LEADERBOARDS_DISABLED, context, mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    public String pageId() {
        return "spei0ri09we";
    }

    /**
     * Sets the color of the options button, depending on if leaderboards mode is on, and if the options meet the leaderboards criteria.
     */
    private static Formatting getOptionsTextColor() {
        if (options().main.leaderboardsMode.getCurrentValue()) {
            if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                return Formatting.RED;
            } else {
                return Formatting.GREEN;
            }
        } else {
            return Formatting.AQUA;
        }
    }

    @Override
    protected boolean shouldRenderSpeedrunnerModTitle() {
        return true;
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return true;
    }

    @Override
    public boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return false;
    }
}