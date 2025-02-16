package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.block.ModBlockFamilies;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.client.render.SpeedrunnerShieldModelRenderer;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.item.equipment.ModEquipmentAssetKeys;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.client.data.*;
import net.minecraft.client.render.item.model.ItemModel;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

/**
 * Used to create the blockstate and model JSON files from scratch using code.
 */
public class ModModelGenerator extends FabricModelProvider {

    public ModModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerLog(ModBlocks.SPEEDRUNNER_LOG).log(ModBlocks.SPEEDRUNNER_LOG).wood(ModBlocks.SPEEDRUNNER_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_SPEEDRUNNER_LOG).log(ModBlocks.STRIPPED_SPEEDRUNNER_LOG).wood(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.DEAD_SPEEDRUNNER_LOG).log(ModBlocks.DEAD_SPEEDRUNNER_LOG).wood(ModBlocks.DEAD_SPEEDRUNNER_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG).log(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG).wood(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD);
        blockStateModelGenerator.registerLog(ModBlocks.DOOM_LOG).log(ModBlocks.DOOM_LOG);
        blockStateModelGenerator.registerLog(ModBlocks.STRIPPED_DOOM_LOG).log(ModBlocks.STRIPPED_DOOM_LOG);

        blockStateModelGenerator.registerSingleton(ModBlocks.SPEEDRUNNER_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerSingleton(ModBlocks.DEAD_SPEEDRUNNER_LEAVES, TexturedModel.LEAVES);
        blockStateModelGenerator.registerSingleton(ModBlocks.DOOM_LEAVES, TexturedModel.LEAVES);

        blockStateModelGenerator.registerFlowerPotPlantAndItem(ModBlocks.SPEEDRUNNER_SAPLING, ModBlocks.POTTED_SPEEDRUNNER_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlantAndItem(ModBlocks.DEAD_SPEEDRUNNER_SAPLING, ModBlocks.DEAD_POTTED_SPEEDRUNNER_SAPLING, BlockStateModelGenerator.CrossType.NOT_TINTED);
        blockStateModelGenerator.registerFlowerPotPlantAndItem(ModBlocks.DEAD_SPEEDRUNNER_BUSH, ModBlocks.POTTED_DEAD_SPEEDRUNNER_BUSH, BlockStateModelGenerator.CrossType.NOT_TINTED);

        BlockStateModelGenerator.BlockTexturePool speedrunnerPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.SPEEDRUNNER_PLANKS);
        speedrunnerPool.slab(ModBlocks.SPEEDRUNNER_SLAB);
        speedrunnerPool.stairs(ModBlocks.SPEEDRUNNER_STAIRS);
        speedrunnerPool.fence(ModBlocks.SPEEDRUNNER_FENCE);
        speedrunnerPool.fenceGate(ModBlocks.SPEEDRUNNER_FENCE_GATE);
        speedrunnerPool.button(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON);
        speedrunnerPool.pressurePlate(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE);
        blockStateModelGenerator.registerWeightedPressurePlate(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE, ModBlocks.SPEEDRUNNER_BLOCK);

        BlockStateModelGenerator.BlockTexturePool deadSpeedrunnerPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
        deadSpeedrunnerPool.slab(ModBlocks.DEAD_SPEEDRUNNER_SLAB);
        deadSpeedrunnerPool.stairs(ModBlocks.DEAD_SPEEDRUNNER_STAIRS);
        deadSpeedrunnerPool.fence(ModBlocks.DEAD_SPEEDRUNNER_FENCE);
        deadSpeedrunnerPool.fenceGate(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE);
        deadSpeedrunnerPool.button(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON);
        deadSpeedrunnerPool.pressurePlate(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);

        speedrunnerPool.family(ModBlockFamilies.SPEEDRUNNER_FAMILY);
        speedrunnerPool.family(ModBlockFamilies.DEAD_SPEEDRUNNER_FAMILY);
        blockStateModelGenerator.registerHangingSign(ModBlocks.STRIPPED_SPEEDRUNNER_LOG, ModBlocks.SPEEDRUNNER_HANGING_SIGN, ModBlocks.SPEEDRUNNER_HANGING_WALL_SIGN);
        blockStateModelGenerator.registerHangingSign(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG, ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, ModBlocks.DEAD_SPEEDRUNNER_HANGING_WALL_SIGN);

        blockStateModelGenerator.registerTrapdoor(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);
        blockStateModelGenerator.registerTrapdoor(ModBlocks.SPEEDRUNNER_TRAPDOOR);

        blockStateModelGenerator.registerDoor(ModBlocks.WOODEN_SPEEDRUNNER_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR);
        blockStateModelGenerator.registerDoor(ModBlocks.SPEEDRUNNER_DOOR);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SPEEDRUNNER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.RAW_SPEEDRUNNER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.THRUSTER_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SPEEDRUNNER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NETHER_SPEEDRUNNER_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.IGNEOUS_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_IGNEOUS_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NETHER_IGNEOUS_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.EXPERIENCE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DEEPSLATE_EXPERIENCE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.NETHER_EXPERIENCE_ORE);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.DOOM_STONE);

        // Speedrunner's Workbench is done manually.
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SPEEDRUNNER_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAW_SPEEDRUNNER, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPEEDRUNNER_NUGGET, Models.GENERATED);

        itemModelGenerator.registerArmor(ModItems.SPEEDRUNNER_HELMET, ModEquipmentAssetKeys.SPEEDRUNNER, "helmet", false);
        itemModelGenerator.registerArmor(ModItems.SPEEDRUNNER_CHESTPLATE, ModEquipmentAssetKeys.SPEEDRUNNER, "chestplate", false);
        itemModelGenerator.registerArmor(ModItems.SPEEDRUNNER_LEGGINGS, ModEquipmentAssetKeys.SPEEDRUNNER, "leggings", false);
        itemModelGenerator.registerArmor(ModItems.SPEEDRUNNER_BOOTS, ModEquipmentAssetKeys.SPEEDRUNNER, "boots", false);

        itemModelGenerator.registerArmor(ModItems.GOLDEN_SPEEDRUNNER_HELMET, ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER, "helmet", false);
        itemModelGenerator.registerArmor(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE, ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER, "chestplate", false);
        itemModelGenerator.registerArmor(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS, ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER, "leggings", false);
        itemModelGenerator.registerArmor(ModItems.GOLDEN_SPEEDRUNNER_BOOTS, ModEquipmentAssetKeys.GOLDEN_SPEEDRUNNER, "boots", false);

        itemModelGenerator.registerBow(ModItems.SPEEDRUNNER_BOW);
        itemModelGenerator.registerCrossbow(ModItems.SPEEDRUNNER_CROSSBOW);
        registerSpeedrunnerShield(itemModelGenerator, ModItems.SPEEDRUNNER_SHIELD);

        itemModelGenerator.register(ModItems.SPEEDRUNNER_SHEARS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPEEDRUNNER_FLINT_AND_STEEL, Models.GENERATED);

        itemModelGenerator.register(ModItems.SPEEDRUNNER_BULK, Models.GENERATED);
        itemModelGenerator.register(ModItems.ROTTEN_SPEEDRUNNER_BULK, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_FLESH, Models.GENERATED);
        itemModelGenerator.register(ModItems.PIGLIN_PORK, Models.GENERATED);
        itemModelGenerator.register(ModItems.COOKED_PIGLIN_PORK, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_PIGLIN_PORK, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_BEEF, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_PORKCHOP, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_MUTTON, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_CHICKEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_RABBIT, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_COD, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_SALMON, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_BREAD, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_POTATO, Models.GENERATED);
        itemModelGenerator.register(ModItems.GOLDEN_BEETROOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.IGNEOUS_ROCK, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPEEDRUNNER_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPEEDRUNNER_CHEST_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEAD_SPEEDRUNNER_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSON_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSON_CHEST_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARPED_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARPED_CHEST_BOAT, Models.GENERATED);
        itemModelGenerator.register(ModItems.ANNUL_EYE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPEEDRUNNERS_EYE, Models.GENERATED);
        itemModelGenerator.register(ModItems.INFERNO_EYE, Models.GENERATED);
        itemModelGenerator.registerWithTextureSource(ModItems.INFINI_PEARL, Items.ENDER_PEARL, Models.GENERATED);
        itemModelGenerator.register(ModItems.PIGLIN_AWAKENER, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLAZE_SPOTTER, Models.GENERATED);
        itemModelGenerator.register(ModItems.RAID_ERADICATOR, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENDER_THRUSTER, Models.GENERATED);
        itemModelGenerator.register(ModItems.DRAGONS_PEARL, Models.GENERATED);

        itemModelGenerator.register(ModItems.SPEEDRUNNER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SPEEDRUNNER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SPEEDRUNNER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SPEEDRUNNER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SPEEDRUNNER_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_SPEEDRUNNER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_SPEEDRUNNER_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.GOLDEN_SPEEDRUNNER_HOE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.WITHER_BONE);
        itemModelGenerator.register(ModItems.WITHER_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.DRAGONS_SWORD, Models.HANDHELD);

        itemModelGenerator.register(ModItems.SPEEDRUNNER_STICK, Models.HANDHELD);
        itemModelGenerator.registerWithTextureSource(ModItems.KNOCKBACK_STICK, Items.STICK, Models.GENERATED);

        // Speedrunner Bow, Crossbow, shield, and wither bone are done separately.
    }

    /**
     * Registers a {@code speedrunner shield renderer.}
     */
    private void registerSpeedrunnerShield(ItemModelGenerator itemModelGenerator, Item item) {
        ItemModel.Unbaked unbaked = ItemModels.special(ModelIds.getItemModelId(item), new SpeedrunnerShieldModelRenderer.Unbaked());
        ItemModel.Unbaked unbaked2 = ItemModels.special(ModelIds.getItemSubModelId(item, "_blocking"), new SpeedrunnerShieldModelRenderer.Unbaked());
        itemModelGenerator.registerCondition(item, ItemModels.usingItemProperty(), unbaked2, unbaked);
    }
}