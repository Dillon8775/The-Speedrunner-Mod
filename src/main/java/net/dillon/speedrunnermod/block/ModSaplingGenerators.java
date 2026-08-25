package net.dillon.speedrunnermod.block;

import net.dillon.speedrunnermod.world.feature.ModWorldFeatures;
import net.dillon.speedrunnermod.world.feature.ModWorldPlacements;
import net.dillon.speedrunnermod.world.feature.WastelandFeatures;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

/**
 * All {@code Speedrunner Mod} sapling generators (configures tree growing).
 */
public class ModSaplingGenerators {
    public static final TreeGrower SPEEDRUNNER = new TreeGrower(
            "speedrunnermod:speedrunner",
            WeightedList.of(WastelandFeatures.DEFAULT_SPEEDRUNNER),
            WeightedList.of(WastelandFeatures.FANCY_SPEEDRUNNER),
            WeightedList.of(),
            WastelandFeatures.DEFAULT_SPEEDRUNNER
    );
    public static final TreeGrower DEAD_SPEEDRUNNER = new TreeGrower(
            "speedrunnermod:dead_speedrunner",
            WeightedList.of(ModWorldFeatures.DEAD_SPEEDRUNNER),
            WeightedList.of(),
            WeightedList.of(),
            ModWorldFeatures.DEAD_SPEEDRUNNER
    );
}