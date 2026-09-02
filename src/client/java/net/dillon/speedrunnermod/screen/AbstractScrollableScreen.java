package net.dillon.speedrunnermod.screen;

import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.blaze3d.platform.NativeImage;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.Identifier;
import net.minecraft.util.CommonColors;
import net.minecraft.util.FormattedCharSequence;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A scrollable text screen.
 */
public abstract class AbstractScrollableScreen extends AbstractModScreen {
    public final List<LineObject> objectsToDisplay = new ArrayList<>();
    private final int scrollSpeed = 12;
    protected float scrollOffset;
    protected float targetScrollOffset;
    private static final float SCROLL_LERP_SPEED = 0.2F;
    private boolean isDraggingScrollbar = false;
    private boolean isDraggingAnywhere = false;
    private int lastMouseY = -1;
    private int top, bottom;

    public AbstractScrollableScreen(Screen parent, Component title) {
        super(parent, title);
    }

    /**
     * Loads and parses lines from the specified resource text file.
     */
    private void loadAndPrintText(Identifier path) {
        try (BufferedReader reader = new BufferedReader(this.minecraft.getResourceManager().openAsReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    this.objectsToDisplay.add(new LineObject(Component.literal(" "), 1.0F, null, 0, 0, null));
                    continue;
                }

                this.objectsToDisplay.add(parseLine(line));
            }
        } catch (IOException e) {
            if (e instanceof FileNotFoundException) {
                SpeedrunnerMod.LOGGER.warn("No text file found for " + this.getClass().getSimpleName() + ": " + path);
            } else {
                e.printStackTrace();
            }
        }
    }

    /**
     * Parses a line, detecting headers ("#") and applying scaled formatting.
     */
    private LineObject parseLine(String line) {
        // Handle image line with optional scale
        if (line.startsWith("!image:")) {
            String imageLine = line.substring("!image:".length()).trim();

            // Extract scale if present
            float customScale = -1.0F; // Use -1 to indicate no custom scale
            String[] parts = imageLine.split("\\s+");
            String imagePath = parts[0];

            for (int i = 1; i < parts.length; i++) {
                if (parts[i].startsWith("scale=")) {
                    try {
                        customScale = Float.parseFloat(parts[i].substring("scale=".length()));
                    } catch (NumberFormatException e) {
                        SpeedrunnerMod.LOGGER.error("Invalid scale format in line: " + line);
                    }
                }
            }

            Identifier imageId = ofSpeedrunnerMod(imagePath);

            try {
                NativeImage image = NativeImage.read(this.minecraft.getResourceManager().open(imageId));
                int originalWidth = image.getWidth();
                int originalHeight = image.getHeight();

                // Use custom scale if provided, otherwise calculate scale to fit max width
                float scale = (customScale > 0) ? Math.min(customScale, 1.0F) : Math.min(1.0F, 300.0F / originalWidth);

                int scaledWidth = (int)(originalWidth * scale);
                int scaledHeight = (int)(originalHeight * scale);

                return new LineObject(null, 1.0F, imageId, scaledWidth, scaledHeight, null);
            } catch (IOException e) {
                if (e instanceof FileNotFoundException) {
                    SpeedrunnerMod.LOGGER.error("No image file found in referencing text file " + this.getClass().getSimpleName() + ": " + imageId);
                } else {
                    e.printStackTrace();
                }
                return new LineObject(Component.literal("[Image Load Failed]"), 1.0F, null, 0, 0, null);
            }
        }

        // Handle headers / regular text
        int headingLevel = 0;
        while (headingLevel < line.length() && line.charAt(headingLevel) == '#') {
            headingLevel++;
        }

        float scale = switch (headingLevel) {
            case 1 -> 2.0F;
            case 2 -> 1.5F;
            case 3 -> 1.3F;
            case 4 -> 1.1F;
            case 5 -> 0.9F;
            default -> 1.0F;
        };

        String content = line.substring(headingLevel).stripLeading();
        Component formatted = this.parseLegacyFormattedText(content);

        return new LineObject(formatted, scale, null, 0, 0, null);
    }

    /**
     * Parses legacy Minecraft formatting codes (e.g., §a, §l) into styled text.
     */
    private Component parseLegacyFormattedText(String input) {
        Style currentStyle = Style.EMPTY;
        MutableComponent result = Component.literal("");

        for (int i = 0; i < input.length(); ) {
            char c = input.charAt(i);
            if (c == '§' && i + 1 < input.length()) {
                currentStyle = applyFormatCode(currentStyle, input.charAt(i + 1));
                i += 2;
            } else {
                result.append(Component.literal(String.valueOf(c)).setStyle(currentStyle));
                i++;
            }
        }

        return result;
    }

    /**
     * Applies a single Minecraft formatting code to the current style.
     */
    private Style applyFormatCode(Style style, char code) {
        return switch (code) {
            case '0' -> style.withColor(ChatFormatting.BLACK);
            case '1' -> style.withColor(ChatFormatting.DARK_BLUE);
            case '2' -> style.withColor(ChatFormatting.DARK_GREEN);
            case '3' -> style.withColor(ChatFormatting.DARK_AQUA);
            case '4' -> style.withColor(ChatFormatting.DARK_RED);
            case '5' -> style.withColor(ChatFormatting.DARK_PURPLE);
            case '6' -> style.withColor(ChatFormatting.GOLD);
            case '7' -> style.withColor(ChatFormatting.GRAY);
            case '8' -> style.withColor(ChatFormatting.DARK_GRAY);
            case '9' -> style.withColor(ChatFormatting.BLUE);
            case 'a' -> style.withColor(ChatFormatting.GREEN);
            case 'b' -> style.withColor(ChatFormatting.AQUA);
            case 'c' -> style.withColor(ChatFormatting.RED);
            case 'd' -> style.withColor(ChatFormatting.LIGHT_PURPLE);
            case 'e' -> style.withColor(ChatFormatting.YELLOW);
            case 'f' -> style.withColor(ChatFormatting.WHITE);
            case 'l' -> style.withBold(true);
            case 'o' -> style.withItalic(true);
            case 'n' -> style.withUnderlined(true);
            case 'm' -> style.withStrikethrough(true);
            case 'k' -> style.withObfuscated(true);
            case 'r' -> Style.EMPTY;
            default -> style;
        };
    }

    /**
     * Gets the wrap width for wrapping text.
     */
    private int getWrapWidth(LineObject line) {
        if (line.scale == 2.0F) {
            return 185;
        } else if (line.scale == 1.5F) {
            return 250;
        } else if (line.scale == 1.3F) {
            return 290;
        } else if (line.scale == 1.1F) {
            return 340;
        } else if (line.scale == 0.9F) {
            return 410;
        } else {
            return 370;
        }
    }

    /**
     * Computes total height of all lines (scaled).
     */
    private int getTotalContentHeight() {
        int totalHeight = 0;

        for (LineObject line : this.objectsToDisplay) {
            if (line.isImage()) {
                totalHeight += line.imageHeight + 16;
            } else if (line.isButton()) {
                totalHeight += 20 + 4;
            } else if (line.isText()) {
                List<FormattedCharSequence> wrapped = this.font.split(line.text, this.getWrapWidth(line));
                int lineHeight = (int)((this.font.lineHeight + 2) * line.scale);
                totalHeight += wrapped.size() * lineHeight;
            }
        }

        return totalHeight;
    }

    /**
     * Initializes top and bottom positions.
     */
    private void initializeTopAndBottom() {
        this.top = this.list.getY() + 20;
        this.bottom = (this.height - 29) - 16;
    }

    /**
     * Calculates maximum scroll offset needed to fit all content.
     */
    private int getAccurateMaxScroll() {
        int visibleHeight = bottom - top;
        int totalHeight = getTotalContentHeight();
        return Math.max(0, totalHeight - visibleHeight);
    }

    /**
     * Determines if the text on the screen should be center aligned.
     */
    public boolean centerAligned() {
        return true;
    }

    /**
     * Initializes the screen: loads content and adds the Done button.
     */
    @Override
    protected void init() {
        this.objectsToDisplay.clear(); // Clear the lines to refresh it
        loadAndPrintText(ofSpeedrunnerMod(this.getTextFile())); // Print the text on the screen

        super.init();
    }

    /**
     * Renders scrollable formatted text.
     */
    @Override
    public void extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float delta) {
        if (!isDraggingScrollbar && !isDraggingAnywhere) {
            this.scrollOffset += (targetScrollOffset - scrollOffset) * SCROLL_LERP_SPEED;
            int maxScroll = getAccurateMaxScroll();
            this.scrollOffset = Math.max(0, Math.min(this.scrollOffset, maxScroll));
        } else {
            this.scrollOffset = targetScrollOffset;
        }
        super.extractRenderState(graphics, mouseX, mouseY, delta);

        initializeTopAndBottom();
        int scrollbarX = this.width - 10;
        int scrollbarWidth = 6;
        int scrollbarHeight = bottom - top;
        int contentHeight = getTotalContentHeight();
        double y = top - this.scrollOffset;

        for (LineObject line : this.objectsToDisplay) {
            float scale = line.scale;

            if (line.isText()) {
                List<FormattedCharSequence> wrapped = this.font.split(line.text, this.getWrapWidth(line));
                int lineHeight = (int)((this.font.lineHeight + 2) * scale);

                for (FormattedCharSequence wrappedLine : wrapped) {
                    if (y + lineHeight < top) {
                        y += lineHeight;
                        continue;
                    }
                    if (y >= bottom) {
                        break;
                    }

                    int textWidth = this.font.width(wrappedLine);
                    double textX = (this.width - textWidth * scale) / 2.0;

                    graphics.pose().pushMatrix();
                    graphics.pose().translate(this.centerAligned() ? (float)textX : (float)this.width / 2 - 175, (float)y);
                    graphics.pose().scale(scale, scale);
                    graphics.text(this.font, wrappedLine, 0, 0, CommonColors.WHITE);
                    graphics.pose().popMatrix();

                    y += lineHeight;
                }
            }

            if (line.isImage()) {
                int scaledWidth = line.imageWidth;
                int scaledHeight = line.imageHeight;

                if (y + scaledHeight < top) {
                    y += scaledHeight + 16;
                    continue;
                }
                if (y >= bottom) {
                    break;
                }

                int visibleY = (int) Math.max(y, top);
                int visibleHeight = (int) Math.min(y + scaledHeight, bottom) - visibleY;
                int imageYOffset = visibleY - (int) y;

                if (visibleHeight > 0) {
                    int x = (this.width - scaledWidth) / 2;
                    graphics.blit(RenderPipelines.GUI_TEXTURED, line.imageId, x, visibleY, 0, imageYOffset, scaledWidth, visibleHeight, scaledWidth, scaledHeight);
                }

                y += scaledHeight + 16;
                continue;
            }

            if (line.isButton()) {
                AbstractWidget button = line.button();
                button.setWidth(this.getButtonsWidth());
                button.setHeight(20);
                button.setX(this.width / 2 - 75);
                button.setY((int) y); // Scrolls with the rest of the content

                if (y + button.getHeight() < top || y >= (bottom - 8)) {
                    button.visible = false;
                    continue;
                }

                button.visible = true;

                if (!this.children().contains(button)) {
                    this.addRenderableWidget(button);
                }

                button.visible = true;
                if (!this.children().contains(button)) {
                    this.addRenderableWidget(button);
                }
                button.extractRenderState(graphics, mouseX, mouseY, delta);

                y += button.getHeight() + 4;
            }
        }

        if (contentHeight > scrollbarHeight) {
            float scrollRatio = (float) scrollbarHeight / contentHeight;
            int thumbHeight = Math.max((int)(scrollbarHeight * scrollRatio), 10);
            int maxScroll = getAccurateMaxScroll();
            float scrollPercent = this.scrollOffset / maxScroll;
            int thumbY = top + (int)(scrollPercent * (scrollbarHeight - thumbHeight));

            // Draw track
            graphics.fill(scrollbarX, top, scrollbarX + scrollbarWidth, bottom, 0xFF202020);

            // Hover effect for thumb
            boolean isHovered = mouseX >= scrollbarX && mouseX <= scrollbarX + scrollbarWidth && mouseY >= thumbY && mouseY <= thumbY + thumbHeight;
            int thumbColor = isHovered ? 0xFFFFFFFF : 0xFFA0A0A0;

            // Draw thumb
            graphics.fill(scrollbarX, thumbY, scrollbarX + scrollbarWidth, thumbY + thumbHeight, thumbColor);
        }
    }

    /**
     * Enables scrollbar dragging when clicked.
     */
    @Override
    public boolean mouseClicked(MouseButtonEvent click, boolean doubled) {
        for (LineObject line : objectsToDisplay) {
            if (line.isButton() && line.button.visible && line.button.isMouseOver(click.x(), click.y())) {
                if (line.button instanceof Button buttonWidget) {
                    buttonWidget.onPress(click);
                } else {
                    line.button.onClick(click, doubled);
                }
                line.button.playDownSound(Minecraft.getInstance().getSoundManager());
                return true;
            }
        }

        if (click.button() == 0) {
            isDraggingAnywhere = true;
            lastMouseY = (int) click.y();
        }

        initializeTopAndBottom();
        int scrollbarX = this.width - 10;
        int scrollbarWidth = 6;
        int scrollbarHeight = bottom - top;
        int contentHeight = getTotalContentHeight();

        if (click.x() >= scrollbarX && click.x() <= scrollbarX + scrollbarWidth && click.y() >= top && click.y() <= bottom && contentHeight > scrollbarHeight) {
            isDraggingScrollbar = true;lastMouseY = (int) click.y();
            return true;
        }

        return super.mouseClicked(click, doubled);
    }

    /**
     * Releases dragging state.
     */
    @Override
    public boolean mouseReleased(MouseButtonEvent click) {
        isDraggingScrollbar = false;
        isDraggingAnywhere = false;
        return super.mouseReleased(click);
    }

    /**
     * Handles mouse wheel scrolling.
     */
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        this.targetScrollOffset -= verticalAmount * scrollSpeed;
        float maxScroll = getAccurateMaxScroll();
        this.targetScrollOffset = Math.max(0.0F, Math.min(this.targetScrollOffset - (float)(verticalAmount * scrollSpeed), maxScroll));
        return true;
    }

    /**
     * Scrolls content when dragging the scrollbar.
     */
    @Override
    public boolean mouseDragged(MouseButtonEvent click, double offsetX, double offsetY) {
        initializeTopAndBottom();
        int scrollbarHeight = bottom - top;
        int contentHeight = getTotalContentHeight();
        float maxScroll = getAccurateMaxScroll();

        if (isDraggingScrollbar) {
            int thumbHeight = Math.max((int)(scrollbarHeight * ((float)scrollbarHeight / contentHeight)), 10);
            int trackHeight = scrollbarHeight - thumbHeight;

            float percent = (float)(click.y() - top - thumbHeight / 2) / (float)trackHeight;
            percent = Math.max(0.0F, Math.min(1.0F, percent));

            this.targetScrollOffset = percent * maxScroll;
            this.scrollOffset = targetScrollOffset;
            return true;
        } else if (isDraggingAnywhere) {
            int dy = (int)(click.y() - lastMouseY);
            this.targetScrollOffset -= dy;
            this.targetScrollOffset = Math.max(0, Math.min(this.targetScrollOffset, maxScroll));
            this.scrollOffset = targetScrollOffset;
            lastMouseY = (int) click.y();
            return true;
        }

        return super.mouseDragged(click, offsetX, offsetY);
    }

    /**
     * Handles keyboard arrow key scrolling.
     */
    @Override
    public boolean keyPressed(KeyEvent input) {
        float maxScroll = getAccurateMaxScroll();

        if (input.key() == InputConstants.KEY_DOWN) {
            this.targetScrollOffset = Math.min(this.targetScrollOffset + scrollSpeed, maxScroll);
            return true;
        } else if (input.key() == InputConstants.KEY_UP) {
            this.targetScrollOffset = Math.max(this.targetScrollOffset - scrollSpeed, 0);
            return true;
        }
        return super.keyPressed(input);
    }

    /**
     * Do not render version text.
     */
    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    /**
     * Render title text.
     */
    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }

    /**
     * Adds a button to scrollable text screen.
     */
    protected AbstractWidget addButtonObject(AbstractWidget button) {
        this.objectsToDisplay.add(new LineObject(null, 1.0F, null, 0, 0, button));
        return button;
    }

    /**
     * Gets the width of buttons.
     */
    protected int getButtonsWidth() {
        return 150;
    }

    /**
     * Returns the text file name to load.
     */
    protected abstract String getTextFile();

    /**
     * Data structure representing a line of text with a scale factor.
     */
    public record LineObject(Component text, float scale, Identifier imageId, int imageWidth, int imageHeight, AbstractWidget button) {
        public boolean isImage() {
            return imageId != null;
        }

        public boolean isButton() {
            return button != null;
        }

        @NotNull
        public AbstractWidget getButton() {
            return this.button;
        }

        public boolean isText() {
            return text != null && imageId == null && button == null;
        }
    }
}