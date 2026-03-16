package net.dillon.speedrunnermod.item;

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

    public static Tool createSpeedrunnerShears() {
        HolderGetter<Block> registryEntryLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return new Tool(
                List.of(
                        Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 17.0F),
                        Tool.Rule.overrideSpeed(registryEntryLookup.getOrThrow(BlockTags.LEAVES), 17.0F),
                        Tool.Rule.overrideSpeed(registryEntryLookup.getOrThrow(BlockTags.WOOL), 7.5F),
                        Tool.Rule.overrideSpeed(HolderSet.direct(Blocks.VINE.builtInRegistryHolder(), Blocks.GLOW_LICHEN.builtInRegistryHolder()), 2.0F)
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