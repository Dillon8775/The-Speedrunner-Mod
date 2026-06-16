package net.dillon.speedrunnermod.screen.feature.doommode;

import net.dillon.speedrunnermod.option.Mode;
import net.dillon.speedrunnermod.screen.AbstractFeatureScreen;
import net.dillon.speedrunnermod.screen.ScreenCategory;
import net.dillon.speedrunnermod.screen.ScreenType;
import net.dillon.speedrunnermod.screen.option.RestartRequiredScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

public class OtherThingsToKnowScreen extends AbstractFeatureScreen {
    private AbstractWidget enableDoomModeButton;

    public OtherThingsToKnowScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_DOOM_MODE_OTHER_THINGS_TO_KNOW);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(ModTexts.OK, button -> this.onClose()).build());
        this.enableDoomModeButton = this.addButtonObject(Button.builder(ModTexts.ENABLE_DOOM_MODE, button -> {
            RestartRequiredScreen.getCurrentOptions();
            if (!isDoomMode()) {
                this.minecraft.gui.setScreen(new RestartRequiredScreen(this));
            }
            options().general.mode.set(Mode.DOOM);
        }).build());
        this.enableDoomModeButton.active = !isDoomMode();
    }

    @Override
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (this.enableDoomModeButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.DOOM_MODE_ALREADY_ENABLED, context, mouseX, mouseY);
        }
    }

    @Override
    @NotNull
    public String linesKey() {
        return "other_things_to_know";
    }

    @Override
    @NotNull
    public ScreenCategory getScreenCategory() {
        return ScreenCategory.DOOM_MODE;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}