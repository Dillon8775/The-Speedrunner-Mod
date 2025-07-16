package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.ShulkerEntity;
import net.minecraft.entity.passive.GolemEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.registry.tag.DamageTypeTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(ShulkerEntity.class)
public abstract class ShulkerEntityMixin extends GolemEntity {
    @Shadow
    abstract boolean isClosed();
    @Shadow
    abstract void spawnNewShulker();

    public ShulkerEntityMixin(EntityType<? extends GolemEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies the {@code maximum health} of a shulker.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeShulkerAttributes(EntityType<? extends ShulkerEntity> entityType, World world, CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, isDoomMode() ? 32.0D : 20.0D);
    }

    /**
     * @author Dillon8775
     * @reason Prevents {@code shulkers} from teleporting, and allows them to be shot with arrows, even when closed.
     */
    @Overwrite
    public boolean damage(ServerWorld world, DamageSource source, float amount) {
        Entity entity2;
        if (this.isClosed()) {
            entity2 = source.getSource();
            if (isDoomMode()) {
                if (entity2 instanceof PersistentProjectileEntity) {
                    return false;
                }
            } else {
                if (entity2 instanceof PersistentProjectileEntity && this.random.nextFloat() < 0.25F) {
                    return false;
                }
            }
        }

        if (!super.damage(world, source, amount)) {
            return false;
        } else {
            if (source.isIn(DamageTypeTags.IS_PROJECTILE)) {
                entity2 = source.getSource();
                if (entity2 != null && entity2.getType() == EntityType.SHULKER_BULLET) {
                    this.spawnNewShulker();
                }
            }

            return true;
        }
    }
}