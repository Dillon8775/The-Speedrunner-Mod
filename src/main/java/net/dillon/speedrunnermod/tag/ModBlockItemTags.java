package net.dillon.speedrunnermod.tag;

import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockItemTagId;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod {@code block item tags.}
 */
public class ModBlockItemTags {
    public static final BlockItemTagId DEAD_SPEEDRUNNER_LOGS = createBlockAndItemTag("dead_speedrunner_logs");
    public static final BlockItemTagId DOOM_LOGS = createBlockAndItemTag("doom_logs");
    public static final BlockItemTagId EXPERIENCE_ORES = createBlockAndItemTag("experience_ores");
    public static final BlockItemTagId IGNEOUS_ORES = createBlockAndItemTag("igneous_ores");
    public static final BlockItemTagId IRON_BLOCKS = createBlockAndItemTag("iron_blocks");
    public static final BlockItemTagId NETHER_PORTAL_BASE_BLOCKS = createBlockAndItemTag("nether_portal_base_blocks");
    public static final BlockItemTagId SPEEDRUNNER_FUELS = createBlockAndItemTag("speedrunner_fuels");
    public static final BlockItemTagId SPEEDRUNNER_LOGS = createBlockAndItemTag("speedrunner_logs");
    public static final BlockItemTagId SPEEDRUNNER_ORES = createBlockAndItemTag("speedrunner_ores");
    public static final BlockItemTagId SPEEDRUNNER_PLANKS = createBlockAndItemTag("speedrunner_planks");
    public static final BlockItemTagId SPEEDRUNNER_SAPLING_PLACEABLES = createBlockAndItemTag("speedrunner_sapling_placeables");

    /**
     * Creates a {@code block item tag.}
     */
    private static BlockItemTagId createBlockAndItemTag(final String name) {
        Identifier id = ofSpeedrunnerMod(name);
        return BlockItemTagId.create(id, id);
    }
}