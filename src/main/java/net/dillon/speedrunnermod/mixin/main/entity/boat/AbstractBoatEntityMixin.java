package net.dillon.speedrunnermod.mixin.main.entity.boat;

import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.item.FireproofBoat;
import net.dillon.speedrunnermod.sound.ModSoundEvents;
import net.dillon.speedrunnermod.tag.ModFluidTags;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Supplier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * A mixin to register, control, and fix modded boats.
 */
@Author(Authors.ANXIETIE)
@Mixin(AbstractBoatEntity.class)
public abstract class AbstractBoatEntityMixin extends Entity implements FireproofBoat {
    @Shadow
    public abstract ActionResult interact(PlayerEntity player, Hand hand);
    @Shadow @Final
    private Supplier<Item> itemSupplier;
    @Unique
    private static final TrackedData<Boolean> FIREPROOF = DataTracker.registerData(AbstractBoatEntity.class, TrackedDataHandlerRegistry.BOOLEAN);

    public AbstractBoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /**
     * Makes all boats slightly slower in lava.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void applySpeedrunnerModBoatProperties(CallbackInfo ci) {
        AbstractBoatEntity abstractBoat = (AbstractBoatEntity)(Object)this;

        if (abstractBoat.isInLava()) {
            abstractBoat.setVelocity(abstractBoat.getVelocity().multiply(ModUtil.LAVA_BOAT_VELOCITY_MULTIPLIER));
        }

        if (ModEntityTypes.isFastBoat(this.itemSupplier)) {
            abstractBoat.setVelocity(abstractBoat.getVelocity().multiply(ModUtil.FAST_BOAT_VELOCITY_MULTIPLIER));
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
     * Makes fireproof boats fire immune.
     */
    @Override
    public boolean isFireImmune() {
        if (options().main.lavaBoats.getCurrentValue()) {
            return ModEntityTypes.isFireproofBoat((AbstractBoatEntity)(Object)this) || super.isFireImmune();
        } else {
            return super.isFireImmune();
        }
    }

    /**
     * Sets the boat to be fireproof.
     */
    @Override
    public void setFireproof(boolean fireproof) {
        this.dataTracker.set(FIREPROOF, fireproof);
    }

    /**
     * @return if the boat is legitimately fireproof.
     */
    @Override
    public boolean isFireproof() {
        return this.dataTracker.get(FIREPROOF);
    }

    /**
     * Creates the {@code fireproof data tracker.}
     */
    @Inject(method = "initDataTracker", at = @At("TAIL"))
    private void writeFireproofTracker(DataTracker.Builder builder, CallbackInfo ci) {
        builder.add(FIREPROOF, false);
    }

    /**
     * Writes the {@code fireproof tracker} to NBT.
     */
    @Inject(method = "writeCustomData", at = @At("TAIL"))
    private void writeFireproofToNbt(WriteView view, CallbackInfo ci) {
        view.putBoolean("Fireproof", this.isFireproof());
    }

    /**
     * Reads the {@code fireproof tracker} by NBT and writes it back.
     */
    @Inject(method = "readCustomData", at = @At("TAIL"))
    private void readFireproofFromNbt(ReadView view, CallbackInfo ci) {
        this.setFireproof(view.getBoolean("Fireproof", false));
    }

    /**
     * Allows the modded boats to float in lava, just like it would in water.
     */
    @Redirect(method = "checkBoatInWater", at = @At(value = "FIELD", target = "Lnet/minecraft/registry/tag/FluidTags;WATER:Lnet/minecraft/registry/tag/TagKey;"))
    private TagKey<Fluid> checkBoatInLava() {
        return ModFluidTags.BOAT_SAFE_FLUIDS;
    }

    /**
     * Fixes a bug where fireproof boats go slightly under lava when landing on it from a high distance.
     */
    @Redirect(method = "getWaterHeightBelow", at = @At(value = "FIELD", target = "Lnet/minecraft/registry/tag/FluidTags;WATER:Lnet/minecraft/registry/tag/TagKey;"))
    private TagKey<Fluid> redirectWaterHeight() {
        return ModFluidTags.BOAT_SAFE_FLUIDS;
    }
}