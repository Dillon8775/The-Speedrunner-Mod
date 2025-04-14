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
public class ModsScreen extends AbstractModScreen {

    public ModsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_MODS);
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttons.add(0, ButtonWidget.builder(ModTexts.SODIUM, (buttonWidget) -> {
            this.openLink(ModLinks.SODIUM, false);
        }).build());

        this.buttons.add(1, ButtonWidget.builder(ModTexts.LITHIUM, (buttonWidget) -> {
            this.openLink(ModLinks.LITHIUM, false);
        }).build());

        this.buttons.add(2, ButtonWidget.builder(ModTexts.PHOSPHOR, (buttonWidget) -> {
            this.openLink(ModLinks.PHOSPHOR, false);
        }).build());

        this.buttons.add(3, ButtonWidget.builder(ModTexts.SPEEDRUN_IGT, (buttonWidget) -> {
            this.openLink(ModLinks.SPEEDRUNIGT, false);
        }).build());

        this.buttons.add(4, ButtonWidget.builder(ModTexts.LAZYDFU, (buttonWidget) -> {
            this.openLink(ModLinks.LAZYDFU, false);
        }).build());

        this.buttons.add(5, ButtonWidget.builder(ModTexts.KRYPTON, (buttonWidget) -> {
            this.openLink(ModLinks.KRYPTON, false);
        }).build());

        this.buttons.add(6, ButtonWidget.builder(ModTexts.SIMPLE_KEYBINDS, (buttonWidget) -> {
            this.openLink(ModLinks.SIMPLE_KEYBINDS, false);
        }).build());

        this.buttons.add(7, ButtonWidget.builder(ModTexts.OPTIFINE, (buttonWidget) -> {}).build());
        this.buttons.get(7).active = false;

        super.init();
    }

    @Override
    protected String pageId() {
        return "dfkadsio";
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.buttons.get(0).isHovered()) {
            this.renderBasicTooltip(ModTexts.SODIUM_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(1).isHovered()) {
            this.renderBasicTooltip(ModTexts.LITHIUM_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(2).isHovered()) {
            this.renderBasicTooltip(ModTexts.PHOSPHOR_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(3).isHovered()) {
            this.renderBasicTooltip(ModTexts.SPEEDRUN_IGT_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(4).isHovered()) {
            this.renderBasicTooltip(ModTexts.LAZYDFU_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(5).isHovered()) {
            this.renderBasicTooltip(ModTexts.KRYPTON_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(6).isHovered()) {
            this.renderBasicTooltip(ModTexts.SIMPLE_KEYBINDS_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(7).isHovered()) {
            this.renderBasicTooltip(ModTexts.OPTIFINE_TOOLTIP, context, mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    public void close() {
        this.client.setScreen(new ResourcesScreen(this.parent, MinecraftClient.getInstance().options));
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