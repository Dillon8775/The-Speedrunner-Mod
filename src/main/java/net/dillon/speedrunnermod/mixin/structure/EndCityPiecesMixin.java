package net.dillon.speedrunnermod.mixin.structure;

import net.dillon.speedrunnermod.loot.ModChestLootTables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.structures.EndCityPieces;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(EndCityPieces.EndCityPiece.class)
public class EndCityPiecesMixin {

    /**
     * Makes end cities use the speedrunner mod end city chest loot.
     */
    @ModifyArg(method = "handleDataMarker", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/RandomizableContainer;setBlockEntityLootTable(Lnet/minecraft/world/level/BlockGetter;Lnet/minecraft/util/RandomSource;Lnet/minecraft/core/BlockPos;Lnet/minecraft/resources/ResourceKey;)V"), index = 3)
    private ResourceKey<LootTable> redirectEndCityLoot(ResourceKey<LootTable> lootTable) {
        return ModChestLootTables.MC_END_CITY_TREASURE;
    }
}