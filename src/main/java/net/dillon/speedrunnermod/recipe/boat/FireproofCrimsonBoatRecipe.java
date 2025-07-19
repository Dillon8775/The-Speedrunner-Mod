package net.dillon.speedrunnermod.recipe.boat;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofOneItemBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;

public class FireproofCrimsonBoatRecipe extends AbstractFireproofOneItemBoatRecipe {

    public FireproofCrimsonBoatRecipe(CraftingRecipeCategory category) {
        super(ModItems.SPEEDRUNNER_PADDLE, ModItems.CRIMSON_BOAT, category);
    }

    @Override
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return ModRecipes.FIREPROOF_CRIMSON_BOAT_RECIPE_SERIALIZER;
    }
}