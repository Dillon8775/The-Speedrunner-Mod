package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;
import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;

public class FTPRestartRequiredScreen extends FTPFeatureScreen {

    public FTPRestartRequiredScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(ModTexts.BACK, button -> {
            this.minecraft.gui.setScreen(this.getPreviousScreen());
            if (isEasyMode()) {
                restartRequired = false;
            }
        }).build());
        this.addButtonObject(Button.builder(ModTexts.RESTART_NOW, button -> {
            clientOptions().storedValues.firstTimePlaying.set(false);
            saveClientChanges();
            this.minecraft.stop();
        }).build());
    }
}