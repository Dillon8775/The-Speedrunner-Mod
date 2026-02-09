package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.fabricmc.fabric.api.resource.conditions.v1.ResourceCondition;
import net.minecraft.component.EnchantmentEffectComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelBasedValue;
import net.minecraft.enchantment.effect.AttributeEnchantmentEffect;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Used to create the JSON files for the Speedrunner Mod enchantments.
 */
public class ModEnchantmentGenerator extends FabricDynamicRegistryProvider {

    public ModEnchantmentGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup registries, Entries entries) {
        RegistryWrapper<Item> itemLookup = registries.getOrThrow(RegistryKeys.ITEM);

        register(entries, ModEnchantments.DASH, Enchantment.builder(
                Enchantment.definition(
                        itemLookup.getOrThrow(ItemTags.FOOT_ARMOR_ENCHANTABLE), // The items that this enchantment can be applied to
                        11, // Probability of appearing in the enchantment table
                        3, // Maximum enchantment level
                        Enchantment.leveledCost(1, 1), // Cost per level (base)
                        Enchantment.leveledCost(1, 3), // Cost per level (maximum)
                        7, // Anvil applying cost
                        AttributeModifierSlot.FEET))
                .addEffect(
                        EnchantmentEffectComponentTypes.ATTRIBUTES,
                        new AttributeEnchantmentEffect(
                                ofSpeedrunnerMod("dash_increased_speed"),
                                EntityAttributes.MOVEMENT_SPEED,
                                EnchantmentLevelBasedValue.linear(0.1F),
                                EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL
                        )
                )
                .addEffect(
                        EnchantmentEffectComponentTypes.ATTRIBUTES,
                        new AttributeEnchantmentEffect(
                                ofSpeedrunnerMod("dash_increased_water_movement"),
                                EntityAttributes.WATER_MOVEMENT_EFFICIENCY,
                                EnchantmentLevelBasedValue.linear(0.05F),
                                EntityAttributeModifier.Operation.ADD_VALUE
                        )
                )
        );

        register(entries, ModEnchantments.COOLDOWN, Enchantment.builder(
                        Enchantment.definition(
                                itemLookup.getOrThrow(ModItemTags.COOLDOWN_ENCHANTMENT_ITEMS),
                                10,
                                3,
                                Enchantment.leveledCost(1, 1),
                                Enchantment.leveledCost(1, 3),
                                5,
                                AttributeModifierSlot.HAND)));

        register(entries, ModEnchantments.WITHERED, Enchantment.builder(
                        Enchantment.definition(
                                itemLookup.getOrThrow(ItemTags.SWORDS),
                                9,
                                5,
                                Enchantment.leveledCost(1, 2),
                                Enchantment.leveledCost(2, 5),
                                6,
                                AttributeModifierSlot.MAINHAND))
        );
    }

    @Author(Authors.TURTYWURTY)
    private static void register(Entries entries, RegistryKey<Enchantment> key, Enchantment.Builder builder, ResourceCondition... resourceConditions) {
        entries.add(key, builder.build(key.getValue()), resourceConditions);
    }

    @Override
    public String getName() {
        return "Speedrunner Mod Enchantments";
    }
}