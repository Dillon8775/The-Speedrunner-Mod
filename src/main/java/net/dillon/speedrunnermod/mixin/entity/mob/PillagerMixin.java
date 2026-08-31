package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.illager.Pillager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(Pillager.class)
public class PillagerMixin {

    /**
     * Modifies {@code pillager} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changePillagerAttributes(EntityType<? extends Pillager> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModAttributeHelper.modifyMaxHealth(dis, isDoomMode() ? 32.0D : 12.0D);
        ModAttributeHelper.modifyAttackDamage(dis, isDoomMode() ? 8.0D : 4.0D);
        ModAttributeHelper.modifyFollowRange(dis, isDoomMode() ? 32.0D : 16.0D);
    }
}