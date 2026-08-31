package net.dillon.speedrunnermod.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;

/**
 * The base screen for any {@code Speedrunner Mod} screen.
 */
public class BaseModScreen extends OptionsSubScreen {
    public EditBox searchField;

    public BaseModScreen(Screen parent, Component title) {
        super(parent, Minecraft.getInstance().options, title);
    }

    /**
     * Fixes resizing issues.
     */
    @Override
    public void resize(int width, int height) {
        String text = "";
        boolean refocus = this.searchField != null && this.searchField.isFocused();
        if (this.searchField != null) {
            text = this.searchField.getValue();
            refocus = this.searchField.isFocused();
        }
        super.resize(width, height);
        this.rebuildWidgets();
        if (this.searchField != null) {
            this.searchField.setValue(text);
            this.searchField.setFocused(refocus);
        }
    }

    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        AbstractModScreen.renderSpeedrunnerModTitleText(graphics, this.width);
    }

    @Override
    protected void addFooter() {
        this.layout.addToFooter(Button.builder(CommonComponents.GUI_DONE, button -> {
            this.onClose();
        }).width(175).build());
    }

    /**
     * A simplified way to render a tooltip.
     */
    protected void renderBasicTooltip(Component text, GuiGraphicsExtractor context, int mouseX, int mouseY) {
        context.setTooltipForNextFrame(this.font, this.font.split(text, 200), mouseX, mouseY);
    }

    /**
     * Needed because this method is abstract in {@link OptionsSubScreen}.
     */
    @Override
    protected void addOptions() {
    }
}