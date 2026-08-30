package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

public class ArrowsExplodeBedsScreen extends DefaultMiscellaneousFeatureFactory {

    public ArrowsExplodeBedsScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(common().worldGen.arrowsDestroyBeds.getCurrentValue() ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            common().worldGen.arrowsDestroyBeds.set(!common().worldGen.arrowsDestroyBeds.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }
}