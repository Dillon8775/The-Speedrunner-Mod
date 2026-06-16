package net.dillon.speedrunnermod.data.generator;

import com.google.common.collect.ImmutableList;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.*;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipePattern;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/**
 * Used to modify {@code vanilla recipes} and create {@code Speedrunner Mod} recipes.
 */
public class ModRecipeProvider extends FabricRecipeProvider {
    private static final ImmutableList<ItemLike> IGNEOUS_ORES = ImmutableList.of(ModBlocks.IGNEOUS_ORE, ModBlocks.DEEPSLATE_IGNEOUS_ORE, ModBlocks.NETHER_IGNEOUS_ORE);
    private static final ImmutableList<ItemLike> EXPERIENCE_ORES = ImmutableList.of(ModBlocks.EXPERIENCE_ORE, ModBlocks.DEEPSLATE_EXPERIENCE_ORE, ModBlocks.NETHER_EXPERIENCE_ORE);
    private static final ImmutableList<ItemLike> SPEEDRUNNER_ORES_AND_BLOCKS = ImmutableList.of(ModBlocks.SPEEDRUNNER_ORE, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE, ModBlocks.NETHER_SPEEDRUNNER_ORE, ModItems.RAW_SPEEDRUNNER);

    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider wrapperLookup, RecipeOutput recipeExporter) {
        return new RecipeProvider(wrapperLookup, recipeExporter) {

            @Override
            public void buildRecipes() {
                RecipeGeneratorHelper helper = new RecipeGeneratorHelper(wrapperLookup, output);

                helper.createAxe(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_AXE);
                helper.createHoe(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_HOE);
                helper.createPickaxe(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_PICKAXE);
                helper.createShovel(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_SHOVEL);
                helper.createSword(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_SWORD);
                helper.createSpear(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_SPEAR);

                helper.createHelmet(ModItems.SPEEDRUNNER_INGOT, ModItems.SPEEDRUNNER_HELMET);
                helper.createChestplate(ModItems.SPEEDRUNNER_INGOT, ModItems.SPEEDRUNNER_CHESTPLATE);
                helper.createLeggings(ModItems.SPEEDRUNNER_INGOT, ModItems.SPEEDRUNNER_LEGGINGS);
                helper.createBoots(ModItems.SPEEDRUNNER_INGOT, ModItems.SPEEDRUNNER_BOOTS);

                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_SWORD, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_SWORD);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_SHOVEL, RecipeCategory.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_PICKAXE, RecipeCategory.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_AXE, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_AXE);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_HOE, RecipeCategory.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_HOE);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_SPEAR, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_SPEAR);

                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_HELMET, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_HELMET);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_CHESTPLATE, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_LEGGINGS, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_BOOTS, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_BOOTS);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(ModItems.SPEEDRUNNER_HARNESS, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_HARNESS);
                helper.offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_NAUTILUS_ARMOR, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_NAUTILUS_ARMOR);

                helper.offerBurnableMaterial(IGNEOUS_ORES, ModItems.IGNEOUS_ROCK, 0.6F, "igneous_rock");
                helper.offerBurnableMaterial(EXPERIENCE_ORES, ModItems.EXPERIENCE_FRAGMENT, 3.0F, "experience_fragment");
                helper.offerBurnableMaterial(SPEEDRUNNER_ORES_AND_BLOCKS, ModItems.SPEEDRUNNER_INGOT, 0.85F, "speedrunner_ingot");

                helper.createCookableFood(Items.ROTTEN_FLESH, ModItems.COOKED_FLESH);
                helper.createCookableFood(ModItems.ROTTEN_SPEEDRUNNER_BULK, Items.ROTTEN_FLESH);
                helper.createCookableFood(ModItems.PIGLIN_PORK, ModItems.COOKED_PIGLIN_PORK);

                helper.createFireproofBoatSet(ModItems.SPEEDRUNNER_BOAT, ModItems.SPEEDRUNNER_CHEST_BOAT, ModItems.FIREPROOF_SPEEDRUNNER_BOAT, ModItems.FIREPROOF_SPEEDRUNNER_CHEST_BOAT, ModBlocks.SPEEDRUNNER_PLANKS, "fireproof_speedrunner_boat");
                this.woodenBoat(ModItems.DEAD_SPEEDRUNNER_BOAT, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
                this.chestBoat(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT, Items.CHEST);
                helper.createFireproofBoatSet(ModItems.CRIMSON_BOAT, ModItems.CRIMSON_CHEST_BOAT, ModItems.FIREPROOF_CRIMSON_BOAT, ModItems.FIREPROOF_CRIMSON_CHEST_BOAT, Blocks.CRIMSON_PLANKS, "fireproof_crimson_boat");
                helper.createFireproofBoatSet(ModItems.WARPED_BOAT, ModItems.WARPED_CHEST_BOAT, ModItems.FIREPROOF_WARPED_BOAT, ModItems.FIREPROOF_WARPED_CHEST_BOAT, Blocks.WARPED_PLANKS, "fireproof_warped_boat");

                helper.banner(Items.BANNER.black(), Blocks.WOOL.black());
                helper.banner(Items.BANNER.blue(), Blocks.WOOL.blue());
                helper.banner(Items.BANNER.brown(), Blocks.WOOL.brown());
                helper.banner(Items.BANNER.cyan(), Blocks.WOOL.cyan());
                helper.banner(Items.BANNER.gray(), Blocks.WOOL.gray());
                helper.banner(Items.BANNER.green(), Blocks.WOOL.green());
                helper.banner(Items.BANNER.lightBlue(), Blocks.WOOL.lightBlue());
                helper.banner(Items.BANNER.lightGray(), Blocks.WOOL.lightGray());
                helper.banner(Items.BANNER.lime(), Blocks.WOOL.lime());
                helper.banner(Items.BANNER.magenta(), Blocks.WOOL.magenta());
                helper.banner(Items.BANNER.orange(), Blocks.WOOL.orange());
                helper.banner(Items.BANNER.pink(), Blocks.WOOL.pink());
                helper.banner(Items.BANNER.purple(), Blocks.WOOL.purple());
                helper.banner(Items.BANNER.red(), Blocks.WOOL.red());
                helper.banner(Items.BANNER.white(), Blocks.WOOL.white());
                helper.banner(Items.BANNER.yellow(), Blocks.WOOL.yellow());

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

                helper.createModdedFenceRecipe(ModBlocks.DEAD_SPEEDRUNNER_FENCE, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
                helper.createModdedFenceRecipe(ModBlocks.SPEEDRUNNER_FENCE, ModBlocks.SPEEDRUNNER_PLANKS);

                helper.createModdedFenceGateRecipe(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
                helper.createModdedFenceGateRecipe(ModBlocks.SPEEDRUNNER_FENCE_GATE, ModBlocks.SPEEDRUNNER_PLANKS);

                this.doorBuilder(ModBlocks.METAL_SPEEDRUNNER_DOOR, Ingredient.of(ModItems.SPEEDRUNNER_INGOT))
                        .unlockedBy(getHasName(ModItems.SPEEDRUNNER_INGOT), this.has(ModItems.SPEEDRUNNER_INGOT))
                        .save(this.output);
                this.doorBuilder(ModBlocks.DEAD_SPEEDRUNNER_DOOR, Ingredient.of(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.has(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .group("wooden_door")
                        .save(this.output);
                this.doorBuilder(ModBlocks.SPEEDRUNNER_DOOR, Ingredient.of(ModBlocks.SPEEDRUNNER_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.SPEEDRUNNER_PLANKS), this.has(ModBlocks.SPEEDRUNNER_PLANKS))
                        .group("wooden_door")
                        .save(this.output);

                this.twoByTwoPacker(RecipeCategory.REDSTONE, ModBlocks.METAL_SPEEDRUNNER_TRAPDOOR, ModItems.SPEEDRUNNER_INGOT);
                this.trapdoorBuilder(ModBlocks.DEAD_SPEEDRUNNER_TRAPDOOR, Ingredient.of(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.has(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .group("wooden_trapdoor")
                        .save(this.output);
                this.trapdoorBuilder(ModBlocks.SPEEDRUNNER_TRAPDOOR, Ingredient.of(ModBlocks.SPEEDRUNNER_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.SPEEDRUNNER_PLANKS), this.has(ModBlocks.SPEEDRUNNER_PLANKS))
                        .group("wooden_trapdoor")
                        .save(this.output);

                this.buttonBuilder(ModBlocks.DEAD_SPEEDRUNNER_BUTTON, Ingredient.of(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.has(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .save(this.output);
                this.buttonBuilder(ModBlocks.SPEEDRUNNER_BUTTON, Ingredient.of(ModBlocks.SPEEDRUNNER_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.SPEEDRUNNER_PLANKS), this.has(ModBlocks.SPEEDRUNNER_PLANKS))
                        .save(this.output);

                this.stairBuilder(ModBlocks.DEAD_SPEEDRUNNER_STAIRS, Ingredient.of(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.has(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .group("wooden_stairs")
                        .save(this.output);
                this.stairBuilder(ModBlocks.SPEEDRUNNER_STAIRS, Ingredient.of(ModBlocks.SPEEDRUNNER_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.SPEEDRUNNER_PLANKS), this.has(ModBlocks.SPEEDRUNNER_PLANKS))
                        .group("wooden_stairs")
                        .save(this.output);

                this.slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEAD_SPEEDRUNNER_SLAB, Ingredient.of(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.has(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .group("wooden_slab")
                        .save(this.output);
                this.slabBuilder(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPEEDRUNNER_SLAB, Ingredient.of(ModBlocks.SPEEDRUNNER_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.SPEEDRUNNER_PLANKS), this.has(ModBlocks.SPEEDRUNNER_PLANKS))
                        .group("wooden_slab")
                        .save(this.output);

                this.pressurePlate(ModBlocks.MEDIATE_WEIGHTED_SPEEDRUNNER_PRESSURE_PLATE, ModItems.SPEEDRUNNER_INGOT);
                this.pressurePlateBuilder(RecipeCategory.REDSTONE, ModBlocks.DEAD_SPEEDRUNNER_PRESSURE_PLATE, Ingredient.of(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.SPEEDRUNNER_PLANKS), this.has(ModItems.SPEEDRUNNER_INGOT))
                        .group("wooden_pressure_plate")
                        .save(this.output);
                this.pressurePlateBuilder(RecipeCategory.REDSTONE, ModBlocks.SPEEDRUNNER_PRESSURE_PLATE, Ingredient.of(ModBlocks.SPEEDRUNNER_PLANKS))
                        .unlockedBy(getHasName(ModBlocks.SPEEDRUNNER_PLANKS), this.has(ModItems.SPEEDRUNNER_INGOT))
                        .group("wooden_pressure_plate")
                        .save(this.output);

                this.planksFromLogs(ModBlocks.DEAD_SPEEDRUNNER_PLANKS, ModItemTags.Block.DEAD_SPEEDRUNNER_LOGS, 4);
                this.planksFromLogs(ModBlocks.SPEEDRUNNER_PLANKS, ModItemTags.Block.SPEEDRUNNER_LOGS, 4);

                this.woodFromLogs(ModBlocks.DEAD_SPEEDRUNNER_WOOD, ModBlocks.DEAD_SPEEDRUNNER_LOG);
                this.woodFromLogs(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD, ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG);
                this.woodFromLogs(ModBlocks.SPEEDRUNNER_WOOD, ModBlocks.SPEEDRUNNER_LOG);
                this.woodFromLogs(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD, ModBlocks.STRIPPED_SPEEDRUNNER_LOG);

                SimpleCookingRecipeBuilder.smelting(
                                Ingredient.of(
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
                                CookingBookCategory.MISC,
                                ModItems.SPEEDRUNNER_NUGGET,
                                0.2F,
                                200
                        )
                        .unlockedBy("has_speedrunner_pickaxe", this.has(ModItems.SPEEDRUNNER_PICKAXE))
                        .unlockedBy("has_speedrunner_shovel", this.has(ModItems.SPEEDRUNNER_SHOVEL))
                        .unlockedBy("has_speedrunner_axe", this.has(ModItems.SPEEDRUNNER_AXE))
                        .unlockedBy("has_speedrunner_hoe", this.has(ModItems.SPEEDRUNNER_HOE))
                        .unlockedBy("has_speedrunner_sword", this.has(ModItems.SPEEDRUNNER_SWORD))
                        .unlockedBy("has_speedrunner_helmet", this.has(ModItems.SPEEDRUNNER_HELMET))
                        .unlockedBy("has_speedrunner_chestplate", this.has(ModItems.SPEEDRUNNER_CHESTPLATE))
                        .unlockedBy("has_speedrunner_leggings", this.has(ModItems.SPEEDRUNNER_LEGGINGS))
                        .unlockedBy("has_speedrunner_boots", this.has(ModItems.SPEEDRUNNER_BOOTS))
                        .save(this.output, getSmeltingRecipeName(ModItems.SPEEDRUNNER_NUGGET));

                SimpleCookingRecipeBuilder.blasting(
                                Ingredient.of(
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
                                CookingBookCategory.MISC,
                                ModItems.SPEEDRUNNER_NUGGET,
                                0.2F,
                                200
                        )
                        .unlockedBy("has_speedrunner_pickaxe", this.has(ModItems.SPEEDRUNNER_PICKAXE))
                        .unlockedBy("has_speedrunner_shovel", this.has(ModItems.SPEEDRUNNER_SHOVEL))
                        .unlockedBy("has_speedrunner_axe", this.has(ModItems.SPEEDRUNNER_AXE))
                        .unlockedBy("has_speedrunner_hoe", this.has(ModItems.SPEEDRUNNER_HOE))
                        .unlockedBy("has_speedrunner_sword", this.has(ModItems.SPEEDRUNNER_SWORD))
                        .unlockedBy("has_speedrunner_helmet", this.has(ModItems.SPEEDRUNNER_HELMET))
                        .unlockedBy("has_speedrunner_chestplate", this.has(ModItems.SPEEDRUNNER_CHESTPLATE))
                        .unlockedBy("has_speedrunner_leggings", this.has(ModItems.SPEEDRUNNER_LEGGINGS))
                        .unlockedBy("has_speedrunner_boots", this.has(ModItems.SPEEDRUNNER_BOOTS))
                        .save(this.output, getBlastingRecipeName(ModItems.SPEEDRUNNER_NUGGET));

                this.shapeless(RecipeCategory.MISC, ModItems.ANNUL_EYE)
                        .requires(ModItems.ENDER_MATTER)
                        .requires(Items.ENDER_EYE)
                        .unlockedBy("has_items", this.has(ModItemTags.AdvancementCriterions.EYE_OF_ANNUL))
                        .save(this.output);

                this.shapeless(RecipeCategory.MISC, ModItems.BLAZE_SPOTTER)
                        .requires(Items.ENDER_PEARL)
                        .requires(Items.FIRE_CHARGE)
                        .requires(Items.LAVA_BUCKET)
                        .unlockedBy("has_items", this.has(ModItemTags.AdvancementCriterions.BLAZE_SPOTTER))
                        .save(this.output);

                this.shapeless(RecipeCategory.MISC, ModItems.DRAGONS_PEARL)
                        .requires(Items.BLAZE_POWDER)
                        .requires(ModItems.SPEEDRUNNERS_EYE)
                        .unlockedBy("has_items", this.has(ModItemTags.AdvancementCriterions.DRAGONS_PEARL))
                        .save(this.output);

                this.shapeless(RecipeCategory.COMBAT, ModItems.DRAGONS_SWORD)
                        .requires(ModItems.SPEEDRUNNER_SWORD)
                        .requires(ModItems.DRAGONS_PEARL)
                        .requires(ModItems.ENDER_MATTER)
                        .unlockedBy("has_items", this.has(ModItemTags.AdvancementCriterions.DRAGONS_SWORD))
                        .save(this.output);

                this.shapeless(RecipeCategory.MISC, ModItems.ENDER_THRUSTER)
                        .requires(Items.ENDER_PEARL)
                        .requires(ModItems.SPEEDRUNNERS_EYE)
                        .unlockedBy("has_items", this.has(ModItemTags.AdvancementCriterions.ENDER_THRUSTER))
                        .save(this.output);

                this.shapeless(RecipeCategory.MISC, ModItems.INFERNO_EYE)
                        .requires(Items.ENDER_PEARL)
                        .requires(Items.FIRE_CHARGE)
                        .unlockedBy("has_items", this.has(ModItemTags.AdvancementCriterions.INFERNO_EYE))
                        .save(this.output);

                this.shapeless(RecipeCategory.MISC, ModItems.RAID_ERADICATOR)
                        .requires(Items.ENCHANTED_GOLDEN_APPLE)
                        .requires(ModItems.SPEEDRUNNERS_EYE)
                        .unlockedBy("has_items", this.has(ModItemTags.AdvancementCriterions.RAID_ERADICATOR))
                        .save(this.output);

                this.shapeless(RecipeCategory.MISC, ModItems.SPEEDRUNNERS_EYE)
                        .requires(Items.ENDER_PEARL)
                        .requires(ModItems.SPEEDRUNNER_INGOT)
                        .unlockedBy("has_items", this.has(ModItems.SPEEDRUNNER_INGOT))
                        .save(this.output);

                this.shapeless(RecipeCategory.TOOLS, ModItems.SPEEDRUNNER_FLINT_AND_STEEL)
                        .requires(Items.FLINT)
                        .requires(ModItems.SPEEDRUNNER_INGOT)
                        .unlockedBy("has_speedrunner_ingot", this.has(ModItemTags.AdvancementCriterions.SPEEDRUNNER_FLINT_AND_STEEL))
                        .group("flint_and_steels")
                        .save(this.output);

                this.shaped(RecipeCategory.TOOLS, ModItems.SPEEDRUNNER_PADDLE)
                        .define('I', ModItems.SPEEDRUNNER_PLANKS)
                        .define('S', ModItemTags.SPEEDRUNNER_STICKS)
                        .pattern("I")
                        .pattern("S")
                        .pattern("I")
                        .unlockedBy("has_speedrunner_plank", this.has(ModItems.SPEEDRUNNER_PLANKS))
                        .save(this.output);

                this.shaped(RecipeCategory.MISC, ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE, 2)
                        .define('#', Items.GOLD_INGOT)
                        .define('C', Items.GOLD_BLOCK)
                        .define('S', ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE)
                        .pattern("#S#")
                        .pattern("#C#")
                        .pattern("###")
                        .showNotification(true)
                        .unlockedBy("has_gold_block", this.has(Items.GOLD_INGOT))
                        .save(this.output);

                this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPEEDRUNNERS_WORKBENCH)
                        .define('I', ModItems.SPEEDRUNNER_INGOT)
                        .define('B', ModItems.SPEEDRUNNER_BLOCK)
                        .define('P', ModItemTags.Block.SPEEDRUNNER_PLANKS)
                        .pattern("III")
                        .pattern("PBP")
                        .pattern("PPP")
                        .unlockedBy("has_planks", this.has(ModItemTags.AdvancementCriterions.SPEEDRUNNERS_WORKBENCH))
                        .save(this.output);

                this.shaped(RecipeCategory.COMBAT, ModItems.SPEEDRUNNER_HARNESS)
                        .define('I', ModItems.SPEEDRUNNER_INGOT)
                        .define('L', Items.WOOL.lightBlue())
                        .define('G', Items.GLASS)
                        .pattern("III")
                        .pattern("GLG")
                        .unlockedBy("has_speedrunner_ingot", this.has(ModItems.SPEEDRUNNER_INGOT))
                        .save(this.output);

                this.shaped(RecipeCategory.COMBAT, ModItems.SPEEDRUNNER_BOW)
                        .define('/', ModItemTags.SPEEDRUNNER_STICKS)
                        .define('S', Items.STRING)
                        .pattern(" /S")
                        .pattern("/ S")
                        .pattern(" /S")
                        .unlockedBy("has_string", this.has(Items.STRING))
                        .group("bows")
                        .save(this.output);

                this.shaped(RecipeCategory.COMBAT, ModItems.SPEEDRUNNER_CROSSBOW)
                        .define('~', Items.STRING)
                        .define('#', ModItemTags.SPEEDRUNNER_STICKS)
                        .define('S', ModItems.SPEEDRUNNER_INGOT)
                        .define('$', Items.TRIPWIRE_HOOK)
                        .pattern("#S#")
                        .pattern("~$~")
                        .pattern(" # ")
                        .unlockedBy("has_speedrunner_ingot", this.has(ModItems.SPEEDRUNNER_INGOT))
                        .group("crossbows")
                        .save(this.output);

                this.shaped(RecipeCategory.TOOLS, ModItems.SPEEDRUNNER_SHEARS)
                        .define('#', ModItems.SPEEDRUNNER_INGOT)
                        .pattern(" #")
                        .pattern("# ")
                        .unlockedBy("has_speedrunner_ingot", this.has(ModItems.SPEEDRUNNER_INGOT))
                        .group("shears")
                        .save(this.output);

                this.shaped(RecipeCategory.COMBAT, ModItems.SPEEDRUNNER_SHIELD)
                        .define('W', ItemTags.PLANKS)
                        .define('o', ModItems.SPEEDRUNNER_INGOT)
                        .pattern("WoW")
                        .pattern("WWW")
                        .pattern(" W ")
                        .unlockedBy("has_speedrunner_ingot", this.has(ModItems.SPEEDRUNNER_INGOT))
                        .group("shields")
                        .save(this.output);

                this.shaped(RecipeCategory.COMBAT, ModItems.GOLDEN_SHIELD)
                        .define('W', ItemTags.PLANKS)
                        .define('o', Items.GOLD_INGOT)
                        .pattern("WoW")
                        .pattern("WWW")
                        .pattern(" W ")
                        .unlockedBy("has_gold_ingot", this.has(Items.GOLD_INGOT))
                        .group("shields")
                        .save(this.output);

                SpecialRecipeBuilder.special(
                        () -> new SpeedrunnerShieldDecorationRecipe(
                                this.tag(ItemTags.BANNERS), Ingredient.of(ModItems.SPEEDRUNNER_SHIELD), new ItemStackTemplate(ModItems.SPEEDRUNNER_SHIELD))
                ).save(this.output, "speedrunner_shield_decoration");
                SpecialRecipeBuilder.special(
                        () -> new GoldenShieldDecorationRecipe(
                                this.tag(ItemTags.BANNERS), Ingredient.of(ModItems.GOLDEN_SHIELD), new ItemStackTemplate(ModItems.GOLDEN_SHIELD))
                ).save(this.output, "golden_shield_decoration");
                SpecialRecipeBuilder.special(
                        () -> new PiglinAwakenerRecipe(
                                RecipeBuilder.createCraftingCommonInfo(false),
                                RecipeBuilder.createCraftingBookInfo(RecipeCategory.MISC, "piglin_awakener"),
                                ShapedRecipePattern.of(
                                        Map.of('#', Ingredient.of(Items.GOLD_INGOT),
                                                'O', Ingredient.of(
                                                        Items.ENDER_PEARL,
                                                        Items.BLAZE_POWDER,
                                                        Items.GOLDEN_APPLE,
                                                        Items.ENCHANTED_GOLDEN_APPLE,
                                                        Items.GOLDEN_CARROT
                                                )),
                                        "###",
                                        "#O#",
                                        "###"
                                ),
                                new ItemStackTemplate(ModItems.PIGLIN_AWAKENER))
                ).save(this.output, "piglin_awakener");
                SpecialRecipeBuilder.special(
                        () -> new DragonFireballRecipe(
                                new ItemStackTemplate(ModItems.DRAGONS_FIREBALL, 8)
                        )
                ).save(this.output, "dragons_fireball");
                SpecialRecipeBuilder.special(
                        () -> new InventoryPreserverRecipe(
                                new ItemStackTemplate(ModItems.INVENTORY_PRESERVER)
                        )
                ).save(this.output, "inventory_preserver");

//                SpecialRecipeBuilder.special(SpeedrunnerShieldDecorationRecipe::new).save(this.output, "speedrunner_shield_decoration");
//                SpecialRecipeBuilder.special(GoldenShieldDecorationRecipe::new).save(this.output, "golden_shield_decoration");
//                SpecialRecipeBuilder.special(PiglinAwakenerRecipe::new).save(this.output, "piglin_awakener");
//                SpecialRecipeBuilder.special(DragonFireballRecipe::new).save(this.output, "dragons_fireball");
//                SpecialRecipeBuilder.special(InventoryPreserverRecipe::new).save(this.output, "inventory_preserver");

                helper.createStickRecipe(true, "speedrunner_stick_from_dead_speedrunner_planks");
                helper.createStickRecipe(false, "speedrunner_stick_from_speedrunner_planks");

                helper.createReversePlankRecipe();

                this.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.FLESH_BLOCK)
                        .define('#', ModItemTags.FLESH)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy("has_flesh_item", this.has(ModItemTags.FLESH))
                        .save(this.output);

                this.shaped(RecipeCategory.MISC, Blocks.OAK_PLANKS)
                        .define('/', Items.STICK)
                        .group("planks")
                        .pattern("//")
                        .pattern("//")
                        .unlockedBy("has_stick", this.has(Items.STICK))
                        .save(this.output, helper.speedrunnerModRecipe("oak_planks_from_sticks"));

                this.shaped(RecipeCategory.MISC, Blocks.OBSIDIAN)
                        .define('#', ModItems.IGNEOUS_ROCK)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .unlockedBy("has_igneous_rock", this.has(ModItems.IGNEOUS_ROCK))
                        .save(this.output, helper.speedrunnerModRecipe("obsidian_from_igneous_rocks"));

                this.shaped(RecipeCategory.FOOD, Items.ENCHANTED_GOLDEN_APPLE)
                        .define('a', Items.GOLDEN_APPLE)
                        .define('B', Items.GOLD_BLOCK)
                        .pattern("BBB")
                        .pattern("BaB")
                        .pattern("BBB")
                        .unlockedBy("has_gold_block", this.has(Items.GOLD_BLOCK))
                        .save(this.output, helper.speedrunnerModRecipe("enchanted_golden_apple"));

                this.shaped(RecipeCategory.COMBAT, Items.TOTEM_OF_UNDYING)
                        .define('e', Items.ENCHANTED_GOLDEN_APPLE)
                        .define('B', Items.GOLD_BLOCK)
                        .pattern("BBB")
                        .pattern("BeB")
                        .pattern("BBB")
                        .unlockedBy("has_enchanted_golden_apple", this.has(Items.ENCHANTED_GOLDEN_APPLE))
                        .save(this.output, helper.speedrunnerModRecipe("totem_of_undying"));

                this.shaped(RecipeCategory.BREWING, Items.BLAZE_ROD)
                        .define('P', Items.BLAZE_POWDER)
                        .define('/', ModItemTags.SPEEDRUNNER_STICKS)
                        .pattern("P")
                        .pattern("P")
                        .pattern("/")
                        .unlockedBy("has_blaze_powder", this.has(Items.BLAZE_POWDER))
                        .save(this.output, helper.speedrunnerModRecipe("blaze_rod"));

                this.shapeless(RecipeCategory.MISC, Items.STRING, 4)
                        .requires(ItemTags.WOOL)
                        .unlockedBy("has_string", this.has(Items.STRING))
                        .save(this.output, helper.speedrunnerModRecipe("string_from_wool"));

                this.shapeless(RecipeCategory.MISC, ModItems.INFINI_PEARL)
                        .requires(Items.ENDER_PEARL)
                        .requires(ModItems.SPEEDRUNNERS_EYE)
                        .requires(ModItems.ENDER_MATTER)
                        .unlockedBy("has_ender_matter", this.has(ModItems.ENDER_MATTER))
                        .save(this.output);

                this.shapeless(RecipeCategory.TOOLS, Items.EXPERIENCE_BOTTLE, 2)
                        .requires(Items.GLASS_BOTTLE)
                        .requires(ModItems.EXPERIENCE_FRAGMENT)
                        .unlockedBy("has_item", this.has(ModItemTags.EXPERIENCE_BOTTLE_CRAFTABLES))
                        .save(this.output);

                this.shapeless(RecipeCategory.COMBAT, ModItems.SPEEDRUNNERS_TOTEM)
                        .requires(Items.TOTEM_OF_UNDYING)
                        .requires(ModItems.ENDER_MATTER)
                        .unlockedBy("has_totem", this.has(Items.TOTEM_OF_UNDYING))
                        .save(this.output);
            }
        };
    }

    @Override
    public String getName() {
        return "Speedrunner Mod Recipe Generator";
    }
}