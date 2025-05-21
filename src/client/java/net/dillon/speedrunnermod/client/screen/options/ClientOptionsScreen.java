package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ButtonSide;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import net.minecraft.text.Text;

import java.io.File;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

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
                ModListOptions.FOG,
                gameOptions.getGamma(),
                ModListOptions.ITEM_TOOLTIPS,
                ModListOptions.TEXTURE_TOOLTIPS,
                ModListOptions.ITEM_MESSAGES,
                ModListOptions.SHOW_DEATH_CORDS,
                ModListOptions.CONFIRM_MESSAGES,
                ModListOptions.PANORAMA
        };
    }

    @Override
    protected void init() {
        this.initializeOptionListWidget();
        this.optionList.addAll(clientOptions(this.gameOptions));
        this.deactivateOptionIf(0, ButtonSide.LARGE, options().mixins.backgroundRendererMixin);
        this.addSelectableChild(this.optionList);
        this.configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), ModOptions.CONFIG);

        super.init();
    }

    @Override
    protected void renderOptionTooltips(DrawContext context, int mouseX, int mouseY) {
        this.renderOptionTooltip(
                0,
                ButtonSide.LEFT,
                options().mixins.backgroundRendererMixin,
                Text.translatable("speedrunnermod.options.fog.tooltip"),
                Text.translatable("speedrunnermod.options.apply_fog_mixin_must_be_enabled.tooltip"),
                context,
                mouseX,
                mouseY
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