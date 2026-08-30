package net.dillon.speedrunnermod.world.structure;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.OptionValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VillageConfig extends Ssr {

    @Override
    public int[] everywhere() {
        return new int[]{4, 2};
    }

    @Override
    public int[] veryCommon() {
        return new int[]{10, 5};
    }

    @Override
    public int[] common() {
        return new int[]{16, 9};
    }

    @Override
    public int[] normal() {
        return new int[]{20, 8};
    }

    @Override
    public int[] rare() {
        return new int[]{42, 10};
    }

    @Override
    public int[] veryRare() {
        return new int[]{52, 16};
    }

    @Override
    public @NotNull OptionValue<List<Integer>> custom() {
        return SpeedrunnerMod.common().customStructureSpawnRates.villages;
    }
}