package net.dillon.speedrunnermod.screen;

import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.util.CommonColors;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;

/**
 * Used to create {@code feature screens}, for the soul purpose of displaying some of Speedrunner Mod's features.
 */
public abstract class AbstractFeatureScreen extends AbstractScrollableScreen {
    protected final Screen parent;
    private Screen category1Screen;
    private Screen category2Screen;
    private Screen category3Screen;
    private Component category1Text;
    private Component category2Text;
    private Component category3Text;
    private boolean hasFourthCategory;
    @Nullable
    private Screen category4Screen;
    @Nullable
    private Component category4Text;
    private Button nextButton, previousButton;
    protected static boolean restartRequired = false;

    /**
     * A basic feature screen constructor.
     */
    public AbstractFeatureScreen(Screen parent, Component title) {
        super(parent, title);
        this.parent = parent;
    }

    /**
     * A feature screen constructor, this one is typically used for the last page of a category.
     */
    public AbstractFeatureScreen(Screen parent, Component title, Screen category1Screen, Component category1Text, Screen category2Screen, Component category2Text, Screen category3Screen, Component category3Text, boolean hasFourthCategory, @Nullable Screen category4Screen, @Nullable Component category4Text) {
        super(parent, title);
        this.parent = parent;
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
        super.init();

        if (this.getScreenCategory() != ScreenCategory.SECRET_DOOM_MODE) {
            // A starter feature screen (or the first page of a certain category of features)
            // consists of only a "Next" and "Done" button
            if (this.getScreenType() != ScreenType.LAST_PAGE && this.getScreenType() != ScreenType.END && this.getScreenType() != ScreenType.FIRST_TIME_PLAYING) {
                this.nextButton = this.addRenderableWidget(Button.builder(ModTexts.NEXT_ARROW, button -> {
                    this.minecraft.setScreen(this.getNextScreen());
                }).bounds(this.getButtonsRightSide() + 100, this.getDoneButtonHeight(), 20, 20).build());
            }

            // A normal feature screen, which is any page between the first and last page of a certain category of features,
            // consists of a "Next", "Previous" and "Done" button
            if (this.getScreenType() != ScreenType.FIRST_PAGE && this.getScreenType() != ScreenType.FIRST_TIME_PLAYING) {
                this.previousButton = this.addRenderableWidget(Button.builder(ModTexts.PREVIOUS, button -> {
                    this.minecraft.setScreen(this.getPreviousScreen());
                }).bounds(this.getButtonsLeftSide() + 30, this.getDoneButtonHeight(), 20, 20).build());
            }

            // A final feature screen (the last page of a certain category of features),
            // Consists of all the other remaining categories to explore, along with a "Previous" and "Done" button.
            if (this.getScreenType() == ScreenType.LAST_PAGE) {
                this.addButtonObject(Button.builder(this.category1Text, button -> this.minecraft.setScreen(this.category1Screen)).build());
                this.addButtonObject(Button.builder(this.category2Text, button -> this.minecraft.setScreen(this.category2Screen)).build());
                this.addButtonObject(Button.builder(this.category3Text, button -> this.minecraft.setScreen(this.category3Screen)).build());
                if (this.hasFourthCategory) {
                    this.addButtonObject(Button.builder(this.category4Text, button -> this.minecraft.setScreen(this.category4Screen)).build());
                }
            }
        }
    }

    /**
     * Renders the basic and additional objects on a feature screen.
     */
    @Override
    public void extractRenderState(GuiGraphicsExtractor context, int mouseX, int mouseY, float delta) {
        super.extractRenderState(context, mouseX, mouseY, delta);
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int farRightSide = rightSide + 273;
        int height = this.height - 24;
        if (this.getScreenCategory() != ScreenCategory.SECRET_DOOM_MODE) {
            context.centeredText(this.font, Component.literal("§lPage:§r " + getPageNumber() + "/" + this.getMaxPages()), this.getScreenCategory() == ScreenCategory.FIRST_TIME_PLAYING ? this.width / 2 : farRightSide, height, CommonColors.WHITE);
        }

        if (this.getScreenCategory() == ScreenCategory.FIRST_TIME_PLAYING) {
            int middle = this.width / 2 - 128;
            int logoHeight = 10;
            context.blit(RenderPipelines.GUI_TEXTURED, Identifier.parse("speedrunnermod:textures/gui/speedrunner_mod.png"), middle, logoHeight, 0.0F, 0.0F, 258, 32, 258, 32);
        }

        this.renderTooltips(context, mouseX, mouseY);
    }

    /**
     * Determine what to do when the user closes a feature screen.
     */
    @Override
    public void onClose() {
        switch (this.getScreenCategory()) {
            case FIRST_TIME_PLAYING -> warn("Cannot close this screen!");
            default -> this.minecraft.setScreen(new MainScreen(this.parent));
        }
    }

