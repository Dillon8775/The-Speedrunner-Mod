package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;

@Environment(EnvType.CLIENT)
public class ExternalScreen extends AbstractModScreen {

    public ExternalScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_EXTERNAL);
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttons.add(0, ButtonWidget.builder(ModTexts.CURSEFORGE, (buttonWidget) -> {
            this.openLink(ModLinks.CURSEFORGE, false);
        }).build());

        this.buttons.add(1, ButtonWidget.builder(ModTexts.MODRINTH, (buttonWidget) -> {
            this.openLink(ModLinks.MODRINTH, false);
        }).build());

        this.buttons.add(2, ButtonWidget.builder(ModTexts.GITHUB, (buttonWidget) -> {
            this.openLink(ModLinks.GITHUB, false);
        }).build());

        this.buttons.add(3, ButtonWidget.builder(ModTexts.WEBPAGE, (buttonWidget) -> {
            this.openLink(ModLinks.WIKI, true);
        }).build());

        this.buttons.add(4, ButtonWidget.builder(ModTexts.YOUTUBE, (buttonWidget) -> {
            this.openLink(ModLinks.DILLON8775_YOUTUBE, true);
        }).build());

        this.buttons.add(5, ButtonWidget.builder(ModTexts.DISCORD, (buttonWidget) -> {
            this.openLink(ModLinks.DISCORD, false);
        }).build());

        this.buttons.add(6, ButtonWidget.builder(ModTexts.MOD_SHOWCASE_VIDEO, (buttonWidget) -> {
            this.openLink(ModLinks.MOD_SHOWCASE_VIDEO, true);
        }).build());

        super.init();
    }

    @Override
    protected String pageId() {
        return "3rij";
    }

    @Override
    protected int columns() {
        return 2;
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
        return true;
    }
}