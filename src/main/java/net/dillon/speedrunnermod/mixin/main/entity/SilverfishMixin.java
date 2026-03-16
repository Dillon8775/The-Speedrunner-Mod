package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Silverfish.class)
public class SilverfishMixin {

    /**
     * Modifies {@code silverfish} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeSilverfishAttributes(EntityType<? extends Silverfish> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 8.0D : 4.0D);
        ModUtil.modifyMovementSpeed(dis, isDoomMode() ? 0.25D : 0.15D);
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 2.0D : 0.01D);
    }
}