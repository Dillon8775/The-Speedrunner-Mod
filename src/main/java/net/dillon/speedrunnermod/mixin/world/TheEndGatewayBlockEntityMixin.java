package net.dillon.speedrunnermod.mixin.world;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.TheEndGatewayBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(TheEndGatewayBlockEntity.class)
public class TheEndGatewayBlockEntityMixin {

    /**
     * Generates {@code doom stone} instead of end stone around the {@code end gateway} world feature if {@code doom mode} is enabled.
     */
    @Redirect(method = "findValidSpawnInChunk", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;END_STONE:Lnet/minecraft/world/level/block/Block;"))
    private static Block changeEndGatewayBaseBlock() {
        return isDoomMode() ? ModBlocks.DOOM_STONE : Blocks.END_STONE;
    }
}