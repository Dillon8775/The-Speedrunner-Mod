package net.dillon.speedrunnermod.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.equipment.ArmorMaterial;

import java.util.function.Consumer;

/**
 * A nautilus item that makes nautiluses swim faster.
 */
public class SpeedrunnerNautilusArmorItem extends Item {
    private final float moveSpeed;
    private final float dashSpeed;

    public SpeedrunnerNautilusArmorItem(Properties properties, ArmorMaterial armorMaterial, float moveSpeed, float dashSpeed) {
        super(properties.stacksTo(1).nautilusArmor(armorMaterial));
        this.moveSpeed = moveSpeed;
        this.dashSpeed = dashSpeed;
    }

    /**
     * @return the movement speed for a nautilus wearing this armor.
     */
    public float getMoveSpeed() {
        return this.moveSpeed;
    }

    /**
     * @return the dash speed for a nautilus wearing this armor.
     */
    public float getDashSpeed() {
        return this.dashSpeed;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        SpeedrunnerItem.addWrappedTooltip(textConsumer, Component.translatable("item.speedrunnermod.speedrunner_nautilus_armor.tooltip"));
    }
}