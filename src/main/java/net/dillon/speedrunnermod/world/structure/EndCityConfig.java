package net.dillon.speedrunnermod.world.structure;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.OptionValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class EndCityConfig extends Ssr {

    @Override
    public int[] everywhere() {
        return new int[]{4, 2};
    }

    @Override
    public int[] veryCommon() {
        return new int[]{5, 4};
    }

    @Override
    public int[] common() {
        return new int[]{7, 6};
    }

    @Override
    public int[] normal() {
        return new int[]{15, 10};
    }

    @Override
    public int[] rare() {
        return new int[]{25, 16};
    }

    @Override
    public int[] veryRare() {
        return new int[]{30, 18};
    }

    @Override
    public @NotNull OptionValue<List<Integer>> custom() {
        return SpeedrunnerMod.common().customStructureSpawnRates.endCities;
    }
}