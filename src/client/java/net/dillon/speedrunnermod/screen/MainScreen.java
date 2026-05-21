package net.dillon.speedrunnermod.screen;

import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.screen.leaderboard.LeaderboardsScreen;
import net.dillon.speedrunnermod.screen.misc.ExternalScreen;
import net.dillon.speedrunnermod.screen.misc.ResourcesScreen;
import net.dillon.speedrunnermod.screen.option.ModOptionsScreen;
import net.dillon.speedrunnermod.screen.secretdoommode.AbstractSecretDoomModeScreen;
import net.dillon.speedrunnermod.screen.secretdoommode.UmScreen;
import net.dillon.speedrunnermod.screen.secretdoommode.YouArentReadyForThisScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.WinScreen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * The {@code main screen} for the Speedrunner Mod, consisting of all the basic resources, such as options, external links, other mods, and more.
 */
public class MainScreen extends AbstractModScreen {
    private Button optionsButton, featuresButton, resourcesButton, externalButton, creditsButton, leaderboardsButton, doomModeButton;

    public MainScreen(Screen parent) {
        super(parent, ModTexts.TITLE);
    }

    @Override
    protected List<AbstractWidget> buttons() {
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
        this.optionsButton = Button.builder(Component.translatable("menu.options").withStyle(getOptionsTextColor()), (button) -> {
            Leaderboards.getCurrentLeaderboardsMode();
            if (options().main.leaderboardsMode.getCurrentValue()) {
                Leaderboards.getCurrentOptions();
            }
            this.minecraft.setScreen(new ModOptionsScreen(this));
        }).build();
        this.featuresButton = Button.builder(ModTexts.MENU_FEATURES, (button) -> {
        }).build();
        this.featuresButton.active = false;

        this.resourcesButton = Button.builder(ModTexts.MENU_RESOURCES, (button) -> {
            this.minecraft.setScreen(new ResourcesScreen(this));
        }).build();

        this.externalButton = Button.builder(ModTexts.MENU_EXTERNAL, (button) -> {
            this.minecraft.setScreen(new ExternalScreen(this));
        }).build();

        this.creditsButton = Button.builder(ModTexts.MENU_CREDITS, (button) -> {
            this.minecraft.setScreen(new WinScreen(Minecraft.getInstance().hasShiftDown(), () -> this.minecraft.setScreen(this)));
        }).build();

        this.leaderboardsButton = Button.builder(ModTexts.MENU_LEADERBOARDS, (button) -> {
            this.minecraft.setScreen(new LeaderboardsScreen(this));
        }).build();
        this.leaderboardsButton.active = false;

        this.doomModeButton = Button.builder(ModTexts.MENU_DOOM_MODE, (button) -> {
            if (AbstractSecretDoomModeScreen.doomModeButtonAlreadyClicked > 0) {
                this.minecraft.setScreen(new UmScreen(this.parent));
            } else {
                this.minecraft.setScreen(new YouArentReadyForThisScreen(this.parent));
            }
        }).build();
        this.doomModeButton.visible = isDoomMode();

        super.init();
    }

    @Override
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
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
    private static ChatFormatting getOptionsTextColor() {
        if (options().main.leaderboardsMode.getCurrentValue()) {
            if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                return ChatFormatting.RED;
            } else {
                return ChatFormatting.GREEN;
            }
        } else {
            return ChatFormatting.AQUA;
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