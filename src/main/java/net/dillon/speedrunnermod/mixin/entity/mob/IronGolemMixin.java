package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.dillon.speedrunnermod.option.ModCommonOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.animal.golem.IronGolem;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(IronGolem.class)
public class IronGolemMixin {

    /**
     * Modifies {@code golem} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeIronGolemAttributes(EntityType<? extends IronGolem> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModAttributeHelper.modifyMaxHealth(dis, ModCommonOptions.doomOrDefault(100.0D, 50.0D));
        ModAttributeHelper.modifyMovementSpeed(dis, ModCommonOptions.doomOrDefault(0.3D, 0.25D));
        ModAttributeHelper.modifyKnockbackResistance(dis, ModCommonOptions.doomOrDefault(0.7D, 0.5D));
        ModAttributeHelper.modifyAttackDamage(dis, ModCommonOptions.doomOrDefault(20.0D, 7.0D));
    }
}