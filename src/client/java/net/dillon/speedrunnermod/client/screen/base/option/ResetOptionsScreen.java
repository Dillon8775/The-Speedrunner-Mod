package net.dillon.speedrunnermod.client.screen.base.option;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.info;

public class ResetOptionsScreen extends AbstractModScreen {

    public ResetOptionsScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addDrawableChild(ButtonWidget.builder(ModTexts.RESTART_NOW, (buttonWidget) -> {
            this.quitWorld();
            info("Closing game! Re-launch to apply changes.");
            this.client.scheduleStop();
        }).dimensions(this.getButtonsLeftSide(), height, 150, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.RESTART_LATER, (buttonWidget) -> {
            this.client.setScreen(this.parent);
        }).dimensions(this.getButtonsRightSide(), height, 150, 20).build());
    }

    @Override
    public void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.reset_options_successful.line1"), this.width / 2, 110, Colors.WHITE);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.reset_options_successful.line2"), this.width / 2, 130, Colors.WHITE);
    }

    @Override
    public String pageId() {
        return "h9efsids";
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
        return false;
    }
}