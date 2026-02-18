package net.dillon.speedrunnermod.data.generator;

import com.google.common.collect.ImmutableList;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.DragonFireballRecipe;
import net.dillon.speedrunnermod.recipe.InventoryPreserverRecipe;
import net.dillon.speedrunnermod.recipe.PiglinAwakenerRecipe;
import net.dillon.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.ComplexRecipeJsonBuilder;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

/**
 * Used to modify {@code vanilla recipes} and create {@code Speedrunner Mod} recipes.
 */
public class ModRecipeGenerator extends FabricRecipeProvider {
    private static final ImmutableList<ItemConvertible> IGNEOUS_ORES = ImmutableList.of(ModBlocks.IGNEOUS_ORE, ModBlocks.DEEPSLATE_IGNEOUS_ORE, ModBlocks.NETHER_IGNEOUS_ORE);
    private static final ImmutableList<ItemConvertible> EXPERIENCE_ORES = ImmutableList.of(ModBlocks.EXPERIENCE_ORE, ModBlocks.DEEPSLATE_EXPERIENCE_ORE, ModBlocks.NETHER_EXPERIENCE_ORE);
    private static final ImmutableList<ItemConvertible> SPEEDRUNNER_ORES_AND_BLOCKS = ImmutableList.of(ModBlocks.SPEEDRUNNER_ORE, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE, ModBlocks.NETHER_SPEEDRUNNER_ORE, ModItems.RAW_SPEEDRUNNER);

    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {

            @Override
            public void generate() {
                RecipeGeneratorHelper helper = new RecipeGeneratorHelper(wrapperLookup, exporter);

                helper.createAxe(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_AXE);
                helper.createHoe(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_HOE);
                helper.createPickaxe(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_PICKAXE);
                helper.createShovel(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_SHOVEL);
                helper.createSword(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_SWORD);

                helper.createHelmet(ModItems.SPEEDRUNNER_INGOT, ModItems.SPEEDRUNNER_HELMET);
                helper.createChestplate(ModItems.SPEEDRUNNER_INGOT, ModItems.SPEEDRUNNER_CHESTPLATE);
                helper.createLeggings(ModItems.SPEEDRUNNER_INGOT, ModItems.SPEEDRUNNER_LEGGINGS);
                helper.createBoots(ModItems.SPEEDRUNNER_INGOT, ModItems.SPEEDRUNNER_BOOTS);

                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_SWORD, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_SWORD);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_SHOVEL, RecipeCategory.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_PICKAXE, RecipeCategory.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_AXE, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_AXE);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_HOE, RecipeCategory.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_HOE);

                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_HELMET, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_HELMET);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_CHESTPLATE, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_LEGGINGS, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_BOOTS, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_BOOTS);

                helper.offerBurnableMaterial(IGNEOUS_ORES, ModItems.IGNEOUS_ROCK, 0.6F, "igneous_rock");
                helper.offerBurnableMaterial(EXPERIENCE_ORES, ModItems.EXPERIENCE_FRAGMENT, 3.0F, "experience_fragment");
                helper.offerBurnableMaterial(SPEEDRUNNER_ORES_AND_BLOCKS, ModItems.SPEEDRUNNER_INGOT, 0.85F, "speedrunner_ingot");

                helper.createCookableFood(Items.ROTTEN_FLESH, ModItems.COOKED_FLESH);
                helper.createCookableFood(ModItems.ROTTEN_SPEEDRUNNER_BULK, Items.ROTTEN_FLESH);
                helper.createCookableFood(ModItems.PIGLIN_PORK, ModItems.COOKED_PIGLIN_PORK);

                helper.createFireproofBoatSet(ModItems.SPEEDRUNNER_BOAT, ModItems.SPEEDRUNNER_CHEST_BOAT, ModItems.FIREPROOF_SPEEDRUNNER_BOAT, ModItems.FIREPROOF_SPEEDRUNNER_CHEST_BOAT, ModBlocks.SPEEDRUNNER_PLANKS, "fireproof_speedrunner_boat");
                helper.createBoatSet(ModItems.DEAD_SPEEDRUNNER_BOAT, ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
                helper.createFireproofBoatSet(ModItems.CRIMSON_BOAT, ModItems.CRIMSON_CHEST_BOAT, ModItems.FIREPROOF_CRIMSON_BOAT, ModItems.FIREPROOF_CRIMSON_CHEST_BOAT, Blocks.CRIMSON_PLANKS, "fireproof_crimson_boat");
                helper.createFireproofBoatSet(ModItems.WARPED_BOAT, ModItems.WARPED_CHEST_BOAT, ModItems.FIREPROOF_WARPED_BOAT, ModItems.FIREPROOF_WARPED_CHEST_BOAT, Blocks.WARPED_PLANKS, "fireproof_warped_boat");

                helper.offerBannerRecipe(Items.BLACK_BANNER, Blocks.BLACK_WOOL);
                helper.offerBannerRecipe(Items.BLUE_BANNER, Blocks.BLUE_WOOL);
                helper.offerBannerRecipe(Items.BROWN_BANNER, Blocks.BROWN_WOOL);
                helper.offerBannerRecipe(Items.CYAN_BANNER, Blocks.CYAN_WOOL);
                helper.offerBannerRecipe(Items.GRAY_BANNER, Blocks.GRAY_WOOL);
                helper.offerBannerRecipe(Items.GREEN_BANNER, Blocks.GREEN_WOOL);
                helper.offerBannerRecipe(Items.LIGHT_BLUE_BANNER, Blocks.LIGHT_BLUE_WOOL);
                helper.offerBannerRecipe(Items.LIGHT_GRAY_BANNER, Blocks.LIGHT_GRAY_WOOL);
                helper.offerBannerRecipe(Items.LIME_BANNER, Blocks.LIME_WOOL);
                helper.offerBannerRecipe(Items.MAGENTA_BANNER, Blocks.MAGENTA_WOOL);
                helper.offerBannerRecipe(Items.ORANGE_BANNER, Blocks.ORANGE_WOOL);
                helper.offerBannerRecipe(Items.PINK_BANNER, Blocks.PINK_WOOL);
                helper.offerBannerRecipe(Items.PURPLE_BANNER, Blocks.PURPLE_WOOL);
                helper.offerBannerRecipe(Items.RED_BANNER, Blocks.RED_WOOL);
                helper.offerBannerRecipe(Items.WHITE_BANNER, Blocks.WHITE_WOOL);
                helper.offerBannerRecipe(Items.YELLOW_BANNER, Blocks.YELLOW_WOOL);

                helper.offerModdedReversibleCompactingRecipes(
                        RecipeCategory.MISC,
                        ModItems.SPEEDRUNNER_INGOT,
                        RecipeCategory.BUILDING_BLOCKS,
                        ModBlocks.SPEEDRUNNER_BLOCK,
                        "speedrunner_block_from_speedrunner_ingot",
                        "speedrunner_block",
                        "speedrunner_ingot_from_speedrunner_block",
                        "speedrunner_ingot"
                );
                helper.offerModdedReversibleCompactingRecipes(
                        RecipeCategory.MISC,
                        ModItems.RAW_SPEEDRUNNER,
                        RecipeCategory.MISC,
                        ModItems.RAW_SPEEDRUNNER_BLOCK,
                        "raw_speedrunner_block_from_raw_speedrunner_ingot",
                        "raw_speedrunner_block",
                        "raw_speedrunner_from_raw_speedrunner_block",
                        "raw_speedrunner"
                );
                helper.offerModdedReversibleCompactingRecipes(
                        RecipeCategory.MISC,
                        ModItems.SPEEDRUNNER_NUGGET,
                        RecipeCategory.MISC,
                        ModItems.SPEEDRUNNER_INGOT,
                        "speedrunner_ingot_from_speedrunner_nuggets",
                        "speedrunner_ingot",
                        "speedrunner_nuggets_from_speedrunner_ingot",
                        "speedrunner_nugget"
                );

                helper.createModdedFenceRecipe(ModBlocks.DEAD_SPEEDRUNNER_FENCE, ModBlocks.DEAD_SPEEDRUNNER_PLANKS, true);
                helper.createModdedFenceRecipe(ModBlocks.SPEEDRUNNER_FENCE, ModBlocks.SPEEDRUNNER_PLANKS, false);

                helper.createModdedFenceGateRecipe(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE, ModBlocks.DEAD_SPEEDRUNNER_PLANKS, true);
                helper.createModdedFenceGateRecipe(ModBlocks.SPEEDRUNNER_FENCE_GATE, ModBlocks.SPEEDRUNNER_PLANKS, false);

                helper.createSign(ModBlocks.DEAD_SPEEDRUNNER_SIGN, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
                helper.createSign(ModBlocks.SPEEDRUNNER_SIGN, ModBlocks.SPEEDRUNNER_PLANKS);

                this.createDoorRecipe(ModBlocks.SPEEDRUNNER_DOOR, Ingredient.ofItem(ModItems.SPEEDRUNNER_INGOT))
                        .criterion(hasItem(ModItems.SPEEDRUNNER_INGOT), this.conditionsFromItem(ModItems.SPEEDRUNNER_INGOT))
                        .offerTo(this.exporter);
                this.createDoorRecipe(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR, Ingredient.ofItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .group("wooden_door")
                        .offerTo(this.exporter);
                this.createDoorRecipe(ModBlocks.WOODEN_SPEEDRUNNER_DOOR, Ingredient.ofItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .group("wooden_door")
                        .offerTo(this.exporter);

                this.offer2x2CompactingRecipe(RecipeCategory.REDSTONE, ModBlocks.SPEEDRUNNER_TRAPDOOR, ModItems.SPEEDRUNNER_INGOT);
                this.createTrapdoorRecipe(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR, Ingredient.ofItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .group("wooden_trapdoor")
                        .offerTo(this.exporter);
                this.createTrapdoorRecipe(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR, Ingredient.ofItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .group("wooden_trapdoor")
                        .offerTo(this.exporter);

                this.createButtonRecipe(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON, Ingredient.ofItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .offerTo(this.exporter);
                this.createButtonRecipe(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON, Ingredient.ofItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .offerTo(this.exporter);

                this.createStairsRecipe(ModBlocks.DEAD_SPEEDRUNNER_STAIRS, Ingredient.ofItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .group("wooden_stairs")
                        .offerTo(this.exporter);
                this.createStairsRecipe(ModBlocks.SPEEDRUNNER_STAIRS, Ingredient.ofItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .group("wooden_stairs")
                        .offerTo(this.exporter);

                this.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEAD_SPEEDRUNNER_SLAB, Ingredient.ofItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .group("wooden_slab")
                        .offerTo(this.exporter);
                this.createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPEEDRUNNER_SLAB, Ingredient.ofItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .group("wooden_slab")
                        .offerTo(this.exporter);

                this.offerPressurePlateRecipe(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE, ModItems.SPEEDRUNNER_INGOT);
                this.createPressurePlateRecipe(RecipeCategory.REDSTONE, ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE, Ingredient.ofItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModItems.SPEEDRUNNER_INGOT))
                        .group("wooden_pressure_plate")
                        .offerTo(this.exporter);
                this.createPressurePlateRecipe(RecipeCategory.REDSTONE, ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE, Ingredient.ofItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModItems.SPEEDRUNNER_INGOT))
                        .group("wooden_pressure_plate")
                        .offerTo(this.exporter);

                this.offerPlanksRecipe(ModBlocks.DEAD_SPEEDRUNNER_PLANKS, ModItemTags.Block.DEAD_SPEEDRUNNER_LOGS, 4);
                this.offerPlanksRecipe(ModBlocks.SPEEDRUNNER_PLANKS, ModItemTags.Block.SPEEDRUNNER_LOGS, 4);

                this.offerBarkBlockRecipe(ModBlocks.DEAD_SPEEDRUNNER_WOOD, ModBlocks.DEAD_SPEEDRUNNER_LOG);
                this.offerBarkBlockRecipe(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD, ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG);
                this.offerBarkBlockRecipe(ModBlocks.SPEEDRUNNER_WOOD, ModBlocks.SPEEDRUNNER_LOG);
                this.offerBarkBlockRecipe(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD, ModBlocks.STRIPPED_SPEEDRUNNER_LOG);

                this.offerHangingSignRecipe(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG);
                this.offerHangingSignRecipe(ModBlocks.SPEEDRUNNER_HANGING_SIGN, ModBlocks.STRIPPED_SPEEDRUNNER_LOG);

                CookingRecipeJsonBuilder.createSmelting(
                                Ingredient.ofItems(
                                        ModItems.SPEEDRUNNER_PICKAXE,
                                        ModItems.SPEEDRUNNER_SHOVEL,
                                        ModItems.SPEEDRUNNER_AXE,
                                        ModItems.SPEEDRUNNER_HOE,
                                        ModItems.SPEEDRUNNER_SWORD,
                                        ModItems.SPEEDRUNNER_HELMET,
                                        ModItems.SPEEDRUNNER_CHESTPLATE,
                                        ModItems.SPEEDRUNNER_LEGGINGS,
                                        ModItems.SPEEDRUNNER_BOOTS
                                ),
                                RecipeCategory.MISC,
                                ModItems.SPEEDRUNNER_NUGGET,
                                0.2F,
                                200
                        )
                        .criterion("has_speedrunner_pickaxe", this.conditionsFromItem(ModItems.SPEEDRUNNER_PICKAXE))
                        .criterion("has_speedrunner_shovel", this.conditionsFromItem(ModItems.SPEEDRUNNER_SHOVEL))
                        .criterion("has_speedrunner_axe", this.conditionsFromItem(ModItems.SPEEDRUNNER_AXE))
                        .criterion("has_speedrunner_hoe", this.conditionsFromItem(ModItems.SPEEDRUNNER_HOE))
                        .criterion("has_speedrunner_sword", this.conditionsFromItem(ModItems.SPEEDRUNNER_SWORD))
                        .criterion("has_speedrunner_helmet", this.conditionsFromItem(ModItems.SPEEDRUNNER_HELMET))
                        .criterion("has_speedrunner_chestplate", this.conditionsFromItem(ModItems.SPEEDRUNNER_CHESTPLATE))
                        .criterion("has_speedrunner_leggings", this.conditionsFromItem(ModItems.SPEEDRUNNER_LEGGINGS))
                        .criterion("has_speedrunner_boots", this.conditionsFromItem(ModItems.SPEEDRUNNER_BOOTS))
                        .offerTo(this.exporter, getSmeltingItemPath(ModItems.SPEEDRUNNER_NUGGET));

                CookingRecipeJsonBuilder.createBlasting(
                                Ingredient.ofItems(
                                        ModItems.SPEEDRUNNER_PICKAXE,
                                        ModItems.SPEEDRUNNER_SHOVEL,
                                        ModItems.SPEEDRUNNER_AXE,
                                        ModItems.SPEEDRUNNER_HOE,
                                        ModItems.SPEEDRUNNER_SWORD,
                                        ModItems.SPEEDRUNNER_HELMET,
                                        ModItems.SPEEDRUNNER_CHESTPLATE,
                                        ModItems.SPEEDRUNNER_LEGGINGS,
                                        ModItems.SPEEDRUNNER_BOOTS
                                ),
                                RecipeCategory.MISC,
                                ModItems.SPEEDRUNNER_NUGGET,
                                0.2F,
                                200
                        )
                        .criterion("has_speedrunner_pickaxe", this.conditionsFromItem(ModItems.SPEEDRUNNER_PICKAXE))
                        .criterion("has_speedrunner_shovel", this.conditionsFromItem(ModItems.SPEEDRUNNER_SHOVEL))
                        .criterion("has_speedrunner_axe", this.conditionsFromItem(ModItems.SPEEDRUNNER_AXE))
                        .criterion("has_speedrunner_hoe", this.conditionsFromItem(ModItems.SPEEDRUNNER_HOE))
                        .criterion("has_speedrunner_sword", this.conditionsFromItem(ModItems.SPEEDRUNNER_SWORD))
                        .criterion("has_speedrunner_helmet", this.conditionsFromItem(ModItems.SPEEDRUNNER_HELMET))
                        .criterion("has_speedrunner_chestplate", this.conditionsFromItem(ModItems.SPEEDRUNNER_CHESTPLATE))
                        .criterion("has_speedrunner_leggings", this.conditionsFromItem(ModItems.SPEEDRUNNER_LEGGINGS))
                        .criterion("has_speedrunner_boots", this.conditionsFromItem(ModItems.SPEEDRUNNER_BOOTS))
                        .offerTo(this.exporter, getBlastingItemPath(ModItems.SPEEDRUNNER_NUGGET));

                this.createShapeless(RecipeCategory.MISC, ModItems.ANNUL_EYE)
                        .input(Items.ENDER_PEARL)
                        .input(Items.BLAZE_POWDER)
                        .input(Items.ENDER_EYE)
                        .criterion("has_items", this.conditionsFromTag(ModItemTags.AdvancementCriterions.EYE_OF_ANNUL))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.MISC, ModItems.BLAZE_SPOTTER)
                        .input(Items.ENDER_PEARL)
                        .input(Items.FIRE_CHARGE)
                        .input(Items.LAVA_BUCKET)
                        .criterion("has_items", this.conditionsFromTag(ModItemTags.AdvancementCriterions.BLAZE_SPOTTER))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.MISC, ModItems.DRAGONS_PEARL)
                        .input(Items.ENDER_PEARL)
                        .input(Items.BLAZE_POWDER)
                        .input(ModItems.SPEEDRUNNERS_EYE)
                        .criterion("has_items", this.conditionsFromTag(ModItemTags.AdvancementCriterions.DRAGONS_PEARL))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.COMBAT, ModItems.DRAGONS_SWORD)
                        .input(ModItems.SPEEDRUNNER_SWORD)
                        .input(ModItems.DRAGONS_PEARL)
                        .input(ModItems.ENDER_MATTER)
                        .criterion("has_items", this.conditionsFromTag(ModItemTags.AdvancementCriterions.DRAGONS_SWORD))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.MISC, ModItems.ENDER_THRUSTER)
                        .input(Items.ENDER_PEARL)
                        .input(ModItems.SPEEDRUNNERS_EYE)
                        .criterion("has_items", this.conditionsFromTag(ModItemTags.AdvancementCriterions.ENDER_THRUSTER))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.MISC, ModItems.INFERNO_EYE)
                        .input(Items.ENDER_PEARL)
                        .input(Items.FIRE_CHARGE)
                        .criterion("has_items", this.conditionsFromTag(ModItemTags.AdvancementCriterions.INFERNO_EYE))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.MISC, ModItems.RAID_ERADICATOR)
                        .input(Items.ENDER_PEARL)
                        .input(Items.ENCHANTED_GOLDEN_APPLE)
                        .input(ModItems.SPEEDRUNNERS_EYE)
                        .criterion("has_items", this.conditionsFromTag(ModItemTags.AdvancementCriterions.RAID_ERADICATOR))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.MISC, ModItems.SPEEDRUNNERS_EYE)
                        .input(Items.ENDER_PEARL)
                        .input(ModItems.SPEEDRUNNER_INGOT)
                        .criterion("has_items", this.conditionsFromItem(ModItems.SPEEDRUNNER_INGOT))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.TOOLS, ModItems.SPEEDRUNNER_FLINT_AND_STEEL)
                        .input(Items.FLINT)
                        .input(ModItems.SPEEDRUNNER_INGOT)
                        .criterion("has_speedrunner_ingot", this.conditionsFromTag(ModItemTags.AdvancementCriterions.SPEEDRUNNER_FLINT_AND_STEEL))
                        .group("flint_and_steels")
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.TOOLS, ModItems.SPEEDRUNNER_PADDLE)
                        .input('I', ModItems.SPEEDRUNNER_NUGGET)
                        .input('S', ModItemTags.SPEEDRUNNER_STICKS)
                        .pattern("I")
                        .pattern("S")
                        .pattern("I")
                        .criterion("has_speedrunner_ingot", this.conditionsFromItem(ModItems.SPEEDRUNNER_NUGGET))
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.MISC, ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE, 2)
                        .input('#', Items.GOLD_INGOT)
                        .input('C', Items.GOLD_BLOCK)
                        .input('S', ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE)
                        .pattern("#S#")
                        .pattern("#C#")
                        .pattern("###")
                        .showNotification(true)
                        .criterion("has_gold_block", this.conditionsFromItem(Items.GOLD_INGOT))
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPEEDRUNNERS_WORKBENCH)
                        .input('I', ModItems.SPEEDRUNNER_INGOT)
                        .input('B', ModItems.SPEEDRUNNER_BLOCK)
                        .input('P', ModItemTags.Block.SPEEDRUNNER_PLANKS)
                        .pattern("III")
                        .pattern("PBP")
                        .pattern("PPP")
                        .criterion("has_planks", this.conditionsFromTag(ModItemTags.AdvancementCriterions.SPEEDRUNNERS_WORKBENCH))
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.COMBAT, ModItems.SPEEDRUNNER_BOW)
                        .input('/', ModItemTags.SPEEDRUNNER_STICKS)
                        .input('S', Items.STRING)
                        .pattern(" /S")
                        .pattern("/ S")
                        .pattern(" /S")
                        .criterion("has_string", this.conditionsFromItem(Items.STRING))
                        .group("bows")
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.COMBAT, ModItems.SPEEDRUNNER_CROSSBOW)
                        .input('~', Items.STRING)
                        .input('#', ModItemTags.SPEEDRUNNER_STICKS)
                        .input('S', ModItems.SPEEDRUNNER_INGOT)
                        .input('$', Items.TRIPWIRE_HOOK)
                        .pattern("#S#")
                        .pattern("~$~")
                        .pattern(" # ")
                        .criterion("has_speedrunner_ingot", this.conditionsFromItem(ModItems.SPEEDRUNNER_INGOT))
                        .group("crossbows")
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.TOOLS, ModItems.SPEEDRUNNER_SHEARS)
                        .input('#', ModItems.SPEEDRUNNER_INGOT)
                        .pattern(" #")
                        .pattern("# ")
                        .criterion("has_speedrunner_ingot", this.conditionsFromItem(ModItems.SPEEDRUNNER_INGOT))
                        .group("shears")
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.COMBAT, ModItems.SPEEDRUNNER_SHIELD)
                        .input('W', ItemTags.PLANKS)
                        .input('o', ModItems.SPEEDRUNNER_INGOT)
                        .pattern("WoW")
                        .pattern("WWW")
                        .pattern(" W ")
                        .criterion("has_speedrunner_ingot", this.conditionsFromItem(ModItems.SPEEDRUNNER_INGOT))
                        .group("shields")
                        .offerTo(this.exporter);

                ComplexRecipeJsonBuilder.create(SpeedrunnerShieldDecorationRecipe::new).offerTo(this.exporter, "speedrunner_shield_decoration");
                ComplexRecipeJsonBuilder.create(PiglinAwakenerRecipe::new).offerTo(this.exporter, "piglin_awakener");
                ComplexRecipeJsonBuilder.create(DragonFireballRecipe::new).offerTo(this.exporter, "dragons_fireball");
                ComplexRecipeJsonBuilder.create(InventoryPreserverRecipe::new).offerTo(this.exporter, "inventory_preserver");

                helper.createStickRecipe(true);
                helper.createStickRecipe(false);

                helper.createReversePlankRecipe(true);
                helper.createReversePlankRecipe(false);

                this.createShaped(RecipeCategory.BUILDING_BLOCKS, ModItems.FLESH_BLOCK)
                        .input('#', ModItemTags.FLESH)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .criterion("has_flesh_item", this.conditionsFromTag(ModItemTags.FLESH))
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.MISC, Blocks.OAK_PLANKS)
                        .input('/', Items.STICK)
                        .group("planks")
                        .pattern("//")
                        .pattern("//")
                        .criterion("has_stick", this.conditionsFromItem(Items.STICK))
                        .offerTo(this.exporter, helper.speedrunnerModRecipe("oak_planks_from_sticks"));

                this.createShaped(RecipeCategory.MISC, Blocks.OBSIDIAN)
                        .input('#', ModItems.IGNEOUS_ROCK)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .criterion("has_igneous_rock", this.conditionsFromItem(ModItems.IGNEOUS_ROCK))
                        .offerTo(this.exporter, helper.speedrunnerModRecipe("obsidian_from_igneous_rocks"));

                this.createShaped(RecipeCategory.FOOD, Items.ENCHANTED_GOLDEN_APPLE)
                        .input('a', Items.GOLDEN_APPLE)
                        .input('B', Items.GOLD_BLOCK)
                        .pattern("BBB")
                        .pattern("BaB")
                        .pattern("BBB")
                        .criterion("has_gold_block", this.conditionsFromItem(Items.GOLD_BLOCK))
                        .offerTo(this.exporter, helper.speedrunnerModRecipe("enchanted_golden_apple"));

                this.createShaped(RecipeCategory.COMBAT, Items.TOTEM_OF_UNDYING)
                        .input('e', Items.ENCHANTED_GOLDEN_APPLE)
                        .input('B', Items.GOLD_BLOCK)
                        .pattern("BBB")
                        .pattern("BeB")
                        .pattern("BBB")
                        .criterion("has_enchanted_golden_apple", this.conditionsFromItem(Items.ENCHANTED_GOLDEN_APPLE))
                        .offerTo(this.exporter, helper.speedrunnerModRecipe("totem_of_undying"));

                this.createShaped(RecipeCategory.BREWING, Items.BLAZE_ROD)
                        .input('P', Items.BLAZE_POWDER)
                        .input('/', ModItemTags.SPEEDRUNNER_STICKS)
                        .pattern("P")
                        .pattern("P")
                        .pattern("/")
                        .criterion("has_blaze_powder", this.conditionsFromItem(Items.BLAZE_POWDER))
                        .offerTo(this.exporter, helper.speedrunnerModRecipe("blaze_rod"));

                this.createShapeless(RecipeCategory.MISC, Items.STRING, 4)
                        .input(ItemTags.WOOL)
                        .criterion("has_string", this.conditionsFromItem(Items.STRING))
                        .offerTo(this.exporter, helper.speedrunnerModRecipe("string_from_wool"));

                this.createShapeless(RecipeCategory.MISC, ModItems.INFINI_PEARL)
                        .input(Items.ENDER_PEARL)
                        .input(ModItems.SPEEDRUNNERS_EYE)
                        .input(ModItems.ENDER_MATTER)
                        .criterion("has_ender_matter", this.conditionsFromItem(ModItems.ENDER_MATTER))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.TOOLS, Items.EXPERIENCE_BOTTLE, 2)
                        .input(Items.GLASS_BOTTLE)
                        .input(ModItems.EXPERIENCE_FRAGMENT)
                        .criterion("has_item", this.conditionsFromTag(ModItemTags.EXPERIENCE_BOTTLE_CRAFTABLES))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.COMBAT, ModItems.SPEEDRUNNERS_TOTEM)
                        .input(Items.TOTEM_OF_UNDYING)
                        .input(ModItems.ENDER_MATTER)
                        .criterion("has_totem", this.conditionsFromItem(Items.TOTEM_OF_UNDYING))
                        .offerTo(this.exporter);
            }
        };
    }

    @Override
    public String getName() {
        return "Speedrunner Mod Recipe Generator";
    }
}