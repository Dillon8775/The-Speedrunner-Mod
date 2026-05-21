package net.dillon.speedrunnermod.loot;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All custom speedrunner mod loot tables.
 */
public class ModLootTables {
    public static final ResourceKey<LootTable> DOOM_BLOCK_LOOT = ResourceKey.create(Registries.LOOT_TABLE, ofSpeedrunnerMod("world/block/doom_block"));
}