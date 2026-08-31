package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.skeleton.AbstractSkeleton;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(AbstractSkeleton.class)
public class AbstractSkeletonMixin extends Monster {

    public AbstractSkeletonMixin(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Modifies the {@code generic movement speed} of any skeleton entity.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeSkeletonMovementSpeed(EntityType<? extends AbstractSkeleton> entityType, Level world, CallbackInfo ci) {
        ModAttributeHelper.modifyMovementSpeed(this, isDoomMode() ? 0.3D : 0.25D);
    }

    /**
     * Increases/decreases the speed at which skeleton entities can shoot with their bow, depending on if {@code doom mode} is {@code ON.}
     */
    @ModifyVariable(method = "reassessWeaponGoal", at = @At("STORE"), ordinal = 0)
    private int increaseSkeletonArrowSpeed(int x) {
        int i = isDoomMode() ? 5 : 20;
        if (this.level().getDifficulty() != Difficulty.HARD) {
            i = isDoomMode() ? 10 : 20;
        }
        return i;
    }
}