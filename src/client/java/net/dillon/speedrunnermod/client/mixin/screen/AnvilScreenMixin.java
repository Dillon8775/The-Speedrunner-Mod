package net.dillon.speedrunnermod.client.mixin.screen;

import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Author(Authors.KURAION)
@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {

    /**
     * Removes the {@code too expensive} level cap on the anvil screen (client-side).
     */
    @ModifyConstant(method = "extractLabels", constant = @Constant(intValue = 40))
    private int removeTooExpensiveText(int i) {
        if (options().main.betterAnvil.getCurrentValue()) {
            return Integer.MAX_VALUE;
        } else {
            return 40;
        }
    }
}