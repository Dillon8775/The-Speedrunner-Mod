package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(IronGolem.class)
public class IronGolemMixin {

    /**
     * Modifies {@code golem} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeIronGolemAttributes(EntityType<? extends IronGolem> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 100.0D : 50.0D);
        ModUtil.modifyMovementSpeed(dis, isDoomMode() ? 0.3D : 0.25D);
        ModUtil.modifyKnockbackResistance(dis, isDoomMode() ? 0.7D : 0.5D);
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 20.0D : 7.0D);
    }
}