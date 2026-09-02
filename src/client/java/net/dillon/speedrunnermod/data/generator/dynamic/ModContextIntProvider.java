package net.dillon.speedrunnermod.data.generator.dynamic;


import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import java.util.concurrent.CompletableFuture;

/**
 * Holds all speedrunner mod number providers.
 */
public class ModContextIntProvider extends FabricDynamicRegistryProvider {

    public ModContextIntProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.CONTEXT_INT_PROVIDER));
    }

    @Override
    public String getName() {
        return "Speedrunner Mod Context Int Provider";
    }
}