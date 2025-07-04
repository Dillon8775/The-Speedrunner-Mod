package net.dillon.speedrunnermod.client.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;
import org.lwjgl.glfw.GLFW;

@Environment(EnvType.CLIENT)
public abstract class AbstractSecretDoomModeScreen extends AbstractFeatureScreen {
    public static int doomModeButtonAlreadyClicked = 0;

    public AbstractSecretDoomModeScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(ButtonWidget.builder(this.getButtonText(), (button) -> {
            this.getButtonFunction();
        }).build());
        this.addButtonObject(ButtonWidget.builder(ModTexts.BACK, (button) -> {
            if (this.getPageNumber() == 1 || this.getPageNumber() == 5) {
                this.close();
            } else {
                this.client.setScreen(this.getPreviousScreen());
            }
        }).build());
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_LEFT || keyCode == GLFW.GLFW_KEY_A) {
            if (this.getPageNumber() != 1) {
                this.client.setScreen(this.getPreviousScreen());
            }
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_RIGHT || keyCode == GLFW.GLFW_KEY_D) {
            if (this.getPageNumber() == 4 || this.getPageNumber() == 9) {
                this.getButtonFunction();
            } else if (this.getPageNumber() != this.getMaxPages()) {
                this.client.setScreen(this.getNextScreen());
            }
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.SECRET_DOOM_MODE;
    }

    protected void getButtonFunction() {
        this.client.setScreen(this.getNextScreen());
    }

    protected abstract Text getButtonText();
}