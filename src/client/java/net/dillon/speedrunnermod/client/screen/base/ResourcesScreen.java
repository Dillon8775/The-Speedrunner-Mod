package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;

import java.util.List;

@Environment(EnvType.CLIENT)
public class ResourcesScreen extends AbstractModScreen {
    private ButtonWidget modsButton, questionsAndIssuesButton, suggestionsAndFeedbackButton, tutorialsButton, modShowcaseVideoButton;

    public ResourcesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_RESOURCES);
    }

    @Override
    protected List<ClickableWidget> buttons() {
        return List.of(
             this.modsButton,
             this.questionsAndIssuesButton,
             this.suggestionsAndFeedbackButton,
             this.tutorialsButton,
             this.modShowcaseVideoButton
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

        this.suggestionsAndFeedbackButton = ButtonWidget.builder(ModTexts.SUGGESTIONS_AND_FEEDBACK, (button) -> {
            this.openLink(ModLinks.SUGGESTIONS_AND_FEEDBACK, true);
        }).build();

        this.tutorialsButton = ButtonWidget.builder(ModTexts.MENU_TUTORIALS, (button) -> {
            this.client.setScreen(new TutorialsScreen(this.parent));
        }).build();

        this.modShowcaseVideoButton = ButtonWidget.builder(ModTexts.MOD_SHOWCASE_VIDEO, (buttonWidget) -> {
            this.openLink(ModLinks.RELEASE_TRAILER, true);
        }).build();

        super.init();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.questionsAndIssuesButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.QUESTIONS_AND_ISSUES_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.suggestionsAndFeedbackButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.SUGGESTIONS_AND_FEEDBACK_TOOLTIP, context, mouseX, mouseY);
        }
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