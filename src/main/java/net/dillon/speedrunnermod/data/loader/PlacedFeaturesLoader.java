package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.dillon.speedrunnermod.util.AI;
import net.dillon.speedrunnermod.util.ModUtil;

import static net.dillon.speedrunnermod.option.ModOptions.*;

/**
 * Contains all of the {@code placed feature modifications.}
 */
@AI
public class PlacedFeaturesLoader {

    /**
     * Increases the spawn rate of {@code monster rooms.}
     */
    public static void modifyMonsterRoom(JsonElement jsonElement) {
        JsonArray placement = jsonElement.getAsJsonObject().getAsJsonArray("placement");

        for (JsonElement element : placement) {
            JsonObject placementObj = element.getAsJsonObject();
            if (placementObj.has("type") && placementObj.get("type").getAsString().equals("minecraft:count")) {
                placementObj.addProperty("count", isSsrEverywhere() || isSsrVeryCommonCommon() || isSsrNormal() ? 16 : 8);
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
                placementObj.addProperty("count", fileName.equals(oreDiamond) ? ModUtil.DIAMOND_ORE_SPAWN_CHANCE : ModUtil.BURIED_DIAMOND_ORE_SPAWN_CHANCE);
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

                placementObj.addProperty("count", ModUtil.LARGE_DIAMOND_ORE_SPAWN_CHANCE);
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
                placementObj.addProperty("count", fileName.equals(oreLapis) ? ModUtil.LAPIS_LAZULI_ORE_SPAWN_CHANCE : ModUtil.BURIED_LAPIS_LAZULI_ORE_SPAWN_CHANCE);
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
                firstElement.addProperty("data", ModUtil.TREES_PLAINS_COUNT);
                break;
            }
        }
    }
}