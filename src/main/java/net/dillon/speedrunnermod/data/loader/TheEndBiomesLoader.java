package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.dillon.speedrunnermod.util.AI;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * Contains all of the {@code end biome modifications.}
 */
@AI
public class TheEndBiomesLoader {

    /**
     * Modifies the end biome and it's entity spawns.
     */
    public static void modifyTheEnd(JsonElement jsonElement) {
        JsonObject theEndSpawners = jsonElement.getAsJsonObject().getAsJsonObject("spawners");
        JsonArray theEndMonsters = new JsonArray();

        JsonObject enderman = new JsonObject();
        enderman.addProperty("type", "minecraft:enderman");
        enderman.addProperty("maxCount", 4);
        enderman.addProperty("minCount", 1);
        enderman.addProperty("weight", isDoomMode() ? 85 : 10);

        JsonObject skeleton = new JsonObject();
        skeleton.addProperty("type", "minecraft:skeleton");
        skeleton.addProperty("maxCount", 4);
        skeleton.addProperty("minCount", 1);
        skeleton.addProperty("weight", 70);

        JsonObject vindicator = new JsonObject();
        vindicator.addProperty("type", "minecraft:vindicator");
        vindicator.addProperty("maxCount", 2);
        vindicator.addProperty("minCount", 1);
        vindicator.addProperty("weight", 65);

        JsonObject zombie = new JsonObject();
        zombie.addProperty("type", "minecraft:zombie");
        zombie.addProperty("maxCount", 1);
        zombie.addProperty("minCount", 1);
        zombie.addProperty("weight", 50);

        JsonObject ravager = new JsonObject();
        ravager.addProperty("type", "minecraft:ravager");
        ravager.addProperty("maxCount", 1);
        ravager.addProperty("minCount", 1);
        ravager.addProperty("weight", 40);

        JsonObject evoker = new JsonObject();
        evoker.addProperty("type", "minecraft:evoker");
        evoker.addProperty("maxCount", 1);
        evoker.addProperty("minCount", 1);
        evoker.addProperty("weight", 25);

        JsonObject breeze = new JsonObject();
        breeze.addProperty("type", "minecraft:breeze");
        breeze.addProperty("maxCount", 1);
        breeze.addProperty("minCount", 1);
        breeze.addProperty("weight", 25);

        theEndMonsters.add(enderman);
        if (isDoomMode()) {
            theEndMonsters.add(skeleton);
            theEndMonsters.add(vindicator);
            theEndMonsters.add(ravager);
            theEndMonsters.add(evoker);
            theEndMonsters.add(zombie);
            theEndMonsters.add(breeze);
        }

        theEndSpawners.getAsJsonArray("monster").addAll(theEndMonsters);

        JsonObject endBiome = jsonElement.getAsJsonObject();
        JsonObject attributes = new JsonObject();

        JsonArray ambientParticles;
        ambientParticles = new JsonArray();
        attributes.add("minecraft:visual/ambient_particles", ambientParticles);

        JsonObject particleEntry = new JsonObject();
        JsonObject particleObject = new JsonObject();
        particleObject.addProperty("type", "minecraft:crimson_spore");
        particleEntry.add("particle", particleObject);
        particleEntry.addProperty("probability", 0.030);

        if (isDoomMode()) {
            ambientParticles.add(particleEntry);
            endBiome.add("attributes", attributes);
        }
    }

    /**
     * Modifies the {@code end noise settings.}
     */
    public static void modifyEnd(JsonElement jsonElement) {
        String stone = isDoomMode() ? "speedrunnermod:doom_stone" : "minecraft:end_stone";
        JsonObject defaultBlock = jsonElement.getAsJsonObject().getAsJsonObject("default_block");
        defaultBlock.addProperty("Name", stone);

        JsonObject surfaceRule = jsonElement.getAsJsonObject().getAsJsonObject("surface_rule");
        JsonObject resultState = surfaceRule.getAsJsonObject("result_state");
        resultState.addProperty("Name", stone);
    }
}