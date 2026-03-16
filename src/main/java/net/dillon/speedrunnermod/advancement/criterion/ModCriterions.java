package net.dillon.speedrunnermod.advancement.criterion;

import net.dillon.speedrunnermod.advancement.TriggeredByItemCriterion;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

/**
 * All speedrunner mod criterions.
 */
public class ModCriterions {
    public static final TriggeredByItemCriterion TRIGGERED_BY_ITEM = register("triggered_by_item_criterion", new TriggeredByItemCriterion());

    public static <T extends CriterionTrigger<?>> T register(String id, T criterion) {
        return Registry.register(BuiltInRegistries.TRIGGER_TYPES, id, criterion);
    }

    /**
     * Initializes speedrunner mod criterions.
     */
    public static void initializeCriterions() {
        SpeedrunnerMod.debug("Initialized criterions.");
    }
}