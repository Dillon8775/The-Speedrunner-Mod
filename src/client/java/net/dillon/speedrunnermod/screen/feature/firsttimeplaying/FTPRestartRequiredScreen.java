package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientConfigHandler;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isEasyMode;

public class FTPRestartRequiredScreen extends FTPFeatureScreen {

    public FTPRestartRequiredScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(Texts.BACK, button -> {
            this.minecraft.gui.setScreen(this.getPreviousScreen());
            if (isEasyMode()) {
                restartRequired = false;
            }
        }).build());
        this.addButtonObject(Button.builder(ModTexts.RESTART_NOW, button -> {
            clientConfigHandler().update(c -> c.storedValues().firstTimePlaying = false);
            this.minecraft.stop();
        }).build());
    }
}