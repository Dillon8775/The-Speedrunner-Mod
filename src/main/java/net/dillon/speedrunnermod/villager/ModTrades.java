package net.dillon.speedrunnermod.villager;

import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.item.core.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.tag.ModEnchantmentTags;
import net.dillon.speedrunnermod.tag.ModPotionTags;
import net.minecraft.advancements.predicates.DataComponentMatchers;
import net.minecraft.advancements.predicates.EnchantmentPredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.MinMaxBounds;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
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
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.List;
import java.util.Optional;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod villager trade entries.
 */
public class ModTrades {
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_1_EMERALD = createTrade("retired_speedrunner/1/emerald");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_1_ENCHANTED_BOOK = createTrade("retired_speedrunner/1/enchanted_book");

    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_2_GOLDEN_APPLE = createTrade("retired_speedrunner/2/golden_apple");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_2_GOLDEN_UPGRADE_SMITHING_TEMPLATE = createTrade("retired_speedrunner/2/golden_upgrade_smithing_template");

    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_3_SPEEDRUNNER_HARNESS = createTrade("retired_speedrunner/3/speedrunner_harness");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_3_SPEEDRUNNER_NAUTILUS_ARMOR = createTrade("retired_speedrunner/3/speedrunner_nautilus_armor");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_3_ENCHANTED_GOLDEN_APPLE = createTrade("retired_speedrunner/3/enchanted_golden_apple");

    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_4_DRAGONS_FIREBALL = createTrade("retired_speedrunner/4/dragons_fireball");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_4_ENCHANTED_BOOK = createTrade("retired_speedrunner/4/enchanted_book");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_4_INFINI_PEARL = createTrade("retired_speedrunner/4/infini_pearl");

    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_5_SPECIAL_ARROW = createTrade("retired_speedrunner/5/special_arrow");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_5_SPECIAL_POTION = createTrade("retired_speedrunner/5/special_potion");
    public static final ResourceKey<VillagerTrade> RETIRED_SPEEDRUNNER_5_NETHERITE_CHESTPLATE = createTrade("retired_speedrunner/5/netherite_chestplate");

