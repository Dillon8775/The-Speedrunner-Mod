package net.dillon.speedrunnermod.mixin.client;

import com.mojang.serialization.Codec;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.dillon.speedrunnermod.util.IncreasedBrightnessSliderCallbacks;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;
import net.minecraft.text.TextContent;
import net.minecraft.text.TranslatableTextContent;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Increases the maximum brightness for the speedrunner mod, allowing for fullbright.
 */
@Author(Authors.ADAMVIOLA)
@Mixin(SimpleOption.class)
public class IncreasedBrightness {
    @Shadow @Final
    Text text;
    @Shadow @Final @Mutable
    Function<Double, Text> textGetter;
    @Shadow @Final @Mutable
    private SimpleOption.Callbacks<Double> callbacks;
    @Shadow @Final @Mutable
    private Codec<Double> codec;
    @Shadow @Final @Mutable
    private Consumer<Double> changeCallback;

    @Inject(at = @At("RETURN"), method = "<init>*")
    private void init(CallbackInfo info) {
        TextContent content = this.text.getContent();
        if (!(content instanceof TranslatableTextContent))
            return;

        String key = ((TranslatableTextContent) content).getKey();
        if (!key.equals("options.gamma"))
            return;

        this.textGetter = this::textGetter;
        this.callbacks = IncreasedBrightnessSliderCallbacks.INSTANCE;
        this.codec = this.callbacks.codec();
        this.changeCallback = this::changeCallback;
    }

    /**
     * Gets the text to display at certain gamma values.
     */
    @Unique
    private Text textGetter(Double gamma) {
        long brightness = Math.round(gamma * 100);
        return Text.translatable("options.gamma").append(": ").append(brightness == 0 ? Text.translatable("options.gamma.min") : brightness == 100 ? Text.translatable("options.gamma.max") : Text.literal(String.valueOf(brightness)));
    }

    /**
     * Used to switch the default callback for the brightnss slider to the speedrunner mod.
     */
    @Unique
    private void changeCallback(Double gamma) {
        MinecraftClient.getInstance().options.getGamma().setValue(gamma);
    }
}