package net.dillon.speedrunnermod.villager;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.tag.ModTradeTags;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.TradeSets;

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

    public static Holder<TradeSet> bootstrap(BootstrapContext<TradeSet> bootstrapContext) {
        TradeSets.register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_1, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_1);
        TradeSets.register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_2, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_2);
        TradeSets.register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_3, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_3);
        TradeSets.register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_4, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_4);
        TradeSets.register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_5, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_5);
        return null;
    }

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
        SpeedrunnerMod.LOGGER.debug("Initialized trade sets.");
    }
}