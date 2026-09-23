package net.dillon.speedrunnermod.screen.synced;

import net.dillon.dillonlib.screen.BasicDillonLibScreen;
import net.dillon.speedrunnermod.option.eum.Mode;
import net.dillon.speedrunnermod.util.ClientModUtil;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.speedrunnermod.main.CommonMain.commonConfigHandler;

public class ModeDoesntMatchScreen extends BasicDillonLibScreen {
    private final Mode serverSideMode;

    public ModeDoesntMatchScreen(Mode serverSideMode) {
        super(Component.translatable("speedrunnermod.title.mode_doesnt_match_server_setting"));
        this.serverSideMode = serverSideMode;
    }

    @Override
    public void onClose() {
        openScreen(new JoinMultiplayerScreen(null));
    }

    @Override
    public void widgets() {
        builder().heightCenter().apply();
        builder().heightDown().apply();
        builder().widthCenter().apply();
        builder().widthLeft(155).apply();

        this.addRenderableWidget(Button.builder(Component.translatable("speedrunnermod.match_mode_to_server"), (buttonWidget) -> {
            commonConfigHandler().update(o -> o.general().mode = this.serverSideMode);
            openScreen(new TimedScreen(null, 5, true));
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.match_mode_to_server.tooltip"))
        ).bounds(builder().captureWidth(), builder().captureHeight(), 150, 20).build());

        builder().widthCenter().apply();
        builder().widthRight(5).apply();

        this.addRenderableWidget(Button.builder(Component.translatable("gui.toMenu"), (buttonWidget) -> {
            this.onClose();
        }).bounds(builder().captureWidth(), builder().captureHeight(), 150, 20).build());
    }

    @Override
    public void drawGraphics(GuiGraphicsExtractor graphics) {
        builder().graphicsHeightTop().apply();
        builder().graphicsWidthCenter().apply();
        builder().graphicsWidthLeft(128).apply();

        ClientModUtil.renderSpeedrunnerModLogo(
                graphics,
                builder().captureGraphicsWidth(),
                true
        );

        builder().graphicsHeightDown(100).apply();

        builder().textCenterAndGraphicsHeightDown(graphics, Component.translatable("speedrunnermod.mode.doesnt_match_server.line1")).apply();
        builder().textCenterAndGraphicsHeightDown(graphics, Component.translatable("speedrunnermod.mode.doesnt_match_server.line2")).apply();
    }
}