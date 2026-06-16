package net.dillon.speedrunnermod.mixin.item;

import net.dillon.speedrunnermod.item.ModFoodComponents;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Modifies {@code vanilla food items} to be better.
 * <p>See {@link ModFoodComponents} for more.</p>
 */
@Mixin(Foods.class)
public class FoodsMixin {
    @Shadow
    public static final FoodProperties APPLE, BAKED_POTATO, BEEF, BEETROOT, BREAD, CARROT, CHICKEN , CHORUS_FRUIT, COD, COOKED_BEEF, COOKED_CHICKEN, COOKED_COD, COOKED_MUTTON, COOKED_PORKCHOP, COOKED_RABBIT, COOKED_SALMON, COOKIE, DRIED_KELP, ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE, GOLDEN_CARROT, HONEY_BOTTLE, MELON_SLICE, MUTTON, POISONOUS_POTATO, PORKCHOP, POTATO, PUFFERFISH, PUMPKIN_PIE, RABBIT, ROTTEN_FLESH, SALMON, SPIDER_EYE, SWEET_BERRIES, GLOW_BERRIES, TROPICAL_FISH;

    static {
        APPLE = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.APPLE : Foods.APPLE;
        BAKED_POTATO = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.BAKED_POTATO : Foods.BAKED_POTATO;
        BEEF = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.BEEF : Foods.BEEF;
        BEETROOT = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.BEETROOT : Foods.BEETROOT;
        BREAD = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.BREAD : Foods.BREAD;
        CARROT = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.CARROT : Foods.CARROT;
        CHICKEN = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.CHICKEN : Foods.CHICKEN;
        CHORUS_FRUIT = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.CHORUS_FRUIT : Foods.CHORUS_FRUIT;
        COD = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.COD : Foods.COD;
        COOKED_BEEF = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_BEEF : Foods.COOKED_BEEF;
        COOKED_CHICKEN = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_CHICKEN : Foods.COOKED_CHICKEN;
        COOKED_COD = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_COD : Foods.COOKED_COD;
        COOKED_MUTTON = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_MUTTON : Foods.COOKED_MUTTON;
        COOKED_PORKCHOP = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_PORKCHOP : Foods.COOKED_PORKCHOP;
        COOKED_RABBIT = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_RABBIT : Foods.COOKED_RABBIT;
        COOKED_SALMON = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_SALMON : Foods.COOKED_SALMON;
        COOKIE = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.COOKIE : Foods.COOKIE;
        DRIED_KELP = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.DRIED_KELP : Foods.DRIED_KELP;
        ENCHANTED_GOLDEN_APPLE = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.ENCHANTED_GOLDEN_APPLE : Foods.ENCHANTED_GOLDEN_APPLE;
        GOLDEN_APPLE = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.GOLDEN_APPLE : Foods.GOLDEN_APPLE;
        GOLDEN_CARROT = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.GOLDEN_CARROT : Foods.GOLDEN_CARROT;
        HONEY_BOTTLE = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.HONEY_BOTTLE : Foods.HONEY_BOTTLE;
        MELON_SLICE = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.MELON_SLICE : Foods.MELON_SLICE;
        MUTTON = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.MUTTON : Foods.MUTTON;
        POISONOUS_POTATO = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.POISONOUS_POTATO : Foods.POISONOUS_POTATO;
        PORKCHOP = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.PORKCHOP : Foods.PORKCHOP;
        POTATO = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.POTATO : Foods.POTATO;
        PUFFERFISH = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.PUFFERFISH : Foods.PUFFERFISH;
        PUMPKIN_PIE = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.PUMPKIN_PIE : Foods.PUMPKIN_PIE;
        RABBIT = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.RABBIT : Foods.RABBIT;
        ROTTEN_FLESH = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.ROTTEN_FLESH : Foods.ROTTEN_FLESH;
        SALMON = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.SALMON : Foods.SALMON;
        SPIDER_EYE = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.SPIDER_EYE : Foods.SPIDER_EYE;
        SWEET_BERRIES = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.SWEET_BERRIES : Foods.SWEET_BERRIES;
        GLOW_BERRIES = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.GLOW_BERRIES : Foods.GLOW_BERRIES;
        TROPICAL_FISH = options().general.betterFoods.getCurrentValue() ? ModFoodComponents.TROPICAL_FISH : Foods.TROPICAL_FISH;
    }
}