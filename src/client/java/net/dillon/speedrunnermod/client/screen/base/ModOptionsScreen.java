package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.client.screen.options.*;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * The {@code options} screen for the Speedrunner Mod, consisting of all the option categories.
 */
@Environment(EnvType.CLIENT)
public class ModOptionsScreen extends AbstractModScreen {
    private ButtonWidget mainOptionsButton, fwcOptionsButton, clientOptionsButton, ssrOptionsButton, advancedOptionsButton, mixinOptionsButton, resetOptionsButton, resetTutorialModeButton;

    public ModOptionsScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.options"));
    }

    @Override
    protected List<ClickableWidget> buttons() {
        return options().main.tutorialMode ?
                List.of(
                        this.mainOptionsButton,
                        this.fwcOptionsButton,
                        this.clientOptionsButton,
                        this.ssrOptionsButton,
                        this.advancedOptionsButton,
                        this.mixinOptionsButton,
                        this.resetOptionsButton,
                        this.resetTutorialModeButton
        ) :
                List.of(
                        this.mainOptionsButton,
                        this.fwcOptionsButton,
                        this.clientOptionsButton,
                        this.ssrOptionsButton,
                        this.advancedOptionsButton,
                        this.mixinOptionsButton,
                        this.resetOptionsButton
                );
    }

    @Override
    protected void init() {
        this.mainOptionsButton = ButtonWidget.builder(ModTexts.MENU_OPTIONS_MAIN, (button) -> {
            this.client.setScreen(new MainOptionsScreen(this));
        }).build();

        this.fwcOptionsButton = ButtonWidget.builder(ModTexts.MENU_FAST_WORLD_CREATION, (button) -> {
            this.client.setScreen(new FastWorldCreationOptionsScreen(this));
        }).build();

        this.clientOptionsButton = ButtonWidget.builder(ModTexts.MENU_OPTIONS_CLIENT, (button) -> {
            this.client.setScreen(new ClientOptionsScreen(this));
        }).build();

        this.ssrOptionsButton = ButtonWidget.builder(ModTexts.MENU_STRUCTURE_SPAWN_RATE_OPTIONS, (button) -> {
            this.client.setScreen(new StructureSpawnRateOptionsScreen(this));
        }).build();

        this.advancedOptionsButton = ButtonWidget.builder(ModTexts.MENU_ADVANCED_OPTIONS, (button) -> {
            this.client.setScreen(new AdvancedOptionsScreen(this));
        }).build();

        this.mixinOptionsButton = ButtonWidget.builder(ModTexts.MENU_MIXIN_OPTIONS, (button) -> {
            this.client.setScreen(new MixinOptionsScreen(this));
        }).build();

        this.resetOptionsButton = ButtonWidget.builder(ModTexts.MENU_OPTIONS_RESET, (button) -> {
            this.client.setScreen(new ResetOptionsConfirmScreen(this, false));
        }).build();

        if (options().main.tutorialMode) {
            this.resetTutorialModeButton = ButtonWidget.builder(ModTexts.MENU_TUTORIAL_MODE_OPTIONS_RESET, (button) -> {
                this.client.setScreen(new ResetOptionsConfirmScreen(this, true));
            }).build();
        }

        super.init();
    }

    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float deltaTicks) {
        this.ssrOptionsButton.active = options().main.structureSpawnRates.custom();
        super.render(context, mouseX, mouseY, deltaTicks);
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
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
            if (options().main.structureSpawnRates.custom()) {
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
        if (options().main.tutorialMode) {
            if (this.resetTutorialModeButton.isHovered()) {
                this.renderBasicTooltip(ModTexts.MENU_TUTORIAL_MODE_OPTIONS_RESET_TOOLTIP, context, mouseX, mouseY);
            }
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