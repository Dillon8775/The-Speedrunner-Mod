package net.dillon.speedrunnermod.recipe;

import com.google.common.collect.ImmutableList;
import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.component.ModPotions;
import net.dillon.speedrunnermod.item.core.ModItems;
import net.dillon.speedrunnermod.tag.ModBlockItemTags;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.*;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

import static net.dillon.dillonlib.util.Arithmetics.S_asTick;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod recipes.
 */
public class ModRecipes extends RecipeProvider {
    private static final ImmutableList<ItemLike> IGNEOUS_ORES = ImmutableList.of(ModBlocks.IGNEOUS_ORE, ModBlocks.DEEPSLATE_IGNEOUS_ORE, ModBlocks.NETHER_IGNEOUS_ORE);
    private static final ImmutableList<ItemLike> EXPERIENCE_ORES = ImmutableList.of(ModBlocks.EXPERIENCE_ORE, ModBlocks.DEEPSLATE_EXPERIENCE_ORE, ModBlocks.NETHER_EXPERIENCE_ORE);
    private static final ImmutableList<ItemLike> SPEEDRUNNER_ORES_AND_BLOCKS = ImmutableList.of(ModBlocks.SPEEDRUNNER_ORE, ModBlocks.DEEPSLATE_SPEEDRUNNER_ORE, ModBlocks.NETHER_SPEEDRUNNER_ORE, ModItems.RAW_SPEEDRUNNER);
    protected static final int CENTER_SLOT_3x3 = 4;
    protected final HolderGetter<Item> items;

    protected ModRecipes(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
        this.items = this.output.lookup(Registries.ITEM);
    }

