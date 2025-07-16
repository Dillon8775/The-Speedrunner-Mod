package net.dillon.speedrunnermod.mixin.main.entity.basic;

import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public abstract DamageSources getDamageSources();
    @Shadow
    public abstract @Nullable Entity getVehicle();
    @Shadow
    public abstract World getWorld();
    @Shadow
    private int fireTicks;
    /**
     * Decreases time set on fire for from lava.
     */
    @ModifyArg(method = "igniteByLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(F)V"))
    private float changeFireFromLavaTime(float x) {
        return ModUtil.getFireDamageFromLavaDuration();
    }

    /**
     * Decreases damage from lava.
     */
    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float changeLavaDamageAmount(float x) {
        return ModUtil.getLavaDamageValue();
    }

    /**
     * Allows players to ride in fireproof boats and chest without burning from the lava.
     */
    @Author(Authors.ANXIETIE)
    @Inject(method = {"setOnFireFromLava", "setOnFireFor"}, at = @At("HEAD"), cancellable = true)
    private void setOnFireFromLava(CallbackInfo ci) {
        Entity vehicle = getVehicle();
        if (options().main.lavaBoats.getCurrentValue()) {
            if (vehicle instanceof AbstractBoatEntity abstractBoat && ModEntityTypes.isFireproofBoat(abstractBoat)) {
                if (this.fireTicks > 0 && this.fireTicks % 20 == 0) {
                    ((Entity)(Object)this).damage((ServerWorld)this.getWorld(), this.getDamageSources().onFire(), ModUtil.getLavaDamageValue());
                }
                ci.cancel();
            }
        }
    }
}