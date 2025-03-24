package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.client.screen.base.feature.FeaturesScreen;
import net.dillon.speedrunnermod.client.screen.base.text.ChangelogsScreen;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.option.Leaderboards;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * The {@code main screen} for the Speedrunner Mod, consisting of all the basic resources, such as options, external links, other mods, and more.
 */
@Environment(EnvType.CLIENT)
public class MainScreen extends AbstractModScreen {

    public MainScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE);
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttons.add(0, ButtonWidget.builder(Text.translatable("menu.options").formatted(getOptionsTextColor()), (button) -> {
            RestartRequiredScreen.getCurrentOptions();
            Leaderboards.getCurrentLeaderboardsMode();
            if (options().main.leaderboardsMode) {
                Leaderboards.getCurrentOptions();
            }
            this.client.setScreen(new ModOptionsScreen(this, options));
        }).build());

        this.buttons.add(1, ButtonWidget.builder(ModTexts.MENU_FEATURES, (button) -> {
            this.client.setScreen(new FeaturesScreen(this, options));
        }).build());

        this.buttons.add(2, ButtonWidget.builder(ModTexts.MENU_CHANGELOGS, (button) -> {
            this.client.setScreen(new ChangelogsScreen(this, options));
        }).build());

        this.buttons.add(3, ButtonWidget.builder(ModTexts.MENU_RESOURCES, (button) -> {
            this.client.setScreen(new ResourcesScreen(this, options));
        }).build());

        this.buttons.add(4, ButtonWidget.builder(ModTexts.MENU_EXTERNAL, (button) -> {
            this.client.setScreen(new ExternalScreen(this, options));
        }).build());

        this.buttons.add(5, ButtonWidget.builder(ModTexts.MENU_CREDITS, (button) -> {
            this.client.setScreen(new ModCreditsScreen(this, options));
        }).build());

        this.buttons.add(6, ButtonWidget.builder(ModTexts.MENU_LEADERBOARDS, (button) -> {
            this.client.setScreen(new LeaderboardsScreen(this, this.options));
        }).build());
        this.buttons.get(6).active = false;

        this.buttons.add(7, ButtonWidget.builder(ModTexts.EASIER_SPEEDRUNNING_MOD, (button) -> {
            this.openLink(ModLinks.EASIER_SPEEDRUNNING_WIKI, true);
        }).build());

        this.buttons.add(8, ButtonWidget.builder(ModTexts.MENU_DOOM_MODE, (button) -> {
            if (SecretDoomModeScreen.doomModeButtonAlreadyClicked > 0) {
                this.client.setScreen(new SecretDoomModeScreen.ScreenFive(this, options));
            } else {
                this.client.setScreen(new SecretDoomModeScreen(this, options));
            }
        }).build());
        this.buttons.get(8).visible = options().main.playingMode.doom();

        super.init();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.buttons.get(0).isHovered()) {
            if (options().main.leaderboardsMode) {
                if (!Leaderboards.isEligibleForLeaderboardRuns()) {
                    this.renderBasicTooltip(ModTexts.MENU_OPTIONS_ACTION_NEEDED, context, mouseX, mouseY);
                } else {
                    this.renderBasicTooltip(ModTexts.MENU_OPTIONS_SAFE, context, mouseX, mouseY);
                }
            } else {
                this.renderBasicTooltip(ModTexts.MENU_OPTIONS_TOOLTIP, context, mouseX, mouseY);
            }
        }
        if (this.buttons.get(1).isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_FEATURES_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(2).isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_CHANGELOGS_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(3).isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_RESOURCES_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(6).isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_LEADERBOARDS_DISABLED, context, mouseX, mouseY);
        }
        if (this.buttons.get(7).isHovered()) {
            this.renderBasicTooltip(ModTexts.EASIER_SPEEDRUNNING_MOD_TOOLTIP, context, mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    protected void renderCustomObjects(DrawContext context) {
        int middle = this.width / 2 - 69;
        int height = 10;
        context.drawTexture(RenderLayer::getGuiTextured, Identifier.of("speedrunnermod:textures/gui/speedrunner_mod.png"), middle, height, 0.0F, 0.0F, 129, 16, 129, 16);
    }

    @Override
    protected String pageId() {
        return "spei0ri09we";
    }

    /**
     * Sets the color of the options button, depending on if leaderboards mode is on, and if the options meet the leaderboards criteria.
     */
    private static Formatting getOptionsTextColor() {
        if (options().main.leaderboardsMode) {
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
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return true;
    }

    @Override
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return false;
    }
}