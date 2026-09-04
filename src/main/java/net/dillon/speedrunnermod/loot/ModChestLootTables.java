package net.dillon.speedrunnermod.loot;

import net.dillon.speedrunnermod.item.core.ModItems;
import net.dillon.speedrunnermod.tag.ModEnchantmentTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

import static net.dillon.speedrunnermod.loot.ModBlockLoot.createLootTable;

/**
 * All custom speedrunner mod chest loot tables.
 */
public class ModChestLootTables extends SimpleFabricLootTableSubProvider {
    public static final ResourceKey<LootTable> TRIAL_CHAMBERS_CORRIDOR = createLootTable("chests/trial_chambers/corridor");
    public static final ResourceKey<LootTable> TRIAL_CHAMBERS_ENTRANCE = createLootTable("chests/trial_chambers/entrance");
    public static final ResourceKey<LootTable> TRIAL_CHAMBERS_INTERSECTION = createLootTable("chests/trial_chambers/intersection");
    public static final ResourceKey<LootTable> TRIAL_CHAMBERS_INTERSECTION_BARREL = createLootTable("chests/trial_chambers/intersection_barrel");
    public static final ResourceKey<LootTable> TRIAL_CHAMBERS_REWARD_OMINOUS_COMMON = createLootTable("chests/trial_chambers/reward_ominous_common");
    public static final ResourceKey<LootTable> TRIAL_CHAMBERS_REWARD_OMINOUS_RARE = createLootTable("chests/trial_chambers/reward_ominous_rare");
    public static final ResourceKey<LootTable> TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE = createLootTable("chests/trial_chambers/reward_ominous_unique");
    public static final ResourceKey<LootTable> TRIAL_CHAMBERS_REWARD_RARE = createLootTable("chests/trial_chambers/reward_rare");
    public static final ResourceKey<LootTable> TRIAL_CHAMBERS_REWARD_UNIQUE = createLootTable("chests/trial_chambers/reward_unique");
    public static final ResourceKey<LootTable> TRIAL_CHAMBERS_SUPPLY = createLootTable("chests/trial_chambers/supply");
    public static final ResourceKey<LootTable> VILLAGE_ARMORER = createLootTable("chests/village/village_armorer");
    public static final ResourceKey<LootTable> VILLAGE_BUTCHER = createLootTable("chests/village/village_butcher");
    public static final ResourceKey<LootTable> VILLAGE_CARTOGRAPHER = createLootTable("chests/village/village_cartographer");
    public static final ResourceKey<LootTable> VILLAGE_DESERT_HOUSE = createLootTable("chests/village/village_desert_house");
    public static final ResourceKey<LootTable> VILLAGE_FISHER = createLootTable("chests/village/village_fisher");
    public static final ResourceKey<LootTable> VILLAGE_FLETCHER = createLootTable("chests/village/village_fletcher");
    public static final ResourceKey<LootTable> VILLAGE_MASON = createLootTable("chests/village/village_mason");
    public static final ResourceKey<LootTable> VILLAGE_PLAINS_HOUSE = createLootTable("chests/village/village_plains_house");
    public static final ResourceKey<LootTable> VILLAGE_SAVANNA_HOUSE = createLootTable("chests/village/village_savanna_house");
    public static final ResourceKey<LootTable> VILLAGE_SHEPHERD = createLootTable("chests/village/village_shepherd");
    public static final ResourceKey<LootTable> VILLAGE_SNOWY_HOUSE = createLootTable("chests/village/village_snowy_house");
    public static final ResourceKey<LootTable> VILLAGE_TAIGA_HOUSE = createLootTable("chests/village/village_taiga_house");
    public static final ResourceKey<LootTable> VILLAGE_TANNERY = createLootTable("chests/village/village_tannery");
    public static final ResourceKey<LootTable> VILLAGE_TEMPLE = createLootTable("chests/village/village_temple");
    public static final ResourceKey<LootTable> VILLAGE_TOOLSMITH = createLootTable("chests/village/village_toolsmith");
    public static final ResourceKey<LootTable> VILLAGE_WEAPONSMITH = createLootTable("chests/village/village_weaponsmith");
    public static final ResourceKey<LootTable> ABANDONED_MINESHAFT = createLootTable("chests/");
    public static final ResourceKey<LootTable> ANCIENT_CITY = createLootTable("chests/");
    public static final ResourceKey<LootTable> BASTION_BRIDGE = createLootTable("chests/");
    public static final ResourceKey<LootTable> BASTION_HOGLIN_STABLE = createLootTable("chests/");
    public static final ResourceKey<LootTable> BASTION_OTHER = createLootTable("chests/");
    public static final ResourceKey<LootTable> BURIED_TREASURE = createLootTable("chests/");
    public static final ResourceKey<LootTable> DESERT_PYRAMID = createLootTable("chests/");
    public static final ResourceKey<LootTable> END_CITY_TREASURE = createLootTable("chests/");
    public static final ResourceKey<LootTable> IGLOO_CHEST = createLootTable("chests/");
    public static final ResourceKey<LootTable> JUNGLE_TEMPLE = createLootTable("chests/");
    public static final ResourceKey<LootTable> NETHER_BRIDGE = createLootTable("chests/");
    public static final ResourceKey<LootTable> PILLAGER_OUTPOST = createLootTable("chests/");
    public static final ResourceKey<LootTable> RUINED_PORTAL = createLootTable("chests/ruined_portal");
    public static final ResourceKey<LootTable> SHIPWRECK_SUPPLY = createLootTable("chests/");
    public static final ResourceKey<LootTable> SHIPWRECK_TREASURE = createLootTable("chests/");
    public static final ResourceKey<LootTable> SIMPLE_DUNGEON = createLootTable("chests/");
    public static final ResourceKey<LootTable> STRONGHOLD_CORRIDOR = createLootTable("chests/");
    public static final ResourceKey<LootTable> STRONGHOLD_CROSSING = createLootTable("chests/");
    public static final ResourceKey<LootTable> STRONGHOLD_LIBRARY = createLootTable("chests/");
    public static final ResourceKey<LootTable> UNDERWATER_RUIN_BIG = createLootTable("chests/");
    public static final ResourceKey<LootTable> UNDERWATER_RUIN_SMALL = createLootTable("chests/");
    public static final ResourceKey<LootTable> WOODLAND_MANSION = createLootTable("chests/");
    private final CompletableFuture<HolderLookup.Provider> registryLookupFuture;

