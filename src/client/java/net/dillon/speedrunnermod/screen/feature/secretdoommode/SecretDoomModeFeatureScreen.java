package net.dillon.speedrunnermod.screen.feature.secretdoommode;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.screen.FeatureScreen;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.network.chat.Component;

/**
 * An abstract representation of a {@code secret doom mode} feature screen.
 */
public class SecretDoomModeFeatureScreen extends FeatureScreen {
    public static int doomModeButtonAlreadyClicked = 0;

    public SecretDoomModeFeatureScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(this.getButtonText(), (button) -> {
            this.getButtonFunction();
        }).build());
        this.addButtonObject(Button.builder(Texts.BACK, (button) -> {
            if (this.getPageNumber() == 1 || this.getPageNumber() == 5) {
                this.onClose();
            } else {
                this.minecraft.gui.setScreen(this.getPreviousScreen());
            }
        }).build());
    }

    @Override
    public boolean keyPressed(KeyEvent input) {
        if (input.key() == InputConstants.KEY_LEFT || input.key() == InputConstants.KEY_A) {
            if (this.getPageNumber() != 1) {
                this.minecraft.gui.setScreen(this.getPreviousScreen());
            }
            return true;
        } else if (input.key() == InputConstants.KEY_RIGHT || input.key() == InputConstants.KEY_D) {
            if (this.getPageNumber() == 4 || this.getPageNumber() == 9) {
                this.getButtonFunction();
            } else if (this.getPageNumber() != this.getMaxPages()) {
                this.minecraft.gui.setScreen(this.getNextScreen());
            }
            return true;
        }
        return super.keyPressed(input);
    }

    protected void getButtonFunction() {
        this.minecraft.gui.setScreen(this.getNextScreen());
    }

    /**
     * Must be overriden.
     */
    protected Component getButtonText() {
        return Texts.BLANK;
    }
}