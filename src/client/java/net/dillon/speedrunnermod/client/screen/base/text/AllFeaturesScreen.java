package net.dillon.speedrunnermod.client.screen.base.text;

import net.dillon.speedrunnermod.client.screen.base.AbstractScrollableScreen;
import net.dillon.speedrunnermod.client.screen.feature.FeaturesScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;

/**
 * All features screen.
 */
@Environment(EnvType.CLIENT)
public class AllFeaturesScreen extends AbstractScrollableScreen {

    public AllFeaturesScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_ALL_FEATURES);
    }

    @Override
    protected String getTextFile() {
        return this.inTextsFolder("all_features");
    }

    @Override
    protected String pageId() {
        return "jojoefsijodeko";
    }

    @Override
    public void close() {
        this.client.setScreen(new FeaturesScreen(this.parent, this.options));
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return true;
    }
}