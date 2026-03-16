package net.dillon.speedrunnermod.client.keybind;

import com.mojang.blaze3d.platform.InputConstants;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.isSimpleKeybindsLoaded;

/**
 * All {@code Speedrunner Mod} keybindings.
 */
public class ModKeybindings {
    public static final KeyMapping.Category SPEEDRUNNER_MOD_KEYBINDINGS = KeyMapping.Category.register(ofSpeedrunnerMod("speedrunnermod.keybinds"));

    public static KeyMapping RESET = KeyMappingHelper.registerKeyMapping(new KeyMapping("speedrunnermod.create_new_world", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, SPEEDRUNNER_MOD_KEYBINDINGS));
    public static KeyMapping TOGGLE_FOG = KeyMappingHelper.registerKeyMapping(new KeyMapping("speedrunnermod.toggle_fog", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_O, SPEEDRUNNER_MOD_KEYBINDINGS));
    public static KeyMapping TOGGLE_FULLBRIGHT = KeyMappingHelper.registerKeyMapping(new KeyMapping("speedrunnermod.toggle_fullbright", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_V, SPEEDRUNNER_MOD_KEYBINDINGS));
    public static KeyMapping TOGGLE_HITBOXES = KeyMappingHelper.registerKeyMapping(new KeyMapping("speedrunnermod.toggle_hitboxes", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_X, SPEEDRUNNER_MOD_KEYBINDINGS));
    public static KeyMapping TOGGLE_CHUNK_BORDERS = KeyMappingHelper.registerKeyMapping(new KeyMapping("speedrunnermod.toggle_chunk_borders", InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_K, SPEEDRUNNER_MOD_KEYBINDINGS));

    /**
     * Initializes all {@code Speedrunner Mod} keybindings.
     */
    public static void initializeKeybinds() {
        SpeedrunnerMod.debug("Initialized keybinds.");
        if (isSimpleKeybindsLoaded()) {
            SpeedrunnerMod.debug("Simple Keybinds mod is loaded, compatibility has been added.");
        }
    }
}