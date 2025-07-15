package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.AI;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.Map;

/**
 * An abstract representation of a {@code fireproof boat recipe.}
 */
public abstract class AbstractFireproofBoatRecipe extends ShapedRecipe {
    private final Item result;

    public AbstractFireproofBoatRecipe(Item plank, Item result, boolean chest, CraftingRecipeCategory category) {
        super("",
                category,
                chest ?
                        RawShapedRecipe.create(
                                Map.of('P', Ingredient.ofItem(plank),
                                        'A', Ingredient.ofItem(ModItems.SPEEDRUNNER_PADDLE),
                                        'C', Ingredient.ofItem(Items.CHEST)),
                                " C ",
                                "PAP",
                                "PPP"
                        ) :
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
     * Allows the recipe to work without the speedrunner paddle.
     * <p>If the recipe has the planks in the correclt slots, it's good!.</p>
     */
    @AI
    public boolean matches(CraftingRecipeInput input, World world) {
        return super.matches(input, world);
    }

    /**
     * Adds the boolean data component to the fireproof boat to make it rideable in lava.
     */
    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        ItemStack result = new ItemStack(this.result);

        ItemStack center = input.getStackInSlot(1); // 1 is "center" slot for this recipe

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