package net.dillon.speedrunnermod.entity.goliath;

import net.dillon.speedrunnermod.mixin.main.entity.goliath.GoliathEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.mob.GiantEntity;

/**
 * See {@link GoliathEntity} for more.
 */
public class GoliathAttackGoal extends MeleeAttackGoal {
    private final GiantEntity giant;
    private int ticks;

    public GoliathAttackGoal(GiantEntity giant, double speed, boolean pauseWhenMobIdle) {
        super(giant, speed, pauseWhenMobIdle);
        this.giant = giant;
    }

    public void start() {
        super.start();
        this.ticks = 0;
    }

    public void stop() {
        super.stop();
        this.giant.setAttacking(false);
    }

    public void tick() {
        super.tick();
        ++this.ticks;
        this.giant.setAttacking(this.ticks >= 5 && this.getCooldown() < this.getMaxCooldown() / 2);
    }
}