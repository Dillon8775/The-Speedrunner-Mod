package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.block.Block;
import net.minecraft.data.family.BlockFamilies;
import net.minecraft.data.family.BlockFamily;

/**
 * The {@code speedrunner mod} block family.
 * <p>See data generators for more.</p>
 */
public class ModBlockFamilies {
    public static final BlockFamily SPEEDRUNNER_FAMILY = registerSignFamily(ModBlocks.SPEEDRUNNER_PLANKS, ModBlocks.SPEEDRUNNER_SIGN, ModBlocks.SPEEDRUNNER_WALL_SIGN);
    public static final BlockFamily DEAD_SPEEDRUNNER_FAMILY = registerSignFamily(ModBlocks.DEAD_SPEEDRUNNER_PLANKS, ModBlocks.DEAD_SPEEDRUNNER_SIGN, ModBlocks.DEAD_SPEEDRUNNER_WALL_SIGN);

    /**
     * Registers a {@code sign block family.}
     */
    private static BlockFamily registerSignFamily(Block block, Block sign, Block wallSign) {
        return BlockFamilies.register(block)
                .sign(sign, wallSign)
                .group("wooden").unlockCriterionName("has_planks").build();
    }

    /**
     * Initializes all Speedrunner Mod {@code block families.}
     */
    public static void initializeBlockFamilies() {
        SpeedrunnerMod.debug("Initialized block families.");
    }
}