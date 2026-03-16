package net.dillon.speedrunnermod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.SkullBlockEntity;

/**
 * Interface used for registering dispenser behavior.
 */
public interface SkullBlockInvoker {
    void onPlaced(Level world, BlockPos pos, SkullBlockEntity entity);
    boolean canDispense(Level world, BlockPos pos, ItemStack stack);
}