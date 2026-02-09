package net.dillon.speedrunnermod.mixin.main.block.dispenser;

import net.dillon.speedrunnermod.block.SkullBlockInvoker;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.SkullBlock;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.block.dispenser.EquippableDispenserBehavior;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.RotationPropertyHelper;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DispenserBehavior.class)
public interface DispenserBehaviorMixin {

    /**
     * Registers dispenser behavior for summmoning Goliath.
     * <p>See {@link DispenserBehavior#registerDefaults()} for more.</p>
     */
    @Inject(method = "registerDefaults", at = @At("TAIL"))
    private static void registerGoliathSummoning(CallbackInfo ci) {
        DispenserBlock.registerBehavior(
                Blocks.ZOMBIE_HEAD,
                new FallibleItemDispenserBehavior() {
                    @Override
                    protected ItemStack dispenseSilently(BlockPointer pointer, ItemStack stack) {
                        World world = pointer.world();
                        Direction direction = pointer.state().get(DispenserBlock.FACING);
                        BlockPos blockPos = pointer.pos().offset(direction);
                        if (world.isAir(blockPos) && ((SkullBlockInvoker)Blocks.ZOMBIE_HEAD).canDispense(world, blockPos, stack)) {
                            world.setBlockState(
                                    blockPos,
                                    Blocks.ZOMBIE_HEAD.getDefaultState().with(SkullBlock.ROTATION, Integer.valueOf(RotationPropertyHelper.fromDirection(direction))),
                                    Block.NOTIFY_ALL
                            );
                            world.emitGameEvent(null, GameEvent.BLOCK_PLACE, blockPos);
                            BlockEntity blockEntity = world.getBlockEntity(blockPos);
                            Block block = world.getBlockState(blockPos).getBlock();
                            if (block instanceof SkullBlockInvoker invoker && blockEntity instanceof SkullBlockEntity) {
                                invoker.onPlaced(world, blockPos, (SkullBlockEntity)blockEntity);
                            }

                            stack.decrement(1);
                            this.setSuccess(true);
                        } else {
                            this.setSuccess(EquippableDispenserBehavior.dispense(pointer, stack));
                        }

                        return stack;
                    }
                }
        );
    }
}