package net.dillon.speedrunnermod.item.equipment;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * The speedrunner chestplate, which give extra protection from speedrunner-imperative mobs.
 */
public class SpeedrunnerChestplateItem extends Item {

    public SpeedrunnerChestplateItem(ArmorMaterial material, Properties properties, boolean golden) {
        ItemAttributeModifiers attributes = material.createAttributes(ArmorType.CHESTPLATE)
                .withModifierAdded(
                        ModAttributes.IMPERATIVE_PROTECTION,
                        new AttributeModifier(ofSpeedrunnerMod("damage_protection_speedrunner_chestplate"), 0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                        EquipmentSlotGroup.CHEST
                );

        if (golden) {
            attributes = attributes.withModifierAdded(
                    ModAttributes.PIGLIN_STEALTH,
                    new AttributeModifier(ofSpeedrunnerMod("piglin_protection_speedrunner_chestplate"), 1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                    EquipmentSlotGroup.CHEST
            );
        }

        super(properties
                .humanoidArmor(material, ArmorType.CHESTPLATE)
                .attributes(
                        attributes
                )
        );
    }
}