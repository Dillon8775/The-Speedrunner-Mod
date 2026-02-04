package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.entity.ModStatusEffects;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AreaEffectCloudEntity.class)
public abstract class AreaEffectCloudEntityMixin {

    /**
     * Prevents the applied instant damage from being applied to entities with the {@code Dragon's Aura} effect.
     */
    @Redirect(method = "serverTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;addStatusEffect(Lnet/minecraft/entity/effect/StatusEffectInstance;Lnet/minecraft/entity/Entity;)Z"))
    private boolean immuneWithDragonsAuraApply(LivingEntity living, StatusEffectInstance effect, Entity source) {
        if (living.hasStatusEffect(ModStatusEffects.DRAGONS_AURA)) {
            return false;
        }

        return living.addStatusEffect(effect, source);
    }

    /**
     * Prevents the instant applied damage from being applied to entities with the {@code Dragon's Aura} effect.
     */
    @Redirect(method = "serverTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffect;applyInstantEffect(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/Entity;Lnet/minecraft/entity/LivingEntity;ID)V"))
    private void immuneWithDragonsAuraInstant(StatusEffect instance, ServerWorld world, Entity effectEntity, Entity attacker, LivingEntity target, int amplifier, double proximity) {
        if (target.hasStatusEffect(ModStatusEffects.DRAGONS_AURA)) {
            return;
        }

        instance.applyInstantEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }
}