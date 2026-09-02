package net.dillon.speedrunnermod.screen.feature;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.minecraft.client.gui.screens.Screen;

public class PotionsAndEnchantmentsScreen extends FeatureCategoryScreen {

    public PotionsAndEnchantmentsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_POTIONS_AND_ENCHANTMENTS);
    }

    @Override
    protected FeatureScreenCategory getScreenCategory() {
        return FeatureScreenCategory.POTIONS_AND_ENCHANTMENTS;
    }
}