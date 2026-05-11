package net.dillon.speedrunnermod.client.screen.base.misc;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import java.util.List;

public class ExternalScreen extends AbstractModScreen {
    private Button curseForgeButton, modrinthButton, githubButton;

    public ExternalScreen(Screen parent) {
        super(parent, ModTexts.TITLE_EXTERNAL);
    }

    @Override
    protected List<AbstractWidget> buttons() {
        return List.of(
                this.curseForgeButton,
                this.modrinthButton,
                this.githubButton
        );
    }

    @Override
    protected void init() {
        this.curseForgeButton = Button.builder(ModTexts.CURSEFORGE, (buttonWidget) -> {
            this.openLink(ModLinks.CURSEFORGE, false);
        }).build();

        this.modrinthButton = Button.builder(ModTexts.MODRINTH, (buttonWidget) -> {
            this.openLink(ModLinks.MODRINTH, false);
        }).build();

        this.githubButton = Button.builder(ModTexts.GITHUB, (buttonWidget) -> {
            this.openLink(ModLinks.GITHUB, false);
        }).build();

        super.init();
    }

    @Override
    public String pageId() {
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
    public boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}