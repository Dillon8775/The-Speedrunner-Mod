package net.dillon.speedrunnermod.mixin.main.entity.boat;

import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.sound.ModSoundEvents;
import net.dillon.speedrunnermod.tag.ModFluidTags;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.util.ModConstants;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.vehicle.AbstractBoatEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
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
public abstract class AbstractBoatEntityMixin extends Entity {
    @Shadow
    public abstract ActionResult interact(PlayerEntity player, Hand hand);
    @Shadow @Final
    private Supplier<Item> itemSupplier;

    public AbstractBoatEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /**
     * Makes all boats slightly slower in lava.
     */
    @Inject(method = "tick", at = @At("HEAD"))
    private void slowDownBoats(CallbackInfo ci) {
        AbstractBoatEntity abstractBoat = (AbstractBoatEntity)(Object)this;

        if (abstractBoat.isInLava()) {
            abstractBoat.setVelocity(abstractBoat.getVelocity().multiply(ModConstants.LAVA_BOAT_VELOCITY_MULTIPLIER));
        }

        if (ModEntityTypes.isFastBoat(this.itemSupplier)) {
            abstractBoat.setVelocity(abstractBoat.getVelocity().multiply(ModConstants.FAST_BOAT_VELOCITY_MULTIPLIER));
        }
    }

    /**
     * Allows the paddling in lava sound to play when paddling a boat in lava.
     */
    @Inject(method = "getPaddleSound", at = @At("HEAD"), cancellable = true)
    public void getPaddleSoundEvent(CallbackInfoReturnable<SoundEvent> cir) {
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
            return ModEntityTypes.isFireproofBoat(this.itemSupplier) || super.isFireImmune();
        } else {
            return super.isFireImmune();
        }
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
    private TagKey<Fluid> fireproofBoats() {
        return ModFluidTags.BOAT_SAFE_FLUIDS;
    }
}