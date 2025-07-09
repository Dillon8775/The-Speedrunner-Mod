package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(ZombieEntity.class)
public class ZombieEntityMixin extends HostileEntity {

    public ZombieEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code zombie} attributes.
     */
    @Inject(method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;)V", at = @At("TAIL"))
    private void init(EntityType<? extends ZombieEntity> entityType, World world, CallbackInfo ci) {
        ModUtil.modifyFollowRange(this, isDoomMode() ? 50.0D : 25.0D);
        ModUtil.modifyMovementSpeed(this, isDoomMode() ? 0.33D : 0.23D);
        ModUtil.modifyAttackDamage(this, isDoomMode() ? 7.0D : 2.0D);
        ModUtil.modifyArmor(this, isDoomMode() ? 2.0D : 1.0D);
    }

    /**
     * Inflicts players with {@code slowness} if {@code doom mode} is enabled.
     */
    @Override
    public boolean tryAttack(ServerWorld world, Entity target) {
        if (!super.tryAttack(world, target)) {
            return false;
        } else {
            if (isDoomMode() && target instanceof PlayerEntity player) {
                player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ModUtil.secondsAsTicks(10), 0));
            }

            return true;
        }
    }
}