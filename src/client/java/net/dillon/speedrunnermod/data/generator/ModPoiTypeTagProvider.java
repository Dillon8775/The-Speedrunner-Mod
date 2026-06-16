package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.villager.ModPoiTypes;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;

import java.util.concurrent.CompletableFuture;

/**
 * Generates files for all speedrunner mod {@code POI types,} which hold the custom villager's profession block.
 */
public class ModPoiTypeTagProvider extends FabricTagsProvider<PoiType> {

    protected ModPoiTypeTagProvider(final FabricPackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.POINT_OF_INTEREST_TYPE, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .addOptional(ModPoiTypes.RETIRED_SPEEDRUNNER);
    }
}