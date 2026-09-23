package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class KeyFeaturesScreen extends FTPFeatureScreen {

    public KeyFeaturesScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(Texts.NEXT, button -> {
            openScreen(this.getNextScreen());
        }).build());
        this.addButtonObject(Button.builder(Texts.BACK, button -> {
            openScreen(this.getPreviousScreen());
        }).build());
    }
}