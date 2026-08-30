package net.dillon.speedrunnermod.world.biome;

import net.dillon.speedrunnermod.helper.ModHelper;
import net.dillon.speedrunnermod.world.feature.ModWorldPlacements;
import net.dillon.speedrunnermod.world.feature.WastelandPlacements;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.placement.CavePlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.ARGB;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
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
        BiomeGenerationSettings.Builder lookupBackedBuilder = new BiomeGenerationSettings.Builder(
                context.lookup(Registries.PLACED_FEATURE), context.lookup(Registries.CARVER));

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
                .hasPrecipitation(false)
                .temperature(0.7F)
                .downfall(0.4F)
                .specialEffects(new BiomeSpecialEffects.Builder()
                        .waterColor(ModHelper.SPEEDRUNNER_WATER_COLOR)
                        .foliageColorOverride(0xAED6F1)
                        .grassColorOverride(0xAED6F1)
                        .build())
                .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, ARGB.vector3fFromRGB24(ModHelper.SPEEDRUNNER_WATER_FOG_COLOR))
                .setAttribute(EnvironmentAttributes.FOG_COLOR, ARGB.vector3fFromRGB24(0xEBF5FB))
                .setAttribute(EnvironmentAttributes.SKY_COLOR, ARGB.vector3fFromRGB24(OverworldBiomes.calculateSkyColor(0.5F)))
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
        builder.addSpawn(EntityTypes.COW, 100, new UniformInt(4, 8));
        builder.addSpawn(EntityTypes.PIG, 100, new UniformInt(4, 8));
        builder.addSpawn(EntityTypes.SHEEP, 100, new UniformInt(4, 8));
        builder.addSpawn(EntityTypes.CHICKEN, 100, new UniformInt(4, 8));
        builder.addSpawn(EntityTypes.HORSE, 40, new UniformInt(2, 6));
        builder.addSpawn(EntityTypes.DONKEY, 20, new UniformInt(2, 3));
        builder.addSpawn(EntityTypes.SNIFFER, 10, new ConstantInt(1));
    }

    /**
     * Adds all {@code monster spawn entries} to the {@code Speedrunner's Wasteland biome.}
     * @param builder
     */
    private static void addSpeedrunnersWastelandMonsters(MobSpawnSettings.Builder builder) {
        builder.addSpawn(EntityTypes.SPIDER, 25, new UniformInt(1, 4));
        builder.addSpawn(EntityTypes.ZOMBIE, 25, new UniformInt(1, 4));
        builder.addSpawn(EntityTypes.ZOMBIE_VILLAGER, 25, new ConstantInt(1));
        builder.addSpawn(EntityTypes.SKELETON, 25, new UniformInt(1, 4));
        builder.addSpawn(EntityTypes.CREEPER, 25, new UniformInt(1, 4));
        builder.addSpawn(EntityTypes.SLIME, 25, new UniformInt(1, 3));
        builder.addSpawn(EntityTypes.ENDERMAN, 100, new UniformInt(1, 2));
    }
}