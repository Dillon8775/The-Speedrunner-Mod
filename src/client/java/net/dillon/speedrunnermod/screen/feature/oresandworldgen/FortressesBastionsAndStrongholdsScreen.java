package net.dillon.speedrunnermod.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.feature.FeatureScreenCategory;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

public class FortressesBastionsAndStrongholdsScreen extends DefaultOresAndWorldGenFeatureFactory {

    public FortressesBastionsAndStrongholdsScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(ModTexts.MENU_MISCELLANEOUS, b -> {
            this.minecraft.gui.setScreen(FeaturePage.getFirstPage(FeatureScreenCategory.MISCELLANEOUS).createScreen(this));
        }).build());
        this.addButtonObject(Button.builder(ModTexts.MENU_FEATURE_DOOM_MODE, b -> {
            this.minecraft.gui.setScreen(FeaturePage.getFirstPage(FeatureScreenCategory.DOOM_MODE).createScreen(this));
        }).build());
        this.addButtonObject(Button.builder(ModTexts.MENU_BLOCKS_AND_ITEMS, b -> {
            this.minecraft.gui.setScreen(FeaturePage.getFirstPage(FeatureScreenCategory.BLOCKS_AND_ITEMS).createScreen(this));
        }).build());
        this.addButtonObject(Button.builder(ModTexts.MENU_TOOLS_AND_ARMOR, b -> {
            this.minecraft.gui.setScreen(FeaturePage.getFirstPage(FeatureScreenCategory.TOOLS_AND_ARMOR).createScreen(this));
        }).build());
        this.addButtonObject(Button.builder(ModTexts.MENU_POTIONS_AND_ENCHANTMENTS, b -> {
            this.minecraft.gui.setScreen(FeaturePage.getFirstPage(FeatureScreenCategory.POTIONS_AND_ENCHANTMENTS).createScreen(this));
        }).build());
    }
}