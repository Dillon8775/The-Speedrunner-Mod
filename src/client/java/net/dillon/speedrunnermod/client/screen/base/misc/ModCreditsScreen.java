package net.dillon.speedrunnermod.client.screen.base.misc;

import net.dillon.speedrunnermod.client.screen.base.AbstractScrollableScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

/**
 * The credits screen for the Speedrunner Mod, rendering a custom credits image.
 */
@Environment(EnvType.CLIENT)
public class ModCreditsScreen extends AbstractScrollableScreen {

    public ModCreditsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_CREDITS);
    }

    @Override
    protected String getTextFile() {
        return this.inTextsFolder("credits");
    }

    @Override
    public String pageId() {
        return "fxgnhfdyrtsdrtseser";
    }
}