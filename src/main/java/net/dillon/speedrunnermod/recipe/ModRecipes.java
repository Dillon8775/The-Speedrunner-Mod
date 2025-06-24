package net.dillon.speedrunnermod.recipe;

import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code custom recipes.}
 */
public class ModRecipes {
    protected static final RecipeSerializer<PiglinAwakenerRecipe> PIGLIN_AWAKENER_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_piglin_awakener"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(PiglinAwakenerRecipe::new));
    protected static final SpecialCraftingRecipe.SpecialRecipeSerializer<SpeedrunnerShieldDecorationRecipe> SPEEDRUNNER_SHIELD_DECORATION_RECIPE =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_speedrunner_shield_decoration"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(SpeedrunnerShieldDecorationRecipe::new));

    /**
     * Initializes all Speedrunner Mod {@code custom recipes.}
     */
    public static void initializeCustomRecipes() {}
}