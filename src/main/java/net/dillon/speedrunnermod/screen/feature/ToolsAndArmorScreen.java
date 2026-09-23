package net.dillon.speedrunnermod.screen.feature;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.minecraft.client.gui.screens.Screen;

public class ToolsAndArmorScreen extends FeatureCategoryScreen {

    public ToolsAndArmorScreen(Screen parent) {
        super(parent, ModTexts.TITLE_TOOLS_AND_ARMOR);
    }

    @Override
    protected FeatureScreenCategory getScreenCategory() {
        return FeatureScreenCategory.TOOLS_AND_ARMOR;
    }
}