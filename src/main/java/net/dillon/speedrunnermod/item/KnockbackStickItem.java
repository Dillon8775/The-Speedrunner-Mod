package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A knockback stick item. It does exactly what it says.
 */
public class KnockbackStickItem extends Item {

    public KnockbackStickItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(10).rarity(Rarity.EPIC));
    }

    /**
     * Makes it so that the knockback stick actually has the knockback enchantment.
     */
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!stack.hasEnchantments()) {
            stack.addEnchantment(ModUtil.worldEnchantment(world, Enchantments.KNOCKBACK), 5);
        }
    }

    /**
     * Return true when hitting an entity.
     */
    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        return true;
    }

    /**
     * Decrement durability when hitting an entity.
     */
    @Override
    public void postDamageEntity(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    /**
     * Always have a glint.
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.knockback_stick.tooltip"));
        }
    }
}