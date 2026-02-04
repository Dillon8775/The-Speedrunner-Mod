package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.PotionItem;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

/**
 * The recipe to craft the dragon's fireball, 1 dragon's aura potion and 8 ender pearls.
 */
public class DragonFireballRecipe extends SpecialCraftingRecipe {

    public DragonFireballRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput craftingRecipeInput, World world) {
        if (craftingRecipeInput.getWidth() == 3 && craftingRecipeInput.getHeight() == 3 && craftingRecipeInput.getStackCount() == 9) {
            for (int i = 0; i < craftingRecipeInput.getHeight(); i++) {
                for (int j = 0; j < craftingRecipeInput.getWidth(); j++) {
                    ItemStack itemStack = craftingRecipeInput.getStackInSlot(j, i);
                    if (itemStack.isEmpty()) {
                        return false;
                    }

                    if (j == 1 && i == 1) {
                        if (!(itemStack.getItem() instanceof PotionItem)) {
                            return false;
                        } else {
                            if (!ModUtil.hasDragonsAura(itemStack)) {
                                return false;
                            }
                        }
                    } else if (!itemStack.isOf(Items.FIRE_CHARGE)) {
                        return false;
                    }
                }
            }

            return true;
        } else {
            return false;
        }
    }

    @Override
    public ItemStack craft(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        ItemStack itemStack = craftingRecipeInput.getStackInSlot(1, 1);
        if (!(itemStack.getItem() instanceof PotionItem)) {
            return ItemStack.EMPTY;
        } else {
            return new ItemStack(ModItems.DRAGONS_FIREBALL, 8);
        }
    }

    @Override
    public RecipeSerializer<DragonFireballRecipe> getSerializer() {
        return ModRecipes.DRAGON_FIREBALL_RECIPE_RECIPE_SERIALIZER;
    }
}