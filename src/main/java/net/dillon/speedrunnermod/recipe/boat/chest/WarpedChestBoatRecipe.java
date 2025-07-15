package net.dillon.speedrunnermod.recipe.boat.chest;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;

/**
 * Represents the {@code warped chest boat recipe.}
 */
public class WarpedChestBoatRecipe extends AbstractFireproofBoatRecipe {

    public WarpedChestBoatRecipe(CraftingRecipeCategory category) {
        super(Items.WARPED_PLANKS, ModItems.WARPED_CHEST_BOAT, true, category);
    }

    @Override
    public RecipeSerializer<WarpedChestBoatRecipe> getSerializer() {
        return ModRecipes.WARPED_CHEST_BOAT_RECIPE_SERIALIZER;
    }
}