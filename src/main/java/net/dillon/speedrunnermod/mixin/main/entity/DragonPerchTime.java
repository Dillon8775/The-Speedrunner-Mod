package net.dillon.speedrunnermod.mixin.main.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.EnderDragonFight;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(EnderDragonFight.class)
public abstract class DragonPerchTime {
    @Shadow @Final
    private ServerWorld world;

    /**
     * Implements the {@code dragon perch time feature} and spawns doom mode monsters.
     */
    @Inject(method = "createDragon", at = @At("RETURN"))
    private void createDragonFeatures(CallbackInfoReturnable<EnderDragonEntity> cir, @Local EnderDragonEntity dragon) {
        if (options().isInstantDragonPerchTime()) {
            dragon.getPhaseManager().setPhase(PhaseType.LANDING);
            playDragonSound();
        } else if (options().isDragonPerchTimeOn()) {
            TaskScheduler.schedule(ModUtil.secondsAsTicks(options().getDragonPerchTime()), () -> {
                dragon.getPhaseManager().setPhase(PhaseType.LANDING);
                playDragonSound();
            });
        }
        if (isDoomMode()) {
            WitherEntity witherEntity = EntityType.WITHER.create(this.world, SpawnReason.EVENT);
            witherEntity.refreshPositionAndAngles(0.0D, 196.0D, 0.0D, this.world.random.nextFloat() * 360.0F, 0.0F);
            this.world.spawnEntity(witherEntity);
            GiantEntity giantEntity = EntityType.GIANT.create(this.world, SpawnReason.EVENT);
            giantEntity.refreshPositionAndAngles(0.0D, 96.0D, 0.0D, this.world.random.nextFloat() * 240.0F, 0.0F);
            this.world.spawnEntity(giantEntity);
        }
    }

    /**
     * Gets every existing player, and plays the dragon perch sound to all of them.
     */
    @Unique
    private void playDragonSound() {
        for (int i = 0; i < this.world.getPlayers().size(); i++) {
            this.world.playSound(null, this.world.getPlayers().get(i).getX(), this.world.getPlayers().get(i).getY(), this.world.getPlayers().get(i).getZ(), SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.HOSTILE, 3.0F, 0.65F);
        }
    }
}