package net.dillon.speedrunnermod.mixin.entity.mob;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.Witch;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModCommonOptions.doomOrDefault;

@Mixin(Witch.class)
public class WitchMixin {

    /**
     * Modifies {@code witch} attributes.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changeWitchAttributes(EntityType<? extends Witch> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModAttributeHelper.modifyMaxHealth(dis, doomOrDefault(26.0D, 14.0D));
        ModAttributeHelper.modifyMovementSpeed(dis, doomOrDefault(0.35D, 0.25D));
    }
}