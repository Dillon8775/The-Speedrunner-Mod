package net.dillon.speedrunnermod.mixin.entity.living;

import net.dillon.dillonlib.util.Arithmetics;
import net.dillon.speedrunnermod.component.ModAttributes;
import net.dillon.speedrunnermod.component.ModMobEffects;
import net.dillon.speedrunnermod.helper.InventoryPreserver;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModEntityTypeTags;
import net.dillon.speedrunnermod.tag.ModItemTags;
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
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.function.Predicate;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

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
    @Shadow
    public abstract float getHealth();

    @Shadow
    public abstract double getAttributeValue(Holder<Attribute> attribute);

    @Unique
    private boolean hadInventoryPreserver = false;

    public LivingEntityMixin(EntityType<?> type, Level world) {
        super(type, world);
    }

    /**
     * Adds all modded attributes to a living entity.
     */
    @Inject(method = "createLivingAttributes", at = @At("RETURN"), cancellable = true)
    private static void addModdedAttributes(CallbackInfoReturnable<AttributeSupplier.Builder> cir) {
        cir.setReturnValue(cir.getReturnValue()
                .add(ModAttributes.BONUS_AIR_RECOVERY)
                .add(ModAttributes.BONUS_BOAT_MOVEMENT_SPEED)
                .add(ModAttributes.BONUS_BOW_CHARGE_SPEED)
                .add(ModAttributes.BONUS_BOW_POWER)
                .add(ModAttributes.BONUS_RANGE)
                .add(ModAttributes.BONUS_COOLDOWN)
                .add(ModAttributes.BONUS_SPEAR_LUNGE_MOMENTUM)
                .add(ModAttributes.BONUS_SPEAR_LUNGE_EXHAUSTION)
                .add(ModAttributes.BONUS_TARGET_DAMAGE)
                .add(ModAttributes.BONUS_INERTIA)
                .add(ModAttributes.IMPERATIVE_DAMAGE)
                .add(ModAttributes.IMPERATIVE_PROTECTION)
                .add(ModAttributes.SHRIEKER_STEALTH)
                .add(ModAttributes.PIGLIN_STEALTH)
                .add(ModAttributes.DOOM_BLOCK_IMMUNITY)
                .add(ModAttributes.DRAGONBANE)
                .add(ModAttributes.INVENTORY_PRESERVATION)
                .add(ModAttributes.LAVA_MOVEMENT_EFFICIENCY)
                .add(ModAttributes.LAVA_INVULNERABILITY)
                .add(ModAttributes.UNDERWATER_VISION)
                .add(ModAttributes.WITHERED_EFFECT)
        );
    }

    /**
     * Prevents the player from losing their inventory upon death.
     */
    @Inject(method = "dropAllDeathLoot", at = @At("HEAD"), cancellable = true)
    private void preventDrop(ServerLevel world, DamageSource damageSource, CallbackInfo ci) {
        LivingEntity user = (LivingEntity) (Object)this;
        if (!(user instanceof Player player)) {
            return;
        }
        if (!(player.level() instanceof ServerLevel serverLevel)) {
            return;
        }
        if (serverLevel.getGameRules().get(GameRules.KEEP_INVENTORY)) {
            return;
        }

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
     * Applies particles around the entity when they have the dragon's aura effect.
     */
    @Inject(method = "aiStep", at = @At("HEAD"))
    private void applyAuraParticles(CallbackInfo ci) {
        if (!this.hasEffect(ModMobEffects.DRAGONS_AURA)) {
            return;
        }

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

    /**
     * Inflicts the {@code withered} enchantment effects.
     */
    @Inject(method = "setLastHurtMob", at = @At("TAIL"))
    private void witheredEffect(Entity target, CallbackInfo ci) {
        LivingEntity self = (LivingEntity) (Object)this;
        float witheredEffect = (float)self.getAttributeValue(ModAttributes.WITHERED_EFFECT);
        if (!(witheredEffect > 0.0F) || (!(target instanceof LivingEntity livingTarget))) {
            return;
        }

        int level = (int)witheredEffect;
        int amplifier = -3 + level;
        if (amplifier < 0) {
            amplifier = 0;
        }
        if (level > 10) {
            level = 10;
        }
        livingTarget.addEffect(new MobEffectInstance(MobEffects.WITHER, Arithmetics.sas(3 + level), amplifier));
    }

    /**
     * Reduces damage dealt to entities with a speedrunner chestplate.
     */
    @Inject(method = "getDamageAfterArmorAbsorb", at = @At("TAIL"), cancellable = true)
    private void speedrunnerModifyArmorDamage(DamageSource source, float damage, CallbackInfoReturnable<Float> cir) {
        Entity attacker = source.getEntity();

        if (!(attacker instanceof LivingEntity livingAttacker)) {
            return;
        }
        if (!livingAttacker.is(ModEntityTypeTags.SPEEDRUNNER_IMPERATIVE_MOBS)) {
            return;
        }

        LivingEntity victim = (LivingEntity)(Object)this;
        float imperative = (float) victim.getAttributeValue(ModAttributes.IMPERATIVE_PROTECTION);
        if (!(imperative > 1.0F)) {
            return;
        }

        float multiplier = 0.5F + imperative;
        cir.setReturnValue(damage / multiplier);
    }

    /**
     * Allows entities to swim upward in lava at a faster rate.
     */
    @Inject(method = "jumpInLiquid", at = @At("HEAD"), cancellable = true)
    private void swimUpward(TagKey<Fluid> type, CallbackInfo ci) {
        LivingEntity living = (LivingEntity)(Object)this;
        float lavaMovement = (float)living.getAttributeValue(ModAttributes.LAVA_MOVEMENT_EFFICIENCY) - 1.0F;
        if (this.isInLava() && lavaMovement > 0.0F) {
            this.setDeltaMovement(this.getDeltaMovement().add(0.0D, Math.clamp(lavaMovement, 0.0F, 0.08F), 0.0D));
            ci.cancel();
        }
    }

    /**
     * Adds some features to the players movement speed and abilities, such as the {@code dash enchantment,} and swimming speeds in water and lava.
     */
    @Inject(method = "travel", at = @At("TAIL"))
    private void applyMovementEffects(Vec3 movementInput, CallbackInfo ci) {
        FluidState fluidState = this.level().getFluidState(this.blockPosition());
        float lavaMovement = (float)this.getAttributeValue(ModAttributes.LAVA_MOVEMENT_EFFICIENCY) - 1.075F;
        float clamped = lavaMovement == -0.015000105F ? 0.020F : lavaMovement;
        if (clamped > 0.0F && this.isInLava() && this.isAffectedByFluids() && !this.canStandOnFluid(fluidState)) {
            this.moveRelative(Math.clamp(clamped, 0.020F, clamped), movementInput);
            if (!this.isNoGravity()) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.01D, 0.0D));
            }

            if (this.getItemBySlot(EquipmentSlot.FEET).is(ModItemTags.SPEED_BOOTS) && this.getRandom().nextFloat() < 0.01F) {
                this.getItemBySlot(EquipmentSlot.FEET).hurtAndBreak(1, (LivingEntity)(Object)this, EquipmentSlot.FEET);
            }
        }
    }

    /**
     * Applies {@code fire resistance} for {@code 2 minutes} when using a totem.
     */
    @Inject(method = "checkTotemDeathProtection", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/component/DeathProtection;applyEffects(Lnet/minecraft/world/item/ItemStack;Lnet/minecraft/world/entity/LivingEntity;)V"))
    private void applyFireResistance(DamageSource source, CallbackInfoReturnable<Boolean> cir) {
        this.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, Arithmetics.mas(2), 0));
    }

    /**
     * Makes the player immune to {@code kinetic damage}, if disabled.
     */
    @Inject(method = "handleFallFlyingCollisions", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;hurt(Lnet/minecraft/world/damagesource/DamageSource;F)V"), cancellable = true)
    private void cancelOutElytraDamage(double oldSpeed, double newSpeed, CallbackInfo ci) {
        if (!common().general.kineticDamage.getCurrentValue()) {
            ci.cancel();
        }
    }

    /**
     * Disables the sound from playing due to {@code kinetic damage}, if disabled.
     */
    @Inject(method = "handleFallFlyingCollisions", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;playSound(Lnet/minecraft/sounds/SoundEvent;FF)V"), cancellable = true)
    private void cancelOutElytraDamageSound(double oldSpeed, double newSpeed, CallbackInfo ci) {
        if (!common().general.kineticDamage.getCurrentValue()) {
            ci.cancel();
        }
    }
}