package net.dillon.speedrunnermod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorMaterial;

import java.util.function.Consumer;

/**
 * A nautilus armor item which makes nautiluses traverse even faster.
 */
public class GoldenSpeedrunnerNautilusArmorItem extends SpeedrunnerNautilusArmorItem {

    public GoldenSpeedrunnerNautilusArmorItem(Properties properties, ArmorMaterial armorMaterial, float moveSpeed, float dashSpeed) {
        super(properties, armorMaterial, moveSpeed, dashSpeed);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.golden_speedrunner_nautilus_armor.tooltip"));
    }
}