package net.dillon.speedrunnermod.client.screen.base.text.changelog.itemupdate;

import net.dillon.speedrunnermod.client.screen.base.text.AbstractScrollableTextScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class v195 extends AbstractScrollableTextScreen {

    public v195(Screen parent, GameOptions options) {
        super(parent, options, Text.literal("v1.9.5 Changelog"));
    }

    @Override
    protected String getTextFile() {
        return this.inChangelogsFolder("1.9.5");
    }

    @Override
    protected String pageId() {
        return "tgjodofjsodja";
    }
}