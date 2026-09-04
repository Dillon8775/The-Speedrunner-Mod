package net.dillon.speedrunnermod.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import java.util.concurrent.CompletableFuture;

/**
 * Creates all dynamic registry providers.
 */
public class DynamicModRegistryProvider extends FabricDynamicRegistryProvider {

    public DynamicModRegistryProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.CONTEXT_INT_PROVIDER));

        entries.addAll(registries.lookupOrThrow(Registries.BIOME));
        entries.addAll(registries.lookupOrThrow(Registries.FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.PLACED_FEATURE));
        entries.addAll(registries.lookupOrThrow(Registries.STRUCTURE)); // Unused right now
        entries.addAll(registries.lookupOrThrow(Registries.STRUCTURE_SET)); // Unused right now
        entries.addAll(registries.lookupOrThrow(Registries.TEMPLATE_POOL)); // Unused right now

        entries.addAll(registries.lookupOrThrow(Registries.ENCHANTMENT));
        entries.addAll(registries.lookupOrThrow(Registries.VILLAGER_TRADE));
        entries.addAll(registries.lookupOrThrow(Registries.TRADE_SET));
    }

    @Override
    public String getName() {
        return "Dynamic Speedrunner Mod Registry Provider";
    }
}