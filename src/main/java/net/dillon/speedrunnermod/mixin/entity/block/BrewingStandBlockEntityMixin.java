package net.dillon.speedrunnermod.mixin.entity.block;

import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

@Mixin(BrewingStandBlockEntity.class)
public class BrewingStandBlockEntityMixin {

    /**
     * Makes potions brew faster.
     */
    @ModifyConstant(method = "serverTick", constant = @Constant(floatValue = 400.0F))
    private static float changeBrewTime(float original) {
        return common().general.fasterBrewing.getCurrentValue() ? 100.0F : original;
    }
}