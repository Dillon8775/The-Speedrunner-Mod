package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.tag.ModTradeTags;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.villager.ModTradeSets;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.TradeSet;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;

import java.util.Optional;

/**
 * Generates all speedrunner mod {@code villager trade sets,} which are sets of villager trades, for each villager profession's level.
 */
@Author(Authors.SAMEDDIFFERENT)
public class ModTradeSetGenerator {

    public static Holder<TradeSet> bootstrap(BootstrapContext<TradeSet> bootstrapContext) {
        register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_1, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_1);
        register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_2, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_2);
        register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_3, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_3);
        register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_4, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_4);
        register(bootstrapContext, ModTradeSets.RETIRED_SPEEDRUNNER_LEVEL_5, ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_5);
        return null;
    }

    /**
     * Registers a villager trade set.
     */
    public static Holder.Reference<TradeSet> register(final BootstrapContext<TradeSet> context, final ResourceKey<TradeSet> resourceKey, final TagKey<VillagerTrade> tradeTag) {
        return register(context, resourceKey, tradeTag, ConstantValue.exactly(2.0F));
    }

    /**
     * Registers the base of a trade set.
     */
    public static Holder.Reference<TradeSet> register(final BootstrapContext<TradeSet> context, final ResourceKey<TradeSet> resourceKey, final TagKey<VillagerTrade> tradeTag, final NumberProvider numberProvider) {
        return context.register(
                resourceKey,
                new TradeSet(
                        context.lookup(Registries.VILLAGER_TRADE).getOrThrow(tradeTag), numberProvider, false, Optional.of(resourceKey.identifier().withPrefix("trade_set/"))
                )
        );
    }
}