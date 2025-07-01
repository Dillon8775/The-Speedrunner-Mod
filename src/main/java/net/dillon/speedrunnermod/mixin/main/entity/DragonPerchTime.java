package net.dillon.speedrunnermod.mixin.main.entity;

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
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.*;

import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isPlayingModeDoom;

@Mixin(EnderDragonFight.class)
public abstract class DragonPerchTime {
    @Shadow @Final
    private ServerWorld world;
    @Shadow
    private UUID dragonUuid;

    /**
     * @author Dillon8775
     * @reason Implements the {@code dragon perch time} feature, and also spawns a giant and wither if {@code doom mode} is enabled.
     */
    @Overwrite
    private EnderDragonEntity createDragon() {
        this.world.getWorldChunk(new BlockPos(0, 128, 0));
        EnderDragonEntity enderDragonEntity = EntityType.ENDER_DRAGON.create(this.world, SpawnReason.EVENT);
        enderDragonEntity.getPhaseManager().setPhase(PhaseType.HOLDING_PATTERN);
        enderDragonEntity.refreshPositionAndAngles(0.0D, 128.0D, 0.0D, this.world.random.nextFloat() * 360.0F, 0.0F);
        this.world.spawnEntity(enderDragonEntity);
        this.dragonUuid = enderDragonEntity.getUuid();
        if (options().isInstantDragonPerchTime()) {
            enderDragonEntity.getPhaseManager().setPhase(PhaseType.LANDING);
            playDragonSound();
        } else if (options().isDragonPerchTimeOn()) {
            new Timer().schedule(new TimerTask() {
                public void run() {
                    enderDragonEntity.getPhaseManager().setPhase(PhaseType.LANDING);
                    playDragonSound();
                }
            }, options().getDragonPerchTime());
        }

        if (isPlayingModeDoom()) {
            WitherEntity witherEntity = EntityType.WITHER.create(this.world, SpawnReason.EVENT);
            witherEntity.refreshPositionAndAngles(0.0D, 196.0D, 0.0D, this.world.random.nextFloat() * 360.0F, 0.0F);
            this.world.spawnEntity(witherEntity);
            GiantEntity giantEntity = EntityType.GIANT.create(this.world, SpawnReason.EVENT);
            giantEntity.refreshPositionAndAngles(0.0D, 96.0D, 0.0D, this.world.random.nextFloat() * 240.0F, 0.0F);
            this.world.spawnEntity(giantEntity);
        }
        return enderDragonEntity;
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