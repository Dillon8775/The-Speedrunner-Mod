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
public class ModsScreen extends AbstractModScreen {
    private ButtonWidget sodiumButton, lithiumButton, speedrunIGTButton, kryptonButton, simpleKeybindsButton, optiFineButton;

    public ModsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_MODS);
    }

    @Override
    protected List<ClickableWidget> buttons() {
        return List.of(
                this.sodiumButton,
                this.lithiumButton,
                this.speedrunIGTButton,
                this.kryptonButton,
                this.simpleKeybindsButton,
                this.optiFineButton
        );
    }

    @Override
    protected void init() {
        this.sodiumButton = ButtonWidget.builder(ModTexts.SODIUM, (buttonWidget) -> {
            this.openLink(ModLinks.SODIUM, false);
        }).build();

        this.lithiumButton = ButtonWidget.builder(ModTexts.LITHIUM, (buttonWidget) -> {
            this.openLink(ModLinks.LITHIUM, false);
        }).build();

        this.speedrunIGTButton = ButtonWidget.builder(ModTexts.SPEEDRUN_IGT, (buttonWidget) -> {
            this.openLink(ModLinks.SPEEDRUNIGT, false);
        }).build();

        this.kryptonButton = ButtonWidget.builder(ModTexts.KRYPTON, (buttonWidget) -> {
            this.openLink(ModLinks.KRYPTON, false);
        }).build();

        this.simpleKeybindsButton = ButtonWidget.builder(ModTexts.SIMPLE_KEYBINDS, (buttonWidget) -> {
            this.openLink(ModLinks.SIMPLE_KEYBINDS, false);
        }).build();

        this.optiFineButton = ButtonWidget.builder(ModTexts.OPTIFINE, (buttonWidget) -> {}).build();
        this.optiFineButton.active = false;

        super.init();
    }

    @Override
    public String pageId() {
        return "dfkadsio";
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
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
        if (this.optiFineButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.OPTIFINE_TOOLTIP, context, mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    public void close() {
        this.client.setScreen(new ResourcesScreen(this.parent));
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