package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.component.ModEnchantments;
import net.dillon.speedrunnermod.data.generator.dynamic.ModEnchantmentProvider;
import net.dillon.speedrunnermod.data.generator.dynamic.ModVillagerTradeProvider;
import net.dillon.speedrunnermod.data.generator.dynamic.ModWorldProvider;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.world.biome.ModBiomes;
import net.dillon.speedrunnermod.world.feature.*;
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
        pack.addProvider(ModModelProvider::new);

        pack.addProvider(ModAttributeTagProvider::new);
        pack.addProvider(ModBiomeTagProvider::new);
        pack.addProvider(ModStructureTagGenerator::new);
        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModDamageTypeTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModFluidTagProvider::new);
        pack.addProvider(ModPotionTagProvider::new);
        pack.addProvider(ModEnchantmentTagProvider::new);
        pack.addProvider(ModEntityTypeTagProvider::new);
        pack.addProvider(ModTradeTagProvider::new);
        pack.addProvider(ModPoiTypeTagProvider::new);

        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModAdvancementTabProvider::new);

        pack.addProvider(ModBlockLootTableProvider::new);
        pack.addProvider(DoomBlockLootProvider::new);

        pack.addProvider(ModEnchantmentProvider::new);
        pack.addProvider(ModVillagerTradeProvider::new);
        pack.addProvider(ModWorldProvider::new);

        DataGenerator.PackGenerator secondPack = fabricDataGenerator.createPack();
        secondPack.addProvider(ModEquipmentAssetProvider::new);

        SpeedrunnerMod.info("Finished running through data generator.");
    }

    /**
     * Runs all other data generators.
     */
    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.TRADE_SET, ModTradeSetProvider::bootstrap);
        registryBuilder.add(Registries.VILLAGER_TRADE, ModTradesProvider::bootstrap);

        registryBuilder.add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);
        registryBuilder.add(Registries.BIOME, ModBiomes::bootstrap);
        registryBuilder.add(Registries.TEMPLATE_POOL, ModPools::bootstrap);
        registryBuilder.add(Registries.STRUCTURE, ModStructures::bootstrap);
        registryBuilder.add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, ModWorldFeatures::bootstrap);
        registryBuilder.add(Registries.CONFIGURED_FEATURE, WastelandFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, ModWorldPlacements::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, WastelandPlacements::bootstrap);
    }
}