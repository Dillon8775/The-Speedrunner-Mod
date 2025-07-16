package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.GhastEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(GhastEntity.class)
public class GhastEntityMixin {

    /**
     * Modifies {@code ghast} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeGhastAttributes(EntityType<? extends GhastEntity> entityType, World world, CallbackInfo ci) {
        MobEntity dis = (MobEntity)(Object)this;
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 20.0D : 5.0D);
        ModUtil.modifyFollowRange(dis, isDoomMode() ? 100.0D : 50.0D);
    }
}