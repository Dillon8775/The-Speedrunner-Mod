package net.dillon.speedrunnermod.screen.feature.potionsandenchantments;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.feature.FeatureScreenCategory;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class CooldownEnchantmentScreen extends DefaultPotionsAndEnchantmentsFeatureFactory {

    public CooldownEnchantmentScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(ModTexts.MENU_ORES_AND_WORLDGEN, b -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.ORES_AND_WORLDGEN).createScreen(this));
        }).build());
        this.addButtonObject(Button.builder(ModTexts.MENU_MISCELLANEOUS, b -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.MISCELLANEOUS).createScreen(this));
        }).build());
        this.addButtonObject(Button.builder(ModTexts.MENU_FEATURE_DOOM_MODE, b -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.DOOM_MODE).createScreen(this));
        }).build());
        this.addButtonObject(Button.builder(ModTexts.MENU_BLOCKS_AND_ITEMS, b -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.BLOCKS_AND_ITEMS).createScreen(this));
        }).build());
        this.addButtonObject(Button.builder(ModTexts.MENU_TOOLS_AND_ARMOR, b -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.TOOLS_AND_ARMOR).createScreen(this));
        }).build());
    }
}