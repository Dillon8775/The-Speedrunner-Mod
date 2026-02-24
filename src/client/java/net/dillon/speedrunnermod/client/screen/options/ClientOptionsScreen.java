package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.isSimpleKeybindsLoaded;

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
    private List<ClickableWidget> clientOptions(GameOptions gameOptions) {
        return isSimpleKeybindsLoaded() ? List.of(
                ModListOptions.fog().createWidget(this.gameOptions),
                ModListOptions.itemMessages().createWidget(this.gameOptions),
                ModListOptions.increasedLavaVision().createWidget(this.gameOptions)) : List.of(
                        ModListOptions.fog().createWidget(this.gameOptions),
                        ModListOptions.itemMessages().createWidget(this.gameOptions),
                        gameOptions.getGamma().createWidget(this.gameOptions),
                        ModListOptions.fullbrightAmount().createWidget(this.gameOptions),
                        ModListOptions.increasedLavaVision().createWidget(this.gameOptions));
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttonList.addAll(clientOptions(this.gameOptions));

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(DrawContext context, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(ModListOptions.fog(), SpeedrunnerModClient.clientOptions().mixins.fogMixins.getCurrentValue(),
                Text.translatable("speedrunnermod.options.fog.tooltip"),
                Text.translatable("speedrunnermod.options.fog_mixins_must_be_enabled.tooltip")
        );
        this.lockOptionWithTooltip(ModListOptions.increasedLavaVision(), SpeedrunnerModClient.clientOptions().mixins.fogMixins.getCurrentValue(),
                Text.translatable("speedrunnermod.options.increased_lava_vision.tooltip"),
                Text.translatable("speedrunnermod.options.fog_mixins_must_be_enabled.tooltip"));
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