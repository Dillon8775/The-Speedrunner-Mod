package net.dillon.speedrunnermod.recipe.boat.chest;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofOneItemBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;

/**
 * Represents the {@code crimson chest boat crafting recipe.}
 */
public class CrimsonChestBoatRecipe extends AbstractFireproofOneItemBoatRecipe {

    public CrimsonChestBoatRecipe(CraftingRecipeCategory category) {
        super(Items.CHEST, ModItems.CRIMSON_BOAT, ModItems.CRIMSON_CHEST_BOAT, category);
    }

    @Override
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return ModRecipes.CRIMSON_CHEST_BOAT_RECIPE_SERIALIZER;
    }
}