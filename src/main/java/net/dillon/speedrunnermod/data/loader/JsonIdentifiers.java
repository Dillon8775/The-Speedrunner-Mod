package net.dillon.speedrunnermod.data.loader;

/**
 * A class used specifically to get the names of certain Minecraft ".json" files.
 */
public final class JsonIdentifiers {
    public static final String WARM_OCEAN = biome("warm_ocean");
    public static final String BASALT_DELTAS = biome("basalt_deltas");
    public static final String CRIMSON_FOREST = biome("crimson_forest");
    public static final String NETHER_WASTES = biome("nether_wastes");
    public static final String SOUL_SAND_VALLEY = biome("soul_sand_valley");
    public static final String THE_END = biome("the_end");
    public static final String WARPED_FOREST = biome("warped_forest");

    public static final String MONSTER_ROOM = placedFeature("monster_room");
    public static final String MONSTER_ROOM_DEEP = placedFeature("monster_room_deep");
    public static final String ORE_DIAMOND = placedFeature("ore_diamond");
    public static final String ORE_DIAMOND_BURIED = placedFeature("ore_diamond_buried");
    public static final String ORE_DIAMOND_LARGE = placedFeature("ore_diamond_large");
    public static final String ORE_LAPIS = placedFeature("ore_lapis");
    public static final String ORE_LAPIS_BURIED = placedFeature("ore_lapis_buried");
    public static final String TREES_PLAINS = placedFeature("trees_plains");

    public static final String ANCIENT_CITIES = structure("ancient_cities");
    public static final String DESERT_PYRAMIDS = structure("desert_pyramids");
    public static final String END_CITIES = structure("end_cities");
    public static final String JUNGLE_TEMPLES = structure("jungle_temples");
    public static final String NETHER_COMPLEXES = structure("nether_complexes");
    public static final String PILLAGER_OUTPOSTS = structure("pillager_outposts");
    public static final String RUINED_PORTALS = structure("ruined_portals");
    public static final String SHIPWRECKS = structure("shipwrecks");
    public static final String STRONGHOLDS = structure("strongholds");
    public static final String TRIAL_CHAMBERS = structure("trial_chambers");
    public static final String VILLAGES = structure("villages");
    public static final String WOODLAND_MANSIONS = structure("woodland_mansions");

    public static final String END = "worldgen/noise_settings/end.json";

    /**
     * Returns the filename for a biome in the "worldgen/biome" folder.
     */
    private static String biome(String biomeKey) {
        return "worldgen/biome/" + biomeKey + ".json";
    }

    /**
     * Returns the filename for a placed feature in the "worldgen/placed_feature" folder.
     */
    private static String placedFeature(String placedFeatureKey) {
        return "worldgen/placed_feature/" + placedFeatureKey + ".json";
    }

    /**
     * Returns the filename for a structure in the "worldgen/structure_set" folder.
     */
    private static String structure(String structureSetKey) {
        return "worldgen/structure_set/" + structureSetKey + ".json";
    }
}