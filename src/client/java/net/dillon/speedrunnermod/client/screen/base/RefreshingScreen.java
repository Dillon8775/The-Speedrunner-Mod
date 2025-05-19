package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;

@Environment(EnvType.CLIENT)
public class RefreshingScreen extends AbstractModScreen {

    public RefreshingScreen(Screen parent) {
        super(parent, ModTexts.BLANK);
    }

    @Override
    protected void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, ModTexts.REFRESHING, this.width / 2, 100, 16777215);
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