package net.dillon.speedrunnermod.mixin.entity.player;

import net.dillon.speedrunnermod.effect.ModStatusEffects;
import net.dillon.speedrunnermod.item.GoldenShieldItem;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerShieldItem;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Giant;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemCooldowns;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

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
     * Makes the Giant disable player's shields.
     */
    @Inject(method = "blockUsingItem", at = @At("TAIL"))
    private void allowSpeedrunnerShieldToTakeHit(ServerLevel world, LivingEntity attacker, CallbackInfo ci) {
        if (isDoomMode() && attacker instanceof Giant) {
            int cooldownLevel = (ModUtil.getItemCooldown((Player)(Object)this) * 5) * 2 /* Doubled cooldown because it's Giant >:) */;
            this.getCooldowns().addCooldown(Items.SHIELD.getDefaultInstance(), cooldownLevel);
            this.getCooldowns().addCooldown(ModItems.SPEEDRUNNER_SHIELD.getDefaultInstance(), (int)(cooldownLevel / SpeedrunnerShieldItem.COOLDOWN_DIVIDER));
            this.getCooldowns().addCooldown(ModItems.GOLDEN_SHIELD.getDefaultInstance(), (int)(cooldownLevel / GoldenShieldItem.COOLDOWN_DIVIDER));
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
        if (this.getMainHandItem().is(ModItems.DRAGONS_SWORD) || this.getOffhandItem().is(ModItems.DRAGONS_SWORD)) {
            this.level().addParticle(ParticleTypes.PORTAL, this.getRandomX(0.5D), this.getRandomY() - 0.25D, this.getRandomZ(0.5D), (this.level().getRandom().nextDouble() - 0.5D) * 2.0D, -this.level().getRandom().nextDouble(), (this.level().getRandom().nextDouble() - 0.5D) * 2.0D);
        }
    }

    /**
     * Gives the player the ender dragon dying sound when they have the dragon's aura effect.
     */
    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void dragonsAuraDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        if (this.hasEffect(ModStatusEffects.DRAGONS_AURA)) {
            cir.setReturnValue(SoundEvents.ENDER_DRAGON_HURT);
        }
    }

    /**
     * Allows player to hold their breath for a longer period of time while underwater.
     */
    @Override
    protected int decreaseAirSupply(int air) {
        if (options().advanced.higherBreathTime.getCurrentValue() && this.random.nextInt((isDoomMode() ? 1 : 4)) > 0) {
            return air;
        }

        return super.decreaseAirSupply(air);
    }

    /**
     * Allows players catch their breath faster after coming out of the water.
     */
    @Override
    public int increaseAirSupply(int air) {
        return Math.min(air + ModUtil.getPlayerBreathTime(), this.getMaxAirSupply());
    }
}