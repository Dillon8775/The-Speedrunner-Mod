package net.dillon.speedrunnermod.screen;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.Minecraft;
import net.minecraft.client.OptionInstance;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.ContainerObjectSelectionList;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.options.OptionsSubScreen;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * Copied over from Minecraft's original {@link net.minecraft.client.gui.components.OptionsList}, this allows you to simply create a list of buttons.
 */
public class CustomButtonListWidget extends ContainerObjectSelectionList<CustomButtonListWidget.ModWidgetEntry> {

    /**
     * Create a new {@link CustomButtonListWidget}.
     */
    public CustomButtonListWidget(Minecraft client, int width, OptionsSubScreen optionsScreen) {
        super(client, width, optionsScreen.layout.getContentHeight(), optionsScreen.layout.getHeaderHeight(), 25);
        this.centerListVertically = false;
    }

    /**
     * Adds a "row" of buttons to the list.
     */
    public void addRow(AbstractWidget firstButton, @Nullable AbstractWidget secondButton) {
        List<AbstractWidget> buttons = new ArrayList<>();
        firstButton.setX(this.width / 2 - 155);
        buttons.add(firstButton);
        if (secondButton != null) {
            secondButton.setX(this.width / 2 + 5);
            buttons.add(secondButton);
        }
        this.addEntry(ModWidgetEntry.create(buttons));
    }

    /**
     * <p>Adds a single button to the list of buttons.</p>
     * This button will take up a whole "row" space.
     */
    public void addSingleOptionEntry(OptionInstance<?> option) {
        AbstractWidget button = option.createButton(Minecraft.getInstance().options);
        button.setX(this.width / 2 - 155);
        button.setWidth(310);
        List<AbstractWidget> buttons = new ArrayList<>();
        buttons.add(button);
        this.addEntry(ModWidgetEntry.create(buttons));
    }

    /**
     * Adds a whole {@link List} of buttons to the screen.
     */
    public void addAll(List<AbstractWidget> buttons) {
        for (int i = 0; i < buttons.size(); i += 2) {
            this.addRow(buttons.get(i), i < buttons.size() - 1 ? buttons.get(i + 1) : null);
        }
    }

    @Override
    public int getRowWidth() {
        return 310;
    }

    @Environment(value= EnvType.CLIENT)
    public static class ModWidgetEntry extends ContainerObjectSelectionList.Entry<ModWidgetEntry> {
        public final List<AbstractWidget> widgets;

        private ModWidgetEntry(List<AbstractWidget> widgets) {
            this.widgets = widgets;
        }

        public static ModWidgetEntry create(List<AbstractWidget> widgets) {
            return new ModWidgetEntry(widgets);
        }

        @Override
        public void extractContent(GuiGraphicsExtractor context, int mouseX, int mouseY, boolean hovered, float deltaTicks) {
            for (AbstractWidget widget : this.widgets) {
                widget.setY(this.getY());
                widget.extractRenderState(context, mouseX, mouseY, deltaTicks);
            }
        }

        @Override
        public List<? extends GuiEventListener> children() {
            return this.widgets;
        }

        @Override
        public List<? extends NarratableEntry> narratables() {
            return this.widgets;
        }
    }
}