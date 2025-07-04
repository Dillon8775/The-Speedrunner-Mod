package net.dillon.speedrunnermod.client.screen.feature;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

/**
 * Determines the category of a {@code feature screen,} which also determines the maximum page number.
 */
@Environment(EnvType.CLIENT)
public enum ScreenCategory {
    BLOCKS_AND_ITEMS,
    TOOLS_AND_ARMOR,
    ORES_AND_WORLDGEN,
    MISCELLANEOUS,
    DOOM_MODE,
    SECRET_DOOM_MODE,
    FIRST_TIME_PLAYING
}