package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.world.ModWorldGen;
import net.minecraft.structure.NetherFortressGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Makes nether fortress smaller and easier to navigate, see {@link ModWorldGen} for more.
 */
@Mixin(NetherFortressGenerator.class)
public class NetherFortressGeneratorMixin {
    @Shadow @Final @Mutable
    private static NetherFortressGenerator.PieceData[] ALL_BRIDGE_PIECES = ModWorldGen.MODIFIED_NETHER_FORTRESS_BRIDGE_PIECES;
    @Shadow @Final @Mutable
    private static  NetherFortressGenerator.PieceData[] ALL_CORRIDOR_PIECES = ModWorldGen.MODIFIED_NETHER_FORTRESS_CORRIDOR_PIECES;

    static {
        if (options().main.customDataGeneration && (!options().main.playingMode.balanced() || !options().advanced.modifiedNetherFortressGeneration)) {
            ALL_BRIDGE_PIECES = ModWorldGen.MODIFIED_NETHER_FORTRESS_BRIDGE_PIECES;
            ALL_CORRIDOR_PIECES = ModWorldGen.MODIFIED_NETHER_FORTRESS_CORRIDOR_PIECES;
        }
    }
}