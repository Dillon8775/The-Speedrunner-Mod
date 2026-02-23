package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.entity.ModStatusEffects;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.item.InfiniPearlItem;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(EnderPearlEntity.class)
public abstract class EnderPearlEntityMixin extends ThrownItemEntity {

    public EnderPearlEntityMixin(EntityType<? extends EnderPearlEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Stops the default portal particles from spawning.
     */
    @Redirect(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticleClient(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V"))
    private void stopParticles(World instance, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        return;
    }

    /**
     * Prevents {@code endermite} from spawning if the thrown item was an {@code InfiniPearl.}
     */
    @Redirect(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;spawnEntity(Lnet/minecraft/entity/Entity;)Z"))
    private boolean preventEndermiteFromSpawning(ServerWorld instance, Entity entity) {
        return !this.isInfiniPearl() && instance.spawnEntity(entity);
    }

    /**
     * Prevents the player from taking damage if using an {@code InfiniPearl,} and also adds other side-effects to the ender pearl.
     */
    @Redirect(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private boolean cancelOutDamageAndAddEffects(ServerPlayerEntity player, ServerWorld world, DamageSource source, float amount) {
        if (this.isInfiniPearl()) {
            return false;
        }

        if (isDoomMode() && !player.hasStatusEffect(ModStatusEffects.DRAGONS_AURA) && !(player.isCreative() || player.isSpectator())) {
            player.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, ModUtil.secondsAsTicks(3), 0));
        }
        return player.damage(player.getEntityWorld(), this.getDamageSources().enderPearl(), ModUtil.getEnderPearlDamageValue());
    }

    /**
     * Spawns particles around the player when landing.
     */
    @Inject(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/projectile/thrown/EnderPearlEntity;playTeleportSound(Lnet/minecraft/world/World;Lnet/minecraft/util/math/Vec3d;)V", ordinal = 0), locals = LocalCapture.CAPTURE_FAILHARD)
    private void sendParticleStatus(HitResult hitResult, CallbackInfo ci, ServerWorld serverWorld, Entity entity, Vec3d vec3d) {
        if (entity instanceof ServerPlayerEntity player) {
            this.getEntityWorld().sendEntityStatus(player, this.isInfiniPearl() ? ModStatuses.ADD_INFINI_PEARL_LANDING_PARTICLES : ModStatuses.ADD_PEARL_LANDING_PARTICLES);
        }
    }

    /**
     * Doubles entity hit damage when throwing an infini pearl.
     */
    @ModifyConstant(method = "onEntityHit", constant = @Constant(floatValue = 0.0F))
    private float doubleDamage(float constant) {
        return this.isInfiniPearl() ? 3.0F : constant;
    }

    /**
     * Adds {@code ender} particles around {@code InfiniPearls.}
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void addBluePortalParticlesInfiniPearl(CallbackInfo ci) {
        if (super.getStack().isOf(ModItems.INFINI_PEARL)) {
            if (!this.isGlowing()) {
                this.setGlowing(true);
            }
            this.getEntityWorld().sendEntityStatus(this, ModStatuses.ADD_TRAIL_BLUE_PORTAL_PARTICLES);
        }
    }

    /**
     * @return if the stack thrown is a {@link InfiniPearlItem}.
     */
    @Unique
    private boolean isInfiniPearl() {
        return super.getStack().isOf(ModItems.INFINI_PEARL);
    }
}