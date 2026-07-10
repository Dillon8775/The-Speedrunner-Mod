package net.dillon.speedrunnermod.screen;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.ConfirmLinkScreen;
import net.minecraft.client.gui.screens.GenericMessageScreen;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.TitleScreen;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.Util;

/**
 * The base screen for any {@code Speedrunner Mod} screen.
 */
public class BaseModScreen extends OptionsSubScreen {
    public EditBox searchField;

    public BaseModScreen(Screen parent, Component title) {
        super(parent, Minecraft.getInstance().options, title);
    }

    /**
     * Quits a world.
     */
    protected void quitWorld() {
        if (this.minecraft.isLocalServer()) {
            this.minecraft.level.disconnect(Component.translatable("menu.savingLevel"));
            this.minecraft.disconnect(new GenericMessageScreen(Component.translatable("menu.savingLevel")), false, false);
        } else {
            this.minecraft.disconnect(new TitleScreen(), false, false);
        }
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

    /**
     * An easier way to open a link.
     */
    protected void openLink(String link, boolean trusted) {
        this.minecraft.gui.setScreen(new ConfirmLinkScreen(openInBrowser -> {
            if (openInBrowser) {
                Util.getPlatform().openUri(link);
            }
            this.minecraft.gui.setScreen(this);
            this.resize(this.width, this.height);
        }, link, trusted));
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