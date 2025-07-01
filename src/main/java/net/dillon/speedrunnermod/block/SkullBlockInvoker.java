package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.util.AI;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Interface used for registering dispenser behavior.
 */
@AI
public interface SkullBlockInvoker {
    void onPlaced(World world, BlockPos pos, SkullBlockEntity entity);
    boolean canDispense(World world, BlockPos pos, ItemStack stack);
}