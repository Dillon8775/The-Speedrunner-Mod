package net.dillon.speedrunnermod.mixin.block.nether_portal;

import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

@Mixin(BaseFireBlock.class)
public class BaseFireBlockMixin {

    /**
     * @author Dillon8775
     * @reason Allows nether portals to be built in all dimensions if {@code Global Nether Portals} is on.
     */
    @Overwrite
    private static boolean inPortalDimension(Level world) {
        if (common().worldGen.globalNetherPortals.getCurrentValue()) {
            return world.dimension() == Level.OVERWORLD || world.dimension() == Level.NETHER || world.dimension() == Level.END;
        } else {
            return world.dimension() == Level.OVERWORLD || world.dimension() == Level.NETHER;
        }
    }
}