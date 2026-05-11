package net.dillon.speedrunnermod.recipe;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.dillon.speedrunnermod.item.ModItems;
import net.minecraft.core.component.DataComponentPatch;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.CraftingInput;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;

/**
 * The recipe for upgrading inventory preservers.
 */
public class InventoryPreserverRecipe extends CustomRecipe {
    public static final MapCodec<InventoryPreserverRecipe> MAP_CODEC = RecordCodecBuilder.mapCodec(
            i -> i.group(
                            ItemStackTemplate.CODEC.fieldOf("result").forGetter(o -> o.result)
                    )
                    .apply(i, InventoryPreserverRecipe::new)
    );
    public static final StreamCodec<RegistryFriendlyByteBuf, InventoryPreserverRecipe> STREAM_CODEC = StreamCodec.composite(
            ItemStackTemplate.STREAM_CODEC,
            o -> o.result,
            InventoryPreserverRecipe::new
    );
    public static final RecipeSerializer<InventoryPreserverRecipe> SERIALIZER = new RecipeSerializer<>(MAP_CODEC, STREAM_CODEC);
    private final ItemStackTemplate result;

    public InventoryPreserverRecipe(final ItemStackTemplate result) {
        this.result = result;
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
        DataComponentPatch.Builder dataComponentPatch = DataComponentPatch.builder();

        for (ItemStack stack : input.items()) {

            if (stack.is(ModItems.INVENTORY_PRESERVER)) {
                int newMaxLevel = stack.getOrDefault(DataComponents.MAX_DAMAGE, 1) + 1;
                dataComponentPatch.set(DataComponents.MAX_DAMAGE, newMaxLevel);
                if (stack.get(DataComponents.MAX_DAMAGE) == 2 && stack.getOrDefault(DataComponents.DAMAGE, 0) == 1) {
                    dataComponentPatch.set(DataComponents.DAMAGE, 1);
                }
                dataComponentPatch.set(
                        DataComponents.ITEM_NAME,
                        newMaxLevel == 2
                                ? Component.translatable("item.speedrunnermod.strong_inventory_preserver")
                                : Component.translatable("item.speedrunnermod.resistant_inventory_preserver")
                );
                break;
            }
        }

        return this.result.apply(dataComponentPatch.build());
    }

    @Override
    public RecipeSerializer<InventoryPreserverRecipe> getSerializer() {
        return SERIALIZER;
    }
}