package net.dillon.speedrunnermod.mixin.block;

import com.llamalad7.mixinextras.sugar.Local;
import net.dillon.speedrunnermod.helper.ModConstants;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BedBlock.class)
public class BedBlockMixin {

    /**
     * Cancels the original explosion method, and creates a new one, increasing the explosion power with beds in the end.
     */
    @ModifyArg(method = "destroyOnUse", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;explode(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/damagesource/DamageSource;Lnet/minecraft/world/level/ExplosionDamageCalculator;Lnet/minecraft/world/phys/Vec3;FZLnet/minecraft/world/level/Level$ExplosionInteraction;)V"), index = 4)
    private float changeBedExplosionPower(float r, @Local Level level) {
        return ModConstants.getBedBlockExplosionPower(level);
    }
}