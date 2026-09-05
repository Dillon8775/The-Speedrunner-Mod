package net.dillon.speedrunnermod.mixin.structure;

import net.dillon.speedrunnermod.loot.ModChestLootTables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.structures.StrongholdPieces;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(StrongholdPieces.RoomCrossing.class)
public class StrongholdPiecesRoomCrossingMixin {

    /**
     * Redirects stronghold room crossings to use {@code crossing} loot table.
     */
    @ModifyArg(method = "postProcess", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/structures/StrongholdPieces$RoomCrossing;createChest(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Lnet/minecraft/util/RandomSource;IIILnet/minecraft/resources/ResourceKey;)Z"), index = 6)
    private ResourceKey<LootTable> redirectCrossingLoot(ResourceKey<LootTable> original) {
        return ModChestLootTables.MC_STRONGHOLD_CROSSING;
    }
}