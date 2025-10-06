package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(AbstractSkeletonEntity.class)
public class AbstractSkeletonEntityMixin extends HostileEntity {

    public AbstractSkeletonEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies the {@code generic movement speed} of any skeleton entity.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeSkeletonMovementSpeed(EntityType<? extends AbstractSkeletonEntity> entityType, World world, CallbackInfo ci) {
        ModUtil.modifyMovementSpeed(this, isDoomMode() ? 0.3D : 0.25D);
    }

    /**
     * Increases/decreases the speed at which skeleton entities can shoot with their bow, depending on if {@code doom mode} is {@code ON.}
     */
    @ModifyVariable(method = "updateAttackType", at = @At("STORE"), ordinal = 0)
    private int increaseSkeletonArrowSpeed(int x) {
        int i = isDoomMode() ? 5 : 20;
        if (this.getEntityWorld().getDifficulty() != Difficulty.HARD) {
            i = isDoomMode() ? 10 : 20;
        }
        return i;
    }
}