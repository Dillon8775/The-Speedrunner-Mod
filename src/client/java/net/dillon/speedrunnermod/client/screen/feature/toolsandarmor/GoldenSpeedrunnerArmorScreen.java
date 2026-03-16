package net.dillon.speedrunnermod.client.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;


public class GoldenSpeedrunnerArmorScreen extends AbstractFeatureScreen {

    public GoldenSpeedrunnerArmorScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_GOLDEN_SPEEDRUNNER_ARMOR);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "golden_speedrunner_armor";
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