package net.dillon.speedrunnermod.client.screen.base.text.changelog.itemupdate;

import net.dillon.speedrunnermod.client.screen.base.text.AbstractChangelogScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class v1962 extends AbstractChangelogScreen {

    public v1962(Screen parent) {
        super(parent, Text.literal("v1.9.6.2 Changelog"));
    }

    @Override
    protected String getTextFile() {
        return this.inChangelogsFolder("1.9.6.2");
    }

    @Override
    protected String pageId() {
        return "eogjeiuow9ifsic";
    }
}