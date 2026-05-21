package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.tag.ModTradeTags;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.villager.ModTrades;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

/**
 * Generates all speedrunner mod {@code villager trade tags,} which are tags that hold the villager's corresponding villager trade entry.
 */
@Author(Authors.SAMEDDIFFERENT)
public class ModTradeTagGenerator extends KeyTagProvider<VillagerTrade> {

    protected ModTradeTagGenerator(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.VILLAGER_TRADE, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_1)
                .add(ModTrades.RETIRED_SPEEDRUNNER_1_BOOK)
                .add(ModTrades.RETIRED_SPEEDRUNNER_1_ENCHANTED_BOOK);

        this.tag(ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_2)
                .add(ModTrades.RETIRED_SPEEDRUNNER_2_EMERALD)
                .add(ModTrades.RETIRED_SPEEDRUNNER_2_GOLDEN_UPGRADE_SMITHING_TEMPLATE);

        this.tag(ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_3)
                .add(ModTrades.RETIRED_SPEEDRUNNER_3_POTION)
                .add(ModTrades.RETIRED_SPEEDRUNNER_3_OMINOUS_BOTTLE);

        this.tag(ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_4)
                .add(ModTrades.RETIRED_SPEEDRUNNER_4_GOLDEN_APPLE)
                .add(ModTrades.RETIRED_SPEEDRUNNER_4_ENCHANTED_BOOK)
                .add(ModTrades.RETIRED_SPEEDRUNNER_4_INFINI_PEARL);

        this.tag(ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_5)
                .add(ModTrades.RETIRED_SPEEDRUNNER_5_NETHERITE_UPGRADE_SMITHING_TEMPLATE)
                .add(ModTrades.RETIRED_SPEEDRUNNER_5_DRAGONS_AURA)
                .add(ModTrades.RETIRED_SPEEDRUNNER_5_NETHERITE_CHESTPLATE);
    }
}