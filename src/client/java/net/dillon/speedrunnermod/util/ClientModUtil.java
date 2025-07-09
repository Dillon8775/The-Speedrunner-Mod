package net.dillon.speedrunnermod.util;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Utility class for client-side features.
 */
@Environment(EnvType.CLIENT)
public class ClientModUtil {

    /**
     * Renders the {@code golden speedrunner smithing template} texture with a custom width and height.
     */
    public static void renderSpeedrunnerSmithingTemplate(DrawContext context, ButtonWidget button) {
        int frametime = 4;
        int currentFrame = (int) ((System.currentTimeMillis() / (frametime * 50)) % 8);
        context.drawTexture(RenderLayer::getGuiTextured, ofSpeedrunnerMod("textures/item/golden_speedrunner_upgrade_smithing_template.png"), button.getX() + 2, button.getY() + 2, 0.0F, currentFrame * 16, 16, 16, 16, 128);
    }

    /**
     * Renders the {@code mod icon.}
     */
    public static void renderModIcon(DrawContext context, ButtonWidget button) {
        context.drawTexture(RenderLayer::getGuiTextured, ofSpeedrunnerMod("textures/gui/icon_other.png"), button.getX() + 1, button.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18);
    }
}