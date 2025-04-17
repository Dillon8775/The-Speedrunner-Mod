package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

/**
 * The credits screen for the Speedrunner Mod, rendering a custom credits image.
 */
@Environment(EnvType.CLIENT)
public class ModCreditsScreen extends AbstractModScreen {

    public ModCreditsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_CREDITS);
    }

    @Override
    public void renderCustomObjects(DrawContext context) {
        context.drawTexture(RenderLayer::getGuiTextured, Identifier.of("speedrunnermod:textures/gui/credits.png"), this.width / 2 - 159, this.height / 6 + 18, 0.0F, 0.0F, 320, 180, 320, 180);
    }

    @Override
    protected String pageId() {
        return "bidfsi";
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
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}