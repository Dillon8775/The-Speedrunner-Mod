package net.dillon.speedrunnermod.client.render;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.BoatEntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.model.BoatEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.vehicle.AbstractBoatEntity;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

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
        registerBoatRenderer(ModEntityTypes.SPEEDRUNNER_BOAT, boatModelLayer("speedrunner"), false);
        registerBoatRenderer(ModEntityTypes.SPEEDRUNNER_CHEST_BOAT, chestBoatModelLayer("speedrunner"), true);

        registerBoatRenderer(ModEntityTypes.DEAD_SPEEDRUNNER_BOAT, boatModelLayer("dead_speedrunner"), false);
        registerBoatRenderer(ModEntityTypes.DEAD_SPEEDRUNNER_CHEST_BOAT, chestBoatModelLayer("dead_speedrunner"), true);

        registerBoatRenderer(ModEntityTypes.CRIMSON_BOAT, boatModelLayer("crimson"), false);
        registerBoatRenderer(ModEntityTypes.CRIMSON_CHEST_BOAT, chestBoatModelLayer("crimson"), true);

        registerBoatRenderer(ModEntityTypes.WARPED_BOAT, boatModelLayer("warped"), false);
        registerBoatRenderer(ModEntityTypes.WARPED_CHEST_BOAT, chestBoatModelLayer("warped"), true);

        info("Initialized custom renderers.");
    }

    /**
     * Registers a boat renderer.
     */
    private static <T extends AbstractBoatEntity> void registerBoatRenderer(EntityType<? extends T> entityType, EntityModelLayer modelLayer, boolean chest) {
        registerEntityRenderer(
                entityType,
                modelLayer,
                chest ? BoatEntityModel::getChestTexturedModelData : BoatEntityModel::getTexturedModelData,
                context -> new BoatEntityRenderer(context, modelLayer)
        );
    }

    /**
     * Registers entity renderers for the speedrunner mod boats.
     */
    private static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> entityType, EntityModelLayer modelLayer, EntityModelLayerRegistry.TexturedModelDataProvider texturedModelDataProvider, EntityRendererFactory<T> entityRendererFactory) {
        EntityModelLayerRegistry.registerModelLayer(modelLayer, texturedModelDataProvider);
        EntityRendererRegistry.register(entityType, entityRendererFactory);
    }

    /**
     * @return the texture path for a normal boat.
     */
    private static EntityModelLayer boatModelLayer(String id) {
        return new EntityModelLayer(ofSpeedrunnerMod("boat/" + id), "main");
    }

    /**
     * Return the texture path for a chest boat.
     */
    private static EntityModelLayer chestBoatModelLayer(String id) {
        return new EntityModelLayer(ofSpeedrunnerMod("chest_boat/" + id), "main");
    }

    /**
     * Initializes all {@code Speedrunner Mod} renderers.
     */
    public static void initializeRenderers() {
        initializeBlockRenderers();
        initializeOtherRenderers();
    }
}