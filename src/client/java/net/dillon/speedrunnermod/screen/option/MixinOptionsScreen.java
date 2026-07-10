package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.gui.screens.Screen;

/**
 * Switches to enable/disable certain mixins from loading into the game.
 */
public class MixinOptionsScreen extends AbstractModScreen {

    public MixinOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_MIXIN_OPTIONS);
    }

    @Override
    protected void init() {
        this.initializeModButtonListWidget();

        this.buttonList.addSingleOptionEntry(createOption(ListOptions.theEndGatewayBlockEntityMixin()));
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.itemStackMixin()));
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.fogMixins()));
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.abstractClientPlayerMixin()));
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.simpleOptionMixin()));
        this.buttonList.addSingleOptionEntry(createOption(ListOptions.logoDrawerMixin()));

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