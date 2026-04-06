package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.crafting.CustomRecipe;
import net.minecraft.world.item.crafting.RecipeSerializer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code custom recipes.}
 */
public class ModRecipes {
    protected static final int CENTER_SLOT_3x3 = 4;

    public static final RecipeSerializer<InventoryPreserverRecipe> INVENTORY_PRESERVER_RECIPE_SERIALIZER =
            Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_inventory_preserver"), new CustomRecipe.Serializer<>(InventoryPreserverRecipe::new));

    protected static final RecipeSerializer<DragonFireballRecipe> DRAGON_FIREBALL_RECIPE_RECIPE_SERIALIZER =
            Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_dragon_fireball"), new CustomRecipe.Serializer<>(DragonFireballRecipe::new));
    protected static final RecipeSerializer<PiglinAwakenerRecipe> PIGLIN_AWAKENER_RECIPE_SERIALIZER =
            Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_piglin_awakener"), new CustomRecipe.Serializer<>(PiglinAwakenerRecipe::new));

    protected static final RecipeSerializer<SpeedrunnerShieldDecorationRecipe> SPEEDRUNNER_SHIELD_DECORATION_RECIPE =
            Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_speedrunner_shield_decoration"), new CustomRecipe.Serializer<>(SpeedrunnerShieldDecorationRecipe::new));
    protected static final RecipeSerializer<GoldenShieldDecorationRecipe> GOLDEN_SHIELD_DECORATION_RECIPE =
            Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_golden_shield_decoration"), new CustomRecipe.Serializer<>(GoldenShieldDecorationRecipe::new));

    /**
     * Initializes all Speedrunner Mod {@code custom recipes.}
     */
    public static void initializeCustomRecipes() {
        SpeedrunnerMod.debug("Initialized custom recipes.");
    }
}