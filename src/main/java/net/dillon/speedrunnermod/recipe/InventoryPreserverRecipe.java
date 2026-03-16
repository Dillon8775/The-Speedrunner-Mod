package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

/**
 * The recipe for upgrading inventory preservers.
 */
public class InventoryPreserverRecipe extends CustomRecipe {

    public InventoryPreserverRecipe(CraftingBookCategory category) {
        super(category);
    }

    /**
     * Ensure we can upgrade the preserver.
     */
    @Override
    public boolean matches(CraftingInput input, Level world) {
        boolean hasPreserver = false;
        boolean hasMatter = false;

        for (ItemStack stack : input.items()) {
            if (stack.isEmpty()) {
                continue;
            }

            if (stack.is(ModItems.INVENTORY_PRESERVER)) {
                if (stack.get(DataComponents.MAX_DAMAGE) >= 3) {
                    return false;
                }
                hasPreserver = true;
            }

            if (stack.is(ModItems.ENDER_MATTER)) {
                hasMatter = true;
            }
        }

        return hasPreserver && hasMatter;
    }

    @Override
    public ItemStack assemble(CraftingInput input) {
        ItemStack result = new ItemStack(ModItems.INVENTORY_PRESERVER);

        for (ItemStack stack : input.items()) {
            if (stack.is(ModItems.INVENTORY_PRESERVER)) {
                int newMaxLevel = stack.getOrDefault(DataComponents.MAX_DAMAGE, 1) + 1;
                result.set(DataComponents.MAX_DAMAGE, newMaxLevel);
                if (stack.get(DataComponents.MAX_DAMAGE) == 2 && stack.getOrDefault(DataComponents.DAMAGE, 0) == 1) {
                    result.set(DataComponents.DAMAGE, 1);
                }
                result.set(
                        DataComponents.ITEM_NAME,
                        newMaxLevel == 2
                                ? Component.translatable("item.speedrunnermod.strong_inventory_preserver")
                                : Component.translatable("item.speedrunnermod.resistant_inventory_preserver")
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