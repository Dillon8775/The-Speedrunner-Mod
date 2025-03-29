package net.dillon.speedrunnermod.advancement.criterion;

import net.dillon.speedrunnermod.advancement.TriggeredByItemCriterion;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

/**
 * All speedrunner mod criterions.
 */
public class ModCriterions {
    public static final TriggeredByItemCriterion TRIGGERED_BY_ITEM = register("triggered_by_item_criterion", new TriggeredByItemCriterion());

    public static <T extends Criterion<?>> T register(String id, T criterion) {
        return Registry.register(Registries.CRITERION, id, criterion);
    }

    /**
     * Initializes speedrunner mod criterions.
     */
    public static void initializeCriterions() {}
}