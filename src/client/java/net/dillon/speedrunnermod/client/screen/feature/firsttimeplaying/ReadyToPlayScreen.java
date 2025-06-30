package net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.saveDedicatedServerChanges;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;

@Environment(EnvType.CLIENT)
public class ReadyToPlayScreen extends AbstractFeatureScreen {
    private ButtonWidget beginPlayingButton;

    public ReadyToPlayScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.enter_tutorial_mode"), button -> {
            clientOptions().client.tutorialMode = true;
            saveDedicatedServerChanges();
            restartRequired = true;
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.beginPlayingButton = this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.begin_playing"), button -> {
            if (restartRequired) {
                this.client.setScreen(this.getNextScreen());
            } else {
                clientOptions().client.firstTimePlaying = false;
                saveClientChanges();
                this.client.setScreen(new TitleScreen());
            }
        }).build());
        this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.back"), button -> {
            this.client.setScreen(this.getPreviousScreen());
        }).build());
    }

    @Override
    protected void renderTooltips(DrawContext context, int x, int y) {
        if (this.beginPlayingButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.begin_playing.tooltip"), context, x, y);
        }
        super.renderTooltips(context, x, y);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_RIGHT || keyCode == GLFW.GLFW_KEY_D) {
            warn("Please choose an option!");
            return true;
        } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    @Override
    public @NotNull String linesKey() {
        return "ready_to_play";
    }

    @Override
    public int getPageNumber() {
        return 4;
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.FIRST_TIME_PLAYING;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FIRST_TIME_PLAYING;
    }
}