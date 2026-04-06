package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.item.ModItems;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.BannerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BannerPatternLayers;

/**
 * The {@code banner decoration recipe} for the golden shield.
 * <p>Copied over from the original {@link net.minecraft.world.item.crafting.ShieldDecorationRecipe}.</p>
 */
public class GoldenShieldDecorationRecipe extends CustomRecipe {

    public GoldenShieldDecorationRecipe() {
        super();
    }

    @Override
    public boolean matches(CraftingInput craftingRecipeInput, Level world) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;
        for (int i = 0; i < craftingRecipeInput.items().size(); ++i) {
            ItemStack itemStack3 = craftingRecipeInput.getItem(i);
            if (itemStack3.isEmpty()) continue;
            if (itemStack3.getItem() instanceof BannerItem) {
                if (!itemStack2.isEmpty()) {
                    return false;
                }
                itemStack2 = itemStack3;
                continue;
            }
            if (itemStack3.is(ModItems.GOLDEN_SHIELD)) {
                if (!itemStack.isEmpty()) {
                    return false;
                }
                BannerPatternLayers bannerPatternsComponent = itemStack3.getOrDefault(DataComponents.BANNER_PATTERNS, BannerPatternLayers.EMPTY);
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
    public ItemStack assemble(CraftingInput craftingRecipeInput) {
        ItemStack itemStack = ItemStack.EMPTY;
        ItemStack itemStack2 = ItemStack.EMPTY;
        for (int i = 0; i < craftingRecipeInput.items().size(); ++i) {
            ItemStack itemStack3 = craftingRecipeInput.getItem(i);
            if (itemStack3.isEmpty()) continue;
            if (itemStack3.getItem() instanceof BannerItem) {
                itemStack = itemStack3;
                continue;
            }
            if (!itemStack3.is(ModItems.GOLDEN_SHIELD)) continue;
            itemStack2 = itemStack3.copy();
        }
        if (itemStack2.isEmpty()) {
            return itemStack2;
        }
        itemStack2.set(DataComponents.BANNER_PATTERNS, itemStack.get(DataComponents.BANNER_PATTERNS));
        itemStack2.set(DataComponents.BASE_COLOR, ((BannerItem)itemStack.getItem()).getColor());
        return itemStack2;
    }

    @Override
    public RecipeSerializer<? extends CustomRecipe> getSerializer() {
        return ModRecipes.GOLDEN_SHIELD_DECORATION_RECIPE;
    }
}