    public ModChestLootTables(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture, LootContextParamSets.CHEST);
        this.registryLookupFuture = registryLookupFuture;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> context) {
        HolderLookup.Provider provider = this.registryLookupFuture.join();
        HolderGetter<Enchantment> enchantments = provider.lookupOrThrow(Registries.ENCHANTMENT);

        context.accept(
                ModChestLootTables.RUINED_PORTAL,
                LootTable.lootTable()
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE)
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.FLINT_AND_STEEL)
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.OBSIDIAN)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(5, 8)))
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.GOLDEN_AXE)
                                                        .apply(
                                                                EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.exactly(33))
                                                        )
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.GOLDEN_PICKAXE)
                                                        .apply(
                                                                EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.exactly(33))
                                                        )
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.GOLDEN_SWORD)
                                                        .apply(EnchantRandomlyFunction.randomApplicableEnchantment(enchantments)
                                                                .withOptions(
                                                                        enchantments.getOrThrow(ModEnchantmentTags.ON_GOLDEN_SWORD_IN_RUINED_PORTAL)
                                                                )
                                                        )
                                        )
                        )
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ContextIntProviders.between(4, 8))
                                        .add(
                                                LootItem.lootTableItem(Items.ENDER_PEARL)
                                                        .setWeight(8)
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GOLDEN_CARROT)
                                                        .setWeight(21)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(7, 15)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(ModItems.SPEEDRUNNER_BULK)
                                                        .setWeight(7)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.exactly(1)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GLOWSTONE)
                                                        .setWeight(1)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(3, 6)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(ModItems.SPEEDRUNNER_INGOT)
                                                        .setWeight(8)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 7)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(ModItems.SPEEDRUNNER_NUGGET)
                                                        .setWeight(9)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(9, 18)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.IRON_NUGGET)
                                                        .setWeight(8)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(9, 18)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.FIRE_CHARGE)
                                                        .setWeight(18)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 2)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GOLD_NUGGET)
                                                        .setWeight(14)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(9, 18)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GOLD_INGOT)
                                                        .setWeight(20)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(9, 27)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                                        .setWeight(25)
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE)
                                                        .setWeight(9)
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GOLD_BLOCK)
                                                        .setWeight(25)
                                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 4)))
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GOLDEN_HOE)
                                                        .setWeight(2)
                                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.exactly(30))
                                                                .withOptions(
                                                                        enchantments.getOrThrow(ModEnchantmentTags.ON_RANDOM_SPEEDRUNNER_LOOT)
                                                                )
                                                        )
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GOLDEN_SHOVEL)
                                                        .setWeight(3)
                                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.exactly(30))
                                                                .withOptions(
                                                                        enchantments.getOrThrow(ModEnchantmentTags.ON_RANDOM_SPEEDRUNNER_LOOT)
                                                                )
                                                        )
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GOLDEN_BOOTS)
                                                        .setWeight(3)
                                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.exactly(30))
                                                                .withOptions(
                                                                        enchantments.getOrThrow(ModEnchantmentTags.ON_RANDOM_SPEEDRUNNER_LOOT)
                                                                )
                                                        )
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GOLDEN_HELMET)
                                                        .setWeight(3)
                                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.exactly(30))
                                                                .withOptions(
                                                                        enchantments.getOrThrow(ModEnchantmentTags.ON_RANDOM_SPEEDRUNNER_LOOT)
                                                                )
                                                        )
                                        )
                                        .add(
                                                LootItem.lootTableItem(Items.GOLDEN_LEGGINGS)
                                                        .setWeight(3)
                                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.exactly(30))
                                                                .withOptions(
                                                                        enchantments.getOrThrow(ModEnchantmentTags.ON_RANDOM_SPEEDRUNNER_LOOT)
                                                                )
                                                        )
                                        )
                        )
        );
    }

    @Override
    public void run() {
    }
}