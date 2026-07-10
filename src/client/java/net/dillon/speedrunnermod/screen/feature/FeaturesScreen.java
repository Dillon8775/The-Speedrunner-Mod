package net.dillon.speedrunnermod.screen.feature;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import java.util.List;

public class FeaturesScreen extends AbstractModScreen {
    private Button blocksAndItemsButton, toolsAndArmorButton, potionsAndEnchantmentsButton, oresAndWorldgenButton, doomModeButton, miscellaneousButton;

    public FeaturesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURES);
    }

    @Override
    protected List<AbstractWidget> buttons() {
        return List.of(
                this.blocksAndItemsButton,
                this.toolsAndArmorButton,
                this.potionsAndEnchantmentsButton,
                this.oresAndWorldgenButton,
                this.doomModeButton,
                this.miscellaneousButton
        );
    }

    @Override
    protected void init() {
        this.blocksAndItemsButton = Button.builder(ModTexts.MENU_BLOCKS_AND_ITEMS, (buttonWidget) -> {
            this.minecraft.gui.setScreen(new BlocksAndItemsScreen(this.parent));
        }).build();

        this.toolsAndArmorButton = Button.builder(ModTexts.MENU_TOOLS_AND_ARMOR, (buttonWidget) -> {
            this.minecraft.gui.setScreen(new ToolsAndArmorScreen(this.parent));
        }).build();

        this.potionsAndEnchantmentsButton = Button.builder(ModTexts.MENU_POTIONS_AND_ENCHANTMENTS, (buttonWidget) -> {
            this.minecraft.gui.setScreen(new PotionsAndEnchantmentsScreen(this.parent));
        }).build();

        this.oresAndWorldgenButton = Button.builder(ModTexts.MENU_ORES_AND_WORLDGEN, (buttonWidget) -> {
            this.minecraft.gui.setScreen(new OresAndWorldGenScreen(this.parent));
        }).build();

        this.doomModeButton = Button.builder(ModTexts.MENU_FEATURE_DOOM_MODE, (buttonWidget) -> {
            this.minecraft.gui.setScreen(new DoomModeScreen(this.parent));
        }).build();

        this.miscellaneousButton = Button.builder(ModTexts.MENU_MISCELLANEOUS, (buttonWidget) -> {
            this.minecraft.gui.setScreen(new MiscellaneousScreen(this.parent));
        }).build();

        super.init();
    }

    @Override
    public boolean isCentered() {
        return true;
    }

    @Override
    public String pageId() {
        return "ipaipads";
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return true;
    }

    @Override
    public boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}