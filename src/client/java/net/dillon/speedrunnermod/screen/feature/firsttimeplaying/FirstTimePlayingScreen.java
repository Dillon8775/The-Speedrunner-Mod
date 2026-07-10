package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.info;

public class FirstTimePlayingScreen extends FTPFeatureScreen {

    public FirstTimePlayingScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(ModTexts.LETS_GO, button -> {
            this.minecraft.gui.setScreen(this.getNextScreen());
        }).build());
        info("Welcome to the Speedrunner Mod!");
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return false;
    }
}