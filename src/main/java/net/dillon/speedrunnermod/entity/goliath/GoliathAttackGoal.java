package net.dillon.speedrunnermod.entity.goliath;

import net.dillon.speedrunnermod.mixin.main.entity.goliath.GoliathEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.monster.Giant;

/**
 * See {@link GoliathEntity} for more.
 */
public class GoliathAttackGoal extends MeleeAttackGoal {
    private final Giant giant;
    private int ticks;

    public GoliathAttackGoal(Giant giant, double speed, boolean pauseWhenMobIdle) {
        super(giant, speed, pauseWhenMobIdle);
        this.giant = giant;
    }

    public void start() {
        super.start();
        this.ticks = 0;
    }

    public void stop() {
        super.stop();
        this.giant.setAggressive(false);
    }

    public void tick() {
        super.tick();
        ++this.ticks;
        this.giant.setAggressive(this.ticks >= 5 && this.getTicksUntilNextAttack() < this.getAttackInterval() / 2);
    }
}