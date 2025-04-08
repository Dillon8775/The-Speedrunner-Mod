package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

/**
 * Interface used for registering dispenser behavior.
 */
@ChatGPT(Credit.FULL_CREDIT)
public interface SkullBlockInvoker {
    void onPlaced(World world, BlockPos pos, SkullBlockEntity entity);
    boolean canDispense(World world, BlockPos pos, ItemStack stack);
}