package net.dillon.speedrunnermod.util;

import net.dillon.speedrunnermod.mixin.main.accessor.AbstractBoatAccessor;
import net.dillon.speedrunnermod.mixin.main.accessor.EntityTypeAccessor;
import net.dillon.speedrunnermod.mixin.main.accessor.SmithingTemplateItemAccessor;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;
import net.minecraft.world.entity.vehicle.boat.Boat;
import net.minecraft.world.entity.vehicle.boat.ChestBoat;
import net.minecraft.world.item.Item;

import java.util.List;
import java.util.function.Supplier;

/**
 * Accessor utility class.
 */
public class AccessorUtil {

    public static EntityType.EntityFactory<Boat> registerBoatFactory(Supplier<Item> boatItem) {
        return EntityTypeAccessor.invokeGetBoatFactory(boatItem);
    }

    public static EntityType.EntityFactory<ChestBoat> registerChestBoatFactory(Supplier<Item> boatItem) {
        return EntityTypeAccessor.invokeGetChestBoatFactory(boatItem);
    }

    public static Supplier<Item> droppedItem(AbstractBoat boat) {
        return ((AbstractBoatAccessor) boat).getDroppedItem();
    }

    public static List<Identifier> upgradeIconList() {
        return SmithingTemplateItemAccessor.invokeNetheriteUpgradeIconList();
    }

    public static List<Identifier> upgradeMaterialList() {
        return SmithingTemplateItemAccessor.invokeNetheriteUpgradeMaterialList();
    }
}