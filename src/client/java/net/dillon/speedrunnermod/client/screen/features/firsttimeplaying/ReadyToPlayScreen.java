package net.dillon.speedrunnermod.client.screen.features.firsttimeplaying;

import net.dillon.speedrunnermod.client.screen.features.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.features.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.features.ScreenType;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.SpeedrunnerMod.warn;

@Environment(EnvType.CLIENT)
public class ReadyToPlayScreen extends AbstractFeatureScreen {

    public ReadyToPlayScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.BLANK, false, false);
    }

    @Override
    protected void addButtons() {
        this.buttons.add(ButtonWidget.builder(Text.translatable("speedrunnermod.enter_tutorial_mode"), button -> {
            options().main.tutorialMode = true;
            ModOptions.saveConfig();
            restartRequired = true;
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.buttons.add(ButtonWidget.builder(Text.translatable("speedrunnermod.begin_playing"), button -> {
            if (restartRequired) {
                this.client.setScreen(this.getNextScreen());
            } else {
                this.client.setScreen(new TitleScreen());
            }
        }).build());
        this.buttons.add(ButtonWidget.builder(Text.translatable("speedrunnermod.back"), button -> {
            this.client.setScreen(this.getPreviousScreen());
        }).build());
    }

    @Override
    protected void renderTooltips(DrawContext context, int x, int y) {
        if (this.buttons.get(1).isHovered()) {
            this.renderBasicTooltip(Text.translatable("speedrunnermod.begin_playing.tooltip"), context, x, y);
        }
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