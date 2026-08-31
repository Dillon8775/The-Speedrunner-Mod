package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.commonConfigHandler;

public class RightClickToRemoveSilkTouchScreen extends DefaultMiscellaneousFeatureFactory {

    public RightClickToRemoveSilkTouchScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(common().general().rightClickToRemoveSilkTouch ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            commonConfigHandler().update(o -> o.general().rightClickToRemoveSilkTouch = !o.general().rightClickToRemoveSilkTouch);
            this.refreshNonRestartableFeature();
        }).build());
    }
}