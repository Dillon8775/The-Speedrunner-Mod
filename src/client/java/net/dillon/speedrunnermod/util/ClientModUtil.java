package net.dillon.speedrunnermod.util;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.util.ARGB;
import net.minecraft.util.CommonColors;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Utility class for client-side features.
 */
public class ClientModUtil {

    /**
     * Renders the {@code golden speedrunner smithing template} texture with a custom width and height.
     */
    public static void renderSpeedrunnerSmithingTemplate(GuiGraphics context, Button button) {
        renderSpeedrunnerSmithingTemplate(context, button, 1.0F);
    }

    /**
     * Renders the {@code mod icon.}
     */
    public static void renderModIcon(GuiGraphics context, Button button) {
        renderModIcon(context, button, 1.0F);
    }

    /**
     * Renders the {@code sync icon.}
     */
    public static void renderSyncIcon(GuiGraphics context, Button button) {
        renderSyncIcon(context, button, 1.0F);
    }

    /**
     * Renders the {@code golden speedrunner smithing template} texture with a custom width and height and a custom alpha fade.
     */
    public static void renderSpeedrunnerSmithingTemplate(GuiGraphics context, Button button, float f) {
        int frametime = 4;
        int currentFrame = (int) ((System.currentTimeMillis() / (frametime * 50)) % 8);
        context.blit(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/item/golden_upgrade_smithing_template.png"), button.getX() + 2, button.getY() + 2, 0.0F, currentFrame * 16, 16, 16, 16, 128, ARGB.color(f, CommonColors.WHITE));
    }

    /**
     * Renders the {@code mod icon} with a custom alpha fade.
     */
    public static void renderModIcon(GuiGraphics context, Button button, float f) {
        context.blit(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/gui/icon_other.png"), button.getX() + 1, button.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18, ARGB.color(f, CommonColors.WHITE));
    }

    /**
     * Renders the {@code sync icon} with a custom alpha fade.
     */
    public static void renderSyncIcon(GuiGraphics context, Button button, float f) {
        context.blit(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/gui/sync.png"), button.getX() + 2, button.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16, ARGB.color(f, CommonColors.WHITE));
    }
}