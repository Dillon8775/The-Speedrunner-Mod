package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.world.feature.ModConfiguredFeatures;
import net.dillon.speedrunnermod.world.feature.WastelandConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

/**
 * All {@code Speedrunner Mod} sapling generators (configures tree growing).
 */
public class ModSaplingGenerators {
    public static final TreeGrower SPEEDRUNNER = new TreeGrower("speedrunnermod:speedrunner", 0.1F, Optional.empty(), Optional.empty(), Optional.of(WastelandConfiguredFeatures.DEFAULT_SPEEDRUNNER), Optional.of(WastelandConfiguredFeatures.FANCY_SPEEDRUNNER), Optional.empty(), Optional.empty());
    public static final TreeGrower DEAD_SPEEDRUNNER = new TreeGrower("speedrunnermod:dead_speedrunner", 0.1F, Optional.empty(), Optional.empty(), Optional.of(ModConfiguredFeatures.DEAD_SPEEDRUNNER), Optional.empty(), Optional.empty(), Optional.empty());
}