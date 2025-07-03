package net.dillon.speedrunnermod.world.structure;

import net.dillon.speedrunnermod.option.OptionValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

public class WoodlandMansionConfig extends Ssr {

    @Override
    public int[] everywhere() {
        return new int[]{6, 4};
    }

    @Override
    public int[] veryCommon() {
        return new int[]{16, 8};
    }

    @Override
    public int[] common() {
        return new int[]{25, 20};
    }

    @Override
    public int[] normal() {
        return new int[]{40, 20};
    }

    @Override
    public int[] rare() {
        return new int[]{100, 20};
    }

    @Override
    public int[] veryRare() {
        return new int[]{120, 25};
    }

    @Override
    public @NotNull OptionValue<List<Integer>> custom() {
        return options().customStructureSpawnRates.woodlandMansions;
    }
}