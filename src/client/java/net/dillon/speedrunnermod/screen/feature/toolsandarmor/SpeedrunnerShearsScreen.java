package net.dillon.speedrunnermod.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class SpeedrunnerShearsScreen extends AbstractFeatureScreen {

    public SpeedrunnerShearsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_SPEEDRUNNER_SHEARS);
    }

    @Override
    public @NotNull String linesKey() {
        return "speedrunner_shears";
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}