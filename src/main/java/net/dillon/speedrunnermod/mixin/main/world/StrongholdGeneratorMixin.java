package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.world.ModWorldGen;
import net.minecraft.structure.StrongholdGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Makes strongholds smaller, and easier to navigate.
 * <p>Applies the opposite effect if {@code doom mode} is enabled.</p>
 */
@Mixin(StrongholdGenerator.class)
public class StrongholdGeneratorMixin {
    @Shadow @Final @Mutable
    private static StrongholdGenerator.PieceData[] ALL_PIECES;

    static {
        if (options().main.customDataGeneration && (!options().main.playingMode.balanced() || !options().advanced.modifiedStrongholdGeneration)) {
            ALL_PIECES = ModWorldGen.MODIFIED_STRONGHOLD_PIECES;
        }
    }
}