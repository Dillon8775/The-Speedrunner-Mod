package net.dillon.speedrunnermod.mixin.entity.block;

import net.minecraft.world.level.block.entity.BrewingStandBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(BrewingStandBlockEntity.class)
public class BrewingStandBlockEntityMixin {

    /**
     * Makes potions brew faster.
     */
    @ModifyConstant(method = "serverTick", constant = @Constant(intValue = 400))
    private static int changeBrewTime(int original) {
        return options().general.fasterBrewing.getCurrentValue() ? 100 : original;
    }
}