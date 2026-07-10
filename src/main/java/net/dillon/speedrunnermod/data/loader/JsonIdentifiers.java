package net.dillon.speedrunnermod.data.loader;

/**
 * A class used specifically to get the names of certain Minecraft ".json" files.
 */
public final class JsonIdentifiers {
    public static final String WARM_OCEAN = ofBiome("warm_ocean");
    public static final String BASALT_DELTAS = ofBiome("basalt_deltas");
    public static final String CRIMSON_FOREST = ofBiome("crimson_forest");
    public static final String NETHER_WASTES = ofBiome("nether_wastes");
    public static final String SOUL_SAND_VALLEY = ofBiome("soul_sand_valley");
    public static final String THE_END = ofBiome("the_end");
    public static final String WARPED_FOREST = ofBiome("warped_forest");

    public static final String MONSTER_ROOM = ofPlacedFeature("monster_room");
    public static final String MONSTER_ROOM_DEEP = ofPlacedFeature("monster_room_deep");
    public static final String ORE_DIAMOND = ofPlacedFeature("ore_diamond");
    public static final String ORE_DIAMOND_BURIED = ofPlacedFeature("ore_diamond_buried");
    public static final String ORE_DIAMOND_LARGE = ofPlacedFeature("ore_diamond_large");
    public static final String ORE_LAPIS = ofPlacedFeature("ore_lapis");
    public static final String ORE_LAPIS_BURIED = ofPlacedFeature("ore_lapis_buried");
    public static final String TREES_PLAINS = ofPlacedFeature("trees_plains");

    public static final String ANCIENT_CITIES = ofStructureSet("ancient_cities");
    public static final String DESERT_PYRAMIDS = ofStructureSet("desert_pyramids");
    public static final String END_CITIES = ofStructureSet("end_cities");
    public static final String JUNGLE_TEMPLES = ofStructureSet("jungle_temples");
    public static final String MINESHAFTS = ofStructureSet("mineshafts");
    public static final String IGLOOS = ofStructureSet("igloos");
    public static final String OCEAN_RUINS = ofStructureSet("ocean_ruins");
    public static final String SWAMP_HUTS = ofStructureSet("swamp_huts");
    public static final String NETHER_COMPLEXES = ofStructureSet("nether_complexes");
    public static final String PILLAGER_OUTPOSTS = ofStructureSet("pillager_outposts");
    public static final String RUINED_PORTALS = ofStructureSet("ruined_portals");
    public static final String SHIPWRECKS = ofStructureSet("shipwrecks");
    public static final String STRONGHOLDS = ofStructureSet("strongholds");
    public static final String TRIAL_CHAMBERS = ofStructureSet("trial_chambers");
    public static final String TRAIL_RUINS = ofStructureSet("trail_ruins");
    public static final String VILLAGES = ofStructureSet("villages");
    public static final String WOODLAND_MANSIONS = ofStructureSet("woodland_mansions");

    public static final String END = "worldgen/noise_settings/end.json";

    /**
     * Returns the filename for a biome in the "worldgen/biome" folder.
     */
    private static String ofBiome(String biomeKey) {
        return "worldgen/biome/" + biomeKey + ".json";
    }

    /**
     * Returns the filename for a placed feature in the "worldgen/placed_feature" folder.
     */
    private static String ofPlacedFeature(String placedFeatureKey) {
        return "worldgen/placed_feature/" + placedFeatureKey + ".json";
    }

    /**
     * Returns the filename for a structure in the "worldgen/structure_set" folder.
     */
    private static String ofStructureSet(String structureSetKey) {
        return "worldgen/structure_set/" + structureSetKey + ".json";
    }
}