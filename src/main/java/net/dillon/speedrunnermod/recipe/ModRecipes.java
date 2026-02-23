package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code custom recipes.}
 */
public class ModRecipes {
    protected static final int CENTER_SLOT_3x3 = 4;

    public static final RecipeSerializer<InventoryPreserverRecipe> INVENTORY_PRESERVER_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_inventory_preserver"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(InventoryPreserverRecipe::new));

    protected static final RecipeSerializer<DragonFireballRecipe> DRAGON_FIREBALL_RECIPE_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_dragon_fireball"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(DragonFireballRecipe::new));
    protected static final RecipeSerializer<PiglinAwakenerRecipe> PIGLIN_AWAKENER_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_piglin_awakener"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(PiglinAwakenerRecipe::new));

    protected static final SpecialCraftingRecipe.SpecialRecipeSerializer<SpeedrunnerShieldDecorationRecipe> SPEEDRUNNER_SHIELD_DECORATION_RECIPE =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_speedrunner_shield_decoration"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(SpeedrunnerShieldDecorationRecipe::new));
    protected static final SpecialCraftingRecipe.SpecialRecipeSerializer<GoldenShieldDecorationRecipe> GOLDEN_SHIELD_DECORATION_RECIPE =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_golden_shield_decoration"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(GoldenShieldDecorationRecipe::new));

    /**
     * Initializes all Speedrunner Mod {@code custom recipes.}
     */
    public static void initializeCustomRecipes() {
        SpeedrunnerMod.debug("Initialized custom recipes.");
    }
}