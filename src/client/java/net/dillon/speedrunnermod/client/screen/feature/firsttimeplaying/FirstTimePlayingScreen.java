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

@Environment(EnvType.CLIENT)
public class FirstTimePlayingScreen extends AbstractFeatureScreen {

    public FirstTimePlayingScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(ButtonWidget.builder(ModTexts.LETS_GO, button -> {
            this.client.setScreen(this.getNextScreen());
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "first_time_playing";
    }

    @Override
    public int getPageNumber() {
        return 1;
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.FIRST_TIME_PLAYING;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FIRST_TIME_PLAYING;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return false;
    }
}