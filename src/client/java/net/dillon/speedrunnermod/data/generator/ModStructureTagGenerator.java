package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.tag.ModStructureTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.concurrent.CompletableFuture;

/**
 * Generates all speedrunner mod {@code structure tags.}
 */
public class ModStructureTagGenerator extends FabricTagsProvider<Structure> {

    /**
     * Constructs a new {@link FabricTagsProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output               the {@link FabricPackOutput} instance
     * @param registryKey
     * @param registryLookupFuture the backing registry for the tag type
     */
    public ModStructureTagGenerator(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.STRUCTURE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModStructureTags.ANCIENT_CITIES)
                .add(BuiltinStructures.ANCIENT_CITY);

        tag(ModStructureTags.BASTIONS)
                .add(BuiltinStructures.BASTION_REMNANT);

        tag(ModStructureTags.DESERT_PYRAMIDS)
                .add(BuiltinStructures.DESERT_PYRAMID);

        tag(ModStructureTags.FORTRESSES)
                .add(BuiltinStructures.FORTRESS);

        tag(ModStructureTags.STRONGHOLDS)
                .add(BuiltinStructures.STRONGHOLD);

        tag(ModStructureTags.END_CITIES)
                .add(BuiltinStructures.END_CITY);

        tag(ModStructureTags.IGLOOS)
                .add(BuiltinStructures.IGLOO);

        tag(ModStructureTags.PILLAGER_OUTPOSTS)
                .add(BuiltinStructures.PILLAGER_OUTPOST);

        tag(ModStructureTags.TRAIL_RUINS)
                .add(BuiltinStructures.TRAIL_RUINS);
    }
}