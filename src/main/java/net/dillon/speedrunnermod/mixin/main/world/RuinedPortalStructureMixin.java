package net.dillon.speedrunnermod.mixin.main.world;

import net.minecraft.structure.RuinedPortalStructurePiece;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.HeightLimitView;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.noise.NoiseConfig;
import net.minecraft.world.gen.structure.RuinedPortalStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(RuinedPortalStructure.class)
public class RuinedPortalStructureMixin {

    /**
     * Allows ruined portals to generate correctly with the {@code speedrunner mod's world generation modifications.}
     */
    @Inject(method = "getFloorHeight", at = @At(value = "RETURN"), cancellable = true)
    private static void getNewFloorHeight(Random random, ChunkGenerator chunkGenerator, RuinedPortalStructurePiece.VerticalPlacement verticalPlacement, boolean airPocket, int height, int blockCountY, BlockBox box, HeightLimitView world, NoiseConfig noiseConfig, CallbackInfoReturnable<Integer> cir) {
        if (options().main.customDataGeneration) {
            if (verticalPlacement == RuinedPortalStructurePiece.VerticalPlacement.PARTLY_BURIED || verticalPlacement == RuinedPortalStructurePiece.VerticalPlacement.UNDERGROUND) {
                cir.setReturnValue(height);
            } else if (verticalPlacement == RuinedPortalStructurePiece.VerticalPlacement.IN_MOUNTAIN) {
                cir.setReturnValue(height - blockCountY + MathHelper.nextBetween(random, 2, 8));
            }
        }
    }
}