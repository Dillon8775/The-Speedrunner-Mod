package net.dillon.speedrunnermod.screen.feature;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.minecraft.client.gui.screens.Screen;

public class BlocksAndItemsScreen extends FeatureCategoryScreen {

    public BlocksAndItemsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_BLOCKS_AND_ITEMS);
    }

    @Override
    protected FeatureScreenCategory getScreenCategory() {
        return FeatureScreenCategory.BLOCKS_AND_ITEMS;
    }
}