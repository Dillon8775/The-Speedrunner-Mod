package net.dillon.speedrunnermod.mixin.structure;

import net.dillon.speedrunnermod.loot.ModChestLootTables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.structures.IglooPieces;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(IglooPieces.IglooPiece.class)
public class IglooPiecesMixin {

    /**
     * Makes igloos use the speedrunner mod end igloo chest loot.
     */
    @ModifyArg(method = "handleDataMarker", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/ChestBlockEntity;setLootTable(Lnet/minecraft/resources/ResourceKey;J)V"), index = 0)
    private ResourceKey<LootTable> redirectIglooLoot(ResourceKey<LootTable> lootTable) {
        return ModChestLootTables.MC_IGLOO_CHEST;
    }
}