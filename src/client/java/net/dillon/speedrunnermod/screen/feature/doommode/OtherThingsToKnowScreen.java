package net.dillon.speedrunnermod.screen.feature.doommode;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.Mode;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.dillon.speedrunnermod.screen.option.RestartRequiredScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

public class OtherThingsToKnowScreen extends DoomModeFeatureScreen {
    private AbstractWidget enableDoomModeButton;

    public OtherThingsToKnowScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();

        this.enableDoomModeButton = this.addButtonObject(Button.builder(ModTexts.ENABLE_DOOM_MODE, button -> {
            RestartRequiredScreen.getCurrentOptions();
            if (!isDoomMode()) {
                this.minecraft.gui.setScreen(new RestartRequiredScreen(this));
            }
            options().general.mode.set(Mode.DOOM);
        }).build());
        this.enableDoomModeButton.active = !isDoomMode();
        this.addButtonObject(Button.builder(ModTexts.NOT_NOW, button -> this.onClose()).build());
    }

    @Override
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (isDoomMode() && this.enableDoomModeButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.DOOM_MODE_ALREADY_ENABLED, context, mouseX, mouseY);
        }
    }
}