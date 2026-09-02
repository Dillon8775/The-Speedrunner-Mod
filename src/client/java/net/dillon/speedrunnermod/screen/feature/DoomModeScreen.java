package net.dillon.speedrunnermod.screen.feature;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.minecraft.client.gui.screens.Screen;

public class DoomModeScreen extends FeatureCategoryScreen {

    public DoomModeScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_DOOM_MODE);
    }

    @Override
    protected FeatureScreenCategory getScreenCategory() {
        return FeatureScreenCategory.DOOM_MODE;
    }
}