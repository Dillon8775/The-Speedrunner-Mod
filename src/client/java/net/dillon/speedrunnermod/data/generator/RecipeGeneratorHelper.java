package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.item.core.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.advancements.Advancement;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A helper class to easily create recipes.
 */
public class RecipeGeneratorHelper extends RecipeProvider {

    protected RecipeGeneratorHelper(BootstrapContext<Recipe<?>> recipeOutput, BootstrapContext<Advancement> advancementOutput) {
        super(recipeOutput, advancementOutput);
    }

    /**
     * Creates a smelting, campfire cooking, and smoker recipe.
     */
    protected void createCookableFood(ItemLike input, ItemLike output) {
        SimpleCookingRecipeBuilder.campfireCooking(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, 60)
                .unlockedBy("has_item", this.has(input))
                .save(this.output, output+"_from_campfire_cooking");
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), RecipeCategory.FOOD, CookingBookCategory.FOOD, output, 0.35F, 200)
                .unlockedBy("has_item", this.has(input))
                .save(this.output, output+"_from_smelting");
        SimpleCookingRecipeBuilder.smoking(Ingredient.of(input), RecipeCategory.FOOD, output, 0.35F, 200)
                .unlockedBy("has_item", this.has(input))
                .save(this.output, output+"_from_smoking");
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
     * Creates a smeltable and blastable material.
     */
    public void offerBurnableMaterial(List<ItemLike> inputs, ItemLike output, float exp, String group) {
        offerNewSmelting(inputs, RecipeCategory.MISC, CookingBookCategory.MISC, output, exp, group);
        offerNewBlasting(inputs, RecipeCategory.MISC, CookingBookCategory.MISC, output, exp, group);
    }

    /**
     * A helper method for creating a new smelting recipe.
     */
    protected void offerNewSmelting(List<ItemLike> inputs, RecipeCategory category, CookingBookCategory cookingBookCategory, ItemLike output, float experience, String group) {
        this.offerMultipleOptionsH(SmeltingRecipe::new, inputs, category, cookingBookCategory, output, experience, 200, group, "_from_smelting");
    }

    /**
     * A helper method for creating a new blasting recipe.
     */
    protected void offerNewBlasting(List<ItemLike> inputs, RecipeCategory category, CookingBookCategory cookingBookCategory, ItemLike output, float experience, String group) {
        this.offerMultipleOptionsH(BlastingRecipe::new, inputs, category, cookingBookCategory, output, experience, 100, group, "_from_blasting");
    }

    /**
     * A helper method for creating a new cooking recipe.
     */
    protected final <T extends AbstractCookingRecipe> void offerMultipleOptionsH(AbstractCookingRecipe.Factory<T> recipeFactory, List<ItemLike> inputs, RecipeCategory craftingCategory, CookingBookCategory cookingBookCategory, ItemLike output, float experience, int cookingTime, String group, String suffix) {
        for (ItemLike itemConvertible : inputs) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemConvertible), craftingCategory, cookingBookCategory, output, experience, cookingTime, recipeFactory)
                    .group(group)
                    .unlockedBy(getHasName(itemConvertible), this.has(itemConvertible))
                    .save(this.output, output + suffix + "_" + this.removeSpeedrunnerModNamespace(itemConvertible.asItem().toString()));
        }
    }

    /**
     * Creates a normal {@code boat} and {@code chest boat} recipe.
     */
    protected void createBoatSet(ItemLike boat, ItemLike chestBoat, ItemLike planks) {
        this.offerFireproofBoatRecipe(boat, planks);
        this.chestBoat(chestBoat, boat);
    }

    /**
     * Creates a fireproof {@code boat} and {@code chest boat} recipe.
     */
    protected void createFireproofBoatSet(ItemLike boat, ItemLike chestBoat, ItemLike fireproofBoat, ItemLike fireproofChestBoat, ItemLike planks, String s) {
        this.woodenBoat(boat, planks);
        this.offerFireproofBoatRecipe(fireproofBoat, planks);
        this.offerPaddleFireproofBoatRecipe(fireproofBoat, boat, fireproofChestBoat, chestBoat, s);
        this.chestBoat(chestBoat, boat);
        this.chestBoat(fireproofChestBoat, fireproofBoat);
    }

    public void offerFireproofBoatRecipe(ItemLike output, ItemLike input) {
        this.shaped(RecipeCategory.TRANSPORTATION, output)
                .define('#', input)
                .define('P', ModItems.SPEEDRUNNER_PADDLE)
                .pattern("#P#")
                .pattern("###")
                .group("boat")
                .unlockedBy("has_item", this.has(input))
                .save(this.output);
    }

    public void offerPaddleFireproofBoatRecipe(ItemLike fireproofBoat, ItemLike boat, ItemLike fireproofChestBoat, ItemLike chestBoat, String s) {
        this.shapeless(RecipeCategory.TRANSPORTATION, fireproofBoat)
                .requires(ModItems.SPEEDRUNNER_PADDLE)
                .requires(boat)
                .group("boat")
                .unlockedBy("has_boat", this.has(ItemTags.BOATS))
                .save(this.output, this.speedrunnerModRecipe(s + "_with_paddle"));
        this.shapeless(RecipeCategory.TRANSPORTATION, fireproofChestBoat)
                .requires(ModItems.SPEEDRUNNER_PADDLE)
                .requires(chestBoat)
                .group("chest_boat")
                .unlockedBy("has_boat", this.has(ItemTags.BOATS))
                .save(this.output, this.speedrunnerModRecipe(s + "_with_chest_paddle"));
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
    protected void createSpear(TagKey<Item> material, ItemLike output) {
        var recipe = this.shaped(RecipeCategory.COMBAT, output)
                .define('#', ModItemTags.STICKS)
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
    protected void createSword(TagKey<Item> material, ItemLike output) {
        var recipe = this.shaped(RecipeCategory.COMBAT, output)
                .define('#', ModItemTags.STICKS)
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
    protected void createShovel(TagKey<Item> material, ItemLike output) {
        var recipe = this.shaped(RecipeCategory.TOOLS, output)
                .define('#', ModItemTags.STICKS)
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
    protected void createPickaxe(TagKey<Item> material, ItemLike output) {
        var recipe = this.shaped(RecipeCategory.TOOLS, output)
                .define('#', ModItemTags.STICKS)
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
    protected void createAxe(TagKey<Item> material, ItemLike output) {
        var recipe = this.shaped(RecipeCategory.TOOLS, output)
                .define('#', ModItemTags.STICKS)
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
    protected void createHoe(TagKey<Item> material, ItemLike output) {
        var recipe = this.shaped(RecipeCategory.TOOLS, output)
                .define('#', ModItemTags.STICKS)
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
    protected void createHelmet(ItemLike input, ItemLike output) {
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
    protected void createChestplate(ItemLike input, ItemLike output) {
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
    protected void createLeggings(ItemLike input, ItemLike output) {
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
    protected void createBoots(ItemLike input, ItemLike output) {
        this.shaped(RecipeCategory.COMBAT, output)
                .define('X', input)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy("has_item", this.has(input))
                .save(this.output);
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
    protected String removeSpeedrunnerModNamespace(String str) {
        return str.substring(15);
    }

    /**
     * Creates a banner recipe with the {@code Speedrunner Mod sticks tag.}
     */
    @Override
    public void banner(ItemLike output, ItemLike inputWool) {
        this.shaped(RecipeCategory.DECORATIONS, output)
                .define('#', inputWool)
                .define('|', ModItemTags.STICKS)
                .pattern("###")
                .pattern("###")
                .pattern(" | ")
                .group("banner")
                .unlockedBy(getHasName(inputWool), this.has(inputWool))
                .save(this.output);
    }

    /**
     * This method does absolutely nothing. Just needed to create a functional helper class.
     */
    @Override
    public void buildRecipes() {
    }
}