package net.dillon.speedrunnermod.item.equipment;

import net.dillon.speedrunnermod.util.Author;
import net.dillon.speedrunnermod.util.Authors;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Equipment assets.
 */
@Author(Authors.BLOCKLEGEND001)
public class ModEquipmentAssetKeys {
    private static final RegistryKey<? extends Registry<EquipmentAsset>> ROOT_ID = RegistryKey.ofRegistry(Identifier.of("equipment_asset"));
    public static final RegistryKey<EquipmentAsset> SPEEDRUNNER = of("speedrunner");
    public static final RegistryKey<EquipmentAsset> GOLDEN_SPEEDRUNNER = of("golden_speedrunner");

    private static RegistryKey<EquipmentAsset> of(String name) {
        return RegistryKey.of(ROOT_ID, ofSpeedrunnerMod(name));
    }
}