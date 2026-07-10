package net.dillon.speedrunnermod.data.generator.dynamic;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;

import java.util.concurrent.CompletableFuture;

/**
 * Used to create the JSON files for the Speedrunner Mod enchantments.
 */
public class ModEnchantmentProvider extends FabricDynamicRegistryProvider {

    public ModEnchantmentProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        entries.addAll(registries.lookupOrThrow(Registries.ENCHANTMENT));
    }

    @Override
    public String getName() {
        return "Speedrunner Mod Enchantments";
    }
}