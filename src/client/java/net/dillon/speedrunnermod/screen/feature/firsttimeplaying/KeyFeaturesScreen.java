package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

public class KeyFeaturesScreen extends FTPFeatureScreen {

    public KeyFeaturesScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(ModTexts.NEXT, button -> {
            this.minecraft.gui.setScreen(this.getNextScreen());
        }).build());
        this.addButtonObject(Button.builder(ModTexts.BACK, button -> {
            this.minecraft.gui.setScreen(this.getPreviousScreen());
        }).build());
    }
}