package net.dillon.speedrunnermod.client.screen.base.synced;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.packet.server.RequestServerSideOptionsC2SPacket;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;

@Environment(EnvType.CLIENT)
public class MatchSettingsWithServerScreen extends AbstractModScreen {
    private ButtonWidget matchAndRestartButton;

    public MatchSettingsWithServerScreen(Screen parent) {
        super(parent, ModTexts.TITLE_MATCH_SETTINGS_WITH_SERVER);
    }

    @Override
    protected void init() {
        this.matchAndRestartButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.MATCH_AND_RESTART, (button) -> {
            ClientPlayNetworking.send(new RequestServerSideOptionsC2SPacket());
            this.matchAndRestartButton.active = false;
        }).dimensions(this.getButtonsLeftSide(), this.getCustomButtonsHeight(), 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.ABORT, (button) -> {
            this.close();
        }).dimensions(this.getButtonsRightSide(), this.getCustomButtonsHeight(), 150, 20).build());
    }

    @Override
    public void renderCustomText(DrawContext context) {
        if (this.matchAndRestartButton.active) {
            context.drawCenteredTextWithShadow(this.textRenderer, ModTexts.MATCH_SETTINGS_WITH_SERVER_LINE1, this.width / 2, 110, 16777215);
            context.drawCenteredTextWithShadow(this.textRenderer, ModTexts.MATCH_SETTINGS_WITH_SERVER_LINE2, this.width / 2, 130, 16777215);
        } else {
            context.drawCenteredTextWithShadow(this.textRenderer, ModTexts.MATCH_SETTINGS_WITH_SERVER_SYNC_FAILED, this.width / 2, 110, 16777215);
            context.drawCenteredTextWithShadow(this.textRenderer, ModTexts.MATCH_SETTINGS_WITH_SERVER_SYNC_FAILED_LINE2, this.width / 2, 130, 16777215);
        }
    }

    @Override
    public void renderTooltips(DrawContext context, int mouseX, int mouseY) {
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