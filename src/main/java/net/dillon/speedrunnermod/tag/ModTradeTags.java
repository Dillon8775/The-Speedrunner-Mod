package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;

import static net.dillon.dillonlib.factory.Factories.createVillagerTradeTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod villager trade tags.
 */
public class ModTradeTags {
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_1 = createVillagerTradeTag(ofSpeedrunnerMod("retired_speedrunner/level_1"));
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_2 = createVillagerTradeTag(ofSpeedrunnerMod("retired_speedrunner/level_2"));
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_3 = createVillagerTradeTag(ofSpeedrunnerMod("retired_speedrunner/level_3"));
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_4 = createVillagerTradeTag(ofSpeedrunnerMod("retired_speedrunner/level_4"));
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_5 = createVillagerTradeTag(ofSpeedrunnerMod("retired_speedrunner/level_5"));

    /**
     * Initializes all Speedrunner Mod {@code trade tags.}
     */
    public static void initializeTradeTags() {
        SpeedrunnerMod.LOGGER.debug("Initialized trade tags.");
    }
}