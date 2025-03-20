package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(WitchEntity.class)
public abstract class WitchEntityMixin extends RaiderEntity {

    public WitchEntityMixin(EntityType<? extends RaiderEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((WitchEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 36;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code witch} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createWitchAttributes() {
        final double genericMaxHealth = options().main.playingMode.doom() ? 26.0D : 14.0D;
        final double genericMovementSpeed = options().main.playingMode.doom() ? 0.35D : 0.25D;
        return HostileEntity.createHostileAttributes().add(EntityAttributes.MAX_HEALTH, genericMaxHealth).add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed);
    }
}