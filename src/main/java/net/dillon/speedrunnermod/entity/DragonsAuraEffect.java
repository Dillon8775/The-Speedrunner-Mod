package net.dillon.speedrunnermod.entity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * An effect which buffs the player in many ways.
 */
public class DragonsAuraEffect extends StatusEffect {

    protected DragonsAuraEffect(StatusEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(
                EntityAttributes.MAX_HEALTH,
                ofSpeedrunnerMod("max_health_dragons_aura"),
                10.0,
                EntityAttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                EntityAttributes.ATTACK_DAMAGE,
                ofSpeedrunnerMod("attack_damage_dragons_aura"),
                2.0,
                EntityAttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                EntityAttributes.ARMOR,
                ofSpeedrunnerMod("armor_dragons_aura"),
                8.0D,
                EntityAttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                EntityAttributes.ARMOR_TOUGHNESS,
                ofSpeedrunnerMod("armor_toughness_dragons_aura"),
                2.0D,
                EntityAttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                EntityAttributes.BLOCK_INTERACTION_RANGE,
                ofSpeedrunnerMod("block_interaction_range_dragons_aura"),
                1.0D,
                EntityAttributeModifier.Operation.ADD_VALUE
        );
    }

    /**
     * Plays the ender dragon sound.
     */
    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        entity.getEntityWorld().playSound(null, entity.getBlockPos(), SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.HOSTILE, 5.0F, 0.65F);
    }
}