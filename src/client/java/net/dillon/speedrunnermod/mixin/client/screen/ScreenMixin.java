package net.dillon.speedrunnermod.mixin.client.screen;

import net.minecraft.client.gui.screen.Screen;
import org.spongepowered.asm.mixin.Mixin;

/**
 * Applies the custom panorama.
 */
@Mixin(Screen.class)
public class ScreenMixin {
//    @Shadow @Final @Mutable
//    public static RotatingCubeMapRenderer ROTATING_PANORAMA_RENDERER;
//    @Shadow @Final
//    public static CubeMapRenderer PANORAMA_RENDERER;
//
//    static {
//        if (options().client.customPanorama) {
//            ROTATING_PANORAMA_RENDERER = new RotatingCubeMapRenderer(new CubeMapRenderer(ofSpeedrunnerMod("textures/gui/title/background/panorama")));
//        } else {
//            ROTATING_PANORAMA_RENDERER = new RotatingCubeMapRenderer(PANORAMA_RENDERER);
//        }
//    }
}