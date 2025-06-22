package net.dillon.speedrunnermod.client.util;

import com.mojang.datafixers.util.Either;
import com.mojang.serialization.Codec;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.client.option.SimpleOption;

import java.util.Optional;

/**
 * Used to increase the maximum brightness for the Speedrunner Mod, and allowing {@code fullbright.}
 */
@Author(Authors.ADAMVIOLA)
public enum IncreasedBrightnessSliderCallbacks implements SimpleOption.SliderCallbacks<Double> {
    INSTANCE;

    @Override
    public Optional<Double> validate(Double double_) {
        return double_ >= SpeedrunnerModClient.getMinBrightness() && double_ <= SpeedrunnerModClient.getMaxBrightness() ? Optional.of(double_) : Optional.empty();
    }

    @Override
    public double toSliderProgress(Double double_) {
        double range = SpeedrunnerModClient.getMaxBrightness() - SpeedrunnerModClient.getMinBrightness();
        double offset = SpeedrunnerModClient.getMinBrightness();
        return (double_ - offset) / range;
    }

    @Override
    public Double toValue(double d) {
        double range = SpeedrunnerModClient.getMaxBrightness() - SpeedrunnerModClient.getMinBrightness();
        double offset = SpeedrunnerModClient.getMinBrightness();
        return d * range + offset;
    }

    @Override
    public Codec<Double> codec() {
        return Codec.either(Codec.doubleRange(SpeedrunnerModClient.getMinBrightness(), SpeedrunnerModClient.getMaxBrightness()), Codec.BOOL).xmap(either -> either.map(value -> value, value -> value ? 1.0 : 0.0), Either::left);
    }
}