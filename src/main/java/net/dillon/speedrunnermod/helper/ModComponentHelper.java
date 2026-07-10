package net.dillon.speedrunnermod.helper;

import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.dillon.speedrunnermod.component.ModAttributes;
import net.dillon.speedrunnermod.component.ModEnchantments;
import net.dillon.speedrunnermod.item.InfiniPearlItem;
import net.dillon.speedrunnermod.item.equipment.ModShieldItem;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.ArrayList;

/**
 * Component utility methods.
 */
public class ModComponentHelper {

    /**
     * @return {@code true} if dragons aura was found.
     */
    public static boolean hasDragonsAura(ItemStack stack) {
        return hasEffect(stack, "speedrunnermod:dragons_aura");
    }

    /**
     * @return {@code true} if withered was found.
     */
    public static boolean hasWithered(ItemStack stack) {
        return hasEffect(stack, "speedrunnermod:withered");
    }

    /**
     * @return {@code true} if luck was found.
     */
    public static boolean hasLuck(ItemStack stack) {
        return hasEffect(stack, "minecraft:luck");
    }

    /**
     * @return {@code true} if effect was found.
     */
    public static boolean hasEffect(ItemStack stack, String potionName) {
        if (stack.get(DataComponents.POTION_CONTENTS) == null) {
            return false;
        }

        for (MobEffectInstance slotEffect : stack.get(DataComponents.POTION_CONTENTS).getAllEffects()) {
            if (slotEffect.getEffect().is(Identifier.parse(potionName))) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return if an enchantment was found.
     */
    public static boolean hasEnchantment(ItemStack stack, ResourceKey<Enchantment> enchantment) {
        ItemEnchantments itemEnchantmentsComponent = EnchantmentHelper.getEnchantmentsForCrafting(stack);
        for (Object2IntMap.Entry<Holder<Enchantment>> entry : itemEnchantmentsComponent.entrySet()) {
            if (entry.getKey().is(enchantment)) {
                return true;
            }
        }
        return false;
    }

    /**
     * @return a check for an enchantment level.
     */
    private static int checkForEnchantmentLevel(Player player, ResourceKey<Enchantment> enchantment, boolean includeOffhand) {
        ArrayList<EquipmentSlot> equipmentSlots = new ArrayList<>();
        equipmentSlots.add(EquipmentSlot.MAINHAND);
        if (includeOffhand) {
            equipmentSlots.add(EquipmentSlot.OFFHAND);
        }

        int bestLevel = 0;
        for (EquipmentSlot slot : equipmentSlots) {
            int newLevel = EnchantmentHelper.getItemEnchantmentLevel(ModHelper.enchantment(player, enchantment), player.getItemBySlot(slot));
            if (newLevel > bestLevel) {
                bestLevel = newLevel;
            }
        }

        return bestLevel;
    }

    /**
     * Applies the correct stack cooldown.
     */
    public static void applyNewItemCooldown(Player playerEntity, ItemStack stack) {
        playerEntity.getCooldowns().addCooldown(stack, getItemCooldown(stack, playerEntity));
    }

    /**
     * @return the stack cooldown with the cooldown enchantment, with no custom addition.
     */
    public static int getItemCooldown(ItemStack stack, Player player) {
        float additionalCooldown = (float)player.getAttributeValue(ModAttributes.BONUS_COOLDOWN);
        float finalCooldown = 20;
        int cooldownLevel;

        if (stack.getItem() instanceof ShieldItem shieldItem) {
            if (shieldItem instanceof ModShieldItem modShieldItem) {
                finalCooldown -= Math.abs(modShieldItem.getCooldownSeconds()) - 1;
            }

            cooldownLevel = checkForEnchantmentLevel(player, ModEnchantments.COOLDOWN, true);
            finalCooldown -= cooldownLevel * (shieldItem instanceof ModShieldItem ? 3 : 2);
        } else if (stack.getItem() instanceof InfiniPearlItem) {
            finalCooldown += additionalCooldown * 20;
        } else {
            finalCooldown -= (Math.abs(additionalCooldown)) * 10;
        }

        if (finalCooldown < 3) {
            finalCooldown = 3;
        }

        return (int)finalCooldown;
    }
}