package net.dillon.speedrunnermod.util;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.util.CommonColors;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Utility class for client-side features.
 */
public class ClientModUtil {

    /**
     * Quickly creates a new world.
     */
    public static void createNewWorld(Minecraft minecraft) {
        if (minecraft.gui != null) {
            minecraft.gui.hud.getChat().clearMessages(false);
        }
        if (minecraft.level != null) {
            minecraft.level.disconnect(Component.translatable("speedrunnermod.creating_new_world"));
        }
        minecraft.disconnect(new GenericMessageScreen(Component.translatable("speedrunnermod.creating_new_world")), false, false);
        CreateWorldScreen.openFresh(minecraft, null);
    }

    /**
     * Renders the {@code golden speedrunner smithing template} texture with a custom width and height.
     */
    public static void renderSpeedrunnerSmithingTemplate(GuiGraphicsExtractor context, Button button) {
        renderSpeedrunnerSmithingTemplate(context, button, 1.0F);
    }

    /**
     * Renders the {@code mod icon.}
     */
    public static void renderModIcon(GuiGraphicsExtractor context, Button button) {
        renderModIcon(context, button, 1.0F);
    }

    /**
     * Renders the {@code sync icon.}
     */
    public static void renderSyncIcon(GuiGraphicsExtractor context, Button button) {
        renderSyncIcon(context, button, 1.0F);
    }

    /**
     * Renders the {@code golden speedrunner smithing template} texture with a custom width and height and a custom alpha fade.
     */
    public static void renderSpeedrunnerSmithingTemplate(GuiGraphicsExtractor context, Button button, float f) {
        int frametime = 4;
        int currentFrame = (int) ((System.currentTimeMillis() / (frametime * 50)) % 8);
        context.blit(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/item/golden_upgrade_smithing_template.png"), button.getX() + 2, button.getY() + 2, 0.0F, currentFrame * 16, 16, 16, 16, 128, ARGB.color(f, CommonColors.WHITE));
    }

    /**
     * Renders the {@code mod icon} with a custom alpha fade.
     */
    public static void renderModIcon(GuiGraphicsExtractor context, Button button, float f) {
        context.blit(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/gui/icon_other.png"), button.getX() + 1, button.getY() + 1, 0.0F, 0.0F, 18, 18, 18, 18, ARGB.color(f, CommonColors.WHITE));
    }

    /**
     * Renders the {@code sync icon} with a custom alpha fade.
     */
    public static void renderSyncIcon(GuiGraphicsExtractor context, Button button, float f) {
        context.blit(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/gui/sync.png"), button.getX() + 2, button.getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16, ARGB.color(f, CommonColors.WHITE));
    }
}