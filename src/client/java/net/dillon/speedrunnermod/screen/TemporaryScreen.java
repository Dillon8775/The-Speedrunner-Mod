package net.dillon.speedrunnermod.screen;

import net.dillon.dillonlib.util.Texts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

public class TemporaryScreen extends AbstractModScreen {
    private final Component text;

    public TemporaryScreen(Screen parent, Component text) {
        super(parent, Texts.BLANK);
        this.text = text;
    }

    @Override
    public void onClose() {
    }

    @Override
    protected void renderCustomText(GuiGraphicsExtractor graphics) {
        graphics.centeredText(this.font, this.text, this.width / 2, 100, CommonColors.WHITE);
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }
}