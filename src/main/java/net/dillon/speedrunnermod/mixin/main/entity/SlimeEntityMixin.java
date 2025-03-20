package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SlimeEntity.class)
public class SlimeEntityMixin extends MobEntity {

    public SlimeEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * Increases the experience dropped upon death.
     */
    @Override
    public int getExperienceToDrop(ServerWorld world) {
        if (this.attackingPlayer != null) {
            this.experiencePoints = 5 + EnchantmentHelper.getEquipmentLevel(ModUtil.entityEnchantment((SlimeEntity)(Object)this, Enchantments.LOOTING), this.attackingPlayer) * 36;
        }
        return super.getExperienceToDrop(world);
    }

    /**
     * @author Dillon8775
     * @reason Increases the time it takes for slimes to make a full jump.
     */
    @Overwrite
    public int getTicksUntilNextJump() {
        return SpeedrunnerMod.getSlimeJumpTime();
    }

    /**
     * @author Dillon8775
     * @reason Decreases the amount of damage that magma cubes do.
     */
    @Overwrite
    public float getDamageAmount() {
        return (float)this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE) * SpeedrunnerMod.getSlimeDamageMultiplier();
    }
}