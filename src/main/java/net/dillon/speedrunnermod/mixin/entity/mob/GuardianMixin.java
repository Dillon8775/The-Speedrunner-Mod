package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Guardian;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Guardian.class)
public class GuardianMixin {

    /**
     * Modifies {@code guardian} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeGuardianAttributes(EntityType<? extends Guardian> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 7.0D : 3.0D);
        ModUtil.modifyFollowRange(dis, isDoomMode() ? 24.0D : 8.0D);
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 35.0D : 15.0D);
    }
}