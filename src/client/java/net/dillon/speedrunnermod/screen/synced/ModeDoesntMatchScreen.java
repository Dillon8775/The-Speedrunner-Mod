package net.dillon.speedrunnermod.screen.synced;

import net.dillon.speedrunnermod.option.eum.Mode;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.multiplayer.JoinMultiplayerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.commonConfigHandler;

public class ModeDoesntMatchScreen extends AbstractModScreen {
    private final Mode serverSideMode;

    public ModeDoesntMatchScreen(Mode serverSideMode) {
        super(null, Component.translatable("speedrunnermod.title.mode_doesnt_match_server_setting"));
        this.serverSideMode = serverSideMode;
    }

    @Override
    protected void init() {
        this.addRenderableWidget(Button.builder(Component.translatable("speedrunnermod.match_mode_to_server"), (buttonWidget) -> {
            commonConfigHandler().update(o -> o.general().mode = this.serverSideMode);
            openScreen(new TimedScreen(null, 5, true));
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.match_mode_to_server.tooltip"))
        ).bounds(this.width / 2 - 155, this.height / 6 + 126, 150, 20).build());

        this.addRenderableWidget(Button.builder(Component.translatable("gui.toMenu"), (buttonWidget) -> {
            this.onClose();
        }).bounds(this.width / 2 + 5, this.height / 6 + 126, 150, 20).build());
    }

    @Override
    public void renderCustomText(GuiGraphicsExtractor graphics) {
        graphics.centeredText(this.font, Component.translatable("speedrunnermod.mode.doesnt_match_server.line1"), this.width / 2, 110, CommonColors.WHITE);
        graphics.centeredText(this.font, Component.translatable("speedrunnermod.mode.doesnt_match_server.line2"), this.width / 2, 130, CommonColors.WHITE);
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