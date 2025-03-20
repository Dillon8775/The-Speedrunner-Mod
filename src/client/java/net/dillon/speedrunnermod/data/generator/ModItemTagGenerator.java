package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.item.ModBlockItems;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.item.Items;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

/**
 * Contains the entries of all new or already existing item tags.
 */
public class ModItemTagGenerator extends FabricTagProvider.ItemTagProvider {

    public ModItemTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup arg) {
        getOrCreateTagBuilder(ConventionalItemTags.BOW_TOOLS)
                .add(ModItems.SPEEDRUNNER_BOW);

        getOrCreateTagBuilder(ConventionalItemTags.CROSSBOW_TOOLS)
                .add(ModItems.SPEEDRUNNER_CROSSBOW);

        getOrCreateTagBuilder(ConventionalItemTags.IGNITER_TOOLS)
                .add(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);

        getOrCreateTagBuilder(ConventionalItemTags.IRON_INGOTS)
                .add(ModItems.SPEEDRUNNER_INGOT);

        getOrCreateTagBuilder(ConventionalItemTags.IRON_NUGGETS)
                .add(ModItems.SPEEDRUNNER_NUGGET);

        getOrCreateTagBuilder(ConventionalItemTags.SHEAR_TOOLS)
                .add(ModItems.SPEEDRUNNER_SHEARS);

        getOrCreateTagBuilder(ConventionalItemTags.SHIELD_TOOLS)
                .add(ModItems.SPEEDRUNNER_SHIELD);

        getOrCreateTagBuilder(ModItemTags.SPEED_BOOTS)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        getOrCreateTagBuilder(ModItemTags.COOLDOWN_ENCHANTMENT_ITEMS)
                .forceAddTag(ConventionalItemTags.SHIELD_TOOLS)
                .add(Items.ENDER_PEARL)
                .add(ModItems.INFINI_PEARL)
                .add(Items.CHORUS_FRUIT);

        getOrCreateTagBuilder(ModItemTags.SPEEDRUNNER_TOOLS)
                .add(ModItems.SPEEDRUNNER_SWORD)
                .add(ModItems.SPEEDRUNNER_SHOVEL)
                .add(ModItems.SPEEDRUNNER_PICKAXE)
                .add(ModItems.SPEEDRUNNER_AXE)
                .add(ModItems.SPEEDRUNNER_HOE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SWORD)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL)
                .add(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_AXE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HOE);

        getOrCreateTagBuilder(ModItemTags.DOOM_STONE_SAFE_TOOLS)
                .forceAddTag(ModItemTags.SPEEDRUNNER_TOOLS);

        getOrCreateTagBuilder(ModItemTags.DRAGON_TOOL_MATERIALS)
                .add(ModItems.DRAGONS_PEARL);

        getOrCreateTagBuilder(ModItemTags.FASTER_BOATS)
                .add(ModItems.SPEEDRUNNER_BOAT)
                .add(ModItems.DEAD_SPEEDRUNNER_BOAT);

        getOrCreateTagBuilder(ModItemTags.FASTER_CHEST_BOATS)
                .add(ModItems.SPEEDRUNNER_CHEST_BOAT)
                .add(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT);

        getOrCreateTagBuilder(ModItemTags.FIREPROOF_BOATS)
                .add(ModItems.SPEEDRUNNER_BOAT)
                .add(ModItems.CRIMSON_BOAT)
                .add(ModItems.WARPED_BOAT);

        getOrCreateTagBuilder(ModItemTags.FIREPROOF_CHEST_BOATS)
                .add(ModItems.SPEEDRUNNER_CHEST_BOAT)
                .add(ModItems.CRIMSON_CHEST_BOAT)
                .add(ModItems.WARPED_CHEST_BOAT);

        getOrCreateTagBuilder(ModItemTags.FIREPROOF_ITEMS)
                .add(Items.BLAZE_ROD)
                .add(Items.BLAZE_POWDER)
                .add(Items.FIRE_CHARGE);

        getOrCreateTagBuilder(ModItemTags.GOLDEN_FOOD_ITEMS)
                .add(Items.GOLDEN_APPLE)
                .add(Items.ENCHANTED_GOLDEN_APPLE)
                .add(Items.GOLDEN_CARROT)
                .add(ModItems.GOLDEN_PIGLIN_PORK)
                .add(ModItems.GOLDEN_BEEF)
                .add(ModItems.GOLDEN_PORKCHOP)
                .add(ModItems.GOLDEN_MUTTON)
                .add(ModItems.GOLDEN_CHICKEN)
                .add(ModItems.GOLDEN_RABBIT)
                .add(ModItems.GOLDEN_COD)
                .add(ModItems.GOLDEN_SALMON)
                .add(ModItems.GOLDEN_BREAD)
                .add(ModItems.GOLDEN_POTATO)
                .add(ModItems.GOLDEN_BEETROOT);

        getOrCreateTagBuilder(ModItemTags.GOLDEN_SPEEDRUNNER_TOOL_MATERIALS)
                .add(Items.GOLD_INGOT);

        getOrCreateTagBuilder(ModItemTags.IGNITABLES)
                .forceAddTag(ConventionalItemTags.IGNITER_TOOLS)
                .add(Items.FIRE_CHARGE);

        getOrCreateTagBuilder(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)
                .forceAddTag(ModItemTags.GOLDEN_FOOD_ITEMS)
                .add(Items.ENDER_PEARL)
                .add(Items.BLAZE_POWDER);

        getOrCreateTagBuilder(ModItemTags.SCULK_SENSOR_SAFE_BOOTS)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        getOrCreateTagBuilder(ModItemTags.SPEED_BOOTS)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        getOrCreateTagBuilder(ModItemTags.SPEEDRUNNER_ARMOR)
                .add(ModItems.SPEEDRUNNER_HELMET)
                .add(ModItems.SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.SPEEDRUNNER_LEGGINGS)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HELMET)
                .add(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        getOrCreateTagBuilder(ModItemTags.SPEEDRUNNER_SHIELD_REPAIRABLE)
                .forceAddTag(ItemTags.PLANKS)
                .add(ModItems.SPEEDRUNNER_INGOT);

        getOrCreateTagBuilder(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS)
                .add(ModItems.SPEEDRUNNER_INGOT);

        getOrCreateTagBuilder(ModItemTags.SPEEDRUNNER_SWORDS)
                .add(ModItems.SPEEDRUNNER_SWORD)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SWORD);

        getOrCreateTagBuilder(ModItemTags.STATE_OF_THE_ART_ITEMS)
                .add(ModItems.ANNUL_EYE)
                .add(ModItems.BLAZE_SPOTTER)
                .add(ModItems.DRAGONS_PEARL)
                .add(ModItems.DRAGONS_SWORD)
                .add(ModItems.ENDER_THRUSTER)
                .add(ModItems.PIGLIN_AWAKENER)
                .add(ModItems.RAID_ERADICATOR);

        getOrCreateTagBuilder(ModItemTags.STICKS)
                .add(Items.STICK)
                .add(ModItems.SPEEDRUNNER_STICK);

        getOrCreateTagBuilder(ModItemTags.TOTEMS)
                .add(Items.TOTEM_OF_UNDYING)
                .add(ModItems.SPEEDRUNNERS_TOTEM);

        getOrCreateTagBuilder(ModItemTags.WITHER_TOOL_MATERIALS)
                .add(ModItems.WITHER_BONE);

        getOrCreateTagBuilder(ModItemTags.AdvancementCriterions.EYE_OF_ANNUL)
                .add(Items.ENDER_PEARL)
                .add(Items.FIRE_CHARGE)
                .add(Items.BLAZE_POWDER);

        getOrCreateTagBuilder(ModItemTags.AdvancementCriterions.DRAGONS_PEARL)
                .add(Items.ENDER_PEARL)
                .add(Items.BLAZE_POWDER)
                .add(Items.FIRE_CHARGE)
                .add(ModItems.SPEEDRUNNERS_EYE);

        getOrCreateTagBuilder(ModItemTags.AdvancementCriterions.DRAGONS_SWORD)
                .add(ModItems.SPEEDRUNNER_SWORD)
                .add(ModItems.DRAGONS_PEARL);

        getOrCreateTagBuilder(ModItemTags.AdvancementCriterions.ENDER_THRUSTER)
                .add(Items.ENDER_PEARL)
                .add(ModItems.SPEEDRUNNERS_EYE);

        getOrCreateTagBuilder(ModItemTags.AdvancementCriterions.INFERNO_EYE)
                .add(Items.ENDER_PEARL)
                .add(Items.BLAZE_POWDER);

        getOrCreateTagBuilder(ModItemTags.Block.DOOM_LOGS)
                .add(ModBlockItems.DOOM_LOG)
                .add(ModBlockItems.STRIPPED_DOOM_LOG);

        getOrCreateTagBuilder(ModItemTags.Block.EXPERIENCE_ORES)
                .add(ModBlockItems.EXPERIENCE_ORE)
                .add(ModBlockItems.DEEPSLATE_EXPERIENCE_ORE)
                .add(ModBlockItems.NETHER_EXPERIENCE_ORE);

        getOrCreateTagBuilder(ModItemTags.Block.IGNEOUS_ORES)
                .add(ModBlockItems.IGNEOUS_ORE)
                .add(ModBlockItems.DEEPSLATE_IGNEOUS_ORE)
                .add(ModBlockItems.NETHER_IGNEOUS_ORE);

        getOrCreateTagBuilder(ModItemTags.Block.IRON_BLOCKS)
                .add(Items.IRON_BLOCK)
                .add(ModBlockItems.SPEEDRUNNER_BLOCK);

        getOrCreateTagBuilder(ModItemTags.Block.NETHER_PORTAL_BASE_BLOCKS)
                .add(Items.OBSIDIAN)
                .add(Items.CRYING_OBSIDIAN);

        getOrCreateTagBuilder(ModItemTags.Block.SMITHING_TABLES)
                .add(Items.SMITHING_TABLE)
                .add(ModBlockItems.SPEEDRUNNERS_WORKBENCH);

        getOrCreateTagBuilder(ModItemTags.Block.SPEEDRUNNER_LOGS)
                .add(ModBlockItems.SPEEDRUNNER_LOG)
                .add(ModBlockItems.STRIPPED_SPEEDRUNNER_LOG)
                .add(ModBlockItems.SPEEDRUNNER_WOOD)
                .add(ModBlockItems.STRIPPED_SPEEDRUNNER_WOOD);

        getOrCreateTagBuilder(ModItemTags.Block.DEAD_SPEEDRUNNER_LOGS)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_LOG)
                .add(ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_LOG)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_WOOD)
                .add(ModBlockItems.DEAD_STRIPPED_SPEEDRUNNER_WOOD);

        getOrCreateTagBuilder(ModItemTags.Block.SPEEDRUNNER_PLANKS)
                .add(ModBlockItems.SPEEDRUNNER_PLANKS)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_PLANKS);

        getOrCreateTagBuilder(ModItemTags.Block.SPEEDRUNNER_FUELS)
                .forceAddTag(ModItemTags.Block.SPEEDRUNNER_LOGS)
                .add(ModBlockItems.SPEEDRUNNER_SAPLING)
                .add(ModBlockItems.SPEEDRUNNER_SLAB)
                .add(ModBlockItems.SPEEDRUNNER_STAIRS)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_TRAPDOOR)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE)
                .add(ModBlockItems.SPEEDRUNNER_FENCE)
                .add(ModBlockItems.SPEEDRUNNER_FENCE_GATE)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_BUTTON)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_BUSH);

        getOrCreateTagBuilder(ModItemTags.Block.SPEEDRUNNER_ORES)
                .add(ModBlockItems.SPEEDRUNNER_ORE)
                .add(ModBlockItems.DEEPSLATE_SPEEDRUNNER_ORE)
                .add(ModBlockItems.NETHER_SPEEDRUNNER_ORE);

        getOrCreateTagBuilder(ModItemTags.Block.SPEEDRUNNER_SAPLING_PLACEABLES)
                .forceAddTag(ItemTags.SAND)
                .add(Items.NETHERRACK)
                .add(Items.CRIMSON_NYLIUM)
                .add(Items.WARPED_NYLIUM);

        getOrCreateTagBuilder(ModItemTags.Block.SPEEDRUNNER_SIGNS)
                .add(ModBlockItems.SPEEDRUNNER_SIGN)
                .add(ModBlockItems.SPEEDRUNNER_HANGING_SIGN)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_SIGN)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_HANGING_SIGN);

        getOrCreateTagBuilder(ModItemTags.Block.TEXTURE_CREATOR_MANNYQUESO)
                .add(ModBlockItems.THRUSTED_BLOCK);

        getOrCreateTagBuilder(ModItemTags.Block.TEXTURE_CREATOR_KREVIKUS)
                .forceAddTag(ModItemTags.Block.EXPERIENCE_ORES);

        getOrCreateTagBuilder(ItemTags.BOATS)
                .add(ModItems.SPEEDRUNNER_BOAT)
                .add(ModItems.DEAD_SPEEDRUNNER_BOAT)
                .add(ModItems.CRIMSON_BOAT)
                .add(ModItems.WARPED_BOAT);

        getOrCreateTagBuilder(ItemTags.CHEST_BOATS)
                .add(ModItems.SPEEDRUNNER_CHEST_BOAT)
                .add(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT)
                .add(ModItems.CRIMSON_CHEST_BOAT)
                .add(ModItems.WARPED_CHEST_BOAT);

        getOrCreateTagBuilder(ItemTags.CREEPER_IGNITERS)
                .add(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);

        getOrCreateTagBuilder(ItemTags.DOORS)
                .add(ModBlockItems.SPEEDRUNNER_DOOR);

        getOrCreateTagBuilder(ItemTags.WOODEN_DOORS)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_DOOR)
                .add(ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_DOOR);

        getOrCreateTagBuilder(ItemTags.FENCE_GATES)
                .add(ModBlockItems.SPEEDRUNNER_FENCE_GATE)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_FENCE_GATE);

        getOrCreateTagBuilder(ItemTags.WOODEN_FENCES)
                .add(ModBlockItems.SPEEDRUNNER_FENCE)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_FENCE);

        getOrCreateTagBuilder(ItemTags.LEAVES)
                .add(ModBlockItems.SPEEDRUNNER_LEAVES)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_LEAVES)
                .add(ModBlockItems.DOOM_LEAVES);

        getOrCreateTagBuilder(ItemTags.LOGS)
                .forceAddTag(ModItemTags.Block.SPEEDRUNNER_LOGS)
                .forceAddTag(ModItemTags.Block.DOOM_LOGS);

        getOrCreateTagBuilder(ItemTags.PIGLIN_FOOD)
                .add(ModItems.PIGLIN_PORK)
                .add(ModItems.COOKED_PIGLIN_PORK)
                .add(ModItems.GOLDEN_PIGLIN_PORK);

        getOrCreateTagBuilder(ItemTags.PIGLIN_LOVED)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SWORD)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL)
                .add(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_AXE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HOE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HELMET)
                .add(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE);

        getOrCreateTagBuilder(ItemTags.PLANKS)
                .add(ModBlockItems.SPEEDRUNNER_PLANKS)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_PLANKS);

        getOrCreateTagBuilder(ItemTags.SAPLINGS)
                .add(ModBlockItems.SPEEDRUNNER_SAPLING)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_SAPLING);

        getOrCreateTagBuilder(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(Items.ANDESITE)
                .add(Items.DIORITE)
                .add(Items.GRANITE)
                .add(Items.MOSSY_COBBLESTONE)
                .add(Items.END_STONE);

        getOrCreateTagBuilder(ItemTags.TRAPDOORS)
                .add(ModBlockItems.SPEEDRUNNER_TRAPDOOR)
                .add(ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);

        getOrCreateTagBuilder(ItemTags.TRIMMABLE_ARMOR)
                .forceAddTag(ModItemTags.SPEEDRUNNER_ARMOR);

        getOrCreateTagBuilder(ItemTags.WOODEN_BUTTONS)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_BUTTON)
                .add(ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_BUTTON);

        getOrCreateTagBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE)
                .add(ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);

        getOrCreateTagBuilder(ItemTags.WOODEN_SLABS)
                .add(ModBlockItems.SPEEDRUNNER_SLAB)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_SLAB);

        getOrCreateTagBuilder(ItemTags.WOODEN_STAIRS)
                .add(ModBlockItems.SPEEDRUNNER_STAIRS)
                .add(ModBlockItems.DEAD_SPEEDRUNNER_STAIRS);

        getOrCreateTagBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlockItems.WOODEN_SPEEDRUNNER_TRAPDOOR)
                .add(ModBlockItems.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);

        getOrCreateTagBuilder(ItemTags.SWORD_ENCHANTABLE)
                .forceAddTag(ItemTags.AXES);

        getOrCreateTagBuilder(ItemTags.MINING_ENCHANTABLE)
                .add(ModItems.SPEEDRUNNER_SHEARS);

        getOrCreateTagBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.SPEEDRUNNER_SHEARS)
                .add(ModItems.SPEEDRUNNER_FLINT_AND_STEEL)
                .add(ModItems.SPEEDRUNNER_BOW)
                .add(ModItems.SPEEDRUNNER_CROSSBOW)
                .add(ModItems.SPEEDRUNNER_SHIELD);

        getOrCreateTagBuilder(ItemTags.BOW_ENCHANTABLE)
                .add(ModItems.SPEEDRUNNER_BOW);

        getOrCreateTagBuilder(ItemTags.CROSSBOW_ENCHANTABLE)
                .add(ModItems.SPEEDRUNNER_CROSSBOW);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(ModItems.SPEEDRUNNER_SWORD)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SWORD)
                .add(ModItems.DRAGONS_SWORD)
                .add(ModItems.WITHER_SWORD);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(ModItems.SPEEDRUNNER_SHOVEL)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(ModItems.SPEEDRUNNER_PICKAXE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(ModItems.SPEEDRUNNER_AXE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_AXE);

        getOrCreateTagBuilder(ItemTags.HOES)
                .add(ModItems.SPEEDRUNNER_HOE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HOE);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.SPEEDRUNNER_HELMET)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HELMET);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.SPEEDRUNNER_LEGGINGS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);

        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        getOrCreateTagBuilder(ItemTags.PIGLIN_SAFE_ARMOR)
                .add(Items.GOLDEN_HELMET)
                .add(Items.GOLDEN_CHESTPLATE)
                .add(Items.GOLDEN_LEGGINGS)
                .add(Items.GOLDEN_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HELMET)
                .add(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);
    }
}