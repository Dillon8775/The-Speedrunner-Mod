package net.dillon.speedrunnermod.mixin.main.block;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.block.SkullBlockInvoker;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.block.entity.SkullBlockEntity;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
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

    public SkullBlockMixin(SkullBlock.SkullType type, Settings settings) {
        super(type, settings);
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
        onPlaced(world, pos);
    }

    /**
     * Helper method for on placed.
     */
    @Unique
    private void onPlaced(World world, BlockPos pos) {
        if (world.getBlockEntity(pos) instanceof SkullBlockEntity skullBlockEntity) {
            onPlaced(world, pos, skullBlockEntity);
        }
    }

    /**
     * Handles the summoning of Goliath.
     * <p>See {@link WitherSkullBlock#onPlaced(World, BlockPos, SkullBlockEntity)} for more.</p>
     */
    @Unique
    public void onPlaced(World world, BlockPos pos, SkullBlockEntity blockEntity) {
        if (!world.isClient) {
            BlockState blockState = blockEntity.getCachedState();
            boolean bl = blockState.isOf(Blocks.ZOMBIE_HEAD) || blockState.isOf(Blocks.ZOMBIE_WALL_HEAD);
            if (bl && pos.getY() >= world.getBottomY() && world.getDifficulty() != Difficulty.PEACEFUL) {
                BlockPattern.Result result = getGoliathBossPattern().searchAround(world, pos);
                if (result != null) {
                    GiantEntity giant = EntityType.GIANT.create(world, SpawnReason.TRIGGERED);
                    if (giant != null) {
                        CarvedPumpkinBlock.breakPatternBlocks(world, result);
                        BlockPos blockPos = result.translate(1, 2, 0).getBlockPos();
                        giant.refreshPositionAndAngles(
                                (double)blockPos.getX() + 0.5,
                                (double)blockPos.getY() + 0.55,
                                (double)blockPos.getZ() + 0.5,
                                result.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F,
                                0.0F
                        );
                        giant.bodyYaw = result.getForwards().getAxis() == Direction.Axis.X ? 0.0F : 90.0F;

                        for (ServerPlayerEntity serverPlayerEntity : world.getNonSpectatingEntities(ServerPlayerEntity.class, giant.getBoundingBox().expand(50.0))) {
                            Criteria.SUMMONED_ENTITY.trigger(serverPlayerEntity, giant);
                        }

                        world.spawnEntity(giant);
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
    public boolean canDispense(World world, BlockPos pos, ItemStack stack) {
        return stack.isOf(Items.ZOMBIE_HEAD) && pos.getY() >= world.getBottomY() + 2 && world.getDifficulty() != Difficulty.PEACEFUL && !world.isClient && getGoliathDispenserPattern().searchAround(world, pos) != null;
    }

    /**
     * Checks for summonable Goliath feature.
     */
    @Unique
    private static BlockPattern getGoliathBossPattern() {
        if (goliathBossPattern == null) {
            goliathBossPattern = BlockPatternBuilder.start()
                    .aisle("^", "#", "#")
                    .where('#', pos -> pos.getBlockState().isOf(ModBlocks.FLESH_BLOCK))
                    .where(
                            '^',
                            CachedBlockPosition.matchesBlockState(
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
                    .where('#', pos -> pos.getBlockState().isOf(ModBlocks.FLESH_BLOCK))
                    .build();
        }

        return goliathDispenserPattern;
    }
}