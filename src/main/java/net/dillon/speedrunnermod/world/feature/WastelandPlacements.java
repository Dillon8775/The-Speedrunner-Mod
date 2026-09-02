package net.dillon.speedrunnermod.world.feature;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

/**
 * All Speedrunner Mod {@code placed features}, specifically for the {@code speedrunner's wasteland} biome.
 */
public class WastelandPlacements {
    public static final ResourceKey<PlacedFeature> DEFAULT_SPEEDRUNNER_PLACED = ModWorldPlacements.create("wasteland_default_speedrunner_placed");
    public static final ResourceKey<PlacedFeature> FANCY_SPEEDRUNNER_PLACED = ModWorldPlacements.create("wasteland_fancy_speedrunner_placed");
    public static final ResourceKey<PlacedFeature> PATCH_RAW_SPEEDRUNNER_BLOCK_PLACED = ModWorldPlacements.create("patch_raw_speedrunner_block");
    public static final ResourceKey<PlacedFeature> FLOWER_SPEEDRUNNER_PLACED = ModWorldPlacements.create("flower_speedrunner");
    public static final ResourceKey<PlacedFeature> SUGAR_CANE_WASTELAND = ModWorldPlacements.create("sugar_cane_wasteland");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_UPPER = ModWorldPlacements.create("wasteland_ore_speedrunner_upper");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_MIDDLE = ModWorldPlacements.create("wasteland_ore_speedrunner_middle");
    public static final ResourceKey<PlacedFeature> ORE_SPEEDRUNNER_SMALL = ModWorldPlacements.create("wasteland_ore_speedrunner_small");
    public static final ResourceKey<PlacedFeature> ORE_EXPERIENCE = ModWorldPlacements.create("wasteland_ore_experience");
    public static final ResourceKey<PlacedFeature> ORE_DIAMOND = ModWorldPlacements.create("wasteland_ore_diamond");
    public static final ResourceKey<PlacedFeature> ORE_DIAMOND_BURIED = ModWorldPlacements.create("wasteland_ore_diamond_buried");
}