package net.dillon.speedrunnermod.mixin.entity;

import net.dillon.speedrunnermod.effect.ModStatusEffects;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.item.InfiniPearlItem;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrowableItemProjectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(ThrownEnderpearl.class)
public abstract class ThrownEnderpearlMixin extends ThrowableItemProjectile {

    public ThrownEnderpearlMixin(EntityType<? extends ThrownEnderpearl> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Stops the default portal particles from spawning.
     */
    @Redirect(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V"))
    private void stopParticles(Level instance, ParticleOptions parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        return;
    }

    /**
     * Prevents {@code endermite} from spawning if the thrown item was an {@code InfiniPearl.}
     */
    @Redirect(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean preventEndermiteFromSpawning(ServerLevel instance, Entity entity) {
        return !this.isInfiniPearl() && instance.addFreshEntity(entity);
    }

    /**
     * Prevents the player from taking damage if using an {@code InfiniPearl,} and also adds other side-effects to the ender pearl.
     */
    @Redirect(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerPlayer;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private boolean cancelOutDamageAndAddEffects(ServerPlayer player, ServerLevel world, DamageSource source, float amount) {
        if (this.isInfiniPearl()) {
            return false;
        }

        if (isDoomMode() && !player.hasEffect(ModStatusEffects.DRAGONS_AURA) && !(player.isCreative() || player.isSpectator())) {
            player.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, ModUtil.secondsAsTicks(3), 0));
        }
        return player.hurtServer(player.level(), this.damageSources().enderPearl(), ModUtil.getEnderPearlDamageValue());
    }

    /**
     * Spawns particles around the player when landing.
     */
    @Inject(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/throwableitemprojectile/ThrownEnderpearl;playSound(Lnet/minecraft/world/level/Level;Lnet/minecraft/world/phys/Vec3;)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private void sendParticleStatus(HitResult hitResult, CallbackInfo ci, ServerLevel serverWorld, Entity entity, Vec3 vec3d) {
        if (entity instanceof ServerPlayer player) {
            this.level().broadcastEntityEvent(player, this.isInfiniPearl() ? ModStatuses.ADD_INFINI_PEARL_LANDING_PARTICLES : ModStatuses.ADD_PEARL_LANDING_PARTICLES);
        }
    }

    /**
     * Doubles entity hit damage when throwing an infini pearl.
     */
    @ModifyConstant(method = "onHitEntity", constant = @Constant(floatValue = 0.0F))
    private float doubleDamage(float constant) {
        return this.isInfiniPearl() ? 3.0F : constant;
    }

    /**
     * Adds {@code ender} particles around {@code InfiniPearls.}
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void addBluePortalParticlesInfiniPearl(CallbackInfo ci) {
        if (super.getItem().is(ModItems.INFINI_PEARL)) {
            if (!this.isCurrentlyGlowing()) {
                this.setGlowingTag(true);
            }
            this.level().broadcastEntityEvent(this, ModStatuses.ADD_TRAIL_BLUE_PORTAL_PARTICLES);
        }
    }

    /**
     * @return if the stack thrown is a {@link InfiniPearlItem}.
     */
    @Unique
    private boolean isInfiniPearl() {
        return super.getItem().is(ModItems.INFINI_PEARL);
    }
}