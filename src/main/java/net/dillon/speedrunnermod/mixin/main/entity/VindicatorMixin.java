package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.illager.AbstractIllager;
import net.minecraft.world.entity.monster.illager.Vindicator;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Vindicator.class)
public abstract class VindicatorMixin extends AbstractIllager {

    public VindicatorMixin(EntityType<? extends AbstractIllager> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code vindicator} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeVindicatorAttributes(EntityType<? extends Vindicator> entityType, Level world, CallbackInfo ci) {
        ModUtil.modifyFollowRange(this, isDoomMode() ? 48.0D : 12.0D);
        ModUtil.modifyMaxHealth(this, isDoomMode() ? 20.0D : 24.0D);
    }

    /**
     * Inflicts players with {@code slowness} if {@code doom mode} is enabled.
     */
    @Override
    public boolean doHurtTarget(ServerLevel world, Entity target) {
        if (!super.doHurtTarget(world, target)) {
            return false;
        } else {
            if (isDoomMode() && target instanceof Player) {
                ((Player)target).addEffect(new MobEffectInstance(MobEffects.SLOWNESS, ModUtil.secondsAsTicks(10), 0));
            }

            return true;
        }
    }
}