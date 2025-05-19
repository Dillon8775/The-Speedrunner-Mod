package net.dillon.speedrunnermod.client.screen.base.text;

import net.dillon.speedrunnermod.client.screen.base.AbstractScrollableScreen;
import net.dillon.speedrunnermod.client.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

/**
 * All features screen.
 */
@Environment(EnvType.CLIENT)
public class AllFeaturesScreen extends AbstractScrollableScreen {

    public AllFeaturesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_ALL_FEATURES);
    }

    @Override
    protected String getTextFile() {
        return this.inTextsFolder("all_features");
    }

    @Override
    public String pageId() {
        return "jojoefsijodeko";
    }

    @Override
    public void close() {
        this.client.setScreen(new FeaturesScreen(this.parent));
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return true;
    }
}