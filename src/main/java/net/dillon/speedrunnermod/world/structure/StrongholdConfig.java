package net.dillon.speedrunnermod.world.structure;

import com.google.gson.JsonElement;
import net.minecraft.world.level.levelgen.structure.placement.ConcentricRingsStructurePlacement;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.option.CommonModOptions.isDoomMode;

/**
 * A special config for strongholds, as they use a {@link ConcentricRingsStructurePlacement}.
 */
public class StrongholdConfig {

    /**
     * Configures strongholds correctly, for their distance, spread, and total count in the world.
     * <p>Distance - how close strongholds can generate to spawn. </p>
     * <p>Spread - how far apart strongholds can generate from each other.</p>
     * <p>Count - the total amount of strongholds that are allowed to create in a single Minecraft world.</p>
     */
    public void configure(JsonElement element) {
        if (!isDoomMode()) {
            element.getAsJsonObject().getAsJsonObject("placement").addProperty("distance", common().worldGen.strongholdDistance.getCurrentValue());
            element.getAsJsonObject().getAsJsonObject("placement").addProperty("spread", common().worldGen.strongholdSpread.getCurrentValue());
        }
        element.getAsJsonObject().getAsJsonObject("placement").addProperty("count", common().worldGen.totalStrongholds.getCurrentValue());
    }
}