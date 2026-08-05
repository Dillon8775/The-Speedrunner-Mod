package net.dillon.speedrunnermod.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.blay09.mods.kuma.api.*;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.platform.ModReferences;
import net.dillon.speedrunnermod.util.ClientModUtil;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.network.chat.Component;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.*;

/**
 * All {@code Speedrunner Mod} keybindings.
 */
public class ModKeyMappings {
    public static final KeyMapping.Category SPEEDRUNNER_MOD_KEY_CATEGORY = KeyMapping.Category.register(ofSpeedrunnerMod("speedrunnermod.keybinds"));

    /**
     * Initializes all {@code Speedrunner Mod} keybindings.
     */
    public static void initializeKeybinds() {
        SpeedrunnerMod.debug("Initialized keybinds.");

        if (isSimpleKeybindsLoaded()) {
            SpeedrunnerMod.debug("Simple Keybinds mod is loaded, compatibility has been added.");
        }
    }

    public static final ManagedKeyMapping RESET_WORLD = Kuma.createKeyMapping(ofSpeedrunnerMod("create_new_world"))
            .overrideCategory(SPEEDRUNNER_MOD_KEY_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_R, KeyModifiers.of(KeyModifier.CONTROL)))
            .handleWorldInput(input -> {
                Minecraft minecraft = Minecraft.getInstance();
                if (minecraft.isLocalServer() && minecraft.getCurrentServer() == null) {
                    if (clientOptions().client.instantWorldCreation.getCurrentValue()) {
                        ClientModUtil.createNewWorld(Minecraft.getInstance());
                        return true;
                    } else {
                        debugWarn("key.speedrunnermod.create_new_world.disabled");
                    }
                } else {
                    debugWarn("key.speedrunnermod.create_new_world.in_multiplayer");
                }
                return false;
            })
            .build();

    public static final ManagedKeyMapping TOGGLE_FOG = Kuma.createKeyMapping(ofSpeedrunnerMod("toggle_fog"))
            .overrideCategory(SPEEDRUNNER_MOD_KEY_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_O))
            .handleWorldInput(input -> {
                if (ModReferences.isModLoaded(ModReferences.QUALITY_OF_QUESO)) {
                    debugWarn("key.speedrunnermod.toggle_fog.quality_of_queso_loaded");
                } else if (ModReferences.isModLoaded(ModReferences.SIMPLE_KEYBINDS)) {
                    debugWarn("key.speedrunnermod.simple_keybinds_loaded");
                } else if (!clientOptions().mixins.fogMixins.getCurrentValue()) {
                    debugWarn("key.speedrunnermod.toggle_fog.mixin_disabled");
                } else {
                    clientOptions().client.fog.set(!clientOptions().client.fog.getCurrentValue());
                    saveClientChanges();
                    Minecraft.getInstance().levelExtractor.allChanged();
                    return true;
                }
                return false;
            })
            .build();

    public static final ManagedKeyMapping TOGGLE_FULLBRIGHT = Kuma.createKeyMapping(ofSpeedrunnerMod("toggle_fullbright"))
            .overrideCategory(SPEEDRUNNER_MOD_KEY_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_V))
            .handleWorldInput(input -> {
                if (ModReferences.isModLoaded(ModReferences.SIMPLE_KEYBINDS)) {
                    debugWarn("key.speedrunnermod.simple_keybinds_loaded");
                } else if (!clientOptions().mixins.optionInstanceMixin.getCurrentValue()) {
                    debugWarn("key.speedrunnermod.toggle_fullbright.mixin_disabled");
                } else {
                    clientOptions().client.fullBright.set(!clientOptions().client.fullBright.getCurrentValue());
                    saveClientChanges();
                    Minecraft.getInstance().options.gamma().set(clientOptions().client.fullBright.getCurrentValue() ? SpeedrunnerModClient.getMaxBrightness() : 1.0D);
                    Minecraft.getInstance().options.save();
                    return true;
                }
                return false;
            })
            .build();

    public static final ManagedKeyMapping TOGGLE_HITBOXES = Kuma.createKeyMapping(ofSpeedrunnerMod("toggle_hitboxes"))
            .overrideCategory(SPEEDRUNNER_MOD_KEY_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_X))
            .handleWorldInput(input -> {
                if (ModReferences.isModLoaded(ModReferences.SIMPLE_KEYBINDS)) {
                    debugWarn("key.speedrunnermod.simple_keybinds_loaded");
                } else {
                    boolean bl = Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.ENTITY_HITBOXES);
                    debugWarn(bl ? "debug.show_hitboxes.on" : "debug.show_hitboxes.off");
                    return true;
                }
                return false;
            })
            .build();

    public static final ManagedKeyMapping TOGGLE_CHUNK_BORDERS = Kuma.createKeyMapping(ofSpeedrunnerMod("toggle_chunk_borders"))
            .overrideCategory(SPEEDRUNNER_MOD_KEY_CATEGORY)
            .withDefault(InputBinding.key(InputConstants.KEY_K))
            .handleWorldInput(input -> {
                if (ModReferences.isModLoaded(ModReferences.SIMPLE_KEYBINDS)) {
                    debugWarn("key.speedrunnermod.simple_keybinds_loaded");
                } else {
                    boolean bl = Minecraft.getInstance().debugEntries.toggleStatus(DebugScreenEntries.ENTITY_HITBOXES);
                    debugWarn(bl ? "debug.chunk_boundaries.on" : "debug.chunk_boundaries.off");
                    return true;
                }
                return false;
            })
            .build();

    /**
     * Sends the player a message.
     */
    private static void debugWarn(String stringOrTranslation, Object... objects) {
        Minecraft.getInstance().gui.hud.getChat().addClientSystemMessage(Component.translatable(stringOrTranslation, objects));
    }
}