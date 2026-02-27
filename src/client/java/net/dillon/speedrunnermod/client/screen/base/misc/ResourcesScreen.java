package net.dillon.speedrunnermod.client.screen.base.misc;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModLinks;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;

import java.util.List;

public class ResourcesScreen extends AbstractModScreen {
    private ButtonWidget modsButton, questionsAndIssuesButton, tutorialsButton, showcaseVideoButton, releaseTrailerButton;

    public ResourcesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_RESOURCES);
    }

    @Override
    protected List<ClickableWidget> buttons() {
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
        this.modsButton = ButtonWidget.builder(ModTexts.MENU_MODS, (button) -> {
            this.client.setScreen(new ModsScreen(this.parent));
        }).build();

        this.questionsAndIssuesButton = ButtonWidget.builder(ModTexts.QUESTIONS_AND_ISSUES, (button) -> {
            this.openLink(ModLinks.QUESTIONS_AND_ISSUES, true);
        }).build();

        this.tutorialsButton = ButtonWidget.builder(ModTexts.MENU_TUTORIALS, (button) -> {
            this.client.setScreen(new TutorialsScreen(this.parent));
        }).build();

        this.showcaseVideoButton = ButtonWidget.builder(ModTexts.MOD_SHOWCASE_VIDEO, (buttonWidget) -> {
            this.openLink(ModLinks.SHOWCASE_VIDEO, true);
        }).build();

        this.releaseTrailerButton = ButtonWidget.builder(ModTexts.MOD_RELEASE_TRAILER, (buttonWidget) -> {
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