package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.dillon.speedrunnermod.util.AI;

import static net.dillon.speedrunnermod.option.ModOptions.isPlayingModeDoom;

/**
 * Contains all of the {@code nether biomes modifications.}
 */
@AI
public class NetherBiomesLoader {

    /**
     * Modifies the water color and monster spawns for the {@code basalt deltas} biome.
     */
    public static void modifyBasaltDeltas(JsonElement jsonElement) {
        JsonObject basaltDeltasEffects = jsonElement.getAsJsonObject().getAsJsonObject("effects");
        basaltDeltasEffects.addProperty("water_color", 0xCACFD2);
        basaltDeltasEffects.addProperty("water_fog_color", 0xD5DBDB);

        JsonObject basaltDeltasSpawners = jsonElement.getAsJsonObject().getAsJsonObject("spawners");
        JsonArray basaltDeltasMonsters = new JsonArray();

        JsonObject ghast = new JsonObject();
        ghast.addProperty("type", "minecraft:ghast");
        ghast.addProperty("maxCount", 1);
        ghast.addProperty("minCount", 1);
        ghast.addProperty("weight", isPlayingModeDoom() ? 40 : 25);

        JsonObject magmaCube = new JsonObject();
        magmaCube.addProperty("type", "minecraft:magma_cube");
        magmaCube.addProperty("maxCount", 4);
        magmaCube.addProperty("minCount", 1);
        magmaCube.addProperty("weight", isPlayingModeDoom() ? 50 : 25);

        JsonObject piglinBrute = new JsonObject();
        piglinBrute.addProperty("type", "minecraft:piglin_brute");
        piglinBrute.addProperty("maxCount", 4);
        piglinBrute.addProperty("minCount", 1);
        piglinBrute.addProperty("weight", 25);

        JsonObject witherSkeleton = new JsonObject();
        witherSkeleton.addProperty("type", "minecraft:wither_skeleton");
        witherSkeleton.addProperty("maxCount", 4);
        witherSkeleton.addProperty("minCount", 1);
        witherSkeleton.addProperty("weight", 50);

        basaltDeltasMonsters.add(ghast);
        basaltDeltasMonsters.add(magmaCube);
        if (isPlayingModeDoom()) {
            basaltDeltasMonsters.add(piglinBrute);
            basaltDeltasMonsters.add(witherSkeleton);
        }

        basaltDeltasSpawners.getAsJsonArray("monster").addAll(basaltDeltasMonsters);
    }

    /**
     * Modifies the water color and monster spawns for the {@code crimson forest} biome.
     */
    public static void modifyCrimsonForest(JsonElement jsonElement) {
        JsonObject crimsonForestEffects = jsonElement.getAsJsonObject().getAsJsonObject("effects");
        crimsonForestEffects.addProperty("water_color", 0xCD6155);
        crimsonForestEffects.addProperty("water_fog_color", 0xE6B0AA);

        JsonObject crimsonForestSpawners = jsonElement.getAsJsonObject().getAsJsonObject("spawners");
        JsonArray crimsonForestMonsters = new JsonArray();

        JsonObject zombifiedPiglin = new JsonObject();
        zombifiedPiglin.addProperty("type", "minecraft:zombified_piglin");
        zombifiedPiglin.addProperty("maxCount", 1);
        zombifiedPiglin.addProperty("minCount", 1);
        zombifiedPiglin.addProperty("weight", 2);

        JsonObject hoglin = new JsonObject();
        hoglin.addProperty("type", "minecraft:hoglin");
        hoglin.addProperty("maxCount", isPlayingModeDoom() ? 6 : 4);
        hoglin.addProperty("minCount", isPlayingModeDoom() ? 4 : 1);
        hoglin.addProperty("weight", isPlayingModeDoom() ? 50 : 6);

        JsonObject piglin = new JsonObject();
        piglin.addProperty("type", "minecraft:piglin");
        piglin.addProperty("maxCount", isPlayingModeDoom() ? 6 : 4);
        piglin.addProperty("minCount", 2);
        piglin.addProperty("weight", isPlayingModeDoom() ? 25 : 9);

        JsonObject piglinBrute = new JsonObject();
        piglinBrute.addProperty("type", "minecraft:piglin_brute");
        piglinBrute.addProperty("maxCount", 4);
        piglinBrute.addProperty("minCount", 1);
        piglinBrute.addProperty("weight", 25);

        JsonObject magmaCube = new JsonObject();
        magmaCube.addProperty("type", "minecraft:magma_cube");
        magmaCube.addProperty("maxCount", 4);
        magmaCube.addProperty("minCount", 1);
        magmaCube.addProperty("weight", 50);

        crimsonForestMonsters.add(zombifiedPiglin);
        crimsonForestMonsters.add(hoglin);
        crimsonForestMonsters.add(piglin);
        if (isPlayingModeDoom()) {
            crimsonForestMonsters.add(piglinBrute);
            crimsonForestMonsters.add(magmaCube);
        }

        crimsonForestSpawners.getAsJsonArray("monster").addAll(crimsonForestMonsters);
    }

