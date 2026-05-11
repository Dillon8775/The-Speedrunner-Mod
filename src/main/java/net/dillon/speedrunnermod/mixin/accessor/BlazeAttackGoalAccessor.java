package net.dillon.speedrunnermod.mixin.accessor;

import net.minecraft.world.entity.monster.Blaze;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(Blaze.BlazeAttackGoal.class)
public interface BlazeAttackGoalAccessor {
    @Accessor("attackTime")
    void setAttackTime(int attackTime);
}