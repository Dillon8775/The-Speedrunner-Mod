package net.dillon.speedrunnermod.mixin.entity.core;

import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(Entity.class)
public abstract class EntityMixin {
    @Shadow
    public abstract DamageSources damageSources();
    @Shadow
    public abstract @Nullable Entity getVehicle();
    @Shadow
    public abstract Level level();
    @Shadow
    private int remainingFireTicks;

    /**
     * Decreases time set on fire for from lava.
     */
    @ModifyArg(method = "lavaIgnite", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;igniteForSeconds(F)V"))
    private float changeFireFromLavaTime(float x) {
        return ModUtil.getFireDamageFromLavaDuration();
    }

    /**
     * Decreases damage from lava.
     */
    @ModifyArg(method = "lavaHurt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/Entity;hurtServer(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/damagesource/DamageSource;F)Z"))
    private float changeLavaDamageAmount(float x) {
        return ModUtil.getLavaDamageValue();
    }

    /**
     * Allows players to ride in fireproof boats and chest without burning from the lava.
     */
    @Author(Authors.ANXIETIE)
    @Inject(method = {"lavaHurt", "igniteForSeconds"}, at = @At("HEAD"), cancellable = true)
    private void setOnFireFromLava(CallbackInfo ci) {
        Entity vehicle = getVehicle();
        if (options().general.lavaBoats.getCurrentValue()) {
            if (vehicle instanceof AbstractBoat abstractBoat && ModEntityTypes.isFireproofBoat(abstractBoat)) {
                if (this.remainingFireTicks > 0 && this.remainingFireTicks % 20 == 0) {
                    ((Entity)(Object)this).hurtServer((ServerLevel)this.level(), this.damageSources().onFire(), 1.0F);
                }
                ci.cancel();
            }
        }
    }

    /**
     * Allows speedrunner shears to work when interacting.
     */
    @Redirect(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 0))
    private boolean redirectShearsOrdinalZero(ItemStack heldItem, Object o) {
        return heldItem.is(ConventionalItemTags.SHEAR_TOOLS);
    }

    /**
     * For the second call of the shears item.
     */
    @Redirect(method = "interact", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z", ordinal = 1))
    private boolean redirectShearsOrdinalOne(ItemStack heldItem, Object o) {
        return heldItem.is(ConventionalItemTags.SHEAR_TOOLS);
    }
}