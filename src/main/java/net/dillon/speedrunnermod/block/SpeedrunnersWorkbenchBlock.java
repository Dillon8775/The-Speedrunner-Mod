package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.screen.WorkbenchScreenHandler;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SmithingTableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

/**
 * A block that allows transferring of enchantments to other items.
 * <p>Also used as the retired speedrunner's job site block</p>
 */
public class SpeedrunnersWorkbenchBlock extends SmithingTableBlock {

    public SpeedrunnersWorkbenchBlock(Properties settings) {
        super(settings);
    }

    /**
     * Create the handled screen factory so the game knows what screen to open.
     */
    @Override
    protected MenuProvider getMenuProvider(BlockState state, Level world, BlockPos pos) {
        return new SimpleMenuProvider(
                (syncId, inventory, player) -> new WorkbenchScreenHandler(syncId, inventory, ContainerLevelAccess.create(world, pos)), Component.translatable("block.speedrunnermod.speedrunners_workbench")
        );
    }

    /**
     * The method to open the screen for the {@code Speedrunner's Workbench.}, doesn't open if {@link ModOptions.Mode} is {@link ModOptions.Mode#BALANCED}.
     */
    @Override
    public InteractionResult useWithoutItem(BlockState state, Level world, BlockPos pos, Player player, BlockHitResult hit) {
        if (!world.isClientSide()) {
            player.openMenu(state.getMenuProvider(world, pos));
        }

        return InteractionResult.SUCCESS;
    }
}