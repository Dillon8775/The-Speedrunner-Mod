package net.dillon.speedrunnermod.mixin.main.entity;

import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(ZombifiedPiglinEntity.class)
public class ZombifiedPiglinEntityMixin {

    /**
     * @author Dillon8775
     * @reason Modifies {@code zombified piglin} attributes.
     */
    @Overwrite
    public static DefaultAttributeContainer.Builder createZombifiedPiglinAttributes() {
        final double zombieSpawnReinforcements = 0.0D;
        final double genericMovementSpeed = options().main.playingMode.doom() ? 0.33000000427232513D : 0.23000000427232513D;
        final double genericAttackDamage = options().main.playingMode.doom() ? 7.0D : 2.0D;
        return ZombieEntity.createZombieAttributes()
                .add(EntityAttributes.SPAWN_REINFORCEMENTS, zombieSpawnReinforcements)
                .add(EntityAttributes.MOVEMENT_SPEED, genericMovementSpeed)
                .add(EntityAttributes.ATTACK_DAMAGE, genericAttackDamage);
    }
}