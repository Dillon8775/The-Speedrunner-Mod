package net.dillon.speedrunnermod.mixin.accessor;

import net.minecraft.references.BlockItemId;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.BiFunction;
import java.util.function.Function;

@Mixin(Items.class)
public interface ItemsInvoker {
    @Invoker("registerItem")
    static Item registerModItem(final ResourceKey<Item> key, final Function<Item.Properties, Item> itemFactory) {
        throw new AssertionError();
    }
    @Invoker("registerItem")
    static Item registerModItem(final ResourceKey<Item> key, final Function<Item.Properties, Item> itemFactory, final Item.Properties properties) {
        throw new AssertionError();
    }
    @Invoker("registerBlock")
    static Item registerModBlock(final BlockItemId id, final Block block) {
        throw new AssertionError();
    }
    @Invoker("registerBlock")
    static Item registerModBlock(final BlockItemId id, final Block block, final BiFunction<Block, Item.Properties, Item> itemFactory) {
        throw new AssertionError();
    }
    @Invoker("registerBlock")
    static Item registerModBlock(final BlockItemId id, final Block block, final BiFunction<Block, Item.Properties, Item> itemFactory, final Item.Properties properties) {
        throw new AssertionError();
    }
}