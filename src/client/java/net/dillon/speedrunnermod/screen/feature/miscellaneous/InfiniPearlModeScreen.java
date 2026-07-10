package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class InfiniPearlModeScreen extends DefaultMiscellaneousFeatureFactory {

    public InfiniPearlModeScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().general.infiniPearlMode.getCurrentValue() ? ModTexts.DISABLE_INFINI_PEARL_MODE : ModTexts.ENABLE_INFINI_PEARL_MODE, button -> {
            options().general.infiniPearlMode.set(!options().general.infiniPearlMode.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }
}