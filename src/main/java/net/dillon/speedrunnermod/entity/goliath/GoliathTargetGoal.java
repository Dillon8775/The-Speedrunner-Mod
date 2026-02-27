package net.dillon.speedrunnermod.entity.goliath;

import net.dillon.speedrunnermod.sound.ModSoundEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.ActiveTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.sound.SoundCategory;

/**
 * A special target goal for Goliath.
 */
public class GoliathTargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {

    public GoliathTargetGoal(MobEntity mob, Class<T> targetClass, boolean checkVisibility) {
        super(mob, targetClass, 10, checkVisibility, false, null);
    }

    /**
     * Plays a custom sound when detecting the player...
     */
    @Override
    public void start() {
        super.start();
        this.mob.getEntityWorld().playSound(null, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ(), ModSoundEvents.ENTITY_GOLIATH_FOUND_YOU, SoundCategory.HOSTILE, 20.0F, 0.7F);
    }
}