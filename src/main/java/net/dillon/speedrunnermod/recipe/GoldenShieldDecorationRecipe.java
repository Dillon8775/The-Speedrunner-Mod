package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.item.ModItems;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.BannerPatternsComponent;
import net.minecraft.item.BannerItem;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.SpecialCraftingRecipe;
import net.minecraft.recipe.book.CraftingRecipeCategory;
import net.minecraft.recipe.input.CraftingRecipeInput;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.world.World;

/**
 * The {@code banner decoration recipe} for the golden shield.
 * <p>Copied over from the original {@link net.minecraft.recipe.ShieldDecorationRecipe}.</p>
 */
public class GoldenShieldDecorationRecipe extends SpecialCraftingRecipe {

    public GoldenShieldDecorationRecipe(CraftingRecipeCategory category) {
        super(category);
    }

    @Override
    public boolean matches(CraftingRecipeInput craftingRecipeInput, World world) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;
        for (int i = 0; i < craftingRecipeInput.getStacks().size(); ++i) {
            ItemStack itemStack3 = craftingRecipeInput.getStackInSlot(i);
            if (itemStack3.isEmpty()) continue;
            if (itemStack3.getItem() instanceof BannerItem) {
                if (!itemStack2.isEmpty()) {
                    return false;
                }
                itemStack2 = itemStack3;
                continue;
            }
            if (itemStack3.isOf(ModItems.GOLDEN_SHIELD)) {
                if (!itemStack.isEmpty()) {
                    return false;
                }
                BannerPatternsComponent bannerPatternsComponent = itemStack3.getOrDefault(DataComponentTypes.BANNER_PATTERNS, BannerPatternsComponent.DEFAULT);
                if (!bannerPatternsComponent.layers().isEmpty()) {
                    return false;
                }
                itemStack = itemStack3;
                continue;
            }
            return false;
        }
        return !itemStack.isEmpty() && !itemStack2.isEmpty();
    }

    @Override
    public ItemStack craft(CraftingRecipeInput craftingRecipeInput, RegistryWrapper.WrapperLookup wrapperLookup) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;
        for (int i = 0; i < craftingRecipeInput.getStacks().size(); ++i) {
            ItemStack itemStack3 = craftingRecipeInput.getStackInSlot(i);
            if (itemStack3.isEmpty()) continue;
            if (itemStack3.getItem() instanceof BannerItem) {
                itemStack = itemStack3;
                continue;
            }
            if (!itemStack3.isOf(ModItems.GOLDEN_SHIELD)) continue;
            itemStack2 = itemStack3.copy();
        }
        if (itemStack2.isEmpty()) {
            return itemStack2;
        }
        itemStack2.set(DataComponentTypes.BANNER_PATTERNS, itemStack.get(DataComponentTypes.BANNER_PATTERNS));
        itemStack2.set(DataComponentTypes.BASE_COLOR, ((BannerItem)itemStack.getItem()).getColor());
        return itemStack2;
    }

    @Override
    public RecipeSerializer<? extends SpecialCraftingRecipe> getSerializer() {
        return ModRecipes.GOLDEN_SHIELD_DECORATION_RECIPE;
    }
}