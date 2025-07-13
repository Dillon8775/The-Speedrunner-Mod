package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.item.ModItems;
import net.minecraft.entity.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(EyeOfEnderEntity.class)
public abstract class EyeOfEnderEntityMixin extends Entity implements FlyingItemEntity {
    @Shadow
    private int lifespan;
    @Shadow
    public abstract ItemStack getStack();
    @Shadow
    protected abstract void addParticles(Vec3d pos, Vec3d velocity);
    @Shadow
    private @Nullable Vec3d targetPos;

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
        Vec3d vec3d = this.getPos().add(this.getVelocity());
        if (!this.getWorld().isClient() && this.targetPos != null) {
            this.setVelocity(EyeOfEnderEntity.updateVelocity(this.getVelocity(), vec3d, this.targetPos));
        }

        if (this.getWorld().isClient()) {
            Vec3d vec3d2 = vec3d.subtract(this.getVelocity().multiply(0.25));
            this.addParticles(vec3d2, this.getVelocity());
        }

        this.setPosition(vec3d);
        if (!this.getWorld().isClient()) {
            this.lifespan++;
            if (this.lifespan > options().getEnderEyeBreakingCooldown() && !this.getWorld().isClient) {
                this.discard();
                if (isDoomMode()) {
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
        }
    }
}