package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.item.ModItems;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;

import java.util.Map;

import static net.dillon.speedrunnermod.recipe.ModRecipes.CENTER_SLOT_3x2;

/**
 * An abstract representation of a {@code fireproof boat recipe.}
 */
public abstract class AbstractFireproofBoatRecipe extends ShapedRecipe {
    private final Item result;

    public AbstractFireproofBoatRecipe(Item plank, Item result, CraftingRecipeCategory category) {
        super("boat",
                category,
                RawShapedRecipe.create(
                        Map.of('P', Ingredient.ofItem(plank),
                                'A', Ingredient.ofItem(ModItems.SPEEDRUNNER_PADDLE)),
                        "PAP",
                        "PPP"
                ),
                new ItemStack(result), true);
        this.result = result;
    }

    /**
     * Adds the boolean data component to the fireproof boat to make it rideable in lava.
     */
    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        ItemStack result = new ItemStack(this.result);

        ItemStack center = input.getStackInSlot(CENTER_SLOT_3x2); // 1 is "center" slot for normal recipe

        if (center.isOf(ModItems.SPEEDRUNNER_PADDLE)) {
            result.set(ModDataComponentTypes.BOOLEAN, true);
        }

        return result;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.MISC;
    }
}