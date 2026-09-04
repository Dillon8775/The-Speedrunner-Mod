package net.dillon.speedrunnermod.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.material.Fluid;

import java.util.concurrent.CompletableFuture;

import static net.dillon.dillonlib.factory.Factories.createFluidTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code fluid tags.}
 */
public class ModFluidTags extends FabricTagsProvider.FluidTagsProvider {
    public static final TagKey<Fluid> BOAT_SAFE_FLUIDS = createFluidTag(ofSpeedrunnerMod("boat_safe_fluids"));

    public ModFluidTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider arg) {
        tag(ModFluidTags.BOAT_SAFE_FLUIDS)
                .addOptionalTag(FluidTags.WATER)
                .addOptionalTag(FluidTags.LAVA);
    }
}