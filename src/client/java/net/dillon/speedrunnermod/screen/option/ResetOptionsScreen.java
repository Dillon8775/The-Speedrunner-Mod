package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.info;

public class ResetOptionsScreen extends AbstractModScreen {

    public ResetOptionsScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void init() {
        int height = this.height / 6 + 126;
        this.addRenderableWidget(Button.builder(ModTexts.RESTART_NOW, (buttonWidget) -> {
            this.quitWorld();
            info("Closing game! Re-launch to apply changes.");
            this.minecraft.stop();
        }).bounds(this.getButtonsLeftSide(), height, 150, 20).build());
        this.addRenderableWidget(Button.builder(ModTexts.RESTART_LATER, (buttonWidget) -> {
            this.minecraft.gui.setScreen(this.parent);
        }).bounds(this.getButtonsRightSide(), height, 150, 20).build());
    }

    @Override
    public void renderCustomText(GuiGraphicsExtractor context) {
        context.centeredText(this.font, Component.translatable("speedrunnermod.reset_options_successful.line1"), this.width / 2, 110, CommonColors.WHITE);
        context.centeredText(this.font, Component.translatable("speedrunnermod.reset_options_successful.line2"), this.width / 2, 130, CommonColors.WHITE);
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