package net.dillon.speedrunnermod.util;

import net.minecraft.client.gl.RenderPipelines;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.util.Colors;
import net.minecraft.util.math.ColorHelper;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Utility class for client-side features.
 */
public class ClientModUtil {

    /**
     * Renders the {@code golden speedrunner smithing template} texture with a custom width and height.
     */
    public static void renderSpeedrunnerSmithingTemplate(DrawContext context, ButtonWidget button) {
        renderSpeedrunnerSmithingTemplate(context, button, 1.0F);
    }

    /**
     * Renders the {@code mod icon.}
     */
    public static void renderModIcon(DrawContext context, ButtonWidget button) {
        renderModIcon(context, button, 1.0F);
    }

    /**
     * Renders the {@code golden speedrunner smithing template} texture with a custom width and height and a custom alpha fade.
     */
    public static void renderSpeedrunnerSmithingTemplate(DrawContext context, ButtonWidget button, float f) {
        int frametime = 4;
        int currentFrame = (int) ((System.currentTimeMillis() / (frametime * 50)) % 8);
        context.drawTexture(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/item/golden_upgrade_smithing_template.png"), button.getX() + 2, button.getY() + 2, 0.0F, currentFrame * 16, 16, 16, 16, 128, ColorHelper.withAlpha(f, Colors.WHITE));
    }

    /**
     * Renders the {@code mod icon} with a custom alpha fade.
     */
    public static void renderModIcon(DrawContext context, ButtonWidget button, float f) {
        context.drawTexture(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/gui/icon_other.png"), button.getX() + 1, button.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18, ColorHelper.withAlpha(f, Colors.WHITE));
    }
}