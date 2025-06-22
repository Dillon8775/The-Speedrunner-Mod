package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;

/**
 * Switches to enable/disable certain mixins from loading into the game.
 */
@Environment(EnvType.CLIENT)
public class MixinOptionsScreen extends AbstractModScreen {

    public MixinOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_MIXIN_OPTIONS);
    }

    @Override
    protected void init() {
        super.init();
        this.optionList.addSingleOptionEntry(ModListOptions.TERRABLENDER_SURFACE_RULE_DATA_MIXIN);
        this.optionList.addSingleOptionEntry(ModListOptions.BACKGROUND_RENDERER_MIXIN);
        this.optionList.addSingleOptionEntry(ModListOptions.SIMPLE_OPTION_MIXIN);
        this.optionList.addSingleOptionEntry(ModListOptions.LOGO_DRAWER_MIXIN);
        this.optionList.addSingleOptionEntry(ModListOptions.RENDER_LAYERS_MIXIN);

        this.addSelectableChild(this.optionList);
    }

    @Override
    public String pageId() {
        return "fpoadsioad";
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