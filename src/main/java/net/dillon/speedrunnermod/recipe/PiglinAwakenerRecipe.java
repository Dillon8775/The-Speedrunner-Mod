package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.IngredientPlacement;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.display.RecipeDisplay;
import net.minecraft.recipe.display.SlotDisplay;
import net.minecraft.recipe.display.SmithingRecipeDisplay;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

import java.util.List;
import java.util.Optional;

/**
 * The recipe for the piglin awakener recipe, which makes it drop the correct item if crafted on the wrong mode.
 */
public class PiglinAwakenerRecipe extends SpecialCraftingRecipe {
    private static final int CENTER_SLOT = 4;
    protected static SpecialRecipeSerializer<PiglinAwakenerRecipe> PIGLIN_AWAKENER_RECIPE = new SpecialRecipeSerializer<>(PiglinAwakenerRecipe::new);

    public PiglinAwakenerRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @ChatGPT(Credit.FULL_CREDIT)
    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        if (input.size() != 9) {
            return false; // must be 3x3
        }

        Item center = input.getStackInSlot(CENTER_SLOT).getItem(); // center slot

        boolean isValidCenter = center.getDefaultStack().isIn(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES);

        if (!isValidCenter) {
            return false;
        }

        // Check the other 8 slots are all gold ingots
        for (int i = 0; i < input.size(); i++) {
            if (i == CENTER_SLOT) {
                continue; // skip center
            }

            if (input.getStackInSlot(i).getItem() != Items.GOLD_INGOT) {
                return false;
            }
        }

        return true;
    }

    /**
     * Copies the item over as a placeholder for what item to drop if used on the wrong playing mode.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        ItemStack result = new ItemStack(ModItems.PIGLIN_AWAKENER);

        ItemStack center = input.getStackInSlot(CENTER_SLOT); // 4 is center slot

        if (center.isIn(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)) {
            result.set(ModDataComponentTypes.ITEM_TO_DROP_ON_WRONG_PLAYING_MODE, center.copyWithCount(1));
        }

        return result;
    }

    @Override
    public boolean isIgnoredInRecipeBook() {
        return false;
    }

    @Override
    public IngredientPlacement getIngredientPlacement() {
        return IngredientPlacement.forMultipleSlots(List.of(
                Optional.of(Ingredient.ofItem(Items.GOLD_INGOT)),
                Optional.of(Ingredient.ofItem(Items.GOLD_INGOT)),
                Optional.of(Ingredient.ofItem(Items.GOLD_INGOT)),
                Optional.of(Ingredient.ofItem(Items.GOLD_INGOT)),
                Optional.of(Ingredient.ofItems(
                        Items.ENDER_PEARL,
                        Items.BLAZE_POWDER,
                        Items.GOLDEN_APPLE,
                        Items.ENCHANTED_GOLDEN_APPLE,
                        Items.GOLDEN_CARROT
                )),
                Optional.of(Ingredient.ofItem(Items.GOLD_INGOT)),
                Optional.of(Ingredient.ofItem(Items.GOLD_INGOT)),
                Optional.of(Ingredient.ofItem(Items.GOLD_INGOT)),
                Optional.of(Ingredient.ofItem(Items.GOLD_INGOT))
        ));
    }

    @Override
    public List<RecipeDisplay> getDisplays() {
        return List.of(
        );
    }

    @Override
    public RecipeSerializer<? extends SpecialCraftingRecipe> getSerializer() {
        return PIGLIN_AWAKENER_RECIPE;
    }
}