package net.dillon.speedrunnermod.data.generator;

import net.dillon.speedrunnermod.component.ModAttributeKeys;
import net.dillon.speedrunnermod.tag.ModAttributeTags;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.entity.ai.attributes.Attribute;

import java.util.concurrent.CompletableFuture;

/**
 * Generates attribute tags.
 */
public class ModAttributeTagProvider extends FabricTagsProvider<Attribute> {

    /**
     * Constructs a new {@link FabricTagsProvider} with the default computed path.
     *
     * <p>Common implementations of this class are provided.
     *
     * @param output               the {@link FabricPackOutput} instance
     * @param registryLookupFuture the backing registry for the tag type
     */
    public ModAttributeTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, Registries.ATTRIBUTE, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModAttributeTags.WHEN_SHOT)
                .addOptional(ModAttributeKeys.ADDITIONAL_BOW_POWER);

        tag(ModAttributeTags.WHEN_THROWN)
                .addOptional(ModAttributeKeys.ADDITIONAL_RANGE)
                .addOptional(ModAttributeKeys.ADDITIONAL_INERTIA)
                .addOptional(ModAttributeKeys.ADDITIONAL_TARGET_DAMAGE);

        tag(ModAttributeTags.WHEN_MINING)
                .addOptional(ModAttributeKeys.DOOM_BLOCK_PROTECTION);

        tag(ModAttributeTags.WHEN_RIDDEN)
                .addOptional(ModAttributeKeys.ADDITIONAL_BOAT_MOVEMENT_SPEED)
                .addOptional(ModAttributeKeys.LAVA_INVULNERABILITY);

        tag(ModAttributeTags.UPON_DEATH)
                .addOptional(ModAttributeKeys.INVENTORY_PRESERVATION);
    }
}