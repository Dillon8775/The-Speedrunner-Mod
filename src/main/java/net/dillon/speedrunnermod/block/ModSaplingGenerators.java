package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.world.feature.ModConfiguredFeatures;
import net.dillon.speedrunnermod.world.feature.WastelandConfiguredFeatures;
import net.minecraft.block.SaplingGenerator;

import java.util.Optional;

/**
 * All {@code Speedrunner Mod} sapling generators (configures tree growing).
 */
public class ModSaplingGenerators {
    public static final SaplingGenerator SPEEDRUNNER = new SaplingGenerator("speedrunnermod:speedrunner", 0.1F, Optional.empty(), Optional.empty(), Optional.of(WastelandConfiguredFeatures.DEFAULT_SPEEDRUNNER), Optional.of(WastelandConfiguredFeatures.FANCY_SPEEDRUNNER), Optional.empty(), Optional.empty());
    public static final SaplingGenerator DEAD_SPEEDRUNNER = new SaplingGenerator("speedrunnermod:dead_speedrunner", 0.1F, Optional.empty(), Optional.empty(), Optional.of(ModConfiguredFeatures.DEAD_SPEEDRUNNER), Optional.empty(), Optional.empty(), Optional.empty());
}