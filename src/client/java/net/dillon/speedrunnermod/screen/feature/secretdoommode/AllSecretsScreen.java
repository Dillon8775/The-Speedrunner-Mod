package net.dillon.speedrunnermod.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class AllSecretsScreen extends SecretDoomModeFeatureScreen {

    public AllSecretsScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected Component getButtonText() {
        return ModTexts.OK;
    }
}