package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.BreezeEntity;
import net.minecraft.entity.mob.MobEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(BreezeEntity.class)
public class BreezeEntityMixin {

    /**
     * @author Dillon8775
     * @reason Modifies {@code breeze} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createBreezeAttributes() {
        final double genericMovementSpeed = options().main.playingMode.doom() ? 0.65D : 0.50D;
        final double genericMaxHealth = options().main.playingMode.doom() ? 35.0D : 25.0D;
        final double genericFollowRange = options().main.playingMode.doom() ? 32.0D : 16.0D;
        final double genericAttackDamage = options().main.playingMode.doom() ? 3.5D : 2.0D;
        return MobEntity.createMobAttributes().add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.MAX_HEALTH, genericMaxHealth)
                .add(EntityAttributes.FOLLOW_RANGE, genericFollowRange)
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage);
    }
}