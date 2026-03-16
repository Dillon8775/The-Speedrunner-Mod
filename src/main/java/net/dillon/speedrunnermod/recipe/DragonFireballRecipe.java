package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

/**
 * The recipe to craft the dragon's fireball, 1 dragon's aura potion and 8 ender pearls.
 */
public class DragonFireballRecipe extends CustomRecipe {

    public DragonFireballRecipe(CraftingBookCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingInput craftingRecipeInput, Level world) {
        if (craftingRecipeInput.width() == 3 && craftingRecipeInput.height() == 3 && craftingRecipeInput.ingredientCount() == 9) {
            for (int i = 0; i < craftingRecipeInput.height(); i++) {
                for (int j = 0; j < craftingRecipeInput.width(); j++) {
                    ItemStack itemStack = craftingRecipeInput.getItem(j, i);
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
                    } else if (!itemStack.is(Items.FIRE_CHARGE)) {
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
    public ItemStack assemble(CraftingInput craftingRecipeInput) {
        ItemStack itemStack = craftingRecipeInput.getItem(1, 1);
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