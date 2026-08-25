package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.tag.ModBlockItemTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BonemealSource;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockState;

/**
 * A sapling that grows faster, and can be placed on a few extra blocks.
 */
public class SpeedrunnerSaplingBlock extends SaplingBlock {

    protected SpeedrunnerSaplingBlock(TreeGrower generator, Properties settings) {
        super(generator, settings);
    }

    /**
     * Decreases the time it takes for a speedrunner sapling to grow.
     */
    @Override
    public boolean isBonemealSuccess(final Level level, final RandomSource random, final BlockPos pos, final BlockState state, final BonemealSource source) {
        return (double)level.getRandom().nextFloat() < 0.99;
    }

    /**
     * Allows for planting on sand, soul sand, and netherrack blocks.
     */
    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(ModBlockItemTags.SPEEDRUNNER_SAPLING_PLACEABLES.block()) || super.mayPlaceOn(floor, world, pos);
    }
}