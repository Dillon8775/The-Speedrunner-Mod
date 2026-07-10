package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.component.ModPotionIds;
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
        tag(ModPotionsTags.RETIRED_SPEEDRUNNER_ARROW_EFFECTS)
                .add(PotionIds.LUCK)
                .add(PotionIds.HARMING)
                .add(ModPotionIds.WITHERED);

        tag(ModPotionsTags.RETIRED_SPEEDRUNNER_POTION_EFFECTS)
                .add(PotionIds.LUCK)
                .add(ModPotionIds.WITHERED)
                .add(ModPotionIds.DRAGONS_AURA);

        tag(ModPotionsTags.DOOM_BLOCK_POTIONS)
                .addOptionalTag(ModPotionsTags.RETIRED_SPEEDRUNNER_ARROW_EFFECTS)
                .add(PotionIds.LONG_STRENGTH)
                .add(PotionIds.LONG_INVISIBILITY)
                .add(PotionIds.LONG_SWIFTNESS)
                .add(PotionIds.LONG_TURTLE_MASTER);
    }
}