package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MagmaCubeEntity;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MagmaCubeEntity.class)
public class MagmaCubeEntityMixin extends SlimeEntity {

    public MagmaCubeEntityMixin(EntityType<? extends SlimeEntity> entityType, World world) {
        super(entityType, world);
    }

    /**
     * @author Dillon8775
     * @reason Increases the time it takes for magma cubes to make a full jump to attack a player.
     */
    @Overwrite
    public int getTicksUntilNextJump() {
        return ModUtil.getSlimeJumpTime();
    }

    /**
     * @author Dillon8775
     * @reason Decreases the amount of damage that magma cubes do.
     */
    @Overwrite
    public float getDamageAmount() {
        return (float)this.getAttributeValue(EntityAttributes.ATTACK_DAMAGE) * ModUtil.getSlimeDamageMultiplier();
    }
}