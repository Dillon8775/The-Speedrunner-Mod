package net.dillon.speedrunnermod.item.equipment;

import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Equipment assets.
 */
@Author(Authors.BLOCKLEGEND001)
public class ModEquipmentAssetKeys {
    private static final ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(Identifier.parse("equipment_asset"));
    public static final ResourceKey<EquipmentAsset> SPEEDRUNNER = of("speedrunner");
    public static final ResourceKey<EquipmentAsset> GOLDEN_SPEEDRUNNER = of("golden_speedrunner");
    public static final ResourceKey<EquipmentAsset> SPEEDRUNNER_HARDNESS = of("speedrunner_harness");
    public static final ResourceKey<EquipmentAsset> GOLDEN_SPEEDRUNNER_HARDNESS = of("golden_speedrunner_harness");

    private static ResourceKey<EquipmentAsset> of(String name) {
        return ResourceKey.create(ROOT_ID, ofSpeedrunnerMod(name));
    }
}