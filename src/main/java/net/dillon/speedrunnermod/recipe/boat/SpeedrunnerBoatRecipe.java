package net.dillon.speedrunnermod.recipe.boat;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;

/**
 * Represents the {@code speedrunner boat crafting recipe.}
 */
public class SpeedrunnerBoatRecipe extends AbstractFireproofBoatRecipe {

    public SpeedrunnerBoatRecipe(CraftingRecipeCategory category) {
        super(ModItems.SPEEDRUNNER_PLANKS, ModItems.SPEEDRUNNER_BOAT, category);
    }

    @Override
    public RecipeSerializer<SpeedrunnerBoatRecipe> getSerializer() {
        return ModRecipes.SPEEDRUNNER_BOAT_RECIPE_SERIALIZER;
    }
}