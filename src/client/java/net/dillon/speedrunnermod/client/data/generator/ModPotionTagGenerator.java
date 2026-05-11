package net.dillon.speedrunnermod.client.data.generator;

import net.dillon.speedrunnermod.tag.ModPotionsTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.HolderTagProvider;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.Potions;

import java.util.concurrent.CompletableFuture;

/**
 * Generates all potion tag files for the speedrunner mod.
 */
public class ModPotionTagGenerator extends HolderTagProvider<Potion> {

    public ModPotionTagGenerator(final PackOutput output, final CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, Registries.POTION, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        this.tag(ModPotionsTags.RETIRED_SPEEDRUNNER_POTIONS)
                .add(Potions.STRENGTH)
                .add(Potions.LONG_STRENGTH)
                .add(Potions.STRONG_STRENGTH)
                .add(Potions.STRONG_REGENERATION)
                .add(Potions.LONG_FIRE_RESISTANCE)
                .add(Potions.INVISIBILITY)
                .add(Potions.LONG_INVISIBILITY)
                .add(Potions.STRONG_HARMING);
    }
}