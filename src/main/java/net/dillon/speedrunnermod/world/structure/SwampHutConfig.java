package net.dillon.speedrunnermod.world.structure;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.OptionValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SwampHutConfig extends Ssr {

    @Override
    public int[] everywhere() {
        return new int[]{5, 3};
    }

    @Override
    public int[] veryCommon() {
        return new int[]{8, 6};
    }

    @Override
    public int[] common() {
        return new int[]{12, 6};
    }

    @Override
    public int[] normal() {
        return new int[]{24, 8};
    }

    @Override
    public int[] rare() {
        return new int[]{40, 10};
    }

    @Override
    public int[] veryRare() {
        return new int[]{45, 12};
    }

    @Override
    public @NotNull OptionValue<List<Integer>> custom() {
        return SpeedrunnerMod.common().customStructureSpawnRates.swampHuts;
    }
}