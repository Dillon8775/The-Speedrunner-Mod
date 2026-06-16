package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.item.ModBlockItemIds;
import net.dillon.speedrunnermod.tag.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

/**
 * Contains the entries of all new or already existing block tags.
 */
public class ModBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

    public ModBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        tag(ModBlockTags.DOOM_LOGS)
                .add(ModBlockItemIds.DOOM_LOG.block());

        tag(ModBlockTags.EXPERIENCE_ORES)
                .add(ModBlockItemIds.EXPERIENCE_ORE.block())
                .add(ModBlockItemIds.DEEPSLATE_EXPERIENCE_ORE.block())
                .add(ModBlockItemIds.NETHER_EXPERIENCE_ORE.block());

        tag(ModBlockTags.IGNEOUS_ORES)
                .add(ModBlockItemIds.IGNEOUS_ORE.block())
                .add(ModBlockItemIds.DEEPSLATE_IGNEOUS_ORE.block())
                .add(ModBlockItemIds.NETHER_IGNEOUS_ORE.block());

        tag(ModBlockTags.IRON_BLOCKS)
                .add(BlockItemIds.IRON_BLOCK.block())
                .add(ModBlockItemIds.SPEEDRUNNER_BLOCK.block());

        tag(ModBlockTags.NETHER_PORTAL_BASE_BLOCKS)
                .add(BlockItemIds.OBSIDIAN.block())
                .add(BlockItemIds.CRYING_OBSIDIAN.block());

        tag(ModBlockTags.SPEEDRUNNER_LOGS)
                .add(ModBlockItemIds.SPEEDRUNNER_LOG.block())
                .add(ModBlockItemIds.STRIPPED_SPEEDRUNNER_LOG.block())
                .add(ModBlockItemIds.SPEEDRUNNER_WOOD.block())
                .add(ModBlockItemIds.STRIPPED_SPEEDRUNNER_WOOD.block());

        tag(ModBlockTags.DEAD_SPEEDRUNNER_LOGS)
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_LOG.block())
                .add(ModBlockItemIds.DEAD_STRIPPED_SPEEDRUNNER_LOG.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_WOOD.block())
                .add(ModBlockItemIds.DEAD_STRIPPED_SPEEDRUNNER_WOOD.block());

        tag(ModBlockTags.SPEEDRUNNER_PLANKS)
                .add(ModBlockItemIds.SPEEDRUNNER_PLANKS.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_PLANKS.block());

        tag(ModBlockTags.SPEEDRUNNER_FUELS)
                .addOptionalTag(ModBlockTags.SPEEDRUNNER_LOGS)
                .add(ModBlockItemIds.SPEEDRUNNER_SAPLING.block())
                .add(ModBlockItemIds.SPEEDRUNNER_SLAB.block())
                .add(ModBlockItemIds.SPEEDRUNNER_STAIRS.block())
                .add(ModBlockItemIds.SPEEDRUNNER_TRAPDOOR.block())
                .add(ModBlockItemIds.SPEEDRUNNER_PRESSURE_PLATE.block())
                .add(ModBlockItemIds.SPEEDRUNNER_FENCE.block())
                .add(ModBlockItemIds.SPEEDRUNNER_FENCE_GATE.block())
                .add(ModBlockItemIds.SPEEDRUNNER_BUTTON.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_BUSH.block());

        tag(ModBlockTags.SPEEDRUNNER_ORES)
                .add(ModBlockItemIds.SPEEDRUNNER_ORE.block())
                .add(ModBlockItemIds.DEEPSLATE_SPEEDRUNNER_ORE.block())
                .add(ModBlockItemIds.NETHER_SPEEDRUNNER_ORE.block());

        tag(ModBlockTags.SPEEDRUNNER_SAPLING_PLACEABLES)
                .addOptionalTag(BlockTags.SAND)
                .addOptionalTag(BlockTags.SOUL_SPEED_BLOCKS)
                .add(BlockItemIds.NETHERRACK.block())
                .add(BlockItemIds.CRIMSON_NYLIUM.block())
                .add(BlockItemIds.WARPED_NYLIUM.block());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .addOptionalTag(ModBlockTags.SPEEDRUNNER_LOGS)
                .addOptionalTag(ModBlockTags.DEAD_SPEEDRUNNER_LOGS)
                .addOptionalTag(ModBlockTags.DOOM_LOGS)
                .add(ModBlockItemIds.SPEEDRUNNER_PLANKS.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_PLANKS.block())
                .add(ModBlockItemIds.SPEEDRUNNER_SLAB.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_SLAB.block())
                .add(ModBlockItemIds.SPEEDRUNNER_STAIRS.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_STAIRS.block())
                .add(ModBlockItemIds.SPEEDRUNNER_FENCE.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_FENCE.block())
                .add(ModBlockItemIds.SPEEDRUNNER_FENCE_GATE.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_FENCE_GATE.block())
                .add(ModBlockItemIds.SPEEDRUNNER_TRAPDOOR.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_TRAPDOOR.block())
                .add(ModBlockItemIds.SPEEDRUNNER_BUTTON.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_BUTTON.block())
                .add(ModBlockItemIds.SPEEDRUNNER_PRESSURE_PLATE.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_PRESSURE_PLATE.block())
                .add(ModBlockItemIds.SPEEDRUNNER_DOOR.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_DOOR.block())
                .add(ModBlockItemIds.SPEEDRUNNERS_WORKBENCH.block());

        tag(BlockTags.MINEABLE_WITH_HOE)
                .add(ModBlockItemIds.SPEEDRUNNER_LEAVES.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_LEAVES.block())
                .add(ModBlockItemIds.DOOM_LEAVES.block());

        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .addOptionalTag(ModBlockTags.EXPERIENCE_ORES)
                .addOptionalTag(ModBlockTags.IGNEOUS_ORES)
                .addOptionalTag(ModBlockTags.SPEEDRUNNER_ORES)
                .add(ModBlockItemIds.SPEEDRUNNER_BLOCK.block())
                .add(ModBlockItemIds.RAW_SPEEDRUNNER_BLOCK.block())
                .add(ModBlockItemIds.METAL_SPEEDRUNNER_DOOR.block())
                .add(ModBlockItemIds.MEDIATE_WEIGHTED_SPEEDRUNNER_PRESSURE_PLATE.block())
                .add(ModBlockItemIds.DOOM_STONE.block());

        tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlockItemIds.THRUSTED_BLOCK.block());

        tag(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlockItemIds.SPEEDRUNNER_BLOCK.block());

        tag(BlockTags.DOORS)
                .add(ModBlockItemIds.METAL_SPEEDRUNNER_DOOR.block());

        tag(BlockTags.WOODEN_DOORS)
                .add(ModBlockItemIds.SPEEDRUNNER_DOOR.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_DOOR.block());

        tag(BlockTags.DRAGON_IMMUNE)
                .add(ModBlockItemIds.DOOM_STONE.block());

        tag(BlockTags.FENCE_GATES)
                .add(ModBlockItemIds.SPEEDRUNNER_FENCE_GATE.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_FENCE_GATE.block());

        tag(BlockTags.WOODEN_FENCES)
                .add(ModBlockItemIds.SPEEDRUNNER_FENCE.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_FENCE.block());

        tag(BlockTags.LEAVES)
                .add(ModBlockItemIds.SPEEDRUNNER_LEAVES.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_LEAVES.block())
                .add(ModBlockItemIds.DOOM_LEAVES.block());

        tag(BlockTags.LOGS)
                .addOptionalTag(ModBlockTags.SPEEDRUNNER_LOGS)
                .addOptionalTag(ModBlockTags.DEAD_SPEEDRUNNER_LOGS)
                .addOptionalTag(ModBlockTags.DOOM_LOGS);

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlockItemIds.IGNEOUS_ORE.block())
                .add(ModBlockItemIds.DEEPSLATE_IGNEOUS_ORE.block())
                .add(ModBlockItemIds.NETHER_IGNEOUS_ORE.block());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlockItemIds.SPEEDRUNNER_BLOCK.block())
                .add(ModBlockItemIds.RAW_SPEEDRUNNER_BLOCK.block())
                .add(ModBlockItemIds.SPEEDRUNNER_ORE.block())
                .add(ModBlockItemIds.DEEPSLATE_SPEEDRUNNER_ORE.block())
                .add(ModBlockItemIds.EXPERIENCE_ORE.block())
                .add(ModBlockItemIds.DEEPSLATE_EXPERIENCE_ORE.block());

        tag(BlockTags.PLANKS)
                .add(ModBlockItemIds.SPEEDRUNNER_PLANKS.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_PLANKS.block());

        tag(BlockTags.TRAPDOORS)
                .add(ModBlockItemIds.METAL_SPEEDRUNNER_TRAPDOOR.block());

        tag(BlockTags.WOODEN_BUTTONS)
                .add(ModBlockItemIds.SPEEDRUNNER_BUTTON.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_BUTTON.block());

        tag(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlockItemIds.SPEEDRUNNER_PRESSURE_PLATE.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_PRESSURE_PLATE.block());

        tag(BlockTags.WOODEN_SLABS)
                .add(ModBlockItemIds.SPEEDRUNNER_SLAB.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_SLAB.block());

        tag(BlockTags.WOODEN_STAIRS)
                .add(ModBlockItemIds.SPEEDRUNNER_STAIRS.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_STAIRS.block());

        tag(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlockItemIds.SPEEDRUNNER_TRAPDOOR.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_TRAPDOOR.block());
    }
}