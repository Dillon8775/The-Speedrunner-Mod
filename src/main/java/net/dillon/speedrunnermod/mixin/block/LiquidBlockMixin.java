package net.dillon.speedrunnermod.mixin.block;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LiquidBlock.class)
public class LiquidBlockMixin {

    /**
     * Makes water replace lava with basalt instead of obsidian when in any nether biome.
     */
    @Redirect(method = "shouldSpreadLiquid", at = @At(value = "FIELD", target = "Lnet/minecraft/world/level/block/Blocks;OBSIDIAN:Lnet/minecraft/world/level/block/Block;"))
    private Block replaceLavaWithBasaltFromWaterInNether(Level world, BlockPos pos, BlockState state) {
        return world.getBiome(pos).is(BiomeTags.IS_NETHER) ? Blocks.BASALT : Blocks.OBSIDIAN;
    }
}