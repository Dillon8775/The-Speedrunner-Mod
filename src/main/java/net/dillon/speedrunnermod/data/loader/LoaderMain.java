package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

/**
 * Contains helper methods used to create certain objects and arrays in JSON files.
 */
public class LoaderMain {

    /**
     * Modifies the creature spawn parameters in a given biome.
     */
    protected static void modifyCreatureSpawns(JsonElement jsonElement, Map<String, Integer[]> creatureValues, boolean waterCreature) {
        JsonArray spawnersArray = jsonElement.getAsJsonObject().getAsJsonObject("spawners").getAsJsonArray(waterCreature ? "water_creature" : "creature");

        for (JsonElement spawnerElement : spawnersArray) {
            JsonObject spawner = spawnerElement.getAsJsonObject();
            String mobType = spawner.get("type").getAsString();

            if (creatureValues.containsKey(mobType)) {
                Integer[] values = creatureValues.get(mobType);
                spawner.addProperty("weight", values[0]);
                spawner.addProperty("minCount", values[1]);
                spawner.addProperty("maxCount", values[2]);
            }
        }
    }

    /**
     * Modifies the monster spawn parameters in a given biome.
     */
    protected static void modifyMonsterSpawns(JsonElement jsonElement, Map<String, Integer[]> monsterValues, boolean customWeight) {
        JsonArray spawnersArray = jsonElement.getAsJsonObject().getAsJsonObject("spawners").getAsJsonArray("monster");

        for (JsonElement spawnerElement : spawnersArray) {
            JsonObject spawner = spawnerElement.getAsJsonObject();
            String mobType = spawner.get("type").getAsString();

            if (monsterValues.containsKey(mobType)) {
                Integer[] values = monsterValues.get(mobType);
                if (!customWeight) {
                    spawner.addProperty("weight", values[0]);
                    spawner.addProperty("minCount", values[1]);
                    spawner.addProperty("maxCount", values[2]);
                } else {
                    spawner.addProperty("minCount", values[0]);
                    spawner.addProperty("maxCount", values[1]);
                }
            }
        }

        if (isDoomMode()) {
            JsonObject vindicator = new JsonObject();
            vindicator.addProperty("type", "minecraft:vindicator");
            vindicator.addProperty("maxCount", 4);
            vindicator.addProperty("minCount", 1);
            vindicator.addProperty("weight", 100);
            spawnersArray.add(vindicator);

            JsonObject evoker = new JsonObject();
            evoker.addProperty("type", "minecraft:evoker");
            evoker.addProperty("maxCount", 1);
            evoker.addProperty("minCount", 1);
            evoker.addProperty("weight", 35);
            spawnersArray.add(evoker);
        }
    }

    /**
     * Creates a mob spawn setting, specifically for creature spawns.
     */
    protected static Integer[] createSpawnSettings(int weight, int minCount, int maxCount) {
        return new Integer[]{weight, minCount, maxCount};
    }

    /**
     * Creates a mob spawn setting without a weight, specifically for monster spawns.
     */
    protected static Integer[] createSpawnSettings(int minCount, int maxCount) {
        return new Integer[]{0, minCount, maxCount};
    }
}