package net.dillon.speedrunnermod.client.screen.feature;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class DoomModeScreen extends AbstractModScreen {

    public DoomModeScreen(Screen parent) {
        super(parent, Text.translatable("speedrunnermod.title.features.doom_mode"));
    }

    @Override
    protected void init() {
        this.addButtonsIteratively(ScreenCategory.DOOM_MODE);
        super.init();
    }

    @Override
    public String pageId() {
        return "gkpiiads";
    }

    @Override
    public void close() {
        this.client.setScreen(new FeaturesScreen(this.parent));
    }

    @Override
    protected int columns() {
        return 2;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    public boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}