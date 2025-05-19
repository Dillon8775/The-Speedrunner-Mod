package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

@Environment(EnvType.CLIENT)
public class ResetOptionsConfirmScreen extends AbstractModScreen {
    private final boolean tutorial;

    public ResetOptionsConfirmScreen(Screen parent, boolean tutorial) {
        super(parent,tutorial ? ModTexts.TITLE_TUTORIAL_MODE_OPTIONS_RESET : ModTexts.TITLE_OPTIONS_RESET);
        this.tutorial = tutorial;
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.RESET_CONFIRM, (buttonWidget) -> {
            if (tutorial) {
                ModOptions.resetAllTutorialModeOptions();
            } else {
                ModOptions.resetAllOptions();
            }
            ModOptions.saveConfig();
            info("Successfully reset all options. Restart the game to take full effect.");
            this.client.setScreen(new ResetOptionsScreen(this.parent));
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.NOT_NOW, (buttonWidget) -> {
            this.close();
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());
    }

    @Override
    public void close() {
        this.client.setScreen(new ModOptionsScreen(this.parent));
    }

    @Override
    public void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable(
                tutorial ? "speedrunnermod.reset_tutorial_mode_options_confirm" : "speedrunnermod.reset_options_confirm"),
                this.width / 2, 110, 16777215);
    }

    @Override
    public String pageId() {
        return "gfiefsi";
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    public boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}