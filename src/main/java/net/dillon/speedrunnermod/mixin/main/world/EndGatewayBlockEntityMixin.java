package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.EndGatewayBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(EndGatewayBlockEntity.class)
public class EndGatewayBlockEntityMixin {

    /**
     * Generates {@code doom stone} instead of end stone around the {@code end gateway} world feature if {@code doom mode} is enabled.
     */
    @Redirect(method = "findPortalPosition", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;END_STONE:Lnet/minecraft/block/Block;"))
    private static Block changeBaseBlock() {
        return options().main.playingMode.doom() ? ModBlocks.DOOM_STONE : Blocks.END_STONE;
    }
}