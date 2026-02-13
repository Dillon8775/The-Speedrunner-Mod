package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;

public class TemporaryScreen extends AbstractModScreen {
    private final Text text;

    public TemporaryScreen(Screen parent, Text text) {
        super(parent, ModTexts.BLANK);
        this.text = text;
    }

    @Override
    public void close() {
    }

    @Override
    protected void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, this.text, this.width / 2, 100, Colors.WHITE);
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