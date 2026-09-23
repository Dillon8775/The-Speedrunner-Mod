package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.entity.ModEntityTypeIds;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityTypeIds;

import java.util.concurrent.CompletableFuture;

import static net.dillon.dillonlib.factory.Factories.createEntityTypeTag;
import static net.dillon.speedrunnermod.main.CommonMain.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code entity type tags.}
 */
public class ModEntityTypeTags extends FabricTagsProvider<EntityType<?>> {
    public static final TagKey<EntityType<?>> FIREPROOF_BOATS = createEntityTypeTag(ofSpeedrunnerMod("fireproof_boats"));
    public static final TagKey<EntityType<?>> FAST_BOATS = createEntityTypeTag(ofSpeedrunnerMod("fast_boats"));
    public static final TagKey<EntityType<?>> SPEEDRUNNER_IMPERATIVE = createEntityTypeTag(ofSpeedrunnerMod("speedrunner_imperative"));
    public static final TagKey<EntityType<?>> GOLIATH_IMMUNE = createEntityTypeTag(ofSpeedrunnerMod("goliath_immune"));
    public static final TagKey<EntityType<?>> WITHERED_EFFECTED = createEntityTypeTag(ofSpeedrunnerMod("withered_effected"));
    public static final TagKey<EntityType<?>> BLACKLISTED_WITHER_TARGETS = createEntityTypeTag(ofSpeedrunnerMod("blacklisted_wither_targets"));
    public static final TagKey<EntityType<?>> BLACKLISTED_FROM_MINION_CALL = createEntityTypeTag(ofSpeedrunnerMod("blacklisted_from_minion_call"));
    public static final TagKey<EntityType<?>> BLACKLISTED_FROM_ENDER_DRAGON_DEATH = createEntityTypeTag(ofSpeedrunnerMod("blacklisted_from_ender_dragon_death"));
    public static final TagKey<EntityType<?>> SPARE_ME_ADVANCEMENT_ENTITIES = createEntityTypeTag(ofSpeedrunnerMod("spare_me_advancement_entities"));

    /**
     * Constructs a new {@link FabricTagsProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output               the {@link FabricPackOutput} instance
     * @param registryLookupFuture the backing registry for the tag type
     */
    public ModEntityTypeTags(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.ENTITY_TYPE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModEntityTypeTags.FIREPROOF_BOATS)
                .add(ModEntityTypeIds.SPEEDRUNNER_BOAT)
                .add(ModEntityTypeIds.SPEEDRUNNER_CHEST_BOAT)
                .add(ModEntityTypeIds.CRIMSON_BOAT)
                .add(ModEntityTypeIds.CRIMSON_CHEST_BOAT)
                .add(ModEntityTypeIds.WARPED_BOAT)
                .add(ModEntityTypeIds.WARPED_CHEST_BOAT);

        tag(ModEntityTypeTags.FAST_BOATS)
                .add(ModEntityTypeIds.SPEEDRUNNER_BOAT)
                .add(ModEntityTypeIds.SPEEDRUNNER_CHEST_BOAT);

        tag(ModEntityTypeTags.SPEEDRUNNER_IMPERATIVE)
                .add(EntityTypeIds.BLAZE)
                .add(EntityTypeIds.ZOMBIE_VILLAGER)
                .add(EntityTypeIds.ENDERMAN)
                .add(EntityTypeIds.PIGLIN)
                .add(EntityTypeIds.PIGLIN_BRUTE)
                .add(EntityTypeIds.FIREBALL)
                .add(EntityTypeIds.SMALL_FIREBALL)
                .add(EntityTypeIds.DRAGON_FIREBALL)
                .add(EntityTypeIds.GIANT);

        tag(ModEntityTypeTags.GOLIATH_IMMUNE)
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

        tag(ModEntityTypeTags.WITHERED_EFFECTED)
                .add(EntityTypeIds.WITHER_SKELETON);

        tag(ModEntityTypeTags.BLACKLISTED_WITHER_TARGETS)
                .add(EntityTypeIds.ENDER_DRAGON)
                .add(EntityTypeIds.GIANT);

        tag(ModEntityTypeTags.BLACKLISTED_FROM_MINION_CALL)
                .add(EntityTypeIds.ENDERMAN);

        tag(ModEntityTypeTags.BLACKLISTED_FROM_ENDER_DRAGON_DEATH)
                .add(EntityTypeIds.ENDERMAN);

        tag(ModEntityTypeTags.SPARE_ME_ADVANCEMENT_ENTITIES)
                .add(EntityTypeIds.ZOMBIE)
                .add(EntityTypeIds.ZOMBIE_VILLAGER)
                .add(EntityTypeIds.DROWNED)
                .add(EntityTypeIds.HUSK);
    }
}