package net.dillon.speedrunnermod.recipe.boat.chest;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofBoatRecipe;
import net.dillon.speedrunnermod.recipe.AbstractFireproofChestBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.item.Items;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;

/**
 * Represents the {@code warped chest boat recipe.}
 */
public class WarpedChestBoatRecipe {

    public static class WithPaddle extends AbstractFireproofBoatRecipe {

        public WithPaddle(CraftingRecipeCategory category) {
            super(Items.WARPED_PLANKS, ModItems.WARPED_CHEST_BOAT, true, category);
        }

        @Override
        public RecipeSerializer<WarpedChestBoatRecipe.WithPaddle> getSerializer() {
            return ModRecipes.WARPED_CHEST_BOAT_WITH_PADDLE_RECIPE_SERIALIZER;
        }
    }

    public static class WithoutPaddle extends AbstractFireproofChestBoatRecipe {

        public WithoutPaddle(CraftingRecipeCategory category) {
            super(ModItems.WARPED_BOAT, ModItems.WARPED_CHEST_BOAT, category);
        }

        @Override
        public RecipeSerializer<ShapelessRecipe> getSerializer() {
            return ModRecipes.WARPED_CHEST_BOAT_WITHOUT_PADDLE_RECIPE_SERIALIZER;
        }
    }
}