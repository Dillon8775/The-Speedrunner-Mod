package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.dillon.speedrunnermod.helper.ModHelper;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

/**
 * Contains all of the {@code placed feature modifications.}
 */
public class PlacedFeaturesLoader {

    /**
     * Increases the spawn rate of {@code monster rooms.}
     */
    public static void modifyMonsterRoom(JsonElement jsonElement) {
        JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

        for (JsonElement element : placement) {
            JsonObject placementObj = element.getAsJsonObject();
            if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:count")) {
                placementObj.addProperty("count", common().worldGen().makeStructuresMoreCommon ? 16 : 8);
                break;
            }
        }
    }

    /**
     * Increases the spawn rate of normal-sized diamond ores.
     */
    public static void modifyOreDiamond(String fileName, String oreDiamond, JsonElement jsonElement) {
        JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

        for (JsonElement element : placement) {
            JsonObject placementObj = element.getAsJsonObject();
            if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:count")) {
                placementObj.addProperty("count", fileName.equals(oreDiamond) ? ModHelper.DIAMOND_ORE_SPAWN_CHANCE : ModHelper.BURIED_DIAMOND_ORE_SPAWN_CHANCE);
                break;
            }
        }
    }

    /**
     * Increases the spawn rate of large diamond ore veins.
     */
    public static void modifyOreDiamondLarge(JsonElement jsonElement) {
        JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

        for (JsonElement element : placement) {
            JsonObject placementObj = element.getAsJsonObject();
            if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:rarity_filter")) {
                placementObj.addProperty("type", "minecraft:count");

                int chance = placementObj.get("chance").getAsInt();
                placementObj.remove("chance");
                placementObj.addProperty("count", chance);

                placementObj.addProperty("count", ModHelper.LARGE_DIAMOND_ORE_SPAWN_CHANCE);
                break;
            }
        }
    }

    /**
     * Increases the spawn rate of lapis ores.
     */
    public static void modifyOreLapis(String fileName, String oreLapis, JsonElement jsonElement) {
        JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

        for (JsonElement element : placement) {
            JsonObject placementObj = element.getAsJsonObject();
            if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:count")) {
                placementObj.addProperty("count", fileName.equals(oreLapis) ? ModHelper.LAPIS_LAZULI_ORE_SPAWN_CHANCE : ModHelper.BURIED_LAPIS_LAZULI_ORE_SPAWN_CHANCE);
                break;
            }
        }
    }

    /**
     * Increases the spawn rate of plain oak trees in plains biomes.
     */
    public static void modifyTreePlains(JsonElement jsonElement) {
        JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

        for (JsonElement element : placement) {
            JsonObject placementObj = element.getAsJsonObject();
            if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:count")) {
                JsonObject countObject = placementObj.getAsJsonObject("count");
                JsonArray distributionArray = countObject.getAsJsonArray("distribution");
                JsonObject firstElement = distributionArray.get(0).getAsJsonObject();
                firstElement.addProperty("data", ModHelper.TREES_PLAINS_COUNT);
                break;
            }
        }
    }
}