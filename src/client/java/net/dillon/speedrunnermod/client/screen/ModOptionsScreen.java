package net.dillon.speedrunnermod.client.screen;

import net.dillon.speedrunnermod.client.screen.options.*;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * The {@code options} screen for the Speedrunner Mod, consisting of all the option categories.
 */
@Environment(EnvType.CLIENT)
public class ModOptionsScreen extends AbstractModScreen {

    public ModOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.options"));
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttons.add(0, ButtonWidget.builder(ModTexts.MENU_OPTIONS_MAIN, (button) -> {
            this.client.setScreen(new MainOptionsScreen(this, options));
        }).build());

        this.buttons.add(1, ButtonWidget.builder(ModTexts.MENU_FAST_WORLD_CREATION, (button) -> {
            this.client.setScreen(new FastWorldCreationOptionsScreen(this, options));
        }).build());

        this.buttons.add(2, ButtonWidget.builder(ModTexts.MENU_OPTIONS_CLIENT, (button) -> {
            this.client.setScreen(new ClientOptionsScreen(this, options));
        }).build());

        this.buttons.add(3, ButtonWidget.builder(ModTexts.MENU_STRUCTURE_SPAWN_RATE_OPTIONS, (button) -> {
            this.client.setScreen(new StructureSpawnRateOptionsScreen(this, options));
        }).build());
        this.buttons.get(3).active = options().main.structureSpawnRates.custom();

        this.buttons.add(4, ButtonWidget.builder(ModTexts.MENU_ADVANCED_OPTIONS, (button) -> {
            this.client.setScreen(new AdvancedOptionsScreen(this, options));
        }).build());

        this.buttons.add(5, ButtonWidget.builder(ModTexts.MENU_MIXIN_OPTIONS, (button) -> {
            this.client.setScreen(new MixinOptionsScreen(this, options));
        }).build());

        this.buttons.add(6, ButtonWidget.builder(ModTexts.MENU_OPTIONS_RESET, (button) -> {
            this.client.setScreen(new ResetOptionsConfirmScreen(this, options));
        }).build());

        super.init();
    }

    @Override
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.buttons.get(0).isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_OPTIONS_MAIN_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(1).isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_FAST_WORLD_CREATION_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(2).isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_OPTIONS_CLIENT_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(3).isHovered()) {
            if (options().main.structureSpawnRates.custom()) {
                this.renderBasicTooltip(ModTexts.MENU_STRUCTURE_SPAWN_RATE_OPTIONS_TOOLTIP, context, mouseX, mouseY);
            } else {
                this.renderBasicTooltip(ModTexts.MENU_STRUCTURE_SPAWN_RATE_OPTIONS_NEEDS_CUSTOM_TOOLTIP, context, mouseX, mouseY);
            }
        }
        if (this.buttons.get(4).isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_ADVANCED_OPTIONS_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(5).isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_MIXIN_OPTIONS_TOOLTIP, context, mouseX, mouseY);
        }
        if (this.buttons.get(6).isHovered()) {
            this.renderBasicTooltip(ModTexts.MENU_OPTIONS_RESET_TOOLTIP, context, mouseX, mouseY);
        }
        super.renderTooltips(context, mouseX, mouseY);
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