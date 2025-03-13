package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.client.util.ButtonSide;
import net.dillon.speedrunnermod.util.ModTexts;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;

import java.io.File;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * The Speedrunner Mod's {@code client options screen.}
 */
@Environment(EnvType.CLIENT)
public class ClientOptionsScreen extends AbstractModScreen {

    public ClientOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_OPTIONS_CLIENT);
    }

    /**
     * All of the {@code client-side speedrunner mod options.}
     */
    private SimpleOption<?>[] clientOptions(GameOptions gameOptions) {
        return new SimpleOption[]{
                options().mixins.backgroundRendererMixin ? ModListOptions.FOG : ModListOptions.Inactiveable.IAO_FOG,
                gameOptions.getGamma(),
                ModListOptions.ITEM_TOOLTIPS,
                ModListOptions.TEXTURE_TOOLTIPS,
                ModListOptions.ITEM_MESSAGES,
                ModListOptions.SHOW_DEATH_CORDS,
                ModListOptions.CONFIRM_MESSAGES,
                ModListOptions.PANORAMA,
                ModListOptions.SOCIAL_BUTTONS
        };
    }

    @Override
    protected void init() {
        this.initializeOptionListWidget();
        this.optionList.addAll(clientOptions(this.gameOptions));
        this.deactivateButton(0, ButtonSide.LARGE, options().mixins.backgroundRendererMixin);
        this.addSelectableChild(this.optionList);
        this.configFile = new File(FabricLoader.getInstance().getConfigDir().toFile(), ModOptions.CONFIG);

        super.init();
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
    protected boolean isOptionsScreen() {
        return true;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}