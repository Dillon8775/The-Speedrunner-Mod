package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.block.ModBlocks;
import net.dillon.speedrunnermod.item.ModItems;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.recipe.CookingRecipeJsonBuilder;
import net.minecraft.data.recipe.RecipeExporter;
import net.minecraft.data.recipe.RecipeGenerator;
import net.minecraft.data.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A helper class to easily create recipes.
 */
public class RecipeGeneratorHelper extends RecipeGenerator {

    protected RecipeGeneratorHelper(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
    }

    /**
     * Creates a smelting, campfire cooking, and smoker recipe.
     */
    protected void createCookableFood(ItemConvertible input, ItemConvertible output) {
        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItem(input), RecipeCategory.FOOD, output, 0.35F, 60)
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter, output+"_from_campfire_cooking");
        CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItem(input), RecipeCategory.FOOD, output, 0.35F, 200)
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter, output+"_from_smelting");
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItem(input), RecipeCategory.FOOD, output, 0.35F, 200)
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter, output+"_from_smoking");
    }

    /**
     * Creates a {@code smithing table} recipe.
     */
    public void offerGoldenSpeedrunnerUpgradeRecipe(Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItem(ModItems.GOLDEN_UPGRADE_SMITHING_TEMPLATE),
                        Ingredient.ofItem(input),
                        this.ingredientFromTag(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS),
                        category,
                        result
                )
                .criterion("has_speedrunner_ingot", this.conditionsFromTag(ModItemTags.SPEEDRUNNER_TOOL_MATERIALS))
                .offerTo(this.exporter, result+"_smithing");
    }

    /**
     * Creates a reversible compacting recipe, with the correct identifier.
     */
    public void offerModdedReversibleCompactingRecipes(RecipeCategory reverseCategory, ItemConvertible baseItem, RecipeCategory compactingCategory, ItemConvertible compactItem, String compactingId, @Nullable String compactingGroup, String reverseId, @Nullable String reverseGroup) {
        this.createShapeless(reverseCategory, baseItem, 9)
                .input(compactItem)
                .group(reverseGroup)
                .criterion(hasItem(compactItem), this.conditionsFromItem(compactItem))
                .offerTo(this.exporter, this.speedrunnerModRecipe(reverseId));
        this.createShaped(compactingCategory, compactItem)
                .input('#', baseItem)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .group(compactingGroup)
                .criterion(hasItem(baseItem), this.conditionsFromItem(baseItem))
                .offerTo(this.exporter, this.speedrunnerModRecipe(compactingId));
    }

    /**
     * Creates a smeltable and blastable material.
     */
    public void offerBurnableMaterial(List<ItemConvertible> inputs, ItemConvertible output, float exp, String group) {
        offerNewSmelting(inputs, RecipeCategory.MISC, output, exp, group);
        offerNewBlasting(inputs, RecipeCategory.MISC, output, exp, group);
    }

    /**
     * A helper method for creating a new smelting recipe.
     */
    protected void offerNewSmelting(List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, String group) {
        this.offerMultipleOptionsH(RecipeSerializer.SMELTING, SmeltingRecipe::new, inputs, category, output, experience, 20, group, "_from_smelting");
    }

    /**
     * A helper method for creating a new blasting recipe.
     */
    protected void offerNewBlasting(List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, String group) {
        this.offerMultipleOptionsH(RecipeSerializer.BLASTING, BlastingRecipe::new, inputs, category, output, experience, 20, group, "_from_blasting");
    }

    /**
     * A helper method for creating a new cooking recipe.
     */
    protected final <T extends AbstractCookingRecipe> void offerMultipleOptionsH(RecipeSerializer<T> serializer, AbstractCookingRecipe.RecipeFactory<T> recipeFactory, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group, String suffix) {
        for (ItemConvertible itemConvertible : inputs) {
            CookingRecipeJsonBuilder.create(Ingredient.ofItem(itemConvertible), category, output, experience, cookingTime, serializer, recipeFactory)
                    .group(group)
                    .criterion(hasItem(itemConvertible), this.conditionsFromItem(itemConvertible))
                    .offerTo(this.exporter, output + suffix + "_" + this.removeSpeedrunnerModNamespace(itemConvertible.asItem().toString()));
        }
    }

    /**
     * Creates a normal {@code boat} and {@code chest boat} recipe.
     */
    protected void createBoatSet(ItemConvertible boat, ItemConvertible chestBoat, ItemConvertible planks) {
        this.offerFireproofBoatRecipe(boat, planks);
        this.offerChestBoatRecipe(chestBoat, boat);
    }

    /**
     * Creates a fireproof {@code boat} and {@code chest boat} recipe.
     */
    protected void createFireproofBoatSet(ItemConvertible boat, ItemConvertible chestBoat, ItemConvertible fireproofBoat, ItemConvertible fireproofChestBoat, ItemConvertible planks, String s) {
        this.offerBoatRecipe(boat, planks);
        this.offerFireproofBoatRecipe(fireproofBoat, planks);
        this.offerPaddleFireproofBoatRecipe(fireproofBoat, boat, fireproofChestBoat, chestBoat, s);
        this.offerChestBoatRecipe(chestBoat, boat);
        this.offerChestBoatRecipe(fireproofChestBoat, fireproofBoat);
    }

    public void offerFireproofBoatRecipe(ItemConvertible output, ItemConvertible input) {
        this.createShaped(RecipeCategory.TRANSPORTATION, output)
                .input('#', input)
                .input('P', ModItems.SPEEDRUNNER_PADDLE)
                .pattern("#P#")
                .pattern("###")
                .group("boat")
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    public void offerPaddleFireproofBoatRecipe(ItemConvertible fireproofBoat, ItemConvertible boat, ItemConvertible fireproofChestBoat, ItemConvertible chestBoat, String s) {
        this.createShapeless(RecipeCategory.TRANSPORTATION, fireproofBoat)
                .input(ModItems.SPEEDRUNNER_PADDLE)
                .input(boat)
                .group("boat")
                .criterion("has_boat", this.conditionsFromTag(ItemTags.BOATS))
                .offerTo(this.exporter, this.speedrunnerModRecipe(s + "_with_paddle"));
        this.createShapeless(RecipeCategory.TRANSPORTATION, fireproofChestBoat)
                .input(ModItems.SPEEDRUNNER_PADDLE)
                .input(chestBoat)
                .group("chest_boat")
                .criterion("has_boat", this.conditionsFromTag(ItemTags.BOATS))
                .offerTo(this.exporter, this.speedrunnerModRecipe(s + "_with_chest_paddle"));
    }

    /**
     * Creates a {@code sign} recipe.
     */
    protected void createSign(ItemConvertible sign, ItemConvertible plank) {
        this.createShaped(RecipeCategory.DECORATIONS, sign, 3)
                .input('#', plank)
                .input('X', ModItemTags.STICKS)
                .group("wooden_sign")
                .pattern("###")
                .pattern("###")
                .pattern(" X ")
                .criterion("has_plank", this.conditionsFromItem(plank))
                .offerTo(this.exporter);
    }

    /**
     * Creates a {@code fence recipe} with speedrunner sticks.
     */
    public void createModdedFenceRecipe(ItemConvertible output, ItemConvertible input, boolean dead) {
        int i = output == Blocks.NETHER_BRICK_FENCE ? 6 : 3;
        Item item = output == Blocks.NETHER_BRICK_FENCE ? Items.NETHER_BRICK : dead ? ModItems.DEAD_SPEEDRUNNER_STICK : ModItems.SPEEDRUNNER_STICK;
        this.createShaped(RecipeCategory.DECORATIONS, output, i)
                .input('W', input)
                .input('#', item)
                .group("wooden_fence")
                .pattern("W#W")
                .pattern("W#W")
                .criterion(hasItem(input), this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    /**
     * Creates a {@code fence gate recipe} with speedrunner sticks.
     */
    public void createModdedFenceGateRecipe(ItemConvertible output, ItemConvertible input, boolean dead) {
        this.createShaped(RecipeCategory.REDSTONE, output)
                .input('#', dead ? ModItems.DEAD_SPEEDRUNNER_STICK : ModItems.SPEEDRUNNER_STICK)
                .input('W', input)
                .group("wooden_fence_gate")
                .pattern("#W#")
                .pattern("#W#")
                .criterion(hasItem(input), this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    /**
     * Creates a {@code stick} recipe.
     */
    public void createStickRecipe(boolean dead) {
        this.createShaped(RecipeCategory.MISC, dead ? ModItems.DEAD_SPEEDRUNNER_STICK : ModItems.SPEEDRUNNER_STICK, 4)
                .input('S', dead ? ModBlocks.DEAD_SPEEDRUNNER_PLANKS : ModBlocks.SPEEDRUNNER_PLANKS)
                .group("sticks")
                .pattern("S")
                .pattern("S")
                .group("sticks")
                .criterion("has_planks", this.conditionsFromItem(dead ? ModBlocks.DEAD_SPEEDRUNNER_PLANKS : ModBlocks.SPEEDRUNNER_PLANKS))
                .offerTo(this.exporter);
    }

    /**
     * Creates a {@code reverse plank} recipe.
     */
    public void createReversePlankRecipe(boolean dead) {
        this.createShaped(RecipeCategory.MISC, dead ? ModBlocks.DEAD_SPEEDRUNNER_PLANKS : ModBlocks.SPEEDRUNNER_PLANKS)
                .input('/', dead ? ModItems.DEAD_SPEEDRUNNER_STICK : ModItems.SPEEDRUNNER_STICK)
                .group("planks")
                .pattern("//")
                .pattern("//")
                .criterion("has_sticks", this.conditionsFromItem(dead ? ModItems.DEAD_SPEEDRUNNER_STICK : ModItems.SPEEDRUNNER_STICK))
                .offerTo(this.exporter, this.speedrunnerModRecipe(dead ? "dead_speedrunner_planks_from_dead_speedrunner_stick" : "speedrunner_planks_from_speedrunner_stick"));
    }

    /**
     * Creates a {@code sword} recipe.
     */
    protected void createSword(TagKey<Item> material, ItemConvertible output) {
        var recipe = this.createShaped(RecipeCategory.COMBAT, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .criterion("has_material", this.conditionsFromTag(material));

        recipe.offerTo(this.exporter);
    }


    /**
     * Creates a {@code shovel} recipe.
     */
    protected void createShovel(TagKey<Item> material, ItemConvertible output) {
        var recipe = this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .criterion("has_material", this.conditionsFromTag(material));

        recipe.offerTo(this.exporter);
    }

    /**
     * Creates a {@code pickaxe} recipe.
     */
    protected void createPickaxe(TagKey<Item> material, ItemConvertible output) {
        var recipe = this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .criterion("has_material", this.conditionsFromTag(material));

        recipe.offerTo(this.exporter);
    }

    /**
     * Creates an {@code axe} recipe.
     */
    protected void createAxe(TagKey<Item> material, ItemConvertible output) {
        var recipe = this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .criterion("has_material", this.conditionsFromTag(material));

        recipe.offerTo(this.exporter);
    }

    /**
     * Creates a {@code hoe} recipe.
     */
    protected void createHoe(TagKey<Item> material, ItemConvertible output) {
        var recipe = this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .criterion("has_material", this.conditionsFromTag(material));

        recipe.offerTo(this.exporter);
    }

    /**
     * Creates a {@code helmet} recipe.
     */
    protected void createHelmet(ItemConvertible input, ItemConvertible output) {
        this.createShaped(RecipeCategory.COMBAT, output)
                .input('X', input)
                .pattern("XXX")
                .pattern("X X")
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    /**
     * Creates a {@code chestplate} recipe.
     */
    protected void createChestplate(ItemConvertible input, ItemConvertible output) {
        this.createShaped(RecipeCategory.COMBAT, output)
                .input('X', input)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    /**
     * Creates a {@code leggings} recipe.
     */
    protected void createLeggings(ItemConvertible input, ItemConvertible output) {
        this.createShaped(RecipeCategory.COMBAT, output)
                .input('X', input)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    /**
     * Creates a {@code boot} recipe.
     */
    protected void createBoots(ItemConvertible input, ItemConvertible output) {
        this.createShaped(RecipeCategory.COMBAT, output)
                .input('X', input)
                .pattern("X X")
                .pattern("X X")
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter);
    }

    /**
     * Returns a speedrunner mod recipe identifier.
     */
    protected RegistryKey<Recipe<?>> speedrunnerModRecipe(String path) {
        return RegistryKey.of(RegistryKeys.RECIPE, ofSpeedrunnerMod(path));
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
    public void offerBannerRecipe(ItemConvertible output, ItemConvertible inputWool) {
        this.createShaped(RecipeCategory.DECORATIONS, output)
                .input('#', inputWool)
                .input('|', ModItemTags.STICKS)
                .pattern("###")
                .pattern("###")
                .pattern(" | ")
                .group("banner")
                .criterion(hasItem(inputWool), this.conditionsFromItem(inputWool))
                .offerTo(this.exporter);
    }

    /**
     * This method does absolutely nothing. Just needed to create a functional helper class.
     */
    @Override
    public void generate() {
    }
}