package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.component.ModPotions;
import net.dillon.speedrunnermod.item.core.ModItems;
import net.dillon.speedrunnermod.loot.ModLootTables;
import net.dillon.speedrunnermod.tag.ModPotionsTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.context.ContextKeySet;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

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
        HolderGetter<Enchantment> enchantments = lookup.lookupOrThrow(Registries.ENCHANTMENT);
        HolderSet<Potion> doomBlockPotions = lookup.getOrThrow(ModPotionsTags.DOOM_BLOCK_POTIONS);
        output.accept(ModLootTables.DOOM_BLOCK_LOOT, LootTable.lootTable()
                .withPool(LootPool.lootPool()
                        .setRolls(
                                ContextIntProviders.exactly(1)
                        )
                        .add(
                                LootItem.lootTableItem(Items.DIAMOND_SWORD)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.between(20, 33)))
                        )
                        .add(
                                LootItem.lootTableItem(Items.NETHERITE_CHESTPLATE)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.between(27, 33)))
                        )
                        .add(
                                LootItem.lootTableItem(Items.BOW)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.between(30, 33)))
                        )
                        .add(
                                LootItem.lootTableItem(Items.CROSSBOW)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.between(27, 33)))
                        )
                        .add(
                                LootItem.lootTableItem(Items.IRON_CHESTPLATE)
                                        .apply(EnchantWithLevelsFunction.enchantWithLevels(enchantments, ContextIntProviders.between(24, 28)))
                        )
                        .add(
                                LootItem.lootTableItem(Items.ENCHANTED_GOLDEN_APPLE)
                        )
                        .add(
                                LootItem.lootTableItem(Items.GOLDEN_APPLE)
                                        .setWeight(4)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                        )
                        .add(
                                LootItem.lootTableItem(ModItems.RAID_ERADICATOR)
                        )
                        .add(
                                LootItem.lootTableItem(ModItems.SPEEDRUNNERS_TOTEM)
                        )
                        .add(
                                LootItem.lootTableItem(Items.FIRE_CHARGE)
                                        .setWeight(3)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 5)))
                        )
                        .add(
                                LootItem.lootTableItem(ModItems.DRAGON_FIREBALL)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(1, 3)))
                        )
                        .add(
                                LootItem.lootTableItem(Items.EXPERIENCE_BOTTLE)
                                        .setWeight(3)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(2, 11)))
                        )
                        .add(
                                LootItem.lootTableItem(Items.ELYTRA)
                                        .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.18F, 0.45F)))
                        )
                        .add(
                                LootItem.lootTableItem(ModItems.KNOCKBACK_STICK)
                        )
                        .add(
                                LootItem.lootTableItem(Items.POTION)
                                        .setWeight(2)
                                        .apply(SetRandomPotionFunction.fromTagKey(doomBlockPotions))
                        )
                        .add(
                                LootItem.lootTableItem(Items.POTION)
                                        .apply(SetPotionFunction.setPotion(ModPotions.DRAGONS_AURA))
                        )
                        .add(
                                LootItem.lootTableItem(ModItems.DRAGONS_PEARL)
                                        .setWeight(2)
                        )
                        .add(
                                LootItem.lootTableItem(Items.WIND_CHARGE)
                                        .setWeight(2)
                                        .apply(SetItemCountFunction.setCount(ContextIntProviders.between(4, 16)))
                        )
                        .add(
                                LootItem.lootTableItem(Items.MACE)
                                        .apply(SetItemDamageFunction.setDamage(ContextFloatProviders.between(0.12F, 0.23F)))
                        )
                )
        );
    }

    @Override
    public void run() {
    }
}