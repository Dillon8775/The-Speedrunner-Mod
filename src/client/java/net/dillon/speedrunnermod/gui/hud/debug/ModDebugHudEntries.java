package net.dillon.speedrunnermod.gui.hud.debug;

import net.minecraft.client.gui.components.debug.DebugScreenEntries;
import net.minecraft.resources.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod debug hud entries.
 */
public class ModDebugHudEntries {
    public static final Identifier VERSION = DebugScreenEntries.register(ofSpeedrunnerMod("version"), new SpeedrunnerModHudEntry());
    public static final Identifier MODE = DebugScreenEntries.register(ofSpeedrunnerMod("mode"), new ModeHudEntry());

    /**
     * Initializes all mod debug hud entries.
     */
    public static void registerDebugHudEntries() {
    }
}