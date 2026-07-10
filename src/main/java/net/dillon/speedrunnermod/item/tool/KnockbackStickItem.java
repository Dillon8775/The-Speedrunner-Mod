package net.dillon.speedrunnermod.item.tool;

import net.dillon.speedrunnermod.advancement.ModPredicates;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.component.ItemAttributeModifiers;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A knockback stick item. It does exactly what it says.
 */
public class KnockbackStickItem extends Item {

    public KnockbackStickItem(Properties settings) {
        super(settings
                .attributes(
                        ItemAttributeModifiers.builder()
                                .add(
                                        Attributes.ATTACK_KNOCKBACK,
                                        new AttributeModifier(ofSpeedrunnerMod("speedrunner_knockback_stick"), 5.0F, AttributeModifier.Operation.ADD_VALUE),
                                        EquipmentSlotGroup.MAINHAND)
                                .build()
                )
                .stacksTo(1)
                .durability(17)
                .rarity(Rarity.EPIC));
    }

    /**
     * Decrement durability when hitting an entity.
     */
    @Override
    public void hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (attacker instanceof ServerPlayer serverPlayer) {
            ModPredicates.TRIGGERED_BY_ITEMLIKE.trigger(serverPlayer, this.getDefaultInstance());
        }
        stack.hurtAndBreak(1, attacker, EquipmentSlot.MAINHAND);
    }

    /**
     * Always have a glint.
     */
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }
}