package net.dillon.speedrunnermod.mixin.client.fix;

import com.google.common.collect.Maps;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.util.Util;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Map;

@Mixin(ItemBlockRenderTypes.class)
public class ItemBlockRenderTypesMixin {

    /**
     * Gives lava a render layer, similar to the built-in water render layer.
     */
    @Unique
    private static final Map<Fluid, ChunkSectionLayer> LAVA = Util.make(Maps.newHashMap(), hashMap -> {
        ChunkSectionLayer renderLayer = ChunkSectionLayer.TRANSLUCENT;
        hashMap.put(Fluids.FLOWING_LAVA, renderLayer);
        hashMap.put(Fluids.LAVA, renderLayer);
    });

    /**
     * Allows modded boats to render correctly when in lava.
     */
    @Inject(method = "getRenderLayer", at = @At("RETURN"), cancellable = true)
    private static void registerLavaBoatRenderer(FluidState state, CallbackInfoReturnable<ChunkSectionLayer> cir) {
        ChunkSectionLayer lavaRenderLayer = LAVA.get(state.getType());
        if (lavaRenderLayer != null) {
            cir.setReturnValue(lavaRenderLayer);
        }
    }
}