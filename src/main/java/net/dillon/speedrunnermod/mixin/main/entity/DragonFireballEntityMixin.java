package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.DragonFireballEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(DragonFireballEntity.class)
public class DragonFireballEntityMixin {

    /**
     * Decreases the amplifier given to the particle effects that come from the dragon fireball with the {@code instant damage} status effect.
     */
    @ModifyArg(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/registry/entry/RegistryEntry;II)V"), index = 2)
    private int changeInstantDamageAmplifier(int x) {
        return ModUtil.getEnderDragonFireballInstantDamageAmplifier();
    }

    /**
     * Buffs the dragon's fireball if a player threw it.
     */
    @Inject(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/AreaEffectCloudEntity;setDuration(I)V"), locals = LocalCapture.CAPTURE_FAILHARD)
    private void buffFireball(HitResult hitResult, CallbackInfo ci, List list, AreaEffectCloudEntity areaEffectCloudEntity, Entity entity) {
        if (areaEffectCloudEntity.getOwner() instanceof PlayerEntity) {
            areaEffectCloudEntity.setRadius(4.5F);
            areaEffectCloudEntity.setDuration(ModUtil.minutesAsTicks(1));
            areaEffectCloudEntity.setRadiusGrowth((15.5F - areaEffectCloudEntity.getRadius()) / areaEffectCloudEntity.getDuration());
            areaEffectCloudEntity.getEntityWorld().createExplosion(
                    null,
                    areaEffectCloudEntity.getX(),
                    areaEffectCloudEntity.getY(),
                    areaEffectCloudEntity.getZ(),
                    options().advanced.fireballExplosionPower.getCurrentValue() * 1.5F,
                    World.ExplosionSourceType.BLOCK);
        }
    }
}