package net.dillon.speedrunnermod.client.screen.base.synced;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;

import java.util.Timer;
import java.util.TimerTask;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;

@Environment(EnvType.CLIENT)
public class TimedScreen extends AbstractModScreen {
    public int countdown;
    private Timer timer;

    public TimedScreen(Screen parent, int countdown) {
        super(parent, ModTexts.BLANK);
        this.countdown = countdown + 1;
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
                MinecraftClient.getInstance().execute(() -> {
                    countdown--;
                    if (countdown <= 0) {
                        timer.cancel();
                        MinecraftClient.getInstance().scheduleStop();
                    }
                });
            }
        }, 0, 1000);
    }

    @Override
    public void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, ModTexts.MATCHED_SETTINGS_WITH_SERVER, this.width / 2, 120, Colors.WHITE);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.restarting_game_timer"), this.width / 2, 140, Colors.WHITE);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal(this.countdown+"..."), this.width / 2, 160, Colors.WHITE);
    }

    @Override
    public void close() {
        warn("Cannot close this screen.");
    }

    /**
     * Default resize method.
     */
    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
        this.refreshWidgetPositions();
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