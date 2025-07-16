package net.dillon.speedrunnermod.recipe.boat;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;

/**
 * Represents the {@code warped boat recipe.}
 */
public class WarpedBoatRecipe extends AbstractFireproofBoatRecipe {

    public WarpedBoatRecipe(CraftingRecipeCategory category) {
        super(Items.WARPED_PLANKS, ModItems.WARPED_BOAT, category);
    }

    @Override
    public RecipeSerializer<WarpedBoatRecipe> getSerializer() {
        return ModRecipes.WARPED_BOAT_RECIPE_SERIALIZER;
    }
}