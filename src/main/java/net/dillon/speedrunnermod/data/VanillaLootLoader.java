package net.dillon.speedrunnermod.data;

import net.dillon.dillonlib.platform.Platforms;
import net.dillon.speedrunnermod.loot.ModChestLootTables;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;

/**
 * Contains all {@code vanilla loot table path modifications}, making generating data way easier.
 */
public class VanillaLootLoader {

    /**
     * Modifies all vanilla loot table paths.
     */
    public static void modifyVanillaLootPaths(CompoundTag structure) {
        structure.getList("blocks").ifPresent(blocks -> {
            for (Tag tag : blocks) {
                if (!(tag instanceof CompoundTag block)) {
                    continue;
                }

                block.getCompound("nbt").ifPresent(blockNbt -> {
                    String lootTable = blockNbt
                            .getString("LootTable")
                            .orElse("");

                    modifyStructures(lootTable, blockNbt);
                });
            }
        });
    }

    /**
     * Modifies all structure loot paths.
     */
    private static void modifyStructures(String lootTable, CompoundTag blockNbt) {
        modifyStructure(BuiltInLootTables.RUINED_PORTAL, ModChestLootTables.RUINED_PORTAL, lootTable, blockNbt);
    }

    /**
     * Modifies and replaces vanilla loot path with the speedrunner mod loot paths.
     */
    private static void modifyStructure(ResourceKey<LootTable> key, ResourceKey<LootTable> newKey, String lootTable, CompoundTag blockNbt) {
        String keyString = keyAsString(key);
        String newKeyString = keyAsString(newKey);

        if (lootTable.equals(keyString)) {
            blockNbt.putString(
                    "LootTable",
                    newKeyString
            );

            SpeedrunnerMod.LOGGER.info("Modified {} loot table. (new key={})", keyString, newKeyString);
        }
    }

    /**
     * @return a {@link ResourceKey} as a string with its identifier.
     */
    private static String keyAsString(ResourceKey<LootTable> key) {
        return key.identifier().toString();
    }
}