package net.dillon.speedrunnermod.data;

import com.google.gson.JsonElement;
import net.dillon.speedrunnermod.option.StructureConfig;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

/**
 * Contains all of the {@code structure modifications,} making them generate more commonly.
 */
public class StructuresLoader {

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ancient cities.}
     */
    public static void modifyAncientCities(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().ancientCity);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code desert pyramids.}
     */
    public static void modifyDesertPyramids(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().desertPyramid);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code end cities.}
     */
    public static void modifyEndCities(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().endCity);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code jungle temples.}
     */
    public static void modifyJunglePyramids(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().junglePyramid);
    }

    /**
     * Changes the {@code frequency} value for {@code mineshafts.}
     */
    public static void modifyMineshafts(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("frequency",
                common().structureConfigs().mineshaft.frequency() / 1000.0F
        );
    }

    /**
     * Changes the {@code frequency} value for {@code igloos.}
     */
    public static void modifyIgloos(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().igloo);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code nether complexes} (nether fortresses and bastions).
     */
    public static void modifyNetherComplexes(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().netherComplexes);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code pillager outposts.}
     */
    public static void modifyPillagerOutposts(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().pillagerOutpost);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ruined portals.}
     */
    public static void modifyRuinedPortals(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().ruinedPortal);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code shipwrecks.}
     */
    public static void modifyShipwrecks(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().shipwreck);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ocean ruins.}
     */
    public static void modifyOceanRuins(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().oceanRuin);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code swamp huts.}
     */
    public static void modifySwampHuts(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().swampHut);
    }

    /**
     * Changes the {@code distance,} {@code spread,} and {@code count} values for {@code strongholds.}
     */
    public static void modifyStrongholds(JsonElement jsonElement) {
        if (!isDoomMode()) {
            jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("distance", common().structureConfigs().stronghold.distance());
            jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spread", common().structureConfigs().stronghold.spread());
        }
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("count", common().structureConfigs().stronghold.totalStrongholdsPerWorld());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code trial chambers.}
     */
    public static void modifyTrialChambers(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().trialChamber);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code trail ruins.}
     */
    public static void modifyTrailRuins(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().trailRuin);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code villagers.}
     */
    public static void modifyVillages(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().village);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code woodland mansions.}
     */
    public static void modifyWoodlandMansions(JsonElement jsonElement) {
        configure(jsonElement, common().structureConfigs().woodlandMansion);
    }

    /**
     * Configures the structure spawn rate {@code spacing} and {@code separation} values.
     */
    private static void configure(JsonElement element, StructureConfig structureConfig) {
        element.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", structureConfig.spacing());
        element.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", structureConfig.separation());
    }
}