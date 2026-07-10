package net.dillon.speedrunnermod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.dillon.speedrunnermod.helper.ModComponentHelper;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PotionItem;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

/**
 * The recipe to craft the dragon's fireball, 1 dragon's aura potion and 8 ender pearls.
 */
public class DragonFireballRecipe extends CustomRecipe {
    public static final MapCodec<DragonFireballRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                            ItemStackTemplate.CODEC.fieldOf("result").forGetter(o -> o.result)
                    )
                    .apply(i, DragonFireballRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, DragonFireballRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            o -> o.result,
            DragonFireballRecipe::new
    );
    public static final RecipeSerializer<DragonFireballRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
    private final ItemStackTemplate result;

    public DragonFireballRecipe(final ItemStackTemplate result) {
        this.result = result;
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
                            if (!ModComponentHelper.hasDragonsAura(itemStack)) {
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
            return this.result.apply(DataComponentPatch.EMPTY);
        }
    }

    @Override
    public RecipeSerializer<DragonFireballRecipe> getSerializer() {
        return SERIALIZER;
    }
}