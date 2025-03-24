package net.dillon.speedrunnermod.client.render;

import com.terraformersmc.terraform.boat.api.client.TerraformBoatClientHelper;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.entity.ModBoats;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;

/**
 * Used to render special things.
 */
@Environment(EnvType.CLIENT)
public class ModRenderers {

    /**
     * Registers block renderers.
     */
    private static void initializeBlockRenderers() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPEEDRUNNER_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEAD_SPEEDRUNNER_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPEEDRUNNER_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEAD_SPEEDRUNNER_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DOOM_LEAVES, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_SPEEDRUNNER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPEEDRUNNER_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SPEEDRUNNER_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.DEAD_SPEEDRUNNER_BUSH, RenderLayer.getCutout());

        info("Initialized custom block models.");
    }

    /**
     * Registers other types of renderers.
     */
    private static void initializeOtherRenderers() {
        TerraformBoatClientHelper.registerModelLayers(ModBoats.SPEEDRUNNER);
        TerraformBoatClientHelper.registerModelLayers(ModBoats.DEAD_SPEEDRUNNER);
        TerraformBoatClientHelper.registerModelLayers(ModBoats.CRIMSON);
        TerraformBoatClientHelper.registerModelLayers(ModBoats.WARPED);

        info("Initialized custom renderers.");
    }

    /**
     * Initializes all {@code Speedrunner Mod} renderers.
     */
    public static void initializeRenderers() {
        initializeBlockRenderers();
        initializeOtherRenderers();
    }
}