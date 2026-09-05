package net.dillon.speedrunnermod.mixin.structure;

import net.dillon.speedrunnermod.loot.ModChestLootTables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(StrongholdPieces.ChestCorridor.class)
public class StrongholdPiecesChestCorridorMixin {

    /**
     * Redirects stronghold corridors to use {@code corridor} loot table.
     */
    @ModifyArg(method = "postProcess", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/structures/StrongholdPieces$ChestCorridor;createChest(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Lnet/minecraft/util/RandomSource;IIILnet/minecraft/resources/ResourceKey;)Z"), index = 6)
    private ResourceKey<LootTable> redirectCorridorLoot(ResourceKey<LootTable> original) {
        return ModChestLootTables.MC_STRONGHOLD_CORRIDOR;
    }
}