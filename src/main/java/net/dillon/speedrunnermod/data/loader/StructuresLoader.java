package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonElement;
import net.dillon.speedrunnermod.world.structure.*;

/**
 * Contains all of the {@code structure modifications,} making them generate more commonly.
 */
public class StructuresLoader {

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ancient cities.}
     */
    public static void modifyAncientCities(JsonElement jsonElement) {
        AncientCityConfig config = new AncientCityConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code desert pyramids.}
     */
    public static void modifyDesertPyramids(JsonElement jsonElement) {
        DesertPyramidConfig config = new DesertPyramidConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code end cities.}
     */
    public static void modifyEndCities(JsonElement jsonElement) {
        EndCityConfig config = new EndCityConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code jungle temples.}
     */
    public static void modifyJungleTemples(JsonElement jsonElement) {
        JunglePyramidConfig config = new JunglePyramidConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code frequency} value for {@code mineshafts.}
     */
    public static void modifyMineshafts(JsonElement jsonElement) {
        MineshaftConfig config = new MineshaftConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code frequency} value for {@code igloos.}
     */
    public static void modifyIgloos(JsonElement jsonElement) {
        IglooConfig config = new IglooConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code nether complexes} (nether fortresses and bastions).
     */
    public static void modifyNetherComplexes(JsonElement jsonElement) {
        NetherComplexesConfig config = new NetherComplexesConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code pillager outposts.}
     */
    public static void modifyPillagerOutposts(JsonElement jsonElement) {
        PillagerOutpostConfig config = new PillagerOutpostConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ruined portals.}
     */
    public static void modifyRuinedPortals(JsonElement jsonElement) {
        RuinedPortalConfig config = new RuinedPortalConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code shipwrecks.}
     */
    public static void modifyShipwrecks(JsonElement jsonElement) {
        ShipwreckConfig config = new ShipwreckConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ocean ruins.}
     */
    public static void modifyOceanRuins(JsonElement jsonElement) {
        OceanRuinConfig config = new OceanRuinConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code swamp huts.}
     */
    public static void modifySwampHuts(JsonElement jsonElement) {
        SwampHutConfig config = new SwampHutConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code distance,} {@code spread,} and {@code count} values for {@code strongholds.}
     */
    public static void modifyStrongholds(JsonElement jsonElement) {
        StrongholdConfig config = new StrongholdConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code trial chambers.}
     */
    public static void modifyTrialChambers(JsonElement jsonElement) {
        TrialChamberConfig config = new TrialChamberConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code trail ruins.}
     */
    public static void modifyTrailRuins(JsonElement jsonElement) {
        TrailRuinConfig config = new TrailRuinConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code villagers.}
     */
    public static void modifyVillages(JsonElement jsonElement) {
        VillageConfig config = new VillageConfig();
        config.configure(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code woodland mansions.}
     */
    public static void modifyWoodlandMansions(JsonElement jsonElement) {
        WoodlandMansionConfig config = new WoodlandMansionConfig();
        config.configure(jsonElement);
    }
}