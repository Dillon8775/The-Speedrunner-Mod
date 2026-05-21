package net.dillon.speedrunnermod.mixin.entity.living;

import net.dillon.speedrunnermod.effect.ModMobEffects;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.event.SpeedrunnersTotemEvent;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnersTotemItem;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.util.InventoryPreserver;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.DeathProtection;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.function.Predicate;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity implements InventoryPreserver {
    @Shadow @Final
    public abstract boolean addEffect(MobEffectInstance effect);
    @Shadow
    public abstract ItemStack getItemBySlot(EquipmentSlot var1);
    @Shadow
    protected abstract boolean isAffectedByFluids();
    @Shadow
    public abstract boolean canStandOnFluid(FluidState fluidState);
    @Shadow
    public abstract void stopRiding();
    @Shadow
    public abstract boolean hasEffect(Holder<MobEffect> effect);
    @Shadow
    public abstract boolean addEffect(MobEffectInstance effect, @Nullable Entity source);
    @Shadow
    public abstract void setHealth(float health);
    @Shadow
    public abstract ItemStack getActiveItem();
    @Shadow
    public static final Predicate<LivingEntity> PLAYER_NOT_WEARING_DISGUISE_ITEM = entity -> {
        if (entity instanceof Player playerEntity) {
            if (playerEntity.hasEffect(ModMobEffects.DRAGONS_AURA)) {
                return false;
            }
            ItemStack itemStack = playerEntity.getItemBySlot(EquipmentSlot.HEAD);
            return !itemStack.is(ItemTags.GAZE_DISGUISE_EQUIPMENT);
        } else {
            return true;
        }
    };
    @Unique
    private boolean hadInventoryPreserver = false;

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    /**
     * Adds some features to the players movement speed and abilities, such as the {@code dash enchantment,} and swimming speeds in water and lava.
     */
    @Inject(method = "travel", at = @At("TAIL"))
    private void applyMovementEffects(Vec3 movementInput, CallbackInfo ci) {
        if (this.getItemBySlot(EquipmentSlot.FEET).is(ModItemTags.SPEED_BOOTS) || EnchantmentHelper.getEnchantmentLevel(ModUtil.enchantment((LivingEntity)(Object)this, ModEnchantments.DASH), (LivingEntity)(Object)this) > 0) {
            int dashEnchantmentLevel = EnchantmentHelper.getEnchantmentLevel(ModUtil.enchantment((LivingEntity)(Object)this, ModEnchantments.DASH), (LivingEntity)(Object)this);
            FluidState fluidState = this.level().getFluidState(this.blockPosition());
            float lavaVelocity = dashEnchantmentLevel > 8 ? (0.1F * dashEnchantmentLevel) / 6.0F : dashEnchantmentLevel == 8 ? 0.1F : dashEnchantmentLevel == 7 ? 0.090F : dashEnchantmentLevel == 6 ? 0.080F : dashEnchantmentLevel == 5 ? 0.070F : dashEnchantmentLevel == 4 ? 0.060F : dashEnchantmentLevel == 3 ? 0.045F : dashEnchantmentLevel == 2 ? 0.040F : dashEnchantmentLevel == 1 ? 0.035F : 0.025F;
            boolean isBuffedItems = this.getItemBySlot(EquipmentSlot.FEET).is(ModItemTags.SPEED_BOOTS) && this.getRandom().nextFloat() < 0.01F;
            if (this.isInLava() && this.isAffectedByFluids() && !this.canStandOnFluid(fluidState)) {
                this.moveRelative(lavaVelocity, movementInput);
                if (!this.isNoGravity()) {
                    this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.02D, 0.0D));
                }

                if (isBuffedItems) {
                    this.getItemBySlot(EquipmentSlot.FEET).hurtAndBreak(1, (LivingEntity)(Object)this, EquipmentSlot.FEET);
                }
            }
        }
    }

    /**
     * Prevents the player from losing their inventory upon death.
     */
    @Inject(method = "dropAllDeathLoot", at = @At("HEAD"), cancellable = true)
    private void preventDrop(ServerLevel world, DamageSource damageSource, CallbackInfo ci) {
        if ((LivingEntity)(Object)this instanceof Player player) {
            for (int i = 0; i < player.getInventory().getContainerSize(); i++) {
                ItemStack stack = player.getInventory().getItem(i);
                if (stack.is(ModItems.INVENTORY_PRESERVER)) {
                    if (stack.getDamageValue() + 1 == stack.getMaxDamage()) {
                        player.getInventory().removeItemNoUpdate(i);
                    } else {
                        stack.hurtWithoutBreaking(1, player);
                    }
                    this.hadInventoryPreserver = true;
                    ci.cancel();
                    break;
                }
            }
        }
    }

    /**
     * @return if the player had an inventory preserver upon death.
     */
    @Override
    public boolean hadInventoryPreserver() {
        return this.hadInventoryPreserver;
    }

    /**
     * Removes the inventory preserver from the player.
     */
    @Override
    public void removeInventoryPreserver() {
        this.hadInventoryPreserver = false;
    }

    /**
     * Writes the {@code inventory preserver boolean} to NBT.
     */
    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void writeInventoryPreserver(ValueOutput view, CallbackInfo ci) {
        view.putBoolean("HadInventoryPreserver", this.hadInventoryPreserver);
    }

    /**
     * Reads the {@code inventory preserver boolean} from NBT.
     */
    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void readInventoryPreserver(ValueInput view, CallbackInfo ci) {
        this.hadInventoryPreserver = view.getBooleanOr("HadInventoryPreserver", false);
    }

    // Calls the totemUse event if supposed to and not totem of undying, skipping vanilla setHealth stuff
    @Author(Authors.YELEEFFF)
    @Inject(method = "checkTotemDeathProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;setHealth(F)V"), locals = LocalCapture.CAPTURE_FAILEXCEPTION, cancellable = true)
    private void applySpeedrunnersTotemEffects(DamageSource source, CallbackInfoReturnable<Boolean> cir, ItemStack stack, DeathProtection deathProtectionComponent) {
        if (stack.getItem() instanceof SpeedrunnersTotemItem) {
            deathProtectionComponent.applyEffects(stack, (LivingEntity)(Object)this);

            SpeedrunnersTotemEvent.EVENT.invoker().invoke(((LivingEntity)(Object) this), stack, source);
            cir.setReturnValue(stack != null);
        }
    }

    /**
     * Cancels out all fall damage when an entity has the {@code Dragon's Aura effect.}
     */
    @Inject(method = "causeFallDamage", at = @At("HEAD"), cancellable = true)
    private void cancelFallDamageDragonsAura(double fallDistance, float damagePerDistance, DamageSource damageSource, CallbackInfoReturnable<Boolean> cir) {
        if (this.hasEffect(ModMobEffects.DRAGONS_AURA)) {
            cir.setReturnValue(false);
        }
    }

    /**
     * Applies a {@code 45% chance} to ignore armor damage when the entity has the {@code dragon's aura} effect.
     */
    @Inject(method = "doHurtEquipment", at = @At("HEAD"), cancellable = true)
    private void cancelOutDamageDragonsAura(DamageSource source, float amount, EquipmentSlot[] slots, CallbackInfo ci) {
        if (this.hasEffect(ModMobEffects.DRAGONS_AURA)) {
            if (this.random.nextFloat() < 0.45F) {
                ci.cancel();
            }
        }
    }

    /**
     * Inflicts the {@code withered} enchantment effects.
     */
    @Inject(method = "setLastHurtMob", at = @At("TAIL"))
    private void witheredEffect(Entity target, CallbackInfo ci) {
        if (EnchantmentHelper.getItemEnchantmentLevel(ModUtil.enchantment(this, ModEnchantments.WITHERED), this.getActiveItem()) > 0) {
            int level = EnchantmentHelper.getItemEnchantmentLevel(ModUtil.enchantment(this, ModEnchantments.WITHERED), this.getActiveItem());
            int amplifier = -3 + level;
            if (amplifier < 0) {
                amplifier = 0;
            }
            if (target instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(MobEffects.WITHER, ModUtil.secondsAsTicks(3 + level), amplifier));
            }
        }
    }

    /**
     * Applies particles around the entity when they have the dragon's aura effect.
     */
    @Inject(method = "aiStep", at = @At("HEAD"))
    private void applyAuraParticles(CallbackInfo ci) {
        if (this.hasEffect(ModMobEffects.DRAGONS_AURA)) {
            for (int i = 0; i < 2; i++) {
                this.level()
                        .addParticle(
                                ParticleTypes.PORTAL,
                                this.getRandomX(0.5),
                                this.getRandomY() - 0.25,
                                this.getRandomZ(0.5),
                                (this.random.nextDouble() - 0.5) * 5.0,
                                -this.random.nextDouble(),
                                (this.random.nextDouble() - 0.5) * 5.0
                        );
            }
        }
    }

    /**
     * Makes the player immune to {@code kinetic damage}, if disabled.
     */
    @Inject(method = "handleFallFlyingCollisions", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)V"), cancellable = true)
    private void cancelOutElytraDamage(double oldSpeed, double newSpeed, CallbackInfo ci) {
        if (!options().main.kineticDamage.getCurrentValue()) {
            ci.cancel();
        }
    }

    /**
     * Disables the sound from playing due to {@code kinetic damage}, if disabled.
     */
    @Inject(method = "handleFallFlyingCollisions", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"), cancellable = true)
    private void cancelOutElytraDamageSound(double oldSpeed, double newSpeed, CallbackInfo ci) {
        if (!options().main.kineticDamage.getCurrentValue()) {
            ci.cancel();
        }
    }

    /**
     * @author Dillon8775
     * @reason Allows entities to "swim upward" at a faster rate if they have on a {@code "buffed"} piece of armor.
     */
    @Overwrite
    public void jumpInLiquid(TagKey<Fluid> fluid) {
        double dashEnchantment = EnchantmentHelper.getEnchantmentLevel(ModUtil.enchantment((LivingEntity)(Object)this, ModEnchantments.DASH), (LivingEntity)(Object)this);
        if (this.isInLava() && this.getItemBySlot(EquipmentSlot.FEET).is(ModItemTags.SPEED_BOOTS)) {
            double velocity = dashEnchantment > 8 ? (0.21D * dashEnchantment) / 6.0D : dashEnchantment == 8 ? 0.21D : dashEnchantment == 7 ? 0.19D : dashEnchantment == 6 ? 0.17D : dashEnchantment == 5 ? 0.15D : dashEnchantment == 4 ? 0.13D : dashEnchantment == 3 ? 0.11D : dashEnchantment == 2 ? 0.09D : dashEnchantment == 1 ? 0.07D : 0.06D;
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, velocity, 0.0D));
        } else if (this.isInLava() && EnchantmentHelper.getEnchantmentLevel(ModUtil.enchantment((LivingEntity)(Object)this, ModEnchantments.DASH), (LivingEntity)(Object)this) > 0) {
            double velocity = dashEnchantment > 8 ? (0.20D * dashEnchantment) / 6.0D : dashEnchantment == 8 ? 0.20D : dashEnchantment == 7 ? 0.18D : dashEnchantment == 6 ? 0.16D : dashEnchantment == 5 ? 0.14D : dashEnchantment == 4 ? 0.12D : dashEnchantment == 3 ? 0.10D : dashEnchantment == 2 ? 0.08D : dashEnchantment == 1 ? 0.06D : 0.04D;
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, velocity, 0.0D));
        } else {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, 0.04D, 0.0D));
        }
    }

    /**
     * Applies {@code fire resistance} for {@code 2 minutes} when using a totem.
     */
    @Inject(method = "checkTotemDeathProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/DeathProtection;applyEffects(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)V"))
    private void applyFireResistance(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, ModUtil.minutesAsTicks(2), 0));
    }
}