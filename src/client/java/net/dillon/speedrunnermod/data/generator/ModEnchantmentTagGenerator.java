package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.tag.ModEnchantmentTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.data.tag.EnchantmentTagProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.registry.RegistryWrapper;

import java.util.concurrent.CompletableFuture;

/**
 * Contains all the entries for new or already existing enchantment tags.
 */
public class ModEnchantmentTagGenerator extends EnchantmentTagProvider {

    public ModEnchantmentTagGenerator(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        this.builder(ModEnchantmentTags.RETIRED_SPEEDRUNNER_TRADES)
                .add(Enchantments.PROTECTION)
                .add(Enchantments.BLAST_PROTECTION)
                .add(Enchantments.FEATHER_FALLING)
                .add(Enchantments.THORNS)
                .add(Enchantments.SHARPNESS)
                .add(Enchantments.FIRE_ASPECT)
                .add(Enchantments.LOOTING)
                .add(Enchantments.FORTUNE)
                .add(Enchantments.POWER)
                .add(Enchantments.INFINITY)
                .add(Enchantments.MENDING)
                .addOptional(ModEnchantments.COOLDOWN);
        this.builder(ModEnchantmentTags.WITHERED_ENCHANTMENTS)
                .addOptional(ModEnchantments.WITHERED);
    }
}