package net.dillon.speedrunnermod.recipe.boat.chest;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofBoatRecipe;
import net.dillon.speedrunnermod.recipe.AbstractFireproofChestBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;

/**
 * Represents the {@code speedrunner chest boat crafting recipe.}
 */
public class SpeedrunnerChestBoatRecipe {

    public static class WithPaddle extends AbstractFireproofBoatRecipe {

        public WithPaddle(CraftingRecipeCategory category) {
            super(ModItems.SPEEDRUNNER_PLANKS, ModItems.SPEEDRUNNER_CHEST_BOAT, true, category);
        }

        @Override
        public RecipeSerializer<WithPaddle> getSerializer() {
            return ModRecipes.SPEEDRUNNER_CHEST_BOAT_WITH_PADDLE_RECIPE_SERIALIZER;
        }
    }

    public static class WithoutPaddle extends AbstractFireproofChestBoatRecipe {

        public WithoutPaddle(CraftingRecipeCategory category) {
            super(ModItems.SPEEDRUNNER_BOAT, ModItems.SPEEDRUNNER_CHEST_BOAT, category);
        }

        @Override
        public RecipeSerializer<ShapelessRecipe> getSerializer() {
            return ModRecipes.SPEEDRUNNER_CHEST_BOAT_WITHOUT_PADDLE_RECIPE_SERIALIZER;
        }
    }
}