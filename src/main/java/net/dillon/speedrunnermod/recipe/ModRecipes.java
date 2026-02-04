package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.recipe.boat.*;
import net.dillon.speedrunnermod.recipe.boat.chest.*;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code custom recipes.}
 */
public class ModRecipes {
    protected static final int CENTER_SLOT_3x2 = 1;
    protected static final int CENTER_SLOT_3x3 = 4;

    public static final RecipeSerializer<CrimsonBoatRecipe> CRIMSON_BOAT_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_crimson_boat"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(CrimsonBoatRecipe::new));
    public static final RecipeSerializer<ShapelessRecipe> FIREPROOF_CRIMSON_BOAT_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_fireproof_crimson_boat"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(FireproofCrimsonBoatRecipe::new));
    public static final RecipeSerializer<ShapelessRecipe> CRIMSON_CHEST_BOAT_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_crimson_chest_boat"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(CrimsonChestBoatRecipe::new));
    public static final RecipeSerializer<ShapelessRecipe> FIREPROOF_CRIMSON_CHEST_BOAT_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_fireproof_crimson_chest_boat"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(FireproofCrimsonChestBoatRecipe::new));

    public static final RecipeSerializer<SpeedrunnerBoatRecipe> SPEEDRUNNER_BOAT_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_speedrunner_boat"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(SpeedrunnerBoatRecipe::new));
    public static final RecipeSerializer<ShapelessRecipe> FIREPROOF_SPEEDRUNNER_BOAT_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_fireproof_speedrunner_boat"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(FireproofSpeedrunnerBoatRecipe::new));
    public static final RecipeSerializer<ShapelessRecipe> SPEEDRUNNER_CHEST_BOAT_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_speedrunner_chest_boat"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(SpeedrunnerChestBoatRecipe::new));
    public static final RecipeSerializer<ShapelessRecipe> FIREPROOF_SPEEDRUNNER_CHEST_BOAT_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_fireproof_speedrunner_chest_boat"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(FireproofSpeedrunnerChestBoatRecipe::new));

    public static final RecipeSerializer<WarpedBoatRecipe> WARPED_BOAT_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_warped_boat"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(WarpedBoatRecipe::new));
    public static final RecipeSerializer<ShapelessRecipe> FIREPROOF_WARPED_BOAT_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_fireproof_warped_boat"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(FireproofWarpedBoatRecipe::new));
    public static final RecipeSerializer<ShapelessRecipe> WARPED_CHEST_BOAT_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_warped_chest_boat"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(WarpedChestBoatRecipe::new));
    public static final RecipeSerializer<ShapelessRecipe> FIREPROOF_WARPED_CHEST_BOAT_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_fireproof_warped_chest_boat"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(FireproofWarpedChestBoatRecipe::new));

    protected static final RecipeSerializer<DragonFireballRecipe> DRAGON_FIREBALL_RECIPE_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_dragon_fireball"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(DragonFireballRecipe::new));
    protected static final RecipeSerializer<PiglinAwakenerRecipe> PIGLIN_AWAKENER_RECIPE_SERIALIZER =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_piglin_awakener"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(PiglinAwakenerRecipe::new));
    protected static final SpecialCraftingRecipe.SpecialRecipeSerializer<SpeedrunnerShieldDecorationRecipe> SPEEDRUNNER_SHIELD_DECORATION_RECIPE =
            Registry.register(Registries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_speedrunner_shield_decoration"), new SpecialCraftingRecipe.SpecialRecipeSerializer<>(SpeedrunnerShieldDecorationRecipe::new));

    /**
     * Initializes all Speedrunner Mod {@code custom recipes.}
     */
    public static void initializeCustomRecipes() {
        SpeedrunnerMod.debug("Initialized custom recipes.");
    }
}