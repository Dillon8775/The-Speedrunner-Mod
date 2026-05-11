package net.dillon.speedrunnermod.client.screen.base.misc;

import net.dillon.speedrunnermod.client.option.Leaderboards;
import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;

public class SpeedrunIGTMissingScreen extends AbstractModScreen {
    protected Button leftButton, middleButton, rightButton;

    public SpeedrunIGTMissingScreen(Screen parent) {
        super(parent, ModTexts.TITLE_SPEEDRUN_IGT_MISSING);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.leftButton = this.addRenderableWidget(Button.builder(ModTexts.DOWNLOAD_AND_INSTALL, (buttonWidget) -> {
            this.openLink(ModLinks.SPEEDRUNIGT, false);
        }).bounds(this.getButtonsLeftSide(), height, 100, 20).build());
        this.middleButton = this.addRenderableWidget(Button.builder(ModTexts.DISABLE_LEADERBOARDS_MODE_AND_RESTART, (buttonWidget) -> {
            Leaderboards.disableLeaderboardsMode();
            this.minecraft.stop();
        }).bounds(this.getButtonsMiddle(), height, 100, 20).build());
        this.rightButton = this.addRenderableWidget(Button.builder(ModTexts.CLOSE_GAME, (buttonWidget) -> {
            info("Closing game!");
            this.minecraft.stop();
        }).bounds(this.getButtonsRightSide(), height, 100, 20).build());
    }

    @Override
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (this.leftButton.isHovered()) {
            this.renderBasicTooltip(Component.translatable("speedrunnermod.download_and_install.tooltip"), context, mouseX, mouseY);
        }
        if (this.middleButton.isHovered()) {
            this.renderBasicTooltip(Component.translatable("speedrunnermod.disable_leaderboards_mode_and_restart.tooltip"), context, mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    public void onClose() {
        warn("Cannot close screen! Please select an option.");
    }

    @Override
    public void renderCustomText(GuiGraphicsExtractor context) {
        context.centeredText(this.font, Component.translatable("speedrunnermod.speedrun_igt_missing.line1"), this.width / 2, 90, CommonColors.WHITE);
        context.centeredText(this.font, Component.translatable("speedrunnermod.speedrun_igt_missing.line2"), this.width / 2, 110, CommonColors.WHITE);
        context.centeredText(this.font, Component.translatable("speedrunnermod.speedrun_igt_missing.line3"), this.width / 2, 130, CommonColors.WHITE);
    }

    @Override
    public String pageId() {
        return "goei09ew09ads";
    }

    @Override
    protected int columns() {
        return 3;
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
        return true;
    }
}