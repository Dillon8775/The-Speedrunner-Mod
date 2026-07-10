package net.dillon.speedrunnermod.component.effect;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * An effect which buffs the player in many ways.
 */
public class DragonsAuraEffect extends MobEffect {

    public DragonsAuraEffect(MobEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(
                Attributes.MAX_HEALTH,
                ofSpeedrunnerMod("max_health_dragons_aura"),
                10.0,
                AttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                Attributes.ATTACK_DAMAGE,
                ofSpeedrunnerMod("attack_damage_dragons_aura"),
                2.0,
                AttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                Attributes.ARMOR,
                ofSpeedrunnerMod("armor_dragons_aura"),
                8.0D,
                AttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                Attributes.ARMOR_TOUGHNESS,
                ofSpeedrunnerMod("armor_toughness_dragons_aura"),
                2.0D,
                AttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                Attributes.BLOCK_INTERACTION_RANGE,
                ofSpeedrunnerMod("block_interaction_range_dragons_aura"),
                1.0D,
                AttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                Attributes.WATER_MOVEMENT_EFFICIENCY,
                ofSpeedrunnerMod("water_movement_efficiency_dragons_aura"),
                2.0D,
                AttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                ModAttributes.LAVA_MOVEMENT_EFFICIENCY,
                ofSpeedrunnerMod("lava_movement_efficiency_dragons_aura"),
                0.1,
                AttributeModifier.Operation.ADD_VALUE
        );
    }

    /**
     * Plays the ender dragon sound.
     */
    @Override
    public void onEffectStarted(LivingEntity entity, int amplifier) {
        entity.level().playSound(null, entity.blockPosition(), SoundEvents.ENDER_DRAGON_GROWL, SoundSource.HOSTILE, 5.0F, 0.65F);
    }
}