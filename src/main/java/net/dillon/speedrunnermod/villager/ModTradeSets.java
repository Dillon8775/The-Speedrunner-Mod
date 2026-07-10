package net.dillon.speedrunnermod.villager;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSet;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod {@code trade sets,} which are a set of trades for each villager level.
 */
public class ModTradeSets {
    public static final ResourceKey<TradeSet> RETIRED_SPEEDRUNNER_LEVEL_1 = createTradeSet("retired_speedrunner/level_1");
    public static final ResourceKey<TradeSet> RETIRED_SPEEDRUNNER_LEVEL_2 = createTradeSet("retired_speedrunner/level_2");
    public static final ResourceKey<TradeSet> RETIRED_SPEEDRUNNER_LEVEL_3 = createTradeSet("retired_speedrunner/level_3");
    public static final ResourceKey<TradeSet> RETIRED_SPEEDRUNNER_LEVEL_4 = createTradeSet("retired_speedrunner/level_4");
    public static final ResourceKey<TradeSet> RETIRED_SPEEDRUNNER_LEVEL_5 = createTradeSet("retired_speedrunner/level_5");

    /**
     * Registers a {@code trade set tag.}
     */
    private static ResourceKey<TradeSet> createTradeSet(String name) {
        return ResourceKey.create(Registries.TRADE_SET, ofSpeedrunnerMod(name));
    }

    /**
     * Initializes all speedrunner mod trade sets.
     */
    public static void initializeTradeSets() {
        SpeedrunnerMod.debug("Initialized trade sets.");
    }
}