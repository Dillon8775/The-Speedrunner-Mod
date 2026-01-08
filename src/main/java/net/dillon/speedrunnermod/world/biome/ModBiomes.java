package net.dillon.speedrunnermod.world.biome;

import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.world.feature.ModPlacedFeatures;
import net.dillon.speedrunnermod.world.feature.WastelandPlacedFeatures;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import net.minecraft.world.gen.feature.OrePlacedFeatures;
import net.minecraft.world.gen.feature.UndergroundPlacedFeatures;
import net.minecraft.world.gen.feature.VegetationPlacedFeatures;

/**
 * All Speedrunner Mod {@code custom biome features and additions.}
 */
public class ModBiomes {

    /**
     * See ModWorldGenerator for more.
     */
    public static void bootstrap(Registerable<Biome> biomeRegisterable) {
        biomeRegisterable.register(ModBiomeKeys.SPEEDRUNNERS_WASTELAND_KEY, createSpeedrunnersWasteland(biomeRegisterable));
    }

    /**
     * All features for the {@code Speedrunner's Wasteland biome.}
     */
    private static Biome createSpeedrunnersWasteland(Registerable<Biome> context) {
        SpawnSettings.Builder spawnBuilder = new SpawnSettings.Builder();
        GenerationSettings.LookupBackedBuilder lookupBackedBuilder = new GenerationSettings.LookupBackedBuilder(
                context.getRegistryLookup(RegistryKeys.PLACED_FEATURE), context.getRegistryLookup(RegistryKeys.CONFIGURED_CARVER));

        DefaultBiomeFeatures.addLandCarvers(lookupBackedBuilder);
        DefaultBiomeFeatures.addAmethystGeodes(lookupBackedBuilder);
        DefaultBiomeFeatures.addDungeons(lookupBackedBuilder);
        DefaultBiomeFeatures.addMineables(lookupBackedBuilder);
        DefaultBiomeFeatures.addSprings(lookupBackedBuilder);
        DefaultBiomeFeatures.addFrozenTopLayer(lookupBackedBuilder);

        DefaultBiomeFeatures.addPlainsTallGrass(lookupBackedBuilder);
        lookupBackedBuilder.feature(GenerationStep.Feature.VEGETAL_DECORATION, VegetationPlacedFeatures.PATCH_GRASS_PLAIN);
        DefaultBiomeFeatures.addDefaultFlowers(lookupBackedBuilder);

        DefaultBiomeFeatures.addCaveMobs(spawnBuilder);
        addSpeedrunnersWastelandMonsters(spawnBuilder);

        addSpeedrunnersWastelandOres(lookupBackedBuilder);
        DefaultBiomeFeatures.addDefaultDisks(lookupBackedBuilder);

        addSpeedrunnersWastelandFeatures(lookupBackedBuilder);

        DefaultBiomeFeatures.addDefaultVegetation(lookupBackedBuilder, true);

        return new Biome.Builder()
                .precipitation(true)
                .temperature(0.3F)
                .downfall(0.7F)
                .effects(new BiomeEffects.Builder()
                        .waterColor(ModUtil.SPEEDRUNNER_WATER_COLOR)
                        .foliageColor(0xAED6F1)
                        .grassColor(0xAED6F1)
                        .build())
                .setEnvironmentAttribute(EnvironmentAttributes.WATER_FOG_COLOR_VISUAL, ModUtil.SPEEDRUNNER_WATER_FOG_COLOR)
                .setEnvironmentAttribute(EnvironmentAttributes.FOG_COLOR_VISUAL, 0xEBF5FB)
                .setEnvironmentAttribute(EnvironmentAttributes.SKY_COLOR_VISUAL, OverworldBiomeCreator.getSkyColor(0.5F))
                .spawnSettings(spawnBuilder.build())
                .generationSettings(lookupBackedBuilder.build()).build();
    }

    /**
     * Adds all {@code ore features} to the {@code Speedrunner's Wasteland biome.}
     */
    private static void addSpeedrunnersWastelandOres(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, WastelandPlacedFeatures.ORE_DIAMOND);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, WastelandPlacedFeatures.ORE_DIAMOND_BURIED);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, WastelandPlacedFeatures.ORE_SPEEDRUNNER_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, WastelandPlacedFeatures.ORE_SPEEDRUNNER_MIDDLE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, WastelandPlacedFeatures.ORE_SPEEDRUNNER_SMALL);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, WastelandPlacedFeatures.ORE_EXPERIENCE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_COAL_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_COAL_LOWER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_IRON_UPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_IRON_MIDDLE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_IRON_SMALL);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GOLD);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_GOLD_LOWER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_REDSTONE);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_REDSTONE_LOWER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_LAPIS);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_LAPIS_BURIED);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, OrePlacedFeatures.ORE_COPPER);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, UndergroundPlacedFeatures.UNDERWATER_MAGMA);
        builder.feature(GenerationStep.Feature.UNDERGROUND_ORES, ModPlacedFeatures.ORE_IGNEOUS);
    }

    /**
     * Adds all {@code "features"} to the {@code Speedrunner's Wasteland biome.}
     */
    private static void addSpeedrunnersWastelandFeatures(GenerationSettings.LookupBackedBuilder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WastelandPlacedFeatures.PATCH_RAW_SPEEDRUNNER_BLOCK_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WastelandPlacedFeatures.DEFAULT_SPEEDRUNNER_PLACED);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, WastelandPlacedFeatures.FANCY_SPEEDRUNNER_PLACED);
    }

    /**
     * Adds all {@code monster spawn entries} to the {@code Speedrunner's Wasteland biome.}
     * @param builder
     */
    private static void addSpeedrunnersWastelandMonsters(SpawnSettings.Builder builder) {
        builder.spawn(SpawnGroup.MONSTER, 25, new SpawnSettings.SpawnEntry(EntityType.SPIDER, 4, 4));
        builder.spawn(SpawnGroup.MONSTER, 25, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE, 1, 4));
        builder.spawn(SpawnGroup.MONSTER, 25, new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_VILLAGER, 1, 1));
        builder.spawn(SpawnGroup.MONSTER, 25, new SpawnSettings.SpawnEntry(EntityType.SKELETON, 1, 4));
        builder.spawn(SpawnGroup.MONSTER, 25, new SpawnSettings.SpawnEntry(EntityType.CREEPER, 1, 4));
        builder.spawn(SpawnGroup.MONSTER, 100, new SpawnSettings.SpawnEntry(EntityType.ENDERMAN, 1, 2));
    }
}