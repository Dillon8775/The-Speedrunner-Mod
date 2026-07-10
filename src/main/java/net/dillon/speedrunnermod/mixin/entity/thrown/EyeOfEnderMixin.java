package net.dillon.speedrunnermod.mixin.entity.thrown;

import net.dillon.speedrunnermod.entity.ModParticleTypes;
import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.EyeOfEnder;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LevelEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(EyeOfEnder.class)
public abstract class EyeOfEnderMixin extends Entity {
    @Shadow
    private int life;
    @Shadow
    public abstract ItemStack getItem();

    public EyeOfEnderMixin(EntityType<? extends EyeOfEnder> type, Level world) {
        super(type, world);
    }

    /**
     * Changes the particle emitted when the eye is floating.
     */
    @Redirect(method = "spawnParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addParticle(Lnet/minecraft/core/particles/ParticleOptions;DDDDDD)V", ordinal = 1))
    private void changeEmittedParticle(Level instance, ParticleOptions parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        instance.addParticle(this.getItem().is(ModItemTags.SMOKE_EYE_PARTICLES) ?
                ParticleTypes.SMOKE :
                this.getItem().is(ModItemTags.BLUE_EYE_PARTICLES) ?
                ModParticleTypes.BLUE_PORTAL :
                ParticleTypes.PORTAL, x, y, z, velocityX, velocityY, velocityZ);
    }

    /**
     * Applies the new {@code ender eye breaking cooldown.}
     */
    @ModifyConstant(method = "tick", constant = @Constant(intValue = 80))
    private int changeEnderEyeBreakingCooldown(int constant) {
        return options().getEnderEyeBreakingCooldown();
    }

    /**
     * Changes the function of the eye of ender, and applies different effects to it in certain modes, based off what type it is.
     */
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/projectile/EyeOfEnder;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"), cancellable = true)
    private void eyeOfEnderFunctions(CallbackInfo ci) {
        if (this.life > options().getEnderEyeBreakingCooldown() && !this.level().isClientSide()) {
            if (this.getItem().is(ModItemTags.ENDER_EYE_DEATH_SOUND)) {
                this.playSound(SoundEvents.ENDER_EYE_DEATH, 1.0F, 1.0F);
            } else if (this.getItem().is(ModItemTags.FIRECHARGE_SOUND)) {
                this.playSound(SoundEvents.FIRECHARGE_USE, 1.0F, 0.4F);
            }

            this.discard();
            ci.cancel();

            if (isDoomMode()) {
                if (this.getItem().is(Items.ENDER_EYE)) {
                    this.level().levelEvent(LevelEvent.PARTICLES_EYE_OF_ENDER_DEATH, this.blockPosition(), 0);
                } else if (this.getItem().is(ModItemTags.PURPLE_EYE_PARTICLES)) {
                    this.level().levelEvent(ModStatuses.ADD_PURPLE_PARTICLES, this.blockPosition(), 0);
                } else if (this.getItem().is(ModItemTags.SMOKE_EYE_PARTICLES)) {
                    this.level().levelEvent(ModStatuses.ADD_SMOKE_PARTICLES, this.blockPosition(), 0);
                } else if (this.getItem().is(ModItemTags.BLUE_EYE_PARTICLES)) {
                    this.level().levelEvent(ModStatuses.ADD_BLUE_PORTAL_PARTICLES_FOR_SPEEDRUNNERS_EYE, this.blockPosition(), 0);
                }
            } else {
                this.level().addFreshEntity(new ItemEntity(this.level(), this.getX(), this.getY(), this.getZ(), this.getItem()));
            }
        }
    }
}