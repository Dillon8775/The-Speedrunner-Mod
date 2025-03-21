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
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.SpeedrunnerMod.warn;

@Environment(EnvType.CLIENT)
public class PlayingModeOption extends AbstractFeatureScreen {

    public PlayingModeOption(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.BLANK, false, false);
    }

    @Override
    protected void addButtons() {
        this.buttons.add(ButtonWidget.builder(Text.translatable("speedrunnermod.options.playing_mode.easy"), button -> {
            options().main.playingMode = ModOptions.PlayingMode.EASY;
            ModOptions.saveConfig();
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.buttons.add(ButtonWidget.builder(Text.translatable("speedrunnermod.options.playing_mode.normal"), button -> {
            options().main.playingMode = ModOptions.PlayingMode.NORMAL;
            ModOptions.saveConfig();
            restartRequired = true;
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.buttons.add(ButtonWidget.builder(Text.translatable("speedrunnermod.options.playing_mode.doom"), button -> {
            options().main.playingMode = ModOptions.PlayingMode.DOOM;
            ModOptions.saveConfig();
            restartRequired = true;
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.buttons.add(ButtonWidget.builder(Text.translatable("speedrunnermod.back"), button -> {
            this.client.setScreen(this.getPreviousScreen());
        }).build());
    }

    @Override
    protected void renderTooltips(DrawContext context, int x, int y) {
        if (this.buttons.get(0).isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.playing_mode.easy.tooltip"), context, x, y);
        } else if (this.buttons.get(1).isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.playing_mode.normal.tooltip"), context, x, y);
        } else if (this.buttons.get(2).isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.playing_mode.doom.tooltip"), context, x, y);
        }
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
    protected Identifier getImage() {
        return null;
    }

    @Override
    protected Identifier getCraftingRecipeImage() {
        return null;
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