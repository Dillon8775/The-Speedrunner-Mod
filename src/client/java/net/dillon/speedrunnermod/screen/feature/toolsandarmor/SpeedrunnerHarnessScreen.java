package net.dillon.speedrunnermod.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class SpeedrunnerHarnessScreen extends AbstractFeatureScreen {

    public SpeedrunnerHarnessScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_SPEEDRUNNER_HARNESS);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "speedrunner_harness";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.TOOLS_AND_ARMOR;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}