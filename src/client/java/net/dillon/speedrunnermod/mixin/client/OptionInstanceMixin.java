package net.dillon.speedrunnermod.mixin.client;

import com.mojang.serialization.Codec;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.util.IncreasedBrightnessSliderCallbacks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.contents.TranslatableContents;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Function;

/**
 * Increases the maximum brightness for the speedrunner mod, allowing for fullbright.
 */
@Author(Authors.ADAMVIOLA)
@Mixin(OptionInstance.class)
public class OptionInstanceMixin {
    @Shadow @Final
    Component caption;
    @Shadow @Final @Mutable
    Function<Double, Component> toString;
    @Shadow @Final @Mutable
    private OptionInstance.ValueSet<Double> values;
    @Shadow @Final @Mutable
    private Codec<Double> codec;
    @Shadow @Final @Mutable
    private OptionInstance.ValueUpdateListener<? super Double> onValueUpdate;

    @Inject(at = @At("RETURN"), method = "<init>*")
    private void init(CallbackInfo info) {
        ComponentContents content = this.caption.getContents();
        if (!(content instanceof TranslatableContents)) {
            return;
        }

        String key = ((TranslatableContents) content).getKey();
        if (!key.equals("options.gamma")) {
            return;
        }

        this.toString = this::textGetter;
        this.values = IncreasedBrightnessSliderCallbacks.INSTANCE;
        this.codec = this.values.codec();
        this.onValueUpdate = this::changeCallback;
    }

    /**
     * Gets the text to display at certain gamma values.
     */
    @Unique
    private Component textGetter(Double gamma) {
        long brightness = Math.round(gamma * 100);
        return Component.translatable("options.gamma").append(": ").append(brightness == 0 ? Component.translatable("options.gamma.min") : brightness == 100 ? Component.translatable("options.gamma.max") : Component.literal(String.valueOf(brightness)));
    }

    /**
     * Used to switch the default callback for the brightnss slider to the speedrunner mod.
     */
    @Unique
    private void changeCallback(Double gamma) {
        Minecraft.getInstance().options.gamma().set(gamma);
    }
}