package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.screen.WorkbenchScreenHandler;
import net.dillon.speedrunnermod.util.TutorialMode;
import net.minecraft.block.BlockState;
import net.minecraft.block.SmithingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A block that allows transferring of enchantments to other items.
 * <p>Also used as the retired speedrunner's job site block</p>
 */
public class SpeedrunnersWorkbenchBlock extends SmithingTableBlock implements TutorialMode {

    public SpeedrunnersWorkbenchBlock(Settings settings) {
        super(settings);
    }

    /**
     * Create the handled screen factory so the game knows what screen to open.
     */
    @Override
    protected NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory(
                (syncId, inventory, player) -> new WorkbenchScreenHandler(syncId, inventory, ScreenHandlerContext.create(world, pos)), Text.translatable("block.speedrunnermod.speedrunners_workbench")
        );
    }

    /**
     * The method to open the screen for the {@code Speedrunner's Workbench.}, doesn't open if {@link ModOptions.PlayingMode} is {@link ModOptions.PlayingMode#NORMAL}.
     */
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
        if (!world.isClient) {
            if (!options().main.playingMode.normal()) {
                player.openHandledScreen(state.createScreenHandlerFactory(world, pos));
            } else {
                player.sendMessage(Text.translatable("block.speedrunnermod.speedrunners_workbench.disabled"), false);
            }
        }

        return ActionResult.SUCCESS;
    }
}