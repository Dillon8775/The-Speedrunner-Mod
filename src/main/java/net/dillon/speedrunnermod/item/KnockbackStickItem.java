package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Rarity;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * A knockback stick item. It does exactly what it says.
 */
public class KnockbackStickItem extends Item  {

    public KnockbackStickItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(10).rarity(Rarity.EPIC));
    }

    /**
     * Makes it so that the knockback stick actually has the knockback enchantment.
     */
    @Override
    public void inventoryTick(ItemStack stack, ServerWorld world, Entity entity, @Nullable EquipmentSlot slot) {
        if (!stack.hasEnchantments()) {
            stack.addEnchantment(ModUtil.worldEnchantment(world, Enchantments.KNOCKBACK), 5);
        }
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
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.knockback_stick.tooltip"));
    }
}