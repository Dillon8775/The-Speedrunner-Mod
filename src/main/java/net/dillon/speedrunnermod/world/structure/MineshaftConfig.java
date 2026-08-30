package net.dillon.speedrunnermod.world.structure;

import com.google.gson.JsonElement;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.OptionValue;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.option.CommonModOptions.*;

/**
 * A special config for mineshafts, as it generates differently.
 */
public class MineshaftConfig {

    public void configure(JsonElement element) {
        element.getAsJsonObject().getAsJsonObject("placement").addProperty("frequency", this.getFrequency() / 1000.0F);
    }

    public float everywhere() {
        return 40.0F;
    }

    public float veryCommon() {
        return 25.0F;
    }

    public float common() {
        return 14.0F;
    }

    public float normal() {
        return 4.0F;
    }

    public float rare() {
        return 3.0F;
    }

    public float veryRare() {
        return 2.0F;
    }

    public @NotNull OptionValue<Integer> custom() {
        return SpeedrunnerMod.common().customStructureSpawnRates.mineshafts;
    }

    /**
     * @return the {@code frequency} for mineshafts.
     */
    private float getFrequency() {
        if (isSsrEverywhere()) return everywhere();
        if (isSsrVeryCommon()) return veryCommon();
        if (isSsrCommon()) return common();
        if (isSsrNormal()) return normal();
        if (isSsrRare()) return rare();
        if (isSsrVeryRare()) return veryRare();
        return (float)custom().getCurrentValue();
    }
}