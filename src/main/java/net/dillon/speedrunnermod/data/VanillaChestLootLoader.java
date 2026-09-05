package net.dillon.speedrunnermod.data;

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
public class VanillaChestLootLoader {

    /**
     * Modifies all structure loot paths.
     */
    private static void modifyStructures(String lootTable, CompoundTag blockNbt) {
        modifyStructure(BuiltInLootTables.ANCIENT_CITY, ModChestLootTables.MC_ANCIENT_CITY, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.BASTION_BRIDGE, ModChestLootTables.MC_BASTION_BRIDGE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.BASTION_HOGLIN_STABLE, ModChestLootTables.MC_BASTION_HOGLIN_STABLE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.BASTION_OTHER, ModChestLootTables.MC_BASTION_OTHER, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.BASTION_TREASURE, ModChestLootTables.MC_BASTION_TREASURE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.BURIED_TREASURE, ModChestLootTables.MC_BURIED_TREASURE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.DESERT_PYRAMID, ModChestLootTables.MC_DESERT_PYRAMID, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.IGLOO_CHEST, ModChestLootTables.MC_IGLOO_CHEST, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.JUNGLE_TEMPLE, ModChestLootTables.MC_JUNGLE_TEMPLE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.PILLAGER_OUTPOST, ModChestLootTables.MC_PILLAGER_OUTPOST, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.RUINED_PORTAL, ModChestLootTables.MC_RUINED_PORTAL, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.SIMPLE_DUNGEON, ModChestLootTables.MC_SIMPLE_DUNGEON, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.UNDERWATER_RUIN_BIG, ModChestLootTables.MC_UNDERWATER_RUIN_BIG, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.UNDERWATER_RUIN_SMALL, ModChestLootTables.MC_UNDERWATER_RUIN_SMALL, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.TRIAL_CHAMBERS_CORRIDOR, ModChestLootTables.MC_TRIAL_CHAMBERS_CORRIDOR, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.TRIAL_CHAMBERS_ENTRANCE, ModChestLootTables.MC_TRIAL_CHAMBERS_ENTRANCE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.TRIAL_CHAMBERS_INTERSECTION, ModChestLootTables.MC_TRIAL_CHAMBERS_INTERSECTION, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.TRIAL_CHAMBERS_INTERSECTION_BARREL, ModChestLootTables.MC_TRIAL_CHAMBERS_INTERSECTION_BARREL, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_COMMON, ModChestLootTables.MC_TRIAL_CHAMBERS_REWARD_OMINOUS_COMMON, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_RARE, ModChestLootTables.MC_TRIAL_CHAMBERS_REWARD_OMINOUS_RARE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE, ModChestLootTables.MC_TRIAL_CHAMBERS_REWARD_OMINOUS_UNIQUE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_RARE, ModChestLootTables.MC_TRIAL_CHAMBERS_REWARD_RARE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.TRIAL_CHAMBERS_REWARD_UNIQUE, ModChestLootTables.MC_TRIAL_CHAMBERS_REWARD_UNIQUE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.TRIAL_CHAMBERS_SUPPLY, ModChestLootTables.MC_TRIAL_CHAMBERS_SUPPLY, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_ARMORER, ModChestLootTables.MC_VILLAGE_ARMORER, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_BUTCHER, ModChestLootTables.MC_VILLAGE_BUTCHER, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_CARTOGRAPHER, ModChestLootTables.MC_VILLAGE_CARTOGRAPHER, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_DESERT_HOUSE, ModChestLootTables.MC_VILLAGE_DESERT_HOUSE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_FISHER, ModChestLootTables.MC_VILLAGE_FISHER, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_FLETCHER, ModChestLootTables.MC_VILLAGE_FLETCHER, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_MASON, ModChestLootTables.MC_VILLAGE_MASON, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_PLAINS_HOUSE, ModChestLootTables.MC_VILLAGE_PLAINS_HOUSE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_SAVANNA_HOUSE, ModChestLootTables.MC_VILLAGE_SAVANNA_HOUSE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_SHEPHERD, ModChestLootTables.MC_VILLAGE_SHEPHERD, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_SNOWY_HOUSE, ModChestLootTables.MC_VILLAGE_SNOWY_HOUSE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_TAIGA_HOUSE, ModChestLootTables.MC_VILLAGE_TAIGA_HOUSE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_TANNERY, ModChestLootTables.MC_VILLAGE_TANNERY, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_TEMPLE, ModChestLootTables.MC_VILLAGE_TEMPLE, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_TOOLSMITH, ModChestLootTables.MC_VILLAGE_TOOLSMITH, lootTable, blockNbt);
        modifyStructure(BuiltInLootTables.VILLAGE_WEAPONSMITH, ModChestLootTables.MC_VILLAGE_WEAPONSMITH, lootTable, blockNbt);
    }

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