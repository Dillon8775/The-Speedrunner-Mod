package net.dillon.speedrunnermod.client.screen.feature;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screens.Screen;

public class OresAndWorldgenScreen extends AbstractModScreen {

    public OresAndWorldgenScreen(Screen parent) {
        super(parent, ModTexts.TITLE_ORES_AND_WORLDGEN);
    }

    @Override
    protected void init() {
        this.addButtonsIteratively(ScreenCategory.ORES_AND_WORLDGEN);
        super.init();
    }

    @Override
    public String pageId() {
        return "dfijoeijaw";
    }

    @Override
    public void onClose() {
        this.minecraft.setScreen(new FeaturesScreen(this.parent));
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
        return false;
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