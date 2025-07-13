package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

/**
 * The Speedrunner Mod's {@code client options screen.}
 */
@Environment(EnvType.CLIENT)
public class ClientOptionsScreen extends AbstractModScreen {

    public ClientOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_OPTIONS_CLIENT);
    }

    /**
     * All of the {@code client-side speedrunner mod options.}
     */
    private SimpleOption<?>[] clientOptions(GameOptions gameOptions) {
        return new SimpleOption[]{
                ModListOptions.fog(),
                gameOptions.getGamma(),
                ModListOptions.itemMessages(),
                ModListOptions.increasedLavaVision()
        };
    }

    @Override
    protected void init() {
        super.init();
        this.optionList.addAll(clientOptions(this.gameOptions));
        this.addSelectableChild(this.optionList);
    }

    @Override
    protected void lockOptionsAndRenderTooltips(DrawContext context, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(ModListOptions.fog(), SpeedrunnerModClient.clientOptions().mixins.fogMixins.getCurrentValue(),
                Text.translatable("speedrunnermod.options.fog.tooltip"),
                Text.translatable("speedrunnermod.options.fog_mixins_must_be_enabled.tooltip")
        );
        this.lockOptionWithTooltip(ModListOptions.increasedLavaVision(), SpeedrunnerModClient.clientOptions().mixins.fogMixins.getCurrentValue(),
                Text.translatable("speedrunnermod.options.increased_lava_vision.tooltip"),
                Text.translatable("speedrunnermod.options.fog_mixins_must_be_enabled.tooltip")
        );
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