package net.dillon.speedrunnermod.item.tool;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.AttackRange;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * The speedrunner spear, with more block reach and increased momentum from the lunge enchantment.
 */
public class SpeedrunnerSpearItem extends Item {

    public SpeedrunnerSpearItem(Properties properties, ToolMaterial material,
                                float maxReach,
                                float maxCreativeReach,
                                float increasedMomentumMultiplier,
                                float attackDuration,
                                float damageMultiplier,
                                float delay,
                                float dismountTime,
                                float dismountThreshold,
                                float knockbackTime,
                                float damageTime) {
        super(properties
                .spear(
                        material,
                        attackDuration,
                        damageMultiplier,
                        delay,
                        dismountTime,
                        dismountThreshold,
                        knockbackTime,
                        5.1F,
                        damageTime,
                        4.6F
                )
                .component(DataComponents.ATTACK_RANGE,
                        new AttackRange(3.0F, maxReach, 3.0F, maxCreativeReach, 0.125F, 0.5F))
                .attributes(
                        ItemAttributeModifiers.builder()
                                .add(
                                        Attributes.ATTACK_DAMAGE,
                                        new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, 0.0F + material.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .add(
                                        Attributes.ATTACK_SPEED,
                                        new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, 1.0F / attackDuration - 4.0, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .add(
                                        ModAttributes.BONUS_SPEAR_REACH,
                                        new AttributeModifier(ofSpeedrunnerMod("spear_reach_speedrunner_spear"), maxReach - 4.5F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .add(
                                        ModAttributes.BONUS_SPEAR_CREATIVE_REACH,
                                        new AttributeModifier(ofSpeedrunnerMod("spear_creative_reach_speedrunner_spear"), maxCreativeReach - 6.5F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .add(
                                        ModAttributes.BONUS_SPEAR_LUNGE_MOMENTUM,
                                        new AttributeModifier(ofSpeedrunnerMod("spear_lunge_momentum_speedrunner_spear"), increasedMomentumMultiplier, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .add(
                                        ModAttributes.BONUS_SPEAR_LUNGE_EXHAUSTION,
                                        new AttributeModifier(ofSpeedrunnerMod("spear_lunge_exhaustion_speedrunner_spear"), -0.7F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND
                                )
                                .build()
                )
        );
    }
}