package net.dillon.speedrunnermod.world.structure;

import net.dillon.speedrunnermod.option.OptionValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class NetherComplexesConfig extends Ssr {

    @Override
    public int[] everywhere() {
        return new int[]{5, 3};
    }

    @Override
    public int[] veryCommon() {
        return new int[]{7, 6};
    }

    @Override
    public int[] common() {
        return new int[]{10, 8};
    }

    @Override
    public int[] normal() {
        return new int[]{17, 10};
    }

    @Override
    public int[] rare() {
        return new int[]{40, 10};
    }

    @Override
    public int[] veryRare() {
        return new int[]{50, 14};
    }

    @Override
    public @NotNull OptionValue<List<Integer>> custom() {
        return options().customStructureSpawnRates.netherComplexes;
    }
}