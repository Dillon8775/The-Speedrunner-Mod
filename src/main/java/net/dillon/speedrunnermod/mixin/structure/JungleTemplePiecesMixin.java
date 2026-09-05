package net.dillon.speedrunnermod.mixin.structure;

import net.dillon.speedrunnermod.loot.ModChestLootTables;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.structure.structures.JungleTemplePiece;
import net.minecraft.world.level.storage.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(JungleTemplePiece.class)
public class JungleTemplePiecesMixin {

    /**
     * Makes jungle temples use the speedrunner mod end jungle temple loot.
     */
    @ModifyArg(method = "postProcess", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/structures/JungleTemplePiece;createChest(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Lnet/minecraft/util/RandomSource;IIILnet/minecraft/resources/ResourceKey;)Z", ordinal = 0), index = 6)
    private ResourceKey<LootTable> redirectJungleTempleLoot(ResourceKey<LootTable> lootTable) {
        return ModChestLootTables.MC_JUNGLE_TEMPLE;
    }

    /**
     * Makes jungle temples use the speedrunner mod end jungle temple loot.
     */
    @ModifyArg(method = "postProcess", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/levelgen/structure/structures/JungleTemplePiece;createChest(Lnet/minecraft/world/level/WorldGenLevel;Lnet/minecraft/world/level/levelgen/structure/BoundingBox;Lnet/minecraft/util/RandomSource;IIILnet/minecraft/resources/ResourceKey;)Z", ordinal = 1), index = 6)
    private ResourceKey<LootTable> redirectJungleTempleLootAgain(ResourceKey<LootTable> lootTable) {
        return ModChestLootTables.MC_JUNGLE_TEMPLE;
    }
}