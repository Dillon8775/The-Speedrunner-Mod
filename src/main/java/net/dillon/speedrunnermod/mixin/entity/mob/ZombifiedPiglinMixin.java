package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.zombie.ZombifiedPiglin;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(ZombifiedPiglin.class)
public class ZombifiedPiglinMixin {

    /**
     * Modifies {@code zombified piglin} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeZombifiedPiglinAttributes(CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModUtil.modifyMovementSpeed(dis, isDoomMode() ? 0.33D : 0.23D);
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 7.0D : 2.0D);
    }
}