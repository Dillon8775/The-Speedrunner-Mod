package net.dillon.speedrunnermod.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class BetterDeathScreen extends DefaultMiscellaneousFeatureFactory {

    public BetterDeathScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().general.showDeathCords.getCurrentValue() ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            options().general.showDeathCords.set(!options().general.showDeathCords.getCurrentValue());
            this.refreshNonRestartableFeature();
        }).build());
    }
}