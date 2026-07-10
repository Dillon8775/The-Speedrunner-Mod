package net.dillon.speedrunnermod.world.structure;

import net.dillon.speedrunnermod.option.OptionValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class IglooConfig extends Ssr {

    @Override
    public int[] everywhere() {
        return new int[]{3, 2};
    }

    @Override
    public int[] veryCommon() {
        return new int[]{7, 6};
    }

    @Override
    public int[] common() {
        return new int[]{9, 6};
    }

    @Override
    public int[] normal() {
        return new int[]{18, 6};
    }

    @Override
    public int[] rare() {
        return new int[]{32, 10};
    }

    @Override
    public int[] veryRare() {
        return new int[]{40, 12};
    }

    @Override
    public @NotNull OptionValue<List<Integer>> custom() {
        return options().customStructureSpawnRates.igloos;
    }
}