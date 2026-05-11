package net.dillon.speedrunnermod.mixin.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.hurtingprojectile.DragonFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.HitResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(DragonFireball.class)
public class DragonFireballMixin {

    /**
     * Decreases the amplifier given to the particle effects that come from the dragon fireball with the {@code instant damage} status effect.
     */
    @ModifyArg(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectInstance;<init>(Lnet/minecraft/core/Holder;II)V"), index = 2)
    private int changeInstantDamageAmplifier(int x) {
        return ModUtil.getEnderDragonFireballInstantDamageAmplifier();
    }

    /**
     * Buffs the dragon's fireball if a player threw it.
     */
    @Inject(method = "onHit", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/AreaEffectCloud;setDuration(I)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void buffFireball(HitResult hitResult, CallbackInfo ci, List list, AreaEffectCloud areaEffectCloudEntity, Entity entity) {
        if (areaEffectCloudEntity.getOwner() instanceof Player) {
            areaEffectCloudEntity.setRadius(4.5F);
            areaEffectCloudEntity.setDuration(ModUtil.minutesAsTicks(1));
            areaEffectCloudEntity.setRadiusPerTick((15.5F - areaEffectCloudEntity.getRadius()) / areaEffectCloudEntity.getDuration());
            areaEffectCloudEntity.level().explode(
                    null,
                    areaEffectCloudEntity.getX(),
                    areaEffectCloudEntity.getY(),
                    areaEffectCloudEntity.getZ(),
                    options().advanced.fireballExplosionPower.getCurrentValue() * 1.5F,
                    Level.ExplosionInteraction.BLOCK);
        }
    }
}