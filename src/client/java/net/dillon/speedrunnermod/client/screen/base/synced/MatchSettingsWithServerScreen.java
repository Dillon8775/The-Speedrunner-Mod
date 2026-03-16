package net.dillon.speedrunnermod.client.screen.base.synced;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.packet.server.RequestServerSideOptionsC2SPacket;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.util.CommonColors;

public class MatchSettingsWithServerScreen extends AbstractModScreen {
    private Button matchAndRestartButton;

    public MatchSettingsWithServerScreen(Screen parent) {
        super(parent, ModTexts.TITLE_MATCH_SETTINGS_WITH_SERVER);
    }

    @Override
    protected void init() {
        this.matchAndRestartButton = this.addRenderableWidget(Button.builder(ModTexts.MATCH_AND_RESTART, (button) -> {
            ClientPlayNetworking.send(new RequestServerSideOptionsC2SPacket());
            this.matchAndRestartButton.active = false;
        }).bounds(this.getButtonsLeftSide(), this.getCustomButtonsHeight(), 150, 20).build());
        this.addRenderableWidget(Button.builder(ModTexts.ABORT, (button) -> {
            this.onClose();
        }).bounds(this.getButtonsRightSide(), this.getCustomButtonsHeight(), 150, 20).build());
    }

    @Override
    public void renderCustomText(GuiGraphics context) {
        if (this.matchAndRestartButton.active) {
            context.drawCenteredString(this.font, ModTexts.MATCH_SETTINGS_WITH_SERVER_LINE1, this.width / 2, 110, CommonColors.WHITE);
            context.drawCenteredString(this.font, ModTexts.MATCH_SETTINGS_WITH_SERVER_LINE2, this.width / 2, 130, CommonColors.WHITE);
        } else {
            context.drawCenteredString(this.font, ModTexts.MATCH_SETTINGS_WITH_SERVER_SYNC_FAILED, this.width / 2, 110, CommonColors.WHITE);
            context.drawCenteredString(this.font, ModTexts.MATCH_SETTINGS_WITH_SERVER_SYNC_FAILED_LINE2, this.width / 2, 130, CommonColors.WHITE);
        }
    }

    @Override
    public void renderTooltips(GuiGraphics context, int mouseX, int mouseY) {
        if (this.matchAndRestartButton.isHovered()) {
            if (this.matchAndRestartButton.active) {
                this.renderBasicTooltip(ModTexts.MATCH_AND_RESTART_TOOLTIP, context, mouseX, mouseY);
            } else {
                this.renderBasicTooltip(ModTexts.MATCH_SETTINGS_WITH_SERVER_FAILED, context, mouseX, mouseY);
            }
        }
    }

    @Override
    public boolean shouldRenderTooltips() {
        return true;
    }

    @Override
    public String pageId() {
        return "sef;kodrgiojer";
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
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