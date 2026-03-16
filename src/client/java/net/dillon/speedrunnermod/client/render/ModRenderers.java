package net.dillon.speedrunnermod.client.render;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.block.sign.TerraformSignBlockHelper;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.vehicle.boat.AbstractBoat;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Used to render special things.
 */
public class ModRenderers {

    /**
     * Registers block renderers.
     */
    private static void initializeBlockRenderers() {
        BlockRenderLayerMap.putBlock(ModBlocks.SPEEDRUNNER_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.DEAD_SPEEDRUNNER_SAPLING, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.SPEEDRUNNER_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.DEAD_SPEEDRUNNER_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.DOOM_LEAVES, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.WOODEN_SPEEDRUNNER_DOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.SPEEDRUNNER_DOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.SPEEDRUNNER_TRAPDOOR, ChunkSectionLayer.CUTOUT);
        BlockRenderLayerMap.putBlock(ModBlocks.DEAD_SPEEDRUNNER_BUSH, ChunkSectionLayer.CUTOUT);

        SpeedrunnerMod.debug("Initialized custom block models.");
    }

    /**
     * Registers other types of renderers.
     */
    private static void initializeOtherRenderers() {
        registerBoatRenderer(ModEntityTypes.SPEEDRUNNER_BOAT, boatModelLayer("speedrunner"), false);
        registerBoatRenderer(ModEntityTypes.FIREPROOF_SPEEDRUNNER_BOAT, boatModelLayer("fireproof/speedrunner"), false);
        registerBoatRenderer(ModEntityTypes.SPEEDRUNNER_CHEST_BOAT, chestBoatModelLayer("speedrunner"), true);
        registerBoatRenderer(ModEntityTypes.FIREPROOF_SPEEDRUNNER_CHEST_BOAT, chestBoatModelLayer("fireproof/speedrunner"), true);

        registerBoatRenderer(ModEntityTypes.DEAD_SPEEDRUNNER_BOAT, boatModelLayer("dead_speedrunner"), false);
        registerBoatRenderer(ModEntityTypes.DEAD_SPEEDRUNNER_CHEST_BOAT, chestBoatModelLayer("dead_speedrunner"), true);

        registerBoatRenderer(ModEntityTypes.CRIMSON_BOAT, boatModelLayer("crimson"), false);
        registerBoatRenderer(ModEntityTypes.FIREPROOF_CRIMSON_BOAT, boatModelLayer("fireproof/crimson"), false);
        registerBoatRenderer(ModEntityTypes.CRIMSON_CHEST_BOAT, chestBoatModelLayer("crimson"), true);
        registerBoatRenderer(ModEntityTypes.FIREPROOF_CRIMSON_CHEST_BOAT, chestBoatModelLayer("fireproof/crimson"), true);

        registerBoatRenderer(ModEntityTypes.WARPED_BOAT, boatModelLayer("warped"), false);
        registerBoatRenderer(ModEntityTypes.FIREPROOF_WARPED_BOAT, boatModelLayer("fireproof/warped"), false);
        registerBoatRenderer(ModEntityTypes.WARPED_CHEST_BOAT, chestBoatModelLayer("warped"), true);
        registerBoatRenderer(ModEntityTypes.FIREPROOF_WARPED_CHEST_BOAT, chestBoatModelLayer("fireproof/warped"), true);

        TerraformSignBlockHelper.registerDefaultWoodType(ofSpeedrunnerMod("speedrunner"));

        SpeedrunnerMod.debug("Initialized custom renderers.");
    }

    /**
     * Registers a boat renderer.
     */
    private static <T extends AbstractBoat> void registerBoatRenderer(EntityType<? extends T> entityType, ModelLayerLocation modelLayer, boolean chest) {
        registerEntityRenderer(
                entityType,
                modelLayer,
                chest ? BoatModel::createChestBoatModel : BoatModel::createBoatModel,
                context -> new BoatRenderer(context, modelLayer)
        );
    }

    /**
     * Registers entity renderers for the speedrunner mod boats.
     */
    private static <T extends Entity> void registerEntityRenderer(EntityType<? extends T> entityType, ModelLayerLocation modelLayer, EntityModelLayerRegistry.TexturedModelDataProvider texturedModelDataProvider, EntityRendererProvider<T> entityRendererFactory) {
        EntityModelLayerRegistry.registerModelLayer(modelLayer, texturedModelDataProvider);
        EntityRenderers.register(entityType, entityRendererFactory);
    }

    /**
     * @return the texture path for a {@code normal boat.}
     */
    private static ModelLayerLocation boatModelLayer(String id) {
        return new ModelLayerLocation(ofSpeedrunnerMod("boat/" + id), "main");
    }

    /**
     * @return the texture path for a {@code chest boat.}
     */
    private static ModelLayerLocation chestBoatModelLayer(String id) {
        return new ModelLayerLocation(ofSpeedrunnerMod("chest_boat/" + id), "main");
    }

    /**
     * Initializes all {@code Speedrunner Mod} renderers.
     */
    public static void initializeRenderers() {
        initializeBlockRenderers();
        initializeOtherRenderers();
    }
}