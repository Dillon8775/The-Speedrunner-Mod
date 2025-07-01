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
import net.minecraft.text.Text;

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
        }).dimensions(this.getButtonsLeftSide(), this.getCustomButtonsHeight(), 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.ABORT, (button) -> {
            this.close();
        }).dimensions(this.getButtonsRightSide(), this.getCustomButtonsHeight(), 150, 20).build());
    }

    @Override
    public void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.match_settings_with_server.line1"), this.width / 2, 110, 16777215);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.match_settings_with_server.line2"), this.width / 2, 130, 16777215);
    }

    @Override
    public void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.matchAndRestartButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.match_and_restart.tooltip"), context, mouseX, mouseY);
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