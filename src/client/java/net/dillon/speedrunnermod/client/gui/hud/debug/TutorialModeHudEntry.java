package net.dillon.speedrunnermod.client.gui.hud.debug;

import net.minecraft.client.gui.hud.debug.DebugHudEntry;
import net.minecraft.client.gui.hud.debug.DebugHudLines;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

/**
 * Indicates if the player is playing on tutorial mode.
 */
public class TutorialModeHudEntry implements DebugHudEntry {

    @Override
    public void render(DebugHudLines lines, @Nullable World world, @Nullable WorldChunk clientChunk, @Nullable WorldChunk chunk) {
        if (clientOptions().client.tutorialMode.getCurrentValue()) {
            lines.addLine("Playing on Tutorial Mode");
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