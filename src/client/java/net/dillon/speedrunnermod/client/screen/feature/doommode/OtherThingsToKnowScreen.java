package net.dillon.speedrunnermod.client.screen.feature.doommode;

import net.dillon.speedrunnermod.client.screen.base.option.RestartRequiredScreen;
import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;


public class OtherThingsToKnowScreen extends AbstractFeatureScreen {
    private ClickableWidget enableDoomModeButton;

    public OtherThingsToKnowScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_DOOM_MODE_OTHER_THINGS_TO_KNOW);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(ButtonWidget.builder(ModTexts.OK, button -> this.close()).build());
        this.enableDoomModeButton = this.addButtonObject(ButtonWidget.builder(ModTexts.ENABLE_DOOM_MODE, button -> {
            RestartRequiredScreen.getCurrentOptions();
            if (!isDoomMode()) {
                this.client.setScreen(new RestartRequiredScreen(this));
            }
            options().main.mode.set(ModOptions.Mode.DOOM);
        }).build());
        this.enableDoomModeButton.active = !isDoomMode();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
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