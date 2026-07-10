package net.dillon.speedrunnermod.item.tool;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.dispenser.ShearsDispenseItemBehavior;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DispenserBlock;

import java.util.List;

/**
 * Shears which can mine certain blocks {@code faster} and have {@code more durability.}
 */
public class SpeedrunnerShearsItem extends ShearsItem {

    /**
     * @return the speedrunner shears breaking tools.
     */
    private static Tool createSpeedrunnerShears() {
        HolderGetter<Block> registrationLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return new Tool(
                List.of(
                        Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 17.0F),
                        Tool.Rule.overrideSpeed(registrationLookup.getOrThrow(BlockTags.SHEARS_EXTREME_BREAKING_SPEED), 17.0F),
                        Tool.Rule.overrideSpeed(registrationLookup.getOrThrow(BlockTags.SHEARS_MAJOR_BREAKING_SPEED), 7.5F),
                        Tool.Rule.overrideSpeed(registrationLookup.getOrThrow(BlockTags.SHEARS_MINOR_BREAKING_SPEED), 2.0F)
                ),
                1.0F,
                1,
                true
        );
    }

    public SpeedrunnerShearsItem(Properties settings) {
        super(settings.stacksTo(1).durability(476).component(DataComponents.TOOL, createSpeedrunnerShears()));
        DispenserBlock.registerBehavior(this, new ShearsDispenseItemBehavior());
    }
}