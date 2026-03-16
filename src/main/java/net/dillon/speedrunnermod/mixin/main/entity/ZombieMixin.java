package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Zombie.class)
public class ZombieMixin extends Monster {

    public ZombieMixin(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code zombie} attributes.
     */
    @Inject(method = "<init>(Lnet/minecraft/world/entity/EntityType;Lnet/minecraft/world/level/Level;)V", at = @At("TAIL"))
    private void changeZombieAttributes(EntityType<? extends Zombie> entityType, Level world, CallbackInfo ci) {
        ModUtil.modifyFollowRange(this, isDoomMode() ? 50.0D : 25.0D);
        ModUtil.modifyMovementSpeed(this, isDoomMode() ? 0.33D : 0.23D);
        ModUtil.modifyAttackDamage(this, isDoomMode() ? 7.0D : 2.0D);
        ModUtil.modifyArmor(this, isDoomMode() ? 2.0D : 1.0D);
    }

    /**
     * Inflicts players with {@code slowness} if {@code doom mode} is enabled.
     */
    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        if (!super.doHurtTarget(world, target)) {
            return false;
        } else {
            if (isDoomMode() && target instanceof Player player) {
                player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, ModUtil.secondsAsTicks(10), 0));
            }

            return true;
        }
    }
}