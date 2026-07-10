package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.damagesource.DamageType;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All Speedrunner Mod {@code damage type tags.}
 */
public class ModDamageTypeTags {
    public static final TagKey<DamageType> ALLOWED_ZOMBIE_MINION_DAMAGE_TYPES = createDamageTypeTag("allowed_zombie_minion_damage_types");

    /**
     * Registers a {@code damage type tag.}
     */
    protected static TagKey<DamageType> createDamageTypeTag(String path) {
        return TagKey.create(Registries.DAMAGE_TYPE, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code damage type tags.}
     */
    public static void initializeDamageTypeTags() {
        SpeedrunnerMod.debug("Initialized damage type tags.");
    }
}