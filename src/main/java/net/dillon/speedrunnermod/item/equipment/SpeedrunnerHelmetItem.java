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
 * The speedrunner helmet, which allows the player to breath longer underwater.
 */
public class SpeedrunnerHelmetItem extends Item {

    public SpeedrunnerHelmetItem(ArmorMaterial material, Properties properties, boolean golden) {
        ItemAttributeModifiers attributes = material.createAttributes(ArmorType.HELMET)
                .withModifierAdded(
                        Attributes.OXYGEN_BONUS,
                        new AttributeModifier(ofSpeedrunnerMod("oxygen_bonus_speedrunner_helmet"), 1.0F, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.HEAD
                )
                .withModifierAdded(
                        ModAttributes.BONUS_AIR_RECOVERY,
                        new AttributeModifier(ofSpeedrunnerMod("air_recovery_speedrunner_helmet"), 2.0F, AttributeModifier.Operation.ADD_VALUE),
                        EquipmentSlotGroup.HEAD
                )
                .withModifierAdded(
                        Attributes.SUBMERGED_MINING_SPEED,
                        new AttributeModifier(ofSpeedrunnerMod("submerged_mining_speed_speedrunner_helmet"), 2.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                        EquipmentSlotGroup.HEAD
                )
                .withModifierAdded(
                        ModAttributes.UNDERWATER_VISION,
                        new AttributeModifier(ofSpeedrunnerMod("underwater_vision_speedrunner_helmet"), 0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                        EquipmentSlotGroup.HEAD
                );

        if (golden) {
            attributes = attributes.withModifierAdded(
                    ModAttributes.PIGLIN_STEALTH,
                    new AttributeModifier(ofSpeedrunnerMod("piglin_protection_speedrunner_helmet"), 1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                    EquipmentSlotGroup.HEAD
            );
        }

        super(properties
                .humanoidArmor(material, ArmorType.HELMET)
                .attributes(attributes)
        );
    }
}