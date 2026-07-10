package net.dillon.speedrunnermod.screen.feature;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.minecraft.client.gui.screens.Screen;

public class OresAndWorldGenScreen extends FeatureCategoryScreen {

    public OresAndWorldGenScreen(Screen parent) {
        super(parent, ModTexts.TITLE_ORES_AND_WORLDGEN);
    }

    @Override
    protected FeatureScreenCategory getScreenCategory() {
        return FeatureScreenCategory.ORES_AND_WORLDGEN;
    }

    @Override
    public String pageId() {
        return "dfijoeijaw";
    }
}