    /**
     * Allows for navigation between pages by using the left and right arrow keys, and to reload the screen based if the user presses "R" on their keyboard.
     */
    @Override
    public boolean keyPressed(KeyEvent input) {
        if (input.key() == GLFW.GLFW_KEY_LEFT || input.key() == GLFW.GLFW_KEY_A) {
            if (this.getPageNumber() != 1) {
                this.minecraft.setScreen(this.getPreviousScreen());
            }
            return true;
        } else if (input.key() == GLFW.GLFW_KEY_RIGHT || input.key() == GLFW.GLFW_KEY_D) {
            if (this.getScreenCategory() == ScreenCategory.FIRST_TIME_PLAYING && !(this.getPageNumber() < 3)) {
                warn("Please choose an option!");
            } else if (this.getPageNumber() != this.getMaxPages()) {
                this.minecraft.setScreen(this.getNextScreen());
            }
            return true;
        }
        return super.keyPressed(input);
    }

    /**
     * Determines if the screen should render the title text.
     */
    @Override
    protected boolean shouldRenderTitleText() {
        return this.getScreenCategory() != ScreenCategory.FIRST_TIME_PLAYING;
    }

    /**
     * Get the respective text file based on screen category.
     */
    @Override
    protected String getTextFile() {
        switch (this.getScreenCategory()) {
            case SECRET_DOOM_MODE -> {
                return this.inSecretDoomModeFolder(this.linesKey());
            }
            case FIRST_TIME_PLAYING -> {
                return this.inTextsFolder(this.linesKey());
            }
            default -> {
                return this.linesKey();
            }
        }
    }

    /**
     * No page ID's for feature screens, we use page numbers instead.
     */
    @Override
    public String pageId() {
        return null;
    }

    /**
     * @return the maximum amount of pages for each category.
     */
    protected int getMaxPages() {
        return this.getCategoryScreenClasses(getScreenCategory()).size();
    }

    /**
     * @return the page number of feature screens.
     */
    private Screen page(int pageNumber) {
        switch (this.getScreenCategory()) {
            case SECRET_DOOM_MODE -> {
                return determineScreen(pageNumber, ScreenCategory.SECRET_DOOM_MODE);
            }
            case FIRST_TIME_PLAYING -> {
                return determineScreen(pageNumber, ScreenCategory.FIRST_TIME_PLAYING);
            }
            default -> {
                return new MainScreen(this.parent);
            }
        }
    }

    /**
     * @return the list of category screens to go by for each screen category.
     */
    private List<Class<? extends AbstractFeatureScreen>> getCategoryScreenClasses(ScreenCategory category) {
        switch (category) {
            case SECRET_DOOM_MODE -> {
                return this.secretDoomModeScreens();
            }
            default -> {
                return this.firstTimePlayingScreens();
            }
        }
    }

    /**
     * Determine the screen to go to, based on the page number.
     */
    private Screen determineScreen(int pageNumber, ScreenCategory category) {
        List<Class<? extends AbstractFeatureScreen>> screenClasses = getCategoryScreenClasses(category);
        if (pageNumber > 0 && pageNumber <= screenClasses.size()) {
            try {
                Class<? extends AbstractFeatureScreen> screenClass = screenClasses.get(pageNumber - 1);
                return screenClass.getDeclaredConstructor(Screen.class).newInstance(this.parent);
            } catch (Exception e) {
                SpeedrunnerMod.error("Failed to create screen: " + e.getMessage());
            }
        }
        return new MainScreen(this.parent);
    }

    /**
     * @return the page number for the screen.
     */
    public int getPageNumber() {
        List<Class<? extends AbstractFeatureScreen>> screenClasses = getCategoryScreenClasses(getScreenCategory());
        for (int i = 0; i < screenClasses.size(); i++) {
            if (screenClasses.get(i) == this.getClass()) {
                return i + 1;
            }
        }
        return 1;
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
     * <p>On {@link ScreenType#FIRST_PAGE} pages, there may not be a previous screen.</p>
     */
    @Nullable
    protected Screen getPreviousScreen() {
        return this.page(this.getPageNumber() - 1);
    }

    /**
     * Render custom tooltips on screen.
     */
    protected void renderTooltips(GuiGraphicsExtractor context, int mouseX, int mouseY) {
        if (this.getScreenCategory() != ScreenCategory.SECRET_DOOM_MODE) {
            if (this.getScreenType() == ScreenType.FIRST_PAGE || this.getScreenType() == ScreenType.DEFAULT) {
                if (this.nextButton.isHovered()) {
                    this.renderBasicTooltip(ModTexts.NEXT_ARROW_TOOLTIP, context, mouseX, mouseY);
                }
                if (this.getScreenType() == ScreenType.DEFAULT && this.previousButton.isHovered()) {
                    this.renderBasicTooltip(ModTexts.PREVIOUS_TOOLTIP, context, mouseX, mouseY);
                }
            } else if (this.getScreenType() == ScreenType.LAST_PAGE || this.getScreenType() == ScreenType.END) {
                if (this.previousButton.isHovered()) {
                    this.renderBasicTooltip(ModTexts.PREVIOUS_TOOLTIP, context, mouseX, mouseY);
                }
            }
        }
    }
    /**
     * Helper for referencing file paths in features/secretdoommode directory.
     */
    protected String inSecretDoomModeFolder(String fileName) {
        return "texts/secretdoommode/" + fileName + ".txt";
    }

    /**
     * Gets the key of the main feature on the feature screen.
     */
    @NotNull
    public abstract String linesKey();

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