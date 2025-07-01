package net.dillon.speedrunnermod.mixin.main.world;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.surfacebuilder.MaterialRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import terrablender.worldgen.TBSurfaceRuleData;

import static net.dillon.speedrunnermod.option.ModOptions.isPlayingModeDoom;

@Mixin(TBSurfaceRuleData.class)
public class TBSurfaceRuleDataMixin {

    /**
     * @author Dillon8775
     * @reason Modifies the basic building blocks of the end, and changes it to doom stone if {@code doom mode} is enabled.
     */
    @Overwrite
    public static MaterialRules.MaterialRule end() {
        return isPlayingModeDoom() ? MaterialRules.block(ModBlocks.DOOM_STONE.getDefaultState()) : MaterialRules.block(Blocks.END_STONE.getDefaultState());
    }
}