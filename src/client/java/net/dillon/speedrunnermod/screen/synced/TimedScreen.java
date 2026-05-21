package net.dillon.speedrunnermod.screen.synced;

import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

import java.util.Timer;
import java.util.TimerTask;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;

public class TimedScreen extends AbstractModScreen {
    private final boolean server;
    public int countdown;
    private Timer timer;

    public TimedScreen(Screen parent, int countdown, boolean server) {
        super(parent, ModTexts.BLANK);
        this.countdown = countdown + 1;
        this.server = server;
    }

    /**
     * Refreshes the screen to countdown and close the game after 5 seconds.
     */
    @Override
    protected void init() {
        this.timer = new Timer();
        this.timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Minecraft.getInstance().execute(() -> {
                    countdown--;
                    if (countdown <= 0) {
                        timer.cancel();
                        Minecraft.getInstance().stop();
                    }
                });
            }
        }, 0, 1000);
    }

    @Override
    public void renderCustomText(GuiGraphicsExtractor context) {
        if (this.server) {
            context.centeredText(this.font, ModTexts.MATCHED_SETTINGS_WITH_SERVER, this.width / 2, 120, CommonColors.WHITE);
        }
        context.centeredText(this.font, Component.translatable("speedrunnermod.restarting_game_timer"), this.width / 2, 140, CommonColors.WHITE);
        context.centeredText(this.font, Component.literal(this.countdown+"..."), this.width / 2, 160, CommonColors.WHITE);
    }

    @Override
    public void onClose() {
        warn("Cannot close this screen.");
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
    public String pageId() {
        return "sdffdf;lkofdspo";
    }

    @Override
    protected int columns() {
        return 1;
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
        return false;
    }
}