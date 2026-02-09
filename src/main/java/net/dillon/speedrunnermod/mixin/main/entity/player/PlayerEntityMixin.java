package net.dillon.speedrunnermod.mixin.main.entity.player;

import net.dillon.speedrunnermod.entity.ModStatusEffects;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.SpeedrunnerShieldItem;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    public abstract ItemCooldownManager getItemCooldownManager();
    @Shadow
    public abstract boolean damage(ServerWorld world, DamageSource source, float amount);
    @Shadow
    public abstract PlayerInventory getInventory();

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Makes the Giant disable player's shields.
     */
    @Inject(method = "takeShieldHit", at = @At("TAIL"))
    private void allowSpeedrunnerShieldToTakeHit(ServerWorld world, LivingEntity attacker, CallbackInfo ci) {
        if (isDoomMode() && attacker instanceof GiantEntity) {
            int cooldownLevel = (ModUtil.getItemCooldown((PlayerEntity)(Object)this) * 5) * 2 /* Doubled cooldown because it's Giant >:) */;
            this.getItemCooldownManager().set(Items.SHIELD.getDefaultStack(), cooldownLevel);
            this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD.getDefaultStack(), (int)(cooldownLevel / SpeedrunnerShieldItem.COOLDOWN_DIVIDER));
            this.clearActiveItem();
            this.getEntityWorld().sendEntityStatus(this, (byte)30);
        }
    }

    /**
     * Adds particles around the player if they are holding a {@code Dragon's Sword}.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void addDragonsSwordParticles(CallbackInfo ci) {
        if (this.getMainHandStack().isOf(ModItems.DRAGONS_SWORD) || this.getOffHandStack().isOf(ModItems.DRAGONS_SWORD)) {
            this.getEntityWorld().addParticleClient(ParticleTypes.PORTAL, this.getParticleX(0.5D), this.getRandomBodyY() - 0.25D, this.getParticleZ(0.5D), (this.getEntityWorld().random.nextDouble() - 0.5D) * 2.0D, -this.getEntityWorld().random.nextDouble(), (this.getEntityWorld().random.nextDouble() - 0.5D) * 2.0D);
        }
    }

    /**
     * Gives the player the ender dragon dying sound when they have the dragon's aura effect.
     */
    @Inject(method = "getDeathSound", at = @At("HEAD"), cancellable = true)
    private void dragonsAuraDeathSound(CallbackInfoReturnable<SoundEvent> cir) {
        if (this.hasStatusEffect(ModStatusEffects.DRAGONS_AURA)) {
            cir.setReturnValue(SoundEvents.ENTITY_ENDER_DRAGON_HURT);
        }
    }

    /**
     * Allows player to hold their breath for a longer period of time while underwater.
     */
    @Override
    protected int getNextAirUnderwater(int air) {
        if (options().advanced.higherBreathTime.getCurrentValue() && this.random.nextInt(4) > 0) {
            return air;
        }

        return super.getNextAirUnderwater(air);
    }

    /**
     * Allows players catch their breath faster after coming out of the water.
     */
    @Override
    public int getNextAirOnLand(int air) {
        return Math.min(air + ModUtil.getPlayerBreathTime(), this.getMaxAir());
    }
}