package net.dillon.speedrunnermod.client.screen.feature;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

public class FeaturesScreen extends AbstractModScreen {
    private Button blocksAndItemsButton, toolsAndArmorButton, oresAndWorldgenButton, doomModeButton, miscellaneousButton;

    public FeaturesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURES);
    }

    @Override
    protected List<AbstractWidget> buttons() {
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
        this.blocksAndItemsButton = Button.builder(Component.translatable("speedrunnermod.menu.features.blocks_and_items"), (buttonWidget) -> {
            this.minecraft.setScreen(new BlocksAndItemsScreen(this.parent));
        }).build();

        this.toolsAndArmorButton = Button.builder(Component.translatable("speedrunnermod.menu.features.tools_and_armor"), (buttonWidget) -> {
            this.minecraft.setScreen(new ToolsAndArmorScreen(this.parent));
        }).build();

        this.oresAndWorldgenButton = Button.builder(Component.translatable("speedrunnermod.menu.features.ores_and_worldgen"), (buttonWidget) -> {
            this.minecraft.setScreen(new OresAndWorldgenScreen(this.parent));
        }).build();

        this.doomModeButton = Button.builder(Component.translatable("speedrunnermod.menu.features.doom_mode"), (buttonWidget) -> {
            this.minecraft.setScreen(new DoomModeScreen(this.parent));
        }).build();

        this.miscellaneousButton = Button.builder(Component.translatable("speedrunnermod.menu.features.miscellaneous"), (buttonWidget) -> {
            this.minecraft.setScreen(new MiscellaneousScreen(this.parent));
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