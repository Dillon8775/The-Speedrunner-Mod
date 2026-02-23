package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonElement;
import net.minecraft.world.biome.SpawnSettings;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

public class EntitySpawnsLoader {

    public static void modifyBiomesWithDefaultMonsters(JsonElement jsonElement) {
        Map<String, Integer[]> monsterSpawns = new HashMap<>();
        monsterSpawns.put("minecraft:spider", LoaderMain.createSpawnSettings(isDoomMode() ? 75 : 100, isDoomMode() ? 1 : 4, isDoomMode() ? 5 : 4));
        monsterSpawns.put("minecraft:slime", LoaderMain.createSpawnSettings(isDoomMode() ? 50 : 100, 1, 4));
        monsterSpawns.put("minecraft:enderman", LoaderMain.createSpawnSettings(isDoomMode() ? 25 : 50, isDoomMode() ? 1 : 4, 4));
        monsterSpawns.put("minecraft:witch", LoaderMain.createSpawnSettings(isDoomMode() ? 50 : 5, 1, isDoomMode() ? 4 : 1));

        LoaderMain.modifyMonsterSpawns(jsonElement, monsterSpawns, false);

        Map<String, Integer[]> customOrNoChangedWeightMonsterSpawns = new HashMap<>();
        monsterSpawns.put("minecraft:zombie", LoaderMain.createSpawnSettings(isDoomMode() ? 1 : 4, 4));
        monsterSpawns.put("minecraft:creeper", LoaderMain.createSpawnSettings(isDoomMode() ? 1 : 2, 4));

        LoaderMain.modifyMonsterSpawns(jsonElement, customOrNoChangedWeightMonsterSpawns, true);
    }

    public static void modifyBiomesWithFarmAnimals(JsonElement jsonElement) {
        Map<String, Integer[]> creatureSpawns = new HashMap<>();
        creatureSpawns.put("minecraft:cow", LoaderMain.createSpawnSettings(16, 1, 4, 8, 4));
        creatureSpawns.put("minecraft:pig", LoaderMain.createSpawnSettings(12, 1, 4, 8, 4));
        creatureSpawns.put("minecraft:sheep", LoaderMain.createSpawnSettings(8, 1, 4, 8, 4));
        creatureSpawns.put("minecraft:chicken", LoaderMain.createSpawnSettings(8, 1, 4, 8, 4));

        LoaderMain.modifyCreatureSpawns(jsonElement, creatureSpawns, false);
    }

    public static void modifyWaterCreatureSpawns(JsonElement jsonElement) {
        Map<String, Integer[]> waterCreatureSpawns = new HashMap<>();
        waterCreatureSpawns.put("minecraft:dolphin", LoaderMain.createSpawnSettings(15, 1, 1, 2, 1));
        waterCreatureSpawns.put("minecraft:tropical_fish", LoaderMain.createSpawnSettings(10, 1, 4, 8, 4));

        LoaderMain.modifyCreatureSpawns(jsonElement, waterCreatureSpawns, true);
    }

    /**
     * A list of all biomes that should modify monster spawns.
     */
    public static List<String> biomesWithDefaultMonsters() {
        return List.of(
                // Newly added biomes
                biomeName("cherry_grove"),
                biomeName("dripstone_caves"),
                biomeName("desert"),
                biomeName("snowy_plains"),
                biomeName("ice_spikes"),
                biomeName("sunflower_plains"),
                biomeName("ocean"),
                biomeName("lukewarm_ocean"),
                biomeName("cold_ocean"),

                biomeName("old_growth_pine_taiga"),
                biomeName("old_growth_spruce_taiga"),
                biomeName("windswept_hills"),
                biomeName("windswept_gravelly_hills"),
                biomeName("windswept_forest"),
                biomeName("savanna"),
                biomeName("savanna_plateau"),
                biomeName("windswept_savanna"),
                biomeName("badlands"),
                biomeName("eroded_badlands"),
                biomeName("wooded_badlands"),
                biomeName("frozen_ocean"),
                biomeName("deep_frozen_ocean"),
                biomeName("forest"),
                biomeName("flower_forest"),
                biomeName("birch_forest"),
                biomeName("old_growth_birch_forest"),
                biomeName("taiga"),
                biomeName("snowy_taiga"),
                biomeName("dark_forest"),
                biomeName("swamp"),
                biomeName("mangrove_swamp"),
                biomeName("river"),
                biomeName("frozen_river"),
                biomeName("beach"),
                biomeName("snowy_beach"),
                biomeName("stony_shore"),
                biomeName("meadow"),
                biomeName("frozen_peaks"),
                biomeName("jagged_peaks"),
                biomeName("stony_peaks"),
                biomeName("snowy_slopes"),
                biomeName("grove"),
                biomeName("lush_caves"));
    }

    /**
     * A list of all biomes that use {@link net.minecraft.world.gen.feature.DefaultBiomeFeatures#addFarmAnimals(SpawnSettings.Builder)}.
     */
    public static List<String> biomesWithFarmAnimals() {
        return List.of(
                biomeName("old_growth_pine_taiga"),
                biomeName("old_growth_spruce_taiga"),
                biomeName("windswept_hills"),
                biomeName("windswept_gravelly_hills"),
                biomeName("windswept_forest"),
                biomeName("savanna"),
                biomeName("savanna_plateau"),
                biomeName("windswept_savanna"),
                biomeName("forest"),
                biomeName("flower_forest"),
                biomeName("birch_forest"),
                biomeName("old_growth_birch_forest"),
                biomeName("taiga"),
                biomeName("snowy_taiga"),
                biomeName("dark_forest"),
                biomeName("swamp"),
                biomeName("grove"));
    }

    /**
     * Returns the file name of the given biome.
     */
    private static String biomeName(String biomeName) {
        return "worldgen/biome/" + biomeName + ".json";
    }
}