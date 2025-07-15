package net.dillon.speedrunnermod.recipe.boat.chest;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.book.CraftingRecipeCategory;

/**
 * Represents the {@code speedrunner chest boat crafting recipe.}
 */
public class SpeedrunnerChestBoatRecipe extends AbstractFireproofBoatRecipe {

    public SpeedrunnerChestBoatRecipe(CraftingRecipeCategory category) {
        super(ModItems.SPEEDRUNNER_PLANKS, ModItems.SPEEDRUNNER_CHEST_BOAT, true, category);
    }

    @Override
    public RecipeSerializer<SpeedrunnerChestBoatRecipe> getSerializer() {
        return ModRecipes.SPEEDRUNNER_CHEST_BOAT_RECIPE_SERIALIZER;
    }
}