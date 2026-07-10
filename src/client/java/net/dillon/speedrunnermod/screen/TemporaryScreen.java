package net.dillon.speedrunnermod.screen;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

public class TemporaryScreen extends AbstractModScreen {
    private final Component text;

    public TemporaryScreen(Screen parent, Component text) {
        super(parent, ModTexts.BLANK);
        this.text = text;
    }

    @Override
    public void onClose() {
    }

    @Override
    protected void renderCustomText(GuiGraphicsExtractor context) {
        context.centeredText(this.font, this.text, this.width / 2, 100, CommonColors.WHITE);
    }

    @Override
    public String pageId() {
        return "bfipdfsioads";
    }

    @Override
    protected int columns() {
        return 0;
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