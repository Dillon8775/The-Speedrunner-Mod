package net.dillon.speedrunnermod.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class IgneousOresScreen extends AbstractFeatureScreen {

    public IgneousOresScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_IGNEOUS_ORES);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "igneous_ores";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.ORES_AND_WORLDGEN;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}