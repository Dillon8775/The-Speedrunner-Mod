package net.dillon.speedrunnermod.item;

import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Rarity;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A knockback stick item. It does exactly what it says.
 */
public class KnockbackStickItem extends Item {

    public KnockbackStickItem(Settings settings) {
        super(settings
                .attributeModifiers(
                        AttributeModifiersComponent.builder()
                                .add(
                                        EntityAttributes.ATTACK_KNOCKBACK,
                                        new EntityAttributeModifier(ofSpeedrunnerMod("speedrunner_knockback_stick"), 5.0F, EntityAttributeModifier.Operation.ADD_VALUE),
                                        AttributeModifierSlot.MAINHAND)
                                .build()
                )
                .maxCount(1)
                .maxDamage(17)
                .rarity(Rarity.EPIC));
    }

    /**
     * Decrement durability when hitting an entity.
     */
    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, EquipmentSlot.MAINHAND);
    }

    /**
     * Always have a glint.
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}