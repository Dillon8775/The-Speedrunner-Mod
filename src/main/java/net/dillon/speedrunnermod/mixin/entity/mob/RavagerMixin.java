package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Ravager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Ravager.class)
public class RavagerMixin {

    /**
     * Modifies {@code ravanger} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeRavagerAttributes(EntityType<? extends Ravager> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModAttributeHelper.modifyMaxHealth(dis, isDoomMode() ? 100.0D : 50.0D);
        ModAttributeHelper.modifyAttackDamage(dis, isDoomMode() ? 16.0D : 10.0D);
        ModAttributeHelper.modifyAttackKnockback(dis, isDoomMode() ? 1.6D : 1.1D);
        ModAttributeHelper.modifyFollowRange(dis, isDoomMode() ? 48.0D : 32.0D);
    }

    /**
     * Inflicts players with {@code slowness} when attacking.
     */
    @Inject(method = "doHurtTarget", at = @At("RETURN"))
    private void ravagerInflictsSlowness(ServerLevel world, Entity target, CallbackInfoReturnable<Boolean> cir) {
        if (isDoomMode() && target instanceof Player) {
            ((Player)target).addEffect(new MobEffectInstance(MobEffects.SLOWNESS, TickCalculator.seconds(10), 0));
        }
    }
}