package net.dillon.speedrunnermod.mixin.fix;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import static net.dillon.speedrunnermod.option.CommonModOptions.isDoomMode;

/**
 * Prevents and fixes piglin brutes from spawning in the air.
 */
@Mixin(SpawnPlacements.class)
public class SpawnPlacementsFix {

    static {
        if (isDoomMode()) {
            SpawnPlacements.register(EntityTypes.PIGLIN_BRUTE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnPlacementsFix::canPiglinBruteSpawn);
        }
    }

    @Unique
    private static boolean canPiglinBruteSpawn(EntityType<? extends PiglinBrute> type, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return !world.getBlockState(pos.below()).is(Blocks.NETHER_WART_BLOCK);
    }
}