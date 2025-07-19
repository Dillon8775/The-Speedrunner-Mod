package net.dillon.speedrunnermod.recipe.boat.chest;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofOneItemBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;

/**
 * Represents the {@code speedrunner chest boat crafting recipe.}
 */
public class SpeedrunnerChestBoatRecipe extends AbstractFireproofOneItemBoatRecipe {

    public SpeedrunnerChestBoatRecipe(CraftingRecipeCategory category) {
        super(Items.CHEST, ModItems.SPEEDRUNNER_BOAT, ModItems.SPEEDRUNNER_CHEST_BOAT, category);
    }

    @Override
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return ModRecipes.SPEEDRUNNER_CHEST_BOAT_RECIPE_SERIALIZER;
    }
}