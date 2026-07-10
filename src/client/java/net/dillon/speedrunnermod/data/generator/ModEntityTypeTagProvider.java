package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.tag.ModEntityTypeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypeIds;

import java.util.concurrent.CompletableFuture;

/**
 * Contains the entries of all new speedrunner mod entity type tags.
 */
public class ModEntityTypeTagProvider extends FabricTagsProvider<EntityType<?>> {

    /**
     * Constructs a new {@link FabricTagsProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output               the {@link FabricPackOutput} instance
     * @param registryLookupFuture the backing registry for the tag type
     */
    public ModEntityTypeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.ENTITY_TYPE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModEntityTypeTags.BLACKLISTED_WITHER_TARGET_MOBS)
                .add(EntityTypeIds.ENDER_DRAGON)
                .add(EntityTypeIds.GIANT);

        tag(ModEntityTypeTags.BLACKLISTED_MINION_CALL_MOBS)
                .add(EntityTypeIds.ENDERMAN);

        tag(ModEntityTypeTags.BLACKLISTED_ENDER_DRAGON_KILL_MOBS)
                .add(EntityTypeIds.ENDERMAN);

        tag(ModEntityTypeTags.SPARE_ME_ADVANCEMENT_MOBS)
                .add(EntityTypeIds.ZOMBIE)
                .add(EntityTypeIds.ZOMBIE_VILLAGER)
                .add(EntityTypeIds.DROWNED)
                .add(EntityTypeIds.HUSK);

        tag(ModEntityTypeTags.SPEEDRUNNER_IMPERATIVE_MOBS)
                .add(EntityTypeIds.BLAZE)
                .add(EntityTypeIds.ZOMBIE_VILLAGER)
                .add(EntityTypeIds.ENDERMAN)
                .add(EntityTypeIds.PIGLIN)
                .add(EntityTypeIds.PIGLIN_BRUTE)
                .add(EntityTypeIds.GIANT);

        tag(ModEntityTypeTags.GOLIATH_IMMUNE_MOBS)
                .add(EntityTypeIds.IRON_GOLEM)
                .add(EntityTypeIds.RAVAGER)
                .add(EntityTypeIds.VINDICATOR)
                .add(EntityTypeIds.ZOMBIE)
                .add(EntityTypeIds.ZOMBIE_VILLAGER)
                .add(EntityTypeIds.ZOMBIFIED_PIGLIN)
                .add(EntityTypeIds.VEX)
                .add(EntityTypeIds.EVOKER)
                .add(EntityTypeIds.EVOKER_FANGS)
                .add(EntityTypeIds.WARDEN)
                .add(EntityTypeIds.WITHER_SKULL)
                .add(EntityTypeIds.WITHER)
                .add(EntityTypeIds.ENDERMAN)
                .add(EntityTypeIds.ENDER_DRAGON)
                .add(EntityTypeIds.AREA_EFFECT_CLOUD);

        tag(ModEntityTypeTags.WITHERED_EFFECTED_MOBS)
                .add(EntityTypeIds.WITHER_SKELETON);
    }
}