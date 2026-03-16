package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.golem.AbstractGolem;
import net.minecraft.world.entity.monster.Shulker;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Shulker.class)
public abstract class ShulkerMixin extends AbstractGolem {
    @Shadow
    abstract boolean isClosed();
    @Shadow
    abstract void hitByShulkerBullet();

    public ShulkerMixin(EntityType<? extends AbstractGolem> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Modifies the {@code maximum health} of a shulker.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeShulkerAttributes(EntityType<? extends Shulker> entityType, Level world, CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, isDoomMode() ? 32.0D : 20.0D);
    }

    /**
     * @author Dillon8775
     * @reason Prevents {@code shulkers} from teleporting, and allows them to be shot with arrows, even when closed.
     */
    @Overwrite
    public boolean hurtServer(ServerLevel world, DamageSource source, float amount) {
        Entity entity2;
        if (this.isClosed()) {
            entity2 = source.getDirectEntity();
            if (isDoomMode()) {
                if (entity2 instanceof AbstractArrow) {
                    return false;
                }
            } else {
                if (entity2 instanceof AbstractArrow && this.random.nextFloat() < 0.25F) {
                    return false;
                }
            }
        }

        if (!super.hurtServer(world, source, amount)) {
            return false;
        } else {
            if (source.is(DamageTypeTags.IS_PROJECTILE)) {
                entity2 = source.getDirectEntity();
                if (entity2 != null && entity2.getType() == EntityType.SHULKER_BULLET) {
                    this.hitByShulkerBullet();
                }
            }

            return true;
        }
    }
}