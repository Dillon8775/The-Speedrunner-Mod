package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.mixin.item.FoodsMixin;
import net.minecraft.world.food.FoodProperties;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * All Speedrunner Mod {@code food components} (or food items).
 * <p>Also contains all modified vanilla food components, see {@link FoodsMixin} for more.</p>
 */
public class ModConsumables {
    protected static final FoodProperties SPEEDRUNNER_BULK;
    protected static final FoodProperties ROTTEN_SPEEDRUNNER_BULK;
    protected static final FoodProperties COOKED_FLESH;
    protected static final FoodProperties PIGLIN_PORK;
    protected static final FoodProperties COOKED_PIGLIN_PORK;
    public static final FoodProperties APPLE;
    public static final FoodProperties BAKED_POTATO;
    public static final FoodProperties BEEF;
    public static final FoodProperties BEETROOT;
    public static final FoodProperties BREAD;
    public static final FoodProperties CARROT;
    public static final FoodProperties CHICKEN;
    public static final FoodProperties CHORUS_FRUIT;
    public static final FoodProperties COD;
    public static final FoodProperties COOKED_BEEF;
    public static final FoodProperties COOKED_CHICKEN;
    public static final FoodProperties COOKED_COD;
    public static final FoodProperties COOKED_MUTTON;
    public static final FoodProperties COOKED_PORKCHOP;
    public static final FoodProperties COOKED_RABBIT;
    public static final FoodProperties COOKED_SALMON;
    public static final FoodProperties COOKIE;
    public static final FoodProperties DRIED_KELP;
    public static final FoodProperties ENCHANTED_GOLDEN_APPLE;
    public static final FoodProperties GOLDEN_APPLE;
    public static final FoodProperties GOLDEN_CARROT;
    public static final FoodProperties HONEY_BOTTLE;
    public static final FoodProperties MELON_SLICE;
    public static final FoodProperties MUTTON;
    public static final FoodProperties POISONOUS_POTATO;
    public static final FoodProperties PORKCHOP;
    public static final FoodProperties POTATO;
    public static final FoodProperties PUFFERFISH;
    public static final FoodProperties PUMPKIN_PIE;
    public static final FoodProperties RABBIT;
    public static final FoodProperties ROTTEN_FLESH;
    public static final FoodProperties SALMON;
    public static final FoodProperties SPIDER_EYE;
    public static final FoodProperties SWEET_BERRIES;
    public static final FoodProperties GLOW_BERRIES;
    public static final FoodProperties TROPICAL_FISH;

    static {
        SPEEDRUNNER_BULK = new FoodProperties.Builder().nutrition(12).saturationModifier(1.2F).alwaysEdible().build();
        ROTTEN_SPEEDRUNNER_BULK = new FoodProperties.Builder().nutrition(4).saturationModifier(0.1F).build();
        COOKED_FLESH = new FoodProperties.Builder().nutrition(6).saturationModifier(0.8F).build();
        PIGLIN_PORK = options().general.betterFoods.getCurrentValue() ? new FoodProperties.Builder().nutrition(4).saturationModifier(0.3F).build() : new FoodProperties.Builder().nutrition(3).saturationModifier(0.2F).build();
        COOKED_PIGLIN_PORK = options().general.betterFoods.getCurrentValue() ? new FoodProperties.Builder().nutrition(8).saturationModifier(0.8F).build() : new FoodProperties.Builder().nutrition(8).saturationModifier(0.7F).build();
        APPLE = new FoodProperties.Builder().nutrition(4).saturationModifier(0.8F).build();
        BAKED_POTATO = new FoodProperties.Builder().nutrition(6).saturationModifier(0.9F).build();
        BEEF = new FoodProperties.Builder().nutrition(4).saturationModifier(0.7F).build();
        BEETROOT = new FoodProperties.Builder().nutrition(2).saturationModifier(1.4F).build();
        BREAD = new FoodProperties.Builder().nutrition(5).saturationModifier(1.1F).build();
        CARROT = new FoodProperties.Builder().nutrition(3).saturationModifier(1.2F).build();
        CHICKEN = new FoodProperties.Builder().nutrition(2).saturationModifier(1.2F).build();
        CHORUS_FRUIT = new FoodProperties.Builder().nutrition(4).saturationModifier(0.6F).alwaysEdible().build();
        COD = new FoodProperties.Builder().nutrition(2).saturationModifier(1.2F).build();
        COOKED_BEEF = new FoodProperties.Builder().nutrition(8).saturationModifier(0.9F).build();
        COOKED_CHICKEN = new FoodProperties.Builder().nutrition(6).saturationModifier(0.9F).build();
        COOKED_COD = new FoodProperties.Builder().nutrition(5).saturationModifier(1.1F).build();
        COOKED_MUTTON = new FoodProperties.Builder().nutrition(6).saturationModifier(0.9F).build();
        COOKED_PORKCHOP = new FoodProperties.Builder().nutrition(8).saturationModifier(0.9F).build();
        COOKED_RABBIT = new FoodProperties.Builder().nutrition(5).saturationModifier(1.1F).build();
        COOKED_SALMON = new FoodProperties.Builder().nutrition(6).saturationModifier(0.9F).build();
        COOKIE = new FoodProperties.Builder().nutrition(2).saturationModifier(1.3F).build();
        DRIED_KELP = new FoodProperties.Builder().nutrition(1).saturationModifier(0.6F).build();
        ENCHANTED_GOLDEN_APPLE = new FoodProperties.Builder().nutrition(8).saturationModifier(1.4F).alwaysEdible().build();
        GOLDEN_APPLE = new FoodProperties.Builder().nutrition(8).saturationModifier(1.3F).alwaysEdible().build();
        GOLDEN_CARROT = new FoodProperties.Builder().nutrition(6).saturationModifier(1.2F).build();
        HONEY_BOTTLE = new FoodProperties.Builder().nutrition(6).saturationModifier(0.5F).build();
        MELON_SLICE = new FoodProperties.Builder().nutrition(4).saturationModifier(1.2F).build();
        MUTTON = new FoodProperties.Builder().nutrition(2).saturationModifier(0.8F).build();
        POISONOUS_POTATO = new FoodProperties.Builder().nutrition(2).saturationModifier(0.7F).build();
        PORKCHOP = new FoodProperties.Builder().nutrition(4).saturationModifier(0.4F).build();
        POTATO = new FoodProperties.Builder().nutrition(1).saturationModifier(1.0F).build();
        PUFFERFISH = new FoodProperties.Builder().nutrition(1).saturationModifier(0.1F).build();
        PUMPKIN_PIE = new FoodProperties.Builder().nutrition(8).saturationModifier(0.9F).build();
        RABBIT = new FoodProperties.Builder().nutrition(3).saturationModifier(0.9F).build();
        ROTTEN_FLESH = new FoodProperties.Builder().nutrition(4).saturationModifier(0.1F).build();
        SALMON = new FoodProperties.Builder().nutrition(2).saturationModifier(1.4F).build();
        SPIDER_EYE = new FoodProperties.Builder().nutrition(2).saturationModifier(1.1F).build();
        SWEET_BERRIES = new FoodProperties.Builder().nutrition(4).saturationModifier(1.2F).build();
        GLOW_BERRIES = new FoodProperties.Builder().nutrition(2).saturationModifier(1.0F).build();
        TROPICAL_FISH = new FoodProperties.Builder().nutrition(2).saturationModifier(1.2F).build();
    }
}