package net.dillon.speedrunnermod.item;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod block item ids.
 */
public class ModBlockItemIds {
    public static final BlockItemId SPEEDRUNNER_LOG = create("speedrunner_log");
    public static final BlockItemId STRIPPED_SPEEDRUNNER_LOG = create("stripped_speedrunner_log");
    public static final BlockItemId SPEEDRUNNER_WOOD = create("speedrunner_wood");
    public static final BlockItemId STRIPPED_SPEEDRUNNER_WOOD = create("stripped_speedrunner_wood");
    public static final BlockItemId SPEEDRUNNER_LEAVES = create("speedrunner_leaves");
    public static final BlockItemId SPEEDRUNNER_SAPLING = create("speedrunner_sapling");
    public static final BlockItemId SPEEDRUNNER_PLANKS = create("speedrunner_planks");
    public static final BlockItemId SPEEDRUNNER_SLAB = create("speedrunner_slab");
    public static final BlockItemId SPEEDRUNNER_STAIRS = create("speedrunner_stairs");
    public static final BlockItemId SPEEDRUNNER_FENCE = create("speedrunner_fence");
    public static final BlockItemId SPEEDRUNNER_FENCE_GATE = create("speedrunner_fence_gate");
    public static final BlockItemId SPEEDRUNNER_TRAPDOOR = create("speedrunner_trapdoor");
    public static final BlockItemId SPEEDRUNNER_BUTTON = create("speedrunner_button");
    public static final BlockItemId SPEEDRUNNER_PRESSURE_PLATE = create("speedrunner_pressure_plate");
    public static final BlockItemId SPEEDRUNNER_DOOR = create("speedrunner_door");
    public static final BlockItemId DEAD_SPEEDRUNNER_LOG = create("dead_speedrunner_log");
    public static final BlockItemId DEAD_STRIPPED_SPEEDRUNNER_LOG = create("dead_stripped_speedrunner_log");
    public static final BlockItemId DEAD_SPEEDRUNNER_WOOD = create("dead_speedrunner_wood");
    public static final BlockItemId DEAD_STRIPPED_SPEEDRUNNER_WOOD = create("dead_stripped_speedrunner_wood");
    public static final BlockItemId DEAD_SPEEDRUNNER_LEAVES = create("dead_speedrunner_leaves");
    public static final BlockItemId DEAD_SPEEDRUNNER_SAPLING = create("dead_speedrunner_sapling");
    public static final BlockItemId DEAD_SPEEDRUNNER_PLANKS = create("dead_speedrunner_planks");
    public static final BlockItemId DEAD_SPEEDRUNNER_SLAB = create("dead_speedrunner_slab");
    public static final BlockItemId DEAD_SPEEDRUNNER_STAIRS = create("dead_speedrunner_stairs");
    public static final BlockItemId DEAD_SPEEDRUNNER_FENCE = create("dead_speedrunner_fence");
    public static final BlockItemId DEAD_SPEEDRUNNER_FENCE_GATE = create("dead_speedrunner_fence_gate");
    public static final BlockItemId DEAD_SPEEDRUNNER_TRAPDOOR = create("dead_speedrunner_trapdoor");
    public static final BlockItemId DEAD_SPEEDRUNNER_BUTTON = create("dead_speedrunner_button");
    public static final BlockItemId DEAD_SPEEDRUNNER_PRESSURE_PLATE = create("dead_speedrunner_pressure_plate");
    public static final BlockItemId DEAD_SPEEDRUNNER_DOOR = create("dead_speedrunner_door");
    public static final BlockItemId METAL_SPEEDRUNNER_DOOR = create("metal_speedrunner_door");
    public static final BlockItemId METAL_SPEEDRUNNER_TRAPDOOR = create("metal_speedrunner_trapdoor");
    public static final BlockItemId MEDIATE_WEIGHTED_SPEEDRUNNER_PRESSURE_PLATE = create("mediate_weighted_speedrunner_pressure_plate");
    public static final BlockItemId DEAD_SPEEDRUNNER_BUSH = create("dead_speedrunner_bush");
    public static final BlockItemId SPEEDRUNNERS_WORKBENCH = create("speedrunners_workbench");
    public static final BlockItemId SPEEDRUNNER_BLOCK = create("speedrunner_block");
    public static final BlockItemId RAW_SPEEDRUNNER_BLOCK = create("raw_speedrunner_block");
    public static final BlockItemId THRUSTED_BLOCK = create("thrusted_block");
    public static final BlockItemId SPEEDRUNNER_ORE = create("speedrunner_ore");
    public static final BlockItemId DEEPSLATE_SPEEDRUNNER_ORE = create("deepslate_speedrunner_ore");
    public static final BlockItemId NETHER_SPEEDRUNNER_ORE = create("nether_speedrunner_ore");
    public static final BlockItemId IGNEOUS_ORE = create("igneous_ore");
    public static final BlockItemId DEEPSLATE_IGNEOUS_ORE = create("deepslate_igneous_ore");
    public static final BlockItemId NETHER_IGNEOUS_ORE = create("nether_igneous_ore");
    public static final BlockItemId EXPERIENCE_ORE = create("experience_ore");
    public static final BlockItemId DEEPSLATE_EXPERIENCE_ORE = create("deepslate_experience_ore");
    public static final BlockItemId NETHER_EXPERIENCE_ORE = create("nether_experience_ore");
    public static final BlockItemId FLESH_BLOCK = create("flesh_block");
    public static final BlockItemId DOOM_STONE = create("doom_stone");
    public static final BlockItemId DOOM_LOG = create("doom_log");
    public static final BlockItemId DOOM_LEAVES = create("doom_leaves");

    /**
     * Creates an {@code block item id.}
     */
    private static BlockItemId create(final String name) {
        Identifier id = ofSpeedrunnerMod(name);
        return BlockItemId.create(id, id);
    }
}