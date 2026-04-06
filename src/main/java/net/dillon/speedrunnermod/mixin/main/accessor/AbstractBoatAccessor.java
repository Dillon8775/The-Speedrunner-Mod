package net.dillon.speedrunnermod.mixin.main.accessor;

import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

@Mixin(AbstractBoatAccessor.class)
public interface AbstractBoatAccessor {
    @Accessor("dropItem")
    Supplier<Item> getDroppedItem();
}