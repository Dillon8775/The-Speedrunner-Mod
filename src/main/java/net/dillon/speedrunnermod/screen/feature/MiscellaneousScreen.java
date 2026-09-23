package net.dillon.speedrunnermod.screen.feature;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.minecraft.client.gui.screens.Screen;

public class MiscellaneousScreen extends FeatureCategoryScreen {

    public MiscellaneousScreen(Screen parent) {
        super(parent, ModTexts.TITLE_MISCELLANEOUS);
    }

    @Override
    protected FeatureScreenCategory getScreenCategory() {
        return FeatureScreenCategory.MISCELLANEOUS;
    }
}