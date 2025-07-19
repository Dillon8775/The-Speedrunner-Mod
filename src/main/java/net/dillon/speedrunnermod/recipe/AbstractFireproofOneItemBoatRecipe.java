package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;

/**
 * An abstract representation of a {@code fireproof boat recipe} with either a {@code speedrunner paddle} or a {@code chest.}
 */
public class AbstractFireproofOneItemBoatRecipe extends ShapelessRecipe {
    private final Item otherItem;
    private final Item boat;
    private final Item result;

    public AbstractFireproofOneItemBoatRecipe(Item otherItem, Item originalBoat, CraftingRecipeCategory category) {
        this(otherItem, originalBoat, originalBoat, category);
    }

    public AbstractFireproofOneItemBoatRecipe(Item otherItem, Item boat, Item result, CraftingRecipeCategory category) {
        super("chest_boat", category, new ItemStack(result), List.of(
                Ingredient.ofItem(boat),
                Ingredient.ofItem(otherItem)
        ));
        this.otherItem = otherItem;
        this.boat = boat;
        this.result = result;
    }

    /**
     * Adds the boolean data component to the fireproof boat to make it rideable in lava.
     */
    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        ItemStack result = new ItemStack(this.result);

        boolean hasOtherItem = false;
        for (ItemStack slot : input.getStacks()) {
            if (slot.isOf(this.otherItem)) {
                hasOtherItem = true;
                break;
            }
        }

        // If crafting a chest boat with just a boat and chest then make sure the chest boat comes with the fireproof component as well
        for (ItemStack slot : input.getStacks()) {
            if (slot.isOf(this.boat) && (slot.getOrDefault(ModDataComponentTypes.BOOLEAN, false) || hasOtherItem)) {
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