    /**
     * Modifies the water color and monster spawns for the {@code nether wastes} biome.
     */
    public static void modifyNetherWastes(JsonElement jsonElement) {
        JsonObject netherWastesEffects = jsonElement.getAsJsonObject().getAsJsonObject("effects");
        netherWastesEffects.addProperty("water_color", 0xD98880);
        netherWastesEffects.addProperty("water_fog_color", 0xE6B0AA);

        JsonObject netherWastesSpawners = jsonElement.getAsJsonObject().getAsJsonObject("spawners");
        JsonArray netherWastesMonsters = new JsonArray();

        JsonObject ghast = new JsonObject();
        ghast.addProperty("type", "minecraft:ghast");
        ghast.addProperty("maxCount", isPlayingModeDoom() ? 1 : 4);
        ghast.addProperty("minCount", 1);
        ghast.addProperty("weight", 20);

        JsonObject zombifiedPiglin = new JsonObject();
        zombifiedPiglin.addProperty("type", "minecraft:zombified_piglin");
        zombifiedPiglin.addProperty("maxCount", 4);
        zombifiedPiglin.addProperty("minCount", isPlayingModeDoom() ? 4 : 1);
        zombifiedPiglin.addProperty("weight", isPlayingModeDoom() ? 50 : 25);

        JsonObject magmaCube = new JsonObject();
        magmaCube.addProperty("type", "minecraft:magma_cube");
        magmaCube.addProperty("maxCount", 4);
        magmaCube.addProperty("minCount", 1);
        magmaCube.addProperty("weight", isPlayingModeDoom() ? 50 : 2);

        JsonObject enderman = new JsonObject();
        enderman.addProperty("type", "minecraft:enderman");
        enderman.addProperty("maxCount", 4);
        enderman.addProperty("minCount", 4);
        enderman.addProperty("weight", isPlayingModeDoom() ? 20 : 1);

        JsonObject piglin = new JsonObject();
        piglin.addProperty("type", "minecraft:piglin");
        piglin.addProperty("maxCount", isPlayingModeDoom() ? 2 : 4);
        piglin.addProperty("minCount", isPlayingModeDoom() ? 1 : 2);
        piglin.addProperty("weight", isPlayingModeDoom() ? 25 : 50);

        JsonObject piglinBrute = new JsonObject();
        piglinBrute.addProperty("type", "minecraft:piglin_brute");
        piglinBrute.addProperty("maxCount", 4);
        piglinBrute.addProperty("minCount", 1);
        piglinBrute.addProperty("weight", 25);

        JsonObject hoglin = new JsonObject();
        hoglin.addProperty("type", "minecraft:hoglin");
        hoglin.addProperty("maxCount", 4);
        hoglin.addProperty("minCount", 1);
        hoglin.addProperty("weight", 100);

        netherWastesMonsters.add(ghast);
        netherWastesMonsters.add(zombifiedPiglin);
        netherWastesMonsters.add(magmaCube);
        netherWastesMonsters.add(enderman);
        netherWastesMonsters.add(piglin);
        if (isPlayingModeDoom()) {
            netherWastesMonsters.add(piglinBrute);
            netherWastesMonsters.add(hoglin);
        }

        netherWastesSpawners.getAsJsonArray("monster").addAll(netherWastesMonsters);
    }

