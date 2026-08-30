package net.dillon.speedrunnermod.world.structure;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.OptionValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class TrailRuinConfig extends Ssr {

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
        return new int[]{11, 7};
    }

    @Override
    public int[] normal() {
        return new int[]{24, 9};
    }

    @Override
    public int[] rare() {
        return new int[]{40, 16};
    }

    @Override
    public int[] veryRare() {
        return new int[]{45, 20};
    }

    @Override
    public @NotNull OptionValue<List<Integer>> custom() {
        return SpeedrunnerMod.common().customStructureSpawnRates.trailRuins;
    }
}