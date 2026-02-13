package net.dillon.speedrunnermod.client.screen.base.option;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.ClientModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;

public class ResetOptionsConfirmScreen extends AbstractModScreen {

    public ResetOptionsConfirmScreen(Screen parent) {
        super(parent, ModTexts.TITLE_OPTIONS_RESET);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.RESET_CONFIRM, (buttonWidget) -> {
            ClientModOptions.resetAllOptions();
            ClientModOptions.resetAllClientOptions();
            saveAllChanges();
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
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.reset_options_confirm"), this.width / 2, 110, Colors.WHITE);
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