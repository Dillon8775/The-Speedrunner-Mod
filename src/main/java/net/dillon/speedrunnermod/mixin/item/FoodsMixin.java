package net.dillon.speedrunnermod.mixin.item;

import net.dillon.speedrunnermod.item.ModConsumables;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;

/**
 * Modifies {@code vanilla food items} to be better.
 * <p>See {@link ModConsumables} for more.</p>
 */
@Mixin(Foods.class)
public class FoodsMixin {
    @Shadow
    public static final FoodProperties APPLE, BAKED_POTATO, BEEF, BEETROOT, BREAD, CARROT, CHICKEN , CHORUS_FRUIT, COD, COOKED_BEEF, COOKED_CHICKEN, COOKED_COD, COOKED_MUTTON, COOKED_PORKCHOP, COOKED_RABBIT, COOKED_SALMON, COOKIE, DRIED_KELP, ENCHANTED_GOLDEN_APPLE, GOLDEN_APPLE, GOLDEN_CARROT, HONEY_BOTTLE, MELON_SLICE, MUTTON, POISONOUS_POTATO, PORKCHOP, POTATO, PUFFERFISH, PUMPKIN_PIE, RABBIT, ROTTEN_FLESH, SALMON, SPIDER_EYE, SWEET_BERRIES, GLOW_BERRIES, TROPICAL_FISH;

    static {
        APPLE = common().general.betterFoods.getCurrentValue() ? ModConsumables.APPLE : Foods.APPLE;
        BAKED_POTATO = common().general.betterFoods.getCurrentValue() ? ModConsumables.BAKED_POTATO : Foods.BAKED_POTATO;
        BEEF = common().general.betterFoods.getCurrentValue() ? ModConsumables.BEEF : Foods.BEEF;
        BEETROOT = common().general.betterFoods.getCurrentValue() ? ModConsumables.BEETROOT : Foods.BEETROOT;
        BREAD = common().general.betterFoods.getCurrentValue() ? ModConsumables.BREAD : Foods.BREAD;
        CARROT = common().general.betterFoods.getCurrentValue() ? ModConsumables.CARROT : Foods.CARROT;
        CHICKEN = common().general.betterFoods.getCurrentValue() ? ModConsumables.CHICKEN : Foods.CHICKEN;
        CHORUS_FRUIT = common().general.betterFoods.getCurrentValue() ? ModConsumables.CHORUS_FRUIT : Foods.CHORUS_FRUIT;
        COD = common().general.betterFoods.getCurrentValue() ? ModConsumables.COD : Foods.COD;
        COOKED_BEEF = common().general.betterFoods.getCurrentValue() ? ModConsumables.COOKED_BEEF : Foods.COOKED_BEEF;
        COOKED_CHICKEN = common().general.betterFoods.getCurrentValue() ? ModConsumables.COOKED_CHICKEN : Foods.COOKED_CHICKEN;
        COOKED_COD = common().general.betterFoods.getCurrentValue() ? ModConsumables.COOKED_COD : Foods.COOKED_COD;
        COOKED_MUTTON = common().general.betterFoods.getCurrentValue() ? ModConsumables.COOKED_MUTTON : Foods.COOKED_MUTTON;
        COOKED_PORKCHOP = common().general.betterFoods.getCurrentValue() ? ModConsumables.COOKED_PORKCHOP : Foods.COOKED_PORKCHOP;
        COOKED_RABBIT = common().general.betterFoods.getCurrentValue() ? ModConsumables.COOKED_RABBIT : Foods.COOKED_RABBIT;
        COOKED_SALMON = common().general.betterFoods.getCurrentValue() ? ModConsumables.COOKED_SALMON : Foods.COOKED_SALMON;
        COOKIE = common().general.betterFoods.getCurrentValue() ? ModConsumables.COOKIE : Foods.COOKIE;
        DRIED_KELP = common().general.betterFoods.getCurrentValue() ? ModConsumables.DRIED_KELP : Foods.DRIED_KELP;
        ENCHANTED_GOLDEN_APPLE = common().general.betterFoods.getCurrentValue() ? ModConsumables.ENCHANTED_GOLDEN_APPLE : Foods.ENCHANTED_GOLDEN_APPLE;
        GOLDEN_APPLE = common().general.betterFoods.getCurrentValue() ? ModConsumables.GOLDEN_APPLE : Foods.GOLDEN_APPLE;
        GOLDEN_CARROT = common().general.betterFoods.getCurrentValue() ? ModConsumables.GOLDEN_CARROT : Foods.GOLDEN_CARROT;
        HONEY_BOTTLE = common().general.betterFoods.getCurrentValue() ? ModConsumables.HONEY_BOTTLE : Foods.HONEY_BOTTLE;
        MELON_SLICE = common().general.betterFoods.getCurrentValue() ? ModConsumables.MELON_SLICE : Foods.MELON_SLICE;
        MUTTON = common().general.betterFoods.getCurrentValue() ? ModConsumables.MUTTON : Foods.MUTTON;
        POISONOUS_POTATO = common().general.betterFoods.getCurrentValue() ? ModConsumables.POISONOUS_POTATO : Foods.POISONOUS_POTATO;
        PORKCHOP = common().general.betterFoods.getCurrentValue() ? ModConsumables.PORKCHOP : Foods.PORKCHOP;
        POTATO = common().general.betterFoods.getCurrentValue() ? ModConsumables.POTATO : Foods.POTATO;
        PUFFERFISH = common().general.betterFoods.getCurrentValue() ? ModConsumables.PUFFERFISH : Foods.PUFFERFISH;
        PUMPKIN_PIE = common().general.betterFoods.getCurrentValue() ? ModConsumables.PUMPKIN_PIE : Foods.PUMPKIN_PIE;
        RABBIT = common().general.betterFoods.getCurrentValue() ? ModConsumables.RABBIT : Foods.RABBIT;
        ROTTEN_FLESH = common().general.betterFoods.getCurrentValue() ? ModConsumables.ROTTEN_FLESH : Foods.ROTTEN_FLESH;
        SALMON = common().general.betterFoods.getCurrentValue() ? ModConsumables.SALMON : Foods.SALMON;
        SPIDER_EYE = common().general.betterFoods.getCurrentValue() ? ModConsumables.SPIDER_EYE : Foods.SPIDER_EYE;
        SWEET_BERRIES = common().general.betterFoods.getCurrentValue() ? ModConsumables.SWEET_BERRIES : Foods.SWEET_BERRIES;
        GLOW_BERRIES = common().general.betterFoods.getCurrentValue() ? ModConsumables.GLOW_BERRIES : Foods.GLOW_BERRIES;
        TROPICAL_FISH = common().general.betterFoods.getCurrentValue() ? ModConsumables.TROPICAL_FISH : Foods.TROPICAL_FISH;
    }
}