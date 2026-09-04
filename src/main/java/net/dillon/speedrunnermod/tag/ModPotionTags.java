package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.component.ModPotionIds;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionIds;

import java.util.concurrent.CompletableFuture;

import static net.dillon.dillonlib.factory.Factories.createPotionTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod potion tags.
 */
public class ModPotionTags extends FabricTagsProvider<Potion> {
    public static final TagKey<Potion> RETIRED_SPEEDRUNNER_ARROW_EFFECTS = createPotionTag(ofSpeedrunnerMod("retired_speedrunner_arrow_effects"));
    public static final TagKey<Potion> RETIRED_SPEEDRUNNER_POTION_EFFECTS = createPotionTag(ofSpeedrunnerMod("retired_speedrunner_potion_effects"));
    public static final TagKey<Potion> DOOM_BLOCK_POTIONS = createPotionTag(ofSpeedrunnerMod("doom_block_potions"));

    public ModPotionTags(final FabricPackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.POTION, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        tag(ModPotionTags.RETIRED_SPEEDRUNNER_ARROW_EFFECTS)
                .add(PotionIds.LUCK)
                .add(PotionIds.HARMING)
                .add(ModPotionIds.WITHERED);

        tag(ModPotionTags.RETIRED_SPEEDRUNNER_POTION_EFFECTS)
                .add(PotionIds.LUCK)
                .add(ModPotionIds.WITHERED)
                .add(ModPotionIds.DRAGONS_AURA);

        tag(ModPotionTags.DOOM_BLOCK_POTIONS)
                .addOptionalTag(ModPotionTags.RETIRED_SPEEDRUNNER_ARROW_EFFECTS)
                .add(PotionIds.LONG_STRENGTH)
                .add(PotionIds.LONG_INVISIBILITY)
                .add(PotionIds.LONG_SWIFTNESS)
                .add(PotionIds.LONG_TURTLE_MASTER);
    }
}