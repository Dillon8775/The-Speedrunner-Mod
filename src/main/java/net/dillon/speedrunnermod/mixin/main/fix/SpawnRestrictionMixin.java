package net.dillon.speedrunnermod.mixin.main.fix;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.PiglinBruteEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import static net.dillon.speedrunnermod.option.ModOptions.isPlayingModeDoom;

/**
 * Prevents and fixes piglin brutes from spawning in the air.
 */
@Mixin(SpawnRestriction.class)
public class SpawnRestrictionMixin {

    static {
        if (isPlayingModeDoom()) {
            SpawnRestriction.register(EntityType.PIGLIN_BRUTE, SpawnLocationTypes.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, SpawnRestrictionMixin::canPiglinBruteSpawn);
        }
    }

    @Unique
    private static boolean canPiglinBruteSpawn(EntityType<? extends PiglinBruteEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random) {
        return !world.getBlockState(pos.down()).isOf(Blocks.NETHER_WART_BLOCK);
    }
}