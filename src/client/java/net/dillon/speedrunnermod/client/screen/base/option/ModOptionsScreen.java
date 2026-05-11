package net.dillon.speedrunnermod.client.screen.base.option;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.options.*;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.speedrunnermod.option.ModOptions.isSsrCustom;

/**
 * The {@code options} screen for the Speedrunner Mod, consisting of all the option categories.
 */
public class ModOptionsScreen extends AbstractModScreen {
    private Button mainOptionsButton, fwcOptionsButton, clientOptionsButton, ssrOptionsButton, advancedOptionsButton, mixinOptionsButton, resetOptionsButton;

    public ModOptionsScreen(Screen parent) {
        super(parent, Component.translatable("speedrunnermod.title.options"));
    }

    @Override
    protected List<AbstractWidget> buttons() {
        List<AbstractWidget> widgets = new java.util.ArrayList<>();
        if (this.mainOptionsButton != null) {
            widgets.add(this.mainOptionsButton);
        }
        if (this.fwcOptionsButton != null) {
            widgets.add(this.fwcOptionsButton);
        }
        if (this.clientOptionsButton != null) {
            widgets.add(this.clientOptionsButton);
        }
        if (this.ssrOptionsButton != null) {
            widgets.add(this.ssrOptionsButton);
        }
        if (this.advancedOptionsButton != null) {
            widgets.add(this.advancedOptionsButton);
        }
        if (this.mixinOptionsButton != null) {
            widgets.add(this.mixinOptionsButton);
        }
        if (this.resetOptionsButton != null) {
            widgets.add(this.resetOptionsButton);
        }
        return widgets;
    }

    @Override
    protected void init() {
        this.mainOptionsButton = Button.builder(ModTexts.MENU_OPTIONS_MAIN, (button) -> {
            this.openOptionsScreen(new MainOptionsScreen(this));
        }).build();

        this.fwcOptionsButton = Button.builder(ModTexts.MENU_FAST_WORLD_CREATION, (button) -> {
            this.openOptionsScreen(new FastWorldCreationOptionsScreen(this));
        }).build();

        this.clientOptionsButton = Button.builder(ModTexts.MENU_OPTIONS_CLIENT, (button) -> {
            this.openOptionsScreen(new ClientOptionsScreen(this));
        }).build();

        this.ssrOptionsButton = Button.builder(ModTexts.MENU_STRUCTURE_SPAWN_RATE_OPTIONS, (button) -> {
            this.openOptionsScreen(new StructureSpawnRateOptionsScreen(this));
        }).build();

        this.advancedOptionsButton = Button.builder(ModTexts.MENU_ADVANCED_OPTIONS, (button) -> {
            this.openOptionsScreen(new AdvancedOptionsScreen(this));
        }).build();

        this.mixinOptionsButton = Button.builder(ModTexts.MENU_MIXIN_OPTIONS, (button) -> {
            this.openOptionsScreen(new MixinOptionsScreen(this));
        }).build();

        this.resetOptionsButton = Button.builder(ModTexts.MENU_OPTIONS_RESET, (button) -> {
            this.minecraft.setScreen(new ResetOptionsConfirmScreen(this));
        }).build();

        super.init();
    }

    /**
     * Gets the current options and sets the screen.
     */
    private void openOptionsScreen(AbstractModScreen screen) {
        RestartRequiredScreen.getCurrentOptions();
        this.minecraft.setScreen(screen);
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor context, int mouseX, int mouseY, float deltaTicks) {
        this.ssrOptionsButton.active = isSsrCustom();
        super.extractRenderState(context, mouseX, mouseY, deltaTicks);
    }

    @Override
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (this.mainOptionsButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_OPTIONS_MAIN_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.fwcOptionsButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_FAST_WORLD_CREATION_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.clientOptionsButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_OPTIONS_CLIENT_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.ssrOptionsButton.isHovered()) {
            if (isSsrCustom()) {
                this.renderBasicTooltip(ModTexts.MENU_STRUCTURE_SPAWN_RATE_OPTIONS_TOOLTIP, context, mouseX, mouseY);
            } else {
                this.renderBasicTooltip(ModTexts.MENU_STRUCTURE_SPAWN_RATE_OPTIONS_NEEDS_CUSTOM_TOOLTIP, context, mouseX, mouseY);
            }
        }
        if (this.advancedOptionsButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_ADVANCED_OPTIONS_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.mixinOptionsButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_MIXIN_OPTIONS_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.resetOptionsButton.isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_OPTIONS_RESET_TOOLTIP, context, mouseX, mouseY);
        }

        super.renderTooltips(context, mouseX, mouseY);
    }

    @Override
    public String pageId() {
        return "cbgipesi";
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