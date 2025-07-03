package net.dillon.speedrunnermod.world.structure;

import net.dillon.speedrunnermod.option.OptionValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class JunglePyramidConfig extends PillagerOutpostConfig {

    @Override
    public @NotNull OptionValue<List<Integer>> custom() {
        return options().customStructureSpawnRates.junglePyramids;
    }
}