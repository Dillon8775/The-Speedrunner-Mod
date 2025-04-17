package net.dillon.speedrunnermod.client.screen.base.text.changelog.balancingupdate;

import net.dillon.speedrunnermod.client.screen.base.text.AbstractChangelogScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Environment(EnvType.CLIENT)
public class v110 extends AbstractChangelogScreen {

    public v110(Screen parent) {
        super(parent, Text.literal("v1.10 Changelog").formatted(Formatting.GREEN));
    }

    @Override
    protected String getTextFile() {
        return this.inChangelogsFolder("1.10");
    }

    @Override
    protected String pageId() {
        return "gfbopkoasdoasd";
    }

    @Override
    protected boolean hasChangelogFile() {
        return true;
    }
}