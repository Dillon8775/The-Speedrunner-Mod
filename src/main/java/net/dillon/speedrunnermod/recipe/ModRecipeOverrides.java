package net.dillon.speedrunnermod.recipe;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.crafting.Recipe;

import java.util.HashSet;
import java.util.Set;

public class ModRecipeOverrides {
    private static final Set<ResourceKey<Recipe<?>>> OVERRIDDEN_RECIPES = new HashSet<>();
    private static final ThreadLocal<Boolean> GENERATING_OVERRIDE = ThreadLocal.withInitial(() -> false);

    static {
        register("minecraft:anvil");
    }

    /**
     * Registers a vanilla recipe ID that should be replaced.
     */
    private static void register(String id) {
        OVERRIDDEN_RECIPES.add(key(id));
    }

    /**
     * Creates a recipe ResourceKey from an identifier.
     */
    public static ResourceKey<Recipe<?>> key(String id) {
        return ResourceKey.create(
                Registries.RECIPE,
                Identifier.parse(id)
        );
    }

    /**
     * Returns whether the given recipe is one of the vanilla
     * recipes that we want to replace.
     */
    public static boolean isOverridden(ResourceKey<Recipe<?>> id) {
        return OVERRIDDEN_RECIPES.contains(id);
    }

    /**
     * Marks the current thread as generating one of our
     * replacement recipes.
     */
    public static void beginOverrideGeneration() {
        GENERATING_OVERRIDE.set(true);
    }

    /**
     * Ends replacement recipe generation.
     */
    public static void endOverrideGeneration() {
        GENERATING_OVERRIDE.remove();
    }

    /**
     * Returns whether our own replacement recipe is currently
     * being generated.
     */
    public static boolean isGeneratingOverride() {
        return GENERATING_OVERRIDE.get();
    }

    /**
     * Saves a replacement recipe using the original vanilla ID.
     */
    public static void save(RecipeBuilder builder, RecipeOutput output, String id) {
        beginOverrideGeneration();

        try {
            builder.save(output, key(id));
        } finally {
            endOverrideGeneration();
        }
    }
}