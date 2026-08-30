package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.dillonlib.util.Texts;
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
        this.addButtonObject(Button.builder(Texts.NEXT, button -> {
            this.minecraft.gui.setScreen(this.getNextScreen());
        }).build());
        this.addButtonObject(Button.builder(Texts.BACK, button -> {
            this.minecraft.gui.setScreen(this.getPreviousScreen());
        }).build());
    }
}