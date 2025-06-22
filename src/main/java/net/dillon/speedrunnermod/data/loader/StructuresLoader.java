package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonElement;
import net.dillon.speedrunnermod.mixin.main.registry.RegistryLoaderMixin;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.world.ModWorldGen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Contains all of the {@code structure modifications,} making them generate more commonly.
 */
@ChatGPT(Credit.FULL_CREDIT)
public class StructuresLoader {

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ancient cities.}
     */
    public static void modifyAncientCities(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", ancientCitySpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", ancientCitySeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code desert pyramids.}
     */
    public static void modifyDesertPyramids(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", desertPyramidSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", desertPyramidSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code end cities.}
     */
    public static void modifyEndCities(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", endCitySpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", endCitySeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code jungle temples.}
     */
    public static void modifyJungleTemples(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", jungleTempleSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", jungleTempleSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code nether complexes} (nether fortresses and bastions).
     */
    public static void modifyNetherComplexes(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", netherComplexesSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", netherComplexesSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code pillager outposts.}
     */
    public static void modifyPillagerOutposts(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", pillagerOutpostSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", pillagerOutpostSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ruined portals.}
     */
    public static void modifyRuinedPortals(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", ruinedPortalSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", ruinedPortalSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code shipwrecks.}
     */
    public static void modifyShipwrecks(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", shipwreckSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", shipwreckSeparation());
    }

    /**
     * Changes the {@code distance,} {@code spread,} and {@code count} values for {@code strongholds.}
     * <p>Distance - how close strongholds can generate to spawn. </p>
     * <p>Spread - how far apart strongholds can generate from each other.</p>
     * <p>Count - the total amount of strongholds that are allowed to create in a single Minecraft world.</p>
     */
    public static void modifyStrongholds(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("distance", options().main.strongholdDistance);
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spread", options().main.strongholdSpread);
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("count", options().main.strongholdCount);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code trial chambers.}
     */
    public static void modifyTrialChambers(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", trialChambersSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", trialChambersSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code villagers.}
     */
    public static void modifyVillages(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", villageSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", villageSeparation());
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code woodland mansions.}
     */
    public static void modifyWoodlandMansions(JsonElement jsonElement) {
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", woodlandMansionSpacing());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", woodlandMansionSeparation());
    }

    /**
     * See {@link ModWorldGen} and {@link RegistryLoaderMixin} for more.
     */
    private static int ancientCitySpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 10;
        } else if (options().main.structureSpawnRates.common()) {
            return 16;
        } else if (options().main.structureSpawnRates.normal() || options().main.structureSpawnRates.ddefault()) {
            return 24;
        } else if (options().main.structureSpawnRates.rare()) {
            return 28;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 32;
        } else {
            return options().structureSpawnRates.ancientCities[0];
        }
    }

    private static int ancientCitySeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.common() || options().main.structureSpawnRates.normal() || options().main.structureSpawnRates.ddefault()) {
            return 8;
        } else if (options().main.structureSpawnRates.rare()) {
            return 12;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 16;
        } else {
            return options().structureSpawnRates.ancientCities[1];
        }
    }

    private static int villageSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 10;
        } else if (options().main.structureSpawnRates.common()) {
            return 16;
        } else if (options().main.structureSpawnRates.normal()) {
            return 20;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 32;
        } else if (options().main.structureSpawnRates.rare()) {
            return 42;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 52;
        } else {
            return options().structureSpawnRates.villages[0];
        }
    }

    private static int villageSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.common()) {
            return 9;
        } else if (options().main.structureSpawnRates.commonNormalOrDefault()) {
            return 8;
        } else if (options().main.structureSpawnRates.rare()) {
            return 10;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 16;
        } else {
            return options().structureSpawnRates.villages[1];
        }
    }

    private static int desertPyramidSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 8;
        } else if (options().main.structureSpawnRates.common()) {
            return 10;
        } else if (options().main.structureSpawnRates.normal()) {
            return 20;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 32;
        } else if (options().main.structureSpawnRates.rare()) {
            return 42;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 52;
        } else {
            return options().structureSpawnRates.desertPyramids[0];
        }
    }

    private static int desertPyramidSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.commonNormalOrDefault()) {
            return 8;
        } else if (options().main.structureSpawnRates.rare()) {
            return 10;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 16;
        } else {
            return options().structureSpawnRates.desertPyramids[1];
        }
    }

    private static int jungleTempleSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 8;
        } else if (options().main.structureSpawnRates.common()) {
            return 10;
        } else if (options().main.structureSpawnRates.normal()) {
            return 20;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 32;
        } else if (options().main.structureSpawnRates.rare()) {
            return 40;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 50;
        } else {
            return options().structureSpawnRates.junglePyramids[0];
        }
    }

    private static int jungleTempleSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.commonNormalOrDefault()) {
            return 8;
        } else if (options().main.structureSpawnRates.rare()) {
            return 10;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 12;
        } else {
            return options().structureSpawnRates.junglePyramids[1];
        }
    }

    private static int pillagerOutpostSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 8;
        } else if (options().main.structureSpawnRates.common()) {
            return 10;
        } else if (options().main.structureSpawnRates.normal()) {
            return 20;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 32;
        } else if (options().main.structureSpawnRates.rare()) {
            return 40;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 50;
        } else {
            return options().structureSpawnRates.pillagerOutposts[0];
        }
    }

    private static int pillagerOutpostSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.commonNormalOrDefault()) {
            return 8;
        } else if (options().main.structureSpawnRates.rare()) {
            return 10;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 12;
        } else {
            return options().structureSpawnRates.pillagerOutposts[1];
        }
    }

    private static int endCitySpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 5;
        } else if (options().main.structureSpawnRates.common()) {
            return 7;
        } else if (options().main.structureSpawnRates.normal()) {
            return 15;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 20;
        } else if (options().main.structureSpawnRates.rare()) {
            return 25;
        } else {
            return options().structureSpawnRates.endCities[0];
        }
    }

    private static int endCitySeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 4;
        } else if (options().main.structureSpawnRates.common()) {
            return 6;
        } else if (options().main.structureSpawnRates.normal()) {
            return 10;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 11;
        } else if (options().main.structureSpawnRates.rare()) {
            return 16;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 18;
        } else {
            return options().structureSpawnRates.endCities[1];
        }
    }

    private static int woodlandMansionSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 6;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 16;
        } else if (options().main.structureSpawnRates.common()) {
            return 25;
        } else if (options().main.structureSpawnRates.normal()) {
            return 40;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 80;
        } else if (options().main.structureSpawnRates.rare()) {
            return 100;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 120;
        } else {
            return options().structureSpawnRates.woodlandMansions[0];
        }
    }

    private static int woodlandMansionSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 8;
        } else if (options().main.structureSpawnRates.commonNormalOrDefault() || options().main.structureSpawnRates.rare()) {
            return 20;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 25;
        } else {
            return options().structureSpawnRates.woodlandMansions[1];
        }
    }

    private static int ruinedPortalSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.common()) {
            return 9;
        } else if (options().main.structureSpawnRates.normal()) {
            return 16;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 40;
        } else if (options().main.structureSpawnRates.rare()) {
            return 50;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 60;
        } else {
            return options().structureSpawnRates.ruinedPortals[0];
        }
    }

    private static int ruinedPortalSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 6;
        } else if (options().main.structureSpawnRates.common()) {
            return 8;
        } else if (options().main.structureSpawnRates.normal()) {
            return 9;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 15;
        } else if (options().main.structureSpawnRates.rare()) {
            return 16;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 20;
        } else {
            return options().structureSpawnRates.ruinedPortals[1];
        }
    }

    private static int shipwreckSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 8;
        } else if (options().main.structureSpawnRates.common()) {
            return 10;
        } else if (options().main.structureSpawnRates.normal()) {
            return 20;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 24;
        } else if (options().main.structureSpawnRates.rare()) {
            return 30;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 40;
        } else {
            return options().structureSpawnRates.shipwrecks[0];
        }
    }

    private static int shipwreckSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 2;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.common() ||
                options().main.structureSpawnRates.normal()) {
            return 8;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 4;
        } else if (options().main.structureSpawnRates.rare()) {
            return 9;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 10;
        } else {
            return options().structureSpawnRates.shipwrecks[1];
        }
    }

    private static int trialChambersSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 5;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 8;
        } else if (options().main.structureSpawnRates.common()) {
            return 12;
        } else if (options().main.structureSpawnRates.normal()) {
            return 20;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 34;
        } else if (options().main.structureSpawnRates.rare()) {
            return 40;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 45;
        } else {
            return options().structureSpawnRates.trialChambers[0];
        }
    }

    private static int trialChambersSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.common() ||
                options().main.structureSpawnRates.normal()) {
            return 8;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 12;
        } else if (options().main.structureSpawnRates.rare()) {
            return 16;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 20;
        } else {
            return options().structureSpawnRates.trialChambers[1];
        }
    }

    private static int netherComplexesSpacing() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 5;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.common()) {
            return 10;
        } else if (options().main.structureSpawnRates.normal()) {
            return 17;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 30;
        } else if (options().main.structureSpawnRates.rare()) {
            return 40;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 50;
        } else {
            return options().structureSpawnRates.netherComplexes[0];
        }
    }

    private static int netherComplexesSeparation() {
        if (options().main.structureSpawnRates.everywhere()) {
            return 3;
        } else if (options().main.structureSpawnRates.veryCommon()) {
            return 7;
        } else if (options().main.structureSpawnRates.common()) {
            return 8;
        } else if (options().main.structureSpawnRates.normal() ||
                options().main.structureSpawnRates.rare()) {
            return 10;
        } else if (options().main.structureSpawnRates.ddefault()) {
            return 4;
        } else if (options().main.structureSpawnRates.veryRare()) {
            return 14;
        } else {
            return options().structureSpawnRates.netherComplexes[1];
        }
    }
}