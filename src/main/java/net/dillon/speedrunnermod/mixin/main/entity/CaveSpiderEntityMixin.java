package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.CaveSpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModOptions.isPlayingModeDoom;

@Mixin(CaveSpiderEntity.class)
public class CaveSpiderEntityMixin {

    /**
     * A thing for {@code doom mode.} >:)
     */
    @Inject(method = "tryAttack", at = @At(value = "RETURN", ordinal = 0))
    private void tryAttack(ServerWorld world, Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (isPlayingModeDoom() && target instanceof PlayerEntity) {
            ((PlayerEntity)target).addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 200, 0));
        }
    }
}