package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import static net.dillon.speedrunnermod.main.CommonMain.ofSpeedrunnerMod;

/**
 * An item that preserves your inventory upon death.
 */
public class InventoryPreserverItem extends TooltipItem {

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
                ),
                Component.translatable("item.speedrunnermod.inventory_preserver.tooltip")
        );
    }
}