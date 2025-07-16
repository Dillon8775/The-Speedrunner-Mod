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
 * Represents the {@code crimson chest boat crafting recipe.}
 */
public class CrimsonChestBoatRecipe {

    public static class WithPaddle extends AbstractFireproofBoatRecipe {
        public WithPaddle(CraftingRecipeCategory category) {
            super(Items.CRIMSON_PLANKS, ModItems.CRIMSON_CHEST_BOAT, true, category);
        }

        @Override
        public RecipeSerializer<WithPaddle> getSerializer() {
            return ModRecipes.CRIMSON_CHEST_BOAT_WITH_PADDLE_RECIPE_SERIALIZER;
        }
    }

    public static class WithoutPaddle extends AbstractFireproofChestBoatRecipe {

        public WithoutPaddle(CraftingRecipeCategory category) {
            super(ModItems.CRIMSON_BOAT, ModItems.CRIMSON_CHEST_BOAT, category);
        }

        @Override
        public RecipeSerializer<ShapelessRecipe> getSerializer() {
            return ModRecipes.CRIMSON_CHEST_BOAT_WITHOUT_PADDLE_RECIPE_SERIALIZER;
        }
    }
}