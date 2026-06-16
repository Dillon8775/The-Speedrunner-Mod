package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.tag.ModPotionsTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionIds;

import java.util.concurrent.CompletableFuture;

/**
 * Generates all potion tag files for the speedrunner mod.
 */
public class ModPotionTagProvider extends FabricTagsProvider<Potion> {

    public ModPotionTagProvider(final FabricPackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.POTION, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(ModPotionsTags.RETIRED_SPEEDRUNNER_POTIONS)
                .add(PotionIds.STRENGTH)
                .add(PotionIds.LONG_STRENGTH)
                .add(PotionIds.STRONG_STRENGTH)
                .add(PotionIds.STRONG_REGENERATION)
                .add(PotionIds.LONG_FIRE_RESISTANCE)
                .add(PotionIds.INVISIBILITY)
                .add(PotionIds.LONG_INVISIBILITY)
                .add(PotionIds.STRONG_HARMING);
    }
}