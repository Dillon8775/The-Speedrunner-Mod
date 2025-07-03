package net.dillon.speedrunnermod.client.screen.feature;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.ClickableWidget;
import net.minecraft.text.Text;

import java.util.List;

@Environment(EnvType.CLIENT)
public class FeaturesScreen extends AbstractModScreen {
    private ButtonWidget blocksAndItemsButton, toolsAndArmorButton, oresAndWorldgenButton, doomModeButton, miscellaneousButton;

    public FeaturesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURES);
    }

    @Override
    protected List<ClickableWidget> buttons() {
        return List.of(
                this.blocksAndItemsButton,
                this.toolsAndArmorButton,
                this.oresAndWorldgenButton,
                this.doomModeButton,
                this.miscellaneousButton
        );
    }

    @Override
    protected void init() {
        this.blocksAndItemsButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.blocks_and_items"), (buttonWidget) -> {
            this.client.setScreen(new BlocksAndItemsScreen(this.parent));
        }).build();

        this.toolsAndArmorButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.tools_and_armor"), (buttonWidget) -> {
            this.client.setScreen(new ToolsAndArmorScreen(this.parent));
        }).build();

        this.oresAndWorldgenButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.ores_and_worldgen"), (buttonWidget) -> {
            this.client.setScreen(new OresAndWorldgenScreen(this.parent));
        }).build();

        this.doomModeButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.doom_mode"), (buttonWidget) -> {
            this.client.setScreen(new DoomModeScreen(this.parent));
        }).build();

        this.miscellaneousButton = ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.miscellaneous"), (buttonWidget) -> {
            this.client.setScreen(new MiscellaneousScreen(this.parent));
        }).build();

        super.init();
    }

    @Override
    public String pageId() {
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
    public boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}