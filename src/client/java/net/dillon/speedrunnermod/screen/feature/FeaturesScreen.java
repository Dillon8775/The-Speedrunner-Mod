package net.dillon.speedrunnermod.screen.feature;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class FeaturesScreen extends AbstractModScreen {

    public FeaturesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURES);
    }

    @Override
    protected void init() {
        super.init();

        Button blocksAndItemsButton = Button.builder(ModTexts.MENU_BLOCKS_AND_ITEMS, (buttonWidget) -> {
            openScreen(new BlocksAndItemsScreen(this));
        }).build();

        Button toolsAndArmorButton = Button.builder(ModTexts.MENU_TOOLS_AND_ARMOR, (buttonWidget) -> {
            openScreen(new ToolsAndArmorScreen(this));
        }).build();

        Button potionsAndEnchantmentsButton = Button.builder(ModTexts.MENU_POTIONS_AND_ENCHANTMENTS, (buttonWidget) -> {
            openScreen(new PotionsAndEnchantmentsScreen(this));
        }).build();

        Button oresAndWorldgenButton = Button.builder(ModTexts.MENU_ORES_AND_WORLDGEN, (buttonWidget) -> {
            openScreen(new OresAndWorldGenScreen(this));
        }).build();

        Button doomModeButton = Button.builder(ModTexts.MENU_FEATURE_DOOM_MODE, (buttonWidget) -> {
            openScreen(new DoomModeScreen(this));
        }).build();

        Button miscellaneousButton = Button.builder(ModTexts.MENU_MISCELLANEOUS, (buttonWidget) -> {
            openScreen(new MiscellaneousScreen(this));
        }).build();

        this.list.addHeader(Component.translatable("speedrunnermod.menu.items"));
        this.list.addSmall(
                List.of(
                        blocksAndItemsButton,
                        toolsAndArmorButton,
                        potionsAndEnchantmentsButton
                )
        );

        this.list.addHeader(Component.translatable("speedrunnermod.menu.world_and_more"));
        this.list.addSmall(
                List.of(
                        oresAndWorldgenButton,
                        doomModeButton,
                        miscellaneousButton
                )
        );
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}