package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.TooltipDisplay;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * An item that preserves your inventory upon death.
 */
public class InventoryPreserverItem extends Item {

    public InventoryPreserverItem(Properties properties) {
        super(properties
                .stacksTo(1)
                .durability(1)
                .rarity(Rarity.RARE)
                .attributes(
                        ItemAttributeModifiers.builder()
                                .add(
                                        ModAttributes.INVENTORY_PRESERVATION,
                                        new AttributeModifier(ofSpeedrunnerMod("inventory_preservation_inventory_preserver"), 1.0F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                        EquipmentSlotGroup.ANY
                                )
                                .build()
                )
        );
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.inventory_preserver.tooltip"));
        super.appendHoverText(stack, context, displayComponent, textConsumer, type);
    }
}