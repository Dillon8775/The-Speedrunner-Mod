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
        super.init();

        this.addButtonsIteratively(this.getScreenCategory());
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}