package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.util.ModConstants;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(EnderPearlEntity.class)
public abstract class EnderPearlEntityMixin extends ThrownItemEntity {

    public EnderPearlEntityMixin(EntityType<? extends EnderPearlEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author Dillon8775
     * @reason Makes ender pearls do less damage, and implements the {@code Infinit Pearl Mode} feature to work correctly.
     */
    @Author(Authors.DUNCANRUNS)
    @Overwrite
    public void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        boolean isInfiniPearl = super.getStack().isOf(ModItems.INFINI_PEARL);

        for(int i = 0; i < 32; ++i) {
            this.getWorld().addParticleClient(ParticleTypes.PORTAL, this.getX(), this.getY() + this.random.nextDouble() * 2.0D, this.getZ(), this.random.nextGaussian(), 0.0D, this.random.nextGaussian());
        }

        if (this.getWorld() instanceof ServerWorld serverWorld && !this.isRemoved()) {
            Entity entity = this.getOwner();
            if (entity instanceof ServerPlayerEntity serverPlayerEntity) {
                if (serverPlayerEntity.networkHandler.isConnectionOpen() && serverPlayerEntity.getWorld() == this.getWorld() && !serverPlayerEntity.isSleeping()) {
                    if (!isInfiniPearl && this.random.nextFloat() < 0.05F && serverWorld.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
                        EndermiteEntity endermiteEntity = EntityType.ENDERMITE.create(this.getWorld(), SpawnReason.TRIGGERED);
                        endermiteEntity.refreshPositionAndAngles(entity.getX(), entity.getY(), entity.getZ(), entity.getYaw(), entity.getPitch());
                        this.getWorld().spawnEntity(endermiteEntity);
                    }

                    if (entity.hasVehicle()) {
                        serverPlayerEntity.requestTeleportAndDismount(this.getX(), this.getY(), this.getZ());
                    } else {
                        entity.requestTeleport(this.getX(), this.getY(), this.getZ());
                    }

                    entity.fallDistance = 0.0F;
                    if (!isInfiniPearl) {
                        if (options().main.playingMode.doom()) {
                            if (!serverPlayerEntity.isCreative() || !serverPlayerEntity.isSpectator()) {
                                ((ServerPlayerEntity)entity).addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, ModUtil.secondsInTicks(3), 0));
                            }
                        }
                        entity.damage(serverWorld, entity.getDamageSources().fall(), ModConstants.ENDER_PEARL_DAMAGE_VALUE);
                    }
                }
            } else if (entity != null) {
                entity.requestTeleport(this.getX(), this.getY(), this.getZ());
                entity.fallDistance = 0.0F;
            }

            this.discard();
        }
    }
}