package net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;
import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;

@Environment(EnvType.CLIENT)
public class FTPRestartRequiredScreen extends AbstractFeatureScreen {

    public FTPRestartRequiredScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(ButtonWidget.builder(ModTexts.BACK, button -> {
            this.client.setScreen(this.getPreviousScreen());
            if (isEasyMode()) {
                restartRequired = false;
            }
        }).build());
        this.addButtonObject(ButtonWidget.builder(ModTexts.RESTART_NOW, button -> {
            clientOptions().client.firstTimePlaying.set(false);
            saveClientChanges();
            this.client.scheduleStop();
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "restart_required";
    }

    @Override
    public int getPageNumber() {
        return 5;
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