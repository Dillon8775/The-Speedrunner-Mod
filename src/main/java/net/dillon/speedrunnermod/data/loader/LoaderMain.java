package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;

import java.util.Map;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Contains helper methods used to create certain objects and arrays in JSON files.
 */
@ChatGPT(Credit.FULL_CREDIT)
public class LoaderMain {

    /**
     * Modifies the creature spawn parameters in a given biome.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    protected static void modifyCreatureSpawns(JsonElement jsonElement, Map<String, Integer[]> creatureValues, boolean waterCreature) {
        JsonArray spawnersArray = jsonElement.getAsJsonObject().getAsJsonObject("spawners").getAsJsonArray(waterCreature ? "water_creature" : "creature");

        for (JsonElement spawnerElement : spawnersArray) {
            JsonObject spawner = spawnerElement.getAsJsonObject();
            String mobType = spawner.get("type").getAsString();

            if (creatureValues.containsKey(mobType)) {
                Integer[] values = creatureValues.get(mobType);
                spawner.addProperty("weight", values[0]);
                if (options().main.mobSpawningRate.equals(ModOptions.MobSpawningRate.LOW)) {
                    spawner.addProperty("minCount", values[1]);
                    spawner.addProperty("maxCount", values[4]);
                } else if (options().main.mobSpawningRate.equals(ModOptions.MobSpawningRate.NORMAL)) {
                    spawner.addProperty("minCount", values[2]);
                    spawner.addProperty("maxCount", values[4]);
                } else if (options().main.mobSpawningRate.equals(ModOptions.MobSpawningRate.HIGH)) {
                    spawner.addProperty("minCount", values[2]);
                    spawner.addProperty("maxCount", values[3]);
                }
            }
        }
    }

    /**
     * Modifies the monster spawn parameters in a given biome.
     */
    @ChatGPT(Credit.FULL_CREDIT)
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

        if (options().main.playingMode.doom()) {
            JsonObject vindicator = new JsonObject();
            vindicator.addProperty("type", "minecraft:vindicator");
            vindicator.addProperty("maxCount", 4);
            vindicator.addProperty("minCount", 1);
            vindicator.addProperty("weight", 100);
            spawnersArray.add(vindicator);
        }
    }

    /**
     * Creates a mob spawn setting, specifically for creature spawns.
     */
    protected static Integer[] createSpawnSettings(int weight, int minCountLow, int defaultMinCount, int maxCountHigh, int defaultMaxCount) {
        return new Integer[]{weight, minCountLow, defaultMinCount, maxCountHigh, defaultMaxCount};
    }

    /**
     * Creates a mob spawn setting, specifically for monster spawns.
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