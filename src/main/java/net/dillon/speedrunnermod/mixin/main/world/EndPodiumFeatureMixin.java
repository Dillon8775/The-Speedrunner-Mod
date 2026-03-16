package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.EndPodiumFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

@Mixin(EndPodiumFeature.class)
public class EndPodiumFeatureMixin {

    /**
     * Generates {@code doom stone} around the main end portal (in the end) if {@code doom mode} is enabled.
     */
    @Redirect(method = "place", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;END_STONE:Lnet/minecraft/world/level/block/Block;"))
    private Block changeEndPortalFeatureBaseBlock() {
        return options().main.customDataGeneration.getCurrentValue() && isDoomMode() ? ModBlocks.DOOM_STONE : Blocks.END_STONE;
    }
}