    /**
     * Registers all Speedrunner Mod {@code custom recipe serializers}.
     */
    public static void registerModRecipeSerializers() {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_inventory_preserver"), InventoryPreserverRecipe.SERIALIZER);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_dragon_fireball"), DragonFireballRecipe.SERIALIZER);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_piglin_awakener"), PiglinAwakenerRecipe.SERIALIZER);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_speedrunner_shield_decoration"), SpeedrunnerShieldDecorationRecipe.SERIALIZER);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_golden_shield_decoration"), GoldenShieldDecorationRecipe.SERIALIZER);
    }

    /**
     * Builds all recipes.
     */
    @Override
    public void buildRecipes() {
        buildBrewingRecipes();
        buildToolsAndWeapons();
        buildSmithingTemplateRecipes();
        buildSmeltableOres();
        buildCompactingAndMetalBurningRecipes();

        buildFoods();
        buildBoats();
        buildWoodRecipes();

        buildSpecialRecipes();
        buildModdedRecipes();

        buildModifiedRecipes();
        overrideVanillaRecipes();
    }

    /**
     * Builds all brewing recipes.
     */
    private void buildBrewingRecipes() {
        new BrewingProvider(output) {
            @Override
            protected void addContainers() {
                this.addContainer(Items.LINGERING_POTION);
                this.addContainer(Items.POTION);
                this.addContainer(Items.SPLASH_POTION);
            }

            @Override
            protected void addContainerTransformations() {
                this.addContainerTransformation(Items.POTION, Items.GUNPOWDER, Items.SPLASH_POTION);
                this.addContainerTransformation(Items.SPLASH_POTION, Items.DRAGON_BREATH, Items.LINGERING_POTION);
            }

            @Override
            protected void buildMixes() {
                this.buildMix(Potions.WATER, ModItems.ENDER_MATTER, ModPotions.DRAGONS_AURA);
                this.buildMix(ModPotions.DRAGONS_AURA, Items.REDSTONE, ModPotions.LONG_DRAGONS_AURA);

                this.buildMix(Potions.AWKWARD, Items.WITHER_SKELETON_SKULL, ModPotions.WITHERED);
                this.buildMix(Potions.AWKWARD, Items.WITHER_ROSE, ModPotions.WITHERED);
                this.buildMix(ModPotions.WITHERED, Items.REDSTONE, ModPotions.LONG_WITHERED);
                this.buildMix(ModPotions.WITHERED, Items.GLOWSTONE_DUST, ModPotions.STRONG_WITHERED);

                this.buildMix(Potions.WATER, Items.LILY_PAD, Potions.LUCK);
                this.buildMix(Potions.LUCK, Items.GLOWSTONE_DUST, ModPotions.STRONG_LUCK);
            }

            @Override
            protected void save(BrewingRecipeBuilder builder) {
                ResourceKey<Recipe<?>> defaultKey = builder.defaultId();

                ResourceKey<Recipe<?>> minecraftKey = ResourceKey.create(
                        Registries.RECIPE,
                        ofSpeedrunnerMod(defaultKey.identifier().getPath())
                );

                builder.save(output, minecraftKey);
            }
        }.buildRecipes();
    }

    /**
     * Builds all tools and weapon recipes.
     */
    private void buildToolsAndWeapons() {
        createModdedAxe(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_AXE);
        createModdedHoe(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_HOE);
        createModdedPickaxe(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_PICKAXE);
        createModdedShovel(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_SHOVEL);
        createModdedSword(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_SWORD);
        createModdedSpear(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS, ModItems.SPEEDRUNNER_SPEAR);

        createModdedHelmet(ModItems.SPEEDRUNNER_INGOT, ModItems.SPEEDRUNNER_HELMET);
        createModdedChestplate(ModItems.SPEEDRUNNER_INGOT, ModItems.SPEEDRUNNER_CHESTPLATE);
        createModdedLeggings(ModItems.SPEEDRUNNER_INGOT, ModItems.SPEEDRUNNER_LEGGINGS);
        createModdedBoots(ModItems.SPEEDRUNNER_INGOT, ModItems.SPEEDRUNNER_BOOTS);

        offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_SWORD, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_SWORD);
        offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_SHOVEL, RecipeCategory.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_SHOVEL);
        offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_PICKAXE, RecipeCategory.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_PICKAXE);
        offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_AXE, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_AXE);
        offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_HOE, RecipeCategory.TOOLS, ModItems.GOLDEN_SPEEDRUNNER_HOE);
        offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_SPEAR, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_SPEAR);

        offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_HELMET, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_HELMET);
        offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_CHESTPLATE, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_CHESTPLATE);
        offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_LEGGINGS, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_LEGGINGS);
        offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_BOOTS, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_BOOTS);
        offerGoldenSpeedrunnerUpgradeRecipe(ModItems.SPEEDRUNNER_HARNESS, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_HARNESS);
        offerGoldenSpeedrunnerUpgradeRecipe(Items.GOLDEN_NAUTILUS_ARMOR, RecipeCategory.COMBAT, ModItems.GOLDEN_SPEEDRUNNER_NAUTILUS_ARMOR);
    }

    /**
     * Builds all smithing template recipes.
     */
    private void buildSmithingTemplateRecipes() {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.DRAGON_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(ModItems.SPEEDRUNNER_SWORD),
                        Ingredient.of(ModItems.DRAGONS_PEARL),
                        RecipeCategory.COMBAT,
                        ModItems.DRAGONS_SWORD
                )
                .unlocks("has_speedrunner_sword", this.has(ModItems.SPEEDRUNNER_SWORD))
                .save(this.output, "dragons_sword_smithing");

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
    }

    /**
     * Builds all smeltable ore recipes.
     */
    private void buildSmeltableOres() {
        offerOreMaterial(IGNEOUS_ORES, ModItems.IGNEOUS_ROCK, 0.6F, "igneous_rock");
        offerOreMaterial(EXPERIENCE_ORES, ModItems.EXPERIENCE_FRAGMENT, 3.0F, "experience_fragment");
        offerOreMaterial(SPEEDRUNNER_ORES_AND_BLOCKS, ModItems.SPEEDRUNNER_INGOT, 0.85F, "speedrunner_ingot");
    }

    /**
     * Builds all compacting and metal burning recipes.
     */
    private void buildCompactingAndMetalBurningRecipes() {
        offerModdedReversibleCompactingRecipes(
                RecipeCategory.MISC,
                ModItems.SPEEDRUNNER_INGOT,
                RecipeCategory.BUILDING_BLOCKS,
                ModBlocks.SPEEDRUNNER_BLOCK,
                "speedrunner_block_from_speedrunner_ingot",
                "speedrunner_block",
                "speedrunner_ingot_from_speedrunner_block",
                "speedrunner_ingot"
        );
        offerModdedReversibleCompactingRecipes(
                RecipeCategory.MISC,
                ModItems.RAW_SPEEDRUNNER,
                RecipeCategory.MISC,
                ModItems.RAW_SPEEDRUNNER_BLOCK,
                "raw_speedrunner_block_from_raw_speedrunner_ingot",
                "raw_speedrunner_block",
                "raw_speedrunner_from_raw_speedrunner_block",
                "raw_speedrunner"
        );
        offerModdedReversibleCompactingRecipes(
                RecipeCategory.MISC,
                ModItems.SPEEDRUNNER_NUGGET,
                RecipeCategory.MISC,
                ModItems.SPEEDRUNNER_INGOT,
                "speedrunner_ingot_from_speedrunner_nuggets",
                "speedrunner_ingot",
                "speedrunner_nuggets_from_speedrunner_ingot",
                "speedrunner_nugget"
        );

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
                        S_asTick(10)
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
                        S_asTick(5)
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
    }

    /**
     * Builds all food recipes.
     */
    private void buildFoods() {
        createCookableFood(Items.ROTTEN_FLESH, ModItems.COOKED_FLESH);
        createCookableFood(ModItems.PIGLIN_PORK, ModItems.COOKED_PIGLIN_PORK);
    }

    /**
     * Builds all boat recipes.
     */
    private void buildBoats() {
        createBoatSet(ModItems.SPEEDRUNNER_BOAT, ModItems.SPEEDRUNNER_CHEST_BOAT, ModBlocks.SPEEDRUNNER_PLANKS);
        createBoatSet(ModItems.DEAD_SPEEDRUNNER_BOAT, ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
        createBoatSet(ModItems.WARPED_BOAT, ModItems.WARPED_CHEST_BOAT, Blocks.WARPED_PLANKS);
        createBoatSet(ModItems.CRIMSON_BOAT, ModItems.CRIMSON_CHEST_BOAT, Blocks.CRIMSON_PLANKS);
    }

    /**
     * Builds all wood recipes.
     */
    private void buildWoodRecipes() {
        createModdedFenceRecipe(ModBlocks.DEAD_SPEEDRUNNER_FENCE, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
        createModdedFenceRecipe(ModBlocks.SPEEDRUNNER_FENCE, ModBlocks.SPEEDRUNNER_PLANKS);

        createModdedFenceGateRecipe(ModBlocks.DEAD_SPEEDRUNNER_FENCE_GATE, ModBlocks.DEAD_SPEEDRUNNER_PLANKS);
        createModdedFenceGateRecipe(ModBlocks.SPEEDRUNNER_FENCE_GATE, ModBlocks.SPEEDRUNNER_PLANKS);

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

        this.planksFromLogs(ModBlocks.DEAD_SPEEDRUNNER_PLANKS, ModBlockItemTags.DEAD_SPEEDRUNNER_LOGS.item(), 4);
        this.planksFromLogs(ModBlocks.SPEEDRUNNER_PLANKS, ModBlockItemTags.SPEEDRUNNER_LOGS.item(), 4);

        this.woodFromLogs(ModBlocks.DEAD_SPEEDRUNNER_WOOD, ModBlocks.DEAD_SPEEDRUNNER_LOG);
        this.woodFromLogs(ModBlocks.SPEEDRUNNER_WOOD, ModBlocks.SPEEDRUNNER_LOG);

        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_BOAT, ModItems.SPEEDRUNNER_BOAT);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_CHEST_BOAT, ModItems.SPEEDRUNNER_CHEST_BOAT);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_LOG, ModItems.SPEEDRUNNER_LOG);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_WOOD, ModItems.SPEEDRUNNER_WOOD);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_LEAVES, ModItems.SPEEDRUNNER_LEAVES);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_SAPLING, ModItems.SPEEDRUNNER_SAPLING);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_PLANKS, ModItems.SPEEDRUNNER_PLANKS);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_SLAB, ModItems.SPEEDRUNNER_SLAB);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_STAIRS, ModItems.SPEEDRUNNER_STAIRS);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_FENCE, ModItems.SPEEDRUNNER_FENCE);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_FENCE_GATE, ModItems.SPEEDRUNNER_FENCE_GATE);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_BUTTON, ModItems.SPEEDRUNNER_BUTTON);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_PRESSURE_PLATE, ModItems.SPEEDRUNNER_PRESSURE_PLATE);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_TRAPDOOR, ModItems.SPEEDRUNNER_TRAPDOOR);
        offerSmeltableDeadSpeedrunner(ModItems.DEAD_SPEEDRUNNER_DOOR, ModItems.SPEEDRUNNER_DOOR);

        createStickRecipe(true, "speedrunner_stick_from_dead_speedrunner_planks");
        createStickRecipe(false, "speedrunner_stick_from_speedrunner_planks");
        createReversePlankRecipe();
    }

    /**
     * Builds all special recipes.
     */
    private void buildSpecialRecipes() {
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
                                                'O', ofTagIngredient(ModItemTags.PIGLIN_AWAKENER_CRAFTABLES)),
                                        "###",
                                        "#O#",
                                        "###"
                                ),
                                new ItemStackTemplate(ModItems.PIGLIN_AWAKENER))
                )
                .unlockedBy("has_item", this.has(ModItemTags.AdvancementCriterions.PIGLIN_AWAKENER))
                .save(this.output, "piglin_awakener");

        SpecialRecipeBuilder.special(
                        () -> new DragonFireballRecipe(
                                new ItemStackTemplate(ModItems.DRAGON_FIREBALL, 8)
                        )
                )
                .unlockedBy("has_fireball", this.has(Items.FIRE_CHARGE))
                .save(this.output, "dragons_fireball");

        SpecialRecipeBuilder.special(
                        () -> new InventoryPreserverRecipe(
                                new ItemStackTemplate(ModItems.INVENTORY_PRESERVER)
                        )
                )
                .unlockedBy("has_other_preserver", this.has(ModItems.INVENTORY_PRESERVER))
                .save(this.output, "inventory_preserver");
    }

    /**
     * Builds all modded recipes.
     */
    private void buildModdedRecipes() {
        this.shapeless(RecipeCategory.MISC, ModItems.ANNUL_EYE)
                .requires(ModItems.ENDER_MATTER)
                .requires(Items.ENDER_EYE)
                .unlockedBy("has_items", this.has(ModItemTags.AdvancementCriterions.EYE_OF_ANNUL))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModItems.BLAZE_SPOTTER)
                .requires(ModItems.INFERNO_EYE)
                .requires(Items.LAVA_BUCKET)
                .unlockedBy("has_items", this.has(ModItemTags.AdvancementCriterions.BLAZE_SPOTTER))
                .save(this.output);

        this.shapeless(RecipeCategory.MISC, ModItems.DRAGONS_PEARL)
                .requires(Items.BLAZE_POWDER)
                .requires(ModItems.SPEEDRUNNERS_EYE)
                .unlockedBy("has_items", this.has(ModItemTags.AdvancementCriterions.DRAGONS_PEARL))
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

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.SPEEDRUNNERS_WORKBENCH)
                .define('I', ModItems.SPEEDRUNNER_INGOT)
                .define('B', ModItems.SPEEDRUNNER_BLOCK)
                .define('P', ModBlockItemTags.SPEEDRUNNER_PLANKS.item())
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
                .define('/', ConventionalItemTags.WOODEN_RODS)
                .define('S', Items.STRING)
                .pattern(" /S")
                .pattern("/ S")
                .pattern(" /S")
                .unlockedBy("has_string", this.has(Items.STRING))
                .group("bows")
                .save(this.output);

        this.shaped(RecipeCategory.COMBAT, ModItems.SPEEDRUNNER_CROSSBOW)
                .define('~', Items.STRING)
                .define('#', ConventionalItemTags.WOODEN_RODS)
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

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModBlocks.DOOM_STONE, 8)
                .define('E', Blocks.END_STONE)
                .define('F', ModBlocks.FLESH_BLOCK)
                .pattern("EEE")
                .pattern("EFE")
                .pattern("EEE")
                .unlockedBy("has_end_stone", this.has(Blocks.END_STONE))
                .save(this.output);

        this.shaped(RecipeCategory.BUILDING_BLOCKS, ModItems.FLESH_BLOCK)
                .define('#', ModItemTags.FLESH)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_flesh_item", this.has(ModItemTags.FLESH))
                .save(output);

        this.shapeless(RecipeCategory.MISC, ModItems.INFINI_PEARL)
                .requires(Items.ENDER_PEARL)
                .requires(ModItems.SPEEDRUNNERS_EYE)
                .requires(ModItems.ENDER_MATTER)
                .unlockedBy("has_ender_matter", this.has(ModItems.ENDER_MATTER))
                .save(output);

        this.shapeless(RecipeCategory.COMBAT, ModItems.SPEEDRUNNERS_TOTEM)
                .requires(Items.TOTEM_OF_UNDYING)
                .requires(ModItems.ENDER_MATTER)
                .unlockedBy("has_totem", this.has(Items.TOTEM_OF_UNDYING))
                .save(output);
    }

    /**
     * Creates all modified recipes.
     */
    private void buildModifiedRecipes() {
        this.shaped(RecipeCategory.MISC, Blocks.OAK_PLANKS)
                .define('/', Items.STICK)
                .group("planks")
                .pattern("//")
                .pattern("//")
                .unlockedBy("has_stick", this.has(Items.STICK))
                .save(output, this.speedrunnerModRecipe("oak_planks_from_sticks"));

        this.shaped(RecipeCategory.MISC, Blocks.OBSIDIAN)
                .define('#', ModItems.IGNEOUS_ROCK)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_igneous_rock", this.has(ModItems.IGNEOUS_ROCK))
                .save(output, this.speedrunnerModRecipe("obsidian_from_igneous_rocks"));

        this.shaped(RecipeCategory.FOOD, Items.ENCHANTED_GOLDEN_APPLE)
                .define('a', Items.GOLDEN_APPLE)
                .define('B', Items.GOLD_BLOCK)
                .pattern("BBB")
                .pattern("BaB")
                .pattern("BBB")
                .unlockedBy("has_gold_block", this.has(Items.GOLD_BLOCK))
                .save(output, this.speedrunnerModRecipe("enchanted_golden_apple"));

        this.shaped(RecipeCategory.COMBAT, Items.TOTEM_OF_UNDYING)
                .define('e', Items.ENCHANTED_GOLDEN_APPLE)
                .define('B', Items.GOLD_BLOCK)
                .pattern("BBB")
                .pattern("BeB")
                .pattern("BBB")
                .unlockedBy("has_enchanted_golden_apple", this.has(Items.ENCHANTED_GOLDEN_APPLE))
                .save(output, this.speedrunnerModRecipe("totem_of_undying"));

        this.shaped(RecipeCategory.BREWING, Items.BLAZE_ROD)
                .define('P', Items.BLAZE_POWDER)
                .define('/', ConventionalItemTags.WOODEN_RODS)
                .pattern("P")
                .pattern("P")
                .pattern("/")
                .unlockedBy("has_blaze_powder", this.has(Items.BLAZE_POWDER))
                .save(output, this.speedrunnerModRecipe("blaze_rod"));

        this.shapeless(RecipeCategory.MISC, Items.STRING, 4)
                .requires(ItemTags.WOOL)
                .unlockedBy("has_string", this.has(Items.STRING))
                .save(output, this.speedrunnerModRecipe("string_from_wool"));

        this.shapeless(RecipeCategory.TOOLS, Items.EXPERIENCE_BOTTLE, 2)
                .requires(Items.GLASS_BOTTLE)
                .requires(ModItems.EXPERIENCE_FRAGMENT)
                .unlockedBy("has_item", this.has(ModItemTags.EXPERIENCE_BOTTLE_CRAFTABLES))
                .save(output);
    }

    /**
     * Overrides certain vanilla recipes.
     */
    private void overrideVanillaRecipes() {
        overrideVanillaAxes(
                Map.of(
                        Items.WOODEN_AXE, ofTagIngredient(ItemTags.WOODEN_TOOL_MATERIALS),
                        Items.COPPER_AXE, ofTagIngredient(ItemTags.COPPER_TOOL_MATERIALS),
                        Items.STONE_AXE, ofTagIngredient(ItemTags.STONE_TOOL_MATERIALS),
                        Items.GOLDEN_AXE, ofTagIngredient(ItemTags.GOLD_TOOL_MATERIALS),
                        Items.IRON_AXE, ofTagIngredient(ItemTags.IRON_TOOL_MATERIALS),
                        Items.DIAMOND_AXE, ofTagIngredient(ItemTags.DIAMOND_TOOL_MATERIALS),
                        Items.NETHERITE_AXE, ofTagIngredient(ItemTags.NETHERITE_TOOL_MATERIALS)
                )
        );

        overrideVanillaHoes(
                Map.of(
                        Items.WOODEN_HOE, ofTagIngredient(ItemTags.WOODEN_TOOL_MATERIALS),
                        Items.COPPER_HOE, ofTagIngredient(ItemTags.COPPER_TOOL_MATERIALS),
                        Items.STONE_HOE, ofTagIngredient(ItemTags.STONE_TOOL_MATERIALS),
                        Items.GOLDEN_HOE, ofTagIngredient(ItemTags.GOLD_TOOL_MATERIALS),
                        Items.IRON_HOE, ofTagIngredient(ItemTags.IRON_TOOL_MATERIALS),
                        Items.DIAMOND_HOE, ofTagIngredient(ItemTags.DIAMOND_TOOL_MATERIALS),
                        Items.NETHERITE_HOE, ofTagIngredient(ItemTags.NETHERITE_TOOL_MATERIALS)
                )
        );

        overrideVanillaPickaxes(
                Map.of(
                        Items.WOODEN_PICKAXE, ofTagIngredient(ItemTags.WOODEN_TOOL_MATERIALS),
                        Items.COPPER_PICKAXE, ofTagIngredient(ItemTags.COPPER_TOOL_MATERIALS),
                        Items.STONE_PICKAXE, ofTagIngredient(ItemTags.STONE_TOOL_MATERIALS),
                        Items.GOLDEN_PICKAXE, ofTagIngredient(ItemTags.GOLD_TOOL_MATERIALS),
                        Items.IRON_PICKAXE, ofTagIngredient(ItemTags.IRON_TOOL_MATERIALS),
                        Items.DIAMOND_PICKAXE, ofTagIngredient(ItemTags.DIAMOND_TOOL_MATERIALS),
                        Items.NETHERITE_PICKAXE, ofTagIngredient(ItemTags.NETHERITE_TOOL_MATERIALS)
                )
        );

        overrideVanillaShovels(
                Map.of(
                        Items.WOODEN_SHOVEL, ofTagIngredient(ItemTags.WOODEN_TOOL_MATERIALS),
                        Items.COPPER_SHOVEL, ofTagIngredient(ItemTags.COPPER_TOOL_MATERIALS),
                        Items.STONE_SHOVEL, ofTagIngredient(ItemTags.STONE_TOOL_MATERIALS),
                        Items.GOLDEN_SHOVEL, ofTagIngredient(ItemTags.GOLD_TOOL_MATERIALS),
                        Items.IRON_SHOVEL, ofTagIngredient(ItemTags.IRON_TOOL_MATERIALS),
                        Items.DIAMOND_SHOVEL, ofTagIngredient(ItemTags.DIAMOND_TOOL_MATERIALS),
                        Items.NETHERITE_SHOVEL, ofTagIngredient(ItemTags.NETHERITE_TOOL_MATERIALS)
                )
        );

        overrideVanillaSwords(
                Map.of(
                        Items.WOODEN_SWORD, ofTagIngredient(ItemTags.WOODEN_TOOL_MATERIALS),
                        Items.COPPER_SWORD, ofTagIngredient(ItemTags.COPPER_TOOL_MATERIALS),
                        Items.STONE_SWORD, ofTagIngredient(ItemTags.STONE_TOOL_MATERIALS),
                        Items.GOLDEN_SWORD, ofTagIngredient(ItemTags.GOLD_TOOL_MATERIALS),
                        Items.IRON_SWORD, ofTagIngredient(ItemTags.IRON_TOOL_MATERIALS),
                        Items.DIAMOND_SWORD, ofTagIngredient(ItemTags.DIAMOND_TOOL_MATERIALS),
                        Items.NETHERITE_SWORD, ofTagIngredient(ItemTags.NETHERITE_TOOL_MATERIALS)
                )
        );

        overrideVanillaSpears(
                Map.of(
                        Items.WOODEN_SPEAR, ofTagIngredient(ItemTags.WOODEN_TOOL_MATERIALS),
                        Items.COPPER_SPEAR, ofTagIngredient(ItemTags.COPPER_TOOL_MATERIALS),
                        Items.STONE_SPEAR, ofTagIngredient(ItemTags.STONE_TOOL_MATERIALS),
                        Items.GOLDEN_SPEAR, ofTagIngredient(ItemTags.GOLD_TOOL_MATERIALS),
                        Items.IRON_SPEAR, ofTagIngredient(ItemTags.IRON_TOOL_MATERIALS),
                        Items.DIAMOND_SPEAR, ofTagIngredient(ItemTags.DIAMOND_TOOL_MATERIALS),
                        Items.NETHERITE_SPEAR, ofTagIngredient(ItemTags.NETHERITE_TOOL_MATERIALS)
                )
        );

        overrideVanilla(
                Items.ANVIL,
                RecipeCategory.DECORATIONS,
                Map.of(
                        'I', ofTagIngredient(ModBlockItemTags.IRON_BLOCKS.item()),
                        'i', ofTagIngredient(ConventionalItemTags.IRON_INGOTS)
                ),
                List.of(
                        "III",
                        " i ",
                        "iii"
                )
        );

        overrideVanilla(
                Items.ARROW,
                RecipeCategory.COMBAT,
                Map.of(
                        '#', ofTagIngredient(ConventionalItemTags.WOODEN_RODS),
                        'X', Ingredient.of(Items.FLINT),
                        'Y', Ingredient.of(Items.FEATHER)
                ),
                List.of(
                        "X",
                        "#",
                        "Y"
                ),
                4
        );

        overrideVanilla(
                Items.BLAST_FURNACE,
                RecipeCategory.MISC,
                Map.of(
                        '#', Ingredient.of(Items.SMOOTH_STONE),
                        'I', ofTagIngredient(ConventionalItemTags.IRON_INGOTS),
                        'X', Ingredient.of(Items.FURNACE)
                ),
                List.of(
                        "III",
                        "IXI",
                        "###"
                )
        );

        overrideVanilla(
                Items.BUCKET,
                RecipeCategory.MISC,
                Map.of(
                        '#', ofTagIngredient(ConventionalItemTags.IRON_INGOTS)
                ),
                List.of(
                        "# #",
                        " # "
                )
        );

        overrideVanilla(
                Items.REDSTONE_TORCH,
                RecipeCategory.REDSTONE,
                Map.of(
                        '#', ofTagIngredient(ConventionalItemTags.WOODEN_RODS),
                        'X', Ingredient.of(Items.REDSTONE)
                ),
                List.of(
                        "X",
                        "#"
                )
        );

        overrideVanilla(
                Items.TORCH,
                RecipeCategory.MISC,
                Map.of(
                        '#', ofTagIngredient(ConventionalItemTags.WOODEN_RODS),
                        'X', Ingredient.of(Items.COAL, Items.CHARCOAL)
                ),
                List.of(
                        "X",
                        "#"
                ),
                4
        );

        overrideVanilla(
                Items.SMITHING_TABLE,
                RecipeCategory.MISC,
                Map.of(
                        '@', ofTagIngredient(ConventionalItemTags.IRON_INGOTS),
                        '#', ofTagIngredient(ItemTags.PLANKS)
                ),
                List.of(
                        "@@",
                        "##",
                        "##"
                )
        );
    }

    /**
     * @return an ingredient from a tag.
     */
    protected Ingredient ofTagIngredient(TagKey<Item> key) {
        return Ingredient.of(
                    this.items.getOrThrow(key)
        );
    }

    /**
     * Creates a {@code smithing table} recipe.
     */
    public void offerGoldenSpeedrunnerUpgradeRecipe(Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeBuilder.smithing(
                        Ingredient.of(ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.of(input),
                        this.tag(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS),
                        category,
                        result
                )
                .unlocks("has_speedrunner_ingot", this.has(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS))
                .save(this.output, result+"_smithing");
    }

    /**
     * Creates a smelting, campfire cooking, and smoker recipe.
     */
    protected void createCookableFood(ItemLike input, ItemLike output) {
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, S_asTick(3))
                .unlockedBy("has_item", this.has(input))
                .save(this.output, output+"_from_campfire_cooking");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.FOOD, CookingBookCategory.FOOD, output, 0.35F, S_asTick(10))
                .unlockedBy("has_item", this.has(input))
                .save(this.output, output+"_from_smelting");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, S_asTick(5))
                .unlockedBy("has_item", this.has(input))
                .save(this.output, output+"_from_smoking");
    }

    /**
     * Creates a dead speedrunner smeltable material.
     */
    public void offerSmeltableDeadSpeedrunner(ItemLike input, ItemLike output) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, output, 0.2F, S_asTick(10))
                .unlockedBy("has_item", this.has(input))
                .save(this.output, BuiltInRegistries.ITEM.getKey(output.asItem()).getPath() + "_from_smelting_dead_variant");
    }

    /**
     * Creates a smeltable and blastable ore material.
     */
    public void offerOreMaterial(List<ItemLike> inputs, ItemLike output, float exp, String group) {
        offerNewSmelting(inputs, RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, output, exp, group, "_from_smelting");
        offerNewBlasting(inputs, RecipeCategory.BUILDING_BLOCKS, CookingBookCategory.BLOCKS, output, exp, group, "_from_blasting");
    }

    /**
     * A helper method for creating a new smelting recipe.
     */
    private void offerNewSmelting(List<ItemLike> inputs, RecipeCategory category, CookingBookCategory cookingBookCategory, ItemLike output, float experience, String group, String suffix) {
        offerMultipleSmeltingOptions(SmeltingRecipe::new, inputs, category, cookingBookCategory, output, experience, S_asTick(10), group, suffix);
    }

    /**
     * A helper method for creating a new blasting recipe.
     */
    private void offerNewBlasting(List<ItemLike> inputs, RecipeCategory category, CookingBookCategory cookingBookCategory, ItemLike output, float experience, String group, String suffix) {
        offerMultipleSmeltingOptions(BlastingRecipe::new, inputs, category, cookingBookCategory, output, experience, S_asTick(5), group, suffix);
    }

    /**
     * A helper method for creating a new cooking recipe.
     */
    private <T extends AbstractCookingRecipe> void offerMultipleSmeltingOptions(AbstractCookingRecipe.Factory<T> recipeFactory, List<ItemLike> inputs, RecipeCategory craftingCategory, CookingBookCategory cookingBookCategory, ItemLike output, float experience, int cookingTime, String group, String suffix) {
        for (ItemLike itemConvertible : inputs) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemConvertible), craftingCategory, cookingBookCategory, output, experience, cookingTime, recipeFactory)
                    .group(group)
                    .unlockedBy(getHasName(itemConvertible), this.has(itemConvertible))
                    .save(this.output, output + suffix + "_" + this.removeSpeedrunnerModNamespace(itemConvertible.asItem().toString()));
        }
    }

    /**
     * Creates a reversible compacting recipe, with the correct identifier.
     */
    public void offerModdedReversibleCompactingRecipes(RecipeCategory reverseCategory, ItemLike baseItem, RecipeCategory compactingCategory, ItemLike compactItem, String compactingId, @Nullable String compactingGroup, String reverseId, @Nullable String reverseGroup) {
        this.shapeless(reverseCategory, baseItem, 9)
                .requires(compactItem)
                .group(reverseGroup)
                .unlockedBy(getHasName(compactItem), this.has(compactItem))
                .save(this.output, this.speedrunnerModRecipe(reverseId));
        this.shaped(compactingCategory, compactItem)
                .define('#', baseItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(compactingGroup)
                .unlockedBy(getHasName(baseItem), this.has(baseItem))
                .save(this.output, this.speedrunnerModRecipe(compactingId));
    }

    /**
     * Creates a normal {@code boat} and {@code chest boat} recipe.
     */
    protected void createBoatSet(ItemLike boat, ItemLike chestBoat, ItemLike planks) {
        this.woodenBoat(boat, planks);
        this.chestBoat(chestBoat, boat);
    }

    /**
     * Creates a {@code fence recipe} with speedrunner sticks.
     */
    public void createModdedFenceRecipe(ItemLike output, ItemLike input) {
        int i = output == Blocks.NETHER_BRICK_FENCE ? 6 : 3;
        Item item = output == Blocks.NETHER_BRICK_FENCE ? Items.NETHER_BRICK : ModItems.SPEEDRUNNER_STICK;
        this.shaped(RecipeCategory.DECORATIONS, output, i)
                .define('W', input)
                .define('#', item)
                .group("wooden_fence")
                .pattern("W#W")
                .pattern("W#W")
                .unlockedBy(getHasName(input), this.has(input))
                .save(this.output);
    }

    /**
     * Creates a {@code fence gate recipe} with speedrunner sticks.
     */
    public void createModdedFenceGateRecipe(ItemLike output, ItemLike input) {
        this.shaped(RecipeCategory.REDSTONE, output)
                .define('#', ModItems.SPEEDRUNNER_STICK)
                .define('W', input)
                .group("wooden_fence_gate")
                .pattern("#W#")
                .pattern("#W#")
                .unlockedBy(getHasName(input), this.has(input))
                .save(this.output);
    }

    /**
     * Creates a {@code stick} recipe.
     */
    public void createStickRecipe(boolean dead, String name) {
        this.shaped(RecipeCategory.MISC, ModItems.SPEEDRUNNER_STICK, 4)
                .define('S', dead ? ModBlocks.DEAD_SPEEDRUNNER_PLANKS : ModBlocks.SPEEDRUNNER_PLANKS)
                .group("sticks")
                .pattern("S")
                .pattern("S")
                .group("sticks")
                .unlockedBy("has_planks", this.has(dead ? ModBlocks.DEAD_SPEEDRUNNER_PLANKS : ModBlocks.SPEEDRUNNER_PLANKS))
                .save(this.output, name);
    }

    /**
     * Creates a {@code reverse plank} recipe.
     */
    public void createReversePlankRecipe() {
        this.shaped(RecipeCategory.MISC, ModBlocks.SPEEDRUNNER_PLANKS)
                .define('/', ModItems.SPEEDRUNNER_STICK)
                .group("planks")
                .pattern("//")
                .pattern("//")
                .unlockedBy("has_sticks", this.has(ModItems.SPEEDRUNNER_STICK))
                .save(this.output, this.speedrunnerModRecipe("speedrunner_planks_from_speedrunner_stick"));
    }

    /**
     * Creates a {@code spear} recipe.
     */
    protected void createModdedSpear(TagKey<Item> material, ItemLike output) {
        var recipe = this.shaped(RecipeCategory.COMBAT, output)
                .define('#', ConventionalItemTags.WOODEN_RODS)
                .define('X', material)
                .pattern("  X")
                .pattern(" # ")
                .pattern("#  ")
                .unlockedBy("has_material", this.has(material));

        recipe.save(this.output);
    }

    /**
     * Creates a {@code sword} recipe.
     */
    protected void createModdedSword(TagKey<Item> material, ItemLike output) {
        var recipe = this.shaped(RecipeCategory.COMBAT, output)
                .define('#', ConventionalItemTags.WOODEN_RODS)
                .define('X', material)
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .unlockedBy("has_material", this.has(material));

        recipe.save(this.output);
    }


    /**
     * Creates a {@code shovel} recipe.
     */
    protected void createModdedShovel(TagKey<Item> material, ItemLike output) {
        var recipe = this.shaped(RecipeCategory.TOOLS, output)
                .define('#', ConventionalItemTags.WOODEN_RODS)
                .define('X', material)
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .unlockedBy("has_material", this.has(material));

        recipe.save(this.output);
    }

    /**
     * Creates a {@code pickaxe} recipe.
     */
    protected void createModdedPickaxe(TagKey<Item> material, ItemLike output) {
        var recipe = this.shaped(RecipeCategory.TOOLS, output)
                .define('#', ConventionalItemTags.WOODEN_RODS)
                .define('X', material)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .unlockedBy("has_material", this.has(material));

        recipe.save(this.output);
    }

    /**
     * Creates an {@code axe} recipe.
     */
    protected void createModdedAxe(TagKey<Item> material, ItemLike output) {
        var recipe = this.shaped(RecipeCategory.TOOLS, output)
                .define('#', ConventionalItemTags.WOODEN_RODS)
                .define('X', material)
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .unlockedBy("has_material", this.has(material));

        recipe.save(this.output);
    }

    /**
     * Creates a {@code hoe} recipe.
     */
    protected void createModdedHoe(TagKey<Item> material, ItemLike output) {
        var recipe = this.shaped(RecipeCategory.TOOLS, output)
                .define('#', ConventionalItemTags.WOODEN_RODS)
                .define('X', material)
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .unlockedBy("has_material", this.has(material));

        recipe.save(this.output);
    }

    /**
     * Creates a {@code helmet} recipe.
     */
    protected void createModdedHelmet(ItemLike input, ItemLike output) {
        this.shaped(RecipeCategory.COMBAT, output)
                .define('X', input)
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy("has_item", this.has(input))
                .save(this.output);
    }

    /**
     * Creates a {@code chestplate} recipe.
     */
    protected void createModdedChestplate(ItemLike input, ItemLike output) {
        this.shaped(RecipeCategory.COMBAT, output)
                .define('X', input)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_item", this.has(input))
                .save(this.output);
    }

    /**
     * Creates a {@code leggings} recipe.
     */
    protected void createModdedLeggings(ItemLike input, ItemLike output) {
        this.shaped(RecipeCategory.COMBAT, output)
                .define('X', input)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_item", this.has(input))
                .save(this.output);
    }

    /**
     * Creates a {@code boot} recipe.
     */
    protected void createModdedBoots(ItemLike input, ItemLike output) {
        this.shaped(RecipeCategory.COMBAT, output)
                .define('X', input)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_item", this.has(input))
                .save(this.output);
    }

    /**
     * Overrides a vanilla recipe.
     */
    protected void overrideVanilla(Item result, RecipeCategory category, final Map<Character, Ingredient> key, final List<String> pattern) {
        overrideVanilla(result, category, key, pattern, 1);
    }

    /**
     * Overrides a vanilla recipe with a custom count result.
     */
    protected void overrideVanilla(Item result, RecipeCategory category, final Map<Character, Ingredient> key, final List<String> pattern, int count) {
        this.output.accept(
                ResourceKey.create(
                        Registries.RECIPE,
                        Identifier.withDefaultNamespace(
                                BuiltInRegistries.ITEM.getKey(result.asItem()).getPath()
                        )
                ),
                new ShapedRecipe(
                        RecipeBuilder.createCraftingCommonInfo(true),
                        RecipeBuilder.createCraftingBookInfo(
                                category,
                                null
                        ),
                        ShapedRecipePattern.of(
                                key,
                                pattern
                        ),
                        new ItemStackTemplate(result, count)
                ),
                null
        );
    }

    /**
     * Overrides all {@code vanilla axe} recipes.
     */
    protected void overrideVanillaAxes(Map<Item, Ingredient> axes) {
        overrideVanillaTool(
                axes,
                RecipeCategory.TOOLS,
                List.of(
                        "##",
                        "#S",
                        " S"
                )
        );
    }

    /**
     * Overrides all {@code vanilla hoe} recipes.
     */
    protected void overrideVanillaHoes(Map<Item, Ingredient> hoes) {
        overrideVanillaTool(
                hoes,
                RecipeCategory.TOOLS,
                List.of(
                        "##",
                        " S",
                        " S"
                )
        );
    }

    /**
     * Overrides all {@code vanilla pickaxe} recipes.
     */
    protected void overrideVanillaPickaxes(Map<Item, Ingredient> pickaxes) {
        overrideVanillaTool(
                pickaxes,
                RecipeCategory.TOOLS,
                List.of(
                        "###",
                        " S ",
                        " S "
                )
        );
    }

    /**
     * Overrides all {@code vanilla shovel} recipes.
     */
    protected void overrideVanillaShovels(Map<Item, Ingredient> shovels) {
        overrideVanillaTool(
                shovels,
                RecipeCategory.TOOLS,
                List.of(
                        "#",
                        "S",
                        "S"
                )
        );
    }

    /**
     * Overrides all {@code vanilla sword} recipes.
     */
    protected void overrideVanillaSwords(Map<Item, Ingredient> swords) {
        overrideVanillaTool(
                swords,
                RecipeCategory.COMBAT,
                List.of(
                        "#",
                        "#",
                        "S"
                )
        );
    }

    /**
     * Overrides all {@code vanilla spear} recipes.
     */
    protected void overrideVanillaSpears(Map<Item, Ingredient> spears) {
        overrideVanillaTool(
                spears,
                RecipeCategory.COMBAT,
                List.of(
                        "  #",
                        " S ",
                        "S  "
                )
        );
    }

    /**
     * Overrides a tool recipe.
     */
    protected void overrideVanillaTool(Map<Item, Ingredient> tools, RecipeCategory category, final List<String> pattern) {
        for (Map.Entry<Item, Ingredient> entry : tools.entrySet()) {
            Item item = entry.getKey();
            Ingredient ingredient = entry.getValue();

            overrideVanilla(
                    item,
                    category,
                    Map.of(
                            '#', ingredient,
                            'S', ofTagIngredient(ConventionalItemTags.WOODEN_RODS)
                    ),
                    pattern
            );
        }
    }

    /**
     * Returns a speedrunner mod recipe identifier.
     */
    protected ResourceKey<Recipe<?>> speedrunnerModRecipe(String path) {
        return ResourceKey.create(Registries.RECIPE, ofSpeedrunnerMod(path));
    }

    /**
     * Removes a specific set of characters (for an identifier) when returning a string.
     */
    @Deprecated
    protected String removeSpeedrunnerModNamespace(String str) {
        return str.substring(15);
    }
}