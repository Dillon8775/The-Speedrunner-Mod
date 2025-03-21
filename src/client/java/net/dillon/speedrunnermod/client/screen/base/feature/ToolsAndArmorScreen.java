package net.dillon.speedrunnermod.client.screen.base.feature;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class ToolsAndArmorScreen extends AbstractModScreen {

    public ToolsAndArmorScreen(Screen parent, GameOptions options) {
        super(parent, options, Text.translatable("speedrunnermod.title.features.tools_and_armor"));
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();
        this.iterate(ScreenCategory.TOOLS_AND_ARMOR);
        super.init();
    }

    @Override
    protected void doneButtonFunction() {
        this.close();
    }

    @Override
    protected String pageId() {
        return "gfnipiads";
    }

    @Override
    public void close() {
        this.client.setScreen(new FeaturesScreen(this.parent, this.options));
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
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}