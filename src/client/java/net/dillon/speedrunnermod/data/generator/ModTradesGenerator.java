package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.potion.ModPotions;
import net.dillon.speedrunnermod.tag.ModEnchantmentTags;
import net.dillon.speedrunnermod.tag.ModPotionsTags;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.advancements.criterion.DataComponentMatchers;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.component.predicates.DataComponentPredicates;
import net.minecraft.core.component.predicates.EnchantmentsPredicate;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.List;
import java.util.Optional;

import static net.dillon.speedrunnermod.villager.ModTrades.*;

/**
 * Generates all speedrunner mod {@code villager trade entries}.
 */
public class ModTradesGenerator {

    public static Holder<VillagerTrade> bootstrap(BootstrapContext<VillagerTrade> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);
        Optional<HolderSet<Enchantment>> enchantmentsForBooks = enchantments.get(ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES).map(named -> named);
        Optional<HolderSet<Enchantment>> witheredEnchantmentForBooks = enchantments.get(ModEnchantmentTags.WITHERED_ENCHANTMENTS).map(named -> named);
        Optional<HolderSet<Enchantment>> doubleTradePrice = enchantments.get(EnchantmentTags.TREASURE).map(named -> named);
        Optional<HolderSet<Potion>> potions = context.lookup(Registries.POTION).get(ModPotionsTags.RETIRED_SPEEDRUNNER_POTIONS).map(named -> named);
        register(
                context,
                RETIRED_SPEEDRUNNER_1_BOOK,
                ofBasic(
                        Items.EMERALD, 1,
                        Items.BOOK, 3,
                        12,
                        3,
                        5
                )
        );
        register(
                context,
                RETIRED_SPEEDRUNNER_1_ENCHANTED_BOOK,
                ofEnchantedBook(
                        3,
                        12,
                        4,
                        0.2F,
                        items,
                        enchantmentsForBooks,
                        doubleTradePrice
                )
        );
        register(
                context,
                RETIRED_SPEEDRUNNER_2_EMERALD,
                ofBasic(
                        ModItems.SPEEDRUNNER_INGOT, 3,
                        Items.EMERALD, 3,
                        12,
                        10,
                        0.0F
                )
        );
        register(
                context,
                RETIRED_SPEEDRUNNER_2_GOLDEN_UPGRADE_SMITHING_TEMPLATE,
                ofBasic(
                        Items.EMERALD, 2,
                        ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE, 1,
                        12,
                        12,
                        0.0F
                )
        );
        register(
                context,
                RETIRED_SPEEDRUNNER_3_POTION,
                new VillagerTrade(
                        new TradeCost(ModItems.SPEEDRUNNER_INGOT, 5),
                        Optional.of(new TradeCost(Items.BLAZE_POWDER, 1)),
                        new ItemStackTemplate(Items.POTION, 1),
                        8,
                        12,
                        0.0F,
                        Optional.empty(),
                        List.of(SetRandomPotionFunction.fromTagKey(potions).build())
                )
        );
        register(
                context,
                RETIRED_SPEEDRUNNER_3_OMINOUS_BOTTLE,
                new VillagerTrade(
                        new TradeCost(Items.EMERALD, 3),
                        Optional.of(new TradeCost(ModItems.ANNUL_EYE, 1)),
                        new ItemStackTemplate(Items.OMINOUS_BOTTLE, 1),
                        4,
                        12,
                        0.0F,
                        Optional.empty(),
                        List.of()
                )
        );
        register(
                context,
                RETIRED_SPEEDRUNNER_4_GOLDEN_APPLE,
                ofBasic(
                        Items.EMERALD, 4,
                        Items.GOLDEN_APPLE, 2,
                        16,
                        18,
                        0.04F
                )
        );
        register(
                context,
                RETIRED_SPEEDRUNNER_4_INFINI_PEARL,
                ofBasic(
                        Items.EMERALD, 24,
                        ModItems.INFINI_PEARL, 1,
                        1,
                        24,
                        0.0F
                )
        );
        register(
                context,
                RETIRED_SPEEDRUNNER_4_ENCHANTED_BOOK,
                ofEnchantedBook(
                        3,
                        12,
                        20,
                        0.2F,
                        items,
                        enchantmentsForBooks,
                        doubleTradePrice
                )
        );
        register(
                context,
                RETIRED_SPEEDRUNNER_5_NETHERITE_UPGRADE_SMITHING_TEMPLATE,
                ofBasic(
                        Items.EMERALD, 3,
                        Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, 1,
                        4,
                        28,
                        0.2F
                )
        );
        register(
                context,
                RETIRED_SPEEDRUNNER_5_DRAGONS_AURA,
                new VillagerTrade(
                        new TradeCost(ModItems.SPEEDRUNNER_INGOT, 5),
                        Optional.of(new TradeCost(Items.BLAZE_POWDER, 1)),
                        new ItemStackTemplate(
                                Items.POTION.builtInRegistryHolder(), 1, DataComponentPatch.builder().set(DataComponents.POTION_CONTENTS, new PotionContents(ModPotions.DRAGONS_AURA)).build()),
                        3,
                        35,
                        0.05F,
                        Optional.empty(),
                        List.of()
                )
        );
        register(
                context,
                RETIRED_SPEEDRUNNER_5_NETHERITE_CHESTPLATE,
                ofConditions(
                        Items.EMERALD, 12,
                        Items.NETHERITE_CHESTPLATE, 1,
                        3,
                        40,
                        0.75F,
                        ModTradesGenerator.enchantedItem(items, enchantmentsForBooks, Items.NETHERITE_CHESTPLATE)
                )
        );
        return null;
    }

    /**
     * @return a basic villager trade.
     */
    private static VillagerTrade ofBasic(Item buyItem, int cost, Item sellItem, int quantity, int maxUses, int xp, float reputationDiscount) {
        return ofConditions(buyItem, cost, sellItem, quantity, maxUses, xp, reputationDiscount, List.of());
    }

    /**
     * @return a basic villager trade, with certain properties attached to it.
     */
    private static VillagerTrade ofConditions(Item buyItem, int cost, Item sellItem, int quantity, int maxUses, int xp, float reputationDiscount, List<LootItemFunction> conditions) {
        return new VillagerTrade(
                new TradeCost(buyItem, cost),
                new ItemStackTemplate(sellItem, quantity),
                maxUses,
                xp,
                reputationDiscount,
                Optional.empty(),
                conditions
        );
    }

    /**
     * @return a maximum level enchanted book.
     */
    private static VillagerTrade ofEnchantedBook(int cost, int maxUses, int xp, float reputationDiscount, HolderGetter<Item> items, Optional<HolderSet<Enchantment>> enchantmentsForBooks, Optional<HolderSet<Enchantment>> doubleTradePrice) {
        return new VillagerTrade(
                new TradeCost(Items.EMERALD, cost),
                Optional.of(new TradeCost(Items.BOOK, 1)),
                new ItemStackTemplate(Items.ENCHANTED_BOOK),
                maxUses,
                xp,
                reputationDiscount,
                Optional.empty(),
                ModTradesGenerator.enchantedBook(items, enchantmentsForBooks),
                doubleTradePrice
        );
    }

    /**
     * @return a list of balanced enchanted books.
     */
    private static List<LootItemFunction> enchantedBook(final HolderGetter<Item> items, final Optional<HolderSet<Enchantment>> options) {
        return List.of(
                new EnchantRandomlyFunction.Builder()
                        .withOptions(options)
                        .allowingIncompatibleEnchantments()
                        .build(),
                FilteredFunction.filtered(
                                new ItemPredicate.Builder()
                                        .of(items, Items.ENCHANTED_BOOK)
                                        .withComponents(
                                                DataComponentMatchers.Builder.components()
                                                        .partial(
                                                                DataComponentPredicates.STORED_ENCHANTMENTS, EnchantmentsPredicate.storedEnchantments(
                                                                        List.of(
                                                                                new EnchantmentPredicate(Optional.empty(), MinMaxBounds.Ints.ANY)
                                                                        )
                                                                )
                                                        )
                                                        .build()
                                        )
                                        .build()
                        )
                        .onFail(Optional.of(DiscardItem.discardItem().build()))
                        .build()
        );
    }

    /**
     * @return an enchanted item with high enchantment levels.
     */
    public static List<LootItemFunction> enchantedItem(final HolderGetter<Item> items, final Optional<HolderSet<Enchantment>> options, final Item expectedItem) {
        return List.of(
                new net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction.Builder(UniformGenerator.between(33.0F, 36.0F))
                        .withOptions(options)
                        .build(),
                FilteredFunction.filtered(
                                new ItemPredicate.Builder()
                                        .of(items, expectedItem)
                                        .withComponents(
                                                DataComponentMatchers.Builder.components()
                                                        .partial(DataComponentPredicates.ENCHANTMENTS, EnchantmentsPredicate.enchantments(
                                                                List.of(
                                                                        new EnchantmentPredicate(Optional.empty(), MinMaxBounds.Ints.ANY))
                                                        ))
                                                        .build()
                                        )
                                        .build()
                        )
                        .onFail(Optional.of(DiscardItem.discardItem().build()))
                        .build()
        );
    }

    /**
     * Registers a villager trade.
     */
    @Author(Authors.SAMEDDIFFERENT)
    private static Holder.Reference<VillagerTrade> register(final BootstrapContext<VillagerTrade> context, final ResourceKey<VillagerTrade> resourceKey, final VillagerTrade villagerTrade) {
        return context.register(resourceKey, villagerTrade);
    }
}
