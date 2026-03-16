package net.dillon.speedrunnermod.entity.goliath;

import net.dillon.speedrunnermod.sound.ModSoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;

/**
 * A special target goal for Goliath.
 */
public class GoliathTargetGoal<T extends LivingEntity> extends NearestAttackableTargetGoal<T> {

    public GoliathTargetGoal(Mob mob, Class<T> targetClass, boolean checkVisibility) {
        super(mob, targetClass, 10, checkVisibility, false, null);
    }

    /**
     * Plays a custom sound when detecting the player...
     */
    @Override
    public void start() {
        super.start();
        this.mob.level().playSound(null, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ(), ModSoundEvents.ENTITY_GOLIATH_FOUND_YOU, SoundSource.HOSTILE, 20.0F, 0.7F);
    }
}