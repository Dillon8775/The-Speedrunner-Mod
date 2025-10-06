package net.dillon.speedrunnermod.client.gui.hud.debug;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.client.gui.hud.debug.DebugHudEntry;
import net.minecraft.client.gui.hud.debug.DebugHudLines;
import net.minecraft.world.World;
import net.minecraft.world.chunk.WorldChunk;
import org.jetbrains.annotations.Nullable;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * The text to render on the right side, indicating that the user is using the Speedrunner Mod.
 */
public class SpeedrunnerModHudEntry implements DebugHudEntry {

    @Override
    public void render(DebugHudLines lines, @Nullable World world, @Nullable WorldChunk clientChunk, @Nullable WorldChunk chunk) {
        lines.addLine(SpeedrunnerMod.THE_SPEEDRUNNER_MOD_STRING + " " + SpeedrunnerMod.MOD_VERSION);
        if (isDoomMode()) {
            lines.addLine("What's that? Doom Mode? Oh, flip.");
        }
    }
}