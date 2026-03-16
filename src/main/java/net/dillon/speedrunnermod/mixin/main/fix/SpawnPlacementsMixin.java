package net.dillon.speedrunnermod.mixin.main.fix;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * Prevents and fixes piglin brutes from spawning in the air.
 */
@Mixin(SpawnPlacements.class)
public class SpawnPlacementsMixin {

    static {
        if (isDoomMode()) {
            SpawnPlacements.register(EntityType.PIGLIN_BRUTE, SpawnPlacementTypes.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, SpawnPlacementsMixin::canPiglinBruteSpawn);
        }
    }

    @Unique
    private static boolean canPiglinBruteSpawn(EntityType<? extends PiglinBrute> type, ServerLevelAccessor world, EntitySpawnReason reason, BlockPos pos, RandomSource random) {
        return !world.getBlockState(pos.below()).is(Blocks.NETHER_WART_BLOCK);
    }
}