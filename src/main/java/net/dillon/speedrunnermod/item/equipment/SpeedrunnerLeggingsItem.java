package net.dillon.speedrunnermod.item.equipment;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * The speedrunner leggings, which make the player move faster whilst shifting.
 */
public class SpeedrunnerLeggingsItem extends Item {

    public SpeedrunnerLeggingsItem(ArmorMaterial material, Properties properties, boolean golden) {
        ItemAttributeModifiers attributes = material.createAttributes(ArmorType.LEGGINGS)
                .withModifierAdded(
                        Attributes.SNEAKING_SPEED,
                        new AttributeModifier(ofSpeedrunnerMod("sneaking_speed_speedrunner_leggings"), 0.1F, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.LEGS
                );

        if (golden) {
            attributes = attributes.withModifierAdded(
                    ModAttributes.PIGLIN_STEALTH,
                    new AttributeModifier(ofSpeedrunnerMod("piglin_protection_speedrunner_leggings"), 1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                    EquipmentSlotGroup.LEGS
            );
        }

        super(properties
                .humanoidArmor(material, ArmorType.LEGGINGS)
                .attributes(
                        attributes
                )
        );
    }
}