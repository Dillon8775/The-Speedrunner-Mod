package net.dillon.speedrunnermod.mixin.main.entity.player;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.ModConstants;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    public abstract ItemCooldownManager getItemCooldownManager();
    @Shadow
    public abstract boolean damage(ServerWorld world, DamageSource source, float amount);

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Makes the Giant disable players' shields and shield cooldowns work correctly.
     */
    @Inject(method = "takeShieldHit", at = @At("TAIL"))
    private void takeShieldHit(ServerWorld world, LivingEntity attacker, CallbackInfo ci) {
        if (isDoomMode()) {
            if (attacker instanceof GiantEntity) {
                int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ModUtil.enchantment((PlayerEntity)(Object)this, ModEnchantments.COOLDOWN), (PlayerEntity)(Object)this);
                int shieldCooldown = coolEnchantment > 5 ? 0 : coolEnchantment == 5 ? 10 : coolEnchantment == 4 ? 25 : coolEnchantment == 3 ? 50 : coolEnchantment == 2 ? 100 : coolEnchantment == 1 ? 150 : 200;
                int speedrunnerShieldCooldown = coolEnchantment > 5 ? 0 : coolEnchantment == 5 ? 5 : coolEnchantment == 4 ? 15 : coolEnchantment == 3 ? 25 : coolEnchantment == 2 ? 75 : coolEnchantment == 1 ? 150 : 180;
                this.getItemCooldownManager().set(Items.SHIELD.getDefaultStack(), shieldCooldown);
                this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD.getDefaultStack(), speedrunnerShieldCooldown);
                this.clearActiveItem();
                this.getWorld().sendEntityStatus(this, (byte)30);
            }
        } else {
            int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ModUtil.enchantment((PlayerEntity)(Object)this, ModEnchantments.COOLDOWN), (PlayerEntity)(Object)this);
            int cooldown = coolEnchantment > 5 ? 0 : coolEnchantment == 5 ? 5 : coolEnchantment == 4 ? 10 : coolEnchantment == 3 ? 20 : coolEnchantment == 2 ? 40 : coolEnchantment == 1 ? 60 : 80;
            this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD.getDefaultStack(), cooldown);
        }
    }

    /**
     * Adds particles around the player if they are holding a {@code Dragon's Sword}.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void addDragonsSwordParticles(CallbackInfo ci) {
        if (this.getMainHandStack().isOf(ModItems.DRAGONS_SWORD) || this.getOffHandStack().isOf(ModItems.DRAGONS_SWORD)) {
            this.getWorld().addParticleClient(ParticleTypes.PORTAL, this.getParticleX(0.5D), this.getRandomBodyY() - 0.25D, this.getParticleZ(0.5D), (this.getWorld().random.nextDouble() - 0.5D) * 2.0D, -this.getWorld().random.nextDouble(), (this.getWorld().random.nextDouble() - 0.5D) * 2.0D);
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
        return Math.min(air + ModConstants.PLAYER_BREATH_TIME, this.getMaxAir());
    }
}