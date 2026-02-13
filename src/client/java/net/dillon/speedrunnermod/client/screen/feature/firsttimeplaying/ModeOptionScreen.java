package net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.saveDedicatedServerChanges;


public class ModeOptionScreen extends AbstractFeatureScreen {
    private ClickableWidget easyButton, balancedButton, doomButton;

    public ModeOptionScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        super.init();
        this.easyButton = this.addButtonObject(ButtonWidget.builder(ModTexts.EASY_MODE, button -> {
            options().main.mode.set(ModOptions.Mode.EASY);
            saveDedicatedServerChanges();
            restartRequired = false;
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.balancedButton = this.addButtonObject(ButtonWidget.builder(ModTexts.BALANCED_MODE, button -> {
            options().main.mode.set(ModOptions.Mode.BALANCED);
            saveDedicatedServerChanges();
            restartRequired = true;
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.doomButton = this.addButtonObject(ButtonWidget.builder(ModTexts.DOOM_MODE, button -> {
            options().main.mode.set(ModOptions.Mode.DOOM);
            saveDedicatedServerChanges();
            restartRequired = true;
            this.client.setScreen(this.getNextScreen());
        }).build());
        this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.back"), button -> {
            this.client.setScreen(this.getPreviousScreen());
        }).build());
    }

    @Override
    protected void renderTooltips(DrawContext context, int x, int y) {
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