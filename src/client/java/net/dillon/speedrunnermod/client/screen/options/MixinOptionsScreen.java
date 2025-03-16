package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.AbstractModScreen;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;

import java.io.File;

/**
 * Switches to enable/disable certain mixins from loading into the game.
 */
@Environment(EnvType.CLIENT)
public class MixinOptionsScreen extends AbstractModScreen {

    public MixinOptionsScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_MIXIN_OPTIONS);
    }

    @Override
    protected void init() {
        this.initializeOptionListWidget();

        this.optionList.addSingleOptionEntry(ModListOptions.TERRABLENDER_SURFACE_RULE_DATA_MIXIN);
        this.optionList.addSingleOptionEntry(ModListOptions.BACKGROUND_RENDERER_MIXIN);
        this.optionList.addSingleOptionEntry(ModListOptions.SIMPLE_OPTION_MIXIN);
        this.optionList.addSingleOptionEntry(ModListOptions.LOGO_DRAWER_MIXIN);
        this.optionList.addSingleOptionEntry(ModListOptions.RENDER_LAYERS_MIXIN);

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