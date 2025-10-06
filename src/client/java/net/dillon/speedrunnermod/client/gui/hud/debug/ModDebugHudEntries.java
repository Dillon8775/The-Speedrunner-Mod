package net.dillon.speedrunnermod.client.gui.hud.debug;

import net.minecraft.client.gui.hud.debug.DebugHudEntries;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod debug hud entries.
 */
public class ModDebugHudEntries {
    public static final Identifier SPEEDRUNNER_MOD = DebugHudEntries.register(ofSpeedrunnerMod("speedrunner_mod_hud_entry"), new SpeedrunnerModHudEntry());

    /**
     * Initializes all mod debug hud entries.
     */
    public static void registerDebugHudEntries() {
    }
}