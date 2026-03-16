package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.tag.ModBlockTags;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
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
    public boolean isBonemealSuccess(Level world, RandomSource random, BlockPos pos, BlockState state) {
        return (double)world.getRandom().nextFloat() < 0.99;
    }

    /**
     * Allows for planting on sand, soul sand, and netherrack blocks.
     */
    @Override
    protected boolean mayPlaceOn(BlockState floor, BlockGetter world, BlockPos pos) {
        return floor.is(ModBlockTags.SPEEDRUNNER_SAPLING_PLACEABLES) || super.mayPlaceOn(floor, world, pos);
    }
}