package net.dillon.speedrunnermod.mixin.entity.mob;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.cubemob.AbstractCubeMob;
import net.minecraft.world.entity.monster.cubemob.MagmaCube;
import net.minecraft.world.entity.monster.cubemob.Slime;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.option.ModCommonOptions.doomOrDefault;

@Mixin(AbstractCubeMob.class)
public abstract class AbstractCubeMobMixin extends AgeableMob {

    public AbstractCubeMobMixin(EntityType<? extends AgeableMob> type, Level level) {
        super(type, level);
    }

    /**
     * Increases the time it takes for slimes to make a full jump.
     */
    @Inject(method = "getJumpDelay", at = @At("HEAD"), cancellable = true)
    private void modifyJumpTime(CallbackInfoReturnable<Integer> cir) {
        if (this.isHostileCube()) {
            cir.setReturnValue(doomOrDefault(20, 100));
        }
    }

    /**
     * Decreases the amount of damage that magma cubes do.
     */
    @Inject(method = "getAttackDamage", at = @At("HEAD"), cancellable = true)
    private void modifyAttackDamage(CallbackInfoReturnable<Float> cir) {
        if (this.isHostileCube()) {
            cir.setReturnValue((float)this.getAttributeValue(Attributes.ATTACK_DAMAGE) * doomOrDefault(2.2F, 1.5F));
        }
    }

    /**
     * @return if this entity is a hostile cube mob.
     */
    @Unique
    private boolean isHostileCube() {
        AbstractCubeMob cube = (AbstractCubeMob)(Object)this;
        return cube instanceof Slime || cube instanceof MagmaCube;
    }
}