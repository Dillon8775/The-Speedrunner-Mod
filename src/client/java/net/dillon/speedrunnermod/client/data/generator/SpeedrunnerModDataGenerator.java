package net.dillon.speedrunnermod.client.data.generator;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.world.biome.ModBiomes;
import net.dillon.speedrunnermod.world.feature.ModConfiguredFeatures;
import net.dillon.speedrunnermod.world.feature.ModPlacedFeatures;
import net.dillon.speedrunnermod.world.feature.WastelandConfiguredFeatures;
import net.dillon.speedrunnermod.world.feature.WastelandPlacedFeatures;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;

/**
 * Runs all Speedrunner Mod {@code data generators} and creates all respective {@code .json} files.
 */
public class SpeedrunnerModDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        SpeedrunnerMod.info("Initializing speedrunner mod data generator!");

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(ModWorldGenerator::new);
        pack.addProvider(ModVillagerTradeGenerator::new);
        pack.addProvider(ModBlockTagGenerator::new);
        pack.addProvider(ModBlockLootTableGenerator::new);
        pack.addProvider(ModEnchantmentGenerator::new);
        pack.addProvider(ModEnchantmentTagGenerator::new);
        pack.addProvider(ModItemTagGenerator::new);
        pack.addProvider(ModFluidTagGenerator::new);
        pack.addProvider(ModAdvancementTabGenerator::new);
        pack.addProvider(ModRecipeGenerator::new);
        pack.addProvider(ModPoiTypeTagGenerator::new);
        pack.addProvider(ModPotionTagGenerator::new);
        pack.addProvider(ModTradeTagGenerator::new);
        pack.addProvider(ModModelGenerator::new);

        DataGenerator.PackGenerator secondPack = fabricDataGenerator.createPack();
        secondPack.addProvider(ModEquipmentAssetProvider::new);

        SpeedrunnerMod.info("Finished running through data generator.");
    }

    /**
     * Runs all other data generators.
     */
    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.TRADE_SET, ModTradeSetGenerator::bootstrap);
        registryBuilder.add(Registries.VILLAGER_TRADE, ModTradesGenerator::bootstrap);

        registryBuilder.add(Registries.BIOME, ModBiomes::bootstrap);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, WastelandConfiguredFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, ModPlacedFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, WastelandPlacedFeatures::bootstrap);
    }
}