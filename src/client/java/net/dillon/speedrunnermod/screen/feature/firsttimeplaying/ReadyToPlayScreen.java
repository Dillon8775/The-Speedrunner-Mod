package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.feature.FeaturesScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;

public class ReadyToPlayScreen extends FTPFeatureScreen {

    public ReadyToPlayScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(ModTexts.BEGIN_PLAYING, button -> {
            if (restartRequired) {
                this.minecraft.gui.setScreen(this.getNextScreen());
            } else {
                clientOptions().storedValues.firstTimePlaying.set(false);
                if (clientOptions().storedValues.viewFeatures.getCurrentValue()) {
                    this.minecraft.gui.setScreen(new FeaturesScreen(null));
                    clientOptions().storedValues.viewFeatures.set(false);
                } else {
                    this.minecraft.gui.setScreen(new TitleScreen());
                }
                saveClientChanges();
            }
        }).build());
        this.addButtonObject(createOption(ListOptions.viewFeatures()));
        this.addButtonObject(Button.builder(ModTexts.BACK, button -> {
            this.minecraft.gui.setScreen(this.getPreviousScreen());
            restartRequired = false;
        }).build());
    }
}