package net.dillon.speedrunnermod.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class UmScreen extends SecretDoomModeFeatureScreen {

    public UmScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected Component getButtonText() {
        return Component.translatable("speedrunnermod.doom_mode_screen.line7.reply");
    }
}