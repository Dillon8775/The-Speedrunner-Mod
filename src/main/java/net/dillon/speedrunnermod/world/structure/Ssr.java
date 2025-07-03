package net.dillon.speedrunnermod.world.structure;

import com.google.gson.JsonElement;
import net.dillon.speedrunnermod.option.OptionValue;
import org.jetbrains.annotations.NotNull;

import java.util.List;

import static net.dillon.speedrunnermod.option.ModOptions.*;

/**
 * The abstract class for configurating {@code Structure Spawn Rate} options.
 */
abstract class Ssr {

    /**
     * Configurates the {@code structure spawn rate spacing and separation values}.
     */
    public final void configurate(JsonElement element) {
        element.getAsJsonObject().getAsJsonObject("placement").addProperty("spacing", getSpacing());
        element.getAsJsonObject().getAsJsonObject("placement").addProperty("separation", getSeparation());
    }

    /**
     * @return the {@code spacing} for each option.
     */
    private int getSpacing() {
        if (isSsrEverywhere()) return everywhere()[0];
        if (isSsrVeryCommon()) return veryCommon()[0];
        if (isSsrCommon()) return common()[0];
        if (isSsrNormal()) return normal()[0];
        if (isSsrRare()) return rare()[0];
        if (isSsrVeryRare()) return veryRare()[0];
        return custom().getCurrentValue().getFirst();
    }

    /**
     * @return the {@code separation} for each option.
     */
    private int getSeparation() {
        if (isSsrEverywhere()) return everywhere()[1];
        if (isSsrVeryCommon()) return veryCommon()[1];
        if (isSsrNormal()) return normal()[1];
        if (isSsrRare()) return rare()[1];
        if (isSsrVeryRare()) return veryRare()[1];
        return custom().getCurrentValue().get(1);
    }

    /**
     * The {@code spacing} and {@code separation} values for each {@link StructureSpawnRate} option.
     * <p>{@code int[]} should only have a size of {@code 1 (2 elements).}</p>
     * <p>{@code index 0 = spacing}</p>
     * <p>{@code index 1 = separation}</p>
     */
    public abstract int[] everywhere();
    public abstract int[] veryCommon();
    public abstract int[] common();
    public abstract int[] normal();
    public abstract int[] rare();
    public abstract int[] veryRare();
    @NotNull
    public abstract OptionValue<List<Integer>> custom();
}