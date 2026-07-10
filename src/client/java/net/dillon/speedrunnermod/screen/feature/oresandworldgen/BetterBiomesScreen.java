package net.dillon.speedrunnermod.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.option.WorldGenOptionsScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class BetterBiomesScreen extends DefaultOresAndWorldGenFeatureFactory {

    public BetterBiomesScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().worldGen.betterBiomes.getCurrentValue() ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            this.refreshRestartableFeature(new WorldGenOptionsScreen(this));
            options().worldGen.betterBiomes.set(!options().worldGen.betterBiomes.getCurrentValue());
            ((WorldGenOptionsScreen)this.minecraft.gui.screen()).searchField.setValue("better biomes");
        }).build());
    }
}