package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.ItemUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(VexEntity.class)
public class VexEntityMixin extends HostileEntity {

    public VexEntityMixin(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ItemUtil.entityEnchantment((VexEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 36;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * @author Dillon8775
     * @reason Modifies {@code vex} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createVexAttributes() {
        final double genericMaxHealth = options().main.playingMode.doom() ? 7.0D : 14.0D;
        final double genericAttackDamage = options().main.playingMode.doom() ? 3.0D : 4.0D;
        return HostileEntity.createHostileAttributes()
                .add(EntityAttributes.MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage);
    }

    /**
     * @author Dillon8775
     * @reason Disables vexes from {@code noClipping} on {@code doom mode.}
     */
    @Redirect(method = "tick", at = @At(value = "FIELD", target = "Lnet/minecraft/entity/mob/VexEntity;noClip:Z"))
    private void setNoClip(VexEntity vex, boolean value) {
        vex.noClip = !options().main.playingMode.doom();
    }

    /**
     * Increases the damage dealt to themselves when decaying.
     */
    @ModifyArg(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/mob/VexEntity;serverDamage(Lnet/minecraft/entity/damage/DamageSource;F)V"), index = 1)
    private float amount(float amount) {
        return SpeedrunnerMod.getVexDecayDamageMultiplier();
    }

    /**
     * Makes vexes take fall damage from doom mode.
     */
    @Override
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource source) {
        return !options().main.playingMode.doom();
    }
}