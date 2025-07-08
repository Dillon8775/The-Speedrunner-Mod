package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(WitchEntity.class)
public class WitchEntityMixin {

    /**
     * Modifies {@code witch} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void init(EntityType<? extends WitchEntity> entityType, World world, CallbackInfo ci) {
        MobEntity dis = (MobEntity)(Object)this;
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 26.0D : 14.0D);
        ModUtil.modifyMovementSpeed(dis, isDoomMode() ? 0.35D : 0.25D);
    }
}