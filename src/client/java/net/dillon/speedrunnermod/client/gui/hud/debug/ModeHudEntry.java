package net.dillon.speedrunnermod.client.gui.hud.debug;

import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jetbrains.annotations.Nullable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Indicates the player's {@code Mode.}
 */
public class ModeHudEntry implements DebugScreenEntry {

    @Override
    public void display(DebugScreenDisplayer lines, @Nullable Level world, @Nullable LevelChunk clientChunk, @Nullable LevelChunk chunk) {
        lines.addLine("Playing Mode: " + options().main.mode.getCurrentValue().toString().toLowerCase());
    }

    /**
     * Set to {@code true} by default.
     */
    @Override
    public boolean isAllowed(boolean reducedDebugInfo) {
        return true;
    }
}