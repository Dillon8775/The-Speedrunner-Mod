package net.dillon.speedrunnermod.mixin.entity.goal;

import net.dillon.speedrunnermod.entity.goliath.Minion;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.ThrowableFireball;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.ZombieAttackGoal;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ZombieAttackGoal.class)
public class ZombieAttackGoalMixin extends MeleeAttackGoal {
    @Shadow @Final
    private Zombie zombie;

    public ZombieAttackGoalMixin(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        super(mob, speedModifier, followingTargetEvenIfNotSeen);
    }

    /**
     * Makes zombies shoot fireballs.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void zombieFireballGoal(CallbackInfo ci) {
        ItemStack heldItem = this.zombie.getItemBySlot(EquipmentSlot.MAINHAND);
        if (!this.zombie.isAggressive() || !heldItem.is(ModItemTags.THROWABLE_FIREBALLS) || !(this.zombie instanceof Minion minion)) {
            return;
        }

        if (!this.isTimeToAttack()) {
            minion.setFireballChargeTime(0);
        }
        minion.setFireballChargeTime(minion.getFireballChargeTime() + 1);

        if (minion.getFireballChargeTime() >= Minion.fireballChargeTime(minion)) {
            ThrowableFireball.createFireballEntity(this.zombie.getItemBySlot(EquipmentSlot.MAINHAND).is(ModItems.DRAGON_FIREBALL), this.zombie);
            minion.setFireballChargeTime(0);
        }
    }
}