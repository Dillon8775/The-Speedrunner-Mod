package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screen.Screen;
import org.jetbrains.annotations.NotNull;


public class NoMorePiglinBrutesScreen extends AbstractFeatureScreen {

    public NoMorePiglinBrutesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_NO_MORE_PIGLIN_BRUTES);
    }

    @Override
    @NotNull
    public String linesKey() {
        return "no_more_piglin_brutes";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.MISCELLANEOUS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}