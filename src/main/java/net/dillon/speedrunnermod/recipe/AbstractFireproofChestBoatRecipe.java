package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;

/**
 * An abstract representation of a {@code fireproof chest boat recipe.}
 */
public class AbstractFireproofChestBoatRecipe extends ShapelessRecipe {
    private final Item boat;
    private final Item result;

    public AbstractFireproofChestBoatRecipe(Item boat, Item result, CraftingRecipeCategory category) {
        super("chest_boat", category, new ItemStack(result), List.of(
                Ingredient.ofItem(boat),
                Ingredient.ofItem(Items.CHEST)
        ));
        this.boat = boat;
        this.result = result;
    }

    /**
     * Adds the boolean data component to the fireproof boat to make it rideable in lava.
     */
    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        ItemStack result = new ItemStack(this.result);

        // If crafting a chest boat with just a boat and chest then make sure the chest boat comes with the fireproof component as well
        for (ItemStack slot : input.getStacks()) {
            if (slot.isOf(this.boat) && slot.getOrDefault(ModDataComponentTypes.BOOLEAN, false)) {
                result.set(ModDataComponentTypes.BOOLEAN, true);
                break;
            }
        }

        return result;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.MISC;
    }
}