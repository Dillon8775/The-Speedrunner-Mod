package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.MagmaCube;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(MagmaCube.class)
public class MagmaCubeMixin extends Slime {

    public MagmaCubeMixin(EntityType<? extends Slime> entityType, Level world) {
        super(entityType, world);
    }

    /**
     * @author Dillon8775
     * @reason Increases the time it takes for magma cubes to make a full jump to attack a player.
     */
    @Overwrite
    public int getJumpDelay() {
        return ModUtil.getSlimeJumpTime();
    }

    /**
     * @author Dillon8775
     * @reason Decreases the amount of damage that magma cubes do.
     */
    @Overwrite
    public float getAttackDamage() {
        return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE) * ModUtil.getSlimeDamageMultiplier();
    }
}