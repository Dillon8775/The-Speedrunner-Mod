package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZombifiedPiglinEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(ZombifiedPiglinEntity.class)
public class ZombifiedPiglinEntityMixin {

    /**
     * Modifies {@code zombified piglin} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(CallbackInfo ci) {
        MobEntity dis = (MobEntity)(Object)this;
        ModUtil.modifyMovementSpeed(dis, isDoomMode() ? 0.33D : 0.23D);
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 7.0D : 2.0D);
    }
}