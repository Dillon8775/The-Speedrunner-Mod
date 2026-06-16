package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;


public class KeyFeaturesScreen extends AbstractFeatureScreen {

    public KeyFeaturesScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(ModTexts.NEXT, button -> {
            this.minecraft.gui.setScreen(this.getNextScreen());
        }).build());
        this.addButtonObject(Button.builder(ModTexts.BACK, button -> {
            this.minecraft.gui.setScreen(this.getPreviousScreen());
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "key_features";
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