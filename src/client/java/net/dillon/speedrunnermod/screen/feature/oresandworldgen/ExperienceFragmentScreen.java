package net.dillon.speedrunnermod.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

public class ExperienceFragmentScreen extends AbstractFeatureScreen {

    public ExperienceFragmentScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_EXPERIENCE_FRAGMENT);
    }

    @Override
    public @NotNull String linesKey() {
        return "experience_fragment";
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.ORES_AND_WORLDGEN;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}