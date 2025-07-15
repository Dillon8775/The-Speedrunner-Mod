package net.dillon.speedrunnermod.recipe.boat.chest;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;

/**
 * Represents the {@code crimson chest boat crafting recipe.}
 */
public class CrimsonChestBoatRecipe extends AbstractFireproofBoatRecipe {

    public CrimsonChestBoatRecipe(CraftingRecipeCategory category) {
        super(Items.CRIMSON_PLANKS, ModItems.CRIMSON_CHEST_BOAT, true, category);
    }

    @Override
    public RecipeSerializer<CrimsonChestBoatRecipe> getSerializer() {
        return ModRecipes.CRIMSON_CHEST_BOAT_RECIPE_SERIALIZER;
    }
}