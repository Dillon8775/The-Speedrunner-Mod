package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.world.biome.ModBiomes;
import net.dillon.speedrunnermod.world.feature.ModConfiguredFeatures;
import net.dillon.speedrunnermod.world.feature.ModPlacedFeatures;
import net.dillon.speedrunnermod.world.feature.WastelandConfiguredFeatures;
import net.dillon.speedrunnermod.world.feature.WastelandPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.data.DataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

/**
 * Runs all Speedrunner Mod {@code data generators} and creates all respective {@code .json} files.
 */
public class SpeedrunnerModDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        SpeedrunnerMod.info("Initializing speedrunner mod data generator!");

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModWorldGenerator::new);
        pack.addProvider(ModBlockTagGenerator::new);
        pack.addProvider(ModBlockLootTableGenerator::new);
        pack.addProvider(ModEnchantmentGenerator::new);
        pack.addProvider(ModEnchantmentTagGenerator::new);
        pack.addProvider(ModAdvancementTabGenerator::new);
        pack.addProvider(ModItemTagGenerator::new);
        pack.addProvider(ModFluidTagGenerator::new);
        pack.addProvider(ModRecipeGenerator::new);
        pack.addProvider(ModModelGenerator::new);

        DataGenerator.Pack secondPack = fabricDataGenerator.createPack();
        secondPack.addProvider(ModEquipmentAssetProvider::new);

        SpeedrunnerMod.info("Finished running through data generator.");
    }

    /**
     * Runs all other data generators.
     */
    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.BIOME, ModBiomes::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, WastelandConfiguredFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, WastelandPlacedFeatures::bootstrap);
    }
}