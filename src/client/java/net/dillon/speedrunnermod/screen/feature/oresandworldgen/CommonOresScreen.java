package net.dillon.speedrunnermod.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class CommonOresScreen extends DefaultOresAndWorldGenFeatureFactory {

    public CommonOresScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().worldGen.commonOres.getCurrentValue() ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            options().worldGen.commonOres.set(!options().worldGen.commonOres.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }
}