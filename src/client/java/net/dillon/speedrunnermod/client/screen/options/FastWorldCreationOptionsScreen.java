package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;

/**
 * The Speedrunner Mod's {@code "fast world creation"} options, which set the settings of each new world created.
 */
@Environment(EnvType.CLIENT)
public class FastWorldCreationOptionsScreen extends AbstractModScreen {

    public FastWorldCreationOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FAST_WORLD_CREATION);
    }

    /**
     * All of the {@code fast world creation options.}
     */
    private List<ClickableWidget> fwcOptions() {
        return List.of(
                ModListOptions.fastWorldCreation().createWidget(this.gameOptions),
                ModListOptions.difficulty().createWidget(this.gameOptions),
                ModListOptions.gameMode().createWidget(this.gameOptions),
                ModListOptions.allowCheats().createWidget(this.gameOptions)
        );
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttonList.addAll(fwcOptions());

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(DrawContext context, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(ModListOptions.gameMode(), clientOptions().client.fastWorldCreation.getCurrentValue(),
                Text.translatable("speedrunnermod.options.difficulty.tooltip"),
                Text.translatable("speedrunnermod.options.fast_world_creation_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.difficulty(), clientOptions().client.fastWorldCreation.getCurrentValue(),
                Text.translatable("speedrunnermod.options.gamemode.tooltip"),
                Text.translatable("speedrunnermod.options.fast_world_creation_must_be_enabled.tooltip")
        );

        this.lockOptionWithTooltip(ModListOptions.allowCheats(), clientOptions().client.fastWorldCreation.getCurrentValue(),
                Text.translatable("speedrunnermod.options.allow_cheats.tooltip"),
                Text.translatable("speedrunnermod.options.fast_world_creation_must_be_enabled.tooltip")
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