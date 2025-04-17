package net.dillon.speedrunnermod.client.screen.feature;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.base.text.AllFeaturesScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

@Environment(EnvType.CLIENT)
public class FeaturesScreen extends AbstractModScreen {

    public FeaturesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURES);
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttons.add(0, ButtonWidget.builder(ModTexts.MENU_ALL_FEATURES, (buttonWidget) -> {
            this.client.setScreen(new AllFeaturesScreen(this.parent));
        }).build());

        this.buttons.add(1, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.blocks_and_items"), (buttonWidget) -> {
            this.client.setScreen(new BlocksAndItemsScreen(this.parent));
        }).build());

        this.buttons.add(2, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.tools_and_armor"), (buttonWidget) -> {
            this.client.setScreen(new ToolsAndArmorScreen(this.parent));
        }).build());

        this.buttons.add(3, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.ores_and_worldgen"), (buttonWidget) -> {
            this.client.setScreen(new OresAndWorldgenScreen(this.parent));
        }).build());

        this.buttons.add(4, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.doom_mode"), (buttonWidget) -> {
            this.client.setScreen(new DoomModeScreen(this.parent));
        }).build());

        this.buttons.add(5, ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.miscellaneous"), (buttonWidget) -> {
            this.client.setScreen(new MiscellaneousScreen(this.parent));
        }).build());

        super.init();
    }

    @Override
    protected String pageId() {
        return "ipaipads";
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