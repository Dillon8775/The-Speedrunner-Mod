package net.dillon.speedrunnermod.mixin.client.screen;

import net.dillon.speedrunnermod.author.Author;
import net.dillon.speedrunnermod.author.Authors;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

@Author(Authors.KURAION)
@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {

    /**
     * Removes the {@code too expensive} level cap on the anvil screen (client-side).
     */
    @ModifyConstant(method = "extractLabels", constant = @Constant(intValue = 40))
    private int removeTooExpensiveText(int i) {
        if (common().general().betterAnvil) {
            return Integer.MAX_VALUE;
        } else {
            return 40;
        }
    }
}