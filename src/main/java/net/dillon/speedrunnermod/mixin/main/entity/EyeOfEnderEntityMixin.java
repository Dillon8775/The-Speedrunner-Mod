package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.item.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isPlayingModeDoom;

@Mixin(EyeOfEnderEntity.class)
public abstract class EyeOfEnderEntityMixin extends Entity implements FlyingItemEntity {
    @Shadow
    private double targetX, targetY, targetZ;
    @Shadow
    private int lifespan;
    @Shadow
    public abstract ItemStack getStack();

    public EyeOfEnderEntityMixin(EntityType<? extends EyeOfEnderEntity> type, World world) {
        super(type, world);
    }

    /**
     * @author Dillon8775
     * @reason Changes the function of the eye of ender, and applies different effects to it in certain modes, based off what type it is.
     */
    @Overwrite
    public void tick() {
        super.tick();
        Vec3d vec3d = this.getVelocity();
        double d = this.getX() + vec3d.x;
        double e = this.getY() + vec3d.y;
        double f = this.getZ() + vec3d.z;
        double g = vec3d.horizontalLength();
        this.setPitch(ProjectileEntity.updateRotation(this.lastPitch, (float)(MathHelper.atan2(vec3d.y, g) * 180.0F / (float)Math.PI)));
        this.setYaw(ProjectileEntity.updateRotation(this.lastYaw, (float)(MathHelper.atan2(vec3d.x, vec3d.z) * 180.0F / (float)Math.PI)));
        if (!this.getWorld().isClient) {
            double h = this.targetX - d;
            double i = this.targetZ - f;
            float j = (float)Math.sqrt(h * h + i * i);
            float k = (float)MathHelper.atan2(i, h);
            double l = MathHelper.lerp(0.0025, g, (double)j);
            double m = vec3d.y;
            if (j < 1.0F) {
                l *= 0.8;
                m *= 0.8;
            }

            int n = this.getY() < this.targetY ? 1 : -1;
            vec3d = new Vec3d(Math.cos(k) * l, m + (n - m) * 0.015F, Math.sin(k) * l);
            this.setVelocity(vec3d);
        }

        if (this.isTouchingWater()) {
            for (int p = 0; p < 4; p++) {
                this.getWorld().addParticleClient(ParticleTypes.BUBBLE, d - vec3d.x * 0.25, e - vec3d.y * 0.25, f - vec3d.z * 0.25, vec3d.x, vec3d.y, vec3d.z);
            }
        } else if (this.getStack().getItem() == Items.ENDER_EYE || this.getStack().getItem() == ModItems.ANNUL_EYE ) {
            this.getWorld().addParticleClient(
                    ParticleTypes.PORTAL,
                    d - vec3d.x * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                    e - vec3d.y * 0.25D - 0.5D,
                    f - vec3d.z * 0.25D + this.random.nextDouble() * 0.6D - 0.3D,
                    vec3d.x, vec3d.y, vec3d.z);
        } else if (this.getStack().getItem() == ModItems.INFERNO_EYE && !this.isTouchingWater()) {
            this.getWorld().addParticleClient(
                    ParticleTypes.SMOKE,
                    this.getParticleX(0.5D),
                    this.getRandomBodyY(),
                    this.getParticleZ(0.5D),
                    0.0D,
                    0.0D,
                    0.0D);
        } else {
            this.getWorld()
                    .addParticleClient(
                            ParticleTypes.PORTAL,
                            d - vec3d.x * 0.25 + this.random.nextDouble() * 0.6 - 0.3,
                            e - vec3d.y * 0.25 - 0.5,
                            f - vec3d.z * 0.25 + this.random.nextDouble() * 0.6 - 0.3,
                            vec3d.x,
                            vec3d.y,
                            vec3d.z
                    );
        }

        if (!this.getWorld().isClient) {
            this.setPosition(d, e, f);
            this.lifespan++;
            if (this.lifespan > options().advanced.enderEyeBreakingCooldown.getCurrentValue() && !this.getWorld().isClient) {
                this.discard();
                if (isPlayingModeDoom()) {
                    if (this.getStack().getItem() == Items.ENDER_EYE) {
                        this.getWorld().syncWorldEvent(WorldEvents.EYE_OF_ENDER_BREAKS, this.getBlockPos(), 0);
                    } else if (this.getStack().getItem() == ModItems.ANNUL_EYE) {
                        this.getWorld().syncWorldEvent(10001, this.getBlockPos(), 0);
                    } else if (this.getStack().getItem() == ModItems.INFERNO_EYE) {
                        this.getWorld().syncWorldEvent(10002, this.getBlockPos(), 0);
                    } else if (this.getStack().getItem() == ModItems.SPEEDRUNNERS_EYE) {
                        this.getWorld().syncWorldEvent(10003, this.getBlockPos(), 0);
                    }
                } else {
                    this.getWorld().spawnEntity(new ItemEntity(this.getWorld(), this.getX(), this.getY(), this.getZ(), this.getStack()));
                }

                if (this.getStack().getItem() == Items.ENDER_EYE || this.getStack().getItem() == ModItems.ANNUL_EYE || this.getStack().getItem() == ModItems.SPEEDRUNNERS_EYE) {
                    this.playSound(SoundEvents.ENTITY_ENDER_EYE_DEATH, 1.0F, 1.0F);
                } else if (this.getStack().getItem() == ModItems.INFERNO_EYE) {
                    this.playSound(SoundEvents.ITEM_FIRECHARGE_USE, 1.0F, 1.0F);
                }
            }
        } else {
            this.setPosition(d, e, f);
        }
    }
}