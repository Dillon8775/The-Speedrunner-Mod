package net.dillon.speedrunnermod.mixin.main.entity.player;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.ModUtil;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.GiantEntity;
import net.minecraft.entity.player.ItemCooldownManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity {
    @Shadow
    public abstract ItemCooldownManager getItemCooldownManager();
    @Shadow
    public abstract ItemStack getEquippedStack(EquipmentSlot slot);
    @Shadow
    public abstract boolean damage(ServerWorld world, DamageSource source, float amount);

    @Shadow @Final private PlayerInventory inventory;

    public PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Makes sure that the speedrunner shield {@code gets disabled} when hit with an {@code axe}, and also lowers this cooldown from the {@code dash enchantment}.
     */
    @Inject(method = "disableShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/player/PlayerEntity;getItemCooldownManager()Lnet/minecraft/entity/player/ItemCooldownManager;"))
    private void disableShield(CallbackInfo ci) {
        Optional<RegistryEntry.Reference<Enchantment>> optional = this.getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getOptional(ModEnchantments.COOLDOWN);
        RegistryEntry<Enchantment> registryEntry = optional.get();
        int coolEnchantment = EnchantmentHelper.getEquipmentLevel(registryEntry, (PlayerEntity)(Object)this);
        int cooldown = coolEnchantment > 5 ? 0 : coolEnchantment == 5 ? 5 : coolEnchantment == 4 ? 10 : coolEnchantment == 3 ? 20 : coolEnchantment == 2 ? 40 : coolEnchantment == 1 ? 60 : 80;
        this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD.getDefaultStack(), cooldown);
    }

    /**
     * Allows speedrunner shields to {@code get disabled}, as a shield should, when hit by an {@code axe.}
     */
    @Redirect(method = "damageShield", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;isOf(Lnet/minecraft/item/Item;)Z"))
    private boolean damageShield(ItemStack stack, Item item) {
        return stack.isIn(ConventionalItemTags.SHIELD_TOOLS);
    }

    /**
     * Makes the Giant disable players' shields.
     */
    @Inject(method = "takeShieldHit", at = @At("TAIL"))
    private void takeShieldHit(LivingEntity attacker, CallbackInfo ci) {
        if (options().main.playingMode.doom()) {
            if (attacker instanceof GiantEntity) {
                int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((PlayerEntity)(Object)this, ModEnchantments.COOLDOWN), (PlayerEntity)(Object)this);
                int shieldCooldown = coolEnchantment > 5 ? 0 : coolEnchantment == 5 ? 10 : coolEnchantment == 4 ? 25 : coolEnchantment == 3 ? 50 : coolEnchantment == 2 ? 100 : coolEnchantment == 1 ? 150 : 200;
                int speedrunnerShieldCooldown = coolEnchantment > 5 ? 0 : coolEnchantment == 5 ? 5 : coolEnchantment == 4 ? 15 : coolEnchantment == 3 ? 25 : coolEnchantment == 2 ? 75 : coolEnchantment == 1 ? 150 : 180;
                this.getItemCooldownManager().set(Items.SHIELD.getDefaultStack(), shieldCooldown);
                this.getItemCooldownManager().set(ModItems.SPEEDRUNNER_SHIELD.getDefaultStack(), speedrunnerShieldCooldown);
                this.clearActiveItem();
                this.getWorld().sendEntityStatus(this, (byte)30);
            }
        }
    }

    /**
     * Adds particles around the player if they are holding a {@code Dragon's Sword}.
     */
    @Inject(method = "tick", at = @At("TAIL"))
    private void addDragonsSwordParticles(CallbackInfo ci) {
        if (this.getMainHandStack().isOf(ModItems.DRAGONS_SWORD) || this.getOffHandStack().isOf(ModItems.DRAGONS_SWORD)) {
            this.getWorld().addParticle(ParticleTypes.PORTAL, this.getParticleX(0.5D), this.getRandomBodyY() - 0.25D, this.getParticleZ(0.5D), (this.getWorld().random.nextDouble() - 0.5D) * 2.0D, -this.getWorld().random.nextDouble(), (this.getWorld().random.nextDouble() - 0.5D) * 2.0D);
        }
    }

    /**
     * Allows player to hold their breath for a longer period of time while underwater.
     */
    @Override
    protected int getNextAirUnderwater(int air) {
        if (options().advanced.higherBreathTime && this.random.nextInt(4) > 0) {
            return air;
        }

        return super.getNextAirUnderwater(air);
    }

    /**
     * Allows the use of totems in the void.
     */
    @Override
    public void attemptTickInVoid() {
        if (this.inventory.contains(ModItems.SPEEDRUNNERS_TOTEM.getDefaultStack()) || this.getMainHandStack().isOf(Items.TOTEM_OF_UNDYING) || this.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING)) {
            if (this.getY() < (double)(this.getWorld().getBottomY() - 64)) {
                int y = this.getWorld().getTopY(Heightmap.Type.MOTION_BLOCKING, 0, 0);
                BlockPos pos = new BlockPos(0, y - 1, 0);
                if (this.getWorld().getBlockState(pos).isOf(Blocks.WATER)) {
                    this.getWorld().setBlockState(pos, Blocks.FROSTED_ICE.getDefaultState());
                } else if (this.getWorld().getBlockState(pos).isOf(Blocks.LAVA)) {
                    this.getWorld().setBlockState(pos, Blocks.LAVA.getDefaultState());
                }
                boolean isAir = this.getWorld().getBlockState(pos.up()).isAir() && this.getWorld().getBlockState(pos.up(1)).isAir();
                if (!isAir) {
                    for (int i = 1; i < 3; i++) {
                        this.getWorld().setBlockState(pos.up(i), Blocks.AIR.getDefaultState(), 3);
                    }
                }

                this.teleport(0.5, y, 0.5, true);
                this.serverDamage(this.getDamageSources().generic(), Integer.MAX_VALUE);
                this.getWorld().playSound(null, this.getX(), this.getEyeY(), this.getZ(), SoundEvents.ENTITY_ENDERMAN_TELEPORT, SoundCategory.PLAYERS, 10.0F, 1.0F);
                ModCriterions.USED_ITEM.trigger((ServerPlayerEntity)(Object)this, ModItems.SPEEDRUNNERS_TOTEM.getDefaultStack());
            }
        } else {
            super.attemptTickInVoid();
        }
    }

    /**
     * Allows players catch their breath faster after coming out of the water.
     */
    @Override
    public int getNextAirOnLand(int air) {
        final int breathTime = SpeedrunnerMod.getPlayerBreathTime();
        return Math.min(air + breathTime, this.getMaxAir());
    }
}