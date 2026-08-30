package net.dillon.speedrunnermod.screen.feature.oresandworldgen;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

public class SpeedrunnersWastelandBiomeScreen extends DefaultOresAndWorldGenFeatureFactory {

    public SpeedrunnersWastelandBiomeScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(common().worldGen.generateSpeedrunnersWasteland.getCurrentValue() ? ModTexts.STOP_SPEEDRUNNERS_WASTELAND_BIOME_FROM_GENERATING : ModTexts.ALLOW_SPEEDRUNNERS_WASTELAND_BIOME_TO_GENERATE, button -> {
            common().worldGen.generateSpeedrunnersWasteland.set(!common().worldGen.generateSpeedrunnersWasteland.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }
}