package net.dillon.speedrunnermod.mixin.entity.mob;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.skeleton.WitherSkeleton;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.dillonlib.util.Arithmetics.S_asTick;
import static net.dillon.speedrunnermod.option.ModCommonOptions.doomOrDefault;
import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(WitherSkeleton.class)
public class WitherSkeletonMixin {

    /**
     * Lowers attack damage from wither skeletons.
     */
    @ModifyArg(method = "finalizeSpawn", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ai/attributes/AttributeInstance;setBaseValue(D)V"))
    private double changeWitherSkeletonMaxDamage(double baseValue) {
        return doomOrDefault(10.0D, 1.0D);
    }

    /**
     * Decreases the amplifier of the wither effect when wither skeleton's attack.
     */
    @ModifyArg(method = "doHurtTarget", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/effect/MobEffectInstance;<init>(Lnet/minecraft/core/Holder;I)V"), index = 1)
    private int changeWitherEffectDuration(int x) {
        return doomOrDefault(200, 60);
    }

    /**
     * Inflicts players with {@code slowness} if {@code doom mode} is enabled.
     */
    @Inject(method = "doHurtTarget", at = @At("RETURN"))
    private void inflictSlowness(ServerLevel world, Entity target, CallbackInfoReturnable<?> cir) {
        if (isDoomMode() && target instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, S_asTick(10), 0));
        }
    }
}