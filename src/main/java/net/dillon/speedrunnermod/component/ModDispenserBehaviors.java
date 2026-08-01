package net.dillon.speedrunnermod.component;

import net.dillon.dillonlib.factory.Factories;
import net.dillon.speedrunnermod.block.SkullBlockInvoker;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.dispenser.BlockSource;
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
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.Map;

/**
 * All dispenser behaviors for the speedrunner mod.
 */
public class ModDispenserBehaviors {

    /**
     * Registers all Speedrunner Mod dispenser behaviors.
     */
    public static void registerDispenserBehaviors() {
        DispenserBlock.registerBehavior(
                Blocks.ZOMBIE_HEAD,
                new OptionalDispenseItemBehavior() {
                    @Override
                    protected @NonNull ItemStack execute(@NonNull BlockSource pointer, @NonNull ItemStack stack) {
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

        Factories.registerBoatDispenserBehavior(List.of(
                Map.of(ModItems.SPEEDRUNNER_BOAT, ModEntityTypes.SPEEDRUNNER_BOAT),
                Map.of(ModItems.SPEEDRUNNER_CHEST_BOAT, ModEntityTypes.SPEEDRUNNER_CHEST_BOAT),
                Map.of(ModItems.FIREPROOF_SPEEDRUNNER_BOAT, ModEntityTypes.FIREPROOF_SPEEDRUNNER_BOAT),
                Map.of(ModItems.FIREPROOF_SPEEDRUNNER_CHEST_BOAT, ModEntityTypes.FIREPROOF_SPEEDRUNNER_CHEST_BOAT),
                Map.of(ModItems.DEAD_SPEEDRUNNER_BOAT, ModEntityTypes.DEAD_SPEEDRUNNER_BOAT),
                Map.of(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT, ModEntityTypes.DEAD_SPEEDRUNNER_CHEST_BOAT),
                Map.of(ModItems.CRIMSON_BOAT, ModEntityTypes.CRIMSON_BOAT),
                Map.of(ModItems.CRIMSON_CHEST_BOAT, ModEntityTypes.CRIMSON_CHEST_BOAT),
                Map.of(ModItems.FIREPROOF_CRIMSON_BOAT, ModEntityTypes.FIREPROOF_CRIMSON_CHEST_BOAT),
                Map.of(ModItems.FIREPROOF_CRIMSON_CHEST_BOAT, ModEntityTypes.FIREPROOF_CRIMSON_CHEST_BOAT),
                Map.of(ModItems.WARPED_BOAT, ModEntityTypes.WARPED_BOAT),
                Map.of(ModItems.WARPED_CHEST_BOAT, ModEntityTypes.WARPED_CHEST_BOAT),
                Map.of(ModItems.FIREPROOF_WARPED_BOAT, ModEntityTypes.FIREPROOF_WARPED_CHEST_BOAT),
                Map.of(ModItems.FIREPROOF_WARPED_CHEST_BOAT, ModEntityTypes.FIREPROOF_WARPED_CHEST_BOAT)
        ));

        Factories.registerShearDispenserBehavior(List.of(
                ModItems.SPEEDRUNNER_SHEARS
        ));

        Factories.registerFlintAndSteelDispenserBehavior(List.of(
                ModItems.SPEEDRUNNER_FLINT_AND_STEEL
        ));
    }
}