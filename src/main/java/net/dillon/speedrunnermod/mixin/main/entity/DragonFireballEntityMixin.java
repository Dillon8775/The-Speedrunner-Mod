package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.projectile.DragonFireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(DragonFireballEntity.class)
public class DragonFireballEntityMixin {

    /**
     * Decreases the amplifier given to the particle effects that come from the dragon fireball with the {@code instant damage} status effect.
     */
    @ModifyArg(method = "onCollision", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/effect/StatusEffectInstance;<init>(Lnet/minecraft/registry/entry/RegistryEntry;II)V"), index = 2)
    private int changeInstantDamageAmplifier(int x) {
        return ModUtil.getEnderDragonFireballInstantDamageAmplifier();
    }
}