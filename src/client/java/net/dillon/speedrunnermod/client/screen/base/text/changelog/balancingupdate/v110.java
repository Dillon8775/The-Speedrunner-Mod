package net.dillon.speedrunnermod.client.screen.base.text.changelog.balancingupdate;

import net.dillon.speedrunnermod.client.screen.base.text.AbstractScrollableTextScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

@Environment(EnvType.CLIENT)
public class v110 extends AbstractScrollableTextScreen {

    public v110(Screen parent, GameOptions options) {
        super(parent, options, Text.literal("v1.10 Changelog").formatted(Formatting.GREEN));
    }

    @Override
    protected String getTextFile() {
        return this.inChangelogsFolder("1.10");
    }

    @Override
    protected String pageId() {
        return "gfbopkoasdoasd";
    }
}