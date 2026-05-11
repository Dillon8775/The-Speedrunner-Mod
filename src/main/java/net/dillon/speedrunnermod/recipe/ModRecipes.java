package net.dillon.speedrunnermod.recipe;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code custom recipes.}
 */
public class ModRecipes {
    protected static final int CENTER_SLOT_3x3 = 4;
    /**
     * Registers all Speedrunner Mod {@code custom recipes.}
     */
    public static void registerModSerializers() {
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_inventory_preserver"), InventoryPreserverRecipe.SERIALIZER);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_dragon_fireball"), DragonFireballRecipe.SERIALIZER);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_piglin_awakener"), PiglinAwakenerRecipe.SERIALIZER);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_speedrunner_shield_decoration"), SpeedrunnerShieldDecorationRecipe.SERIALIZER);
        Registry.register(BuiltInRegistries.RECIPE_SERIALIZER, ofSpeedrunnerMod("crafting_golden_shield_decoration"), GoldenShieldDecorationRecipe.SERIALIZER);

        SpeedrunnerMod.debug("Initialized custom recipes.");
    }
}