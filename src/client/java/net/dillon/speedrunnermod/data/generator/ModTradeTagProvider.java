package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.tag.ModTradeTags;
import net.dillon.speedrunnermod.villager.ModTrades;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

/**
 * Generates all speedrunner mod {@code villager trade tags,} which are tags that hold the villager's corresponding villager trade entry.
 */
@Author(Authors.SAMEDDIFFERENT)
public class ModTradeTagProvider extends FabricTagsProvider<VillagerTrade> {

    protected ModTradeTagProvider(final FabricPackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.VILLAGER_TRADE, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_1)
                .add(ModTrades.RETIRED_SPEEDRUNNER_1_EMERALD)
                .add(ModTrades.RETIRED_SPEEDRUNNER_1_ENCHANTED_BOOK);

        tag(ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_2)
                .add(ModTrades.RETIRED_SPEEDRUNNER_2_GOLDEN_APPLE)
                .add(ModTrades.RETIRED_SPEEDRUNNER_2_GOLDEN_UPGRADE_SMITHING_TEMPLATE);

        tag(ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_3)
                .add(ModTrades.RETIRED_SPEEDRUNNER_3_SPEEDRUNNER_HARNESS)
                .add(ModTrades.RETIRED_SPEEDRUNNER_3_SPEEDRUNNER_NAUTILUS_ARMOR)
                .add(ModTrades.RETIRED_SPEEDRUNNER_3_ENCHANTED_GOLDEN_APPLE);

        tag(ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_4)
                .add(ModTrades.RETIRED_SPEEDRUNNER_4_DRAGONS_FIREBALL)
                .add(ModTrades.RETIRED_SPEEDRUNNER_4_ENCHANTED_BOOK)
                .add(ModTrades.RETIRED_SPEEDRUNNER_4_INFINI_PEARL);

        tag(ModTradeTags.RETIRED_SPEEDRUNNER_LEVEL_5)
                .add(ModTrades.RETIRED_SPEEDRUNNER_5_SPECIAL_ARROW)
                .add(ModTrades.RETIRED_SPEEDRUNNER_5_SPECIAL_POTION)
                .add(ModTrades.RETIRED_SPEEDRUNNER_5_NETHERITE_CHESTPLATE);
    }
}