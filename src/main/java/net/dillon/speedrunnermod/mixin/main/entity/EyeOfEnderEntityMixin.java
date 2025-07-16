package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.entity.ModStatuses;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.particle.ModParticleTypes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EyeOfEnderEntity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(EyeOfEnderEntity.class)
public abstract class EyeOfEnderEntityMixin extends Entity {
    @Shadow
    private int lifespan;
    @Shadow
    public abstract ItemStack getStack();

    public EyeOfEnderEntityMixin(EntityType<? extends EyeOfEnderEntity> type, World world) {
        super(type, world);
    }

    /**
     * Changes the particle emitted when the eye is floating.
     */
    @Redirect(method = "addParticles", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/World;addParticleClient(Lnet/minecraft/particle/ParticleEffect;DDDDDD)V", ordinal = 1))
    private void changeEmittedParticle(World instance, ParticleEffect parameters, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
        instance.addParticleClient(this.getStack().isOf(ModItems.INFERNO_EYE) ?
                ParticleTypes.SMOKE :
                this.getStack().isOf(ModItems.SPEEDRUNNERS_EYE) ?
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
    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/EyeOfEnderEntity;playSound(Lnet/minecraft/sound/SoundEvent;FF)V"), cancellable = true)
    private void eyeOfEnderFunctions(CallbackInfo ci) {
        if (this.lifespan > options().getEnderEyeBreakingCooldown() && !this.getWorld().isClient) {
            if (this.getStack().getItem() == Items.ENDER_EYE || this.getStack().getItem() == ModItems.SPEEDRUNNERS_EYE) {
                this.playSound(SoundEvents.ENTITY_ENDER_EYE_DEATH, 1.0F, 1.0F);
            } else if (this.getStack().getItem() == ModItems.INFERNO_EYE) {
                this.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1.0F, 1.0F);
            }
            this.discard(); // cancel out vanilla code
            ci.cancel();
            if (isDoomMode()) {
                if (this.getStack().getItem() == Items.ENDER_EYE) {
                    this.getWorld().syncWorldEvent(WorldEvents.EYE_OF_ENDER_BREAKS, this.getBlockPos(), 0);
                } else if (this.getStack().getItem() == ModItems.INFERNO_EYE) {
                    this.getWorld().syncWorldEvent(ModStatuses.ADD_SMOKE_PARTICLES, this.getBlockPos(), 0);
                } else if (this.getStack().getItem() == ModItems.SPEEDRUNNERS_EYE) {
                    this.getWorld().syncWorldEvent(ModStatuses.ADD_BLUE_PORTAL_PARTICLES_FOR_SPEEDRUNNERS_EYE, this.getBlockPos(), 0);
                }
            } else {
                this.getWorld().spawnEntity(new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), this.getStack()));
            }
        }
    }
}