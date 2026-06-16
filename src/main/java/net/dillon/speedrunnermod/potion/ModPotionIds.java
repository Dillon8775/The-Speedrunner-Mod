package net.dillon.speedrunnermod.potion;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.alchemy.Potion;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod potion ids.
 */
public class ModPotionIds {
    public static final ResourceKey<Potion> DRAGONS_AURA = create("dragons_aura");
    public static final ResourceKey<Potion> LONG_DRAGONS_AURA = create("long_dragons_aura");

    /**
     * Creates a {@code potion id.}
     */
    private static ResourceKey<Potion> create(final String name) {
        return ResourceKey.create(Registries.POTION, ofSpeedrunnerMod(name));
    }
}