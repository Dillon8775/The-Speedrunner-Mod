package net.dillon.speedrunnermod.data.loader;

import com.google.gson.JsonElement;
import net.dillon.speedrunnermod.util.AI;
import net.dillon.speedrunnermod.world.structure.*;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Contains all of the {@code structure modifications,} making them generate more commonly.
 */
@AI
public class StructuresLoader {

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ancient cities.}
     */
    public static void modifyAncientCities(JsonElement jsonElement) {
        AncientCityConfig config = new AncientCityConfig();
        config.configurate(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code desert pyramids.}
     */
    public static void modifyDesertPyramids(JsonElement jsonElement) {
        DesertPyramidConfig config = new DesertPyramidConfig();
        config.configurate(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code end cities.}
     */
    public static void modifyEndCities(JsonElement jsonElement) {
        EndCityConfig config = new EndCityConfig();
        config.configurate(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code jungle temples.}
     */
    public static void modifyJungleTemples(JsonElement jsonElement) {
        JunglePyramidConfig config = new JunglePyramidConfig();
        config.configurate(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code nether complexes} (nether fortresses and bastions).
     */
    public static void modifyNetherComplexes(JsonElement jsonElement) {
        NetherComplexesConfig config = new NetherComplexesConfig();
        config.configurate(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code pillager outposts.}
     */
    public static void modifyPillagerOutposts(JsonElement jsonElement) {
        PillagerOutpostConfig config = new PillagerOutpostConfig();
        config.configurate(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code ruined portals.}
     */
    public static void modifyRuinedPortals(JsonElement jsonElement) {
        RuinedPortalConfig config = new RuinedPortalConfig();
        config.configurate(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code shipwrecks.}
     */
    public static void modifyShipwrecks(JsonElement jsonElement) {
        ShipwreckConfig config = new ShipwreckConfig();
        config.configurate(jsonElement);
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
        TrialChamberConfig config = new TrialChamberConfig();
        config.configurate(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code villagers.}
     */
    public static void modifyVillages(JsonElement jsonElement) {
        VillageConfig config = new VillageConfig();
        config.configurate(jsonElement);
    }

    /**
     * Changes the {@code spacing} and {@code separation} values for {@code woodland mansions.}
     */
    public static void modifyWoodlandMansions(JsonElement jsonElement) {
        WoodlandMansionConfig config = new WoodlandMansionConfig();
        config.configurate(jsonElement);
    }
}