package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.mixin.block.BlockStateBaseMixin;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

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

    /**
     * These are the block tags that have a modified hardness value. See {@link BlockStateBaseMixin} for more.
     */
    public static class BlockHardness {
        public static TagKey<Block> INSTABREAK = of("instabreak");
        public static TagKey<Block> HARDNESS_0_1 = blockHardness(0, 1);
        public static TagKey<Block> HARDNESS_0_2 = blockHardness(0, 2);
        public static TagKey<Block> HARDNESS_0_3 = blockHardness(0, 3);
        public static TagKey<Block> HARDNESS_0_35 = blockHardness(0, 35);
        public static TagKey<Block> HARDNESS_0_37 = blockHardness(0, 37);
        public static TagKey<Block> HARDNESS_0_4 = blockHardness(0, 4);
        public static TagKey<Block> HARDNESS_0_5 = blockHardness(0, 5);
        public static TagKey<Block> HARDNESS_0_6 = blockHardness(0, 6);
        public static TagKey<Block> HARDNESS_0_65 = blockHardness(0, 65);
        public static TagKey<Block> HARDNESS_0_7 = blockHardness(0, 7);
        public static TagKey<Block> HARDNESS_0_8 = blockHardness(0, 8);
        public static TagKey<Block> HARDNESS_1_3 = blockHardness(1, 3);
        public static TagKey<Block> HARDNESS_1_4 = blockHardness(1, 4);
        public static TagKey<Block> HARDNESS_1_5 = blockHardness(1, 5);
        public static TagKey<Block> HARDNESS_1_6 = blockHardness(1, 6);
        public static TagKey<Block> HARDNESS_2 = blockHardness(2);
        public static TagKey<Block> HARDNESS_2_5 = blockHardness(2, 5);
        public static TagKey<Block> HARDNESS_3 = blockHardness(3);
        public static TagKey<Block> HARDNESS_4 = blockHardness(4);
        public static TagKey<Block> HARDNESS_4_5 = blockHardness(4, 5);
        public static TagKey<Block> HARDNESS_5 = blockHardness(5);
        public static TagKey<Block> HARDNESS_6 = blockHardness(6);
        public static TagKey<Block> HARDNESS_7 = blockHardness(7);
        public static TagKey<Block> HARDNESS_8 = blockHardness(8);
        public static TagKey<Block> HARDNESS_9 = blockHardness(9);
        public static TagKey<Block> HARDNESS_10 = blockHardness(10);
        public static TagKey<Block> HARDNESS_25 = blockHardness(25);
    }

    /**
     * Registers a {@code block tag.}
     */
    private static TagKey<Block> of(String path) {
        return TagKey.create(Registries.BLOCK, ofSpeedrunnerMod(path));
    }

    /**
     * Registers a {@code block hardness tag} with a "double" value.
     */
    private static TagKey<Block> blockHardness(int base, int decimal) {
        return TagKey.create(Registries.BLOCK, ofSpeedrunnerMod("block_hardness/"+base+"-"+decimal+"_hardness"));
    }

    /**
     * Registers a {@code block hardness tag} with an integer value.
     */
    private static TagKey<Block> blockHardness(int hardness) {
        return TagKey.create(Registries.BLOCK, ofSpeedrunnerMod("block_hardness/"+hardness+"_hardness"));
    }

    /**
     * Initializes all Speedrunner Mod {@code block tags.}
     */
    public static void initializeBlockTags() {
        SpeedrunnerMod.debug("Initialized block tags.");
    }
}