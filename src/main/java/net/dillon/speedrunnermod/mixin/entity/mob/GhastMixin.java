package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.dillon.speedrunnermod.option.ModCommonOptions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Ghast.class)
public class GhastMixin {

    /**
     * Modifies {@code ghast} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeGhastAttributes(EntityType<? extends Ghast> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModAttributeHelper.modifyMaxHealth(dis, ModCommonOptions.doomOrDefault(20.0D, 5.0D));
        ModAttributeHelper.modifyFollowRange(dis, ModCommonOptions.doomOrDefault(100.0D, 50.0D));
    }

    /**
     * Modifies the strength of a ghast's fireball.
     */
    @Inject(method = "getExplosionPower", at = @At("RETURN"), cancellable = true)
    private void modifyExplosionPower(CallbackInfoReturnable<Integer> cir) {
        cir.setReturnValue((int)(ModConstants.getSmallFireballDamageValue()));
    }
}