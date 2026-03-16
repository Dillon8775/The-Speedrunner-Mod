package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Ghast;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(Ghast.GhastShootFireballGoal.class)
public abstract class GhastEntityShootFireballGoalMixin extends Goal {
    @Shadow @Final
    private Ghast ghast;

    /**
     * Kills the ghast immediately after it shoots a fireball, if the {@code kill ghast upon fireball} option is enabled.
     */
    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/monster/Ghast$GhastShootFireballGoal;chargeTime:I", ordinal = 1, opcode = Opcodes.PUTFIELD))
    private void killGhastUponFireball(CallbackInfo ci) {
        if (options().main.killGhastOnFireball.getCurrentValue()) {
            this.ghast.kill(getServerLevel(this.ghast));
        }
    }

    /**
     * Increases the ghast's fireball cooldown, unless it's on {@code doom mode}.
     */
    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/monster/Ghast$GhastShootFireballGoal;chargeTime:I", ordinal = 1, opcode = Opcodes.PUTFIELD))
    private void changeGhastFireballCooldown(Ghast.GhastShootFireballGoal ghast, int value) {
        ghast.chargeTime = ModUtil.getGhastFireballCooldown();
    }
}