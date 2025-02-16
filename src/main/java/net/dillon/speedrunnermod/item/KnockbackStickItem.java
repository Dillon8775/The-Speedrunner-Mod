package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
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
        super(settings.maxCount(1).rarity(Rarity.EPIC));
    }

    /**
     * Makes it so that the knockback stick actually has the knockback enchantment.
     */
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!stack.hasEnchantments()) {
            stack.addEnchantment(ItemUtil.worldEnchantment(world, Enchantments.KNOCKBACK), 5);
        }
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