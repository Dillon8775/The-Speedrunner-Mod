package net.dillon.speedrunnermod.mixin.main.block.dispenser;

import net.dillon.speedrunnermod.block.SkullBlockInvoker;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
import net.minecraft.core.dispenser.DispenseItemBehavior;
import net.minecraft.core.dispenser.EquipmentDispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.properties.RotationSegment;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DispenseItemBehavior.class)
public interface DispenseItemBehaviorMixin {

    /**
     * Registers dispenser behavior for summmoning Goliath.
     * <p>See {@link DispenseItemBehavior#bootStrap()} for more.</p>
     */
    @Inject(method = "bootStrap", at = @At("TAIL"))
    private static void registerGoliathSummoning(CallbackInfo ci) {
        DispenserBlock.registerBehavior(
                Blocks.ZOMBIE_HEAD,
                new OptionalDispenseItemBehavior() {
                    @Override
                    protected ItemStack execute(BlockSource pointer, ItemStack stack) {
                        Level world = pointer.level();
                        Direction direction = pointer.state().getValue(DispenserBlock.FACING);
                        BlockPos blockPos = pointer.pos().relative(direction);
                        if (world.isEmptyBlock(blockPos) && ((SkullBlockInvoker) Blocks.ZOMBIE_HEAD).canDispense(world, blockPos, stack)) {
                            world.setBlock(
                                    blockPos,
                                    Blocks.ZOMBIE_HEAD.defaultBlockState().setValue(SkullBlock.ROTATION, Integer.valueOf(RotationSegment.convertToSegment(direction))),
                                    Block.UPDATE_ALL
                            );
                            world.gameEvent(null, GameEvent.BLOCK_PLACE, blockPos);
                            BlockEntity blockEntity = world.getBlockEntity(blockPos);
                            Block block = world.getBlockState(blockPos).getBlock();
                            if (block instanceof SkullBlockInvoker invoker && blockEntity instanceof SkullBlockEntity) {
                                invoker.onPlaced(world, blockPos, (SkullBlockEntity)blockEntity);
                            }

                            stack.shrink(1);
                            this.setSuccess(true);
                        } else {
                            this.setSuccess(EquipmentDispenseItemBehavior.dispenseEquipment(pointer, stack));
                        }

                        return stack;
                    }
                }
        );
    }
}