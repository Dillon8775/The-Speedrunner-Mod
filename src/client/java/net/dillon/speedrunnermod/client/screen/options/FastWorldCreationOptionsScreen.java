package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * The Speedrunner Mod's {@code "fast world creation"} options, which set the settings of each new world created.
 */
public class FastWorldCreationOptionsScreen extends AbstractModScreen {

    public FastWorldCreationOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FAST_WORLD_CREATION);
    }

    /**
     * All of the {@code fast world creation options.}
     */
    private List<AbstractWidget> fwcOptions() {
        return List.of(
                ModListOptions.fastWorldCreation().createButton(this.options),
                ModListOptions.difficulty().createButton(this.options),
                ModListOptions.gameMode().createButton(this.options),
                ModListOptions.allowCheats().createButton(this.options)
        );
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttonList.addAll(fwcOptions());

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(GuiGraphics context, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(ModListOptions.gameMode(), clientOptions().client.fastWorldCreation.getCurrentValue(),
                Component.translatable("speedrunnermod.options.gamemode.tooltip"),
                Component.translatable("speedrunnermod.options.fast_world_creation_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.difficulty(), !isDoomMode(),
                Component.translatable("speedrunnermod.options.difficulty.tooltip"),
                clientOptions().client.fastWorldCreation.getCurrentValue() ?
                        Component.translatable("speedrunnermod.options.difficulty_locked.tooltip") :
                        Component.translatable("speedrunnermod.options.fast_world_creation_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.allowCheats(), clientOptions().client.fastWorldCreation.getCurrentValue(),
                Component.translatable("speedrunnermod.options.allow_cheats.tooltip"),
                Component.translatable("speedrunnermod.options.fast_world_creation_must_be_enabled.tooltip")
        );
    }

    @Override
    public String pageId() {
        return "gfnipfioad";
    }

    @Override
    protected int columns() {
        return 3;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    public boolean isOptionsScreen() {
        return true;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}