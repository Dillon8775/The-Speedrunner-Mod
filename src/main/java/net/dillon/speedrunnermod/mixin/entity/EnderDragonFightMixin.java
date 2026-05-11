package net.dillon.speedrunnermod.mixin.entity;

import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.enderdragon.EnderDragon;
import net.minecraft.world.entity.boss.enderdragon.phases.EnderDragonPhase;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.level.dimension.end.EnderDragonFight;
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
public abstract class EnderDragonFightMixin {
    @Shadow @Final
    private ServerLevel level;

    /**
     * Implements the {@code dragon perch time feature} and spawns doom mode monsters.
     */
    @Inject(method = "createNewDragon", at = @At("RETURN"))
    private void createDragonFeatures(CallbackInfoReturnable<EnderDragon> cir, @Local EnderDragon dragon) {
        if (options().isInstantDragonPerchTime()) {
            dragon.getPhaseManager().setPhase(EnderDragonPhase.LANDING);
            playDragonSound();
        } else if (options().isDragonPerchTimeOn()) {
            TaskScheduler.schedule(ModUtil.secondsAsTicks(options().getDragonPerchTime()), () -> {
                dragon.getPhaseManager().setPhase(EnderDragonPhase.LANDING);
                playDragonSound();
            });
        }
        if (isDoomMode()) {
            WitherBoss witherEntity = EntityType.WITHER.create(this.level, EntitySpawnReason.EVENT);
            witherEntity.snapTo(0.0D, 196.0D, 0.0D, this.level.getRandom().nextFloat() * 360.0F, 0.0F);
            this.level.addFreshEntity(witherEntity);
            Giant giantEntity = EntityType.GIANT.create(this.level, EntitySpawnReason.EVENT);
            giantEntity.snapTo(0.0D, 96.0D, 0.0D, this.level.getRandom().nextFloat() * 240.0F, 0.0F);
            this.level.addFreshEntity(giantEntity);
        }
    }

    /**
     * Gets every existing player, and plays the dragon perch sound to all of them.
     */
    @Unique
    private void playDragonSound() {
        for (int i = 0; i < this.level.players().size(); i++) {
            this.level.playSound(null, this.level.players().get(i).getX(), this.level.players().get(i).getY(), this.level.players().get(i).getZ(), SoundEvents.ENDER_DRAGON_GROWL, SoundSource.HOSTILE, 3.0F, 0.65F);
        }
    }
}