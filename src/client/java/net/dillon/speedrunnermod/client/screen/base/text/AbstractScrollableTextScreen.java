package net.dillon.speedrunnermod.client.screen.base.text;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static net.dillon.speedrunnermod.SpeedrunnerMod.ofSpeedrunnerMod;

/**
 * A scrollable text screen.
 */
@Environment(EnvType.CLIENT)
public abstract class AbstractScrollableTextScreen extends AbstractModScreen {
    private final Screen parent;
    private final List<FormattedLine> lines = new ArrayList<>();
    private final int scrollSpeed = 12;
    private int scrollOffset;
    private float targetScrollOffset;
    private static final float SCROLL_LERP_SPEED = 0.2F;
    private boolean isDraggingScrollbar = false;
    private boolean isDraggingAnywhere = false;
    private int lastMouseY = -1;
    private int top, bottom;

    public AbstractScrollableTextScreen(Screen parent, GameOptions options, Text title) {
        super(parent, options, title);
        this.parent = parent;
    }

    /**
     * Loads and parses lines from the specified resource text file.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    private void loadAndPrintText(Identifier path) {
        if (path == null) {
            SpeedrunnerMod.warn("No text file found for this screen.");
            return;
        }
        try (BufferedReader reader = new BufferedReader(this.client.getResourceManager().openAsReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    // Add a "spacer" line to visually break lines
                    this.lines.add(new FormattedLine(Text.literal(" "), 1.0F));
                    continue;
                }

                this.lines.add(parseLine(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Parses legacy Minecraft formatting codes (e.g., §a, §l) into styled text.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    private Text parseLegacyFormattedText(String input) {
        Style currentStyle = Style.EMPTY;
        MutableText result = Text.literal("");

        for (int i = 0; i < input.length(); ) {
            char c = input.charAt(i);
            if (c == '§' && i + 1 < input.length()) {
                currentStyle = applyFormatCode(currentStyle, input.charAt(i + 1));
                i += 2;
            } else {
                result.append(Text.literal(String.valueOf(c)).setStyle(currentStyle));
                i++;
            }
        }

        return result;
    }

    /**
     * Applies a single Minecraft formatting code to the current style.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    private Style applyFormatCode(Style style, char code) {
        return switch (code) {
            case '0' -> style.withColor(0x000000);
            case '1' -> style.withColor(0x0000AA);
            case '2' -> style.withColor(0x00AA00);
            case '3' -> style.withColor(0x00AAAA);
            case '4' -> style.withColor(0xAA0000);
            case '5' -> style.withColor(0xAA00AA);
            case '6' -> style.withColor(0xFFAA00);
            case '7' -> style.withColor(0xAAAAAA);
            case '8' -> style.withColor(0x555555);
            case '9' -> style.withColor(0x5555FF);
            case 'a' -> style.withColor(0x55FF55);
            case 'b' -> style.withColor(0x55FFFF);
            case 'c' -> style.withColor(0xFF5555);
            case 'd' -> style.withColor(0xFF55FF);
            case 'e' -> style.withColor(0xFFFF55);
            case 'f' -> style.withColor(0xFFFFFF);
            case 'l' -> style.withBold(true);
            case 'o' -> style.withItalic(true);
            case 'n' -> style.withUnderline(true);
            case 'm' -> style.withStrikethrough(true);
            case 'k' -> style.withObfuscated(true);
            case 'r' -> Style.EMPTY;
            default -> style;
        };
    }

    /**
     * Parses a line, detecting headers ("#") and applying scaled formatting.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    private FormattedLine parseLine(String line) {
        int headingLevel = 0;
        while (headingLevel < line.length() && line.charAt(headingLevel) == '#') headingLevel++;

        float scale = switch (headingLevel) {
            case 1 -> 2.0F;
            case 2 -> 1.5F;
            case 3 -> 1.3F;
            case 4 -> 1.1F;
            case 5 -> 0.9F;
            default -> 1.0F;
        };

        String content = line.substring(headingLevel).stripLeading();
        return new FormattedLine(parseLegacyFormattedText(content), scale);
    }

    /**
     * Gets the wrap width for wrapping text.
     */
    private int getWrapWidth(FormattedLine line) {
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
    @ChatGPT(Credit.FULL_CREDIT)
    private int getTotalContentHeight() {
        int totalHeight = 0;
        for (FormattedLine line : this.lines) {
            float scale = line.scale;
            List<OrderedText> wrappedLines = this.textRenderer.wrapLines(line.text, this.getWrapWidth(line));
            int lineHeight = (int)((this.textRenderer.fontHeight + 2) * scale);
            totalHeight += wrappedLines.size() * lineHeight;
        }
        return totalHeight;
    }

    /**
     * Initializes top and bottom positions.
     */
    private void initializeTopAndBottom() {
        top = this.buttonList.getY() + 20;
        bottom = this.doneButton.getY() - 16;
    }

    /**
     * Calculates maximum scroll offset needed to fit all content.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    private int getAccurateMaxScroll() {
        initializeTopAndBottom();
        int visibleHeight = bottom - top;

        int totalHeight = getTotalContentHeight();
        return Math.max(0, totalHeight - visibleHeight);
    }

    /**
     * Initializes the screen: loads content and adds the Done button.
     */
    @Override
    protected void init() {
        this.initializeCustomButtonListWidget(); // Initialize button list widget, mainly used just for rendering the top and bottom lines
        this.lines.clear(); // Clear the lines to refresh it
        loadAndPrintText(ofSpeedrunnerMod(this.getTextFile())); // Print the text on the screen

        this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, (button) -> this.client.setScreen(this.parent)).dimensions(this.width / 2 - 100, this.height - 29, 200, 20).build());
        super.init();
    }

    /**
     * Renders scrollable formatted text.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        if (!isDraggingScrollbar && !isDraggingAnywhere) {
            this.scrollOffset += (int)((targetScrollOffset - this.scrollOffset) * SCROLL_LERP_SPEED);
        } else {
            this.scrollOffset = (int) targetScrollOffset;
        }
        super.render(context, mouseX, mouseY, delta);
        this.buttonList.render(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 15, 0xFFFFFF);

        initializeTopAndBottom();
        int scrollbarX = this.width - 10;
        int scrollbarWidth = 6;
        int scrollbarHeight = bottom - top;
        int contentHeight = getTotalContentHeight();
        double y = top - this.scrollOffset;

        for (FormattedLine line : this.lines) {
            float scale = line.scale;
            List<OrderedText> wrapped = this.textRenderer.wrapLines(line.text, this.getWrapWidth(line));
            int lineHeight = (int)((this.textRenderer.fontHeight + 2) * scale);

            for (OrderedText wrappedLine : wrapped) {
                if (y + lineHeight < top) {
                    y += lineHeight;
                    continue;
                }
                if (y >= bottom) break;

                context.getMatrices().push();
                context.getMatrices().translate(this.width / 2.0 - 175, y, 0);
                context.getMatrices().scale(scale, scale, 1);
                context.drawTextWithShadow(this.textRenderer, wrappedLine, 0, 0, 0xFFFFFF);
                context.getMatrices().pop();

                y += lineHeight;
            }
        }

        // Draw scrollbar if content overflows
        if (contentHeight > scrollbarHeight) {
            float scrollRatio = (float) scrollbarHeight / contentHeight;
            int thumbHeight = Math.max((int)(scrollbarHeight * scrollRatio), 10);
            int maxScroll = getAccurateMaxScroll();
            float scrollPercent = (float) this.scrollOffset / maxScroll;
            int thumbY = top + (int)(scrollPercent * (scrollbarHeight - thumbHeight));

            // Draw track
            context.fill(scrollbarX, top, scrollbarX + scrollbarWidth, bottom, 0xFF202020);

            // Hover effect for thumb
            boolean isHovered = mouseX >= scrollbarX && mouseX <= scrollbarX + scrollbarWidth && mouseY >= thumbY && mouseY <= thumbY + thumbHeight;
            int thumbColor = isHovered ? 0xFFFFFFFF : 0xFFA0A0A0;

            // Draw thumb
            context.fill(scrollbarX, thumbY, scrollbarX + scrollbarWidth, thumbY + thumbHeight, thumbColor);
        }
    }

    /**
     * Handles mouse wheel scrolling.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    @Override
    public boolean mouseScrolled(double mouseX, double mouseY, double horizontalAmount, double verticalAmount) {
        if (!isDraggingScrollbar) {
            this.targetScrollOffset -= (verticalAmount * scrollSpeed);
            int maxScroll = getAccurateMaxScroll();
            this.targetScrollOffset = Math.max(0, Math.min(this.targetScrollOffset, maxScroll));
        }
        return true;
    }

    /**
     * Enables scrollbar dragging when clicked.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (button == 0) {
            isDraggingAnywhere = true;
            lastMouseY = (int) mouseY;
        }

        initializeTopAndBottom();
        int scrollbarX = this.width - 10;
        int scrollbarWidth = 6;
        int scrollbarHeight = bottom - top;
        int contentHeight = getTotalContentHeight();

        if (mouseX >= scrollbarX && mouseX <= scrollbarX + scrollbarWidth &&
                mouseY >= top && mouseY <= bottom &&
                contentHeight > scrollbarHeight) {
            isDraggingScrollbar = true;
            lastMouseY = (int) mouseY;
            return true;
        }

        return super.mouseClicked(mouseX, mouseY, button);
    }

    /**
     * Releases dragging state.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    @Override
    public boolean mouseReleased(double mouseX, double mouseY, int button) {
        isDraggingScrollbar = false;
        isDraggingAnywhere = false;
        return super.mouseReleased(mouseX, mouseY, button);
    }

    /**
     * Scrolls content when dragging the scrollbar.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        initializeTopAndBottom();
        int scrollbarHeight = bottom - top;
        int contentHeight = getTotalContentHeight();
        int maxScroll = getAccurateMaxScroll();

        if (isDraggingScrollbar) {
            int thumbHeight = Math.max((int)(scrollbarHeight * ((float)scrollbarHeight / contentHeight)), 10);
            int trackHeight = scrollbarHeight - thumbHeight;

            float percent = (float)(mouseY - top - thumbHeight / 2) / (float)trackHeight;
            percent = Math.max(0.0F, Math.min(1.0F, percent));

            targetScrollOffset = percent * maxScroll;
            scrollOffset = (int) targetScrollOffset;
            return true;
        } else if (isDraggingAnywhere) {
            int dy = (int)(mouseY - lastMouseY);
            targetScrollOffset -= dy;
            targetScrollOffset = Math.max(0, Math.min(targetScrollOffset, maxScroll));
            scrollOffset = (int) targetScrollOffset;
            lastMouseY = (int) mouseY;
            return true;
        }

        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    /**
     * Handles keyboard arrow key scrolling.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        int maxScroll = getAccurateMaxScroll();

        if (keyCode == 264) { // Down arrow
            this.targetScrollOffset = Math.min(this.targetScrollOffset + scrollSpeed, maxScroll);
            return true;
        } else if (keyCode == 265) { // Up arrow
            this.targetScrollOffset = Math.max(this.targetScrollOffset - scrollSpeed, 0);
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    /**
     * Default columns for all scrollable screens is 2.
     */
    @Override
    protected int columns() {
        return 2;
    }

    /**
     * Do not render version text.
     */
    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    /**
     * Not an options screen.
     */
    @Override
    protected boolean isOptionsScreen() {
        return false;
    }

    /**
     * Render title text.
     */
    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }

    @Override
    public void close() {
        this.client.setScreen(this.parent);
    }

    /**
     * Helper for referencing changelog file paths.
     */
    protected String inChangelogsFolder(String fileName) {
        return "texts/changelogs/" + fileName + ".txt";
    }

    /**
     * Helper for referencing file paths in texts directory.
     */
    protected String inTextsFolder(String fileName) {
        return "texts/" + fileName + ".txt";
    }

    /**
     * Returns the text file name to load.
     */
    protected abstract String getTextFile();

    /**
     * Data structure representing a line of text with a scale factor.
     */
    @ChatGPT(Credit.PARTIAL_CREDIT)
    private record FormattedLine(Text text, float scale) {}
}