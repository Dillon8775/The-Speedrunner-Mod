package net.dillon.speedrunnermod.data.generator;

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
import net.minecraft.registry.RegistryEntryLookup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A helper class to easily create recipes.
 */
public class RecipeGeneratorHelper extends RecipeGenerator {
    private final RegistryEntryLookup<Item> itemLookup;

    protected RecipeGeneratorHelper(RegistryWrapper.WrapperLookup registries, RecipeExporter exporter) {
        super(registries, exporter);
        this.itemLookup = registries.getOrThrow(RegistryKeys.ITEM);
    }

    /**
     * Creates a smelting, campfire cooking, and smoker recipe.
     */
    protected void createCookableFood(ItemConvertible input, ItemConvertible output, boolean vanilla) {
        CookingRecipeJsonBuilder defaultFood = CookingRecipeJsonBuilder.createSmelting(Ingredient.ofItem(input), RecipeCategory.FOOD, output, 0.35F, 20)
                .criterion("has_item", this.conditionsFromItem(input));

        if (vanilla) {
            defaultFood.offerTo(this.exporter, vanilla(getItemPath(output)));
        } else {
            defaultFood.offerTo(this.exporter);
        }

        CookingRecipeJsonBuilder.createCampfireCooking(Ingredient.ofItem(input), RecipeCategory.FOOD, output, 0.35F, 60)
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter, vanilla ? vanilla(getItemPath(output)+"_from_campfire_cooking") : output+"_from_campfire_cooking");
        CookingRecipeJsonBuilder.createSmoking(Ingredient.ofItem(input), RecipeCategory.FOOD, output, 0.35F, 20)
                .criterion("has_item", this.conditionsFromItem(input))
                .offerTo(this.exporter, vanilla ? vanilla(getItemPath(output)+"_from_smoking") : output+"_from_smoking");
    }

    /**
     * Creates a {@code smithing table} recipe.
     */
    public void offerGoldenSpeedrunnerUpgradeRecipe(Item input, RecipeCategory category, Item result) {
        SmithingTransformRecipeJsonBuilder.create(
                        Ingredient.ofItem(ModItems.GOLDEN_SPEEDRUNNER_UPGRADE_SMITHING_TEMPLATE),
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
    public void offerBurnableMaterial(List<ItemConvertible> inputs, ItemConvertible output, float exp, String group, boolean vanilla) {
        offerNewSmelting(inputs, RecipeCategory.MISC, output, exp, 20, group, vanilla);
        offerNewBlasting(inputs, RecipeCategory.MISC, output, exp, 20, group, vanilla);
    }

    /**
     * A helper method for creating a new smelting recipe.
     */
    protected void offerNewSmelting(List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group, boolean vanilla) {
        this.offerMultipleOptionsH(RecipeSerializer.SMELTING, SmeltingRecipe::new, inputs, category, output, experience, cookingTime, group, "_from_smelting", vanilla);
    }

    /**
     * A helper method for creating a new blasting recipe.
     */
    protected void offerNewBlasting(List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group, boolean vanilla) {
        this.offerMultipleOptionsH(RecipeSerializer.BLASTING, BlastingRecipe::new, inputs, category, output, experience, cookingTime, group, "_from_blasting", vanilla);
    }

    /**
     * A helper method for creating a new cooking recipe.
     */
    protected final <T extends AbstractCookingRecipe> void offerMultipleOptionsH(RecipeSerializer<T> serializer, AbstractCookingRecipe.RecipeFactory<T> recipeFactory, List<ItemConvertible> inputs, RecipeCategory category, ItemConvertible output, float experience, int cookingTime, String group, String suffix, boolean vanilla) {
        for (ItemConvertible itemConvertible : inputs) {
            CookingRecipeJsonBuilder.create(Ingredient.ofItem(itemConvertible), category, output, experience, cookingTime, serializer, recipeFactory)
                    .group(group)
                    .criterion(hasItem(itemConvertible), this.conditionsFromItem(itemConvertible))
                    .offerTo(this.exporter, vanilla ? vanilla(getItemPath(output) + suffix + "_" + getItemPath(itemConvertible)) : output + suffix + "_" + this.removeSpeedrunnerModNamespace(itemConvertible.asItem().toString()));
        }
    }

    /**
     * Creates a {@code boat} and {@code chest boat} recipe.
     */
    protected void createBoatSet(ItemConvertible boat, ItemConvertible chestBoat, ItemConvertible planks) {
        this.offerBoatRecipe(boat, planks);
        this.offerChestBoatRecipe(chestBoat, boat);
    }

    /**
     * Creates a {@code sign} recipe.
     */
    protected void createSign(ItemConvertible sign, ItemConvertible plank) {
        this.createShaped(RecipeCategory.DECORATIONS, sign, 3)
                .input('#', plank)
                .input('X', ModItemTags.STICKS)
                .pattern("###")
                .pattern(" X ")
                .criterion("has_plank", this.conditionsFromItem(plank))
                .offerTo(this.exporter);
    }

    /**
     * Creates a {@code fence recipe} with speedrunner sticks.
     */
    public void createModdedFenceRecipe(ItemConvertible output, ItemConvertible input) {
        int i = output == Blocks.NETHER_BRICK_FENCE ? 6 : 3;
        Item item = output == Blocks.NETHER_BRICK_FENCE ? Items.NETHER_BRICK : ModItems.SPEEDRUNNER_STICK;
        this.createShaped(RecipeCategory.DECORATIONS, output, i)
                .input('W', input)
                .input('#', item)
                .pattern("W#W")
                .pattern("W#W")
                .criterion(hasItem(input), this.conditionsFromItem(input));
    }

    /**
     * Creates a {@code fence gate recipe} with speedrunner sticks.
     */
    public void createModdedFenceGateRecipe(ItemConvertible output, ItemConvertible input) {
        this.createShaped(RecipeCategory.REDSTONE, output)
                .input('#', ModItems.SPEEDRUNNER_STICK)
                .input('W', input)
                .pattern("#W#")
                .pattern("#W#")
                .criterion(hasItem(input), this.conditionsFromItem(input));
    }

    /**
     * Creates a {@code sword} recipe.
     */
    protected void createSword(TagKey<Item> material, ItemConvertible output, boolean vanilla) {
        var recipe = this.createShaped(RecipeCategory.COMBAT, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .criterion("has_material", this.conditionsFromTag(material));

        if (vanilla) {
            recipe.offerTo(this.exporter, vanilla(getItemPath(output)));
        } else {
            recipe.offerTo(this.exporter);
        }
    }


    /**
     * Creates a {@code shovel} recipe.
     */
    protected void createShovel(TagKey<Item> material, ItemConvertible output, boolean vanilla) {
        var recipe = this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .criterion("has_material", this.conditionsFromTag(material));

        if (vanilla) {
            recipe.offerTo(this.exporter, vanilla(getItemPath(output)));
        } else {
            recipe.offerTo(this.exporter);
        }
    }

    /**
     * Creates a {@code pickaxe} recipe.
     */
    protected void createPickaxe(TagKey<Item> material, ItemConvertible output, boolean vanilla) {
        var recipe = this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .criterion("has_material", this.conditionsFromTag(material));

        if (vanilla) {
            recipe.offerTo(this.exporter, vanilla(getItemPath(output)));
        } else {
            recipe.offerTo(this.exporter);
        }
    }

    /**
     * Creates an {@code axe} recipe.
     */
    protected void createAxe(TagKey<Item> material, ItemConvertible output, boolean vanilla) {
        var recipe = this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .criterion("has_material", this.conditionsFromTag(material));

        if (vanilla) {
            recipe.offerTo(this.exporter, vanilla(getItemPath(output)));
        } else {
            recipe.offerTo(this.exporter);
        }
    }

    /**
     * Creates a {@code hoe} recipe.
     */
    protected void createHoe(TagKey<Item> material, ItemConvertible output, boolean vanilla) {
        var recipe = this.createShaped(RecipeCategory.TOOLS, output)
                .input('#', ModItemTags.STICKS)
                .input('X', material)
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .criterion("has_material", this.conditionsFromTag(material));

        if (vanilla) {
            recipe.offerTo(this.exporter, vanilla(getItemPath(output)));
        } else {
            recipe.offerTo(this.exporter);
        }
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
     * Returns an overwritten vanilla recipe, with the speedrunner mod identifier.
     */
    protected RegistryKey<Recipe<?>> speedrunnerModOfVanillaRecipe(String path) {
        return RegistryKey.of(RegistryKeys.RECIPE, ofSpeedrunnerMod(vanilla(path)));
    }

    /**
     * Removes the {@code "minecraft:"} when returning a string.
     */
    protected String removeMinecraftNamespace(String str) {
        return str.substring(10);
    }

    /**
     * Removes a specific set of characters (for an identifier) when returning a string.
     */
    protected String removeSpeedrunnerModNamespace(String str) {
        return str.substring(15);
    }

    /**
     * Returns the path for a recipe, in the {@code vanilla} folder.
     */
    protected String vanilla(String name) {
        return "vanilla/"+name;
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
                .offerTo(this.exporter, vanilla(this.removeMinecraftNamespace(output.toString())));
    }

    /**
     * This method does absolutely nothing. Just needed to create a functional helper class.
     */
    @Override
    public void generate() {
    }
}