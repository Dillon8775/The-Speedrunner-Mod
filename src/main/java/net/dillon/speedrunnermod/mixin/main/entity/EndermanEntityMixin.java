package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Optional;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(EndermanEntity.class)
public class EndermanEntityMixin extends HostileEntity {

    public EndermanEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Modifies {@code enderman} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends EndermanEntity> entityType, World world, CallbackInfo ci) {
        ModUtil.modifyMaxHealth(this, isDoomMode() ? 60.0D : 25.0D);
        ModUtil.modifyAttackDamage(this, isDoomMode() ? 8.0D : 4.0D);
        ModUtil.modifyFollowRange(this, isDoomMode() ? 64.0D : 12.0D);
    }

    /**
     * Makes enderman drop {@code ender matter} based on the conditions for it.
     */
    @Override
    public void onDeath(DamageSource damageSource) {
        EndermanEntity endermanEntity = (EndermanEntity)(Object)this;
        LivingEntity attacker = endermanEntity.getAttacker();
        if (damageSource.isOf(DamageTypes.PLAYER_ATTACK) && attacker != null) {
            Optional<RegistryEntry.Reference<Enchantment>> optional = this.getWorld().getRegistryManager().getOrThrow(RegistryKeys.ENCHANTMENT).getOptional(Enchantments.LOOTING);
            RegistryEntry<Enchantment> registryEntry = optional.get();
            int looting = EnchantmentHelper.getEquipmentLevel(registryEntry, attacker);
            boolean attackerHasSpeedrunnerSword = attacker.getMainHandStack().isIn(ModItemTags.SPEEDRUNNER_SWORDS);
            double chance = looting >= 3 ? 0.10 : looting == 2 ? 0.075 : looting == 1 ? 0.06 : 0.05;
            if (attackerHasSpeedrunnerSword) {
                chance += 0.02;
            }
            if (endermanEntity.getRandom().nextDouble() < chance) {
                endermanEntity.dropItem(endermanEntity.getServer().getWorld(endermanEntity.getEntityWorld().getRegistryKey()), ModItems.ENDER_MATTER);
            }
        }
        super.onDeath(damageSource);
    }
}