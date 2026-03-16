package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Witch.class)
public class WitchMixin {

    /**
     * Modifies {@code witch} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeWitchAttributes(EntityType<? extends Witch> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 26.0D : 14.0D);
        ModUtil.modifyMovementSpeed(dis, isDoomMode() ? 0.35D : 0.25D);
    }
}