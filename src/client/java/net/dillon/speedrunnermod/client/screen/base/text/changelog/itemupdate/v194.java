package net.dillon.speedrunnermod.client.screen.base.text.changelog.itemupdate;

import net.dillon.speedrunnermod.client.screen.base.text.AbstractChangelogScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class v194 extends AbstractChangelogScreen {

    public v194(Screen parent, GameOptions options) {
        super(parent, options, Text.literal("v1.9.4 Changelog"));
    }

    @Override
    protected String getTextFile() {
        return this.inChangelogsFolder("1.9.4");
    }

    @Override
    protected String pageId() {
        return "fghjriuowjuw";
    }
}