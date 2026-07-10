package net.dillon.speedrunnermod.screen.misc;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModLinks;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;

import java.util.List;

public class OtherModsScreen extends AbstractModScreen {
    private Button sodiumButton, lithiumButton, speedrunIGTButton, kryptonButton, simpleKeybindsButton, qualityOfQuesoButton;

    public OtherModsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_MODS);
    }

    @Override
    protected List<AbstractWidget> buttons() {
        return List.of(
                this.sodiumButton,
                this.lithiumButton,
                this.speedrunIGTButton,
                this.kryptonButton,
                this.simpleKeybindsButton,
                this.qualityOfQuesoButton
        );
    }

    @Override
    protected void init() {
        this.sodiumButton = Button.builder(ModTexts.SODIUM, (buttonWidget) -> {
            this.openLink(ModLinks.SODIUM, false);
        }).build();

        this.lithiumButton = Button.builder(ModTexts.LITHIUM, (buttonWidget) -> {
            this.openLink(ModLinks.LITHIUM, false);
        }).build();

        this.speedrunIGTButton = Button.builder(ModTexts.SPEEDRUN_IGT, (buttonWidget) -> {
            this.openLink(ModLinks.SPEEDRUNIGT, false);
        }).build();

        this.kryptonButton = Button.builder(ModTexts.KRYPTON, (buttonWidget) -> {
            this.openLink(ModLinks.KRYPTON, false);
        }).build();

        this.simpleKeybindsButton = Button.builder(ModTexts.SIMPLE_KEYBINDS, (buttonWidget) -> {
            this.openLink(ModLinks.SIMPLE_KEYBINDS, false);
        }).build();

        this.qualityOfQuesoButton = Button.builder(ModTexts.QUALITY_OF_QUESO, (buttonWidget) -> {
            this.openLink(ModLinks.QUALITY_OF_QUESO, false);
        }).build();

        super.init();
    }

    @Override
    public String pageId() {
        return "dfkadsio";
    }

    @Override
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (this.sodiumButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.SODIUM_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.lithiumButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.LITHIUM_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.speedrunIGTButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.SPEEDRUN_IGT_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.kryptonButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.KRYPTON_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.simpleKeybindsButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.SIMPLE_KEYBINDS_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.qualityOfQuesoButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.QUALITY_OF_QUESO_TOOLTIP, context, mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    public void onClose() {
        this.minecraft.gui.setScreen(new ResourcesScreen(this.parent));
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return true;
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