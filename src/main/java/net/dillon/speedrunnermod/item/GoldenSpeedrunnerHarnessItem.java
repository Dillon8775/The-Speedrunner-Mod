package net.dillon.speedrunnermod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.EquipmentAsset;

import java.util.function.Consumer;

/**
 * A harness that flies even faster than a normal speedrunner harness.
 */
public class GoldenSpeedrunnerHarnessItem extends SpeedrunnerHarnessItem {

    public GoldenSpeedrunnerHarnessItem(Properties properties, ResourceKey<EquipmentAsset> equipmentAsset, float flyingSpeedDivider) {
        super(properties, equipmentAsset, flyingSpeedDivider);
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.golden_speedrunner_harness.tooltip"));
    }
}