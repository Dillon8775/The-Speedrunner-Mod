package net.dillon.speedrunnermod.client.gui.hud.debug;

import net.minecraft.client.gui.hud.debug.DebugHudEntry;
import net.minecraft.client.gui.hud.debug.DebugHudLines;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * Indicates the player's {@code Mode.}
 */
public class ModeHudEntry implements DebugHudEntry {

    @Override
    public void render(DebugHudLines lines, @Nullable World world, @Nullable WorldChunk clientChunk, @Nullable WorldChunk chunk) {
        lines.addLine("Playing Mode: " + options().main.mode.getCurrentValue().toString().toLowerCase());
        if (isDoomMode()) {
            lines.addLine("What's that? Doom Mode? Oh, flip.");
        }
    }

    /**
     * Set to {@code true} by default.
     */
    @Override
    public boolean canShow(boolean reducedDebugInfo) {
        return true;
    }
}