    public static Holder<VillagerTrade> bootstrap(BootstrapContext<VillagerTrade> context) {
        HolderGetter<Item> items = context.lookup(Registries.ITEM);
        HolderGetter<Enchantment> enchantments = context.lookup(Registries.ENCHANTMENT);

        HolderSet<Enchantment> enchantmentsForBooks =
                enchantments.getOrThrow(ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES);

        HolderSet<Enchantment> doubleTradePrice =
                enchantments.getOrThrow(EnchantmentTags.TREASURE);

        HolderGetter<Potion> potions = context.lookup(Registries.POTION);

        register(
                context,
                RETIRED_SPEEDRUNNER_1_EMERALD,
                ofBasic(
                        ModItems.SPEEDRUNNER_INGOT, 3,
                        Items.EMERALD, 1,
                        12,
                        3,
                        0.6F
                )
        );

        register(
                context,
                RETIRED_SPEEDRUNNER_1_ENCHANTED_BOOK,
                ofEnchantedBook(
                        3,
                        12,
                        4,
                        1.5F,
                        items,
                        enchantmentsForBooks,
                        doubleTradePrice
                )
        );

        register(
                context,
                RETIRED_SPEEDRUNNER_2_GOLDEN_APPLE,
                ofBasic(
                        Items.EMERALD, 3,
                        Items.GOLDEN_APPLE, 2,
                        8,
                        11,
                        0.1F
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
                        0.4F
                )
        );

        register(
                context,
                RETIRED_SPEEDRUNNER_3_SPEEDRUNNER_HARNESS,
                VillagerTrade.builder(
                                new TradeCost(Items.EMERALD, 7),
                                new ItemStackTemplate(ModItems.SPEEDRUNNER_HARNESS, 1),
                                6,
                                14,
                                0.3F
                        )
                        .additionalWants(new TradeCost(ModItems.SPEEDRUNNER_INGOT, 1))
                        .build()
        );

        register(
                context,
                RETIRED_SPEEDRUNNER_3_SPEEDRUNNER_NAUTILUS_ARMOR,
                VillagerTrade.builder(
                                new TradeCost(Items.EMERALD, 5),
                                new ItemStackTemplate(ModItems.SPEEDRUNNER_NAUTILUS_ARMOR, 1),
                                6,
                                14,
                                0.4F
                        )
                        .additionalWants(new TradeCost(ModItems.SPEEDRUNNER_INGOT, 1))
                        .build()
        );

        register(
                context,
                RETIRED_SPEEDRUNNER_3_ENCHANTED_GOLDEN_APPLE,
                ofBasic(
                        Items.EMERALD, 9,
                        Items.ENCHANTED_GOLDEN_APPLE, 1,
                        3,
                        15,
                        0.1F
                )
        );

        register(
                context,
                RETIRED_SPEEDRUNNER_4_DRAGONS_FIREBALL,
                ofBasic(
                        ModItems.DRAGONS_PEARL, 1,
                        ModItems.DRAGON_FIREBALL, 3,
                        6,
                        20,
                        0.3F
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
                        0.3F,
                        items,
                        enchantmentsForBooks,
                        doubleTradePrice
                )
        );

        register(
                context,
                RETIRED_SPEEDRUNNER_5_SPECIAL_ARROW,
                VillagerTrade.builder(
                                new TradeCost(Items.EMERALD, 3),
                                new ItemStackTemplate(Items.TIPPED_ARROW, 5),
                                6,
                                34,
                                0.4F
                        )
                        .additionalWants(new TradeCost(Items.ARROW, 1))
                        .addModifier(
                                SetRandomPotionFunction
                                        .fromTagKey(
                                                potions.getOrThrow(
                                                        ModPotionTags.RETIRED_SPEEDRUNNER_ARROW_EFFECTS
                                                )
                                        )
                        )
                        .build()
        );

        register(
                context,
                RETIRED_SPEEDRUNNER_5_SPECIAL_POTION,
                VillagerTrade.builder(
                                new TradeCost(Items.BLAZE_POWDER, 12),
                                new ItemStackTemplate(Items.POTION, 1),
                                1,
                                35,
                                0.05F
                        )
                        .addModifier(
                                SetRandomPotionFunction
                                        .fromTagKey(
                                                potions.getOrThrow(
                                                        ModPotionTags.RETIRED_SPEEDRUNNER_POTION_EFFECTS
                                                )
                                        )
                        )
                        .build()
        );

        register(
                context,
                RETIRED_SPEEDRUNNER_5_NETHERITE_CHESTPLATE,
                ofConditions(
                        Items.EMERALD, 12,
                        Items.NETHERITE_CHESTPLATE, 1,
                        3,
                        40,
                        0.5F,
                        enchantedItem(
                                items,
                                enchantmentsForBooks,
                                Items.NETHERITE_CHESTPLATE
                        )
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
    private static VillagerTrade ofConditions(Item buyItem, int cost, Item sellItem, int quantity, int maxUses, int xp, float reputationDiscount, List<LootItemFunction> functions) {
        VillagerTrade.Builder builder = VillagerTrade.builder(
                new TradeCost(buyItem, cost),
                new ItemStackTemplate(sellItem, quantity),
                maxUses,
                xp,
                reputationDiscount
        );

        for (LootItemFunction function : functions) {
            builder.addModifiers(Holder.direct(function));
        }

        return builder.build();
    }

    /**
     * @return a maximum level enchanted book.
     */
    private static VillagerTrade ofEnchantedBook(int cost, int maxUses, int xp, float reputationDiscount, HolderGetter<Item> items, HolderSet<Enchantment> enchantmentsForBooks, HolderSet<Enchantment> doubleTradePrice) {
        VillagerTrade.Builder builder = VillagerTrade.builder(
                new TradeCost(Items.EMERALD, cost),
                new ItemStackTemplate(Items.ENCHANTED_BOOK),
                maxUses,
                xp,
                reputationDiscount
        );

        builder.additionalWants(new TradeCost(Items.BOOK, 1));

        for (LootItemFunction function : enchantedBook(items, enchantmentsForBooks)) {
            builder.addModifiers(Holder.direct(function));
        }

        builder.doubleTradePriceEnchantments(doubleTradePrice);
        return builder.build();
    }

    /**
     * @return a list of balanced enchanted books.
     */
    private static List<LootItemFunction> enchantedBook(final HolderGetter<Item> items, final HolderSet<Enchantment> options) {
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
                        .onFail(DiscardItem.discardItem().build())
                        .build()
        );
    }

    /**
     * @return an enchanted item with high enchantment levels.
     */
    public static List<LootItemFunction> enchantedItem(final HolderGetter<Item> items, final HolderSet<Enchantment> options, final Item expectedItem) {
        return List.of(
                new EnchantWithLevelsFunction.Builder(ContextIntProviders.between(33, 36))
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
                        .onFail(DiscardItem.discardItem().build())
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

    /**
     * Creates a resource key for a villager trade.
     */
    @Author(Authors.SAMEDDIFFERENT)
    private static ResourceKey<VillagerTrade> createTrade(String path) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all speedrunner mod trades.
     */
    public static void initializeTrades() {
        SpeedrunnerMod.LOGGER.debug("Initialized trades.");
    }
}