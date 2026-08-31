package net.dillon.speedrunnermod.mixin.entity.goal;

import net.dillon.speedrunnermod.tag.ModEntityTypeTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.TargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(TargetGoal.class)
public class TargetGoalMixin {
    @Shadow
    @Final
    protected Mob mob;

    /**
     * Prevents the wither from attacking the ender dragon, on doom mode.
     */
    @Inject(method = "canAttack", at = @At("HEAD"), cancellable = true)
    private void preventWitherFromAggroingDragon(LivingEntity target, TargetingConditions targetConditions, CallbackInfoReturnable<Boolean> cir) {
        if (isDoomMode() && this.mob instanceof WitherBoss && target.is(ModEntityTypeTags.BLACKLISTED_WITHER_TARGET_MOBS)) {
            cir.setReturnValue(false);
        }
    }
}