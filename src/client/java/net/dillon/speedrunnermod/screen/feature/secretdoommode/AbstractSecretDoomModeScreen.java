package net.dillon.speedrunnermod.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

public abstract class AbstractSecretDoomModeScreen extends AbstractFeatureScreen {
    public static int doomModeButtonAlreadyClicked = 0;

    public AbstractSecretDoomModeScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(this.getButtonText(), (button) -> {
            this.getButtonFunction();
        }).build());
        this.addButtonObject(Button.builder(ModTexts.BACK, (button) -> {
            if (this.getPageNumber() == 1 || this.getPageNumber() == 5) {
                this.onClose();
            } else {
                this.minecraft.gui.setScreen(this.getPreviousScreen());
            }
        }).build());
    }

    @Override
    public boolean keyPressed(KeyEvent input) {
        if (input.key() == GLFW.GLFW_KEY_LEFT || input.key() == GLFW.GLFW_KEY_A) {
            if (this.getPageNumber() != 1) {
                this.minecraft.gui.setScreen(this.getPreviousScreen());
            }
            return true;
        } else if (input.key() == GLFW.GLFW_KEY_RIGHT || input.key() == GLFW.GLFW_KEY_D) {
            if (this.getPageNumber() == 4 || this.getPageNumber() == 9) {
                this.getButtonFunction();
            } else if (this.getPageNumber() != this.getMaxPages()) {
                this.minecraft.gui.setScreen(this.getNextScreen());
            }
            return true;
        }
        return super.keyPressed(input);
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.SECRET_DOOM_MODE;
    }

    protected void getButtonFunction() {
        this.minecraft.gui.setScreen(this.getNextScreen());
    }

    protected abstract Component getButtonText();
}