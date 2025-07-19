package net.dillon.speedrunnermod.recipe.boat.chest;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.AbstractFireproofOneItemBoatRecipe;
import net.dillon.speedrunnermod.recipe.ModRecipes;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;

public class FireproofCrimsonChestBoatRecipe extends AbstractFireproofOneItemBoatRecipe {

    public FireproofCrimsonChestBoatRecipe(CraftingRecipeCategory category) {
        super(ModItems.SPEEDRUNNER_PADDLE, ModItems.CRIMSON_CHEST_BOAT, category);
    }

    @Override
    public RecipeSerializer<ShapelessRecipe> getSerializer() {
        return ModRecipes.FIREPROOF_CRIMSON_CHEST_BOAT_RECIPE_SERIALIZER;
    }
}