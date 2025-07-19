package net.dillon.speedrunnermod.recipe.boat;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofOneItemBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;

public class FireproofSpeedrunnerBoatRecipe extends AbstractFireproofOneItemBoatRecipe {

    public FireproofSpeedrunnerBoatRecipe(CraftingRecipeCategory category) {
        super(ModItems.SPEEDRUNNER_PADDLE, ModItems.SPEEDRUNNER_BOAT, category);
    }

    @Override
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return ModRecipes.FIREPROOF_SPEEDRUNNER_BOAT_RECIPE_SERIALIZER;
    }
}