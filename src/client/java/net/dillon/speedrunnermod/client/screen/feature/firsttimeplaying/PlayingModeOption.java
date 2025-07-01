package net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.*;

@Environment(EnvType.CLIENT)
public class PlayingModeOption extends AbstractFeatureScreen {
    private ButtonWidget easyButton, balancedButton, doomButton;

    public PlayingModeOption(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.easyButton = this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.options.playing_mode.easy"), button -> {
            options().main.playingMode.set(ModOptions.PlayingMode.EASY);
            saveDedicatedServerChanges();
            restartRequired = false;
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.balancedButton = this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.options.playing_mode.balanced"), button -> {
            options().main.playingMode.set(ModOptions.PlayingMode.BALANCED);
            saveDedicatedServerChanges();
            restartRequired = true;
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.doomButton = this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.options.playing_mode.doom"), button -> {
            options().main.playingMode.set(ModOptions.PlayingMode.DOOM);
            saveDedicatedServerChanges();
            restartRequired = true;
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.back"), button -> {
            this.client.setScreen(this.getPreviousScreen());
        }).build());
    }

    @Override
    protected void renderTooltips(DrawContext context, int x, int y) {
        if (this.easyButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.playing_mode.easy.tooltip"), context, x, y);
        } else if (this.balancedButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.playing_mode.balanced.tooltip"), context, x, y);
        } else if (this.doomButton.isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.playing_mode.doom.tooltip"), context, x, y);
        }
        super.renderTooltips(context, x, y);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_RIGHT || keyCode == GLFW.GLFW_KEY_D) {
             warn("Please select a playing mode!");
             return true;
        } else {
            return super.keyPressed(keyCode, scanCode, modifiers);
        }
    }

    @Override
    public @NotNull String linesKey() {
        return "playing_mode_option";
    }

    @Override
    public int getPageNumber() {
        return 3;
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