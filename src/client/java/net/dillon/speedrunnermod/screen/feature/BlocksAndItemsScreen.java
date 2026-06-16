package net.dillon.speedrunnermod.screen.feature;

import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;

public class BlocksAndItemsScreen extends AbstractModScreen {

    public BlocksAndItemsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_BLOCKS_AND_ITEMS);
    }

    @Override
    protected void init() {
        this.addButtonsIteratively(ScreenCategory.BLOCKS_AND_ITEMS);
        super.init();
    }

    @Override
    public String pageId() {
        return "gfipiads";
    }

    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(new FeaturesScreen(this.parent));
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean hasSearchField() {
        return true;
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