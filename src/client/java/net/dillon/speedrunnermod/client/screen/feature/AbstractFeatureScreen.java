package net.dillon.speedrunnermod.client.screen.feature;

import net.dillon.speedrunnermod.client.screen.base.BaseModScreen;
import net.dillon.speedrunnermod.client.screen.base.RefreshingScreen;
import net.dillon.speedrunnermod.client.screen.base.feature.*;
import net.dillon.speedrunnermod.client.screen.feature.blocksanditems.SpeedrunnerIngotsScreen;
import net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying.FirstTimePlayingScreen;
import net.dillon.speedrunnermod.client.screen.feature.more.TripledDropsScreen;
import net.dillon.speedrunnermod.client.screen.feature.oresandworldgen.SpeedrunnersWastelandBiomeScreen;
import net.dillon.speedrunnermod.client.screen.feature.toolsandarmor.SpeedrunnerArmorScreen;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.dillon.speedrunnermod.SpeedrunnerMod.*;

/**
 * Used to create {@code feature screens}, for the soul purpose of displaying some of Speedrunner Mod's features.
 */
@Environment(EnvType.CLIENT)
public abstract class AbstractFeatureScreen extends BaseModScreen {
    protected final Screen parent;
    private final boolean renderBaseImage;
    private final boolean renderCraftingRecipe;
    private boolean moveButtons = false;
    private Screen category1Screen;
    private Screen category2Screen;
    private Screen category3Screen;
    private Text category1Text;
    private Text category2Text;
    private Text category3Text;
    private boolean hasFourthCategory;
    @Nullable
    private Screen category4Screen;
    @Nullable
    private Text category4Text;
    public ArrayList<ButtonWidget> buttons = new ArrayList<>();
    private ButtonWidget refreshButton;
    private final Map<ButtonWidget, Integer[]> buttonMap = new HashMap<>();
    public static boolean restartRequired = false;

    /**
     * A basic feature screen constructor.
     */
    public AbstractFeatureScreen(Screen parent, GameOptions options, Text title, boolean renderBaseImage, boolean renderCraftingRecipe) {
        super(parent, options, title);
        this.parent = parent;
        this.renderBaseImage = renderBaseImage;
        this.renderCraftingRecipe = renderCraftingRecipe;
    }

    /**
     * A feature screen constructor which additionally determines if certain buttons should be moved over.
     */
    public AbstractFeatureScreen(Screen parent, GameOptions options, Text title, boolean renderBaseImage, boolean renderCraftingRecipe, boolean moveButtons) {
        super(parent, options, title);
        this.parent = parent;
        this.renderBaseImage = renderBaseImage;
        this.renderCraftingRecipe = renderCraftingRecipe;
        this.moveButtons = moveButtons;
    }

    /**
     * A feature screen constructor, this one is typically used for the last page of a category.
     */
    public AbstractFeatureScreen(Screen parent, GameOptions options, Text title, boolean renderBaseImage, boolean renderCraftingRecipe, Screen category1Screen, Text category1Text, Screen category2Screen, Text category2Text, Screen category3Screen, Text category3Text, boolean hasFourthCategory, @Nullable Screen category4Screen, @Nullable Text category4Text) {
        super(parent, options, title);
        this.parent = parent;
        this.renderBaseImage = renderBaseImage;
        this.renderCraftingRecipe = renderCraftingRecipe;
        this.category1Screen = category1Screen;
        this.category2Screen = category2Screen;
        this.category3Screen = category3Screen;
        this.category1Text = category1Text;
        this.category2Text = category2Text;
        this.category3Text = category3Text;
        this.hasFourthCategory = hasFourthCategory;
        this.category4Screen = category4Screen;
        this.category4Text = category4Text;
    }

    /**
     * A feature screen constructor, also typically used for the last page of a category, which additionally determines if certain buttons should be moved around.
     */
    public AbstractFeatureScreen(Screen parent, GameOptions options, Text title, boolean renderBaseImage, boolean renderCraftingRecipe, boolean moveButtons, Screen category1Screen, Text category1Text, Screen category2Screen, Text category2Text, Screen category3Screen, Text category3Text, boolean hasFourthCategory, @Nullable Screen category4Screen, @Nullable Text category4Text) {
        super(parent, options, title);
        this.parent = parent;
        this.renderBaseImage = renderBaseImage;
        this.renderCraftingRecipe = renderCraftingRecipe;
        this.moveButtons = moveButtons;
        this.category1Screen = category1Screen;
        this.category2Screen = category2Screen;
        this.category3Screen = category3Screen;
        this.category1Text = category1Text;
        this.category2Text = category2Text;
        this.category3Text = category3Text;
        this.hasFourthCategory = hasFourthCategory;
        this.category4Screen = category4Screen;
        this.category4Text = category4Text;
    }

