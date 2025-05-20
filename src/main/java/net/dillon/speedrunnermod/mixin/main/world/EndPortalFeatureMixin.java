package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.EndPortalFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(EndPortalFeature.class)
public class EndPortalFeatureMixin {

    /**
     * Generates {@code doom stone} around the main end portal (in the end) if {@code doom mode} is enabled.
     */
    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;END_STONE:Lnet/minecraft/block/Block;"))
    private Block changeBaseBlock() {
        return options().main.customDataGeneration && options().main.playingMode.doom() ? ModBlocks.DOOM_STONE : Blocks.END_STONE;
    }
}