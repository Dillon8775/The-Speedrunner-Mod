package net.dillon.speedrunnermod.mixin.entity.player;

import net.dillon.speedrunnermod.component.ModAttributes;
import net.dillon.speedrunnermod.component.ModMobEffects;
import net.dillon.speedrunnermod.helper.ModComponentHelper;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModEntityTypeTags;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.option.CommonModOptions.isDoomMode;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {
    @Shadow
    public abstract ItemCooldowns getCooldowns();
    @Shadow
    public abstract boolean hurtServer(ServerLevel world, DamageSource source, float amount);
    @Shadow
    public abstract Inventory getInventory();

    public PlayerMixin(EntityType<? extends LivingEntity> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * Increases attack damage based on certain conditions.
     */
    @ModifyVariable(method = "attack", at = @At("STORE"), ordinal = 0)
    private float speedrunnerSwordDamage(float original, Entity target) {
        if (target instanceof LivingEntity living) {
            Player self = (Player)(Object)this;
            float damage = original;

            if (living.is(ModEntityTypeTags.SPEEDRUNNER_IMPERATIVE_MOBS)) {
                damage *= (float)self.getAttributeValue(ModAttributes.IMPERATIVE_DAMAGE);
            }

            return damage;
        }

        return original;
    }

    /**
     * Makes the Giant disable player's shields.
     */
    @Inject(method = "blockUsingItem", at = @At("TAIL"))
    private void giantBlocksShields(final ServerLevel level, final LivingEntity attacker, final DamageSource source, final float damage, CallbackInfo ci) {
        if (!isDoomMode()) {
            return;
        }

        if (attacker instanceof Giant) {
            ItemStack heldItem = this.getActiveItem();
            int cooldownLevel = (ModComponentHelper.getItemCooldown(heldItem, (Player)(Object)this) * 5) * 2; // Doubled cooldown
            this.getCooldowns().addCooldown(Items.SHIELD.getDefaultInstance(), cooldownLevel);
            this.getCooldowns().addCooldown(ModItems.SPEEDRUNNER_SHIELD.getDefaultInstance(), (int)(cooldownLevel / 1.6F));
            this.getCooldowns().addCooldown(ModItems.GOLDEN_SHIELD.getDefaultInstance(), (int)(cooldownLevel / 1.9F));
            this.stopUsingItem();
            this.level().broadcastEntityEvent(this, (byte)30);
            this.level().playSound(null, this.blockPosition(), SoundEvents.SHIELD_BREAK.value(), SoundSource.PLAYERS);
        }
    }

    /**
     * Adds particles around the player if they are holding a {@code Dragon's Sword}.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void addDragonsSwordParticles(CallbackInfo ci) {
        if (!this.isHolding(heldItem -> heldItem.is(ModItemTags.DRAGON_PARTICLE_ITEMS))) {
            return;
        }

        for (int i = 0; i < 2; i++) {
            this.level().addParticle(ParticleTypes.PORTAL,
                    this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D),
                    (this.level().getRandom().nextDouble() - 0.5D) * 2.0D, -this.level().getRandom().nextDouble(),
                    (this.level().getRandom().nextDouble() - 0.5D) * 2.0D);
        }
    }

    /**
     * Gives the player the ender dragon dying sound when they have the dragon's aura effect.
     */
    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void dragonsAuraDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        if (this.hasEffect(ModMobEffects.DRAGONS_AURA)) {
            cir.setReturnValue(SoundEvents.ENDER_DRAGON_HURT);
        }
    }

    /**
     * Allows player to hold their breath for a longer period of time while underwater.
     */
    @Override
    protected int decreaseAirSupply(int air) {
        if (!common().advanced.increasedOxygen.getCurrentValue() || isDoomMode()) {
            return super.decreaseAirSupply(air);
        }

        if (this.random.nextInt(4) > 0) {
            return air;
        }

        return super.decreaseAirSupply(air);
    }

    /**
     * Allows players catch their breath faster after coming out of the water.
     */
    @Override
    public int increaseAirSupply(int air) {
        if (!common().advanced.increasedOxygen.getCurrentValue()) {
            return super.increaseAirSupply(air);
        }

        Player self = (Player) (Object)this;
        float increasedBreathTime = ModConstants.getPlayerBreathTime();
        float additionalAirRecovery = (float)self.getAttributeValue(ModAttributes.BONUS_AIR_RECOVERY);
        return Math.min(air + (int)(increasedBreathTime + additionalAirRecovery), this.getMaxAirSupply());
    }
}