package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.boss.wither.WitherBoss;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(WitherBoss.class)
public class WitherBossMixin extends Monster {

    public WitherBossMixin(EntityType<? extends Monster> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Modifies the {@code maximum health} for the wither.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeWitherMaxHealth(EntityType<? extends WitherBoss> entityType, Level world, CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, ModUtil.getWitherMaxHealth());
    }

    /**
     * Prevents the wither from taking damage from {@code Goliath.}
     */
    @Inject(method = "hurtServer", at = @At("HEAD"), cancellable = true)
    private void immuneToGoliath(ServerLevel world, DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
        if (source.getEntity() instanceof Giant) {
            cir.setReturnValue(false);
        }
    }
}