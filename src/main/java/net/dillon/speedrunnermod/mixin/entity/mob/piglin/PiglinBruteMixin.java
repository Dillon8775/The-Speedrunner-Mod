package net.dillon.speedrunnermod.mixin.entity.mob.piglin;

import net.dillon.speedrunnermod.helper.ModAttributeHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.monster.piglin.PiglinBrute;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(PiglinBrute.class)
public class PiglinBruteMixin {

    /**
     * Modifies the {@code maximum health} for the piglin brute.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void changePiglinBruteAttributes(EntityType<? extends PiglinBrute> entityType, Level world, CallbackInfo ci) {
        Mob dis = (Mob)(Object)this;
        ModAttributeHelper.modifyMaxHealth(dis, isDoomMode() ? 25.0D : 50.0D);
    }
}