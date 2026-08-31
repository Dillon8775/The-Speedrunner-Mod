package net.dillon.speedrunnermod.mixin.world;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.EndIslandFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.option.ModCommonOptions.isDoomMode;

@Mixin(EndIslandFeature.class)
public class EndIslandFeatureMixin {

    /**
     * Makes {@code doom stone} generate as the base block of the {@code end islands} (found at the outer end islands) if {@code doom mode} is enabled.
     */
    @Redirect(method = "place", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;END_STONE:Lnet/minecraft/world/level/block/Block;"))
    private Block changeEndIslandBaseBlock() {
        return isDoomMode() ? ModBlocks.DOOM_STONE : Blocks.END_STONE;
    }
}