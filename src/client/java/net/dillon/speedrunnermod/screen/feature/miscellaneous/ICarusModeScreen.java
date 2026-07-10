package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class ICarusModeScreen extends DefaultMiscellaneousFeatureFactory {

    public ICarusModeScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().general.iCarusMode.getCurrentValue() ? ModTexts.DISABLE_ICARUS_MODE : ModTexts.ENABLE_ICARUS_MODE, button -> {
            options().general.iCarusMode.set(!options().general.iCarusMode.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }
}