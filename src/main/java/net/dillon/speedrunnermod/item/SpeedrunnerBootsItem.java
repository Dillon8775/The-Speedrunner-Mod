package net.dillon.speedrunnermod.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.Equippable;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * The speedrunner boots, which makes the player faster.
 */
public class SpeedrunnerBootsItem extends Item {

    public SpeedrunnerBootsItem(ArmorMaterial material, int multiplier, Properties settings) {
        super(settings
                .stacksTo(1)
                .durability(ArmorType.BOOTS.getDurability(multiplier))
                .attributes(
                        ItemAttributeModifiers.builder()
                                .add(
                                        Attributes.ARMOR,
                                        new AttributeModifier(ofSpeedrunnerMod("armor_speedrunner_boots"), material.defense().get(ArmorType.BOOTS), AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.FEET
                                )
                                .add(
                                        Attributes.ARMOR_TOUGHNESS,
                                        new AttributeModifier(ofSpeedrunnerMod("armor_toughness_speedrunner_boots"), material.toughness(), AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.FEET
                                )
                                .add(
                                        Attributes.MOVEMENT_SPEED,
                                        new AttributeModifier(ofSpeedrunnerMod("movement_speed_speedrunner_boots"), 0.2F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                        EquipmentSlotGroup.FEET)
                                .add(
                                        Attributes.WATER_MOVEMENT_EFFICIENCY,
                                        new AttributeModifier(ofSpeedrunnerMod("water_movement_speedrunner_boots"), 0.15F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.FEET
                                )
                                .build()
                )
                .enchantable(material.enchantmentValue())
                .component(
                        DataComponents.EQUIPPABLE, Equippable.builder(EquipmentSlot.FEET).setEquipSound(material.equipSound()).setAsset(material.assetId()).build()
                )
                .repairable(material.repairIngredient())
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable("item.speedrunnermod.speedrunner_boots.tooltip").withStyle(ChatFormatting.GRAY));
        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
    }
}