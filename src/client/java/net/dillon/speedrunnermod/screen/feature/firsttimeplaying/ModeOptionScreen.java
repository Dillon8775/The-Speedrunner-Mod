package net.dillon.speedrunnermod.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.Mode;
import net.dillon.speedrunnermod.screen.feature.FeaturePage;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.saveDedicatedServerChanges;

public class ModeOptionScreen extends FTPFeatureScreen {
    private AbstractWidget easyButton, balancedButton, doomButton;

    public ModeOptionScreen(Screen parent, FeaturePage featurePage) {
        super(parent, featurePage);
    }

    @Override
    protected void init() {
        super.init();
        this.easyButton = this.addButtonObject(Button.builder(ModTexts.EASY_MODE, button -> {
            common().general.mode.set(Mode.EASY);
            saveDedicatedServerChanges();
            restartRequired = false;
            this.minecraft.gui.setScreen(this.getNextScreen());
        }).build());
        this.balancedButton = this.addButtonObject(Button.builder(ModTexts.BALANCED_MODE, button -> {
            common().general.mode.set(Mode.BALANCED);
            saveDedicatedServerChanges();
            restartRequired = true;
            this.minecraft.gui.setScreen(this.getNextScreen());
        }).build());
        this.doomButton = this.addButtonObject(Button.builder(ModTexts.DOOM_MODE, button -> {
            common().general.mode.set(Mode.DOOM);
            saveDedicatedServerChanges();
            restartRequired = true;
            this.minecraft.gui.setScreen(this.getNextScreen());
        }).build());
        this.addButtonObject(Button.builder(Component.translatable("speedrunnermod.back"), button -> {
            this.minecraft.gui.setScreen(this.getPreviousScreen());
        }).build());
    }

    @Override
    protected void renderTooltips(GuiGraphicsExtractor context, int x, int y) {
        if (this.easyButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.EASY_MODE_TOOLTIP, context, x, y);
        } else if (this.balancedButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.BALANCED_MODE_TOOLTIP, context, x, y);
        } else if (this.doomButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.DOOM_MODE_TOOLTIP, context, x, y);
        }
        super.renderTooltips(context, x, y);
    }

    @Override
    public int getPageNumber() {
        return 3;
    }
}