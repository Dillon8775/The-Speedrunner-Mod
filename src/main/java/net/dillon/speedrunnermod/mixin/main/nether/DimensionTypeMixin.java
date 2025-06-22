package net.dillon.speedrunnermod.mixin.main.nether;

import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(DimensionType.class)
public class DimensionTypeMixin {
    @Shadow @Final
    private boolean ultrawarm;

    /**
     * Allows water to be placed in the nether if the {@code allow water in nether} option is on.
     */
    @Inject(method = "ultrawarm", at = @At("RETURN"), cancellable = true)
    private void allowWaterInNether(CallbackInfoReturnable<Boolean> cir) {
        if (options().main.netherWater && this.ultrawarm) {
            cir.setReturnValue(false);
        }
    }
}