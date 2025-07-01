package net.dillon.speedrunnermod.mixin.client.enchantment;

import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Author(Authors.KURAION)
@Environment(EnvType.CLIENT)
@Mixin(AnvilScreen.class)
public class RemoveTooExpensiveText {

    /**
     * Removes the {@code too expensive} level cap on the anvil screen (client-side).
     */
    @ModifyConstant(method = "drawForeground", constant = @Constant(intValue = 40))
    private int mixinLimitInt(int i) {
        if (options().main.betterAnvil.getCurrentValue()) {
            return Integer.MAX_VALUE;
        } else {
            return 40;
        }
    }
}