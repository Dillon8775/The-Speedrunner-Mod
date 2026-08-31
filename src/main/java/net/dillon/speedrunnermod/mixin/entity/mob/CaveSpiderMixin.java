package net.dillon.speedrunnermod.mixin.entity.mob;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.spider.CaveSpider;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(CaveSpider.class)
public class CaveSpiderMixin {

    /**
     * A thing for {@code doom mode.} >:)
     */
    @Inject(method = "doHurtTarget", at = @At(value = "RETURN", ordinal = 0))
    private void caveSpiderInflictsSlowness(ServerLevel world, Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (isDoomMode() && target instanceof Player player) {
            player.addEffect(new MobEffectInstance(MobEffects.SLOWNESS, 200, 0));
        }
    }
}