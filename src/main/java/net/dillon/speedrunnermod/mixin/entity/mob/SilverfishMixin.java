package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.dillon.speedrunnermod.option.ModCommonOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Silverfish.class)
public class SilverfishMixin {

    /**
     * Modifies {@code silverfish} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeSilverfishAttributes(EntityType<? extends Silverfish> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModAttributeHelper.modifyMaxHealth(dis, ModCommonOptions.doomOrDefault(8.0D, 4.0D));
        ModAttributeHelper.modifyMovementSpeed(dis, ModCommonOptions.doomOrDefault(0.25D, 0.15D));
        ModAttributeHelper.modifyAttackDamage(dis, ModCommonOptions.doomOrDefault(2.0D, 0.01D));
    }
}