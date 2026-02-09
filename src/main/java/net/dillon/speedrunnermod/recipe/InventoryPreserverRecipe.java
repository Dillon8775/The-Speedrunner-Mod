package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.world.World;

/**
 * The recipe for upgrading inventory preservers.
 */
public class InventoryPreserverRecipe extends SpecialCraftingRecipe {

    public InventoryPreserverRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    /**
     * Ensure we can upgrade the preserver.
     */
    @Override
    public boolean matches(CraftingRecipeInput input, World world) {
        boolean hasPreserver = false;
        boolean hasMatter = false;

        for (ItemStack stack : input.getStacks()) {
            if (stack.isEmpty()) {
                continue;
            }

            if (stack.isOf(ModItems.INVENTORY_PRESERVER)) {
                if (stack.get(DataComponentTypes.MAX_DAMAGE) >= 3) {
                    return false;
                }
                hasPreserver = true;
            }

            if (stack.isOf(ModItems.ENDER_MATTER)) {
                hasMatter = true;
            }
        }

        return hasPreserver && hasMatter;
    }

    @Override
    public ItemStack craft(CraftingRecipeInput input, RegistryWrapper.WrapperLookup registries) {
        ItemStack result = new ItemStack(ModItems.INVENTORY_PRESERVER);

        for (ItemStack stack : input.getStacks()) {
            if (stack.isOf(ModItems.INVENTORY_PRESERVER)) {
                int newMaxLevel = stack.getOrDefault(DataComponentTypes.MAX_DAMAGE, 1) + 1;
                result.set(DataComponentTypes.MAX_DAMAGE, newMaxLevel);
                if (stack.get(DataComponentTypes.MAX_DAMAGE) == 2 && stack.getOrDefault(DataComponentTypes.DAMAGE, 0) == 1) {
                    result.set(DataComponentTypes.DAMAGE, 1);
                }
                result.set(
                        DataComponentTypes.ITEM_NAME,
                        newMaxLevel == 2
                                ? Text.translatable("item.speedrunnermod.strong_inventory_preserver")
                                : Text.translatable("item.speedrunnermod.resistant_inventory_preserver")
                );
                break;
            }
        }

        return result;
    }

    @Override
    public RecipeSerializer<InventoryPreserverRecipe> getSerializer() {
        return ModRecipes.INVENTORY_PRESERVER_RECIPE_SERIALIZER;
    }
}