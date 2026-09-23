package net.dillon.speedrunnermod.data;

import com.google.gson.JsonElement;
import net.dillon.speedrunnermod.option.StructureConfig;

import static net.dillon.speedrunnermod.main.CommonMain.common;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

/**
 * Contains all  {@code structure modifications}, making them generate more commonly.
 */
public record StructuresLoader(LoaderMain main) {
    public static int MODIFIED_STRUCTURES = 0;

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code abandoned camps.}
     */
    public void modifyAbandonedCamp() {
        configure(main().rootJsonElement(), common().structureConfigs().abandonedCamp);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ancient cities.}
     */
    public void modifyAncientCities() {
        configure(main().rootJsonElement(), common().structureConfigs().ancientCity);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code desert pyramids.}
     */
    public void modifyDesertPyramids() {
        configure(main().rootJsonElement(), common().structureConfigs().desertPyramid);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code end cities.}
     */
    public void modifyEndCities() {
        configure(main().rootJsonElement(), common().structureConfigs().endCity);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code jungle temples.}
     */
    public void modifyJunglePyramids() {
        configure(main().rootJsonElement(), common().structureConfigs().junglePyramid);
    }

    /**
     * Changes the {@code frequency} value for {@code mineshafts.}
     */
    public void modifyMineshafts() {
        main().rootJsonElement().getAsJsonObject().getAsJsonObject("placement").addProperty("frequency",
                common().structureConfigs().mineshaft.frequency() / 1000.0F
        );
        MODIFIED_STRUCTURES++;
    }

    /**
     * Changes the {@code frequency} value for {@code igloos.}
     */
    public void modifyIgloos() {
        configure(main().rootJsonElement(), common().structureConfigs().igloo);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code nether complexes} (nether fortresses and bastions).
     */
    public void modifyNetherComplexes() {
        configure(main().rootJsonElement(), common().structureConfigs().netherComplexes);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code pillager outposts.}
     */
    public void modifyPillagerOutposts() {
        configure(main().rootJsonElement(), common().structureConfigs().pillagerOutpost);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ruined portals.}
     */
    public void modifyRuinedPortals() {
        configure(main().rootJsonElement(), common().structureConfigs().ruinedPortal);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code shipwrecks.}
     */
    public void modifyShipwrecks() {
        configure(main().rootJsonElement(), common().structureConfigs().shipwreck);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ocean ruins.}
     */
    public void modifyOceanRuins() {
        configure(main().rootJsonElement(), common().structureConfigs().oceanRuin);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code swamp huts.}
     */
    public void modifySwampHuts() {
        configure(main().rootJsonElement(), common().structureConfigs().swampHut);
    }

    /**
     * Changes the {@code distance,} {@code spread,} and {@code count} values for {@code strongholds.}
     */
    public void modifyStrongholds() {
        if (!isDoomMode()) {
            main().rootJsonElement().getAsJsonObject().getAsJsonObject("placement").addProperty("distance", common().structureConfigs().stronghold.distance());
            main().rootJsonElement().getAsJsonObject().getAsJsonObject("placement").addProperty("spread", common().structureConfigs().stronghold.spread());
        }
        main().rootJsonElement().getAsJsonObject().getAsJsonObject("placement").addProperty("count", common().structureConfigs().stronghold.totalStrongholdsPerWorld());
        MODIFIED_STRUCTURES++;
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code trial chambers.}
     */
    public void modifyTrialChambers() {
        configure(main().rootJsonElement(), common().structureConfigs().trialChamber);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code trail ruins.}
     */
    public void modifyTrailRuins() {
        configure(main().rootJsonElement(), common().structureConfigs().trailRuin);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code villagers.}
     */
    public void modifyVillages() {
        configure(main().rootJsonElement(), common().structureConfigs().village);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code woodland mansions.}
     */
    public void modifyWoodlandMansions() {
        configure(main().rootJsonElement(), common().structureConfigs().woodlandMansion);
    }

    /**
     * Configures the structure spawn rate {@code spacing} and {@code separation} values.
     */
    private void configure(JsonElement element, StructureConfig structureConfig) {
        element.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", structureConfig.spacing());
        element.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", structureConfig.separation());
        MODIFIED_STRUCTURES++;
    }
}