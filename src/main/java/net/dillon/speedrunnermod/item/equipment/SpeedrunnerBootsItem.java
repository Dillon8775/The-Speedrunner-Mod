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
 * The speedrunner boots, which makes the player faster.
 */
public class SpeedrunnerBootsItem extends Item {

    public SpeedrunnerBootsItem(ArmorMaterial material, Properties settings, boolean golden) {
        ItemAttributeModifiers attributes = material.createAttributes(ArmorType.BOOTS)
                .withModifierAdded(
                        Attributes.MOVEMENT_SPEED,
                        new AttributeModifier(ofSpeedrunnerMod("movement_speed_speedrunner_boots"), 0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                        EquipmentSlotGroup.FEET
                )
                .withModifierAdded(
                        ModAttributes.SHRIEKER_STEALTH,
                        new AttributeModifier(ofSpeedrunnerMod("sculk_shrieker_protection_speedrunner_boots"), 1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                        EquipmentSlotGroup.FEET
                )
                .withModifierAdded(
                        Attributes.WATER_MOVEMENT_EFFICIENCY,
                        new AttributeModifier(ofSpeedrunnerMod("water_movement_speedrunner_boots"), 0.15F, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.FEET
                )
                .withModifierAdded(
                        ModAttributes.LAVA_MOVEMENT_EFFICIENCY,
                        new AttributeModifier(ofSpeedrunnerMod("lava_movement_speedrunner_boots"), 0.1F, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.FEET
                );

        if (golden) {
            attributes = attributes.withModifierAdded(
                    ModAttributes.PIGLIN_STEALTH,
                    new AttributeModifier(ofSpeedrunnerMod("piglin_protection_speedrunner_boots"), 1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                    EquipmentSlotGroup.FEET
            );
        }

        super(settings
                .humanoidArmor(material, ArmorType.BOOTS)
                .attributes(
                     attributes
                )
        );
    }
}