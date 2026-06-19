package net.dillon.speedrunnermod.world.biome;

import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.world.feature.ModWorldPlacements;
import net.dillon.speedrunnermod.world.feature.WastelandPlacements;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;

/**
 * All Speedrunner Mod {@code custom biome features and additions.}
 */
public class SpeedrunnersWastelandBiome {

    /**
     * All features for the {@code Speedrunner's Wasteland biome.}
     */
    protected static Biome createSpeedrunnersWasteland(BootstrapContext<Biome> context) {
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        net.minecraft.world.level.biome.BiomeGenerationSettings.Builder lookupBackedBuilder = new net.minecraft.world.level.biome.BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CONFIGURED_CARVER));

        BiomeDefaultFeatures.addDefaultCarversAndLakes(lookupBackedBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(lookupBackedBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(lookupBackedBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(lookupBackedBuilder);
        BiomeDefaultFeatures.addDefaultSprings(lookupBackedBuilder);
        BiomeDefaultFeatures.addSurfaceFreezing(lookupBackedBuilder);

        BiomeDefaultFeatures.addPlainGrass(lookupBackedBuilder);
        lookupBackedBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_GRASS_PLAIN);
        BiomeDefaultFeatures.addDefaultFlowers(lookupBackedBuilder);

        BiomeDefaultFeatures.caveSpawns(spawnBuilder);
        addSpeedrunnersWastelandFarmAnimals(spawnBuilder);
        addSpeedrunnersWastelandMonsters(spawnBuilder);

        addSpeedrunnersWastelandOres(lookupBackedBuilder);
        BiomeDefaultFeatures.addDefaultSoftDisks(lookupBackedBuilder);

        addSpeedrunnersWastelandFeatures(lookupBackedBuilder);

        BiomeDefaultFeatures.addDefaultExtraVegetation(lookupBackedBuilder, true);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(true)
                .temperature(0.3F)
                .downfall(0.7F)
                .specialEffects(new net.minecraft.world.level.biome.BiomeSpecialEffects.Builder()
                        .waterColor(ModUtil.SPEEDRUNNER_WATER_COLOR)
                        .foliageColorOverride(0xAED6F1)
                        .grassColorOverride(0xAED6F1)
                        .build())
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, ModUtil.SPEEDRUNNER_WATER_FOG_COLOR)
                .setAttribute(EnvironmentAttributes.FOG_COLOR, 0xEBF5FB)
                .setAttribute(EnvironmentAttributes.SKY_COLOR, OverworldBiomes.calculateSkyColor(0.5F))
                .mobSpawnSettings(spawnBuilder.build())
                .generationSettings(lookupBackedBuilder.build()).build();
    }

    /**
     * Adds all {@code ore features} to the {@code Speedrunner's Wasteland biome.}
     */
    private static void addSpeedrunnersWastelandOres(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WastelandPlacements.ORE_DIAMOND);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WastelandPlacements.ORE_DIAMOND_BURIED);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WastelandPlacements.ORE_SPEEDRUNNER_UPPER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WastelandPlacements.ORE_SPEEDRUNNER_MIDDLE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WastelandPlacements.ORE_SPEEDRUNNER_SMALL);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, WastelandPlacements.ORE_EXPERIENCE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_UPPER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COAL_LOWER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_UPPER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_MIDDLE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_IRON_SMALL);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GOLD);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_GOLD_LOWER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_REDSTONE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_REDSTONE_LOWER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_LAPIS);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_LAPIS_BURIED);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, OrePlacements.ORE_COPPER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, CavePlacements.UNDERWATER_MAGMA);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, ModWorldPlacements.ORE_IGNEOUS);
    }

    /**
     * Adds all {@code "features"} to the {@code Speedrunner's Wasteland biome.}
     */
    private static void addSpeedrunnersWastelandFeatures(BiomeGenerationSettings.Builder builder) {
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WastelandPlacements.PATCH_RAW_SPEEDRUNNER_BLOCK_PLACED);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WastelandPlacements.DEFAULT_SPEEDRUNNER_PLACED);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WastelandPlacements.FANCY_SPEEDRUNNER_PLACED);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WastelandPlacements.FLOWER_SPEEDRUNNER_PLACED);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, WastelandPlacements.SUGAR_CANE_WASTELAND);
    }

    /**
     * Adds all {@code farm animal spawn entries} to the {@code Speedrunner's Wasteland biome.}
     */
    public static void addSpeedrunnersWastelandFarmAnimals(final MobSpawnSettings.Builder builder) {
        builder.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(EntityTypes.COW, 4, 8));
        builder.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(EntityTypes.PIG, 4, 8));
        builder.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(EntityTypes.SHEEP, 4, 8));
        builder.addSpawn(MobCategory.CREATURE, 100, new MobSpawnSettings.SpawnerData(EntityTypes.CHICKEN, 4, 8));
        builder.addSpawn(MobCategory.CREATURE, 40, new MobSpawnSettings.SpawnerData(EntityTypes.HORSE, 2, 6));
        builder.addSpawn(MobCategory.CREATURE, 20, new MobSpawnSettings.SpawnerData(EntityTypes.DONKEY, 2, 3));
        builder.addSpawn(MobCategory.CREATURE, 10, new MobSpawnSettings.SpawnerData(EntityTypes.SNIFFER, 1, 1));
    }

    /**
     * Adds all {@code monster spawn entries} to the {@code Speedrunner's Wasteland biome.}
     * @param builder
     */
    private static void addSpeedrunnersWastelandMonsters(MobSpawnSettings.Builder builder) {
        builder.addSpawn(MobCategory.MONSTER, 25, new MobSpawnSettings.SpawnerData(EntityTypes.SPIDER, 4, 4));
        builder.addSpawn(MobCategory.MONSTER, 25, new MobSpawnSettings.SpawnerData(EntityTypes.ZOMBIE, 1, 4));
        builder.addSpawn(MobCategory.MONSTER, 25, new MobSpawnSettings.SpawnerData(EntityTypes.ZOMBIE_VILLAGER, 1, 1));
        builder.addSpawn(MobCategory.MONSTER, 25, new MobSpawnSettings.SpawnerData(EntityTypes.SKELETON, 1, 4));
        builder.addSpawn(MobCategory.MONSTER, 25, new MobSpawnSettings.SpawnerData(EntityTypes.CREEPER, 1, 4));
        builder.addSpawn(MobCategory.MONSTER, 25, new MobSpawnSettings.SpawnerData(EntityTypes.SLIME, 1, 3));
        builder.addSpawn(MobCategory.MONSTER, 100, new MobSpawnSettings.SpawnerData(EntityTypes.ENDERMAN, 1, 2));
    }
}