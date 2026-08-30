package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

import static net.dillon.dillonlib.factory.Factories.createStructureTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code structure tags.} These are only really used because vanilla Minecraft doesn't have a tag for these structures.
 */
public class ModStructureTags {
    public static final TagKey<Structure> ANCIENT_CITIES = createStructureTag(ofSpeedrunnerMod("ancient_cities"));
    public static final TagKey<Structure> BASTIONS = createStructureTag(ofSpeedrunnerMod("bastions"));
    public static final TagKey<Structure> DESERT_PYRAMIDS = createStructureTag(ofSpeedrunnerMod("desert_pyramids"));
    public static final TagKey<Structure> FORTRESSES = createStructureTag(ofSpeedrunnerMod("fortresses"));
    public static final TagKey<Structure> STRONGHOLDS = createStructureTag(ofSpeedrunnerMod("strongholds"));
    public static final TagKey<Structure> END_CITIES = createStructureTag(ofSpeedrunnerMod("end_cities"));
    public static final TagKey<Structure> IGLOOS = createStructureTag(ofSpeedrunnerMod("igloos"));
    public static final TagKey<Structure> PILLAGER_OUTPOSTS = createStructureTag(ofSpeedrunnerMod("pillager_outposts"));
    public static final TagKey<Structure> TRAIL_RUINS = createStructureTag(ofSpeedrunnerMod("trail_ruins"));

    /**
     * Initializes all Speedrunner Mod {@code structure tags.}
     */
    public static void initializeStructureTags() {
        SpeedrunnerMod.LOGGER.debug("Initialized structure tags.");
    }
}