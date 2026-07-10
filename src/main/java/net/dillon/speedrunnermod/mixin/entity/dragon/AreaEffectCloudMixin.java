package net.dillon.speedrunnermod.mixin.entity.dragon;

import net.dillon.speedrunnermod.component.ModMobEffects;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.PowerParticleOption;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.alchemy.PotionContents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(AreaEffectCloud.class)
public abstract class AreaEffectCloudMixin {
    @Shadow
    private PotionContents potionContents;
    @Shadow
    public abstract ParticleOptions getParticle();

    /**
     * Prevents the applied instant damage from being applied to entities with the {@code Dragon's Aura} effect.
     */
    @Redirect(method = "serverTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;addEffect(Lnet/minecraft/world/effect/MobEffectInstance;Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean immuneWithDragonsAuraApply(LivingEntity living, MobEffectInstance effect, Entity source) {
        for (MobEffectInstance e : this.potionContents.getAllEffects()) {
            if (this.canIgnoreDamage(e, living)) {
                return false;
            }
        }

        return living.addEffect(effect, source);
    }

    /**
     * Prevents the instant applied damage from being applied to entities with the {@code Dragon's Aura} effect.
     */
    @Redirect(method = "serverTick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffect;applyInstantaneousEffect(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/entity/LivingEntity;ID)V"))
    private void immuneWithDragonsAuraInstant(MobEffect instance, ServerLevel world, Entity effectEntity, Entity attacker, LivingEntity target, int amplifier, double proximity) {
        for (MobEffectInstance e : this.potionContents.getAllEffects()) {
            if (this.canIgnoreDamage(e, target)) {
                return;
            }
        }

        instance.applyInstantaneousEffect(world, effectEntity, attacker, target, amplifier, proximity);
    }

    /**
     * @return if an entity can ignore the {@code dragon's breath} effects.
     */
    @Unique
    private boolean canIgnoreDamage(MobEffectInstance e, LivingEntity living) {
        return living.hasEffect(ModMobEffects.DRAGONS_AURA) && e.getEffect() == MobEffects.INSTANT_DAMAGE && this.getParticle() instanceof PowerParticleOption;
    }
}