package net.dillon.speedrunnermod.screen.feature.doommode;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.eum.Mode;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.commonConfigHandler;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

public class OtherThingsToKnowScreen extends DoomModeFeatureScreen {
    private AbstractWidget enableDoomModeButton;

    public OtherThingsToKnowScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.enableDoomModeButton = this.addButtonObject(Button.builder(ModTexts.ENABLE_DOOM_MODE, button -> {
            commonConfigHandler().update(o -> o.general().mode = Mode.DOOM);
        }).build());
        this.enableDoomModeButton.active = !isDoomMode();
        this.addButtonObject(Button.builder(Component.translatable("speedrunnermod.not_now"), button -> this.onClose()).build());
    }
}