package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.block.ModBlockFamilies;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.client.render.GoldenShieldModelRenderer;
import net.dillon.speedrunnermod.client.render.SpeedrunnerShieldModelRenderer;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.equipment.ModEquipmentAssetKeys;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.special.SpecialModelRenderer;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

/**
 * Used to create the blockstate and model JSON files from scratch using code.
 */
public class ModModelGenerator extends FabricModelProvider {

    public ModModelGenerator(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.woodProvider(ModBlocks.SPEEDRUNNER_LOG).logWithHorizontal(ModBlocks.SPEEDRUNNER_LOG).wood(ModBlocks.SPEEDRUNNER_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_SPEEDRUNNER_LOG).logWithHorizontal(ModBlocks.STRIPPED_SPEEDRUNNER_LOG).wood(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.DEAD_SPEEDRUNNER_LOG).logWithHorizontal(ModBlocks.DEAD_SPEEDRUNNER_LOG).wood(ModBlocks.DEAD_SPEEDRUNNER_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG).logWithHorizontal(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG).wood(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
        blockStateModelGenerator.woodProvider(ModBlocks.DOOM_LOG).logWithHorizontal(ModBlocks.DOOM_LOG);
        blockStateModelGenerator.woodProvider(ModBlocks.STRIPPED_DOOM_LOG).logWithHorizontal(ModBlocks.STRIPPED_DOOM_LOG);

        blockStateModelGenerator.createTrivialBlock(ModBlocks.SPEEDRUNNER_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.DEAD_SPEEDRUNNER_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.createTrivialBlock(ModBlocks.DOOM_LEAVES, TexturedModel.LEAVES);

        blockStateModelGenerator.createPlantWithDefaultItem(ModBlocks.SPEEDRUNNER_SAPLING, ModBlocks.POTTED_SPEEDRUNNER_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createPlantWithDefaultItem(ModBlocks.DEAD_SPEEDRUNNER_SAPLING, ModBlocks.DEAD_POTTED_SPEEDRUNNER_SAPLING, BlockModelGenerators.PlantType.NOT_TINTED);
        blockStateModelGenerator.createPlantWithDefaultItem(ModBlocks.DEAD_SPEEDRUNNER_BUSH, ModBlocks.POTTED_DEAD_SPEEDRUNNER_BUSH, BlockModelGenerators.PlantType.NOT_TINTED);

        BlockModelGenerators.BlockFamilyProvider speedrunnerPool = blockStateModelGenerator.family(ModBlocks.SPEEDRUNNER_PLANKS);
        speedrunnerPool.slab(ModBlocks.SPEEDRUNNER_SLAB);
        speedrunnerPool.stairs(ModBlocks.SPEEDRUNNER_STAIRS);
        speedrunnerPool.fence(ModBlocks.SPEEDRUNNER_FENCE);
        speedrunnerPool.fenceGate(ModBlocks.SPEEDRUNNER_FENCE_GATE);
        speedrunnerPool.button(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON);
        speedrunnerPool.pressurePlate(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        blockStateModelGenerator.createWeightedPressurePlate(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE, ModBlocks.SPEEDRUNNER_BLOCK);

        BlockModelGenerators.BlockFamilyProvider deadSpeedrunnerPool = blockStateModelGenerator.family(ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
        deadSpeedrunnerPool.slab(ModBlocks.DEAD_SPEEDRUNNER_SLAB);
        deadSpeedrunnerPool.stairs(ModBlocks.DEAD_SPEEDRUNNER_STAIRS);
        deadSpeedrunnerPool.fence(ModBlocks.DEAD_SPEEDRUNNER_FENCE);
        deadSpeedrunnerPool.fenceGate(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE);
        deadSpeedrunnerPool.button(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON);
        deadSpeedrunnerPool.pressurePlate(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);

        speedrunnerPool.generateFor(ModBlockFamilies.SPEEDRUNNER_FAMILY);
        speedrunnerPool.generateFor(ModBlockFamilies.DEAD_SPEEDRUNNER_FAMILY);
        blockStateModelGenerator.createHangingSign(ModBlocks.STRIPPED_SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_HANGING_SIGN, ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN);
        blockStateModelGenerator.createHangingSign(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG, ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, ModBlocks.DEAD_SPEEDRUNNER_HANGING_WALL_SIGN);

        blockStateModelGenerator.createTrapdoor(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR);
        blockStateModelGenerator.createTrapdoor(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
        blockStateModelGenerator.createTrapdoor(ModBlocks.SPEEDRUNNER_TRAPDOOR);

        blockStateModelGenerator.createDoor(ModBlocks.WOODEN_SPEEDRUNNER_DOOR);
        blockStateModelGenerator.createDoor(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR);
        blockStateModelGenerator.createDoor(ModBlocks.SPEEDRUNNER_DOOR);

        blockStateModelGenerator.createTrivialCube(ModBlocks.SPEEDRUNNER_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.RAW_SPEEDRUNNER_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.THRUSTED_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.SPEEDRUNNER_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.NETHER_SPEEDRUNNER_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.IGNEOUS_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_IGNEOUS_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.NETHER_IGNEOUS_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.EXPERIENCE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DEEPSLATE_EXPERIENCE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.NETHER_EXPERIENCE_ORE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.FLESH_BLOCK);
        blockStateModelGenerator.createTrivialCube(ModBlocks.DOOM_STONE);

        TextureMapping textureMap = new TextureMapping()
                .put(TextureSlot.PARTICLE, TextureMapping.getBlockTexture(ModBlocks.SPEEDRUNNERS_WORKBENCH, "_front"))
                .put(TextureSlot.DOWN, TextureMapping.getBlockTexture(ModBlocks.SPEEDRUNNERS_WORKBENCH, "_bottom"))
                .put(TextureSlot.UP, TextureMapping.getBlockTexture(ModBlocks.SPEEDRUNNERS_WORKBENCH, "_top"))
                .put(TextureSlot.NORTH, TextureMapping.getBlockTexture(ModBlocks.SPEEDRUNNERS_WORKBENCH, "_front"))
                .put(TextureSlot.SOUTH, TextureMapping.getBlockTexture(ModBlocks.SPEEDRUNNERS_WORKBENCH, "_front"))
                .put(TextureSlot.EAST, TextureMapping.getBlockTexture(ModBlocks.SPEEDRUNNERS_WORKBENCH, "_side"))
                .put(TextureSlot.WEST, TextureMapping.getBlockTexture(ModBlocks.SPEEDRUNNERS_WORKBENCH, "_side"));
        blockStateModelGenerator.blockStateOutput
                .accept(BlockModelGenerators.createSimpleBlock(ModBlocks.SPEEDRUNNERS_WORKBENCH, BlockModelGenerators.plainVariant(ModelTemplates.CUBE.create(ModBlocks.SPEEDRUNNERS_WORKBENCH, textureMap, blockStateModelGenerator.modelOutput))));
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.INVENTORY_PRESERVER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAW_SPEEDRUNNER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_NUGGET, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateTrimmableItem(ModItems.SPEEDRUNNER_HELMET, ModEquipmentAssetKeys.SPEEDRUNNER, ItemModelGenerators.prefixForSlotTrim("helmet"), false);
        itemModelGenerator.generateTrimmableItem(ModItems.SPEEDRUNNER_CHESTPLATE, ModEquipmentAssetKeys.SPEEDRUNNER, ItemModelGenerators.prefixForSlotTrim("chestplate"), false);
        itemModelGenerator.generateTrimmableItem(ModItems.SPEEDRUNNER_LEGGINGS, ModEquipmentAssetKeys.SPEEDRUNNER, ItemModelGenerators.prefixForSlotTrim("leggings"), false);
        itemModelGenerator.generateTrimmableItem(ModItems.SPEEDRUNNER_BOOTS, ModEquipmentAssetKeys.SPEEDRUNNER, ItemModelGenerators.prefixForSlotTrim("boots"), false);

        itemModelGenerator.generateTrimmableItem(ModItems.GOLDEN_SPEEDRUNNER_HELMET, ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER, ItemModelGenerators.prefixForSlotTrim("helmet"), false);
        itemModelGenerator.generateTrimmableItem(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE, ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER, ItemModelGenerators.prefixForSlotTrim("chestplate"), false);
        itemModelGenerator.generateTrimmableItem(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS, ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER, ItemModelGenerators.prefixForSlotTrim("leggings"), false);
        itemModelGenerator.generateTrimmableItem(ModItems.GOLDEN_SPEEDRUNNER_BOOTS, ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER, ItemModelGenerators.prefixForSlotTrim("boots"), false);

        itemModelGenerator.generateBow(ModItems.SPEEDRUNNER_BOW);
        itemModelGenerator.generateCrossbow(ModItems.SPEEDRUNNER_CROSSBOW);
        this.registerModdedShield(itemModelGenerator, ModItems.SPEEDRUNNER_SHIELD, new SpeedrunnerShieldModelRenderer.Unbaked());
        this.registerModdedShield(itemModelGenerator, ModItems.GOLDEN_SHIELD, new GoldenShieldModelRenderer.Unbaked());

        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_SHEARS, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_FLINT_AND_STEEL, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_BULK, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ROTTEN_SPEEDRUNNER_BULK, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOKED_FLESH, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PIGLIN_PORK, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.COOKED_PIGLIN_PORK, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLIATH_SPAWN_EGG, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.IGNEOUS_ROCK, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FIREPROOF_SPEEDRUNNER_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FIREPROOF_SPEEDRUNNER_CHEST_BOAT, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.DEAD_SPEEDRUNNER_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.CRIMSON_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FIREPROOF_CRIMSON_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.CRIMSON_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FIREPROOF_CRIMSON_CHEST_BOAT, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.WARPED_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FIREPROOF_WARPED_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.WARPED_CHEST_BOAT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.FIREPROOF_WARPED_CHEST_BOAT, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.ANNUL_EYE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNERS_EYE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNERS_TOTEM, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.INFERNO_EYE, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.INFINI_PEARL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ENDER_MATTER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.PIGLIN_AWAKENER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.BLAZE_SPOTTER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.RAID_ERADICATOR, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ENDER_THRUSTER, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DRAGONS_PEARL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.DRAGONS_FIREBALL, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.EXPERIENCE_FRAGMENT, ModelTemplates.FLAT_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_PADDLE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_SPEEDRUNNER_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_SPEEDRUNNER_AXE, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.GOLDEN_SPEEDRUNNER_HOE, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.DRAGONS_SWORD, ModelTemplates.FLAT_HANDHELD_ITEM);

        itemModelGenerator.generateFlatItem(ModItems.SPEEDRUNNER_STICK, ModelTemplates.FLAT_HANDHELD_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.KNOCKBACK_STICK, Items.STICK, ModelTemplates.FLAT_HANDHELD_ITEM);

        // Speedrunner Bow, Crossbow, shield, and wither bone are done separately.
    }

    /**
     * Registers a {@code speedrunner shield renderer.}
     */
    private void registerModdedShield(ItemModelGenerators itemModelGenerator, Item item, SpecialModelRenderer.Unbaked unbakedModel) {
        net.minecraft.client.renderer.item.ItemModel.Unbaked unbaked = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(item), unbakedModel);
        net.minecraft.client.renderer.item.ItemModel.Unbaked unbaked2 = ItemModelUtils.specialModel(ModelLocationUtils.getModelLocation(item, "_blocking"), unbakedModel);
        itemModelGenerator.generateBooleanDispatch(item, ItemModelUtils.isUsingItem(), unbaked2, unbaked);
    }
}