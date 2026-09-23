package net.dillon.speedrunnermod.loot.data;

import net.dillon.speedrunnermod.tag.ModEnchantmentTags;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.functions.*;
import net.minecraft.world.level.storage.loot.providers.number.floats.ContextFloatProviders;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

import static net.dillon.speedrunnermod.main.CommonMain.ofSpeedrunnerMod;

/**
 * Stores data required for making loot tables.
 */
public abstract class GeneratableLootTable {
    public LootTableData data;

    public GeneratableLootTable(final LootTableData data) {
        this.data = data;
    }

    /**
     * Generates the loot for a specified class.
     */
    public abstract void generateLoot();

    /**
     * Creates a new speedrunner mod loot table.
     */
    public static ResourceKey<LootTable> createLootTable(final String path) {
        return ResourceKey.create(Registries.LOOT_TABLE, ofSpeedrunnerMod(path));
    }

    /**
     * Sets the count of a loot entry.
     */
    public LootItemConditionalFunction.Builder<?> setCount(int min, int max) {
        return SetItemCountFunction.setCount(ContextIntProviders.between(min, max));
    }

    /**
     * Sets the count of a loot entry.
     */
    public LootItemConditionalFunction.Builder<?> setCount(int exact) {
        return SetItemCountFunction.setCount(ContextIntProviders.exactly(exact));
    }

    /**
     * Sets the item damage of a loot entry.
     */
    public LootItemConditionalFunction.Builder<?> setDamage(float min, float max) {
        return SetItemDamageFunction.setDamage(ContextFloatProviders.between(min, max));
    }

    /**
     * Randomly enchants a loot entry with a specified enchantment tag key.
     */
    public EnchantRandomlyFunction.Builder randomEnchantment(TagKey<Enchantment> key) {
        return EnchantRandomlyFunction.randomApplicableEnchantment(data.enchantments())
                .withOptions(
                        data.enchantments().getOrThrow(key)
                );
    }

    /**
     * Randomly enchants a loot entry with a random speedrunner book enchantment.
     */
    public EnchantRandomlyFunction.Builder randomSpeedrunnerBookEnchantment() {
        return EnchantRandomlyFunction.randomApplicableEnchantment(data.enchantments())
                .withOptions(
                        data.enchantments().getOrThrow(ModEnchantmentTags.ON_RANDOM_SPEEDRUNNER_BOOK)
                );
    }

    /**
     * Randomly enchants a loot entry with a specified enchantment key.
     */
    public EnchantRandomlyFunction.Builder withEnchantment(ResourceKey<Enchantment> key) {
        return new EnchantRandomlyFunction.Builder()
                .withEnchantment(
                        data.enchantments().getOrThrow(key)
                );
    }

    /**
     * Enchants a loot entry between an exact amount of levels.
     */
    public EnchantWithLevelsFunction.Builder enchantBetweenLevels(int minLevels, int maxLevels) {
        return EnchantWithLevelsFunction.enchantWithLevels(data.enchantments(), ContextIntProviders.between(minLevels, maxLevels));
    }

    /**
     * Enchants a loot entry between an exact amount of levels, with a specified enchantment tag key.
     */
    public EnchantWithLevelsFunction.Builder enchantBetweenLevelsAndKey(TagKey<Enchantment> key, int minLevels, int maxLevels) {
        return enchantBetweenLevels(minLevels, maxLevels)
                .withOptions(
                        data.enchantments().getOrThrow(key)
                );
    }

    /**
     * Enchants a loot entry with an exact amount of levels, with a specified enchantment tag key.
     */
    public EnchantWithLevelsFunction.Builder enchantWithLevels(int exactLevels) {
        return enchantBetweenLevels(exactLevels, exactLevels);
    }

    /**
     * Enchants a loot entry with an exact amount of levels, with a specified enchantment tag key.
     */
    public EnchantWithLevelsFunction.Builder enchantWithLevelsAndKey(TagKey<Enchantment> key, int exactLevels) {
        return enchantBetweenLevelsAndKey(key, exactLevels, exactLevels);
    }

    /**
     * Enchants a loot entry with an exact amount of levels, and the {@link ModEnchantmentTags#ON_RANDOM_SPEEDRUNNER_LOOT} key.
     */
    public EnchantWithLevelsFunction.Builder enchantWithRandomSpeedrunnerLoot(int exactLevels) {
        return enchantBetweenLevelsAndKey(ModEnchantmentTags.ON_RANDOM_SPEEDRUNNER_LOOT, exactLevels, exactLevels);
    }

    /**
     * Enchants a loot entry between a certain amount of levels, and the {@link ModEnchantmentTags#ON_RANDOM_SPEEDRUNNER_LOOT} key.
     */
    public EnchantWithLevelsFunction.Builder enchantWithRandomSpeedrunnerLoot(int minLevels, int maxLevels) {
        return enchantBetweenLevelsAndKey(ModEnchantmentTags.ON_RANDOM_SPEEDRUNNER_LOOT, minLevels, maxLevels);
    }
}