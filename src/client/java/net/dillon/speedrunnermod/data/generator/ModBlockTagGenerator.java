package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.tag.ModBlockTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Blocks;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

/**
 * Contains the entries of all new or already existing block tags.
 */
public class ModBlockTagGenerator extends FabricTagProvider.BlockTagProvider {

    public ModBlockTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ModBlockTags.DOOM_LOGS)
                .add(ModBlocks.DOOM_LOG)
                .add(ModBlocks.STRIPPED_DOOM_LOG);

        getOrCreateTagBuilder(ModBlockTags.EXPERIENCE_ORES)
                .add(ModBlocks.EXPERIENCE_ORE)
                .add(ModBlocks.DEEPSLATE_EXPERIENCE_ORE)
                .add(ModBlocks.NETHER_EXPERIENCE_ORE);

        getOrCreateTagBuilder(ModBlockTags.IGNEOUS_ORES)
                .add(ModBlocks.IGNEOUS_ORE)
                .add(ModBlocks.DEEPSLATE_IGNEOUS_ORE)
                .add(ModBlocks.NETHER_IGNEOUS_ORE);

        getOrCreateTagBuilder(ModBlockTags.IRON_BLOCKS)
                .add(Blocks.IRON_BLOCK)
                .add(ModBlocks.SPEEDRUNNER_BLOCK);

        getOrCreateTagBuilder(ModBlockTags.NETHER_PORTAL_BASE_BLOCKS)
                .add(Blocks.OBSIDIAN)
                .add(Blocks.CRYING_OBSIDIAN);

        getOrCreateTagBuilder(ModBlockTags.SMITHING_TABLES)
                .add(Blocks.SMITHING_TABLE)
                .add(ModBlocks.SPEEDRUNNERS_WORKBENCH);

        getOrCreateTagBuilder(ModBlockTags.SPEEDRUNNER_LOGS)
                .add(ModBlocks.SPEEDRUNNER_LOG)
                .add(ModBlocks.STRIPPED_SPEEDRUNNER_LOG)
                .add(ModBlocks.SPEEDRUNNER_WOOD)
                .add(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD);

        getOrCreateTagBuilder(ModBlockTags.DEAD_SPEEDRUNNER_LOGS)
                .add(ModBlocks.DEAD_SPEEDRUNNER_LOG)
                .add(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG)
                .add(ModBlocks.DEAD_SPEEDRUNNER_WOOD)
                .add(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD);

        getOrCreateTagBuilder(ModBlockTags.SPEEDRUNNER_PLANKS)
                .add(ModBlocks.SPEEDRUNNER_PLANKS)
                .add(ModBlocks.DEAD_SPEEDRUNNER_PLANKS);

        getOrCreateTagBuilder(ModBlockTags.SPEEDRUNNER_FUELS)
                .forceAddTag(ModBlockTags.SPEEDRUNNER_LOGS)
                .add(ModBlocks.SPEEDRUNNER_SAPLING)
                .add(ModBlocks.SPEEDRUNNER_SLAB)
                .add(ModBlocks.SPEEDRUNNER_STAIRS)
                .add(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR)
                .add(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE)
                .add(ModBlocks.SPEEDRUNNER_FENCE)
                .add(ModBlocks.SPEEDRUNNER_FENCE_GATE)
                .add(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON)
                .add(ModBlocks.DEAD_SPEEDRUNNER_BUSH);

        getOrCreateTagBuilder(ModBlockTags.SPEEDRUNNER_ORES)
                .add(ModBlocks.SPEEDRUNNER_ORE)
                .add(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE)
                .add(ModBlocks.NETHER_SPEEDRUNNER_ORE);

        getOrCreateTagBuilder(ModBlockTags.SPEEDRUNNER_SAPLING_PLACEABLES)
                .forceAddTag(BlockTags.SAND)
                .forceAddTag(BlockTags.SOUL_SPEED_BLOCKS)
                .add(Blocks.NETHERRACK)
                .add(Blocks.CRIMSON_NYLIUM)
                .add(Blocks.WARPED_NYLIUM);

        getOrCreateTagBuilder(ModBlockTags.SPEEDRUNNER_SIGNS)
                .add(ModBlocks.SPEEDRUNNER_SIGN)
                .add(ModBlocks.SPEEDRUNNER_WALL_SIGN)
                .add(ModBlocks.SPEEDRUNNER_HANGING_SIGN)
                .add(ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN)
                .add(ModBlocks.DEAD_SPEEDRUNNER_SIGN)
                .add(ModBlocks.DEAD_SPEEDRUNNER_WALL_SIGN)
                .add(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN)
                .add(ModBlocks.DEAD_SPEEDRUNNER_HANGING_WALL_SIGN);

        getOrCreateTagBuilder(BlockTags.AXE_MINEABLE)
                .forceAddTag(ModBlockTags.SPEEDRUNNER_LOGS)
                .forceAddTag(ModBlockTags.DEAD_SPEEDRUNNER_LOGS)
                .forceAddTag(ModBlockTags.DOOM_LOGS)
                .forceAddTag(ModBlockTags.SPEEDRUNNER_SIGNS)
                .add(ModBlocks.SPEEDRUNNER_PLANKS)
                .add(ModBlocks.DEAD_SPEEDRUNNER_PLANKS)
                .add(ModBlocks.SPEEDRUNNER_SLAB)
                .add(ModBlocks.DEAD_SPEEDRUNNER_SLAB)
                .add(ModBlocks.SPEEDRUNNER_STAIRS)
                .add(ModBlocks.DEAD_SPEEDRUNNER_STAIRS)
                .add(ModBlocks.SPEEDRUNNER_FENCE)
                .add(ModBlocks.DEAD_SPEEDRUNNER_FENCE)
                .add(ModBlocks.SPEEDRUNNER_FENCE_GATE)
                .add(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE)
                .add(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR)
                .add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR)
                .add(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON)
                .add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON)
                .add(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE)
                .add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE)
                .add(ModBlocks.WOODEN_SPEEDRUNNER_DOOR)
                .add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR)
                .add(ModBlocks.SPEEDRUNNERS_WORKBENCH);

        getOrCreateTagBuilder(BlockTags.HOE_MINEABLE)
                .add(ModBlocks.SPEEDRUNNER_LEAVES)
                .add(ModBlocks.DEAD_SPEEDRUNNER_LEAVES)
                .add(ModBlocks.DOOM_LEAVES);

        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .forceAddTag(ModBlockTags.EXPERIENCE_ORES)
                .forceAddTag(ModBlockTags.IGNEOUS_ORES)
                .forceAddTag(ModBlockTags.SPEEDRUNNER_ORES)
                .add(ModBlocks.SPEEDRUNNER_BLOCK)
                .add(ModBlocks.RAW_SPEEDRUNNER_BLOCK)
                .add(ModBlocks.SPEEDRUNNER_DOOR)
                .add(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE)
                .add(ModBlocks.DOOM_STONE);

        getOrCreateTagBuilder(BlockTags.SHOVEL_MINEABLE)
                .add(ModBlocks.THRUSTED_BLOCK);

        getOrCreateTagBuilder(BlockTags.BEACON_BASE_BLOCKS)
                .add(ModBlocks.SPEEDRUNNER_BLOCK);

        getOrCreateTagBuilder(BlockTags.DOORS)
                .add(ModBlocks.SPEEDRUNNER_DOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_DOORS)
                .add(ModBlocks.WOODEN_SPEEDRUNNER_DOOR)
                .add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR);

        getOrCreateTagBuilder(BlockTags.DRAGON_IMMUNE)
                .add(ModBlocks.DOOM_STONE);

        getOrCreateTagBuilder(BlockTags.FENCE_GATES)
                .add(ModBlocks.SPEEDRUNNER_FENCE_GATE)
                .add(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_FENCES)
                .add(ModBlocks.SPEEDRUNNER_FENCE)
                .add(ModBlocks.DEAD_SPEEDRUNNER_FENCE);

        getOrCreateTagBuilder(BlockTags.LEAVES)
                .add(ModBlocks.SPEEDRUNNER_LEAVES)
                .add(ModBlocks.DEAD_SPEEDRUNNER_LEAVES)
                .add(ModBlocks.DOOM_LEAVES);

        getOrCreateTagBuilder(BlockTags.LOGS)
                .forceAddTag(ModBlockTags.SPEEDRUNNER_LOGS)
                .forceAddTag(ModBlockTags.DEAD_SPEEDRUNNER_LOGS)
                .forceAddTag(ModBlockTags.DOOM_LOGS);

        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.IGNEOUS_ORE)
                .add(ModBlocks.DEEPSLATE_IGNEOUS_ORE)
                .add(ModBlocks.NETHER_IGNEOUS_ORE);

        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.SPEEDRUNNER_BLOCK)
                .add(ModBlocks.RAW_SPEEDRUNNER_BLOCK)
                .add(ModBlocks.SPEEDRUNNER_ORE)
                .add(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE)
                .add(ModBlocks.EXPERIENCE_ORE)
                .add(ModBlocks.DEEPSLATE_EXPERIENCE_ORE);

        getOrCreateTagBuilder(BlockTags.PLANKS)
                .add(ModBlocks.SPEEDRUNNER_PLANKS)
                .add(ModBlocks.DEAD_SPEEDRUNNER_PLANKS);

        getOrCreateTagBuilder(BlockTags.SAPLINGS)
                .add(ModBlocks.SPEEDRUNNER_SAPLING)
                .add(ModBlocks.DEAD_SPEEDRUNNER_SAPLING);

        getOrCreateTagBuilder(BlockTags.STANDING_SIGNS)
                .add(ModBlocks.SPEEDRUNNER_SIGN)
                .add(ModBlocks.DEAD_SPEEDRUNNER_SIGN);

        getOrCreateTagBuilder(BlockTags.WALL_SIGNS)
                .add(ModBlocks.SPEEDRUNNER_WALL_SIGN)
                .add(ModBlocks.DEAD_SPEEDRUNNER_WALL_SIGN);

        getOrCreateTagBuilder(BlockTags.CEILING_HANGING_SIGNS)
                .add(ModBlocks.SPEEDRUNNER_HANGING_SIGN)
                .add(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN);

        getOrCreateTagBuilder(BlockTags.WALL_HANGING_SIGNS)
                .add(ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN)
                .add(ModBlocks.DEAD_SPEEDRUNNER_HANGING_WALL_SIGN);

        getOrCreateTagBuilder(BlockTags.TRAPDOORS)
                .add(ModBlocks.SPEEDRUNNER_TRAPDOOR);

        getOrCreateTagBuilder(BlockTags.WOODEN_BUTTONS)
                .add(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON)
                .add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON);

        getOrCreateTagBuilder(BlockTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE)
                        .add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);

        getOrCreateTagBuilder(BlockTags.WOODEN_SLABS)
                .add(ModBlocks.SPEEDRUNNER_SLAB)
                .add(ModBlocks.DEAD_SPEEDRUNNER_SLAB);

        getOrCreateTagBuilder(BlockTags.WOODEN_STAIRS)
                .add(ModBlocks.SPEEDRUNNER_STAIRS)
                .add(ModBlocks.DEAD_SPEEDRUNNER_STAIRS);

        getOrCreateTagBuilder(BlockTags.WOODEN_TRAPDOORS)
                .add(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR)
                .add(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
    }
}