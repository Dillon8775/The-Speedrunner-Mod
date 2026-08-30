package net.dillon.speedrunnermod.tag;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.attributes.Attribute;

import static net.dillon.dillonlib.factory.Factories.createAttributeTag;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * All speedrunner mod attribute tags.
 */
public class ModAttributeTags {
    public static final TagKey<Attribute> WHEN_SHOT = createAttributeTag(ofSpeedrunnerMod("when_shot"));
    public static final TagKey<Attribute> WHEN_THROWN = createAttributeTag(ofSpeedrunnerMod("when_thrown"));
    public static final TagKey<Attribute> WHEN_MINING = createAttributeTag(ofSpeedrunnerMod("when_mining"));
    public static final TagKey<Attribute> WHEN_RIDDEN = createAttributeTag(ofSpeedrunnerMod("when_ridden"));
    public static final TagKey<Attribute> UPON_DEATH = createAttributeTag(ofSpeedrunnerMod("upon_death"));

    /**
     * Initializes all Speedrunner Mod {@code attribute tags.}
     */
    public static void initializeAttributeTags() {
        SpeedrunnerMod.LOGGER.debug("Initialized attribute tags.");
    }
}