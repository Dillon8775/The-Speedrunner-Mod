package net.dillon.speedrunnermod.world.structure;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.OptionValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AncientCityConfig extends Ssr {

    @Override
    public int[] everywhere() {
        return new int[]{4, 3};
    }

    @Override
    public int[] veryCommon() {
        return new int[]{10, 5};
    }

    @Override
    public int[] common() {
        return new int[]{10, 6};
    }

    @Override
    public int[] normal() {
        return new int[]{24, 8};
    }

    @Override
    public int[] rare() {
        return new int[]{28, 12};
    }

    @Override
    public int[] veryRare() {
        return new int[]{32, 16};
    }

    @Override
    public @NotNull OptionValue<List<Integer>> custom() {
        return SpeedrunnerMod.common().customStructureSpawnRates.ancientCities;
    }
}