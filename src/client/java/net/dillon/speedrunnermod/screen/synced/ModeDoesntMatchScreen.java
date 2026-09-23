package net.dillon.speedrunnermod.screen.synced;

import net.dillon.speedrunnermod.option.eum.Mode;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.commonConfigHandler;

public class ModeDoesntMatchScreen extends AbstractModScreen {
    private final Mode serverSideMode;

    public ModeDoesntMatchScreen(Mode serverSideMode) {
        super(null, Component.translatable("speedrunnermod.title.mode_doesnt_match_server_setting"));
        this.serverSideMode = serverSideMode;
    }

    @Override
    public void widgets() {
        builder().widthCenter().apply();
        builder().widthLeft(155).apply();

        this.addRenderableWidget(Button.builder(Component.translatable("speedrunnermod.match_mode_to_server"), (buttonWidget) -> {
            commonConfigHandler().update(o -> o.general().mode = this.serverSideMode);
            openScreen(new TimedScreen(null, 5, true));
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.match_mode_to_server.tooltip"))
        ).bounds(builder().captureWidth(), this.height / 6 + 126, 150, 20).build());

        builder().widthCenter().apply();
        builder().widthRight(5).apply();

        this.addRenderableWidget(Button.builder(Component.translatable("gui.toMenu"), (buttonWidget) -> {
            this.onClose();
        }).bounds(builder().captureWidth(), this.height / 6 + 126, 150, 20).build());
    }

    @Override
    public void renderCustomText(GuiGraphicsExtractor graphics) {
        builder().renderHeightTop().apply();
        builder().renderHeightDown(110).apply();

        builder().textCenterAndHeightDown(graphics, Component.translatable("speedrunnermod.mode.doesnt_match_server.line1")).apply();
        builder().textCenterAndHeightDown(graphics, Component.translatable("speedrunnermod.mode.doesnt_match_server.line2")).apply();
    }

    @Override
    public void onClose() {
        openScreen(new JoinMultiplayerScreen(null));
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}