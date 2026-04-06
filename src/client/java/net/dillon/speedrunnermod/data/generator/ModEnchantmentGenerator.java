package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.EnchantmentAttributeEffect;

import java.util.concurrent.CompletableFuture;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Used to create the JSON files for the Speedrunner Mod enchantments.
 */
public class ModEnchantmentGenerator extends FabricDynamicRegistryProvider {

    public ModEnchantmentGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        HolderLookup<Item> itemLookup = registries.lookupOrThrow(Registries.ITEM);

        register(entries, ModEnchantments.DASH, Enchantment.enchantment(
                Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE), // The items that this enchantment can be applied to
                        11, // Probability of appearing in the enchantment table
                        3, // Maximum enchantment level
                        Enchantment.dynamicCost(1, 1), // Cost per level (base)
                        Enchantment.dynamicCost(1, 3), // Cost per level (maximum)
                        7, // Anvil applying cost
                        EquipmentSlotGroup.FEET))
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ofSpeedrunnerMod("dash_increased_speed"),
                                Attributes.MOVEMENT_SPEED,
                                LevelBasedValue.perLevel(0.1F),
                                AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                )
                .withEffect(
                        EnchantmentEffectComponents.ATTRIBUTES,
                        new EnchantmentAttributeEffect(
                                ofSpeedrunnerMod("dash_increased_water_movement"),
                                Attributes.WATER_MOVEMENT_EFFICIENCY,
                                LevelBasedValue.perLevel(0.05F),
                                AttributeModifier.Operation.ADD_VALUE
                        )
                )
        );

        register(entries, ModEnchantments.COOLDOWN, Enchantment.enchantment(
                        Enchantment.definition(
                                itemLookup.getOrThrow(ModItemTags.COOLDOWN_ENCHANTMENT_ITEMS),
                                10,
                                3,
                                Enchantment.dynamicCost(1, 1),
                                Enchantment.dynamicCost(1, 3),
                                5,
                                EquipmentSlotGroup.HAND)));

        register(entries, ModEnchantments.WITHERED, Enchantment.enchantment(
                        Enchantment.definition(
                                itemLookup.getOrThrow(ItemTags.SWORDS),
                                9,
                                5,
                                Enchantment.dynamicCost(1, 2),
                                Enchantment.dynamicCost(2, 5),
                                6,
                                EquipmentSlotGroup.MAINHAND))
        );
    }

    @Author(Authors.TURTYWURTY)
    private static void register(Entries entries, ResourceKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.identifier()), resourceConditions);
    }

    @Override
    public String getName() {
        return "Speedrunner Mod Enchantments";
    }
}