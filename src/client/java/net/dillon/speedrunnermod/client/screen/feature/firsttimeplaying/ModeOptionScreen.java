package net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.saveDedicatedServerChanges;


public class ModeOptionScreen extends AbstractFeatureScreen {
    private AbstractWidget easyButton, balancedButton, doomButton;

    public ModeOptionScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.easyButton = this.addButtonObject(Button.builder(ModTexts.EASY_MODE, button -> {
            options().main.mode.set(ModOptions.Mode.EASY);
            saveDedicatedServerChanges();
            restartRequired = false;
            this.minecraft.setScreen(this.getNextScreen());
        }).build());
        this.balancedButton = this.addButtonObject(Button.builder(ModTexts.BALANCED_MODE, button -> {
            options().main.mode.set(ModOptions.Mode.BALANCED);
            saveDedicatedServerChanges();
            restartRequired = true;
            this.minecraft.setScreen(this.getNextScreen());
        }).build());
        this.doomButton = this.addButtonObject(Button.builder(ModTexts.DOOM_MODE, button -> {
            options().main.mode.set(ModOptions.Mode.DOOM);
            saveDedicatedServerChanges();
            restartRequired = true;
            this.minecraft.setScreen(this.getNextScreen());
        }).build());
        this.addButtonObject(Button.builder(Component.translatable("speedrunnermod.back"), button -> {
            this.minecraft.setScreen(this.getPreviousScreen());
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
    public @NotNull String linesKey() {
        return "mode_option";
    }

    @Override
    public int getPageNumber() {
        return 3;
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.FIRST_TIME_PLAYING;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.FIRST_TIME_PLAYING;
    }
}