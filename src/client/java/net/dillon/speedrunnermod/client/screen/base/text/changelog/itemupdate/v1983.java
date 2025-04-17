package net.dillon.speedrunnermod.client.screen.base.text.changelog.itemupdate;

import net.dillon.speedrunnermod.client.screen.base.text.AbstractChangelogScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class v1983 extends AbstractChangelogScreen {

    public v1983(Screen parent) {
        super(parent, Text.literal("v1.9.8.3 Changelog"));
    }

    @Override
    protected String getTextFile() {
        return this.inChangelogsFolder("1.9.8.3");
    }

    @Override
    protected String pageId() {
        return "dgjojodfijodf";
    }
}