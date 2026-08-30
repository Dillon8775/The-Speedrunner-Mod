package net.dillon.speedrunnermod.screen.feature.secretdoommode;

import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class EyeFeaturesScreen extends SecretDoomModeFeatureScreen {

    public EyeFeaturesScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void getButtonFunction() {
        this.onClose();
        doomModeButtonAlreadyClicked = 0;
    }

    @Override
    protected Component getButtonText() {
        return Texts.OK;
    }
}