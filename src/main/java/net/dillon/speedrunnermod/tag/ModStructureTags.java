package net.dillon.speedrunnermod.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;

import java.util.concurrent.CompletableFuture;

import static net.dillon.dillonlib.factory.Factories.createStructureTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code structure tags.} These are only really used because vanilla Minecraft doesn't have a tag for these structures.
 */
public class ModStructureTags extends FabricTagsProvider<Structure> {
    public static final TagKey<Structure> ANCIENT_CITIES = createStructureTag(ofSpeedrunnerMod("ancient_cities"));
    public static final TagKey<Structure> BASTIONS = createStructureTag(ofSpeedrunnerMod("bastions"));
    public static final TagKey<Structure> DESERT_PYRAMIDS = createStructureTag(ofSpeedrunnerMod("desert_pyramids"));
    public static final TagKey<Structure> FORTRESSES = createStructureTag(ofSpeedrunnerMod("fortresses"));
    public static final TagKey<Structure> STRONGHOLDS = createStructureTag(ofSpeedrunnerMod("strongholds"));
    public static final TagKey<Structure> END_CITIES = createStructureTag(ofSpeedrunnerMod("end_cities"));
    public static final TagKey<Structure> IGLOOS = createStructureTag(ofSpeedrunnerMod("igloos"));
    public static final TagKey<Structure> PILLAGER_OUTPOSTS = createStructureTag(ofSpeedrunnerMod("pillager_outposts"));
    public static final TagKey<Structure> TRAIL_RUINS = createStructureTag(ofSpeedrunnerMod("trail_ruins"));

    /**
     * Constructs a new {@link FabricTagsProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output               the {@link FabricPackOutput} instance
     * @param registryLookupFuture the backing registry for the tag type
     */
    public ModStructureTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
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