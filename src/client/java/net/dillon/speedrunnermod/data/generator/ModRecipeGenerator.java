package net.dillon.speedrunnermod.data.generator;

import com.google.common.collect.ImmutableList;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.recipe.SpeedrunnerShieldDecorationRecipe;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
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
    private static final ImmutableList<ItemConvertible> DIAMOND_ORES = ImmutableList.of(Items.DIAMOND_ORE, Items.DEEPSLATE_DIAMOND_ORE);
    private static final ImmutableList<ItemConvertible> EMERALD_ORES = ImmutableList.of(Items.EMERALD_ORE, Items.DEEPSLATE_EMERALD_ORE);
    private static final ImmutableList<ItemConvertible> GOLD_ORES = ImmutableList.of(Items.GOLD_ORE, Items.DEEPSLATE_GOLD_ORE, Items.NETHER_GOLD_ORE, Items.RAW_GOLD);
    private static final ImmutableList<ItemConvertible> IRON_ORES = ImmutableList.of(Items.IRON_ORE, Items.DEEPSLATE_IRON_ORE, Items.RAW_IRON);
    private static final ImmutableList<ItemConvertible> LAPIS_ORES = ImmutableList.of(Items.LAPIS_ORE, Items.DEEPSLATE_LAPIS_ORE);
    private static final ImmutableList<ItemConvertible> REDSTONE_ORES = ImmutableList.of(Items.REDSTONE_ORE, Items.DEEPSLATE_REDSTONE_ORE);
    private static final ImmutableList<ItemConvertible> IGNEOUS_ORES = ImmutableList.of(ModBlocks.IGNEOUS_ORE, ModBlocks.DEEPSLATE_IGNEOUS_ORE, ModBlocks.NETHER_IGNEOUS_ORE);
    private static final ImmutableList<ItemConvertible> SPEEDRUNNER_ORES_AND_BLOCKS = ImmutableList.of(ModBlocks.SPEEDRUNNER_ORE, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE, ModBlocks.NETHER_SPEEDRUNNER_ORE);

    public ModRecipeGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeGenerator getRecipeGenerator(RegistryWrapper.WrapperLookup wrapperLookup, RecipeExporter recipeExporter) {
        return new RecipeGenerator(wrapperLookup, recipeExporter) {

            @Override
            public void generate() {
                RecipeGeneratorHelper helper = new RecipeGeneratorHelper(wrapperLookup, exporter);

                // VANILLA/MODDED
                helper.createAxe(ItemTags.DIAMOND_TOOL_MATERIALS, Items.DIAMOND_AXE, true);
                helper.createAxe(ItemTags.GOLD_TOOL_MATERIALS, Items.GOLDEN_AXE, true);
                helper.createAxe(ItemTags.IRON_TOOL_MATERIALS, Items.IRON_AXE, true);
                helper.createAxe(ItemTags.STONE_TOOL_MATERIALS, Items.STONE_AXE, true);
                helper.createAxe(ItemTags.WOODEN_TOOL_MATERIALS, Items.WOODEN_AXE, true);
                helper.createAxe(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_AXE, false);

                helper.createHoe(ItemTags.DIAMOND_TOOL_MATERIALS, Items.DIAMOND_HOE, true);
                helper.createHoe(ItemTags.GOLD_TOOL_MATERIALS, Items.GOLDEN_HOE, true);
                helper.createHoe(ItemTags.IRON_TOOL_MATERIALS, Items.IRON_HOE, true);
                helper.createHoe(ItemTags.STONE_TOOL_MATERIALS, Items.STONE_HOE, true);
                helper.createHoe(ItemTags.WOODEN_TOOL_MATERIALS, Items.WOODEN_HOE, true);
                helper.createHoe(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_HOE, false);

                helper.createPickaxe(ItemTags.DIAMOND_TOOL_MATERIALS, Items.DIAMOND_PICKAXE, true);
                helper.createPickaxe(ItemTags.GOLD_TOOL_MATERIALS, Items.GOLDEN_PICKAXE, true);
                helper.createPickaxe(ItemTags.IRON_TOOL_MATERIALS, Items.IRON_PICKAXE, true);
                helper.createPickaxe(ItemTags.STONE_TOOL_MATERIALS, Items.STONE_PICKAXE, true);
                helper.createPickaxe(ItemTags.WOODEN_TOOL_MATERIALS, Items.WOODEN_PICKAXE, true);
                helper.createPickaxe(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_PICKAXE, false);

                helper.createShovel(ItemTags.DIAMOND_TOOL_MATERIALS, Items.DIAMOND_SHOVEL, true);
                helper.createShovel(ItemTags.GOLD_TOOL_MATERIALS, Items.GOLDEN_SHOVEL, true);
                helper.createShovel(ItemTags.IRON_TOOL_MATERIALS, Items.IRON_SHOVEL, true);
                helper.createShovel(ItemTags.STONE_TOOL_MATERIALS, Items.STONE_SHOVEL, true);
                helper.createShovel(ItemTags.WOODEN_TOOL_MATERIALS, Items.WOODEN_SHOVEL, true);
                helper.createShovel(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_SHOVEL, false);

                helper.createSword(ItemTags.DIAMOND_TOOL_MATERIALS, Items.DIAMOND_SWORD, true);
                helper.createSword(ItemTags.GOLD_TOOL_MATERIALS, Items.GOLDEN_SWORD, true);
                helper.createSword(ItemTags.IRON_TOOL_MATERIALS, Items.IRON_SWORD, true);
                helper.createSword(ItemTags.STONE_TOOL_MATERIALS, Items.STONE_SWORD, true);
                helper.createSword(ItemTags.WOODEN_TOOL_MATERIALS, Items.WOODEN_SWORD, true);
                helper.createSword(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_SWORD, false);

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

                helper.offerBurnableMaterial(DIAMOND_ORES, Items.DIAMOND, 30, "diamond", true);
                helper.offerBurnableMaterial(EMERALD_ORES, Items.EMERALD, 300, "emerald", true);
                helper.offerBurnableMaterial(GOLD_ORES, Items.GOLD_INGOT, 150, "gold_ingot", true);
                helper.offerBurnableMaterial(IRON_ORES, Items.IRON_INGOT, 100, "iron_ingot", true);
                helper.offerBurnableMaterial(LAPIS_ORES, Items.LAPIS_LAZULI, 100, "lapis_lazuli", true);
                helper.offerBurnableMaterial(REDSTONE_ORES, Items.REDSTONE, 100, "redstone", true);
                helper.offerBurnableMaterial(IGNEOUS_ORES, ModItems.IGNEOUS_ROCK, 0.65F, "igneous_rock", false);
                helper.offerBurnableMaterial(SPEEDRUNNER_ORES_AND_BLOCKS, ModItems.SPEEDRUNNER_INGOT, 200, "speedrunner_ingot", false);

                helper.createCookableFood(Items.POTATO, Items.BAKED_POTATO, true);
                helper.createCookableFood(Items.BEEF, Items.COOKED_BEEF, true);
                helper.createCookableFood(Items.CHICKEN, Items.COOKED_CHICKEN, true);
                helper.createCookableFood(Items.COD, Items.COOKED_COD, true);
                helper.createCookableFood(Items.MUTTON, Items.COOKED_MUTTON, true);
                helper.createCookableFood(Items.PORKCHOP, Items.COOKED_PORKCHOP, true);
                helper.createCookableFood(Items.RABBIT, Items.COOKED_RABBIT, true);
                helper.createCookableFood(Items.SALMON, Items.COOKED_SALMON, true);
                helper.createCookableFood(ModItems.ROTTEN_SPEEDRUNNER_BULK, Items.ROTTEN_FLESH, true);
                helper.createCookableFood(Items.ROTTEN_FLESH, ModItems.COOKED_FLESH, false);
                helper.createCookableFood(ModItems.PIGLIN_PORK, ModItems.COOKED_PIGLIN_PORK, false);

                helper.createBoatSet(ModItems.DEAD_SPEEDRUNNER_BOAT, ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
                helper.createBoatSet(ModItems.SPEEDRUNNER_BOAT, ModItems.SPEEDRUNNER_CHEST_BOAT, ModBlocks.SPEEDRUNNER_PLANKS);
                helper.createBoatSet(ModItems.CRIMSON_BOAT, ModItems.CRIMSON_CHEST_BOAT, Blocks.CRIMSON_PLANKS);
                helper.createBoatSet(ModItems.WARPED_BOAT, ModItems.WARPED_CHEST_BOAT, Blocks.WARPED_PLANKS);

                helper.createGoldenFoodItem(Items.COOKED_BEEF, ModItems.GOLDEN_BEEF);
                helper.createGoldenFoodItem(Items.BEETROOT, ModItems.GOLDEN_BEETROOT);
                helper.createGoldenFoodItem(Items.BREAD, ModItems.GOLDEN_BREAD);
                helper.createGoldenFoodItem(Items.COOKED_CHICKEN, ModItems.GOLDEN_CHICKEN);
                helper.createGoldenFoodItem(Items.COOKED_COD, ModItems.GOLDEN_COD);
                helper.createGoldenFoodItem(Items.COOKED_MUTTON, ModItems.GOLDEN_MUTTON);
                helper.createGoldenFoodItem(ModItems.COOKED_PIGLIN_PORK, ModItems.GOLDEN_PIGLIN_PORK);
                helper.createGoldenFoodItem(Items.COOKED_PORKCHOP, ModItems.GOLDEN_PORKCHOP);
                helper.createGoldenFoodItem(Items.BAKED_POTATO, ModItems.GOLDEN_POTATO);
                helper.createGoldenFoodItem(Items.COOKED_RABBIT, ModItems.GOLDEN_RABBIT);
                helper.createGoldenFoodItem(Items.COOKED_SALMON, ModItems.GOLDEN_SALMON);

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
                        null,
                        "speedrunner_ingot_from_speedrunner_block",
                        null
                );
                helper.offerModdedReversibleCompactingRecipes(
                        RecipeCategory.MISC,
                        ModItems.SPEEDRUNNER_NUGGET,
                        RecipeCategory.MISC,
                        ModItems.SPEEDRUNNER_INGOT,
                        "speedrunner_ingot_from_speedrunner_nuggets",
                        null,
                        "speedrunner_nuggets_from_speedrunner_ingot",
                        null
                );

                // VANILLA
                this.createShaped(RecipeCategory.TRANSPORTATION, Blocks.ACTIVATOR_RAIL, 6)
                        .input('#', Blocks.REDSTONE_TORCH)
                        .input('S', Items.STICK)
                        .input('X', ConventionalItemTags.IRON_INGOTS)
                        .pattern("XSX")
                        .pattern("X#X")
                        .pattern("XSX")
                        .criterion("has_rail", this.conditionsFromItem(Blocks.RAIL))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("activator_rail"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.ANVIL)
                        .input('I', ModItemTags.Block.IRON_BLOCKS)
                        .input('i', ConventionalItemTags.IRON_INGOTS)
                        .pattern("III")
                        .pattern(" i ")
                        .pattern("iii")
                        .criterion("has_iron_block", this.conditionsFromTag(ModItemTags.Block.IRON_BLOCKS))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("anvil"));

                this.createShaped(RecipeCategory.DECORATIONS, Items.ARMOR_STAND)
                        .input('/', ModItemTags.STICKS)
                        .input('_', Blocks.SMOOTH_STONE_SLAB)
                        .pattern("///")
                        .pattern(" / ")
                        .pattern("/_/")
                        .criterion("has_stone_slab", this.conditionsFromItem(Blocks.SMOOTH_STONE_SLAB))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("armor_stand"));

                this.createShaped(RecipeCategory.COMBAT, Items.ARROW, 4)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Items.FLINT)
                        .input('Y', Items.FEATHER)
                        .pattern("X")
                        .pattern("#")
                        .pattern("Y")
                        .criterion("has_feather", this.conditionsFromItem(Items.FEATHER))
                        .criterion("has_flint", this.conditionsFromItem(Items.FLINT))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("arrow"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.BLAST_FURNACE)
                        .input('#', Blocks.SMOOTH_STONE)
                        .input('X', Blocks.FURNACE)
                        .input('I', ConventionalItemTags.IRON_INGOTS)
                        .pattern("III")
                        .pattern("IXI")
                        .pattern("###")
                        .criterion("has_smooth_stone", this.conditionsFromItem(Blocks.SMOOTH_STONE))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("blast_furnace"));

                this.createShaped(RecipeCategory.COMBAT, Items.BOW)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Items.STRING)
                        .pattern(" #X")
                        .pattern("# X")
                        .pattern(" #X")
                        .criterion("has_string", this.conditionsFromItem(Items.STRING))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("bow"));

                this.createShaped(RecipeCategory.MISC, Items.BUCKET)
                        .input('#', ConventionalItemTags.IRON_INGOTS)
                        .pattern("# #")
                        .pattern(" # ")
                        .criterion("has_ingot", this.conditionsFromTag(ConventionalItemTags.IRON_INGOTS))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("bucket"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.CAMPFIRE)
                        .input('L', ItemTags.LOGS)
                        .input('S', ModItemTags.STICKS)
                        .input('C', ItemTags.COALS)
                        .pattern(" S ")
                        .pattern("SCS")
                        .pattern("LLL")
                        .criterion("has_stick", this.conditionsFromTag(ModItemTags.STICKS))
                        .criterion("has_coal", this.conditionsFromTag(ItemTags.COALS))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("campfire"));

                this.createShaped(RecipeCategory.BREWING, Blocks.CAULDRON)
                        .input('#', ConventionalItemTags.IRON_INGOTS)
                        .pattern("# #")
                        .pattern("# #")
                        .pattern("###")
                        .criterion("has_water_bucket", this.conditionsFromItem(Items.WATER_BUCKET))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("cauldron"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.CHAIN)
                        .input('I', ConventionalItemTags.IRON_INGOTS)
                        .input('N', ConventionalItemTags.IRON_NUGGETS)
                        .pattern("N")
                        .pattern("I")
                        .pattern("N")
                        .criterion("has_iron_nugget", this.conditionsFromTag(ConventionalItemTags.IRON_NUGGETS))
                        .criterion("has_iron_ingot", this.conditionsFromTag(ConventionalItemTags.IRON_INGOTS))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("chain"));

                this.createShaped(RecipeCategory.TOOLS, Items.COMPASS)
                        .input('#', ConventionalItemTags.IRON_INGOTS)
                        .input('X', Items.REDSTONE)
                        .pattern(" # ")
                        .pattern("#X#")
                        .pattern(" # ")
                        .criterion("has_redstone", this.conditionsFromItem(Items.REDSTONE))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("compass"));

                this.createShaped(RecipeCategory.COMBAT, Items.CROSSBOW)
                        .input('~', Items.STRING)
                        .input('#', Items.STICK)
                        .input('&', Items.IRON_INGOT)
                        .input('$', Blocks.TRIPWIRE_HOOK)
                        .pattern("#&#")
                        .pattern("~$~")
                        .pattern(" # ")
                        .criterion("has_string", this.conditionsFromItem(Items.STRING))
                        .criterion("has_iron_ingot", this.conditionsFromItem(Items.IRON_INGOT))
                        .criterion("has_tripwire_hook", this.conditionsFromItem(Blocks.TRIPWIRE_HOOK))
                        .group("crossbows")
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("crossbow"));

                this.createShaped(RecipeCategory.TRANSPORTATION, Blocks.DETECTOR_RAIL, 6)
                        .input('R', Items.REDSTONE)
                        .input('#', Blocks.STONE_PRESSURE_PLATE)
                        .input('X', ConventionalItemTags.IRON_INGOTS)
                        .pattern("X X")
                        .pattern("X#X")
                        .pattern("XRX")
                        .criterion("has_rail", this.conditionsFromItem(Blocks.RAIL))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("detector_rail"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.DISPENSER)
                        .input('R', Items.REDSTONE)
                        .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                        .input('X', Items.BOW)
                        .pattern("###")
                        .pattern("#X#")
                        .pattern("#R#")
                        .criterion("has_bow", this.conditionsFromItem(Items.BOW))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("dispenser"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.DROPPER)
                        .input('R', Items.REDSTONE)
                        .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                        .pattern("###")
                        .pattern("# #")
                        .pattern("#R#")
                        .criterion("has_redstone", this.conditionsFromItem(Items.REDSTONE))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("dropper"));

                this.createShaped(RecipeCategory.TOOLS, Items.FISHING_ROD)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Items.STRING)
                        .pattern("  #")
                        .pattern(" #X")
                        .pattern("# X")
                        .criterion("has_string", this.conditionsFromItem(Items.STRING))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("fishing_rod"));

                this.createShapeless(RecipeCategory.TOOLS, Items.FLINT_AND_STEEL)
                        .input(Items.IRON_INGOT)
                        .input(Items.FLINT)
                        .criterion("has_flint", this.conditionsFromItem(Items.FLINT))
                        .criterion("has_obsidian", this.conditionsFromItem(Blocks.OBSIDIAN))
                        .group("flint_and_steels")
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("flint_and_steel"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.GRINDSTONE)
                        .input('I', ModItemTags.STICKS)
                        .input('-', Blocks.STONE_SLAB)
                        .input('#', ItemTags.PLANKS)
                        .pattern("I-I")
                        .pattern("# #")
                        .criterion("has_stone_slab", this.conditionsFromItem(Blocks.STONE_SLAB))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("grindstone"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.HOPPER)
                        .input('C', Blocks.CHEST)
                        .input('I', ConventionalItemTags.IRON_INGOTS)
                        .pattern("I I")
                        .pattern("ICI")
                        .pattern(" I ")
                        .criterion("has_iron_ingot", this.conditionsFromTag(ConventionalItemTags.IRON_INGOTS))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("hopper"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.IRON_BARS, 16)
                        .input('#', ConventionalItemTags.IRON_INGOTS)
                        .pattern("###")
                        .pattern("###")
                        .criterion("has_iron_ingot", this.conditionsFromTag(ConventionalItemTags.IRON_INGOTS))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("iron_bars"));

                this.createShaped(RecipeCategory.DECORATIONS, Items.ITEM_FRAME)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Items.LEATHER)
                        .pattern("###")
                        .pattern("#X#")
                        .pattern("###")
                        .criterion("has_leather", this.conditionsFromItem(Items.LEATHER))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("item_frame"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.LADDER, 3)
                        .input('#', ModItemTags.STICKS)
                        .pattern("# #")
                        .pattern("###")
                        .pattern("# #")
                        .criterion("has_stick", this.conditionsFromItem(Items.STICK))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("ladder"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.LANTERN)
                        .input('#', Items.TORCH)
                        .input('X', ConventionalItemTags.IRON_NUGGETS)
                        .pattern("XXX")
                        .pattern("X#X")
                        .pattern("XXX")
                        .criterion("has_nugget", this.conditionsFromTag(ConventionalItemTags.IRON_NUGGETS))
                        .criterion("has_ingot", this.conditionsFromTag(ConventionalItemTags.IRON_INGOTS))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("lantern"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.LEVER)
                        .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                        .input('X', Items.STICK)
                        .pattern("X")
                        .pattern("#")
                        .criterion("has_cobblestone", this.conditionsFromTag(ItemTags.STONE_CRAFTING_MATERIALS))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("lever"));

                this.createShaped(RecipeCategory.TRANSPORTATION, Items.MINECART)
                        .input('#', ConventionalItemTags.IRON_INGOTS)
                        .pattern("# #")
                        .pattern("###")
                        .criterion("has_iron_ingot", this.conditionsFromTag(ConventionalItemTags.IRON_INGOTS))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("minecart"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.OBSERVER)
                        .input('Q', Items.QUARTZ)
                        .input('R', Items.REDSTONE)
                        .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                        .pattern("###")
                        .pattern("RRQ")
                        .pattern("###")
                        .criterion("has_quartz", this.conditionsFromItem(Items.QUARTZ))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("observer"));

                this.createShaped(RecipeCategory.DECORATIONS, Items.PAINTING)
                        .input('#', ModItemTags.STICKS)
                        .input('X', ItemTags.WOOL)
                        .pattern("###")
                        .pattern("#X#")
                        .pattern("###")
                        .criterion("has_wool", this.conditionsFromTag(ItemTags.WOOL))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("painting"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.PISTON)
                        .input('R', Items.REDSTONE)
                        .input('#', ItemTags.STONE_CRAFTING_MATERIALS)
                        .input('T', ItemTags.PLANKS)
                        .input('X', ConventionalItemTags.IRON_INGOTS)
                        .pattern("TTT")
                        .pattern("#X#")
                        .pattern("#R#")
                        .criterion("has_redstone", this.conditionsFromItem(Items.REDSTONE))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("piston"));

                this.createShaped(RecipeCategory.TRANSPORTATION, Blocks.POWERED_RAIL, 6)
                        .input('R', Items.REDSTONE)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Items.GOLD_INGOT)
                        .pattern("X X")
                        .pattern("X#X")
                        .pattern("XRX")
                        .criterion("has_rail", this.conditionsFromItem(Blocks.RAIL))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("powered_rail"));

                this.createShaped(RecipeCategory.TRANSPORTATION, Blocks.RAIL, 16)
                        .input('#', ModItemTags.STICKS)
                        .input('X', ConventionalItemTags.IRON_INGOTS)
                        .pattern("X X")
                        .pattern("X#X")
                        .pattern("X X")
                        .criterion("has_minecart", this.conditionsFromItem(Items.MINECART))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("rail"));

                this.createShaped(RecipeCategory.BUILDING_BLOCKS, Blocks.RAW_IRON_BLOCK)
                        .input('#', Items.RAW_IRON)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .criterion("has_raw_iron", this.conditionsFromItem(Items.RAW_IRON))
                        .group("raw_iron_blocks")
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("raw_iron_block"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.REDSTONE_TORCH)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Items.REDSTONE)
                        .pattern("X")
                        .pattern("#")
                        .criterion("has_redstone", this.conditionsFromItem(Items.REDSTONE))
                        .group("torches")
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("redstone_torch"));

                this.createShaped(RecipeCategory.TOOLS, Items.SHEARS)
                        .input('#', Items.IRON_INGOT)
                        .pattern(" #")
                        .pattern("# ")
                        .criterion("has_iron_ingot", this.conditionsFromItem(Items.IRON_INGOT))
                        .group("shears")
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("shears"));

                this.createShaped(RecipeCategory.COMBAT, Items.SHIELD)
                        .input('W', ItemTags.PLANKS)
                        .input('o', Items.IRON_INGOT)
                        .pattern("WoW")
                        .pattern("WWW")
                        .pattern(" W ")
                        .criterion("has_iron_ingot", this.conditionsFromItem(Items.IRON_INGOT))
                        .group("shields")
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("shield"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.SOUL_CAMPFIRE)
                        .input('L', ItemTags.LOGS)
                        .input('S', ModItemTags.STICKS)
                        .input('#', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                        .pattern(" S ")
                        .pattern("S#S")
                        .pattern("LLL")
                        .criterion("has_soul_sand", this.conditionsFromTag(ItemTags.SOUL_FIRE_BASE_BLOCKS))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("soul_campfire"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.SOUL_LANTERN)
                        .input('#', Items.SOUL_TORCH)
                        .input('X', ConventionalItemTags.IRON_NUGGETS)
                        .pattern("XXX")
                        .pattern("X#X")
                        .pattern("XXX")
                        .criterion("has_soul_torch", this.conditionsFromItem(Items.SOUL_TORCH))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("soul_lantern"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.SOUL_TORCH, 4)
                        .input('X', Ingredient.ofItems(Items.COAL, Items.CHARCOAL))
                        .input('#', ModItemTags.STICKS)
                        .input('S', ItemTags.SOUL_FIRE_BASE_BLOCKS)
                        .pattern("X")
                        .pattern("#")
                        .pattern("S")
                        .criterion("has_soul_sand", this.conditionsFromTag(ItemTags.SOUL_FIRE_BASE_BLOCKS))
                        .group("torches")
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("soul_torch"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.STONECUTTER)
                        .input('I', ConventionalItemTags.IRON_INGOTS)
                        .input('#', Blocks.STONE)
                        .pattern(" I ")
                        .pattern("###")
                        .criterion("has_stone", this.conditionsFromItem(Blocks.STONE))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("stonecutter"));

                this.createShaped(RecipeCategory.DECORATIONS, Blocks.TORCH, 4)
                        .input('#', ModItemTags.STICKS)
                        .input('X', Ingredient.ofItems(Items.COAL, Items.CHARCOAL))
                        .pattern("X")
                        .pattern("#")
                        .criterion("has_stone_pickaxe", this.conditionsFromItem(Items.STONE_PICKAXE))
                        .group("torches")
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("torch"));

                this.createShaped(RecipeCategory.REDSTONE, Blocks.TRIPWIRE_HOOK, 2)
                        .input('#', ItemTags.PLANKS)
                        .input('S', ModItemTags.STICKS)
                        .input('I', ConventionalItemTags.IRON_INGOTS)
                        .pattern("I")
                        .pattern("S")
                        .pattern("#")
                        .criterion("has_string", this.conditionsFromItem(Items.STRING))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe("tripwire_hook"));

                CookingRecipeJsonBuilder.createSmelting(
                                Ingredient.ofItems(
                                        Items.IRON_PICKAXE,
                                        Items.IRON_SHOVEL,
                                        Items.IRON_AXE,
                                        Items.IRON_HOE,
                                        Items.IRON_SWORD,
                                        Items.IRON_HELMET,
                                        Items.IRON_CHESTPLATE,
                                        Items.IRON_LEGGINGS,
                                        Items.IRON_BOOTS,
                                        Items.IRON_HORSE_ARMOR,
                                        Items.CHAINMAIL_HELMET,
                                        Items.CHAINMAIL_CHESTPLATE,
                                        Items.CHAINMAIL_LEGGINGS,
                                        Items.CHAINMAIL_BOOTS
                                ),
                                RecipeCategory.MISC,
                                Items.IRON_NUGGET,
                                100F,
                                20
                        )
                        .criterion("has_iron_pickaxe", this.conditionsFromItem(Items.IRON_PICKAXE))
                        .criterion("has_iron_shovel", this.conditionsFromItem(Items.IRON_SHOVEL))
                        .criterion("has_iron_axe", this.conditionsFromItem(Items.IRON_AXE))
                        .criterion("has_iron_hoe", this.conditionsFromItem(Items.IRON_HOE))
                        .criterion("has_iron_sword", this.conditionsFromItem(Items.IRON_SWORD))
                        .criterion("has_iron_helmet", this.conditionsFromItem(Items.IRON_HELMET))
                        .criterion("has_iron_chestplate", this.conditionsFromItem(Items.IRON_CHESTPLATE))
                        .criterion("has_iron_leggings", this.conditionsFromItem(Items.IRON_LEGGINGS))
                        .criterion("has_iron_boots", this.conditionsFromItem(Items.IRON_BOOTS))
                        .criterion("has_iron_horse_armor", this.conditionsFromItem(Items.IRON_HORSE_ARMOR))
                        .criterion("has_chainmail_helmet", this.conditionsFromItem(Items.CHAINMAIL_HELMET))
                        .criterion("has_chainmail_chestplate", this.conditionsFromItem(Items.CHAINMAIL_CHESTPLATE))
                        .criterion("has_chainmail_leggings", this.conditionsFromItem(Items.CHAINMAIL_LEGGINGS))
                        .criterion("has_chainmail_boots", this.conditionsFromItem(Items.CHAINMAIL_BOOTS))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe(getSmeltingItemPath(Items.IRON_NUGGET)));

                CookingRecipeJsonBuilder.createBlasting(
                                Ingredient.ofItems(
                                        Items.IRON_PICKAXE,
                                        Items.IRON_SHOVEL,
                                        Items.IRON_AXE,
                                        Items.IRON_HOE,
                                        Items.IRON_SWORD,
                                        Items.IRON_HELMET,
                                        Items.IRON_CHESTPLATE,
                                        Items.IRON_LEGGINGS,
                                        Items.IRON_BOOTS,
                                        Items.IRON_HORSE_ARMOR,
                                        Items.CHAINMAIL_HELMET,
                                        Items.CHAINMAIL_CHESTPLATE,
                                        Items.CHAINMAIL_LEGGINGS,
                                        Items.CHAINMAIL_BOOTS
                                ),
                                RecipeCategory.MISC,
                                Items.IRON_NUGGET,
                                100F,
                                20
                        )
                        .criterion("has_iron_pickaxe", this.conditionsFromItem(Items.IRON_PICKAXE))
                        .criterion("has_iron_shovel", this.conditionsFromItem(Items.IRON_SHOVEL))
                        .criterion("has_iron_axe", this.conditionsFromItem(Items.IRON_AXE))
                        .criterion("has_iron_hoe", this.conditionsFromItem(Items.IRON_HOE))
                        .criterion("has_iron_sword", this.conditionsFromItem(Items.IRON_SWORD))
                        .criterion("has_iron_helmet", this.conditionsFromItem(Items.IRON_HELMET))
                        .criterion("has_iron_chestplate", this.conditionsFromItem(Items.IRON_CHESTPLATE))
                        .criterion("has_iron_leggings", this.conditionsFromItem(Items.IRON_LEGGINGS))
                        .criterion("has_iron_boots", this.conditionsFromItem(Items.IRON_BOOTS))
                        .criterion("has_iron_horse_armor", this.conditionsFromItem(Items.IRON_HORSE_ARMOR))
                        .criterion("has_chainmail_helmet", this.conditionsFromItem(Items.CHAINMAIL_HELMET))
                        .criterion("has_chainmail_chestplate", this.conditionsFromItem(Items.CHAINMAIL_CHESTPLATE))
                        .criterion("has_chainmail_leggings", this.conditionsFromItem(Items.CHAINMAIL_LEGGINGS))
                        .criterion("has_chainmail_boots", this.conditionsFromItem(Items.CHAINMAIL_BOOTS))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe(getBlastingItemPath(Items.IRON_NUGGET)));

                CookingRecipeJsonBuilder.createSmelting(
                                Ingredient.ofItems(
                                        Items.GOLDEN_PICKAXE,
                                        Items.GOLDEN_SHOVEL,
                                        Items.GOLDEN_AXE,
                                        Items.GOLDEN_HOE,
                                        Items.GOLDEN_SWORD,
                                        Items.GOLDEN_HELMET,
                                        Items.GOLDEN_CHESTPLATE,
                                        Items.GOLDEN_LEGGINGS,
                                        Items.GOLDEN_BOOTS,
                                        Items.GOLDEN_HORSE_ARMOR
                                ),
                                RecipeCategory.MISC,
                                Items.GOLD_NUGGET,
                                100.0F,
                                20
                        )
                        .criterion("has_golden_pickaxe", this.conditionsFromItem(Items.GOLDEN_PICKAXE))
                        .criterion("has_golden_shovel", this.conditionsFromItem(Items.GOLDEN_SHOVEL))
                        .criterion("has_golden_axe", this.conditionsFromItem(Items.GOLDEN_AXE))
                        .criterion("has_golden_hoe", this.conditionsFromItem(Items.GOLDEN_HOE))
                        .criterion("has_golden_sword", this.conditionsFromItem(Items.GOLDEN_SWORD))
                        .criterion("has_golden_helmet", this.conditionsFromItem(Items.GOLDEN_HELMET))
                        .criterion("has_golden_chestplate", this.conditionsFromItem(Items.GOLDEN_CHESTPLATE))
                        .criterion("has_golden_leggings", this.conditionsFromItem(Items.GOLDEN_LEGGINGS))
                        .criterion("has_golden_boots", this.conditionsFromItem(Items.GOLDEN_BOOTS))
                        .criterion("has_golden_horse_armor", this.conditionsFromItem(Items.GOLDEN_HORSE_ARMOR))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe(getSmeltingItemPath(Items.GOLD_NUGGET)));

                CookingRecipeJsonBuilder.createSmelting(
                                Ingredient.ofItems(
                                        Items.GOLDEN_PICKAXE,
                                        Items.GOLDEN_SHOVEL,
                                        Items.GOLDEN_AXE,
                                        Items.GOLDEN_HOE,
                                        Items.GOLDEN_SWORD,
                                        Items.GOLDEN_HELMET,
                                        Items.GOLDEN_CHESTPLATE,
                                        Items.GOLDEN_LEGGINGS,
                                        Items.GOLDEN_BOOTS,
                                        Items.GOLDEN_HORSE_ARMOR
                                ),
                                RecipeCategory.MISC,
                                Items.GOLD_NUGGET,
                                100.0F,
                                20
                        )
                        .criterion("has_golden_pickaxe", this.conditionsFromItem(Items.GOLDEN_PICKAXE))
                        .criterion("has_golden_shovel", this.conditionsFromItem(Items.GOLDEN_SHOVEL))
                        .criterion("has_golden_axe", this.conditionsFromItem(Items.GOLDEN_AXE))
                        .criterion("has_golden_hoe", this.conditionsFromItem(Items.GOLDEN_HOE))
                        .criterion("has_golden_sword", this.conditionsFromItem(Items.GOLDEN_SWORD))
                        .criterion("has_golden_helmet", this.conditionsFromItem(Items.GOLDEN_HELMET))
                        .criterion("has_golden_chestplate", this.conditionsFromItem(Items.GOLDEN_CHESTPLATE))
                        .criterion("has_golden_leggings", this.conditionsFromItem(Items.GOLDEN_LEGGINGS))
                        .criterion("has_golden_boots", this.conditionsFromItem(Items.GOLDEN_BOOTS))
                        .criterion("has_golden_horse_armor", this.conditionsFromItem(Items.GOLDEN_HORSE_ARMOR))
                        .offerTo(this.exporter, helper.speedrunnerModOfVanillaRecipe(getBlastingItemPath(Items.GOLD_NUGGET)));

                // MODDED
                helper.createModdedFenceRecipe(ModBlocks.DEAD_SPEEDRUNNER_FENCE, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
                helper.createModdedFenceRecipe(ModBlocks.SPEEDRUNNER_FENCE, ModBlocks.SPEEDRUNNER_PLANKS);

                helper.createModdedFenceGateRecipe(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
                helper.createModdedFenceGateRecipe(ModBlocks.SPEEDRUNNER_FENCE_GATE, ModBlocks.SPEEDRUNNER_PLANKS);

                helper.createSign(ModBlocks.DEAD_SPEEDRUNNER_SIGN, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
                helper.createSign(ModBlocks.SPEEDRUNNER_SIGN, ModBlocks.SPEEDRUNNER_PLANKS);

                this.createDoorRecipe(ModBlocks.SPEEDRUNNER_DOOR, Ingredient.ofItem(ModItems.SPEEDRUNNER_INGOT))
                        .criterion(hasItem(ModItems.SPEEDRUNNER_INGOT), this.conditionsFromItem(ModItems.SPEEDRUNNER_INGOT))
                        .offerTo(this.exporter);
                this.createDoorRecipe(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_DOOR, Ingredient.ofItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .offerTo(this.exporter);
                this.createDoorRecipe(ModBlocks.WOODEN_SPEEDRUNNER_DOOR, Ingredient.ofItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .offerTo(this.exporter);

                this.offer2x2CompactingRecipe(RecipeCategory.REDSTONE, ModBlocks.SPEEDRUNNER_TRAPDOOR, ModItems.SPEEDRUNNER_INGOT);
                this.createTrapdoorRecipe(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_TRAPDOOR, Ingredient.ofItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .offerTo(this.exporter);
                this.createTrapdoorRecipe(ModBlocks.WOODEN_SPEEDRUNNER_TRAPDOOR, Ingredient.ofItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .offerTo(this.exporter);

                this.createButtonRecipe(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_BUTTON, Ingredient.ofItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .offerTo(this.exporter);
                this.createButtonRecipe(ModBlocks.WOODEN_SPEEDRUNNER_BUTTON, Ingredient.ofItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .offerTo(this.exporter);

                this.createStairsRecipe(ModBlocks.DEAD_SPEEDRUNNER_STAIRS, Ingredient.ofItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.DEAD_SPEEDRUNNER_PLANKS))
                        .offerTo(this.exporter);
                this.createStairsRecipe(ModBlocks.SPEEDRUNNER_STAIRS, Ingredient.ofItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .criterion(hasItem(ModBlocks.SPEEDRUNNER_PLANKS), this.conditionsFromItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .offerTo(this.exporter);

                this.offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DEAD_SPEEDRUNNER_SLAB, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
                this.offerSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPEEDRUNNER_SLAB, ModBlocks.SPEEDRUNNER_PLANKS);

                this.offerPressurePlateRecipe(ModBlocks.SPEEDRUNNER_WEIGHTED_PRESSURE_PLATE, ModItems.SPEEDRUNNER_INGOT);
                this.offerPressurePlateRecipe(ModBlocks.DEAD_WOODEN_SPEEDRUNNER_PRESSURE_PLATE, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
                this.offerPressurePlateRecipe(ModBlocks.WOODEN_SPEEDRUNNER_PRESSURE_PLATE, ModBlocks.SPEEDRUNNER_PLANKS);

                this.offerPlanksRecipe(ModBlocks.DEAD_SPEEDRUNNER_PLANKS, ModItemTags.Block.DEAD_SPEEDRUNNER_LOGS, 4);
                this.offerPlanksRecipe(ModBlocks.SPEEDRUNNER_PLANKS, ModItemTags.Block.SPEEDRUNNER_LOGS, 4);

                this.offerBarkBlockRecipe(ModBlocks.DEAD_SPEEDRUNNER_WOOD, ModBlocks.DEAD_SPEEDRUNNER_LOG);
                this.offerBarkBlockRecipe(ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_WOOD, ModBlocks.DEAD_STRIPPED_SPEEDRUNNER_LOG);
                this.offerBarkBlockRecipe(ModBlocks.SPEEDRUNNER_WOOD, ModBlocks.SPEEDRUNNER_LOG);
                this.offerBarkBlockRecipe(ModBlocks.STRIPPED_SPEEDRUNNER_WOOD, ModBlocks.STRIPPED_SPEEDRUNNER_LOG);

                this.offerHangingSignRecipe(ModBlocks.DEAD_SPEEDRUNNER_HANGING_SIGN, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
                this.offerHangingSignRecipe(ModBlocks.SPEEDRUNNER_HANGING_SIGN, ModBlocks.SPEEDRUNNER_PLANKS);

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
                                100.0F,
                                20
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
                                100.0F,
                                20
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
                        .input(Items.FIRE_CHARGE)
                        .input(Items.BLAZE_POWDER)
                        .criterion("has_items", this.conditionsFromTag(ModItemTags.AdvancementCriterions.EYE_OF_ANNUL))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.MISC, ModItems.BLAZE_SPOTTER)
                        .input(Items.ENDER_PEARL)
                        .input(Items.FIRE_CHARGE)
                        .input(Items.LAVA_BUCKET)
                        .criterion("has_items", this.conditionsFromItem(Items.LAVA_BUCKET))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.MISC, ModItems.DRAGONS_PEARL)
                        .input(Items.ENDER_PEARL)
                        .input(Items.FIRE_CHARGE)
                        .input(Items.BLAZE_POWDER)
                        .input(ModItems.SPEEDRUNNERS_EYE)
                        .criterion("has_items", this.conditionsFromTag(ModItemTags.AdvancementCriterions.DRAGONS_PEARL))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.MISC, ModItems.DRAGONS_SWORD)
                        .input(ModItems.SPEEDRUNNER_SWORD)
                        .input(ModItems.DRAGONS_PEARL)
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
                        .input(Items.FIRE_CHARGE)
                        .input(Items.ENCHANTED_GOLDEN_APPLE)
                        .input(ModItems.SPEEDRUNNERS_EYE)
                        .criterion("has_items", this.conditionsFromItem(Items.ENCHANTED_GOLDEN_APPLE))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.MISC, ModItems.SPEEDRUNNERS_EYE)
                        .input(Items.ENDER_PEARL)
                        .input(ModItems.SPEEDRUNNER_INGOT)
                        .criterion("has_items", this.conditionsFromItem(ModItems.SPEEDRUNNER_INGOT))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.TOOLS, ModItems.SPEEDRUNNER_FLINT_AND_STEEL)
                        .input(Items.FLINT)
                        .input(ModItems.SPEEDRUNNER_INGOT)
                        .criterion("has_speedrunner_ingot", this.conditionsFromItem(ModItems.SPEEDRUNNER_INGOT))
                        .group("flint_and_steels")
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.MISC, ModItems.GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE, 2)
                        .input('#', Items.GOLD_INGOT)
                        .input('C', Items.GOLD_BLOCK)
                        .input('S', ModItems.GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE)
                        .pattern("#S#")
                        .pattern("#C#")
                        .pattern("###")
                        .showNotification(true)
                        .criterion("has_gold_block", this.conditionsFromItem(Items.GOLD_BLOCK))
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.RAW_SPEEDRUNNER_BLOCK)
                        .input('#', ModItems.RAW_SPEEDRUNNER)
                        .pattern("###")
                        .pattern("###")
                        .pattern("###")
                        .criterion("has_raw_speedrunner", this.conditionsFromItem(ModItems.RAW_SPEEDRUNNER))
                        .group("raw_iron_blocks")
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.MISC, ModItems.PIGLIN_AWAKENER)
                        .input('#', Items.GOLD_INGOT)
                        .input('X', ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)
                        .pattern("###")
                        .pattern("#X#")
                        .pattern("###")
                        .criterion("has_items", this.conditionsFromTag(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES))
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPEEDRUNNERS_WORKBENCH)
                        .input('@', ModItems.SPEEDRUNNER_INGOT)
                        .input('#', ModItemTags.Block.SPEEDRUNNER_PLANKS)
                        .pattern("@@")
                        .pattern("##")
                        .pattern("##")
                        .criterion("has_planks", this.conditionsFromTag(ModItemTags.Block.SPEEDRUNNER_PLANKS))
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.COMBAT, ModItems.SPEEDRUNNER_BOW)
                        .input('/', ModItemTags.STICKS)
                        .input('S', ModItems.SPEEDRUNNER_INGOT)
                        .pattern(" /S")
                        .pattern("/ S")
                        .pattern(" /S")
                        .criterion("has_speedrunner_ingot", this.conditionsFromItem(ModItems.SPEEDRUNNER_INGOT))
                        .group("bows")
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.COMBAT, ModItems.SPEEDRUNNER_CROSSBOW)
                        .input('~', Items.STRING)
                        .input('#', ModItems.SPEEDRUNNER_STICK)
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

                this.createShaped(RecipeCategory.MISC, ModItems.SPEEDRUNNER_STICK, 4)
                        .input('S', ModItemTags.Block.SPEEDRUNNER_PLANKS)
                        .pattern("S")
                        .pattern("S")
                        .group("sticks")
                        .criterion("has_planks", this.conditionsFromItem(ModBlocks.SPEEDRUNNER_PLANKS))
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.MISC, ModBlocks.SPEEDRUNNER_PLANKS)
                        .input('/', ModItems.SPEEDRUNNER_STICK)
                        .pattern("//")
                        .pattern("//")
                        .criterion("has_sticks", this.conditionsFromItem(ModItems.SPEEDRUNNER_STICK))
                        .offerTo(this.exporter, helper.speedrunnerModRecipe("speedrunner_planks_from_speedrunner_stick"));

                this.createShaped(RecipeCategory.MISC, ModItems.WITHER_BONE)
                        .input('B', Items.BONE)
                        .input('C', Items.COAL)
                        .pattern("BC")
                        .pattern("CB")
                        .criterion("has_bone", this.conditionsFromItem(Items.BONE))
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.COMBAT, ModItems.WITHER_SWORD)
                        .input('#', ModItems.SPEEDRUNNER_STICK)
                        .input('X', ModItems.WITHER_BONE)
                        .pattern("X")
                        .pattern("X")
                        .pattern("#")
                        .criterion("has_wither_bone", this.conditionsFromItem(ModItems.WITHER_BONE))
                        .offerTo(this.exporter);

                this.createShaped(RecipeCategory.MISC, Blocks.OAK_PLANKS)
                        .input('/', Items.STICK)
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

                this.createShapeless(RecipeCategory.MISC, Items.STRING, 4)
                        .input(ItemTags.WOOL)
                        .criterion("has_string", this.conditionsFromItem(Items.STRING))
                        .offerTo(this.exporter, helper.speedrunnerModRecipe("string_from_wool"));

                this.createShapeless(RecipeCategory.MISC, ModItems.CRAFTABLE_INFINI_PEARL)
                        .input(Items.ENDER_PEARL)
                        .input(ModItems.SPEEDRUNNERS_EYE)
                        .input(ModItems.ENDER_THRUSTER)
                        .input(ModItems.ENDER_MATTER)
                        .criterion("has_ender_matter", this.conditionsFromItem(ModItems.ENDER_MATTER))
                        .offerTo(this.exporter);

                this.createShapeless(RecipeCategory.COMBAT, ModItems.SPEEDRUNNERS_TOTEM)
                        .input(Items.TOTEM_OF_UNDYING)
                        .input(ModItems.SPEEDRUNNERS_EYE)
                        .input(ModItemTags.INFINI_PEARLS)
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