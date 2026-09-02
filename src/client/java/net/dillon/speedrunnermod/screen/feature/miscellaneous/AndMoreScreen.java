package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.feature.FeatureScreenCategory;
import net.dillon.speedrunnermod.util.ModLinks;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.dillonlib.task.ClientTasks.openLink;
import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class AndMoreScreen extends DefaultMiscellaneousFeatureFactory {

    public AndMoreScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.addButtonObject(Button.builder(Component.translatable("speedrunnermod.menu.features.learn_more"), button -> {
            openLink(this, ModLinks.MODRINTH, true);
        }).build());

        this.addButtonObject(Button.builder(ModTexts.MENU_BLOCKS_AND_ITEMS, button -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.BLOCKS_AND_ITEMS).createScreen(this));
        }).build());

        this.addButtonObject(Button.builder(ModTexts.MENU_TOOLS_AND_ARMOR, button -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.TOOLS_AND_ARMOR).createScreen(this));
        }).build());

        this.addButtonObject(Button.builder(ModTexts.MENU_POTIONS_AND_ENCHANTMENTS, button -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.POTIONS_AND_ENCHANTMENTS).createScreen(this));
        }).build());

        this.addButtonObject(Button.builder(ModTexts.MENU_ORES_AND_WORLDGEN, button -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.ORES_AND_WORLDGEN).createScreen(this));
        }).build());

        this.addButtonObject(Button.builder(ModTexts.MENU_FEATURE_DOOM_MODE, button -> {
            openScreen(FeaturePage.getFirstPage(FeatureScreenCategory.DOOM_MODE).createScreen(this));
        }).build());
    }
}