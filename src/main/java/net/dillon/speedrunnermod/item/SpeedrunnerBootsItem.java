package net.dillon.speedrunnermod.item;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.equipment.ArmorMaterial;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * The speedrunner boots, which makes the player faster.
 */
public class SpeedrunnerBootsItem extends Item {

    public SpeedrunnerBootsItem(ArmorMaterial material, int multiplier, Settings settings) {
        super(settings
                .maxCount(1)
                .maxDamage(EquipmentType.BOOTS.getMaxDamage(multiplier))
                .attributeModifiers(
                        AttributeModifiersComponent.builder()
                                .add(
                                        EntityAttributes.ARMOR,
                                        new EntityAttributeModifier(ofSpeedrunnerMod("armor_speedrunner_boots"), material.defense().get(EquipmentType.BOOTS), EntityAttributeModifier.Operation.ADD_VALUE),
                                        AttributeModifierSlot.FEET
                                )
                                .add(
                                        EntityAttributes.ARMOR_TOUGHNESS,
                                        new EntityAttributeModifier(ofSpeedrunnerMod("armor_toughness_speedrunner_boots"), material.toughness(), EntityAttributeModifier.Operation.ADD_VALUE),
                                        AttributeModifierSlot.FEET
                                )
                                .add(
                                        EntityAttributes.MOVEMENT_SPEED,
                                        new EntityAttributeModifier(ofSpeedrunnerMod("movement_speed_speedrunner_boots"), 0.2F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                        AttributeModifierSlot.FEET)
                                .add(
                                        EntityAttributes.WATER_MOVEMENT_EFFICIENCY,
                                        new EntityAttributeModifier(ofSpeedrunnerMod("water_movement_speedrunner_boots"), 0.15F, EntityAttributeModifier.Operation.ADD_VALUE),
                                        AttributeModifierSlot.FEET
                                )
                                .build()
                )
                .enchantable(material.enchantmentValue())
                .component(
                        DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.FEET).equipSound(material.equipSound()).model(material.assetId()).build()
                )
                .repairable(material.repairIngredient())
        );
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.speedrunner_boots.tooltip").formatted(Formatting.GRAY));
        super.appendTooltip(stack, context, displayComponent, textConsumer, type);
    }
}