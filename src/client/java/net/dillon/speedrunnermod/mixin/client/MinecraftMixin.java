package net.dillon.speedrunnermod.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveClientChanges;

/**
 * Implements all keybindings functions into the game.
 */
@Mixin(Minecraft.class)
public class MinecraftMixin {

    /**
     * Ensures that the {@code fullbright} option is correctly initialized when launching the game.
     */
    @Inject(method = "<init>", at = @At("TAIL"))
    private void setGammaUponStart(GameConfig args, CallbackInfo ci) {
        clientOptions().client.fullBright.set(Minecraft.getInstance().options.gamma().get() >= clientOptions().client.fullBrightAmount.getCurrentValue());
        saveClientChanges();
    }
}