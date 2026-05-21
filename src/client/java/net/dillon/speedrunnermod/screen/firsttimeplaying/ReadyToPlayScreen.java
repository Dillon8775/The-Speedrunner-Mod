package net.dillon.speedrunnermod.screen.firsttimeplaying;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;


public class ReadyToPlayScreen extends AbstractFeatureScreen {

    public ReadyToPlayScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(ModTexts.BEGIN_PLAYING, button -> {
            if (restartRequired) {
                this.minecraft.setScreen(this.getNextScreen());
            } else {
                clientOptions().storedValues.firstTimePlaying.set(false);
                this.minecraft.setScreen(new TitleScreen());
                saveClientChanges();
            }
        }).build());
        this.addButtonObject(Button.builder(ModTexts.BACK, button -> {
            this.minecraft.setScreen(this.getPreviousScreen());
            restartRequired = false;
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "ready_to_play";
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