package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.item.ModBlockItemIds;
import net.dillon.speedrunnermod.item.ModItemIds;
import net.dillon.speedrunnermod.tag.ModBlockItemTags;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.tags.ItemTags;

import java.util.concurrent.CompletableFuture;

/**
 * Contains the entries of all new or already existing item tags.
 */
public class ModItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        tag(ConventionalItemTags.BOW_TOOLS)
                .add(ModItemIds.SPEEDRUNNER_BOW);

        tag(ConventionalItemTags.CROSSBOW_TOOLS)
                .add(ModItemIds.SPEEDRUNNER_CROSSBOW);

        tag(ConventionalItemTags.IGNITER_TOOLS)
                .add(ModItemIds.SPEEDRUNNER_FLINT_AND_STEEL);

        tag(ConventionalItemTags.IRON_INGOTS)
                .add(ModItemIds.SPEEDRUNNER_INGOT);

        tag(ConventionalItemTags.IRON_NUGGETS)
                .add(ModItemIds.SPEEDRUNNER_NUGGET);

        tag(ConventionalItemTags.SHEAR_TOOLS)
                .add(ModItemIds.SPEEDRUNNER_SHEARS);

        tag(ConventionalItemTags.SHIELD_TOOLS)
                .add(ModItemIds.GOLDEN_SHIELD)
                .add(ModItemIds.SPEEDRUNNER_SHIELD);

        tag(ConventionalItemTags.NAUTILUS_ARMORS)
                .addOptionalTag(ModItemTags.SPEEDRUNNER_NAUTILUSES);

        tag(ModItemTags.SPEED_BOOTS)
                .add(ModItemIds.SPEEDRUNNER_BOOTS)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_BOOTS);

        tag(ModItemTags.COOLDOWN_ENCHANTMENT_ITEMS)
                .addOptionalTag(ConventionalItemTags.SHIELD_TOOLS)
                .addOptionalTag(ModItemTags.THROWABLE_FIREBALLS)
                .add(ItemIds.ENDER_PEARL)
                .add(ModItemIds.INFINI_PEARL)
                .add(ItemIds.CHORUS_FRUIT)
                .add(ItemIds.WIND_CHARGE);

        tag(ModItemTags.DRAGON_TOOL_MATERIALS)
                .add(ModItemIds.DRAGONS_PEARL);

        tag(ModItemTags.DRAGON_PARTICLE_ITEMS)
                .add(ModItemIds.DRAGONS_PEARL)
                .add(ModItemIds.DRAGON_FIREBALL)
                .add(ModItemIds.DRAGON_UPGRADE_SMITHING_TEMPLATE)
                .add(ModItemIds.DRAGONS_SWORD);

        tag(ModItemTags.EXPERIENCE_BOTTLE_CRAFTABLES)
                .add(ItemIds.GLASS_BOTTLE)
                .add(ModItemIds.EXPERIENCE_FRAGMENT);

        tag(ModItemTags.FLESH)
                .add(ModItemIds.COOKED_FLESH)
                .add(ItemIds.ROTTEN_FLESH);

        tag(ModItemTags.FIREPROOF_BOATS)
                .add(ModItemIds.FIREPROOF_SPEEDRUNNER_BOAT)
                .add(ModItemIds.FIREPROOF_CRIMSON_BOAT)
                .add(ModItemIds.FIREPROOF_WARPED_BOAT);

        tag(ModItemTags.FIREPROOF_CHEST_BOATS)
                .add(ModItemIds.FIREPROOF_SPEEDRUNNER_CHEST_BOAT)
                .add(ModItemIds.FIREPROOF_CRIMSON_CHEST_BOAT)
                .add(ModItemIds.FIREPROOF_WARPED_CHEST_BOAT);

        tag(ModItemTags.FIREPROOF_ITEMS)
                .addOptionalTag(ModItemTags.FIREPROOF_BOATS)
                .addOptionalTag(ModItemTags.FIREPROOF_CHEST_BOATS)
                .add(ItemIds.BLAZE_ROD)
                .add(ItemIds.BLAZE_POWDER)
                .add(ItemIds.FIRE_CHARGE);

        tag(ModItemTags.GOLDEN_SPEEDRUNNER_TOOL_MATERIALS)
                .add(ItemIds.GOLD_INGOT);

        tag(ModItemTags.SPEEDRUNNER_SHIELD_REPAIRABLE)
                .add(ItemIds.GOLD_INGOT);

        tag(ModItemTags.IGNITABLES)
                .addOptionalTag(ConventionalItemTags.IGNITER_TOOLS)
                .add(ItemIds.FIRE_CHARGE);

        tag(ModItemTags.THROWABLE_FIREBALLS)
                .add(ItemIds.FIRE_CHARGE)
                .add(ModItemIds.DRAGON_FIREBALL);

        tag(ModItemTags.ENDER_EYE_DEATH_SOUND)
                .add(ItemIds.ENDER_EYE)
                .add(ModItemIds.ANNUL_EYE)
                .add(ModItemIds.SPEEDRUNNERS_EYE);

        tag(ModItemTags.FIRECHARGE_SOUND)
                .add(ModItemIds.INFERNO_EYE);

        tag(ModItemTags.PURPLE_EYE_PARTICLES)
                .add(ModItemIds.ANNUL_EYE);

        tag(ModItemTags.SMOKE_EYE_PARTICLES)
                .add(ModItemIds.INFERNO_EYE);

        tag(ModItemTags.BLUE_EYE_PARTICLES)
                .add(ModItemIds.SPEEDRUNNERS_EYE);

        tag(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)
                .addOptionalTag(ItemTags.PIGLIN_LOVED)
                .add(ItemIds.ENDER_PEARL)
                .add(ItemIds.BLAZE_POWDER)
                .remove(ItemIds.GOLD_INGOT)
                .remove(ItemIds.RAW_GOLD)
                .remove(BlockItemIds.GOLD_BLOCK.item())
                .remove(BlockItemIds.RAW_GOLD_BLOCK.item())
                .remove(BlockItemIds.GILDED_BLACKSTONE.item())
                .removeTag(ItemTags.GOLD_ORES);

        tag(ModItemTags.SPEED_BOOTS)
                .add(ModItemIds.SPEEDRUNNER_BOOTS)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_BOOTS);

        tag(ModItemTags.SPEEDRUNNER_ARMOR)
                .add(ModItemIds.SPEEDRUNNER_HELMET)
                .add(ModItemIds.SPEEDRUNNER_CHESTPLATE)
                .add(ModItemIds.SPEEDRUNNER_LEGGINGS)
                .add(ModItemIds.SPEEDRUNNER_BOOTS)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_HELMET)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_CHESTPLATE)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_LEGGINGS)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_BOOTS);

        tag(ModItemTags.SPEEDRUNNER_SHIELD_REPAIRABLE)
                .addOptionalTag(ItemTags.PLANKS)
                .add(ModItemIds.SPEEDRUNNER_INGOT);

        tag(ModItemTags.GOLDEN_SHIELD_REPAIRABLE)
                .addOptionalTag(ItemTags.PLANKS)
                .add(ItemIds.GOLD_INGOT);

        tag(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS)
                .add(ModItemIds.SPEEDRUNNER_INGOT);

        tag(ModItemTags.SPEEDRUNNER_HARNESSES)
                .add(ModItemIds.SPEEDRUNNER_HARNESS)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_HARNESS);

        tag(ModItemTags.SPEEDRUNNER_NAUTILUSES)
                .add(ModItemIds.SPEEDRUNNER_NAUTILUS_ARMOR)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_NAUTILUS_ARMOR);

        tag(ModItemTags.STICKS)
                .add(ItemIds.STICK)
                .add(ModItemIds.SPEEDRUNNER_STICK);

        tag(ModItemTags.SPEEDRUNNER_STICKS)
                .add(ModItemIds.SPEEDRUNNER_STICK);

        tag(ModItemTags.TOTEMS)
                .add(ItemIds.TOTEM_OF_UNDYING)
                .add(ModItemIds.SPEEDRUNNERS_TOTEM);

        tag(ModItemTags.UPGRADEABLE_GOLD)
                .add(ItemIds.GOLDEN_SWORD)
                .add(ItemIds.GOLDEN_PICKAXE)
                .add(ItemIds.GOLDEN_SHOVEL)
                .add(ItemIds.GOLDEN_AXE)
                .add(ItemIds.GOLDEN_HOE)
                .add(ItemIds.GOLDEN_HELMET)
                .add(ItemIds.GOLDEN_CHESTPLATE)
                .add(ItemIds.GOLDEN_LEGGINGS)
                .add(ItemIds.GOLDEN_BOOTS)
                .add(ItemIds.GOLDEN_SPEAR)
                .add(ItemIds.GOLDEN_NAUTILUS_ARMOR)
                .add(ModItemIds.SPEEDRUNNER_NAUTILUS_ARMOR);

        tag(ModItemTags.SPEEDRUNNERS_WORKBENCH_CONVERTABLE)
                .addOptionalTag(ConventionalItemTags.ENCHANTABLES)
                .add(ItemIds.BOOK)
                .add(ModItemIds.SPEEDRUNNER_INGOT);

        tag(ModItemTags.SPEEDRUNNERS_WORKBENCH_UPGRADEABLE)
                .addOptionalTag(ConventionalItemTags.ENCHANTABLES)
                .add(ItemIds.GOLDEN_NAUTILUS_ARMOR)
                .add(ModItemIds.SPEEDRUNNER_NAUTILUS_ARMOR);

        tag(ModBlockItemTags.DOOM_LOGS.item())
                .add(ModBlockItemIds.DOOM_LOG.item());

        tag(ModBlockItemTags.EXPERIENCE_ORES.item())
                .add(ModBlockItemIds.EXPERIENCE_ORE.item())
                .add(ModBlockItemIds.DEEPSLATE_EXPERIENCE_ORE.item())
                .add(ModBlockItemIds.NETHER_EXPERIENCE_ORE.item());

        tag(ModBlockItemTags.IGNEOUS_ORES.item())
                .add(ModBlockItemIds.IGNEOUS_ORE.item())
                .add(ModBlockItemIds.DEEPSLATE_IGNEOUS_ORE.item())
                .add(ModBlockItemIds.NETHER_IGNEOUS_ORE.item());

        tag(ModBlockItemTags.IRON_BLOCKS.item())
                .add(BlockItemIds.IRON_BLOCK.item())
                .add(ModBlockItemIds.SPEEDRUNNER_BLOCK.item());

        tag(ModBlockItemTags.SPEEDRUNNER_LOGS.item())
                .add(ModBlockItemIds.SPEEDRUNNER_LOG.item())
                .add(ModBlockItemIds.SPEEDRUNNER_WOOD.item());

        tag(ModBlockItemTags.DEAD_SPEEDRUNNER_LOGS.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_LOG.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_WOOD.item());

        tag(ModBlockItemTags.SPEEDRUNNER_PLANKS.item())
                .add(ModBlockItemIds.SPEEDRUNNER_PLANKS.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_PLANKS.item());

        tag(ModBlockItemTags.SPEEDRUNNER_FUELS.item())
                .addOptionalTag(ModBlockItemTags.SPEEDRUNNER_LOGS.item())
                .add(ModBlockItemIds.SPEEDRUNNER_SAPLING.item())
                .add(ModBlockItemIds.SPEEDRUNNER_SLAB.item())
                .add(ModBlockItemIds.SPEEDRUNNER_STAIRS.item())
                .add(ModBlockItemIds.SPEEDRUNNER_TRAPDOOR.item())
                .add(ModBlockItemIds.SPEEDRUNNER_PRESSURE_PLATE.item())
                .add(ModBlockItemIds.SPEEDRUNNER_FENCE.item())
                .add(ModBlockItemIds.SPEEDRUNNER_FENCE_GATE.item())
                .add(ModBlockItemIds.SPEEDRUNNER_BUTTON.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_BUSH.item());

        tag(ModBlockItemTags.SPEEDRUNNER_ORES.item())
                .add(ModBlockItemIds.SPEEDRUNNER_ORE.item())
                .add(ModBlockItemIds.DEEPSLATE_SPEEDRUNNER_ORE.item())
                .add(ModBlockItemIds.NETHER_SPEEDRUNNER_ORE.item());

        tag(ModBlockItemTags.SPEEDRUNNER_SAPLING_PLACEABLES.item())
                .addOptionalTag(ItemTags.SAND)
                .add(BlockItemIds.NETHERRACK.item())
                .add(BlockItemIds.CRIMSON_NYLIUM.item())
                .add(BlockItemIds.WARPED_NYLIUM.item());

        tag(ModItemTags.AdvancementCriterions.EYE_OF_ANNUL)
                .add(ModItemIds.ENDER_MATTER)
                .add(ItemIds.ENDER_EYE);

        tag(ModItemTags.AdvancementCriterions.BLAZE_SPOTTER)
                .add(ModItemIds.INFERNO_EYE)
                .add(ItemIds.LAVA_BUCKET);

        tag(ModItemTags.AdvancementCriterions.DRAGONS_PEARL)
                .add(ItemIds.BLAZE_POWDER)
                .add(ModItemIds.SPEEDRUNNERS_EYE);

        tag(ModItemTags.AdvancementCriterions.DRAGONS_SWORD)
                .add(ModItemIds.SPEEDRUNNER_SWORD)
                .add(ModItemIds.DRAGONS_PEARL);

        tag(ModItemTags.AdvancementCriterions.ENDER_THRUSTER)
                .add(ItemIds.ENDER_PEARL)
                .add(ModItemIds.SPEEDRUNNERS_EYE);

        tag(ModItemTags.AdvancementCriterions.INFERNO_EYE)
                .add(ItemIds.ENDER_PEARL)
                .add(ItemIds.BLAZE_POWDER);

        tag(ModItemTags.AdvancementCriterions.RAID_ERADICATOR)
                .addOptionalTag(ModItemTags.TOTEMS)
                .add(ItemIds.ENCHANTED_GOLDEN_APPLE);

        tag(ModItemTags.AdvancementCriterions.PIGLIN_AWAKENER)
                .addOptionalTag(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)
                .add(ItemIds.GOLD_INGOT);

        tag(ModItemTags.AdvancementCriterions.SPEEDRUNNER_FLINT_AND_STEEL)
                .add(ItemIds.FLINT)
                .add(ItemIds.FLINT_AND_STEEL)
                .add(ModItemIds.SPEEDRUNNER_INGOT);

        tag(ModItemTags.AdvancementCriterions.SPEEDRUNNERS_WORKBENCH)
                .addOptionalTag(ModBlockItemTags.SPEEDRUNNER_PLANKS.item())
                .add(ModItemIds.SPEEDRUNNER_INGOT);

        tag(ItemTags.BOATS)
                .add(ModItemIds.SPEEDRUNNER_BOAT)
                .add(ModItemIds.FIREPROOF_SPEEDRUNNER_BOAT)
                .add(ModItemIds.DEAD_SPEEDRUNNER_BOAT)
                .add(ModItemIds.CRIMSON_BOAT)
                .add(ModItemIds.FIREPROOF_CRIMSON_BOAT)
                .add(ModItemIds.WARPED_BOAT)
                .add(ModItemIds.FIREPROOF_WARPED_BOAT);

        tag(ItemTags.CHEST_BOATS)
                .add(ModItemIds.SPEEDRUNNER_CHEST_BOAT)
                .add(ModItemIds.FIREPROOF_SPEEDRUNNER_CHEST_BOAT)
                .add(ModItemIds.DEAD_SPEEDRUNNER_CHEST_BOAT)
                .add(ModItemIds.CRIMSON_CHEST_BOAT)
                .add(ModItemIds.FIREPROOF_CRIMSON_CHEST_BOAT)
                .add(ModItemIds.WARPED_CHEST_BOAT)
                .add(ModItemIds.FIREPROOF_WARPED_CHEST_BOAT);

        tag(ItemTags.CREEPER_IGNITERS)
                .add(ModItemIds.SPEEDRUNNER_FLINT_AND_STEEL);

        tag(ItemTags.MELEE_WEAPON_ENCHANTABLE)
                .addOptionalTag(ItemTags.AXES);

        tag(ItemTags.WOODEN_DOORS)
                .add(ModBlockItemIds.SPEEDRUNNER_DOOR.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_DOOR.item());

        tag(ItemTags.FENCE_GATES)
                .add(ModBlockItemIds.SPEEDRUNNER_FENCE_GATE.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_FENCE_GATE.item());

        tag(ItemTags.WOODEN_FENCES)
                .add(ModBlockItemIds.SPEEDRUNNER_FENCE.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_FENCE.item());

        tag(ItemTags.LEAVES)
                .add(ModBlockItemIds.SPEEDRUNNER_LEAVES.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_LEAVES.item())
                .add(ModBlockItemIds.DOOM_LEAVES.item());

        tag(ItemTags.LOGS)
                .addOptionalTag(ModBlockItemTags.SPEEDRUNNER_LOGS.item())
                .addOptionalTag(ModBlockItemTags.DOOM_LOGS.item());

        tag(ItemTags.PIGLIN_FOOD)
                .add(ModItemIds.PIGLIN_PORK)
                .add(ModItemIds.COOKED_PIGLIN_PORK);

        tag(ItemTags.PIGLIN_LOVED)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_SWORD)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_SHOVEL)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_PICKAXE)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_AXE)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_HOE)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_HELMET)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_CHESTPLATE)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_LEGGINGS)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_BOOTS)
                .add(ModItemIds.GOLDEN_UPGRADE_SMITHING_TEMPLATE);

        tag(ItemTags.PLANKS)
                .add(ModBlockItemIds.SPEEDRUNNER_PLANKS.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_PLANKS.item());

        tag(ItemTags.SAPLINGS)
                .add(ModBlockItemIds.SPEEDRUNNER_SAPLING.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_SAPLING.item());

        tag(ItemTags.STONE_CRAFTING_MATERIALS)
                .add(BlockItemIds.ANDESITE.item())
                .add(BlockItemIds.DIORITE.item())
                .add(BlockItemIds.GRANITE.item())
                .add(BlockItemIds.MOSSY_COBBLESTONE.item())
                .add(BlockItemIds.END_STONE.item());

        tag(ItemTags.TRIMMABLE_ARMOR)
                .addOptionalTag(ModItemTags.SPEEDRUNNER_ARMOR);

        tag(ItemTags.HARNESSES)
                .addOptionalTag(ModItemTags.SPEEDRUNNER_HARNESSES);

        tag(ItemTags.WOODEN_BUTTONS)
                .add(ModBlockItemIds.SPEEDRUNNER_BUTTON.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_BUTTON.item());

        tag(ItemTags.WOODEN_PRESSURE_PLATES)
                .add(ModBlockItemIds.SPEEDRUNNER_PRESSURE_PLATE.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_PRESSURE_PLATE.item());

        tag(ItemTags.WOODEN_SLABS)
                .add(ModBlockItemIds.SPEEDRUNNER_SLAB.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_SLAB.item());

        tag(ItemTags.WOODEN_STAIRS)
                .add(ModBlockItemIds.SPEEDRUNNER_STAIRS.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_STAIRS.item());

        tag(ItemTags.WOODEN_TRAPDOORS)
                .add(ModBlockItemIds.SPEEDRUNNER_TRAPDOOR.item())
                .add(ModBlockItemIds.DEAD_SPEEDRUNNER_TRAPDOOR.item());

        tag(ItemTags.MINING_ENCHANTABLE)
                .add(ModItemIds.SPEEDRUNNER_SHEARS);

        tag(ItemTags.DURABILITY_ENCHANTABLE)
                .add(ModItemIds.SPEEDRUNNER_SHEARS)
                .add(ModItemIds.SPEEDRUNNER_FLINT_AND_STEEL)
                .add(ModItemIds.SPEEDRUNNER_BOW)
                .add(ModItemIds.SPEEDRUNNER_CROSSBOW)
                .add(ModItemIds.SPEEDRUNNER_SHIELD)
                .add(ModItemIds.GOLDEN_SHIELD)
                .add(ModItemIds.KNOCKBACK_STICK)
                .add(ModItemIds.INFINI_PEARL);

        tag(ItemTags.BOW_ENCHANTABLE)
                .add(ModItemIds.SPEEDRUNNER_BOW);

        tag(ItemTags.CROSSBOW_ENCHANTABLE)
                .add(ModItemIds.SPEEDRUNNER_CROSSBOW);

        tag(ItemTags.SPEARS)
                .add(ModItemIds.SPEEDRUNNER_SPEAR)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_SPEAR);

        tag(ItemTags.SWORDS)
                .add(ModItemIds.SPEEDRUNNER_SWORD)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_SWORD)
                .add(ModItemIds.DRAGONS_SWORD);

        tag(ItemTags.SHOVELS)
                .add(ModItemIds.SPEEDRUNNER_SHOVEL)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_SHOVEL);

        tag(ItemTags.PICKAXES)
                .add(ModItemIds.SPEEDRUNNER_PICKAXE)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_PICKAXE);

        tag(ItemTags.AXES)
                .add(ModItemIds.SPEEDRUNNER_AXE)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_AXE);

        tag(ItemTags.HOES)
                .add(ModItemIds.SPEEDRUNNER_HOE)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_HOE);

        tag(ItemTags.HEAD_ARMOR)
                .add(ModItemIds.SPEEDRUNNER_HELMET)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_HELMET);

        tag(ItemTags.CHEST_ARMOR)
                .add(ModItemIds.SPEEDRUNNER_CHESTPLATE)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_CHESTPLATE);

        tag(ItemTags.LEG_ARMOR)
                .add(ModItemIds.SPEEDRUNNER_LEGGINGS)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_LEGGINGS);

        tag(ItemTags.FOOT_ARMOR)
                .add(ModItemIds.SPEEDRUNNER_BOOTS)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_BOOTS);

        tag(ItemTags.PIGLIN_SAFE_ARMOR)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_HELMET)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_CHESTPLATE)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_LEGGINGS)
                .add(ModItemIds.GOLDEN_SPEEDRUNNER_BOOTS);
    }
}