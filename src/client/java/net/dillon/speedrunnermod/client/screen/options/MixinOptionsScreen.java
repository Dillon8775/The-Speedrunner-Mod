package net.dillon.speedrunnermod.client.screen.options;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.option.ModListOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.screen.Screen;

/**
 * Switches to enable/disable certain mixins from loading into the game.
 */
public class MixinOptionsScreen extends AbstractModScreen {

    public MixinOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_MIXIN_OPTIONS);
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttonList.addSingleOptionEntry(ModListOptions.terraBlenderSurfaceRuleDataMixin());
        this.buttonList.addSingleOptionEntry(ModListOptions.fogMixins());
        this.buttonList.addSingleOptionEntry(ModListOptions.simpleOptionMixin());
        this.buttonList.addSingleOptionEntry(ModListOptions.logoDrawerMixin());
        this.buttonList.addSingleOptionEntry(ModListOptions.renderLayersMixin());

        super.init();
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