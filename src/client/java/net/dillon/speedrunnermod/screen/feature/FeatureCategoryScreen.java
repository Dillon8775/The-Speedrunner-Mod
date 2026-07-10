package net.dillon.speedrunnermod.screen.feature;

import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

/**
 * A screen category that holds all screens in a certain category.
 */
public abstract class FeatureCategoryScreen extends AbstractModScreen {

    public FeatureCategoryScreen(Screen parent, Component title) {
        super(parent, title);
    }

    /**
     * @return the screen category for this instance of a feature category.
     */
    protected abstract FeatureScreenCategory getScreenCategory();

    @Override
    protected void init() {
        this.addButtonsIteratively(this.getScreenCategory());
        super.init();
    }

    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(new FeaturesScreen(this.parent));
    }

    @Override
    public boolean isCentered() {
        return true;
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