package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.world.biome.ModBiomes;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.concurrent.CompletableFuture;

/**
 * Creates all biome tags for the Speedrunner Mod.
 */
public class ModBiomeTags extends FabricTagsProvider<Biome> {

    /**
     * Constructs a new {@link FabricTagsProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output               the {@link FabricPackOutput} instance
     * @param registryLookupFuture the backing registry for the tag type
     */
    public ModBiomeTags(final FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.BIOME, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(BiomeTags.ALLOWS_SURFACE_SLIME_SPAWNS)
                .add(ModBiomes.SPEEDRUNNERS_WASTELAND);

        tag(BiomeTags.HAS_MINESHAFT)
                .add(ModBiomes.SPEEDRUNNERS_WASTELAND);

        tag(BiomeTags.HAS_RUINED_PORTAL_DESERT)
                .add(ModBiomes.SPEEDRUNNERS_WASTELAND);

        tag(BiomeTags.HAS_RUINED_PORTAL_MOUNTAIN)
                .add(ModBiomes.SPEEDRUNNERS_WASTELAND);

        tag(BiomeTags.HAS_RUINED_PORTAL_STANDARD)
                .add(ModBiomes.SPEEDRUNNERS_WASTELAND);

        tag(BiomeTags.HAS_VILLAGE_PLAINS)
                .add(ModBiomes.SPEEDRUNNERS_WASTELAND)
                .add(Biomes.SUNFLOWER_PLAINS)
                .add(Biomes.FOREST)
                .add(Biomes.FLOWER_FOREST)
                .add(Biomes.MEADOW);

        tag(BiomeTags.HAS_VILLAGE_TAIGA)
                .add(ModBiomes.SPEEDRUNNERS_WASTELAND)
                .add(Biomes.WINDSWEPT_HILLS);
    }
}