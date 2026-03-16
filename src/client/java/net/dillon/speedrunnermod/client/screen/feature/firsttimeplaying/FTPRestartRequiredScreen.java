package net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;
import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;


public class FTPRestartRequiredScreen extends AbstractFeatureScreen {

    public FTPRestartRequiredScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(ModTexts.BACK, button -> {
            this.minecraft.setScreen(this.getPreviousScreen());
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

    @Override
    public @NotNull String linesKey() {
        return "restart_required";
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.FIRST_TIME_PLAYING;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FIRST_TIME_PLAYING;
    }
}