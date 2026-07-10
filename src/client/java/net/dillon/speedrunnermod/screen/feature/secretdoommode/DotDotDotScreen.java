package net.dillon.speedrunnermod.screen.feature.secretdoommode;

import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public class DotDotDotScreen extends SecretDoomModeFeatureScreen {

    public DotDotDotScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected Component getButtonText() {
        return Component.translatable("speedrunnermod.doom_mode_screen.line8.reply");
    }
}