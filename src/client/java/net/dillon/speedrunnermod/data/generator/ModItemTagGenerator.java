package net.dillon.speedrunnermod.data.generator;

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
        valueLookupBuilder(ConventionalItemTags.BOW_TOOLS)
                .add(ModItems.SPEEDRUNNER_BOW);

        valueLookupBuilder(ConventionalItemTags.CROSSBOW_TOOLS)
                .add(ModItems.SPEEDRUNNER_CROSSBOW);

        valueLookupBuilder(ConventionalItemTags.IGNITER_TOOLS)
                .add(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);

        valueLookupBuilder(ConventionalItemTags.IRON_INGOTS)
                .add(ModItems.SPEEDRUNNER_INGOT);

        valueLookupBuilder(ConventionalItemTags.IRON_NUGGETS)
                .add(ModItems.SPEEDRUNNER_NUGGET);

        valueLookupBuilder(ConventionalItemTags.SHEAR_TOOLS)
                .add(ModItems.SPEEDRUNNER_SHEARS);

        valueLookupBuilder(ConventionalItemTags.SHIELD_TOOLS)
                .add(ModItems.SPEEDRUNNER_SHIELD);

        valueLookupBuilder(ModItemTags.SPEED_BOOTS)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        valueLookupBuilder(ModItemTags.COOLDOWN_ENCHANTMENT_ITEMS)
                .forceAddTag(ConventionalItemTags.SHIELD_TOOLS)
                .add(Items.ENDER_PEARL)
                .add(ModItems.INFINI_PEARL)
                .add(Items.CHORUS_FRUIT);

        valueLookupBuilder(ModItemTags.SPEEDRUNNER_TOOLS)
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

        valueLookupBuilder(ModItemTags.DOOM_STONE_SAFE_TOOLS)
                .forceAddTag(ModItemTags.SPEEDRUNNER_TOOLS);

        valueLookupBuilder(ModItemTags.DRAGON_TOOL_MATERIALS)
                .add(ModItems.DRAGONS_PEARL);

        valueLookupBuilder(ModItemTags.EXPERIENCE_BOTTLE_CRAFTABLES)
                .add(Items.GLASS_BOTTLE)
                .add(ModItems.EXPERIENCE_FRAGMENT);

        valueLookupBuilder(ModItemTags.FASTER_BOATS)
                .add(ModItems.SPEEDRUNNER_BOAT)
                .add(ModItems.DEAD_SPEEDRUNNER_BOAT);

        valueLookupBuilder(ModItemTags.FASTER_CHEST_BOATS)
                .add(ModItems.SPEEDRUNNER_CHEST_BOAT)
                .add(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT);

        valueLookupBuilder(ModItemTags.FLESH)
                .add(ModItems.COOKED_FLESH)
                .add(Items.ROTTEN_FLESH);

        valueLookupBuilder(ModItemTags.FIREPROOF_BOATS)
                .add(ModItems.SPEEDRUNNER_BOAT)
                .add(ModItems.CRIMSON_BOAT)
                .add(ModItems.WARPED_BOAT);

        valueLookupBuilder(ModItemTags.FIREPROOF_CHEST_BOATS)
                .add(ModItems.SPEEDRUNNER_CHEST_BOAT)
                .add(ModItems.CRIMSON_CHEST_BOAT)
                .add(ModItems.WARPED_CHEST_BOAT);

        valueLookupBuilder(ModItemTags.FIREPROOF_ITEMS)
                .add(Items.BLAZE_ROD)
                .add(Items.BLAZE_POWDER)
                .add(Items.FIRE_CHARGE);

        valueLookupBuilder(ModItemTags.GOLDEN_FOOD_ITEMS)
                .add(Items.GOLDEN_APPLE)
                .add(Items.ENCHANTED_GOLDEN_APPLE)
                .add(Items.GOLDEN_CARROT);

        valueLookupBuilder(ModItemTags.GOLDEN_SPEEDRUNNER_ARMOR)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HELMET)
                .add(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        valueLookupBuilder(ModItemTags.GOLDEN_SPEEDRUNNER_TOOL_MATERIALS)
                .add(Items.GOLD_INGOT);

        valueLookupBuilder(ModItemTags.IGNITABLES)
                .forceAddTag(ConventionalItemTags.IGNITER_TOOLS)
                .add(Items.FIRE_CHARGE);

        valueLookupBuilder(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)
                .forceAddTag(ModItemTags.GOLDEN_FOOD_ITEMS)
                .add(Items.ENDER_PEARL)
                .add(Items.BLAZE_POWDER);

        valueLookupBuilder(ModItemTags.SCULK_SENSOR_SAFE_BOOTS)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        valueLookupBuilder(ModItemTags.SPEED_BOOTS)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        valueLookupBuilder(ModItemTags.SPEEDRUNNER_ARMOR)
                .add(ModItems.SPEEDRUNNER_HELMET)
                .add(ModItems.SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.SPEEDRUNNER_LEGGINGS)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HELMET)
                .add(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        valueLookupBuilder(ModItemTags.SPEEDRUNNER_SHIELD_REPAIRABLE)
                .forceAddTag(ItemTags.PLANKS)
                .add(ModItems.SPEEDRUNNER_INGOT);

        valueLookupBuilder(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS)
                .add(ModItems.SPEEDRUNNER_INGOT);

        valueLookupBuilder(ModItemTags.SPEEDRUNNER_SWORDS)
                .add(ModItems.SPEEDRUNNER_SWORD)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SWORD);

        valueLookupBuilder(ModItemTags.STATE_OF_THE_ART_ITEMS)
                .add(ModItems.ANNUL_EYE)
                .add(ModItems.BLAZE_SPOTTER)
                .add(ModItems.DRAGONS_PEARL)
                .add(ModItems.DRAGONS_SWORD)
                .add(ModItems.ENDER_THRUSTER)
                .add(ModItems.PIGLIN_AWAKENER)
                .add(ModItems.RAID_ERADICATOR);

        valueLookupBuilder(ModItemTags.STICKS)
                .add(Items.STICK)
                .add(ModItems.SPEEDRUNNER_STICK)
                .add(ModItems.DEAD_SPEEDRUNNER_STICK);

        valueLookupBuilder(ModItemTags.SPEEDRUNNER_STICKS)
                .add(ModItems.SPEEDRUNNER_STICK)
                .add(ModItems.DEAD_SPEEDRUNNER_STICK);

        valueLookupBuilder(ModItemTags.TOTEMS)
                .add(Items.TOTEM_OF_UNDYING)
                .add(ModItems.SPEEDRUNNERS_TOTEM);

        valueLookupBuilder(ModItemTags.Block.DOOM_LOGS)
                .add(ModItems.DOOM_LOG)
                .add(ModItems.STRIPPED_DOOM_LOG);

        valueLookupBuilder(ModItemTags.Block.EXPERIENCE_ORES)
                .add(ModItems.EXPERIENCE_ORE)
                .add(ModItems.DEEPSLATE_EXPERIENCE_ORE)
                .add(ModItems.NETHER_EXPERIENCE_ORE);

        valueLookupBuilder(ModItemTags.Block.IGNEOUS_ORES)
                .add(ModItems.IGNEOUS_ORE)
                .add(ModItems.DEEPSLATE_IGNEOUS_ORE)
                .add(ModItems.NETHER_IGNEOUS_ORE);

        valueLookupBuilder(ModItemTags.Block.IRON_BLOCKS)
                .add(Items.IRON_BLOCK)
                .add(ModItems.SPEEDRUNNER_BLOCK);

        valueLookupBuilder(ModItemTags.Block.NETHER_PORTAL_BASE_BLOCKS)
                .add(Items.OBSIDIAN)
                .add(Items.CRYING_OBSIDIAN);

        valueLookupBuilder(ModItemTags.Block.SPEEDRUNNER_LOGS)
                .add(ModItems.SPEEDRUNNER_LOG)
                .add(ModItems.STRIPPED_SPEEDRUNNER_LOG)
                .add(ModItems.SPEEDRUNNER_WOOD)
                .add(ModItems.STRIPPED_SPEEDRUNNER_WOOD);

        valueLookupBuilder(ModItemTags.Block.DEAD_SPEEDRUNNER_LOGS)
                .add(ModItems.DEAD_SPEEDRUNNER_LOG)
                .add(ModItems.DEAD_STRIPPED_SPEEDRUNNER_LOG)
                .add(ModItems.DEAD_SPEEDRUNNER_WOOD)
                .add(ModItems.DEAD_STRIPPED_SPEEDRUNNER_WOOD);

        valueLookupBuilder(ModItemTags.Block.SPEEDRUNNER_PLANKS)
                .add(ModItems.SPEEDRUNNER_PLANKS)
                .add(ModItems.DEAD_SPEEDRUNNER_PLANKS);

        valueLookupBuilder(ModItemTags.Block.SPEEDRUNNER_FUELS)
                .forceAddTag(ModItemTags.Block.SPEEDRUNNER_LOGS)
                .add(ModItems.SPEEDRUNNER_SAPLING)
                .add(ModItems.SPEEDRUNNER_SLAB)
                .add(ModItems.SPEEDRUNNER_STAIRS)
                .add(ModItems.WOODEN_SPEEDRUNNER_TRAPDOOR)
                .add(ModItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE)
                .add(ModItems.SPEEDRUNNER_FENCE)
                .add(ModItems.SPEEDRUNNER_FENCE_GATE)
                .add(ModItems.WOODEN_SPEEDRUNNER_BUTTON)
                .add(ModItems.DEAD_SPEEDRUNNER_BUSH);

        valueLookupBuilder(ModItemTags.Block.SPEEDRUNNER_ORES)
                .add(ModItems.SPEEDRUNNER_ORE)
                .add(ModItems.DEEPSLATE_SPEEDRUNNER_ORE)
                .add(ModItems.NETHER_SPEEDRUNNER_ORE);

        valueLookupBuilder(ModItemTags.Block.SPEEDRUNNER_SAPLING_PLACEABLES)
                .forceAddTag(ItemTags.SAND)
                .add(Items.NETHERRACK)
                .add(Items.CRIMSON_NYLIUM)
                .add(Items.WARPED_NYLIUM);

        valueLookupBuilder(ModItemTags.Block.SPEEDRUNNER_SIGNS)
                .add(ModItems.SPEEDRUNNER_SIGN)
                .add(ModItems.SPEEDRUNNER_HANGING_SIGN)
                .add(ModItems.DEAD_SPEEDRUNNER_SIGN)
                .add(ModItems.DEAD_SPEEDRUNNER_HANGING_SIGN);

        valueLookupBuilder(ModItemTags.AdvancementCriterions.EYE_OF_ANNUL)
                .add(Items.ENDER_PEARL)
                .add(Items.BLAZE_POWDER)
                .add(Items.ENDER_EYE);

        valueLookupBuilder(ModItemTags.AdvancementCriterions.BLAZE_SPOTTER)
                .add(Items.ENDER_PEARL)
                .add(Items.FIRE_CHARGE)
                .add(Items.LAVA_BUCKET);

        valueLookupBuilder(ModItemTags.AdvancementCriterions.DRAGONS_PEARL)
                .add(Items.ENDER_PEARL)
                .add(Items.BLAZE_POWDER)
                .add(ModItems.SPEEDRUNNERS_EYE);

        valueLookupBuilder(ModItemTags.AdvancementCriterions.DRAGONS_SWORD)
                .add(ModItems.SPEEDRUNNER_SWORD)
                .add(ModItems.DRAGONS_PEARL);

        valueLookupBuilder(ModItemTags.AdvancementCriterions.ENDER_THRUSTER)
                .add(Items.ENDER_PEARL)
                .add(ModItems.SPEEDRUNNERS_EYE);

        valueLookupBuilder(ModItemTags.AdvancementCriterions.INFERNO_EYE)
                .add(Items.ENDER_PEARL)
                .add(Items.BLAZE_POWDER);

        valueLookupBuilder(ModItemTags.AdvancementCriterions.RAID_ERADICATOR)
                .forceAddTag(ModItemTags.TOTEMS)
                .add(Items.ENCHANTED_GOLDEN_APPLE);

        valueLookupBuilder(ModItemTags.AdvancementCriterions.PIGLIN_AWAKENER)
                .forceAddTag(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)
                .add(Items.GOLD_INGOT);

        valueLookupBuilder(ModItemTags.AdvancementCriterions.SPEEDRUNNER_FLINT_AND_STEEL)
                .add(Items.FLINT)
                .add(Items.FLINT_AND_STEEL)
                .add(ModItems.SPEEDRUNNER_INGOT);

        valueLookupBuilder(ModItemTags.AdvancementCriterions.SPEEDRUNNERS_WORKBENCH)
                .forceAddTag(ModItemTags.Block.SPEEDRUNNER_PLANKS)
                .add(ModItems.SPEEDRUNNER_INGOT);

        valueLookupBuilder(ItemTags.BOATS)
                .add(ModItems.SPEEDRUNNER_BOAT)
                .add(ModItems.DEAD_SPEEDRUNNER_BOAT)
                .add(ModItems.CRIMSON_BOAT)
                .add(ModItems.WARPED_BOAT);

        valueLookupBuilder(ItemTags.CHEST_BOATS)
                .add(ModItems.SPEEDRUNNER_CHEST_BOAT)
                .add(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT)
                .add(ModItems.CRIMSON_CHEST_BOAT)
                .add(ModItems.WARPED_CHEST_BOAT);

        valueLookupBuilder(ItemTags.CREEPER_IGNITERS)
                .add(ModItems.SPEEDRUNNER_FLINT_AND_STEEL);

        valueLookupBuilder(ItemTags.DOORS)
                .add(ModItems.SPEEDRUNNER_DOOR);

        valueLookupBuilder(ItemTags.WOODEN_DOORS)
                .add(ModItems.WOODEN_SPEEDRUNNER_DOOR)
                .add(ModItems.DEAD_WOODEN_SPEEDRUNNER_DOOR);

        valueLookupBuilder(ItemTags.FENCE_GATES)
                .add(ModItems.SPEEDRUNNER_FENCE_GATE)
                .add(ModItems.DEAD_SPEEDRUNNER_FENCE_GATE);

        valueLookupBuilder(ItemTags.WOODEN_FENCES)
                .add(ModItems.SPEEDRUNNER_FENCE)
                .add(ModItems.DEAD_SPEEDRUNNER_FENCE);

        valueLookupBuilder(ItemTags.LEAVES)
                .add(ModItems.SPEEDRUNNER_LEAVES)
                .add(ModItems.DEAD_SPEEDRUNNER_LEAVES)
                .add(ModItems.DOOM_LEAVES);

        valueLookupBuilder(ItemTags.LOGS)
                .forceAddTag(ModItemTags.Block.SPEEDRUNNER_LOGS)
                .forceAddTag(ModItemTags.Block.DOOM_LOGS);

        valueLookupBuilder(ItemTags.PIGLIN_FOOD)
                .add(ModItems.PIGLIN_PORK)
                .add(ModItems.COOKED_PIGLIN_PORK);

        valueLookupBuilder(ItemTags.PIGLIN_LOVED)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SWORD)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL)
                .add(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_AXE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HOE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HELMET)
                .add(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE);

        valueLookupBuilder(ItemTags.PLANKS)
                .add(ModItems.SPEEDRUNNER_PLANKS)
                .add(ModItems.DEAD_SPEEDRUNNER_PLANKS);

        valueLookupBuilder(ItemTags.SAPLINGS)
                .add(ModItems.SPEEDRUNNER_SAPLING)
                .add(ModItems.DEAD_SPEEDRUNNER_SAPLING);

        valueLookupBuilder(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(Items.ANDESITE)
                .add(Items.DIORITE)
                .add(Items.GRANITE)
                .add(Items.MOSSY_COBBLESTONE)
                .add(Items.END_STONE);

        valueLookupBuilder(ItemTags.TRAPDOORS)
                .add(ModItems.SPEEDRUNNER_TRAPDOOR)
                .add(ModItems.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);

        valueLookupBuilder(ItemTags.TRIMMABLE_ARMOR)
                .forceAddTag(ModItemTags.SPEEDRUNNER_ARMOR);

        valueLookupBuilder(ItemTags.WOODEN_BUTTONS)
                .add(ModItems.WOODEN_SPEEDRUNNER_BUTTON)
                .add(ModItems.DEAD_WOODEN_SPEEDRUNNER_BUTTON);

        valueLookupBuilder(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModItems.WOODEN_SPEEDRUNNER_PRESSURE_PLATE)
                .add(ModItems.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE);

        valueLookupBuilder(ItemTags.WOODEN_SLABS)
                .add(ModItems.SPEEDRUNNER_SLAB)
                .add(ModItems.DEAD_SPEEDRUNNER_SLAB);

        valueLookupBuilder(ItemTags.WOODEN_STAIRS)
                .add(ModItems.SPEEDRUNNER_STAIRS)
                .add(ModItems.DEAD_SPEEDRUNNER_STAIRS);

        valueLookupBuilder(ItemTags.WOODEN_TRAPDOORS)
                .add(ModItems.WOODEN_SPEEDRUNNER_TRAPDOOR)
                .add(ModItems.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR);

        valueLookupBuilder(ItemTags.MINING_ENCHANTABLE)
                .add(ModItems.SPEEDRUNNER_SHEARS);

        valueLookupBuilder(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItems.SPEEDRUNNER_SHEARS)
                .add(ModItems.SPEEDRUNNER_FLINT_AND_STEEL)
                .add(ModItems.SPEEDRUNNER_BOW)
                .add(ModItems.SPEEDRUNNER_CROSSBOW)
                .add(ModItems.SPEEDRUNNER_SHIELD)
                .add(ModItems.INFINI_PEARL);

        valueLookupBuilder(ItemTags.BOW_ENCHANTABLE)
                .add(ModItems.SPEEDRUNNER_BOW);

        valueLookupBuilder(ItemTags.CROSSBOW_ENCHANTABLE)
                .add(ModItems.SPEEDRUNNER_CROSSBOW);

        valueLookupBuilder(ItemTags.SWORDS)
                .add(ModItems.SPEEDRUNNER_SWORD)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SWORD)
                .add(ModItems.DRAGONS_SWORD);

        valueLookupBuilder(ItemTags.SHOVELS)
                .add(ModItems.SPEEDRUNNER_SHOVEL)
                .add(ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);

        valueLookupBuilder(ItemTags.PICKAXES)
                .add(ModItems.SPEEDRUNNER_PICKAXE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);

        valueLookupBuilder(ItemTags.AXES)
                .add(ModItems.SPEEDRUNNER_AXE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_AXE);

        valueLookupBuilder(ItemTags.HOES)
                .add(ModItems.SPEEDRUNNER_HOE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HOE);

        valueLookupBuilder(ItemTags.HEAD_ARMOR)
                .add(ModItems.SPEEDRUNNER_HELMET)
                .add(ModItems.GOLDEN_SPEEDRUNNER_HELMET);

        valueLookupBuilder(ItemTags.CHEST_ARMOR)
                .add(ModItems.SPEEDRUNNER_CHESTPLATE)
                .add(ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);

        valueLookupBuilder(ItemTags.LEG_ARMOR)
                .add(ModItems.SPEEDRUNNER_LEGGINGS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);

        valueLookupBuilder(ItemTags.FOOT_ARMOR)
                .add(ModItems.SPEEDRUNNER_BOOTS)
                .add(ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

        valueLookupBuilder(ItemTags.PIGLIN_SAFE_ARMOR)
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