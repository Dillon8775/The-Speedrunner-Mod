package net.dillon.speedrunnermod.mixin.entity.boat;

import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.item.FireproofBoat;
import net.dillon.speedrunnermod.sound.ModSoundEvents;
import net.dillon.speedrunnermod.tag.ModFluidTags;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * A mixin to register, control, and fix modded boats.
 */
@Author(Authors.ANXIETIE)
@Mixin(AbstractBoat.class)
public abstract class AbstractBoatMixin extends Entity implements FireproofBoat {
    @Shadow
    public abstract InteractionResult interact(final Player player, final InteractionHand hand, final Vec3 location);
    @Unique
    private static final EntityDataAccessor<Boolean> FIREPROOF = SynchedEntityData.defineId(AbstractBoat.class, EntityDataSerializers.BOOLEAN);
    @Unique
    private static final EntityDataAccessor<Float> BOAT_SPEED = SynchedEntityData.defineId(AbstractBoat.class, EntityDataSerializers.FLOAT);

    public AbstractBoatMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    /**
     * Sets the boat to be fireproof.
     */
    @Override
    public void setFireproof(boolean fireproof) {
        this.entityData.set(FIREPROOF, fireproof);
    }

    /**
     * @return if the boat is legitimately fireproof.
     */
    @Override
    public boolean isFireproof() {
        return this.entityData.get(FIREPROOF);
    }

    /**
     * Sets the boat's speed.
     */
    @Override
    public void setBoatSpeed(float boatSpeed) {
        this.entityData.set(BOAT_SPEED, boatSpeed);
    }

    /**
     * @return the boat's sped.
     */
    @Override
    public float getBoatSpeed() {
        return this.entityData.get(BOAT_SPEED);
    }

    /**
     * Creates the {@code fireproof data tracker.}
     */
    @Inject(method = "defineSynchedData", at = @At("TAIL"))
    private void writeFireproofTracker(SynchedEntityData.Builder builder, CallbackInfo ci) {
        builder.define(FIREPROOF, false);
        builder.define(BOAT_SPEED, 0.0F);
    }

    /**
     * Writes the {@code fireproof tracker} to NBT.
     */
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void writeFireproofToNbt(ValueOutput view, CallbackInfo ci) {
        view.putBoolean("Fireproof", this.isFireproof());
        view.putFloat("BoatSpeed", this.getBoatSpeed());
    }

    /**
     * Reads the {@code fireproof tracker} by NBT and writes it back.
     */
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readFireproofFromNbt(ValueInput view, CallbackInfo ci) {
        this.setFireproof(view.getBooleanOr("Fireproof", false));
        this.setBoatSpeed(view.getFloatOr("BoatSpeed", 0.0F));
    }

    /**
     * Makes all boats slightly slower in lava.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void applySpeedrunnerModBoatProperties(CallbackInfo ci) {
        AbstractBoat abstractBoat = (AbstractBoat)(Object)this;

        if (abstractBoat.isInLava()) {
            abstractBoat.setDeltaMovement(abstractBoat.getDeltaMovement().scale(ModHelper.LAVA_BOAT_VELOCITY_MULTIPLIER));
        }

        if (this.getBoatSpeed() > 0.0F) {
            abstractBoat.setDeltaMovement(abstractBoat.getDeltaMovement().scale(1.0F + (this.getBoatSpeed() / 10.0F)));
        }
    }

    /**
     * Allows the paddling in lava sound to play when paddling a boat in lava.
     */
    @Inject(method = "getPaddleSound", at = @At("HEAD"), cancellable = true)
    public void lavaPaddleSound(CallbackInfoReturnable<SoundEvent> cir) {
        if (this.isInLava()) {
            cir.setReturnValue(ModSoundEvents.ENTITY_BOAT_PADDLE_LAVA);
        }
    }

    /**
     * Makes fireproof boats float higher, to make their rendering appear correct.
     */
    @ModifyConstant(method = "floatBoat", constant = @Constant(doubleValue = 0.65D))
    private double floatFireproofBoofHigher(double constant) {
        return this.isFireproof() ? 0.30F : constant;
    }

    /**
     * Makes fireproof boats fire immune.
     */
    @Override
    public boolean fireImmune() {
        return this.isFireproof() || super.fireImmune();
    }

    /**
     * Allows the modded boats to float in lava, just like it would in water.
     */
    @Redirect(method = "checkInWater", at = @At(value = "FIELD", target = "Lnet/minecraft/tags/FluidTags;WATER:Lnet/minecraft/tags/TagKey;"))
    private TagKey<Fluid> checkBoatInLava() {
        return ModFluidTags.BOAT_SAFE_FLUIDS;
    }

    /**
     * Fixes a bug where fireproof boats go slightly under lava when landing on it from a high distance.
     */
    @Redirect(method = "getWaterLevelAbove", at = @At(value = "FIELD", target = "Lnet/minecraft/tags/FluidTags;WATER:Lnet/minecraft/tags/TagKey;"))
    private TagKey<Fluid> redirectWaterHeight() {
        return ModFluidTags.BOAT_SAFE_FLUIDS;
    }
}