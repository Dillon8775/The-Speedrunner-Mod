package net.dillon.speedrunnermod.render;

import net.dillon.speedrunnermod.block.sign.TerraformSignBlockHelper;
import net.dillon.speedrunnermod.entity.ModEntityTypes;
import net.fabricmc.fabric.api.client.rendering.v1.ModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.object.boat.BoatModel;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Used to render special things.
 */
public class ModRenderers {
    private static final ModelLayerLocation SPEEDRUNNER = boatModelLayer("speedrunner");
    private static final ModelLayerLocation DEAD_SPEEDRUNNER = boatModelLayer("dead_speedrunner");
    private static final ModelLayerLocation CRIMSON = boatModelLayer("crimson");
    private static final ModelLayerLocation WARPED = boatModelLayer("warped");

    private static final ModelLayerLocation SPEEDRUNNER_CHEST = chestBoatModelLayer("speedrunner");
    private static final ModelLayerLocation DEAD_SPEEDRUNNER_CHEST = chestBoatModelLayer("dead_speedrunner");
    private static final ModelLayerLocation CRIMSON_CHEST = chestBoatModelLayer("crimson");
    private static final ModelLayerLocation WARPED_CHEST = chestBoatModelLayer("warped");

    private static final ModelLayerLocation FIREPROOF_SPEEDRUNNER = boatModelLayer("fireproof/speedrunner");
    private static final ModelLayerLocation FIREPROOF_SPEEDRUNNER_CHEST = chestBoatModelLayer("fireproof/speedrunner");
    private static final ModelLayerLocation FIREPROOF_CRIMSON = boatModelLayer("fireproof/crimson");
    private static final ModelLayerLocation FIREPROOF_CRIMSON_CHEST = chestBoatModelLayer("fireproof/crimson");
    private static final ModelLayerLocation FIREPROOF_WARPED = boatModelLayer("fireproof/warped");
    private static final ModelLayerLocation FIREPROOF_WARPED_CHEST = chestBoatModelLayer("fireproof/warped");

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
     * Registers all {@code Speedrunner Mod} entity renderers.
     */
    private static void registerEntityRenderers() {
        EntityRenderers.register(ModEntityTypes.SPEEDRUNNER_BOAT, context -> new BoatRenderer(context, SPEEDRUNNER));
        EntityRenderers.register(ModEntityTypes.DEAD_SPEEDRUNNER_BOAT, context -> new BoatRenderer(context, DEAD_SPEEDRUNNER));
        EntityRenderers.register(ModEntityTypes.CRIMSON_BOAT, context -> new BoatRenderer(context, CRIMSON));
        EntityRenderers.register(ModEntityTypes.WARPED_BOAT, context -> new BoatRenderer(context, WARPED));

        EntityRenderers.register(ModEntityTypes.SPEEDRUNNER_CHEST_BOAT, context -> new BoatRenderer(context, SPEEDRUNNER_CHEST));
        EntityRenderers.register(ModEntityTypes.DEAD_SPEEDRUNNER_CHEST_BOAT, context -> new BoatRenderer(context, DEAD_SPEEDRUNNER_CHEST));
        EntityRenderers.register(ModEntityTypes.CRIMSON_CHEST_BOAT, context -> new BoatRenderer(context, CRIMSON_CHEST));
        EntityRenderers.register(ModEntityTypes.WARPED_CHEST_BOAT, context -> new BoatRenderer(context, WARPED_CHEST));

        EntityRenderers.register(ModEntityTypes.FIREPROOF_SPEEDRUNNER_BOAT, context -> new BoatRenderer(context, FIREPROOF_SPEEDRUNNER));
        EntityRenderers.register(ModEntityTypes.FIREPROOF_SPEEDRUNNER_CHEST_BOAT, context -> new BoatRenderer(context, FIREPROOF_SPEEDRUNNER_CHEST));
        EntityRenderers.register(ModEntityTypes.FIREPROOF_CRIMSON_BOAT, context -> new BoatRenderer(context, FIREPROOF_CRIMSON));
        EntityRenderers.register(ModEntityTypes.FIREPROOF_CRIMSON_CHEST_BOAT, context -> new BoatRenderer(context, FIREPROOF_CRIMSON_CHEST));
        EntityRenderers.register(ModEntityTypes.FIREPROOF_WARPED_BOAT, context -> new BoatRenderer(context, FIREPROOF_WARPED));
        EntityRenderers.register(ModEntityTypes.FIREPROOF_WARPED_CHEST_BOAT, context -> new BoatRenderer(context, FIREPROOF_WARPED_CHEST));
    }

    /**
     * Registers all boat model layers.
     */
    private static void registerModelLayers() {
        ModelLayerRegistry.registerModelLayer(SPEEDRUNNER, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(DEAD_SPEEDRUNNER, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(CRIMSON, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(WARPED, BoatModel::createBoatModel);

        ModelLayerRegistry.registerModelLayer(SPEEDRUNNER_CHEST, BoatModel::createChestBoatModel);
        ModelLayerRegistry.registerModelLayer(DEAD_SPEEDRUNNER_CHEST, BoatModel::createChestBoatModel);
        ModelLayerRegistry.registerModelLayer(CRIMSON_CHEST, BoatModel::createChestBoatModel);
        ModelLayerRegistry.registerModelLayer(WARPED_CHEST, BoatModel::createChestBoatModel);

        ModelLayerRegistry.registerModelLayer(FIREPROOF_SPEEDRUNNER, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(FIREPROOF_SPEEDRUNNER_CHEST, BoatModel::createChestBoatModel);
        ModelLayerRegistry.registerModelLayer(FIREPROOF_CRIMSON, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(FIREPROOF_CRIMSON_CHEST, BoatModel::createChestBoatModel);
        ModelLayerRegistry.registerModelLayer(FIREPROOF_WARPED, BoatModel::createBoatModel);
        ModelLayerRegistry.registerModelLayer(FIREPROOF_WARPED_CHEST, BoatModel::createChestBoatModel);
    }

    /**
     * Registers miscellaneous renderers.
     */
    private static void registerOtherRenderers() {
        TerraformSignBlockHelper.registerDefaultWoodType(ofSpeedrunnerMod("speedrunner"));
    }

    /**
     * Registers all speedrunner mod renderers.
     */
    public static void registerRenderers() {
        registerEntityRenderers();
        registerModelLayers();
        registerOtherRenderers();
    }
}