package net.dillon.speedrunnermod.client.data.generator;

import net.dillon.speedrunnermod.villager.ModPoiTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.KeyTagProvider;
import net.minecraft.tags.PoiTypeTags;
import net.minecraft.world.entity.ai.village.poi.PoiType;

import java.util.concurrent.CompletableFuture;

/**
 * Generates files for all speedrunner mod {@code POI types,} which hold the custom villager's profession block.
 */
public class ModPoiTypeTagGenerator extends KeyTagProvider<PoiType> {

    protected ModPoiTypeTagGenerator(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.POINT_OF_INTEREST_TYPE, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(PoiTypeTags.ACQUIRABLE_JOB_SITE)
                .addOptional(ModPoiTypes.RETIRED_SPEEDRUNNER);
    }
}