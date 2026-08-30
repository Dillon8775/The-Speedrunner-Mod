package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.tag.ModTradeTags;
import net.dillon.speedrunnermod.villager.ModTradeSets;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.TradeSets;

/**
 * Generates all speedrunner mod {@code villager trade sets,} which are sets of villager trades, for each villager profession's level.
 */
@Author(Authors.SAMEDDIFFERENT)
public class ModTradeSetProvider {

    public static Holder<TradeSet> bootstrap(BootstrapContext<TradeSet> bootstrapContext) {
        TradeSets.register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_1, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_1);
        TradeSets.register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_2, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_2);
        TradeSets.register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_3, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_3);
        TradeSets.register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_4, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_4);
        TradeSets.register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_5, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_5);
        return null;
    }
}