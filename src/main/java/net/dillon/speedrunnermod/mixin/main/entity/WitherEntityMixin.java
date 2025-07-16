package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(WitherEntity.class)
public class WitherEntityMixin extends HostileEntity {

    public WitherEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies the {@code maximum health} for the wither.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeWitherMaxHealth(EntityType<? extends WitherEntity> entityType, World world, CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, ModUtil.getWitherMaxHealth());
    }

    /**
     * Prevents the wither from taking damage from {@code Goliath.}
     */
    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void immuneToGoliath(ServerWorld world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getAttacker() instanceof GiantEntity) {
            cir.setReturnValue(false);
        }
    }

    @Override
    public void onDeath(DamageSource source) {
        super.onDeath(source);
        if (this.getAttacker() instanceof PlayerEntity player && isDoomMode()) {
            ModUtil.completeStepS2C(TutorialStep.KILL_WITHER, player, "speedrunnermod.tutorial_mode.kill_dragon");
        }
    }
}