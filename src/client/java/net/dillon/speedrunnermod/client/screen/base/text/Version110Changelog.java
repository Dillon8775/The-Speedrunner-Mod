package net.dillon.speedrunnermod.client.screen.base.text;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class Version110Changelog extends AbstractScrollableTextScreen {

    public Version110Changelog(Screen parent, GameOptions options) {
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

    @Override
    protected int columns() {
        return 0;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return false;
    }
}