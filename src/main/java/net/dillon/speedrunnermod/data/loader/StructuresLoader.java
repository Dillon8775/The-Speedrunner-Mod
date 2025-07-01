package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonElement;
import net.dillon.speedrunnermod.mixin.main.registry.RegistryLoaderMixin;
import net.dillon.speedrunnermod.util.AI;
import net.dillon.speedrunnermod.world.ModWorldGen;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.*;

/**
 * Contains all of the {@code structure modifications,} making them generate more commonly.
 */
@AI
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
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("distance", options().main.strongholdDistance.getCurrentValue());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("spread", options().main.strongholdSpread.getCurrentValue());
        jsonElement.getAsJsonObject().getAsJsonObject("placement").addProperty("count", options().main.strongholdCount.getCurrentValue());
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
        if (isStructureSpawnRatesEverywhere()) {
            return 4;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 10;
        } else if (isStructureSpawnRatesCommon()) {
            return 16;
        } else if (isStructureSpawnRatesNormal() || isStructureSpawnRatesDefault()) {
            return 24;
        } else if (isStructureSpawnRatesRare()) {
            return 28;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 32;
        } else {
            return options().structureSpawnRates.ancientCities.getCurrentValue().getFirst();
        }
    }

    private static int ancientCitySeparation() {
        if (isStructureSpawnRatesEverywhere()) {
            return 3;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 7;
        } else if (isStructureSpawnRatesCommon() || isStructureSpawnRatesNormal() || isStructureSpawnRatesDefault()) {
            return 8;
        } else if (isStructureSpawnRatesRare()) {
            return 12;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 16;
        } else {
            return options().structureSpawnRates.ancientCities.getCurrentValue().get(1);
        }
    }

    private static int villageSpacing() {
        if (isStructureSpawnRatesEverywhere()) {
            return 4;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 10;
        } else if (isStructureSpawnRatesCommon()) {
            return 16;
        } else if (isStructureSpawnRatesNormal()) {
            return 20;
        } else if (isStructureSpawnRatesDefault()) {
            return 32;
        } else if (isStructureSpawnRatesRare()) {
            return 42;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 52;
        } else {
            return options().structureSpawnRates.villages.getCurrentValue().getFirst();
        }
    }

    private static int villageSeparation() {
        if (isStructureSpawnRatesEverywhere()) {
            return 2;
        } else if (isStructureSpawnRatesCommon()) {
            return 9;
        } else if (isStructureSpawnRatesCommonNormalOrDefault()) {
            return 8;
        } else if (isStructureSpawnRatesRare()) {
            return 10;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 16;
        } else {
            return options().structureSpawnRates.villages.getCurrentValue().get(1);
        }
    }

    private static int desertPyramidSpacing() {
        if (isStructureSpawnRatesEverywhere()) {
            return 3;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 8;
        } else if (isStructureSpawnRatesCommon()) {
            return 10;
        } else if (isStructureSpawnRatesNormal()) {
            return 20;
        } else if (isStructureSpawnRatesDefault()) {
            return 32;
        } else if (isStructureSpawnRatesRare()) {
            return 42;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 52;
        } else {
            return options().structureSpawnRates.desertPyramids.getCurrentValue().getFirst();
        }
    }

    private static int desertPyramidSeparation() {
        if (isStructureSpawnRatesEverywhere()) {
            return 2;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 7;
        } else if (isStructureSpawnRatesCommonNormalOrDefault()) {
            return 8;
        } else if (isStructureSpawnRatesRare()) {
            return 10;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 16;
        } else {
            return options().structureSpawnRates.desertPyramids.getCurrentValue().get(1);
        }
    }

    private static int jungleTempleSpacing() {
        if (isStructureSpawnRatesEverywhere()) {
            return 3;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 8;
        } else if (isStructureSpawnRatesCommon()) {
            return 10;
        } else if (isStructureSpawnRatesNormal()) {
            return 20;
        } else if (isStructureSpawnRatesDefault()) {
            return 32;
        } else if (isStructureSpawnRatesRare()) {
            return 40;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 50;
        } else {
            return options().structureSpawnRates.junglePyramids.getCurrentValue().getFirst();
        }
    }

    private static int jungleTempleSeparation() {
        if (isStructureSpawnRatesEverywhere()) {
            return 2;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 7;
        } else if (isStructureSpawnRatesCommonNormalOrDefault()) {
            return 8;
        } else if (isStructureSpawnRatesRare()) {
            return 10;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 12;
        } else {
            return options().structureSpawnRates.junglePyramids.getCurrentValue().get(1);
        }
    }

    private static int pillagerOutpostSpacing() {
        if (isStructureSpawnRatesEverywhere()) {
            return 3;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 8;
        } else if (isStructureSpawnRatesCommon()) {
            return 10;
        } else if (isStructureSpawnRatesNormal()) {
            return 20;
        } else if (isStructureSpawnRatesDefault()) {
            return 32;
        } else if (isStructureSpawnRatesRare()) {
            return 40;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 50;
        } else {
            return options().structureSpawnRates.pillagerOutposts.getCurrentValue().getFirst();
        }
    }

    private static int pillagerOutpostSeparation() {
        if (isStructureSpawnRatesEverywhere()) {
            return 2;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 7;
        } else if (isStructureSpawnRatesCommonNormalOrDefault()) {
            return 8;
        } else if (isStructureSpawnRatesRare()) {
            return 10;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 12;
        } else {
            return options().structureSpawnRates.pillagerOutposts.getCurrentValue().get(1);
        }
    }

    private static int endCitySpacing() {
        if (isStructureSpawnRatesEverywhere()) {
            return 4;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 5;
        } else if (isStructureSpawnRatesCommon()) {
            return 7;
        } else if (isStructureSpawnRatesNormal()) {
            return 15;
        } else if (isStructureSpawnRatesDefault()) {
            return 20;
        } else if (isStructureSpawnRatesRare()) {
            return 25;
        } else {
            return options().structureSpawnRates.endCities.getCurrentValue().getFirst();
        }
    }

    private static int endCitySeparation() {
        if (isStructureSpawnRatesEverywhere()) {
            return 2;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 4;
        } else if (isStructureSpawnRatesCommon()) {
            return 6;
        } else if (isStructureSpawnRatesNormal()) {
            return 10;
        } else if (isStructureSpawnRatesDefault()) {
            return 11;
        } else if (isStructureSpawnRatesRare()) {
            return 16;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 18;
        } else {
            return options().structureSpawnRates.endCities.getCurrentValue().get(1);
        }
    }

    private static int woodlandMansionSpacing() {
        if (isStructureSpawnRatesEverywhere()) {
            return 6;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 16;
        } else if (isStructureSpawnRatesCommon()) {
            return 25;
        } else if (isStructureSpawnRatesNormal()) {
            return 40;
        } else if (isStructureSpawnRatesDefault()) {
            return 80;
        } else if (isStructureSpawnRatesRare()) {
            return 100;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 120;
        } else {
            return options().structureSpawnRates.woodlandMansions.getCurrentValue().getFirst();
        }
    }

    private static int woodlandMansionSeparation() {
        if (isStructureSpawnRatesEverywhere()) {
            return 4;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 8;
        } else if (isStructureSpawnRatesCommonNormalOrDefault() || isStructureSpawnRatesRare()) {
            return 20;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 25;
        } else {
            return options().structureSpawnRates.woodlandMansions.getCurrentValue().get(1);
        }
    }

    private static int ruinedPortalSpacing() {
        if (isStructureSpawnRatesEverywhere()) {
            return 4;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 7;
        } else if (isStructureSpawnRatesCommon()) {
            return 9;
        } else if (isStructureSpawnRatesNormal()) {
            return 16;
        } else if (isStructureSpawnRatesDefault()) {
            return 40;
        } else if (isStructureSpawnRatesRare()) {
            return 50;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 60;
        } else {
            return options().structureSpawnRates.ruinedPortals.getCurrentValue().getFirst();
        }
    }

    private static int ruinedPortalSeparation() {
        if (isStructureSpawnRatesEverywhere()) {
            return 2;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 6;
        } else if (isStructureSpawnRatesCommon()) {
            return 8;
        } else if (isStructureSpawnRatesNormal()) {
            return 9;
        } else if (isStructureSpawnRatesDefault()) {
            return 15;
        } else if (isStructureSpawnRatesRare()) {
            return 16;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 20;
        } else {
            return options().structureSpawnRates.ruinedPortals.getCurrentValue().get(1);
        }
    }

    private static int shipwreckSpacing() {
        if (isStructureSpawnRatesEverywhere()) {
            return 3;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 8;
        } else if (isStructureSpawnRatesCommon()) {
            return 10;
        } else if (isStructureSpawnRatesNormal()) {
            return 20;
        } else if (isStructureSpawnRatesDefault()) {
            return 24;
        } else if (isStructureSpawnRatesRare()) {
            return 30;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 40;
        } else {
            return options().structureSpawnRates.shipwrecks.getCurrentValue().getFirst();
        }
    }

    private static int shipwreckSeparation() {
        if (isStructureSpawnRatesEverywhere()) {
            return 2;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 7;
        } else if (isStructureSpawnRatesCommon() ||
                isStructureSpawnRatesNormal()) {
            return 8;
        } else if (isStructureSpawnRatesDefault()) {
            return 4;
        } else if (isStructureSpawnRatesRare()) {
            return 9;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 10;
        } else {
            return options().structureSpawnRates.shipwrecks.getCurrentValue().get(1);
        }
    }

    private static int trialChambersSpacing() {
        if (isStructureSpawnRatesEverywhere()) {
            return 5;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 8;
        } else if (isStructureSpawnRatesCommon()) {
            return 12;
        } else if (isStructureSpawnRatesNormal()) {
            return 20;
        } else if (isStructureSpawnRatesDefault()) {
            return 34;
        } else if (isStructureSpawnRatesRare()) {
            return 40;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 45;
        } else {
            return options().structureSpawnRates.trialChambers.getCurrentValue().getFirst();
        }
    }

    private static int trialChambersSeparation() {
        if (isStructureSpawnRatesEverywhere()) {
            return 3;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 7;
        } else if (isStructureSpawnRatesCommon() ||
                isStructureSpawnRatesNormal()) {
            return 8;
        } else if (isStructureSpawnRatesDefault()) {
            return 12;
        } else if (isStructureSpawnRatesRare()) {
            return 16;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 20;
        } else {
            return options().structureSpawnRates.trialChambers.getCurrentValue().get(1);
        }
    }

    private static int netherComplexesSpacing() {
        if (isStructureSpawnRatesEverywhere()) {
            return 5;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 7;
        } else if (isStructureSpawnRatesCommon()) {
            return 10;
        } else if (isStructureSpawnRatesNormal()) {
            return 17;
        } else if (isStructureSpawnRatesDefault()) {
            return 30;
        } else if (isStructureSpawnRatesRare()) {
            return 40;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 50;
        } else {
            return options().structureSpawnRates.netherComplexes.getCurrentValue().getFirst();
        }
    }

    private static int netherComplexesSeparation() {
        if (isStructureSpawnRatesEverywhere()) {
            return 3;
        } else if (isStructureSpawnRatesVeryCommon()) {
            return 7;
        } else if (isStructureSpawnRatesCommon()) {
            return 8;
        } else if (isStructureSpawnRatesNormal() ||
                isStructureSpawnRatesRare()) {
            return 10;
        } else if (isStructureSpawnRatesDefault()) {
            return 4;
        } else if (isStructureSpawnRatesVeryRare()) {
            return 14;
        } else {
            return options().structureSpawnRates.netherComplexes.getCurrentValue().get(1);
        }
    }
}