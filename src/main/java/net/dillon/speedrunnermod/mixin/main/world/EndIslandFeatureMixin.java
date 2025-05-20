package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.EndIslandFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Mixin(EndIslandFeature.class)
public class EndIslandFeatureMixin {

    /**
     * Makes {@code doom stone} generate as the base block of the {@code end islands} (found at the outer end islands) if {@code doom mode} is enabled.
     */
    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/block/Blocks;END_STONE:Lnet/minecraft/block/Block;"))
    private Block changeBaseBlock() {
        return options().main.customDataGeneration && options().main.playingMode.doom() ? ModBlocks.DOOM_STONE : Blocks.END_STONE;
    }
}