package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.tag.ModDamageTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

import java.util.concurrent.CompletableFuture;

/**
 * Generates files for damage type tags.
 */
public class ModDamageTypeTagProvider extends FabricTagsProvider<DamageType> {

    /**
     * Constructs a new {@link FabricTagsProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output               the {@link FabricPackOutput} instance
     * @param registryLookupFuture the backing registry for the tag type
     */
    public ModDamageTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.DAMAGE_TYPE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModDamageTypeTags.ALLOWED_ZOMBIE_MINION_DAMAGE_TYPES)
                .add(DamageTypes.PLAYER_ATTACK)
                .add(DamageTypes.FELL_OUT_OF_WORLD);
    }
}