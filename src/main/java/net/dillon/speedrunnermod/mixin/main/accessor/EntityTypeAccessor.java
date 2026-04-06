package net.dillon.speedrunnermod.mixin.main.accessor;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Supplier;

@Mixin(EntityType.class)
public interface EntityTypeAccessor {
    @Invoker("boatFactory")
    static EntityType.EntityFactory<Boat> invokeGetBoatFactory(Supplier<Item> boatItem) {
        throw new AssertionError();
    }
    @Invoker("chestBoatFactory")
    static EntityType.EntityFactory<ChestBoat> invokeGetChestBoatFactory(Supplier<Item> boatItem) {
        throw new AssertionError();
    }
}