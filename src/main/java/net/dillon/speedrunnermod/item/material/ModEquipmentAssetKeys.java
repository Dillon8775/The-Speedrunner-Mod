package net.dillon.speedrunnermod.item.material;

import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.equipment.EquipmentAsset;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Equipment assets.
 */
public class ModEquipmentAssetKeys {
    private static final ResourceKey<? extends Registry<EquipmentAsset>> ROOT_ID = ResourceKey.createRegistryKey(Identifier.parse("equipment_asset"));
    public static final ResourceKey<EquipmentAsset> SPEEDRUNNER = createEquipmentAssetKey("speedrunner");
    public static final ResourceKey<EquipmentAsset> GOLDEN_SPEEDRUNNER = createEquipmentAssetKey("golden_speedrunner");
    public static final ResourceKey<EquipmentAsset> SPEEDRUNNER_HARDNESS = createEquipmentAssetKey("speedrunner_harness");
    public static final ResourceKey<EquipmentAsset> GOLDEN_SPEEDRUNNER_HARDNESS = createEquipmentAssetKey("golden_speedrunner_harness");

    /**
     * Creates a {@code equipment asset.}
     */
    private static ResourceKey<EquipmentAsset> createEquipmentAssetKey(String name) {
        return ResourceKey.create(ROOT_ID, ofSpeedrunnerMod(name));
    }
}