package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Endermite;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(Endermite.class)
public class EndermiteMixin {

    /**
     * Modifies {@code endermite} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeEndermiteAttributes(EntityType<? extends Endermite> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModAttributeHelper.modifyMaxHealth(dis, isDoomMode() ? 8.0D : 4.0D);
        ModAttributeHelper.modifyMovementSpeed(dis, isDoomMode() ? 0.25D : 0.15D);
        ModAttributeHelper.modifyAttackDamage(dis, isDoomMode() ? 2.0D : 0.01D);
    }
}