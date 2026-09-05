package net.dillon.speedrunnermod.mixin.structure;

import net.dillon.speedrunnermod.loot.ModChestLootTables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.structures.MineshaftPieces;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(MineshaftPieces.MineShaftCorridor.class)
public class MineshaftPiecesMineShaftCorridorMixin {

    /**
     * Makes mineshafts use the speedrunner mod mineshaft chest loot.
     */
    @ModifyArg(method = "postProcess", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/structures/MineshaftPieces$MineShaftCorridor;createChest(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Lnet/minecraft/util/RandomSource;IIILnet/minecraft/resources/ResourceKey;)Z", ordinal = 0), index = 6)
    private ResourceKey<LootTable> redirectMineshaftLoot(ResourceKey<LootTable> lootTable) {
        return ModChestLootTables.MC_ABANDONED_MINESHAFT;
    }

    /**
     * Makes mineshafts use the speedrunner mod mineshaft chest loot.
     */
    @ModifyArg(method = "postProcess", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/structures/MineshaftPieces$MineShaftCorridor;createChest(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Lnet/minecraft/util/RandomSource;IIILnet/minecraft/resources/ResourceKey;)Z", ordinal = 1), index = 6)
    private ResourceKey<LootTable> redirectMineshaftLootAgain(ResourceKey<LootTable> lootTable) {
        return ModChestLootTables.MC_ABANDONED_MINESHAFT;
    }
}