package net.dillon.speedrunnermod.recipe.boat;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;

/**
 * Represents the {@code crimson boat crafting recipe.}
 */
public class CrimsonBoatRecipe extends AbstractFireproofBoatRecipe {

    public CrimsonBoatRecipe(CraftingRecipeCategory category) {
        super(Items.CRIMSON_PLANKS, ModItems.CRIMSON_BOAT, category);
    }

    @Override
    public RecipeSerializer<CrimsonBoatRecipe> getSerializer() {
        return ModRecipes.CRIMSON_BOAT_RECIPE_SERIALIZER;
    }
}