package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;


public class CustomPanoramaScreen extends AbstractFeatureScreen {

    public CustomPanoramaScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_CUSTOM_PANORAMA);
    }

    @Override
    public @NotNull String linesKey() {
        return "custom_panorama";
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.MISCELLANEOUS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}