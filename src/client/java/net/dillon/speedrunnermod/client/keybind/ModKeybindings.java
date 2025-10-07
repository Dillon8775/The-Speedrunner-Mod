package net.dillon.speedrunnermod.client.keybind;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.isSimpleKeybindsLoaded;

/**
 * All {@code Speedrunner Mod} keybindings.
 */
@Environment(EnvType.CLIENT)
public class ModKeybindings {
    public static final KeyBinding.Category SPEEDRUNNER_MOD_KEYBINDINGS = KeyBinding.Category.create(ofSpeedrunnerMod("speedrunnermod.keybinds"));

    public static KeyBinding RESET = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.create_new_world", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R, SPEEDRUNNER_MOD_KEYBINDINGS));
    public static KeyBinding TOGGLE_FOG = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.toggle_fog", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_O, SPEEDRUNNER_MOD_KEYBINDINGS));
    public static KeyBinding TOGGLE_FULLBRIGHT = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.toggle_fullbright", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V, SPEEDRUNNER_MOD_KEYBINDINGS));
    public static KeyBinding TOGGLE_HITBOXES = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.toggle_hitboxes", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_X, SPEEDRUNNER_MOD_KEYBINDINGS));
    public static KeyBinding TOGGLE_CHUNK_BORDERS = KeyBindingHelper.registerKeyBinding(new KeyBinding("speedrunnermod.toggle_chunk_borders", InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_K, SPEEDRUNNER_MOD_KEYBINDINGS));

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