    /**
     * Creates the basic buttons for every feature screen.
     * <p>See comments inside of method for more documentation.</p>
     */
    @Override
    protected void init() {
        if (this.getScreenCategory() == ScreenCategory.FIRST_TIME_PLAYING) {
            this.buttons.clear();
        }

        int width = this.getButtonsWidth();
        int height = this.getButtonsHeight();

        // A starter feature screen (or the first page of a certain category of features)
        // consists of only a "Next" and "Done" button
        if (this.getScreenType() == ScreenType.STARTER) {
            this.addDrawableChild(ButtonWidget.builder(ModTexts.NEXT, button -> this.client.setScreen(this.getNextScreen())).dimensions(width, height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.close()).dimensions(width, height, 150, 20).build());
        }

        // A normal feature screen, which is any page between the first and last page of a certain category of features,
        // consists of a "Next", "Previous" and "Done" button
        else if (this.getScreenType() == ScreenType.NORMAL) {
            this.addDrawableChild(ButtonWidget.builder(ModTexts.NEXT, button -> this.client.setScreen(this.getNextScreen())).dimensions(width, height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(ModTexts.PREVIOUS, button -> this.client.setScreen(this.getPreviousScreen())).dimensions(width, height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.close()).dimensions(width, height, 150, 20).build());
        }

        // A final feature screen (the last page of a certain category of features),
        // Consists of all the other remaining categories to explore, along with a "Previous" and "Done" button.
        else if (this.getScreenType() == ScreenType.FINAL) {
            this.addDrawableChild(ButtonWidget.builder(this.category1Text, button -> this.client.setScreen(this.category1Screen)).dimensions(width, height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(this.category2Text, button -> this.client.setScreen(this.category2Screen)).dimensions(width, height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(this.category3Text, button -> this.client.setScreen(this.category3Screen)).dimensions(width, height, 150, 20).build());

            if (hasFourthCategory) {
                height += 24;
                this.addDrawableChild(ButtonWidget.builder(this.category4Text, button -> this.client.setScreen(this.category4Screen)).dimensions(width, height, 150, 20).build());
            }

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(ModTexts.PREVIOUS, button -> this.client.setScreen(this.getPreviousScreen())).dimensions(width, height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.close()).dimensions(width, height, 150, 20).build());
        }

        // An "end" feature screen, which is only used for the last page of a certain category and the last actual category,
        // Consists of all the other categories to re-explore, as well as a "Previous" and "Done" button.
        else if (this.getScreenType() == ScreenType.END) {
            height = this.height / 6 + 115;
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.learn_more"), button -> {
                this.openLink(ModLinks.WIKI, true);
            }).dimensions(this.getButtonsWidth(), height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.blocks_and_items"), button -> {
                this.client.setScreen(new SpeedrunnerIngotsScreen(this.parent, MinecraftClient.getInstance().options));
            }).dimensions(this.getButtonsWidth(), height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.tools_and_armor"), button -> {
                this.client.setScreen(new SpeedrunnerArmorScreen(this.parent, MinecraftClient.getInstance().options));
            }).dimensions(this.getButtonsWidth(), height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.ores_and_worldgen"), button -> {
                this.client.setScreen(new SpeedrunnersWastelandBiomeScreen(this.parent, MinecraftClient.getInstance().options));
            }).dimensions(this.getButtonsWidth(), height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(ModTexts.PREVIOUS, button -> {
                this.client.setScreen(new TripledDropsScreen(this.parent, MinecraftClient.getInstance().options));
            }).dimensions(this.getButtonsWidth(), height, 150, 20).build());

            height += 24;
            this.addDrawableChild(ButtonWidget.builder(ScreenTexts.DONE, button -> this.close()).dimensions(this.getButtonsWidth(), height, 150, 20).build());
        }

        // For custom screen types.
        else if (this.getScreenType() == ScreenType.FIRST_TIME_PLAYING) {
            this.addButtons();
            for (ButtonWidget button : buttons) {
                button.setDimensionsAndPosition(this.getButtonsX(), 20, this.getButtonsWidth(), height);
                this.buttonMap.put(button, new Integer[]{this.getButtonsX(), 20, this.getButtonsWidth(), height});
                this.addDrawableChild(button);
                height += 24;
            }
            this.refreshButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.BLANK, button -> {
                int pageNumber = this.getPageNumber(); // do this so page # isn't changed when refreshing the screen
                this.client.setScreen(new RefreshingScreen(parent, options));
                this.client.setScreen(this.determineRefreshedScreen(pageNumber));
            }).dimensions(this.getButtonsWidth() - 24, this.getButtonsHeight(), 20, 20).build());
            this.addDrawableChild(ButtonWidget.builder(Text.translatable("menu.quit"), button -> {
                if (this.getPageNumber() == this.getMaxPages()) {
                    options().client.firstTimePlaying = false;
                    ModOptions.saveConfig();
                }
                this.client.scheduleStop();
            }).dimensions(this.getButtonsWidth(), height, this.getButtonsX(), 20).build());
        }
    }

    /**
     * Determine what to do when the user closes a screen.
     */
    @Override
    public void close() {
        if (this.getScreenCategory() == ScreenCategory.BLOCKS_AND_ITEMS) {
            this.client.setScreen(new BlocksAndItemsScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategory.TOOLS_AND_ARMOR) {
            this.client.setScreen(new ToolsAndArmorScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategory.ORES_AND_WORLDGEN) {
            this.client.setScreen(new OresAndWorldgenScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategory.MORE) {
            this.client.setScreen(new MoreScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategory.DOOM_MODE) {
            this.client.setScreen(new DoomModeScreen(this.parent, MinecraftClient.getInstance().options));
        } else if (this.getScreenCategory() == ScreenCategory.FIRST_TIME_PLAYING) {
            warn("Cannot close!");
        } else {
            this.client.setScreen(new FeaturesScreen(this.parent, MinecraftClient.getInstance().options));
        }
    }

    /**
     * Renders the basic and additional objects on a feature screen.
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        if (this.shouldRenderTitleText()) {
            context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 15, 16777215);
        }

        List<OrderedText> screenText = this.client.textRenderer.wrapLines(this.textToDisplay(), 396);
        int textHeight = 100 - (screenText.size() - 2) * 10;
        textHeight = Math.max(textHeight, 70);
        for (OrderedText text : screenText) {
            context.drawCenteredTextWithShadow(this.textRenderer, text, this.width / 2, textHeight, 16777215);
            textHeight += 15;
        }

        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int farRightSide = rightSide + 273;
        int height = this.height - 24;
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("§lPage:§r " + this.getPageNumber() + "/" + this.getMaxPages()), farRightSide, height, 16777215);

        if (this.renderBaseImage) {
            if (screenText.size() <= 8) {
                context.drawTexture(RenderLayer::getGuiTextured, this.getImage(), this.getImageX(), this.getImageY(), 0.0F, 0.0F, this.getImageWidth(), this.getImageHeight(), this.getImageWidth(), this.getImageHeight());
            } else {
                throw new IllegalArgumentException();
            }
        }

        if (this.renderCraftingRecipe) {
            context.drawTexture(RenderLayer::getGuiTextured, this.getCraftingRecipeImage(), this.getCraftingRecipeImageX(), this.getCraftingRecipeImageY(), 0.0F, 0.0F, this.getCraftingRecipeImageWidth(), this.getCraftingRecipeImageHeight(), this.getCraftingRecipeImageWidth(), this.getCraftingRecipeImageHeight());
        }

        if (this.getDownscaledImage() != null) {
            this.renderFullResolutionDownscaledImage(context);
        }

        if (this.getScreenCategory() == ScreenCategory.FIRST_TIME_PLAYING) {
            int middle = this.width / 2 - 128;
            int logoHeight = 10;
            context.drawTexture(RenderLayer::getGuiTextured, Identifier.of("speedrunnermod:textures/gui/speedrunner_mod.png"), middle, logoHeight, 0.0F, 0.0F, 258, 32, 258, 32);
            context.drawTexture(RenderLayer::getGuiTextured, ofSpeedrunnerMod("textures/gui/button/refresh.png"), this.buttons.getFirst().getX() - 22, this.buttons.getFirst().getY() + 2, 0.0F, 0.0F, 16, 16, 16, 16);
            if (this.refreshButton.isHovered()) {
                this.renderBasicTooltip(ModTexts.REFRESH_TOOLTIP, context, mouseX, mouseY);
            }
        }
        this.renderCustomImage(context);
        this.renderTooltips(context, mouseX, mouseY);
    }

    /**
     * Allows for navigation between pages by using the left and right arrow keys.
     */
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_LEFT || keyCode == GLFW.GLFW_KEY_A) {
            if (this.getScreenCategory() == ScreenCategory.FIRST_TIME_PLAYING) {
                if (this.getPageNumber() != 1) {
                    this.client.setScreen(this.getPreviousScreen());
                }
            } else {
                this.client.setScreen(this.getPreviousScreen());
            }
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_RIGHT || keyCode == GLFW.GLFW_KEY_D) {
            this.client.setScreen(this.getNextScreen());
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
    }

    /**
     * Returns the maximum amount of pages for each category.
     */
    protected int getMaxPages() {
        switch (this.getScreenCategory()) {
            case BLOCKS_AND_ITEMS -> {
                return calculateMaxPages(ScreenCategory.BLOCKS_AND_ITEMS);
            }
            case TOOLS_AND_ARMOR -> {
                return calculateMaxPages(ScreenCategory.TOOLS_AND_ARMOR);
            }
            case ORES_AND_WORLDGEN -> {
                return calculateMaxPages(ScreenCategory.ORES_AND_WORLDGEN);
            }
            case MORE -> {
                return calculateMaxPages(ScreenCategory.MORE);
            }
            case DOOM_MODE -> {
                return calculateMaxPages(ScreenCategory.DOOM_MODE);
            }
            case FIRST_TIME_PLAYING ->  {
                return calculateMaxPages(ScreenCategory.FIRST_TIME_PLAYING);
            }
            default -> {
                return 0;
            }
        }
    }

    /**
     * Returns the page number of feature screens.
     */
    private Screen page(int pageNumber) {
        switch (this.getScreenCategory()) {
            case BLOCKS_AND_ITEMS -> {
                return determineScreen(pageNumber, ScreenCategory.BLOCKS_AND_ITEMS);
            }
            case TOOLS_AND_ARMOR -> {
                return determineScreen(pageNumber, ScreenCategory.TOOLS_AND_ARMOR);
            }
            case ORES_AND_WORLDGEN -> {
                return determineScreen(pageNumber, ScreenCategory.ORES_AND_WORLDGEN);
            }
            case MORE -> {
                return determineScreen(pageNumber, ScreenCategory.MORE);
            }
            case DOOM_MODE -> {
                return determineScreen(pageNumber, ScreenCategory.DOOM_MODE);
            }
            case FIRST_TIME_PLAYING -> {
                return determineScreen(pageNumber, ScreenCategory.FIRST_TIME_PLAYING);
            }
            default -> {
                return new FeaturesScreen(this.parent, this.options);
            }
        }
    }

    /**
     * Determine the screen to go to, based on the page number.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    private Screen determineScreen(int pageNumber, ScreenCategory category) {
        for (AbstractFeatureScreen screen : this.allFeatureScreens()) {
            if (screen.getPageNumber() == pageNumber && screen.getScreenCategory() == category) {
                return screen;
            }
        }
        return new FeaturesScreen(this.parent, this.options);
    }

    public Screen determineRefreshedScreen(int pageNumber) {
        for (AbstractFeatureScreen screen : this.allFeatureScreens()) {
            if (screen.getPageNumber() == pageNumber && screen.getScreenCategory() == ScreenCategory.FIRST_TIME_PLAYING) {
                return screen;
            }
        }
        return new FirstTimePlayingScreen(this.parent, this.options);
    }

    /**
     * Calculates the total amount of pages that are in a {@link ScreenCategory}.
     */
    protected int calculateMaxPages(ScreenCategory category) {
        int i = 0;
        for (AbstractFeatureScreen screen : this.allFeatureScreens()) {
            if (screen.getScreenCategory() == category) {
                i++;
            }
        }
        return i;
    }

    /**
     * Returns the category to display on the feature screen.
     */
    private String linesCategory() {
        if (this.getScreenCategory() == ScreenCategory.BLOCKS_AND_ITEMS) {
            return ".blocks_and_items.";
        } else if (this.getScreenCategory() == ScreenCategory.TOOLS_AND_ARMOR) {
            return ".tools_and_armor.";
        } else if (this.getScreenCategory() == ScreenCategory.ORES_AND_WORLDGEN) {
            return ".ores_and_worldgen.";
        } else if (this.getScreenCategory() == ScreenCategory.DOOM_MODE) {
            return ".doom_mode.";
        } else {
            return ".more.";
        }
    }

    /**
     * Returns the text to display on the feature screen.
     */
    private Text textToDisplay() {
        return this.getScreenCategory() != ScreenCategory.FIRST_TIME_PLAYING ? Text.translatable("speedrunnermod.features" + this.linesCategory() + this.linesKey() + ".text") : Text.translatable("speedrunnermod." + this.linesKey());
    }

    /**
     * Returns the buttons width on the screen.
     * <p>You should only {@link Override} this method if you use the {@link AbstractFeatureScreen#renderCustomImage(DrawContext)} method.</p>
     */
    protected int getButtonsWidth() {
        return this.renderCraftingRecipe || this.moveButtons ? this.width / 2 - 175 : this.width / 2 - 75;
    }

    /**
     * Returns the width for first time playing buttons width.
     */
    protected int getButtonsX() {
        return 150;
    }

    /**
     * Returns the height of the buttons on the screen.
     */
    protected int getButtonsHeight() {
        if (this.getScreenType() == ScreenType.FINAL) {
            return hasFourthCategory ? this.height / 6 + 115 : this.height / 6 + 135;
        } else {
            return this.height / 6 + 150;
        }
    }

    /**
     * Gets the X placement of the feature image, if present.
     */
    protected int getImageX() {
        return this.width / 2 - 15;
    }

    /**
     * Gets the Y placement of the feature image, if present.
     */
    protected int getImageY() {
        return 160;
    }

    /**
     * Gets the X placement of the crafting recipe image, if present.
     */
    protected int getCraftingRecipeImageX() {
        return this.width / 2 + 10;
    }

    /**
     * Gets the Y placement of the crafting recipe image, if present.
     */
    protected int getCraftingRecipeImageY() {
        return 205;
    }

    /**
     * Gets the width of the crafting recipe image, if present.
     */
    protected int getCraftingRecipeImageWidth() {
        return 171;
    }

    /**
     * Gets the height of the crafting recipe image, if present.
     */
    protected int getCraftingRecipeImageHeight() {
        return 78;
    }

    /**
     * Renders a {@code 1920x1080} image cut down to {@code 240x135} on the screen.
     */
    protected void renderFullResolutionDownscaledImage(DrawContext context) {
        context.drawTexture(RenderLayer::getGuiTextured, this.getDownscaledImage(), this.width / 2, 170, 0.0F, 0.0F, 240, 135, 240, 135);
    }

    /**
     * <p>Returns the downscaled image location.</p>
     * As long as this method returns {@code null}, no image will be rendered on the screen.
     */
    protected Identifier getDownscaledImage() {
        return null;
    }

    /**
     * Render custom tooltips on screen.
     */
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
    }

    /**
     * Render a custom image on a feature screen.
     */
    protected void renderCustomImage(DrawContext context) {
    }

    /**
     * Adds buttons to the {@code buttons} variable. Used to add all buttons to the screen.
     */
    protected void addButtons() {
    }

    /**
     * Determines if the screen should render the title text.
     */
    protected boolean shouldRenderTitleText() {
        return true;
    }

    /**
     * Gets the {@code next screen} that should be displayed when clicking the {@code "Next"} button.
     */
    @Nullable
    protected Screen getNextScreen() {
        return this.page(this.getPageNumber() + 1);
    }

    /**
     * <p>Gets the {@code previous screen}, which goes back to the screen displayed before.</p>
     * <p>On {@link ScreenType#STARTER} pages, there may not be a previous screen.</p>
     */
    @Nullable
    protected Screen getPreviousScreen() {
        return this.page(this.getPageNumber() - 1);
    }

    /**
     * Gets the {@code width} of the rendered image.
     */
    protected int getImageWidth() {
        return 0;
    }

    /**
     * Gets the {@code height} of the rendered image.
     */
    protected int getImageHeight() {
        return 0;
    }

    /**
     * Gets the key of the main feature on the feature screen.
     */
    @NotNull
    public abstract String linesKey();

    /**
     * Returns the page number of an {@link AbstractFeatureScreen}.
     */
    public abstract int getPageNumber();

    /**
     * Displays an image of the item on the screen.
     */
    protected abstract Identifier getImage();

    /**
     * Displays a {@code crafting recipe} of the item on the screen.
     */
    protected abstract Identifier getCraftingRecipeImage();

    /**
     * Returns the screen category that the feature screen fits in.
     */
    @NotNull
    public abstract ScreenCategory getScreenCategory();

    /**
     * Returns the type of feature screen.
     */
    @NotNull
    protected abstract ScreenType getScreenType();
}