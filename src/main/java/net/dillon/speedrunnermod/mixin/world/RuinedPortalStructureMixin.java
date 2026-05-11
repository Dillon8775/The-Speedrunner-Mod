package net.dillon.speedrunnermod.mixin.world;

import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.RandomState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.structures.RuinedPortalPiece;
import net.minecraft.world.level.levelgen.structure.structures.RuinedPortalStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(RuinedPortalStructure.class)
public class RuinedPortalStructureMixin {

    /**
     * Allows ruined portals to generate correctly with the {@code speedrunner mod's world generation modifications.}
     */
    @Inject(method = "findSuitableY", at = @At(value = "RETURN"), cancellable = true)
    private static void newFloorHeight(RandomSource random, ChunkGenerator chunkGenerator, RuinedPortalPiece.VerticalPlacement verticalPlacement, boolean airPocket, int height, int blockCountY, BoundingBox box, LevelHeightAccessor world, RandomState noiseConfig, CallbackInfoReturnable<Integer> cir) {
        if (options().main.customDataGeneration.getCurrentValue()) {
            if (verticalPlacement == RuinedPortalPiece.VerticalPlacement.PARTLY_BURIED || verticalPlacement == RuinedPortalPiece.VerticalPlacement.UNDERGROUND) {
                cir.setReturnValue(height);
            } else if (verticalPlacement == RuinedPortalPiece.VerticalPlacement.IN_MOUNTAIN) {
                cir.setReturnValue(height - blockCountY + Mth.randomBetweenInclusive(random, 2, 8));
            }
        }
    }
}