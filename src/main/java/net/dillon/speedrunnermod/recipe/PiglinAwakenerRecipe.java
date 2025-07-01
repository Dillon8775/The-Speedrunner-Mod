package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.AI;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RawShapedRecipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapedRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;

import java.util.Map;

import static net.dillon.speedrunnermod.option.ModOptions.isPlayingModeBalanced;

/**
 * The recipe for the piglin awakener recipe, which makes it drop the correct item if crafted on the wrong mode.
 */
public class PiglinAwakenerRecipe extends ShapedRecipe {
    private static final int CENTER_SLOT = 4;

    public PiglinAwakenerRecipe(CraftingRecipeCategory category) {
        super("",
                category,
                RawShapedRecipe.create(
                        Map.of('#', Ingredient.ofItem(Items.GOLD_INGOT),
                                'O', Ingredient.ofItems(
                                        Items.ENDER_PEARL,
                                        Items.BLAZE_POWDER,
                                        Items.GOLDEN_APPLE,
                                        Items.ENCHANTED_GOLDEN_APPLE,
                                        Items.GOLDEN_CARROT
                                )),
                        "###",
                        "#O#",
                        "###"
        ),
                new ItemStack(ModItems.PIGLIN_AWAKENER), true);
    }

    /**
     * Copies the item over as a placeholder for what item to drop if used on the wrong playing mode.
     */
    @AI
    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        ItemStack result = new ItemStack(ModItems.PIGLIN_AWAKENER);

        ItemStack center = input.getStackInSlot(CENTER_SLOT); // 4 is center slot

        if (isPlayingModeBalanced() && center.isIn(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)) {
            result.set(ModDataComponentTypes.STORED_ITEMSTACK, center.copyWithCount(1));
        }

        return result;
    }

    @Override
    public RecipeSerializer<PiglinAwakenerRecipe> getSerializer() {
        return ModRecipes.PIGLIN_AWAKENER_RECIPE_SERIALIZER;
    }

    @Override
    public CraftingRecipeCategory getCategory() {
        return CraftingRecipeCategory.MISC;
    }
}