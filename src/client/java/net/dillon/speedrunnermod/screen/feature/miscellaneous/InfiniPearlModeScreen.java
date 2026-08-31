package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.commonConfigHandler;

public class InfiniPearlModeScreen extends DefaultMiscellaneousFeatureFactory {

    public InfiniPearlModeScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(common().general().infiniPearlMode ? ModTexts.DISABLE_INFINI_PEARL_MODE : ModTexts.ENABLE_INFINI_PEARL_MODE, button -> {
            commonConfigHandler().update(o -> o.general().infiniPearlMode = !o.general().infiniPearlMode);
            this.refreshNonRestartableFeature();
        }).build());
    }
}