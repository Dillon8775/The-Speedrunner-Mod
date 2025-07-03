package net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.saveDedicatedServerChanges;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;

@Environment(EnvType.CLIENT)
public class ReadyToPlayScreen extends AbstractFeatureScreen {
    private ButtonWidget beginPlayingButton;

    public ReadyToPlayScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(ButtonWidget.builder(ModTexts.ENTER_TUTORIAL_MODE, button -> {
            clientOptions().client.tutorialMode.set(true);
            saveDedicatedServerChanges();
            restartRequired = true;
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.beginPlayingButton = this.addButtonObject(ButtonWidget.builder(ModTexts.BEGIN_PLAYING, button -> {
            if (restartRequired) {
                this.client.setScreen(this.getNextScreen());
            } else {
                clientOptions().client.firstTimePlaying.set(false);
                saveClientChanges();
                this.client.setScreen(new TitleScreen());
            }
        }).build());
        this.addButtonObject(ButtonWidget.builder(ModTexts.BACK, button -> {
            this.client.setScreen(this.getPreviousScreen());
            restartRequired = false;
        }).build());
    }

    @Override
    protected void renderTooltips(DrawContext context, int x, int y) {
        if (this.beginPlayingButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.BEGIN_PLAYING_TOOLTIP, context, x, y);
        }
        super.renderTooltips(context, x, y);
    }

    @Override
    public @NotNull String linesKey() {
        return "ready_to_play";
    }

    @Override
    public int getPageNumber() {
        return 4;
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