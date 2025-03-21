package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;

@Environment(EnvType.CLIENT)
public class ResourcesScreen extends AbstractModScreen {

    public ResourcesScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_RESOURCES);
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttons.add(0, ButtonWidget.builder(ModTexts.MENU_MODS, (button) -> {
            this.client.setScreen(new ModsScreen(this.parent, MinecraftClient.getInstance().options));
        }).build());

        this.buttons.add(1, ButtonWidget.builder(ModTexts.QUESTIONS_AND_ISSUES, (button) -> {
            this.openLink(ModLinks.QUESTIONS_AND_ISSUES, true);
        }).build());

        this.buttons.add(2, ButtonWidget.builder(ModTexts.MENU_WIKI, (button) -> {
            this.openLink(ModLinks.WIKI, true);
        }).build());

        this.buttons.add(3, ButtonWidget.builder(ModTexts.SUGGESTIONS_AND_FEEDBACK, (button) -> {
            this.openLink(ModLinks.SUGGESTIONS_AND_FEEDBACK, true);
        }).build());

        this.buttons.add(4, ButtonWidget.builder(ModTexts.MENU_TUTORIALS, (button) -> {
            this.client.setScreen(new TutorialsScreen(this.parent, MinecraftClient.getInstance().options));
        }).build());

        super.init();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.buttons.get(1).isHovered()) {
            this.renderBasicTooltip(ModTexts.QUESTIONS_AND_ISSUES_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(3).isHovered()) {
            this.renderBasicTooltip(ModTexts.SUGGESTIONS_AND_FEEDBACK_TOOLTIP, context, mouseX, mouseY);
        }
    }

    @Override
    protected String pageId() {
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
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}