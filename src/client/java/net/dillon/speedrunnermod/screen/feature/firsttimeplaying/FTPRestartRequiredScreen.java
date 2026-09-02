package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;
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
            openScreen(this.getPreviousScreen());
            if (isEasyMode()) {
                restartRequired = false;
            }
        }).build());
        this.addButtonObject(Button.builder(Component.translatable("speedrunnermod.restart_now"), button -> {
            clientConfigHandler().update(c -> c.storedValues().firstTimePlaying = false);
            this.minecraft.stop();
        }).build());
    }
}