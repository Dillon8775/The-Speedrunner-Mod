package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.Structure;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code structure tags.} These are only really used because vanilla Minecraft doesn't have a tag for these structures.
 */
public class ModStructureTags {
    public static final TagKey<Structure> ANCIENT_CITIES = of("ancient_cities");
    public static final TagKey<Structure> BASTIONS = of("bastions");
    public static final TagKey<Structure> DESERT_PYRAMIDS = of("desert_pyramids");
    public static final TagKey<Structure> FORTRESSES = of("fortresses");
    public static final TagKey<Structure> STRONGHOLDS = of("strongholds");

    /**
     * Registers a {@code structure tag.}
     */
    private static TagKey<Structure> of(String path) {
        return TagKey.create(Registries.STRUCTURE, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code structure tags.}
     */
    public static void initializeStructureTags() {
        SpeedrunnerMod.debug("Initialized structure tags.");
    }
}