package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

/**
 * The Speedrunner Mod's {@code client options screen.}
 */
public class ClientOptionsScreen extends AbstractModScreen {
    private AbstractWidget fog, increasedLavaVision;

    public ClientOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_OPTIONS_CLIENT);
    }

    /**
     * All of the {@code client options.}
     * <p>These are displayed in order.</p>
     */
    private List<AbstractWidget> clientOptions() {
        this.fog = createOption(ListOptions.fog());
        this.increasedLavaVision = createOption(ListOptions.increasedLavaVision());

        return List.of(
                this.fog,
                createOption(ListOptions.itemMessages()),

                this.increasedLavaVision,
                createOption(ListOptions.warningMessages()),

                createOption(Minecraft.getInstance().options.gamma()),
                createOption(ListOptions.showResetButton()),

                createOption(ListOptions.fullbrightAmount())
        );
    }

    @Override
    protected void init() {
        this.initializeModButtonListWidget();

        this.buttonList.addAll(clientOptions());

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(this.fog, SpeedrunnerModClient.client().mixins.fogMixins.getCurrentValue(),
                Component.translatable("speedrunnermod.options.fog.tooltip"),
                Component.translatable("speedrunnermod.options.fog_mixins_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );
        this.lockOptionWithTooltip(this.increasedLavaVision, SpeedrunnerModClient.client().mixins.fogMixins.getCurrentValue(),
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