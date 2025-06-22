package net.dillon.speedrunnermod.recipe;

import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code custom recipes.}
 */
public class ModRecipes {

    /**
     * Registers a {@code special crafting recipe.}
     */
    private static void register(String path, SpecialCraftingRecipe.SpecialRecipeSerializer<?> specialCraftingRecipe) {
        Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod(path), specialCraftingRecipe);
    }

    /**
     * Initializes all Speedrunner Mod {@code custom recipes.}
     */
    public static void registerCustomRecipes() {
        register("crafting_special_speedrunner_shield_decoration", SpeedrunnerShieldDecorationRecipe.SPEEDRUNNER_SHIELD_DECORATION_RECIPE);
        register("crafting_piglin_awakener", PiglinAwakenerRecipe.PIGLIN_AWAKENER_RECIPE);
    }
}