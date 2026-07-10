package net.dillon.speedrunnermod.item.equipment;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A nautilus item that makes nautiluses swim faster.
 */
public class SpeedrunnerNautilusArmorItem extends Item {

    public SpeedrunnerNautilusArmorItem(Properties properties, ArmorMaterial material, float moveSpeed, float dashSpeed) {
        super(properties
                .nautilusArmor(material)
                .attributes(
                        material.createAttributes(ArmorType.BODY)
                                .withModifierAdded(
                                        ModAttributes.BONUS_NAUTILUS_MOVEMENT_SPEED,
                                        new AttributeModifier(ofSpeedrunnerMod("happy_ghast_flying_speed_speedrunner_harness"), moveSpeed * 10, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.BODY
                                )
                                .withModifierAdded(
                                        ModAttributes.BONUS_NAUTILUS_DASH_SPEED,
                                        new AttributeModifier(ofSpeedrunnerMod("happy_ghast_flying_speed_speedrunner_harness"), dashSpeed, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.BODY
                                )
                )
        );
    }
}