package net.dillon.speedrunnermod.mixin.client.fix;

import com.google.common.collect.Maps;
import net.minecraft.client.render.BlockRenderLayer;
import net.minecraft.client.render.BlockRenderLayers;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Util;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(BlockRenderLayers.class)
public class BlockRenderLayersMixin {

    /**
     * Gives lava a render layer, similar to the built-in water render layer.
     */
    @Unique
    private static final Map<Fluid, BlockRenderLayer> LAVA = Util.make(Maps.newHashMap(), hashMap -> {
        BlockRenderLayer renderLayer = BlockRenderLayer.TRANSLUCENT;
        hashMap.put(Fluids.FLOWING_LAVA, renderLayer);
        hashMap.put(Fluids.LAVA, renderLayer);
    });

    /**
     * Allows modded boats to render correctly when in lava.
     */
    @Inject(method = "getFluidLayer", at = @At("RETURN"), cancellable = true)
    private static void registerLavaBoatRenderer(FluidState state, CallbackInfoReturnable<BlockRenderLayer> cir) {
        BlockRenderLayer lavaRenderLayer = LAVA.get(state.getFluid());
        if (lavaRenderLayer != null) {
            cir.setReturnValue(lavaRenderLayer);
        }
    }
}