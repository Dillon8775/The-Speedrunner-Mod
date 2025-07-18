package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code block tags.} Useful for many things in some mixins.
 */
public class ModBlockTags {
    public static TagKey<Block> DEAD_SPEEDRUNNER_LOGS = of("dead_speedrunner_logs");
    public static TagKey<Block> DOOM_LOGS = of("doom_logs");
    public static TagKey<Block> EXPERIENCE_ORES = of("experience_ores");
    public static TagKey<Block> IGNEOUS_ORES = of("igneous_ores");
    public static TagKey<Block> IRON_BLOCKS = of("iron_blocks");
    public static TagKey<Block> NETHER_PORTAL_BASE_BLOCKS = of("nether_portal_base_blocks");
    public static TagKey<Block> SPEEDRUNNER_FUELS = of("speedrunner_fuels");
    public static TagKey<Block> SPEEDRUNNER_LOGS = of("speedrunner_logs");
    public static TagKey<Block> SPEEDRUNNER_ORES = of("speedrunner_ores");
    public static TagKey<Block> SPEEDRUNNER_PLANKS = of("speedrunner_planks");
    public static TagKey<Block> SPEEDRUNNER_SAPLING_PLACEABLES = of("speedrunner_sapling_placeables");
    public static TagKey<Block> SPEEDRUNNER_SIGNS = of("speedrunner_signs");

    /**
     * These are the block tags that have a modified hardness value. See {@link net.dillon.speedrunnermod.mixin.main.block.AbstractBlockStateMixin} for more.
     */
    public static class BlockHardness {
        public static TagKey<Block> ZERO_HARDNESS = blockHardness(0);
        public static TagKey<Block> ZERO_ONE_HARDNESS = blockHardness(0, 1);
        public static TagKey<Block> ZERO_TWO_HARDNESS = blockHardness(0, 2);
        public static TagKey<Block> ZERO_THREE_HARDNESS = blockHardness(0, 3);
        public static TagKey<Block> ZERO_THREEFIVE_HARDNESS = blockHardness(0, 35);
        public static TagKey<Block> ZERO_THREESEVEN_HARDNESS = blockHardness(0, 37);
        public static TagKey<Block> ZERO_FOUR_HARDNESS = blockHardness(0, 4);
        public static TagKey<Block> ZERO_FIVE_HARDNESS = blockHardness(0, 5);
        public static TagKey<Block> ZERO_SIX_HARDNESS = blockHardness(0, 6);
        public static TagKey<Block> ZERO_SIXFIVE_HARDNESS = blockHardness(0, 65);
        public static TagKey<Block> ZERO_SEVEN_HARDNESS = blockHardness(0, 7);
        public static TagKey<Block> ZERO_EIGHT_HARDNESS = blockHardness(0, 8);
        public static TagKey<Block> ONE_HARDNESS = blockHardness(1);
        public static TagKey<Block> ONE_THREE_HARDNESS = blockHardness(1, 3);
        public static TagKey<Block> ONE_FOUR_HARDNESS = blockHardness(1, 4);
        public static TagKey<Block> ONE_FIVE_HARDNESS = blockHardness(1, 5);
        public static TagKey<Block> ONE_SIX_HARDNESS = blockHardness(1, 6);
        public static TagKey<Block> TWO_HARDNESS = blockHardness(2);
        public static TagKey<Block> TWO_FIVE_HARDNESS = blockHardness(2, 5);
        public static TagKey<Block> THREE_HARDNESS = blockHardness(3);
        public static TagKey<Block> FOUR_FIVE_HARDNESS = blockHardness(4, 5);
        public static TagKey<Block> FIVE_HARDNESS = blockHardness(5);
        public static TagKey<Block> SIX_HARDNESS = blockHardness(6);
        public static TagKey<Block> SEVEN_HARDNESS = blockHardness(7);
        public static TagKey<Block> EIGHT_HARDNESS = blockHardness(8);
        public static TagKey<Block> NINE_HARDNESS = blockHardness(9);
        public static TagKey<Block> TEN_HARDNESS = blockHardness(10);
        public static TagKey<Block> TWENTY_FIVE_HARDNESS = blockHardness(25);
    }

    /**
     * Registers a {@code block tag.}
     */
    private static TagKey<Block> of(String path) {
        return TagKey.of(RegistryKeys.BLOCK, ofSpeedrunnerMod(path));
    }

    /**
     * Registers a {@code block hardness tag} with a "double" value.
     */
    private static TagKey<Block> blockHardness(int base, int decimal) {
        return TagKey.of(RegistryKeys.BLOCK, ofSpeedrunnerMod("block_hardness/"+base+"-"+decimal+"_hardness"));
    }

    /**
     * Registers a {@code block hardness tag} with an integer value.
     */
    private static TagKey<Block> blockHardness(int hardness) {
        return TagKey.of(RegistryKeys.BLOCK, ofSpeedrunnerMod("block_hardness/"+hardness+"_hardness"));
    }

    /**
     * Initializes all Speedrunner Mod {@code block tags.}
     */
    public static void initializeBlockTags() {
        SpeedrunnerMod.debug("Initialized block tags.");
    }
}