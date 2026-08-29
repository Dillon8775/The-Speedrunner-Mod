package net.dillon.speedrunnermod.tag;

import net.minecraft.tags.BlockItemTagId;

import static net.dillon.dillonlib.factory.Factories.createBlockAndItemTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod {@code block item tags.}
 */
public class ModBlockItemTags {
    public static final BlockItemTagId DEAD_SPEEDRUNNER_LOGS = createBlockAndItemTag(ofSpeedrunnerMod("dead_speedrunner_logs"));
    public static final BlockItemTagId DOOM_LOGS = createBlockAndItemTag(ofSpeedrunnerMod("doom_logs"));
    public static final BlockItemTagId EXPERIENCE_ORES = createBlockAndItemTag(ofSpeedrunnerMod("experience_ores"));
    public static final BlockItemTagId IGNEOUS_ORES = createBlockAndItemTag(ofSpeedrunnerMod("igneous_ores"));
    public static final BlockItemTagId IRON_BLOCKS = createBlockAndItemTag(ofSpeedrunnerMod("iron_blocks"));
    public static final BlockItemTagId SPEEDRUNNER_FUELS = createBlockAndItemTag(ofSpeedrunnerMod("speedrunner_fuels"));
    public static final BlockItemTagId SPEEDRUNNER_LOGS = createBlockAndItemTag(ofSpeedrunnerMod("speedrunner_logs"));
    public static final BlockItemTagId SPEEDRUNNER_ORES = createBlockAndItemTag(ofSpeedrunnerMod("speedrunner_ores"));
    public static final BlockItemTagId SPEEDRUNNER_PLANKS = createBlockAndItemTag(ofSpeedrunnerMod("speedrunner_planks"));
    public static final BlockItemTagId SPEEDRUNNER_SAPLING_PLACEABLES = createBlockAndItemTag(ofSpeedrunnerMod("speedrunner_sapling_placeables"));
}