package net.dillon.speedrunnermod.mixin.main.entity.basic;

import net.dillon.speedrunnermod.entity.ModBoats;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.util.ModConstants;
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

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public World world;
    @Shadow
    public abstract DamageSources getDamageSources();
    @Shadow
    public abstract @Nullable Entity getVehicle();
    @Shadow
    private int fireTicks;
    @Shadow
    public abstract World getWorld();

    /**
     * Decreases time set on fire for from lava.
     */
    @ModifyArg(method = "igniteByLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(F)V"))
    private float setOnFireFromLavaTime(float x) {
        return ModConstants.FIRE_DAMAGE_FROM_LAVA_DURATION;
    }

    /**
     * Decreases damage from lava.
     */
    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float setOnFireFromLavaAmount(float x) {
        return ModConstants.LAVA_DAMAGE_VALUE;
    }

    /**
     * Allows players to ride in fireproof boats and chest without burning from the lava.
     */
    @Author(Authors.ANXIETIE)
    @Inject(method = "setOnFireFromLava", at = @At("HEAD"), cancellable = true)
    private void setOnFireFromLava(CallbackInfo ci) {
        Entity vehicle = getVehicle();
        if (options().main.lavaBoats && this.getWorld() instanceof ServerWorld serverWorld) {
            if (vehicle instanceof AbstractBoatEntity abstractBoat && ModBoats.isFireproofBoat(abstractBoat.itemSupplier)) {
                if (fireTicks > 0 && fireTicks % 20 == 0) {
                    ((Entity)(Object)this).damage(serverWorld, this.getDamageSources().onFire(), 1.0F);
                }
                ci.cancel();
            }
        }
    }
}