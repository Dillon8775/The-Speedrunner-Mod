package net.dillon.speedrunnermod.screen.synced;

import net.dillon.dillonlib.screen.BasicDillonLibScreen;
import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.util.ClientModUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;

import java.util.Timer;
import java.util.TimerTask;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class TimedScreen extends BasicDillonLibScreen {
    private final Screen parent;
    private final boolean server;
    public int countdown;
    private boolean canceled;
    private Timer timer;

    public TimedScreen(Screen parent, int countdown, boolean server) {
        super(Texts.BLANK);
        this.parent = parent;
        this.countdown = countdown + 1;
        this.server = server;
    }

    /**
     * Refreshes the screen to countdown and close the game after 5 seconds.
     */
    @Override
    public void widgets() {
        this.canceled = false;
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Minecraft.getInstance().execute(() -> {
                    if (canceled) {
                        timer.cancel();
                    }
                    countdown--;
                    if (countdown <= 0) {
                        timer.cancel();
                        Minecraft.getInstance().stop();
                    }
                });
            }
        }, 0, 1000);

        builder().heightCenter().apply();
        builder().heightDown().apply();
        builder().widthCenter().apply();
        builder().widthLeft(100).apply();

        this.addRenderableWidget(Button.builder(Component.translatable("gui.cancel"), (buttonWidget) -> {
            this.onClose();
        }).bounds(builder().captureWidth(), builder().captureHeight(), 200, 20).build());
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

        builder().graphicsHeightTop().apply();
        builder().graphicsHeightDown(120).apply();

        if (this.server) {
            builder().textCenterAndGraphicsHeightDown(graphics, ModTexts.MATCHED_SETTINGS_WITH_SERVER).apply();
        } else {
            builder().graphicsHeightDown();
        }
        builder().textCenterAndGraphicsHeightDown(graphics, Component.translatable("speedrunnermod.restarting_game_timer")).apply();
        builder().textCenterAndGraphicsHeightDown(graphics, Component.literal(this.countdown+"..."));
    }

    @Override
    public void onClose() {
        openScreen(this.minecraft.level == null ? new TitleScreen() : this.parent);
        this.canceled = true;
    }
}