package net.dillon.speedrunnermod.mixin.block;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.block.SkullBlockInvoker;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.pattern.BlockInWorld;
import net.minecraft.world.level.block.state.pattern.BlockPattern;
import net.minecraft.world.level.block.state.pattern.BlockPatternBuilder;
import net.minecraft.world.level.block.state.predicate.BlockStatePredicate;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

/**
 * For summoning Goliath.
 */
@Mixin(SkullBlock.class)
public abstract class SkullBlockMixin extends AbstractSkullBlock implements SkullBlockInvoker {
    @Unique
    @Nullable
    private static BlockPattern goliathBossPattern;
    @Unique
    @Nullable
    private static BlockPattern goliathDispenserPattern;

    public SkullBlockMixin(SkullBlock.Type type, Properties settings) {
        super(type, settings);
    }

    @Override
    public void setPlacedBy(Level world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        onPlaced(world, pos);
    }

    /**
     * Helper method for on placed.
     */
    @Unique
    private void onPlaced(Level world, BlockPos pos) {
        if (world.getBlockEntity(pos) instanceof SkullBlockEntity skullBlockEntity) {
            onPlaced(world, pos, skullBlockEntity);
        }
    }

    /**
     * Handles the summoning of Goliath.
     * <p>See {@link WitherSkullBlock#checkSpawn(Level, BlockPos, SkullBlockEntity)} for more.</p>
     */
    @Unique
    public void onPlaced(Level world, BlockPos pos, SkullBlockEntity blockEntity) {
        if (!world.isClientSide()) {
            BlockState blockState = blockEntity.getBlockState();
            boolean bl = blockState.is(Blocks.ZOMBIE_HEAD) || blockState.is(Blocks.ZOMBIE_WALL_HEAD);
            if (bl && pos.getY() >= world.getMinY() && world.getDifficulty() != Difficulty.PEACEFUL) {
                BlockPattern.BlockPatternMatch result = getGoliathBossPattern().find(world, pos);
                if (result != null) {
                    Giant giant = EntityType.GIANT.create(world, EntitySpawnReason.TRIGGERED);
                    if (giant != null) {
                        CarvedPumpkinBlock.clearPatternBlocks(world, result);
                        BlockPos blockPos = result.getBlock(1, 2, 0).getPos();
                        giant.snapTo(
                                (double)blockPos.getX() + 0.5,
                                (double)blockPos.getY() + 0.55,
                                (double)blockPos.getZ() + 0.5,
                                result.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F,
                                0.0F
                        );
                        giant.yBodyRot = result.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F;

                        for (ServerPlayer serverPlayerEntity : world.getEntitiesOfClass(ServerPlayer.class, giant.getBoundingBox().inflate(50.0))) {
                            CriteriaTriggers.SUMMONED_ENTITY.trigger(serverPlayerEntity, giant);
                        }

                        world.addFreshEntity(giant);
                        CarvedPumpkinBlock.updatePatternBlocks(world, result);
                    }
                }
            }
        }
    }

    /**
     * Determines if the block can be dispensed.
     */
    @Unique
    public boolean canDispense(Level world, BlockPos pos, ItemStack stack) {
        return stack.is(Items.ZOMBIE_HEAD) && pos.getY() >= world.getMinY() + 2 && world.getDifficulty() != Difficulty.PEACEFUL && !world.isClientSide() && getGoliathDispenserPattern().find(world, pos) != null;
    }

    /**
     * Checks for summonable Goliath feature.
     */
    @Unique
    private static BlockPattern getGoliathBossPattern() {
        if (goliathBossPattern == null) {
            goliathBossPattern = BlockPatternBuilder.start()
                    .aisle("^", "#", "#")
                    .where('#', pos -> pos.getState().is(ModBlocks.FLESH_BLOCK))
                    .where(
                            '^',
                            BlockInWorld.hasState(
                                    BlockStatePredicate.forBlock(Blocks.ZOMBIE_HEAD).or(BlockStatePredicate.forBlock(Blocks.ZOMBIE_WALL_HEAD))
                            )
                    )
                    .build();
        }

        return goliathBossPattern;
    }

    /**
     * Checks for summonable Goliath feature if dispensed.
     */
    @Unique
    private static BlockPattern getGoliathDispenserPattern() {
        if (goliathDispenserPattern == null) {
            goliathDispenserPattern = BlockPatternBuilder.start()
                    .aisle(" ", "#", "#")
                    .where('#', pos -> pos.getState().is(ModBlocks.FLESH_BLOCK))
                    .build();
        }

        return goliathDispenserPattern;
    }
}