package net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.client.option.ModListOptions;
import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.client.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.client.main.SpeedrunnerModClient.saveClientChanges;


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
                if (clientOptions().storedValues.enterFeaturesScreen.getCurrentValue()) {
                    this.minecraft.setScreen(new FeaturesScreen(null));
                    clientOptions().storedValues.enterFeaturesScreen.set(false);
                } else {
                    this.minecraft.setScreen(new TitleScreen());
                }
                saveClientChanges();
            }
        }).build());
        this.addButtonObject(ModListOptions.enterFeatureScreens().createButton(Minecraft.getInstance().options));
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