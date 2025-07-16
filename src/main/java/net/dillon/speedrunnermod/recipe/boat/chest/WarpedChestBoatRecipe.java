package net.dillon.speedrunnermod.recipe.boat.chest;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofChestBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;

/**
 * Represents the {@code warped chest boat recipe.}
 */
public class WarpedChestBoatRecipe extends AbstractFireproofChestBoatRecipe {

    public WarpedChestBoatRecipe(CraftingRecipeCategory category) {
        super(ModItems.WARPED_BOAT, ModItems.WARPED_CHEST_BOAT, category);
    }

    @Override
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return ModRecipes.WARPED_CHEST_BOAT_RECIPE_SERIALIZER;
    }
}