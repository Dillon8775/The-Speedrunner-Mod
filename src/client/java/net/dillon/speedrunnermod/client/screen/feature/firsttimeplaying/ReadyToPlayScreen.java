package net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
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
        this.addButtonObject(ButtonWidget.builder(ModTexts.BEGIN_PLAYING, button -> {
            if (restartRequired) {
                this.client.setScreen(this.getNextScreen());
            } else {
                clientOptions().storedValues.firstTimePlaying.set(false);
                if (clientOptions().storedValues.enterFeaturesScreen.getCurrentValue()) {
                    this.client.setScreen(new FeaturesScreen(null));
                    clientOptions().storedValues.enterFeaturesScreen.set(false);
                } else {
                    this.client.setScreen(new TitleScreen());
                }
                saveClientChanges();
            }
        }).build());
        this.addButtonObject(ModListOptions.enterFeatureScreens().createWidget(MinecraftClient.getInstance().options));
        this.addButtonObject(ButtonWidget.builder(ModTexts.BACK, button -> {
            this.client.setScreen(this.getPreviousScreen());
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