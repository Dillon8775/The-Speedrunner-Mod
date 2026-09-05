package net.dillon.speedrunnermod.mixin.structure;

import net.dillon.speedrunnermod.loot.ModChestLootTables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.structures.ShipwreckPieces;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

/**
 * Modifies vanilla shipwreck chest loot to use the speedrunner mod's loot table.
 */
@Mixin(ShipwreckPieces.class)
public class ShipwreckPiecesMixin {
    @Shadow @Final
    private static final Map<String, ResourceKey<LootTable>> MARKERS_TO_LOOT = Map.of(
            "map_chest", BuiltInLootTables.SHIPWRECK_MAP,
            "treasure_chest", ModChestLootTables.MC_SHIPWRECK_TREASURE,
            "supply_chest", ModChestLootTables.MC_SHIPWRECK_SUPPLY
    );
}