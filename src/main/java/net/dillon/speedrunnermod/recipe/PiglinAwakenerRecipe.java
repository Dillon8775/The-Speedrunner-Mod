package net.dillon.speedrunnermod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;

import static net.dillon.speedrunnermod.recipe.ModRecipes.CENTER_SLOT_3x3;

/**
 * The recipe for the piglin awakener recipe, which makes it drop the correct item if crafted on the wrong mode.
 */
public class PiglinAwakenerRecipe extends ShapedRecipe {
    public static final MapCodec<PiglinAwakenerRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                            Recipe.CommonInfo.MAP_CODEC.forGetter(o -> o.commonInfo),
                            CraftingRecipe.CraftingBookInfo.MAP_CODEC.forGetter(o -> o.bookInfo),
                            ShapedRecipePattern.MAP_CODEC.forGetter(o -> o.pattern),
                            ItemStackTemplate.CODEC.fieldOf("result").forGetter(o -> o.result)
                    )
                    .apply(i, PiglinAwakenerRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, PiglinAwakenerRecipe> STREAM_CODEC = StreamCodec.composite(
            Recipe.CommonInfo.STREAM_CODEC,
            o -> o.commonInfo,
            CraftingRecipe.CraftingBookInfo.STREAM_CODEC,
            o -> o.bookInfo,
            ShapedRecipePattern.STREAM_CODEC,
            o -> o.pattern,
            ItemStackTemplate.STREAM_CODEC,
            o -> o.result,
            PiglinAwakenerRecipe::new
    );
    @SuppressWarnings("unchecked")
    public static final RecipeSerializer<ShapedRecipe> SERIALIZER =
            (RecipeSerializer<ShapedRecipe>) (RecipeSerializer<?>) new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
    protected final Recipe.CommonInfo commonInfo;
    protected final CraftingRecipe.CraftingBookInfo bookInfo;
    private final ShapedRecipePattern pattern;
    private final ItemStackTemplate result;

    public PiglinAwakenerRecipe(CommonInfo commonInfo, CraftingBookInfo bookInfo, ShapedRecipePattern pattern, ItemStackTemplate result) {
        super(commonInfo, bookInfo, pattern, result);
        this.commonInfo = commonInfo;
        this.bookInfo = bookInfo;
        this.pattern = pattern;
        this.result = result;
    }

    /**
     * Copies the item over as a placeholder for what item to drop if used on the wrong mode.
     */
    @Override
    public ItemStack assemble(CraftingInput input) {
        ItemStack center = input.getItem(CENTER_SLOT_3x3); // 4 is center slot

        DataComponentPatch.Builder dataComponentPatchBuilder = DataComponentPatch.builder();
        if (center.is(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)) {
            dataComponentPatchBuilder.set(ModDataComponentTypes.STORED_ITEMSTACK, center.copyWithCount(1));
        }

        return this.result.apply(dataComponentPatchBuilder.build());
    }

    @Override
    public RecipeSerializer<ShapedRecipe> getSerializer() {
        return SERIALIZER;
    }
}
