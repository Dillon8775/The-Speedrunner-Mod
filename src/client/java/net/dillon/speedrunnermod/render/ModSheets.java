package net.dillon.speedrunnermod.render;

import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.sprite.SpriteId;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * Sheets for special renderers.
 */
public class ModSheets {
    protected static final SpriteId SPEEDRUNNER_SHIELD_BASE = Sheets.SHIELD_MAPPER.apply(ofSpeedrunnerMod("peedrunner_shield_base"));
    protected static final SpriteId SPEEDRUNNER_SHIELD_BASE_NO_PATTERN = Sheets.SHIELD_MAPPER.apply(ofSpeedrunnerMod("speedrunner_shield_base_no_pattern"));
    protected static final SpriteId GOLDEN_SHIELD_BASE = Sheets.SHIELD_MAPPER.apply(ofSpeedrunnerMod("golden_shield_base"));
    protected static final SpriteId GOLDEN_SHIELD_BASE_NO_PATTERN = Sheets.SHIELD_MAPPER.apply(ofSpeedrunnerMod("golden_shield_base_no_pattern"));
}