package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attribute;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod attribute tags.
 */
public class ModAttributeTags {
    public static final TagKey<Attribute> WHEN_SHOT = createAttributeTag("when_shot");
    public static final TagKey<Attribute> WHEN_THROWN = createAttributeTag("when_thrown");
    public static final TagKey<Attribute> WHEN_MINING = createAttributeTag("when_mining");
    public static final TagKey<Attribute> WHEN_RIDDEN = createAttributeTag("when_ridden");
    public static final TagKey<Attribute> UPON_DEATH = createAttributeTag("upon_death");

    /**
     * Registers a {@code attribute tag.}
     */
    private static TagKey<Attribute> createAttributeTag(String path) {
        return TagKey.create(Registries.ATTRIBUTE, ofSpeedrunnerMod(path));
    }

    /**
     * Initializes all Speedrunner Mod {@code attribute tags.}
     */
    public static void initializeAttributeTags() {
        SpeedrunnerMod.debug("Initialized attribute tags.");
    }
}