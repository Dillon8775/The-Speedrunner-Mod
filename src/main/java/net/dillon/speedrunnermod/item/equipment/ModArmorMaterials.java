package net.dillon.speedrunnermod.item.equipment;

import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Util;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;

/**
 * All Speedrunner Mod {@code armor materials} (for helmets, chestplates, leggings, and boots).
 */
public interface ModArmorMaterials {
    ArmorMaterial SPEEDRUNNER = new ArmorMaterial(30, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.HELMET, 2);
        map.put(ArmorType.CHESTPLATE, 7);
        map.put(ArmorType.LEGGINGS, 6);
        map.put(ArmorType.BOOTS, 2);
        map.put(ArmorType.BODY, 8);
    }), 16, SoundEvents.ARMOR_EQUIP_IRON, 1.0F, 0.0F, ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModEquipmentAssetKeys.SPEEDRUNNER);
    ArmorMaterial GOLDEN_SPEEDRUNNER = new ArmorMaterial(11, Util.make(new EnumMap<>(ArmorType.class), map -> {
        map.put(ArmorType.HELMET, 2);
        map.put(ArmorType.CHESTPLATE, 6);
        map.put(ArmorType.LEGGINGS, 4);
        map.put(ArmorType.BOOTS, 2);
        map.put(ArmorType.BODY, 8);
    }), 27, SoundEvents.ARMOR_EQUIP_GOLD, 0.5F, 0.0F, ModItemTags.GOLDEN_SPEEDRUNNER_TOOL_MATERIALS, ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER);
}