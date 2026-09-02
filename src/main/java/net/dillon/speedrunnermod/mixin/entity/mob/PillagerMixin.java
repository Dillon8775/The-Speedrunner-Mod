package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.dillon.speedrunnermod.option.ModCommonOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.illager.Pillager;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Pillager.class)
public class PillagerMixin {

    /**
     * Modifies {@code pillager} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changePillagerAttributes(EntityType<? extends Pillager> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModAttributeHelper.modifyMaxHealth(dis, ModCommonOptions.doomOrDefault(32.0D, 12.0D));
        ModAttributeHelper.modifyAttackDamage(dis, ModCommonOptions.doomOrDefault(8.0D, 4.0D));
        ModAttributeHelper.modifyFollowRange(dis, ModCommonOptions.doomOrDefault(32.0D, 16.0D));
    }
}