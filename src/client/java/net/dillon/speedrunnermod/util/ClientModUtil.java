package net.dillon.speedrunnermod.util;

import net.dillon.dillonlib.platform.info.UpdatableSpriteButton;
import net.dillon.dillonlib.task.ClientTasks;
import net.dillon.dillonlib.util.Texts;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.MainScreen;
import net.dillon.speedrunnermod.screen.feature.FeaturesScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.util.ARGB;
import net.minecraft.util.CommonColors;

import java.util.Map;

import static net.dillon.dillonlib.task.ClientTasks.*;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.client;

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
     * Creates the speedrunner mod main menu featuresButton.
     */
    public static UpdatableSpriteButton createMenuButton(Screen parent) {
        return ClientTasks.createMenuButton(
                "Speedrunner Mod Main Menu",
                ofSpeedrunnerMod("hud/logo_pickaxe"),
                onPress -> openScreen(new MainScreen(parent)),
                Map.of(
                        ModConstants.HAS_UPDATE,
                        Component.translatable("speedrunnermod.title.update_available")
                ),
                Component.translatable("speedrunnermod.title.options.tooltip"),
                18,
                18,
                true
        );
    }

    /**
     * Creates the features featuresButton.
     */
    public static Button createFeaturesButton(Screen parent, int x, int y) {
        return Button.builder(Texts.BLANK, (buttonWidget) -> {
            openScreen(new FeaturesScreen(parent));
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.features.tooltip"))
        ).bounds(x, y, 20, 20).build();
    }

    /**
     * Creates the create new world featuresButton.
     */
    public static Button createNewWorldButton(Screen parent, int x, int y) {
        return Button.builder(Texts.BLANK, (buttonWidget) -> ClientModUtil.createNewWorld(getMinecraft()))
                .bounds(x, y, 20, 20)
                .tooltip(
                        Tooltip.create(
                                client().worldCreation().instantWorldCreation ?
                                        ModTexts.CREATE_WORLD_BUTTON_TOOLTIP :
                                        ModTexts.CREATE_WORLD_BUTTON_DISABLED_TOOLTIP
                        )
                )
                .build();
    }

    /**
     * Renders the speedrunner mod logo at a specified position.
     */
    public static void renderSpeedrunnerModLogo(GuiGraphicsExtractor graphics, int x, int y, boolean big) {
        drawSprite(
                graphics,
                ofSpeedrunnerMod("hud/speedrunner_mod"),
                x,
                y,
                big ? 258 : 129,
                big ? 32 : 16
        );
    }

    /**
     * Draws the speedrunner boots texture on the {@code create world featuresButton}.
     */
    public static void renderSpeedrunnerBoots(GuiGraphicsExtractor graphics, Button createWorldButton) {
        renderSpeedrunnerBoots(
                graphics,
                createWorldButton,
                1.0F
        );
    }

    /**
     * Draws the speedrunner boots texture on the {@code create world featuresButton} with a custom alpha fade.
     */
    public static void renderSpeedrunnerBoots(GuiGraphicsExtractor graphics, Button createWorldButton, float f) {
        blitSmallTexture(
                graphics,
                ofSpeedrunnerMod("textures/item/speedrunner_boots.png"),
                createWorldButton,
                f
        );
    }

    /**
     * Renders the {@code golden speedrunner smithing template} texture with a custom width and height.
     */
    public static void renderSpeedrunnerSmithingTemplate(GuiGraphicsExtractor graphics, Button featuresButton) {
        renderSpeedrunnerSmithingTemplate(
                graphics,
                featuresButton,
                1.0F);
    }

    /**
     * Renders the {@code golden speedrunner smithing template} texture with a custom width and height and a custom alpha fade.
     */
    public static void renderSpeedrunnerSmithingTemplate(GuiGraphicsExtractor graphics, Button featuresButton, float f) {
        int frametime = 4;
        int currentFrame = (int) ((System.currentTimeMillis() / (frametime * 50)) % 8);
        graphics.blit(RenderPipelines.GUI_TEXTURED, ofSpeedrunnerMod("textures/item/golden_upgrade_smithing_template.png"), featuresButton.getX() + 2, featuresButton.getY() + 2, 0.0F, currentFrame * 16, 16, 16, 16, 128, ARGB.color(f, CommonColors.WHITE));
    }
}