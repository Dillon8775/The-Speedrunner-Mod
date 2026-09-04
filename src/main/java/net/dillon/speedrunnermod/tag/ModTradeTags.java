package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.villager.ModTrades;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.trading.VillagerTrade;

import java.util.concurrent.CompletableFuture;

import static net.dillon.dillonlib.factory.Factories.createVillagerTradeTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod villager trade tags.
 */
public class ModTradeTags extends FabricTagsProvider<VillagerTrade> {
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_1 = createVillagerTradeTag(ofSpeedrunnerMod("retired_speedrunner/level_1"));
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_2 = createVillagerTradeTag(ofSpeedrunnerMod("retired_speedrunner/level_2"));
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_3 = createVillagerTradeTag(ofSpeedrunnerMod("retired_speedrunner/level_3"));
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_4 = createVillagerTradeTag(ofSpeedrunnerMod("retired_speedrunner/level_4"));
    public static final TagKey<VillagerTrade> RETIRED_SPEEDRUNNER_LEVEL_5 = createVillagerTradeTag(ofSpeedrunnerMod("retired_speedrunner/level_5"));

    public ModTradeTags(final FabricPackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider) {
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