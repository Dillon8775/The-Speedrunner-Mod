package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class FirstTimePlayingScreen extends FTPFeatureScreen {

    public FirstTimePlayingScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(Texts.LETS_GO, button -> {
            openScreen(this.getNextScreen());
        }).build());
        SpeedrunnerMod.LOGGER.info("Welcome to the Speedrunner Mod!");
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return false;
    }
}