package net.dillon.speedrunnermod.tag;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;

import java.util.concurrent.CompletableFuture;

import static net.dillon.dillonlib.factory.Factories.createDamageTypeTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code damage type tags.}
 */
public class ModDamageTypeTags extends FabricTagsProvider<DamageType> {
    public static final TagKey<DamageType> ALLOWED_ZOMBIE_MINION_DAMAGE_TYPES = createDamageTypeTag(ofSpeedrunnerMod("allowed_zombie_minion_damage_types"));

    /**
     * Constructs a new {@link FabricTagsProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output               the {@link FabricPackOutput} instance
     * @param registryLookupFuture the backing registry for the tag type
     */
    public ModDamageTypeTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.DAMAGE_TYPE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModDamageTypeTags.ALLOWED_ZOMBIE_MINION_DAMAGE_TYPES)
                .add(DamageTypes.PLAYER_ATTACK)
                .add(DamageTypes.FELL_OUT_OF_WORLD);
    }
}