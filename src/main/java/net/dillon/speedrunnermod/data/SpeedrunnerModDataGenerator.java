package net.dillon.speedrunnermod.data;

import net.dillon.speedrunnermod.advancement.ModAdvancementProvider;
import net.dillon.speedrunnermod.component.ModEnchantments;
import net.dillon.speedrunnermod.loot.ModBlockLoot;
import net.dillon.speedrunnermod.loot.ModEntityLoot;
import net.dillon.speedrunnermod.loot.ModFloatProviders;
import net.dillon.speedrunnermod.loot.ModIntProviders;
import net.dillon.speedrunnermod.loot.providers.*;
import net.dillon.speedrunnermod.recipe.ModRecipeProvider;
import net.dillon.speedrunnermod.render.ModEquipmentAssetProvider;
import net.dillon.speedrunnermod.render.ModModelProviders;
import net.dillon.speedrunnermod.tag.*;
import net.dillon.speedrunnermod.villager.ModTradeSets;
import net.dillon.speedrunnermod.villager.ModTrades;
import net.dillon.speedrunnermod.world.biome.ModBiomes;
import net.dillon.speedrunnermod.world.feature.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

/**
 * Runs all Speedrunner Mod {@code data generators} and creates all respective {@code .json} files.
 */
public class SpeedrunnerModDataGenerator implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        final FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        // Basic Pack Providers
        pack.addProvider(ModAdvancementProvider::new);
        pack.addProvider((output, registriesFuture) -> new ModEquipmentAssetProvider(output));
        pack.addProvider(ModRecipeProvider::new);
        pack.addProvider(ModModelProviders::new);

        // Loot Providers
        pack.addProvider(ModBlockLoot::new);
        pack.addProvider(ModEntityLoot::new);
        pack.addProvider(ModBlockLootTables::new);
        pack.addProvider(ModArchaeologyLootTables::new);
        pack.addProvider(ModChestLootTables::new);
        pack.addProvider(ModFishingLootTables::new);
        pack.addProvider(ModPiglinBarterLootTables::new);
        pack.addProvider(ModShearingLootTables::new);

        // Dynamic Providers
        pack.addProvider(DynamicModRegistryProvider::new);

        // Tag Providers
        pack.addProvider(ModAttributeTags::new);
        pack.addProvider(ModBiomeTags::new);
        pack.addProvider(ModBlockItemTags::new);
        pack.addProvider(ModDamageTypeTags::new);
        pack.addProvider(ModEnchantmentTags::new);
        pack.addProvider(ModEntityTypeTags::new);
        pack.addProvider(ModFluidTags::new);
        pack.addProvider(ModItemTags::new);
        pack.addProvider(ModPointOfInterestTypeTags::new);
        pack.addProvider(ModPotionTags::new);
        pack.addProvider(ModStructureTags::new);
        pack.addProvider(ModTradeTags::new);
    }

    /**
     * Builds all {@code final} registries for Datagen.
     */
    @Override
    public void buildRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.BIOME, ModBiomes::bootstrap);
        registryBuilder.add(Registries.FEATURE, ModWorldFeatures::bootstrap);
        registryBuilder.add(Registries.PLACED_FEATURE, ModWorldPlacements::bootstrap);
        registryBuilder.add(Registries.MATERIAL_RULE, ModMaterialRules::bootstrap);
        registryBuilder.add(Registries.TEMPLATE_POOL, ModPools::bootstrap);
        registryBuilder.add(Registries.STRUCTURE, ModStructures::bootstrap);
        registryBuilder.add(Registries.STRUCTURE_SET, ModStructureSets::bootstrap);

        registryBuilder.add(Registries.ENCHANTMENT, ModEnchantments::bootstrap);
        registryBuilder.add(Registries.TRADE_SET, ModTradeSets::bootstrap);
        registryBuilder.add(Registries.VILLAGER_TRADE, ModTrades::bootstrap);
    }

    /**
     * Builds all {@code reloadable} registries for Datagen.
     */
    @Override
    public void buildReloadableRegistry(RegistrySetBuilder registryBuilder) {
        registryBuilder.add(Registries.CONTEXT_INT_PROVIDER, ModIntProviders::bootstrap);
        registryBuilder.add(Registries.CONTEXT_FLOAT_PROVIDER, ModFloatProviders::bootstrap);
    }
}