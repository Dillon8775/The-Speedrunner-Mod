package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.item.core.ModBlockItemIds;
import net.dillon.speedrunnermod.tag.ModBlockHardnessTags;
import net.dillon.speedrunnermod.tag.ModBlockItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockIds;
import net.minecraft.references.BlockItemIds;
import net.minecraft.tags.BlockItemTags;
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
        tag(ModBlockItemTags.DOOM_LOGS.block())
                .add(ModBlockItemIds.DOOM_LOG.block());

        tag(ModBlockItemTags.EXPERIENCE_ORES.block())
                .add(ModBlockItemIds.EXPERIENCE_ORE.block())
                .add(ModBlockItemIds.DEEPSLATE_EXPERIENCE_ORE.block())
                .add(ModBlockItemIds.NETHER_EXPERIENCE_ORE.block());

        tag(ModBlockItemTags.IGNEOUS_ORES.block())
                .add(ModBlockItemIds.IGNEOUS_ORE.block())
                .add(ModBlockItemIds.DEEPSLATE_IGNEOUS_ORE.block())
                .add(ModBlockItemIds.NETHER_IGNEOUS_ORE.block());

        tag(ModBlockItemTags.IRON_BLOCKS.block())
                .add(BlockItemIds.IRON_BLOCK.block())
                .add(ModBlockItemIds.SPEEDRUNNER_BLOCK.block());

        tag(ModBlockItemTags.SPEEDRUNNER_LOGS.block())
                .add(ModBlockItemIds.SPEEDRUNNER_LOG.block())
                .add(ModBlockItemIds.SPEEDRUNNER_WOOD.block());

        tag(ModBlockItemTags.DEAD_SPEEDRUNNER_LOGS.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_LOG.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_WOOD.block());

        tag(ModBlockItemTags.SPEEDRUNNER_PLANKS.block())
                .add(ModBlockItemIds.SPEEDRUNNER_PLANKS.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_PLANKS.block());

        tag(ModBlockItemTags.SPEEDRUNNER_FUELS.block())
                .addOptionalTag(ModBlockItemTags.SPEEDRUNNER_LOGS.block())
                .add(ModBlockItemIds.SPEEDRUNNER_SAPLING.block())
                .add(ModBlockItemIds.SPEEDRUNNER_SLAB.block())
                .add(ModBlockItemIds.SPEEDRUNNER_STAIRS.block())
                .add(ModBlockItemIds.SPEEDRUNNER_TRAPDOOR.block())
                .add(ModBlockItemIds.SPEEDRUNNER_PRESSURE_PLATE.block())
                .add(ModBlockItemIds.SPEEDRUNNER_FENCE.block())
                .add(ModBlockItemIds.SPEEDRUNNER_FENCE_GATE.block())
                .add(ModBlockItemIds.SPEEDRUNNER_BUTTON.block())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_BUSH.block());

        tag(ModBlockItemTags.SPEEDRUNNER_ORES.block())
                .add(ModBlockItemIds.SPEEDRUNNER_ORE.block())
                .add(ModBlockItemIds.DEEPSLATE_SPEEDRUNNER_ORE.block())
                .add(ModBlockItemIds.NETHER_SPEEDRUNNER_ORE.block());

        tag(ModBlockItemTags.SPEEDRUNNER_SAPLING_PLACEABLES.block())
                .addOptionalTag(BlockTags.SAND)
                .addOptionalTag(BlockTags.SOUL_SPEED_BLOCKS)
                .add(BlockItemIds.NETHERRACK.block())
                .add(BlockItemIds.CRIMSON_NYLIUM.block())
                .add(BlockItemIds.WARPED_NYLIUM.block());

        tag(ModBlockHardnessTags.INSTABREAK)
                .addOptionalTag(BlockItemTags.BEDS.block());

        tag(ModBlockHardnessTags.HARDNESS_0_1)
                .addOptionalTag(BlockItemTags.LEAVES.block())
                .add(BlockItemIds.SNOW_BLOCK.block())
                .add(BlockItemIds.VINE.block());

        tag(ModBlockHardnessTags.HARDNESS_0_2)
                .add(BlockItemIds.SEA_LANTERN.block())
                .add(BlockItemIds.GLOWSTONE.block())
                .add(BlockItemIds.HAY_BLOCK.block());

        tag(ModBlockHardnessTags.HARDNESS_0_3)
                .addOptionalTag(BlockItemTags.WOODEN_BUTTONS.block());

        tag(ModBlockHardnessTags.HARDNESS_0_35);

        tag(ModBlockHardnessTags.HARDNESS_0_37)
                .add(BlockItemIds.NETHERRACK.block())
                .add(BlockItemIds.WARPED_NYLIUM.block())
                .add(BlockItemIds.CRIMSON_NYLIUM.block());

        tag(ModBlockHardnessTags.HARDNESS_0_4)
                .add(BlockItemIds.DIRT.block())
                .add(BlockItemIds.ROOTED_DIRT.block())
                .add(BlockItemIds.COARSE_DIRT.block())
                .add(BlockItemIds.MUD.block())
                .add(BlockItemIds.PODZOL.block())
                .add(BlockItemIds.SAND.block())
                .add(BlockItemIds.RED_SAND.block())
                .add(BlockItemIds.ICE.block())
                .add(BlockItemIds.SOUL_SAND.block())
                .add(BlockItemIds.SOUL_SOIL.block())
                .add(BlockItemIds.PACKED_ICE.block())
                .add(BlockIds.FROSTED_ICE)
                .add(BlockItemIds.MAGMA_BLOCK.block())
                .add(BlockItemIds.DRIED_KELP_BLOCK.block());

        tag(ModBlockHardnessTags.HARDNESS_0_5)
                .add(BlockItemIds.GRASS_BLOCK.block())
                .add(BlockItemIds.MYCELIUM.block())
                .add(BlockItemIds.GRAVEL.block())
                .add(BlockItemIds.CLAY.block())
                .add(BlockItemIds.DIRT_PATH.block());

        tag(ModBlockHardnessTags.HARDNESS_0_6)
                .add(BlockItemIds.NETHER_WART_BLOCK.block())
                .add(BlockItemIds.WARPED_WART_BLOCK.block())
                .add(BlockItemIds.SHROOMLIGHT.block());

        tag(ModBlockHardnessTags.HARDNESS_0_65)
                .addOptionalTag(BlockItemTags.WOOL.block());

        tag(ModBlockHardnessTags.HARDNESS_0_7)
                .add(BlockItemIds.SANDSTONE.block())
                .add(BlockItemIds.CHISELED_SANDSTONE.block())
                .add(BlockItemIds.CUT_SANDSTONE.block())
                .add(BlockItemIds.SANDSTONE_STAIRS.block())
                .add(BlockItemIds.QUARTZ_BLOCK.block())
                .add(BlockItemIds.CHISELED_QUARTZ_BLOCK.block())
                .add(BlockItemIds.QUARTZ_PILLAR.block())
                .add(BlockItemIds.QUARTZ_STAIRS.block())
                .add(BlockItemIds.RED_SANDSTONE.block())
                .add(BlockItemIds.CHISELED_RED_SANDSTONE.block())
                .add(BlockItemIds.CUT_RED_SANDSTONE.block())
                .add(BlockItemIds.RED_SANDSTONE_STAIRS.block())
                .add(BlockItemIds.SANDSTONE_SLAB.block())
                .add(BlockItemIds.CUT_SANDSTONE_SLAB.block())
                .add(BlockItemIds.QUARTZ_SLAB.block())
                .add(BlockItemIds.RED_SANDSTONE_SLAB.block())
                .add(BlockItemIds.CUT_RED_SANDSTONE_SLAB.block())
                .add(BlockItemIds.RED_SANDSTONE_WALL.block())
                .add(BlockItemIds.SANDSTONE_WALL.block())
                .add(BlockItemIds.QUARTZ_BRICKS.block());

        tag(ModBlockHardnessTags.HARDNESS_0_8)
                .add(BlockItemIds.PUMPKIN.block())
                .add(BlockItemIds.CARVED_PUMPKIN.block())
                .add(BlockItemIds.JACK_O_LANTERN.block())
                .add(BlockItemIds.MELON.block())
                .add(BlockItemIds.SMOOTH_SANDSTONE.block())
                .add(BlockItemIds.SMOOTH_QUARTZ.block())
                .add(BlockItemIds.SMOOTH_RED_SANDSTONE.block())
                .add(BlockItemIds.SMOOTH_RED_SANDSTONE_STAIRS.block())
                .add(BlockItemIds.SMOOTH_SANDSTONE_STAIRS.block())
                .add(BlockItemIds.SMOOTH_QUARTZ_STAIRS.block())
                .add(BlockItemIds.SMOOTH_RED_SANDSTONE_SLAB.block())
                .add(BlockItemIds.SMOOTH_QUARTZ_SLAB.block());

        tag(ModBlockHardnessTags.HARDNESS_1_0)
                .add(BlockItemIds.BASALT.block())
                .add(BlockItemIds.POLISHED_BASALT.block())
                .add(BlockItemIds.SMOOTH_BASALT.block());

        tag(ModBlockHardnessTags.HARDNESS_1_3)
                .addOptionalTag(BlockItemTags.LOGS.block())
                .addOptionalTag(BlockItemTags.PLANKS.block())
                .addOptionalTag(BlockItemTags.SLABS.block())
                .addOptionalTag(BlockItemTags.STAIRS.block())
                .addOptionalTag(BlockItemTags.WALLS.block())
                .addOptionalTag(BlockItemTags.FENCES.block())
                .addOptionalTag(BlockItemTags.FENCE_GATES.block())
                .addOptionalTag(BlockItemTags.STONE_BRICKS.block())
                .add(BlockItemIds.BOOKSHELF.block())
                .add(BlockItemIds.JUKEBOX.block())
                .add(BlockItemIds.BRICKS.block())
                .add(BlockItemIds.PRISMARINE.block())
                .add(BlockItemIds.PRISMARINE_BRICKS.block())
                .add(BlockItemIds.DARK_PRISMARINE.block())
                .add(BlockItemIds.STONE.block())
                .add(BlockItemIds.GRANITE.block())
                .add(BlockItemIds.POLISHED_GRANITE.block())
                .add(BlockItemIds.DIORITE.block())
                .add(BlockItemIds.POLISHED_DIORITE.block())
                .add(BlockItemIds.ANDESITE.block())
                .add(BlockItemIds.POLISHED_ANDESITE.block())
                .add(BlockItemIds.TUFF.block());

        tag(ModBlockHardnessTags.HARDNESS_1_4)
                .add(BlockItemIds.COBBLESTONE.block())
                .add(BlockItemIds.MOSSY_COBBLESTONE.block())
                .add(BlockItemIds.SMOOTH_STONE.block())
                .add(BlockItemIds.BLUE_ICE.block())
                .add(BlockItemIds.BLACKSTONE.block())
                .add(BlockItemIds.POLISHED_BLACKSTONE.block())
                .add(BlockItemIds.POLISHED_BLACKSTONE_BRICKS.block())
                .add(BlockItemIds.CHISELED_POLISHED_BLACKSTONE.block())
                .add(BlockItemIds.CRACKED_POLISHED_BLACKSTONE_BRICKS.block())
                .add(BlockItemIds.GILDED_BLACKSTONE.block());

        tag(ModBlockHardnessTags.HARDNESS_1_5)
                .addOptionalTag(BlockItemTags.WOODEN_TRAPDOORS.block())
                .addOptionalTag(BlockItemTags.WOODEN_DOORS.block())
                .add(BlockItemIds.CHEST.block())
                .add(BlockItemIds.TRAPPED_CHEST.block())
                .add(BlockItemIds.CRAFTING_TABLE.block())
                .add(BlockItemIds.NETHER_BRICKS.block())
                .add(BlockItemIds.RED_NETHER_BRICKS.block())
                .add(BlockItemIds.CHISELED_NETHER_BRICKS.block())
                .add(BlockItemIds.CRACKED_NETHER_BRICKS.block())
                .add(BlockItemIds.END_STONE.block())
                .add(BlockItemIds.END_STONE_BRICKS.block())
                .add(BlockItemIds.BONE_BLOCK.block())
                .add(BlockItemIds.CAULDRON.block())
                .add(BlockItemIds.BEACON.block())
                .add(BlockItemIds.LOOM.block())
                .add(BlockItemIds.BARREL.block())
                .add(BlockItemIds.CARTOGRAPHY_TABLE.block())
                .add(BlockItemIds.FLETCHING_TABLE.block())
                .add(BlockItemIds.SMITHING_TABLE.block())
                .add(BlockItemIds.GRINDSTONE.block())
                .add(BlockItemIds.LECTERN.block());

        tag(ModBlockHardnessTags.HARDNESS_1_6);

        tag(ModBlockHardnessTags.HARDNESS_2_0)
                .addOptionalTag(BlockItemTags.LANTERNS.block())
                .add(BlockItemIds.FURNACE.block())
                .add(BlockItemIds.BLAST_FURNACE.block())
                .add(BlockItemIds.SMOKER.block())
                .add(BlockItemIds.DROPPER.block())
                .add(BlockItemIds.DISPENSER.block())
                .add(BlockItemIds.COBWEB.block());

        tag(ModBlockHardnessTags.HARDNESS_2_5)
                .add(BlockItemIds.DEEPSLATE.block())
                .add(BlockItemIds.DEEPSLATE_BRICKS.block())
                .add(BlockItemIds.DEEPSLATE_BRICK_SLAB.block())
                .add(BlockItemIds.DEEPSLATE_BRICK_STAIRS.block())
                .add(BlockItemIds.DEEPSLATE_BRICK_WALL.block())
                .add(BlockItemIds.DEEPSLATE_TILES.block())
                .add(BlockItemIds.DEEPSLATE_TILE_SLAB.block())
                .add(BlockItemIds.DEEPSLATE_TILE_STAIRS.block())
                .add(BlockItemIds.DEEPSLATE_TILE_WALL.block())
                .add(BlockItemIds.COBBLED_DEEPSLATE.block())
                .add(BlockItemIds.COBBLED_DEEPSLATE_SLAB.block())
                .add(BlockItemIds.COBBLED_DEEPSLATE_STAIRS.block())
                .add(BlockItemIds.COBBLED_DEEPSLATE_WALL.block())
                .add(BlockItemIds.POLISHED_DEEPSLATE.block())
                .add(BlockItemIds.POLISHED_DEEPSLATE_SLAB.block())
                .add(BlockItemIds.POLISHED_DEEPSLATE_STAIRS.block())
                .add(BlockItemIds.POLISHED_DEEPSLATE_WALL.block())
                .add(BlockItemIds.CHISELED_DEEPSLATE.block())
                .add(BlockItemIds.CRACKED_DEEPSLATE_BRICKS.block())
                .add(BlockItemIds.CRACKED_DEEPSLATE_TILES.block())
                .add(BlockItemIds.GOLD_BLOCK.block())
                .add(BlockItemIds.LODESTONE.block());

        tag(ModBlockHardnessTags.HARDNESS_3_0)
                .addOptionalTag(BlockItemTags.ANVIL.block())
                .add(BlockItemIds.IRON_DOOR.block())
                .add(BlockItemIds.IRON_TRAPDOOR.block())
                .add(BlockItemIds.IRON_BARS.block())
                .add(BlockItemIds.IRON_CHAIN.block())
                .add(BlockItemIds.REDSTONE_BLOCK.block())
                .add(BlockItemIds.COAL_BLOCK.block())
                .add(BlockItemIds.ENCHANTING_TABLE.block())
                .add(BlockItemIds.BELL.block());

        tag(ModBlockHardnessTags.HARDNESS_4_0);

        tag(ModBlockHardnessTags.HARDNESS_4_5);

        tag(ModBlockHardnessTags.HARDNESS_5_0);

        tag(ModBlockHardnessTags.HARDNESS_6_0);

        tag(ModBlockHardnessTags.HARDNESS_7_0);

        tag(ModBlockHardnessTags.HARDNESS_8_0);

        tag(ModBlockHardnessTags.HARDNESS_9_0);

        tag(ModBlockHardnessTags.HARDNESS_10)
                .add(BlockItemIds.CRYING_OBSIDIAN.block());

        tag(ModBlockHardnessTags.HARDNESS_25)
                .add(BlockItemIds.OBSIDIAN.block());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .addOptionalTag(ModBlockItemTags.SPEEDRUNNER_LOGS.block())
                .addOptionalTag(ModBlockItemTags.DEAD_SPEEDRUNNER_LOGS.block())
                .addOptionalTag(ModBlockItemTags.DOOM_LOGS.block())
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
                .addOptionalTag(ModBlockItemTags.EXPERIENCE_ORES.block())
                .addOptionalTag(ModBlockItemTags.IGNEOUS_ORES.block())
                .addOptionalTag(ModBlockItemTags.SPEEDRUNNER_ORES.block())
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
                .addOptionalTag(ModBlockItemTags.SPEEDRUNNER_LOGS.block())
                .addOptionalTag(ModBlockItemTags.DEAD_SPEEDRUNNER_LOGS.block())
                .addOptionalTag(ModBlockItemTags.DOOM_LOGS.block());

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

        tag(BlockTags.NETHER_PORTAL_FRAME)
                .add(BlockItemIds.CRYING_OBSIDIAN.block());

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