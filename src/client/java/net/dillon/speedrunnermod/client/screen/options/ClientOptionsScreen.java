package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.client.option.ModListOptions;
import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.Options;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.speedrunnermod.client.main.SpeedrunnerModClient.isSimpleKeybindsLoaded;

/**
 * The Speedrunner Mod's {@code client options screen.}
 */
public class ClientOptionsScreen extends AbstractModScreen {

    public ClientOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_OPTIONS_CLIENT);
    }

    /**
     * All of the {@code client-side speedrunner mod options.}
     */
    private List<AbstractWidget> clientOptions(Options gameOptions) {
        return isSimpleKeybindsLoaded() ? List.of(
                ModListOptions.fog().createButton(this.options),
                ModListOptions.itemMessages().createButton(this.options),
                ModListOptions.increasedLavaVision().createButton(this.options)) : List.of(
                        ModListOptions.fog().createButton(this.options),
                        ModListOptions.itemMessages().createButton(this.options),
                        gameOptions.gamma().createButton(this.options),
                        ModListOptions.fullbrightAmount().createButton(this.options),
                        ModListOptions.increasedLavaVision().createButton(this.options));
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttonList.addAll(clientOptions(this.options));

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(ModListOptions.fog(), SpeedrunnerModClient.clientOptions().mixins.fogMixins.getCurrentValue(),
                Component.translatable("speedrunnermod.options.fog.tooltip"),
                Component.translatable("speedrunnermod.options.fog_mixins_must_be_enabled.tooltip")
        );
        this.lockOptionWithTooltip(ModListOptions.increasedLavaVision(), SpeedrunnerModClient.clientOptions().mixins.fogMixins.getCurrentValue(),
                Component.translatable("speedrunnermod.options.increased_lava_vision.tooltip"),
                Component.translatable("speedrunnermod.options.fog_mixins_must_be_enabled.tooltip"));
    }

    @Override
    public String pageId() {
        return "giiadsaa";
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