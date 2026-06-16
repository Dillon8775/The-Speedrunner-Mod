package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.loot.ModLootTables;
import net.dillon.speedrunnermod.potion.ModPotions;
import net.dillon.speedrunnermod.tag.ModPotionsTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantWithLevelsFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SetPotionFunction;
import net.minecraft.world.level.storage.loot.functions.SetRandomPotionFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

/**
 * Loot that can be obtained from mining doom blocks.
 */
public class DoomBlockLootProvider extends SimpleFabricLootTableSubProvider {
    private final CompletableFuture<HolderLookup.Provider> registryLookupFuture;

    public DoomBlockLootProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        this(output, registryLookupFuture, LootContextParamSets.BLOCK);
    }

    public DoomBlockLootProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture, ContextKeySet contextParamSet) {
        super(output, registryLookupFuture, contextParamSet);
        this.registryLookupFuture = registryLookupFuture;
    }

    @Override
    public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> output) {
        HolderLookup.Provider lookup = this.registryLookupFuture.join();
        HolderSet<Potion> potions = lookup.getOrThrow(ModPotionsTags.RETIRED_SPEEDRUNNER_POTIONS);
        output.accept(ModLootTables.DOOM_BLOCK_LOOT, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.DIAMOND_SWORD)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(lookup, UniformGenerator.between(20.0F, 33.0F))))
                        .add(LootItem.lootTableItem(Items.NETHERITE_CHESTPLATE)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(lookup, UniformGenerator.between(27.0F, 33.0F))))
                        .add(LootItem.lootTableItem(Items.BOW)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(lookup, UniformGenerator.between(30.0F, 33.0F))))
                        .add(LootItem.lootTableItem(Items.CROSSBOW)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(lookup, UniformGenerator.between(27.0F, 33.0F))))
                        .add(LootItem.lootTableItem(Items.IRON_CHESTPLATE)
                                .apply(EnchantWithLevelsFunction.enchantWithLevels(lookup, UniformGenerator.between(24.0F, 28.0F))))
                        .add(LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE))
                        .add(LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                .setWeight(4)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(ModItems.RAID_ERADICATOR))
                        .add(LootItem.lootTableItem(ModItems.SPEEDRUNNERS_TOTEM))
                        .add(LootItem.lootTableItem(Items.FIRE_CHARGE)
                                .setWeight(3)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F))))
                        .add(LootItem.lootTableItem(ModItems.DRAGONS_FIREBALL)
                                .setWeight(2)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F))))
                        .add(LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE)
                                .setWeight(3)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 11.0F))))
                        .add(LootItem.lootTableItem(Items.ELYTRA))
                        .add(LootItem.lootTableItem(ModItems.KNOCKBACK_STICK))
                        .add(LootItem.lootTableItem(Items.POTION)
                                .setWeight(2)
                                .apply(SetRandomPotionFunction.fromTagKey(potions)))
                        .add(LootItem.lootTableItem(Items.POTION)
                                .apply(SetPotionFunction.setPotion(ModPotions.DRAGONS_AURA)))
                        .add(LootItem.lootTableItem(ModItems.DRAGONS_PEARL)
                                .setWeight(2))
                        .add(LootItem.lootTableItem(Items.WIND_CHARGE)
                                .setWeight(2)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(4.0F, 16.0F))))
                        .add(LootItem.lootTableItem(Items.MACE))
                )
        );
    }
}