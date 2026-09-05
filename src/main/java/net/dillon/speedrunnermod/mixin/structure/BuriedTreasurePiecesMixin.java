package net.dillon.speedrunnermod.mixin.structure;

import net.dillon.speedrunnermod.loot.ModChestLootTables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.structures.BuriedTreasurePieces;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(BuriedTreasurePieces.BuriedTreasurePiece.class)
public class BuriedTreasurePiecesMixin {

    /**
     * Makes buried treasures use the speedrunner mod buried treasure loot.
     */
    @ModifyArg(method = "postProcess", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/structures/BuriedTreasurePieces$BuriedTreasurePiece;createChest(Lnet/minecraft/world/level/ServerLevelAccessor;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/block/state/BlockState;)Z"), index = 4)
    private ResourceKey<LootTable> redirectBuriedTreasureLoot(ResourceKey<LootTable> lootTable) {
        return ModChestLootTables.MC_BURIED_TREASURE;
    }
}