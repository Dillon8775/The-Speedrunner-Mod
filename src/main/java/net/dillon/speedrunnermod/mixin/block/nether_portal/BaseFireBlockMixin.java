package net.dillon.speedrunnermod.mixin.block.nether_portal;

import net.dillon.speedrunnermod.tag.ModBlockTags;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

@Mixin(BaseFireBlock.class)
public class BaseFireBlockMixin {

    /**
     * Returns {@code nether portal base blocks} (crying obsidian is included in this tag).
     */
    @Redirect(method = "isPortal", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z"))
    private static boolean newNetherPortalBaseBlocks(BlockState state, Object o) {
        return state.is(ModBlockTags.NETHER_PORTAL_BASE_BLOCKS);
    }

    /**
     * @author Dillon8775
     * @reason Allows nether portals to be built in all dimensions if {@code Global Nether Portals} is on.
     */
    @Overwrite
    private static boolean inPortalDimension(Level world) {
        if (options().worldGen.globalNetherPortals.getCurrentValue()) {
            return world.dimension() == Level.OVERWORLD || world.dimension() == Level.NETHER || world.dimension() == Level.END;
        } else {
            return world.dimension() == Level.OVERWORLD || world.dimension() == Level.NETHER;
        }
    }
}