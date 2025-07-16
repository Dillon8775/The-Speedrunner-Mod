package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.ZoglinEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(ZoglinEntity.class)
public class ZoglinEntityMixin {

    /**
     * Modifies {@code zoglin} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeZoglinAttributes(EntityType<? extends ZoglinEntity> entityType, World world, CallbackInfo ci) {
        MobEntity dis = (MobEntity)(Object)this;
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 60.0D : 25.0D);
        ModUtil.modifyKnockbackResistance(dis, isDoomMode() ? 0.7D : 0.6D);
        ModUtil.modifyAttackKnockback(dis, isDoomMode() ? 1.2D : 0.5D);
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 8.0D : 4.0D);
    }
}