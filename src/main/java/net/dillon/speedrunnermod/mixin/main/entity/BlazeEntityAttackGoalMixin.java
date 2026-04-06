package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.monster.Blaze;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Blaze.BlazeAttackGoal.class)
public class BlazeEntityAttackGoalMixin {

    /**
     * Increases the blaze's fireball cooldown, unless it's on {@code doom mode}.
     */
    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/world/entity/monster/Blaze$BlazeAttackGoal;attackTime:I", ordinal = 4, opcode = Opcodes.PUTFIELD))
    private void changeBlazeFireballCooldown(Blaze.BlazeAttackGoal blaze, int value) {
        blaze.attackTime = ModUtil.getBlazeFireballCooldown();
    }

    /**
     * Prevents blazes from melee attacking.
     */
    @Redirect(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/monster/Blaze;tryAttack(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/entity/Entity;)Z"))
    private boolean disableMeleeAttacking(Blaze instance, ServerLevel serverWorld, Entity entity) {
        return false;
    }
}