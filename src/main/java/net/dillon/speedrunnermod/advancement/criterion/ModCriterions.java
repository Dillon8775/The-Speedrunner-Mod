package net.dillon.speedrunnermod.advancement.criterion;

import net.dillon.speedrunnermod.advancement.UsedItemCriterion;
import net.minecraft.advancement.criterion.Criterion;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

/**
 * All speedrunner mod criterions.
 */
public class ModCriterions {
    public static final UsedItemCriterion USED_ITEM = register("used_item_criterion", new UsedItemCriterion());

    public static <T extends Criterion<?>> T register(String id, T criterion) {
        return Registry.register(Registries.CRITERION, id, criterion);
    }

    /**
     * Initializes speedrunner mod criterions.
     */
    public static void initializeCriterions() {}
}