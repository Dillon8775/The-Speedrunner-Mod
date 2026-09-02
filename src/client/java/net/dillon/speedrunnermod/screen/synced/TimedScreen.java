package net.dillon.speedrunnermod.screen.synced;

import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

import java.util.Timer;
import java.util.TimerTask;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class TimedScreen extends AbstractModScreen {
    private final Screen parent;
    private final boolean server;
    public int countdown;
    private boolean canceled;
    private Timer timer;

    public TimedScreen(Screen parent, int countdown, boolean server) {
        super(parent, Texts.BLANK);
        this.parent = parent;
        this.countdown = countdown + 1;
        this.server = server;
    }

    /**
     * Refreshes the screen to countdown and close the game after 5 seconds.
     */
    @Override
    protected void init() {
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

        this.addRenderableWidget(Button.builder(Component.translatable("gui.cancel"), (buttonWidget) -> {
            this.onClose();
        }).bounds(this.width / 2 - 50, this.height / 6 + 126, 150, 20).build());
    }

    @Override
    public void renderCustomText(GuiGraphicsExtractor graphics) {
        if (this.server) {
            graphics.centeredText(this.font, ModTexts.MATCHED_SETTINGS_WITH_SERVER, this.width / 2, 120, CommonColors.WHITE);
        }
        graphics.centeredText(this.font, Component.translatable("speedrunnermod.restarting_game_timer"), this.width / 2, 140, CommonColors.WHITE);
        graphics.centeredText(this.font, Component.literal(this.countdown+"..."), this.width / 2, 160, CommonColors.WHITE);
    }

    @Override
    public void onClose() {
        openScreen(this.minecraft.level == null ? new TitleScreen() : this.parent);
        this.canceled = true;
    }

    /**
     * Default resize method.
     */
    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.repositionElements();
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }
}