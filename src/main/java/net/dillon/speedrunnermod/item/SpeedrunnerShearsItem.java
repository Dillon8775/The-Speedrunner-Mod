package net.dillon.speedrunnermod.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.DispenserBlock;
import net.minecraft.block.dispenser.ShearsDispenserBehavior;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.ToolComponent;
import net.minecraft.item.ShearsItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.BlockTags;

import java.util.List;

/**
 * Shears which can mine certain blocks {@code faster} and have {@code more durability.}
 */
public class SpeedrunnerShearsItem extends ShearsItem {

    public static ToolComponent createSpeedrunnerShears() {
        RegistryEntryLookup<Block> registryEntryLookup = Registries.createEntryLookup(Registries.BLOCK);
        return new ToolComponent(
                List.of(
                        ToolComponent.Rule.ofAlwaysDropping(RegistryEntryList.of(Blocks.COBWEB.getRegistryEntry()), 17.0F),
                        ToolComponent.Rule.of(registryEntryLookup.getOrThrow(BlockTags.LEAVES), 17.0F),
                        ToolComponent.Rule.of(registryEntryLookup.getOrThrow(BlockTags.WOOL), 7.5F),
                        ToolComponent.Rule.of(RegistryEntryList.of(Blocks.VINE.getRegistryEntry(), Blocks.GLOW_LICHEN.getRegistryEntry()), 2.0F)
                ),
                1.0F,
                1,
                true
        );
    }

    public SpeedrunnerShearsItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(476).component(DataComponentTypes.TOOL, createSpeedrunnerShears()));
        DispenserBlock.registerBehavior(this, new ShearsDispenserBehavior());
    }
}