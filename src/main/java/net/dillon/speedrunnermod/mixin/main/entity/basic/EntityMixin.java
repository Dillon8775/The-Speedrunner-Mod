package net.dillon.speedrunnermod.mixin.main.entity.basic;

import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSources;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
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
    public World world;
    @Shadow
    public abstract DamageSources getDamageSources();
    @Shadow
    public abstract @Nullable Entity getVehicle();
    @Shadow
    public abstract World getWorld();

    /**
     * Decreases time set on fire for from lava.
     */
    @ModifyArg(method = "igniteByLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;setOnFireFor(F)V"))
    private float setOnFireFromLavaTime(float x) {
        return ModUtil.getFireDamageFromLavaDuration();
    }

    /**
     * Decreases damage from lava.
     */
    @ModifyArg(method = "setOnFireFromLava", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/Entity;damage(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/damage/DamageSource;F)Z"))
    private float setOnFireFromLavaAmount(float x) {
        return ModUtil.getLavaDamageValue();
    }

    /**
     * Allows players to ride in fireproof boats and chest without burning from the lava.
     */
    @Inject(method = {"setOnFireFromLava", "setOnFireFor"}, at = @At("HEAD"), cancellable = true)
    private void setOnFireFromLava(CallbackInfo ci) {
        Entity vehicle = getVehicle();
        if (options().main.lavaBoats.getCurrentValue() && vehicle instanceof AbstractBoatEntity abstractBoat && ModEntityTypes.isFireproofBoat(abstractBoat.itemSupplier)) {
            ci.cancel();
        }
    }
}