package net.dillon.speedrunnermod.client.screen.base.synced;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.multiplayer.MultiplayerScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.saveDedicatedServerChanges;

@Environment(EnvType.CLIENT)
public class PlayingModeDoesntMatchScreen extends AbstractModScreen {
    private ButtonWidget matchPlayingModeToServerButton;
    private final ModOptions.PlayingMode serverSidePlayingMode;

    public PlayingModeDoesntMatchScreen(Screen parent, ModOptions.PlayingMode serverSidePlayingMode) {
        super(parent, ModTexts.TITLE_PLAYING_MODE_DOESNT_MATCH_SERVER_SETTING);
        this.serverSidePlayingMode = serverSidePlayingMode;
    }

    @Override
    protected void init() {
        this.matchPlayingModeToServerButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.MATCH_PLAYING_MODE_TO_SERVER, (buttonWidget) -> {
            options().main.playingMode.set(this.serverSidePlayingMode);
            saveDedicatedServerChanges();
            this.client.setScreen(new TimedScreen(null, 5));
        }).dimensions(this.getButtonsLeftSide(), this.getCustomButtonsHeight(), 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(Text.translatable("gui.toMenu"), (buttonWidget) -> {
            this.close();
        }).dimensions(this.getButtonsRightSide(), this.getCustomButtonsHeight(), 150, 20).build());
    }

    @Override
    public void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.playing_mode.doesnt_match_server.line1"), this.width / 2, 110, 16777215);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.playing_mode.doesnt_match_server.line2"), this.width / 2, 130, 16777215);
    }

    @Override
    public void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.matchPlayingModeToServerButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.match_playing_mode_to_server.tooltip"), context, mouseX, mouseY);
        }
    }

    @Override
    public void close() {
        this.client.setScreen(new MultiplayerScreen(null));
    }

    @Override
    public boolean shouldRenderTooltips() {
        return true;
    }

    @Override
    public String pageId() {
        return "cdxgfiojkdfoj";
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