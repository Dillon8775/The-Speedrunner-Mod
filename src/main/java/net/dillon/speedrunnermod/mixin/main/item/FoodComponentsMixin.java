package net.dillon.speedrunnermod.mixin.main.item;

import net.dillon.speedrunnermod.item.ModFoodComponents;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.component.type.FoodComponents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * Modifies {@code vanilla food items} to be better.
 * <p>See {@link ModFoodComponents} for more.</p>
 */
@Mixin(FoodComponents.class)
public class FoodComponentsMixin {
    @Shadow
    public static final FoodComponent APPLE, BAKED_POTATO, BEEF, BEETROOT, BREAD, CARROT, CHICKEN , CHORUS_FRUIT, COD, COOKED_BEEF, COOKED_CHICKEN, COOKED_COD, COOKED_MUTTON, COOKED_PORKCHOP, COOKED_RABBIT, COOKED_SALMON, COOKIE, DRIED_KELP, ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE, GOLDEN_CARROT, HONEY_BOTTLE, MELON_SLICE, MUTTON, POISONOUS_POTATO, PORKCHOP, POTATO, PUFFERFISH, PUMPKIN_PIE, RABBIT, ROTTEN_FLESH, SALMON, SPIDER_EYE, SWEET_BERRIES, GLOW_BERRIES, TROPICAL_FISH;

    static {
        APPLE = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.APPLE : FoodComponents.APPLE;
        BAKED_POTATO = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.BAKED_POTATO : FoodComponents.BAKED_POTATO;
        BEEF = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.BEEF : FoodComponents.BEEF;
        BEETROOT = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.BEETROOT : FoodComponents.BEETROOT;
        BREAD = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.BREAD : FoodComponents.BREAD;
        CARROT = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.CARROT : FoodComponents.CARROT;
        CHICKEN = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.CHICKEN : FoodComponents.CHICKEN;
        CHORUS_FRUIT = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.CHORUS_FRUIT : FoodComponents.CHORUS_FRUIT;
        COD = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.COD : FoodComponents.COD;
        COOKED_BEEF = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_BEEF : FoodComponents.COOKED_BEEF;
        COOKED_CHICKEN = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_CHICKEN : FoodComponents.COOKED_CHICKEN;
        COOKED_COD = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_COD : FoodComponents.COOKED_COD;
        COOKED_MUTTON = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_MUTTON : FoodComponents.COOKED_MUTTON;
        COOKED_PORKCHOP = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_PORKCHOP : FoodComponents.COOKED_PORKCHOP;
        COOKED_RABBIT = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_RABBIT : FoodComponents.COOKED_RABBIT;
        COOKED_SALMON = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.COOKED_SALMON : FoodComponents.COOKED_SALMON;
        COOKIE = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.COOKIE : FoodComponents.COOKIE;
        DRIED_KELP = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.DRIED_KELP : FoodComponents.DRIED_KELP;
        ENCHANTED_GOLDEN_APPLE = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.ENCHANTED_GOLDEN_APPLE : FoodComponents.ENCHANTED_GOLDEN_APPLE;
        GOLDEN_APPLE = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.GOLDEN_APPLE : FoodComponents.GOLDEN_APPLE;
        GOLDEN_CARROT = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.GOLDEN_CARROT : FoodComponents.GOLDEN_CARROT;
        HONEY_BOTTLE = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.HONEY_BOTTLE : FoodComponents.HONEY_BOTTLE;
        MELON_SLICE = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.MELON_SLICE : FoodComponents.MELON_SLICE;
        MUTTON = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.MUTTON : FoodComponents.MUTTON;
        POISONOUS_POTATO = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.POISONOUS_POTATO : FoodComponents.POISONOUS_POTATO;
        PORKCHOP = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.PORKCHOP : FoodComponents.PORKCHOP;
        POTATO = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.POTATO : FoodComponents.POTATO;
        PUFFERFISH = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.PUFFERFISH : FoodComponents.PUFFERFISH;
        PUMPKIN_PIE = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.PUMPKIN_PIE : FoodComponents.PUMPKIN_PIE;
        RABBIT = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.RABBIT : FoodComponents.RABBIT;
        ROTTEN_FLESH = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.ROTTEN_FLESH : FoodComponents.ROTTEN_FLESH;
        SALMON = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.SALMON : FoodComponents.SALMON;
        SPIDER_EYE = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.SPIDER_EYE : FoodComponents.SPIDER_EYE;
        SWEET_BERRIES = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.SWEET_BERRIES : FoodComponents.SWEET_BERRIES;
        GLOW_BERRIES = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.GLOW_BERRIES : FoodComponents.GLOW_BERRIES;
        TROPICAL_FISH = options().main.betterFoods.getCurrentValue() ? ModFoodComponents.TROPICAL_FISH : FoodComponents.TROPICAL_FISH;
    }
}