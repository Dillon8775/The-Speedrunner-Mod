package net.dillon.speedrunnermod.component.effect;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Inflicts withered on any target when the user has the effect.
 */
public class WitheredEffect extends MobEffect {

    public WitheredEffect(MobEffectCategory category, int color) {
        super(category, color);
        this.addAttributeModifier(
                Attributes.MAX_HEALTH,
                ofSpeedrunnerMod("max_health_withered"),
                -4.0,
                AttributeModifier.Operation.ADD_VALUE
        );
        this.addAttributeModifier(
                ModAttributes.WITHERED_EFFECT,
                ofSpeedrunnerMod("withered_effect_withered_potion"),
                2.5,
                AttributeModifier.Operation.ADD_VALUE
        );
    }
}