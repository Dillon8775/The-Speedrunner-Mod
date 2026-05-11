package net.dillon.speedrunnermod.client.mixin.screen;

import net.minecraft.client.gui.screens.inventory.BrewingStandScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(BrewingStandScreen.class)
public class BrewingStandScreenMixin {

    /**
     * Modifies the total brewing time, so it displays correctly.
     */
    @ModifyConstant(method = "extractBackground", constant = @Constant(floatValue = 400.0F))
    private static float changeBrewDisplayTime(float original) {
        return options().main.fasterBrewing.getCurrentValue() ? 100.0F : original;
    }
}