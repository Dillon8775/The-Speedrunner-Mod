package net.dillon.speedrunnermod.mixin.accessor;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.function.Supplier;

@Mixin(EntityTypes.class)
public interface EntityTypesInvoker {
    @Invoker("boatFactory")
    static EntityType.EntityFactory<Boat> invokeBoatFactory(Supplier<Item> boatItem) {
        throw new AssertionError();
    }
    @Invoker("chestBoatFactory")
    static EntityType.EntityFactory<ChestBoat> invokeChestBoatFactory(Supplier<Item> boatItem) {
        throw new AssertionError();
    }
}