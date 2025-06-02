package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModConstants;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.GhastEntity;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(GhastEntity.ShootFireballGoal.class)
public abstract class GhastEntityShootFireballGoalMixin extends Goal {
    @Shadow @Final
    private GhastEntity ghast;

    /**
     * Kills the ghast immediately after it shoots a fireball, if the {@code kill ghast upon fireball} option is enabled.
     */
    @Inject(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/GhastEntity$ShootFireballGoal;cooldown:I", ordinal = 1, opcode = Opcodes.PUTFIELD))
    private void killGhastUponFireball(CallbackInfo ci) {
        if (options().main.killGhastOnFireball) {
            this.ghast.kill(getServerWorld(this.ghast));
        }
    }

    /**
     * Increases the ghast's fireball cooldown, unless it's on {@code doom mode}.
     */
    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/GhastEntity$ShootFireballGoal;cooldown:I", ordinal = 1, opcode = Opcodes.PUTFIELD))
    private void changeFireballCooldown(GhastEntity.ShootFireballGoal ghast, int value) {
        ghast.cooldown = ModConstants.GHAST_FIREBALL_COOLDOWN;
    }
}