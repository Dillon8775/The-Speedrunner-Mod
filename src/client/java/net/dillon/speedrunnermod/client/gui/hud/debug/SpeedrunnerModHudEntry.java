package net.dillon.speedrunnermod.client.gui.hud.debug;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.client.gui.components.debug.DebugScreenDisplayer;
import net.minecraft.client.gui.components.debug.DebugScreenEntry;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.chunk.LevelChunk;
import org.jetbrains.annotations.Nullable;

/**
 * The text to render on the right side, indicating that the user is using the Speedrunner Mod.
 */
public class SpeedrunnerModHudEntry implements DebugScreenEntry {

    @Override
    public void display(DebugScreenDisplayer lines, @Nullable Level world, @Nullable LevelChunk clientChunk, @Nullable LevelChunk chunk) {
        lines.addLine(SpeedrunnerMod.THE_SPEEDRUNNER_MOD_STRING + " " + SpeedrunnerMod.MOD_VERSION);
    }

    /**
     * Set to {@code true} by default.
     */
    @Override
    public boolean isAllowed(boolean reducedDebugInfo) {
        return true;
    }
}