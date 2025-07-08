package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.IllagerEntity;
import net.minecraft.entity.mob.VindicatorEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(VindicatorEntity.class)
public abstract class VindicatorEntityMixin extends IllagerEntity {

    public VindicatorEntityMixin(EntityType<? extends IllagerEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code vindicator} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends VindicatorEntity> entityType, World world, CallbackInfo ci) {
        ModUtil.modifyFollowRange(this, isDoomMode() ? 48.0D : 12.0D);
        ModUtil.modifyMaxHealth(this, isDoomMode() ? 20.0D : 24.0D);
    }

    /**
     * Inflicts players with {@code slowness} if {@code doom mode} is enabled.
     */
    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        if (!super.tryAttack(world, target)) {
            return false;
        } else {
            if (isDoomMode() && target instanceof PlayerEntity) {
                ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ModUtil.secondsInTicks(10), 0));
            }

            return true;
        }
    }
}