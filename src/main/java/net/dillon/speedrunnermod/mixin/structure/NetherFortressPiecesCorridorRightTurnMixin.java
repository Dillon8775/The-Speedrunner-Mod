package net.dillon.speedrunnermod.mixin.structure;

import net.dillon.speedrunnermod.loot.ModChestLootTables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.structures.NetherFortressPieces;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(NetherFortressPieces.CastleSmallCorridorRightTurnPiece.class)
public class NetherFortressPiecesCorridorRightTurnMixin {

    /**
     * Redirects nether fortress corridor right turns to use {@code nether bridge} loot table.
     */
    @ModifyArg(method = "postProcess", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/structures/NetherFortressPieces$CastleSmallCorridorRightTurnPiece;createChest(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Lnet/minecraft/util/RandomSource;IIILnet/minecraft/resources/ResourceKey;)Z"), index = 6)
    private ResourceKey<LootTable> redirectNetherBridgeRightTurnLoot(ResourceKey<LootTable> original) {
        return ModChestLootTables.MC_NETHER_BRIDGE;
    }
}