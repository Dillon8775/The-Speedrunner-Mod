package net.dillon.speedrunnermod.mixin.main.entity;

import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(Zoglin.class)
public class ZoglinMixin {

    /**
     * Modifies {@code zoglin} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeZoglinAttributes(EntityType<? extends Zoglin> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModUtil.modifyMaxHealth(dis, isDoomMode() ? 60.0D : 25.0D);
        ModUtil.modifyKnockbackResistance(dis, isDoomMode() ? 0.7D : 0.6D);
        ModUtil.modifyAttackKnockback(dis, isDoomMode() ? 1.2D : 0.5D);
        ModUtil.modifyAttackDamage(dis, isDoomMode() ? 8.0D : 4.0D);
    }
}