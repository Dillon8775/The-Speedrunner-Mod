package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

import static net.dillon.dillonlib.factory.Factories.createBlockTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod {@code block harness tags}.
 */
public class ModBlockHardnessTags {
    public static final TagKey<Block> INSTABREAK = createBlockTag(ofSpeedrunnerMod("block_hardness/instabreak"));
    public static final TagKey<Block> HARDNESS_0_1 = createBlockHardnessTag(0, 1);
    public static final TagKey<Block> HARDNESS_0_2 = createBlockHardnessTag(0, 2);
    public static final TagKey<Block> HARDNESS_0_3 = createBlockHardnessTag(0, 3);
    public static final TagKey<Block> HARDNESS_0_35 = createBlockHardnessTag(0, 35);
    public static final TagKey<Block> HARDNESS_0_37 = createBlockHardnessTag(0, 37);
    public static final TagKey<Block> HARDNESS_0_4 = createBlockHardnessTag(0, 4);
    public static final TagKey<Block> HARDNESS_0_5 = createBlockHardnessTag(0, 5);
    public static final TagKey<Block> HARDNESS_0_6 = createBlockHardnessTag(0, 6);
    public static final TagKey<Block> HARDNESS_0_65 = createBlockHardnessTag(0, 65);
    public static final TagKey<Block> HARDNESS_0_7 = createBlockHardnessTag(0, 7);
    public static final TagKey<Block> HARDNESS_0_8 = createBlockHardnessTag(0, 8);
    public static final TagKey<Block> HARDNESS_1_0 = createBlockHardnessTag(1);
    public static final TagKey<Block> HARDNESS_1_3 = createBlockHardnessTag(1, 3);
    public static final TagKey<Block> HARDNESS_1_4 = createBlockHardnessTag(1, 4);
    public static final TagKey<Block> HARDNESS_1_5 = createBlockHardnessTag(1, 5);
    public static final TagKey<Block> HARDNESS_1_6 = createBlockHardnessTag(1, 6);
    public static final TagKey<Block> HARDNESS_2_0 = createBlockHardnessTag(2);
    public static final TagKey<Block> HARDNESS_2_5 = createBlockHardnessTag(2, 5);
    public static final TagKey<Block> HARDNESS_3_0 = createBlockHardnessTag(3);
    public static final TagKey<Block> HARDNESS_4_0 = createBlockHardnessTag(4);
    public static final TagKey<Block> HARDNESS_4_5 = createBlockHardnessTag(4, 5);
    public static final TagKey<Block> HARDNESS_5_0 = createBlockHardnessTag(5);
    public static final TagKey<Block> HARDNESS_6_0 = createBlockHardnessTag(6);
    public static final TagKey<Block> HARDNESS_7_0 = createBlockHardnessTag(7);
    public static final TagKey<Block> HARDNESS_8_0 = createBlockHardnessTag(8);
    public static final TagKey<Block> HARDNESS_9_0 = createBlockHardnessTag(9);
    public static final TagKey<Block> HARDNESS_10 = createBlockHardnessTag(10);
    public static final TagKey<Block> HARDNESS_25 = createBlockHardnessTag(25);

    /**
     * Registers a {@code block baseHardness tag} with a "double" value.
     */
    private static TagKey<Block> createBlockHardnessTag(int base, int decimal) {
        return createBlockTag(ofSpeedrunnerMod("block_hardness/"+base+"-"+decimal+"_hardness"));
    }

    /**
     * Registers a {@code block baseHardness tag} with an integer value.
     */
    private static TagKey<Block> createBlockHardnessTag(int baseHardness) {
        return createBlockTag(ofSpeedrunnerMod("block_hardness/"+ baseHardness +"_hardness"));
    }

    /**
     * Initializes all Speedrunner Mod {@code block baseHardness tags.}
     */
    public static void initializeBlockHardnessTags() {
        SpeedrunnerMod.debug("Initialized block baseHardness tags.");
    }
}