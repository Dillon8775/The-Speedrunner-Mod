package net.dillon.speedrunnermod.mixin.accessor;

import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.function.Supplier;

@Mixin(AbstractBoat.class)
public interface AbstractBoatAccessor {
    @Accessor("dropItem")
    Supplier<Item> getDroppedItem();
}