package net.dillon.speedrunnermod.client.gui.hud.debug;

import net.minecraft.client.gui.hud.debug.DebugHudEntries;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod debug hud entries.
 */
public class ModDebugHudEntries {
    public static final Identifier VERSION = DebugHudEntries.register(ofSpeedrunnerMod("version"), new SpeedrunnerModHudEntry());
    public static final Identifier MODE = DebugHudEntries.register(ofSpeedrunnerMod("mode"), new ModeHudEntry());
    public static final Identifier TUTORIAL_MODE = DebugHudEntries.register(ofSpeedrunnerMod("tutorial_mode"), new TutorialModeHudEntry());

    /**
     * Initializes all mod debug hud entries.
     */
    public static void registerDebugHudEntries() {
    }
}