    /**
     * Modifies the water color and monster spawns for the {@code soul sand valley} biome.
     */
    public static void modifySoulSandValley(JsonElement jsonElement) {
        JsonObject soulSandValleyEffects = jsonElement.getAsJsonObject().getAsJsonObject("effects");
        soulSandValleyEffects.addProperty("water_color", 0xD98880);
        soulSandValleyEffects.addProperty("water_fog_color", 0xE6B0AA);

        JsonObject soulSandValleySpawners = jsonElement.getAsJsonObject().getAsJsonObject("spawners");
        JsonArray soulSandValleyMonsters = new JsonArray();

        JsonObject skeleton = new JsonObject();
        skeleton.addProperty("type", "minecraft:skeleton");
        skeleton.addProperty("maxCount", isPlayingModeDoom() ? 5 : 4);
        skeleton.addProperty("minCount", isPlayingModeDoom() ? 5 : 1);
        skeleton.addProperty("weight", isPlayingModeDoom() ? 50 : 10);

        JsonObject ghast = new JsonObject();
        ghast.addProperty("type", "minecraft:ghast");
        ghast.addProperty("maxCount", 4);
        ghast.addProperty("minCount", isPlayingModeDoom() ? 4 : 1);
        ghast.addProperty("weight", 50);

        JsonObject enderman = new JsonObject();
        enderman.addProperty("type", "minecraft:enderman");
        enderman.addProperty("maxCount", 4);
        enderman.addProperty("minCount", 4);
        enderman.addProperty("weight", isPlayingModeDoom() ? 10 : 5);

        JsonObject piglinBrute = new JsonObject();
        piglinBrute.addProperty("type", "minecraft:piglin_brute");
        piglinBrute.addProperty("maxCount", 4);
        piglinBrute.addProperty("minCount", 1);
        piglinBrute.addProperty("weight", 25);

        soulSandValleyMonsters.add(skeleton);
        soulSandValleyMonsters.add(ghast);
        soulSandValleyMonsters.add(enderman);
        if (isPlayingModeDoom()) {
            soulSandValleyMonsters.add(piglinBrute);
        }

        soulSandValleySpawners.getAsJsonArray("monster").addAll(soulSandValleyMonsters);
    }

    /**
     * Modifies the water color and monster spawns for the {@code warped forest} biome.
     */
    public static void modifyWarpedForest(JsonElement jsonElement) {
        JsonObject crimsonForestEffects = jsonElement.getAsJsonObject().getAsJsonObject("effects");
        crimsonForestEffects.addProperty("water_color", 0x167E86);
        crimsonForestEffects.addProperty("water_fog_color", 0x14B485);

        JsonObject warpedForestSpawners = jsonElement.getAsJsonObject().getAsJsonObject("spawners");
        JsonArray warpedForestMonsters = new JsonArray();

        JsonObject enderman = new JsonObject();
        enderman.addProperty("type", "minecraft:enderman");
        enderman.addProperty("maxCount", 4);
        enderman.addProperty("minCount", 4);
        enderman.addProperty("weight", 5);

        JsonObject piglin = new JsonObject();
        piglin.addProperty("type", "minecraft:piglin");
        piglin.addProperty("maxCount", 4);
        piglin.addProperty("minCount", isPlayingModeDoom() ? 4 : 1);
        piglin.addProperty("weight", isPlayingModeDoom() ? 25 : 5);

        JsonObject hoglin = new JsonObject();
        hoglin.addProperty("type", "minecraft:hoglin");
        hoglin.addProperty("maxCount", 4);
        hoglin.addProperty("minCount", 1);
        hoglin.addProperty("weight", 50);

        JsonObject piglinBrute = new JsonObject();
        piglinBrute.addProperty("type", "minecraft:piglin_brute");
        piglinBrute.addProperty("maxCount", 4);
        piglinBrute.addProperty("minCount", 1);
        piglinBrute.addProperty("weight", 25);

        JsonObject magmaCube = new JsonObject();
        magmaCube.addProperty("type", "minecraft:magma_cube");
        magmaCube.addProperty("maxCount", 4);
        magmaCube.addProperty("minCount", 1);
        magmaCube.addProperty("weight", 50);

        warpedForestMonsters.add(enderman);
        warpedForestMonsters.add(piglin);
        if (isPlayingModeDoom()) {
            warpedForestMonsters.add(hoglin);
            warpedForestMonsters.add(piglinBrute);
            warpedForestMonsters.add(magmaCube);
        }

        warpedForestSpawners.getAsJsonArray("monster").addAll(warpedForestMonsters);
    }
}