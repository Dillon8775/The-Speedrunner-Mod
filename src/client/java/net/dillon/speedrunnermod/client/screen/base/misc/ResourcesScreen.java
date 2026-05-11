package net.dillon.speedrunnermod.client.screen.base.misc;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import java.util.List;

public class ResourcesScreen extends AbstractModScreen {
    private Button modsButton, questionsAndIssuesButton, tutorialsButton, showcaseVideoButton, releaseTrailerButton;

    public ResourcesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_RESOURCES);
    }

    @Override
    protected List<AbstractWidget> buttons() {
        return List.of(
                this.modsButton,
                this.questionsAndIssuesButton,
                this.tutorialsButton,
                this.showcaseVideoButton,
                this.releaseTrailerButton
        );
    }

    @Override
    protected void init() {
        this.modsButton = Button.builder(ModTexts.MENU_MODS, (button) -> {
            this.minecraft.setScreen(new ModsScreen(this.parent));
        }).build();

        this.questionsAndIssuesButton = Button.builder(ModTexts.QUESTIONS_AND_ISSUES, (button) -> {
            this.openLink(ModLinks.QUESTIONS_AND_ISSUES, true);
        }).build();

        this.tutorialsButton = Button.builder(ModTexts.MENU_TUTORIALS, (button) -> {
            this.minecraft.setScreen(new TutorialsScreen(this.parent));
        }).build();

        this.showcaseVideoButton = Button.builder(ModTexts.MOD_SHOWCASE_VIDEO, (buttonWidget) -> {
            this.openLink(ModLinks.SHOWCASE_VIDEO, true);
        }).build();

        this.releaseTrailerButton = Button.builder(ModTexts.MOD_RELEASE_TRAILER, (buttonWidget) -> {
            this.openLink(ModLinks.RELEASE_TRAILER, true);
        }).build();

        super.init();
    }

    @Override
    public String pageId() {
        return "t09efi0z";
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