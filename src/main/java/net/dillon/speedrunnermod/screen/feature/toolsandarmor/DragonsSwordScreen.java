package net.dillon.speedrunnermod.screen.feature.toolsandarmor;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.feature.FeatureScreenCategory;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class DragonsSwordScreen extends DefaultToolsAndArmorFeatureFactory {

    /**
     * A basic feature screen constructor.
     *
     * @param parent
     * @param featurePage
     */
    public DragonsSwordScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(ModTexts.MENU_POTIONS_AND_ENCHANTMENTS, b -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.POTIONS_AND_ENCHANTMENTS).createScreen(this));
        }).build());
        this.addButtonObject(Button.builder(ModTexts.MENU_ORES_AND_WORLDGEN, b -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.ORES_AND_WORLDGEN).createScreen(this));
        }).build());
        this.addButtonObject(Button.builder(ModTexts.MENU_FEATURE_DOOM_MODE, b -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.DOOM_MODE).createScreen(this));
        }).build());
        this.addButtonObject(Button.builder(ModTexts.MENU_BLOCKS_AND_ITEMS, b -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.BLOCKS_AND_ITEMS).createScreen(this));
        }).build());
    }
}