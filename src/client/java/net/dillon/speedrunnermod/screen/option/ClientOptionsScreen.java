package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.isSimpleKeybindsLoaded;

/**
 * The Speedrunner Mod's {@code client options screen.}
 */
public class ClientOptionsScreen extends AbstractModScreen {
    private AbstractWidget fog, increasedLavaVision;

    public ClientOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_OPTIONS_CLIENT);
    }

    @Override
    protected void init() {
        this.fog = createOption(ListOptions.fog());
        this.increasedLavaVision = createOption(ListOptions.increasedLavaVision());

        this.initializeCustomButtonListWidget();

        this.buttonList.addRow(this.fog, createOption(ListOptions.itemMessages()));
        this.buttonList.addRow(this.increasedLavaVision, createOption(ListOptions.showResetButton()));
        if (!isSimpleKeybindsLoaded()) {
            this.buttonList.addRow(createOption(Minecraft.getInstance().options.gamma()), createOption(ListOptions.fullbrightAmount()));
        }

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(this.fog, SpeedrunnerModClient.clientOptions().mixins.fogMixins.getCurrentValue(),
                Component.translatable("speedrunnermod.options.fog.tooltip"),
                Component.translatable("speedrunnermod.options.fog_mixins_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );
        this.lockOptionWithTooltip(this.increasedLavaVision, SpeedrunnerModClient.clientOptions().mixins.fogMixins.getCurrentValue(),
                Component.translatable("speedrunnermod.options.increased_lava_vision.tooltip"),
                Component.translatable("speedrunnermod.options.fog_mixins_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY);
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
        return true;
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