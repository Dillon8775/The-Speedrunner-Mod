package net.dillon.speedrunnermod.world.feature;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.Feature;

/**
 * All Speedrunner Mod {@code configured features,} specifically for the {@code speedrunner's wasteland} biome.
 */
public class WastelandFeatures {
    public static final ResourceKey<Feature> DEFAULT_SPEEDRUNNER = ModWorldFeatures.create("wasteland_default_speedrunner");
    public static final ResourceKey<Feature> FANCY_SPEEDRUNNER = ModWorldFeatures.create("wasteland_fancy_speedrunner");
    protected static final ResourceKey<Feature> PATCH_RAW_SPEEDRUNNER_BLOCK = ModWorldFeatures.create("wasteland_patch_raw_speedrunner_block");
    protected static final ResourceKey<Feature> FLOWER_SPEEDRUNNER = ModWorldFeatures.create("flower_speedrunner");
    protected static final ResourceKey<Feature> ORE_SPEEDRUNNER = ModWorldFeatures.create("wasteland_ore_speedrunner");
    protected static final ResourceKey<Feature> ORE_SPEEDRUNNER_SMALL = ModWorldFeatures.create("wasteland_ore_speedrunner_small");
    protected static final ResourceKey<Feature> ORE_EXPERIENCE = ModWorldFeatures.create("wasteland_ore_experience");
    protected static final ResourceKey<Feature> ORE_DIAMOND = ModWorldFeatures.create("wasteland_ore_diamond");
    protected static final ResourceKey<Feature> ORE_DIAMOND_BURIED = ModWorldFeatures.create("wasteland_ore_diamond_buried");
}