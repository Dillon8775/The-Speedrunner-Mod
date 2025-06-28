package net.dillon.speedrunnermod.client.screen.feature;

import net.dillon.speedrunnermod.client.screen.base.AbstractScrollableScreen;
import net.dillon.speedrunnermod.client.screen.base.RestartRequiredScreen;
import net.dillon.speedrunnermod.client.screen.feature.blocksanditems.SpeedrunnerIngotsScreen;
import net.dillon.speedrunnermod.client.screen.feature.oresandworldgen.SpeedrunnersWastelandBiomeScreen;
import net.dillon.speedrunnermod.client.screen.feature.toolsandarmor.SpeedrunnerArmorScreen;
import net.dillon.speedrunnermod.client.screen.options.MainOptionsScreen;
import net.dillon.speedrunnermod.client.util.ModLinks;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.util.ChatGPT;
import net.dillon.speedrunnermod.util.Credit;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;

import java.util.function.Function;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.warn;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;

/**
 * Used to create {@code feature screens}, for the soul purpose of displaying some of Speedrunner Mod's features.
 */
@Environment(EnvType.CLIENT)
public abstract class AbstractFeatureScreen extends AbstractScrollableScreen {
    protected final Screen parent;
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
    private ButtonWidget nextButton, previousButton;
    public static boolean restartRequired = false;

    /**
     * A basic feature screen constructor.
     */
    public AbstractFeatureScreen(Screen parent, Text title) {
        super(parent, title);
        this.parent = parent;
    }

    /**
     * A feature screen constructor, this one is typically used for the last page of a category.
     */
    public AbstractFeatureScreen(Screen parent, Text title, Screen category1Screen, Text category1Text, Screen category2Screen, Text category2Text, Screen category3Screen, Text category3Text, boolean hasFourthCategory, @Nullable Screen category4Screen, @Nullable Text category4Text) {
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

//        if (this.getScreenCategory() == ScreenCategory.FIRST_TIME_PLAYING) {
//            this.buttons().clear();
//        }

        // A starter feature screen (or the first page of a certain category of features)
        // consists of only a "Next" and "Done" button
        if (this.getScreenType() != ScreenType.FINAL && this.getScreenType() != ScreenType.END && this.getScreenType() != ScreenType.FIRST_TIME_PLAYING) {
            this.nextButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.NEXT, button -> {
                this.client.setScreen(this.getNextScreen());
            }).dimensions(this.getButtonsRightSide() + 100, this.getDoneButtonsHeight(), 20, 20).build());
        }

        // A normal feature screen, which is any page between the first and last page of a certain category of features,
        // consists of a "Next", "Previous" and "Done" button
        if (this.getScreenType() != ScreenType.STARTER && this.getScreenType() != ScreenType.FIRST_TIME_PLAYING) {
            this.previousButton = this.addDrawableChild(ButtonWidget.builder(ModTexts.PREVIOUS, button -> {
                this.client.setScreen(this.getPreviousScreen());
            }).dimensions(this.getButtonsLeftSide() + 30, this.getDoneButtonsHeight(), 20, 20).build());
        }

        // A final feature screen (the last page of a certain category of features),
        // Consists of all the other remaining categories to explore, along with a "Previous" and "Done" button.
        if (this.getScreenType() == ScreenType.FINAL) {
            this.addButtonObject(ButtonWidget.builder(this.category1Text, button -> this.client.setScreen(this.category1Screen)).build());
            this.addButtonObject(ButtonWidget.builder(this.category2Text, button -> this.client.setScreen(this.category2Screen)).build());
            this.addButtonObject(ButtonWidget.builder(this.category3Text, button -> this.client.setScreen(this.category3Screen)).build());
            if (hasFourthCategory) {
                this.addButtonObject(ButtonWidget.builder(this.category4Text, button -> this.client.setScreen(this.category4Screen)).build());
            }
        }

        // An "end" feature screen, which is only used for the last page of a certain category and the last actual category,
        // Consists of all the other categories to re-explore, as well as a "Previous" and "Done" button.
        if (this.getScreenType() == ScreenType.END) {
            this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.learn_more"), button -> {
                this.openLink(ModLinks.MODRINTH, true);
            }).build());

            this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.blocks_and_items"), button -> {
                this.client.setScreen(new SpeedrunnerIngotsScreen(this.parent));
            }).build());

            this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.tools_and_armor"), button -> {
                this.client.setScreen(new SpeedrunnerArmorScreen(this.parent));
            }).build());

            this.addButtonObject(ButtonWidget.builder(Text.translatable("speedrunnermod.menu.features.ores_and_worldgen"), button -> {
                this.client.setScreen(new SpeedrunnersWastelandBiomeScreen(this.parent));
            }).build());
        }
    }

    /**
     * Renders the basic and additional objects on a feature screen.
     */
    @Override
    public void render(DrawContext context, int mouseX, int mouseY, float delta) {
        super.render(context, mouseX, mouseY, delta);
        int leftSide = this.width / 2 - 155;
        int rightSide = leftSide + 160;
        int farRightSide = rightSide + 273;
        int height = this.height - 24;
        context.drawCenteredTextWithShadow(this.textRenderer, Text.literal("§lPage:§r " + getPageNumber() + "/" + this.getMaxPages()), farRightSide, height, 16777215);

        if (this.getScreenCategory() == ScreenCategory.FIRST_TIME_PLAYING) {
            int middle = this.width / 2 - 128;
            int logoHeight = 10;
            context.drawTexture(RenderLayer::getGuiTextured, Identifier.of("speedrunnermod:textures/gui/speedrunner_mod.png"), middle, logoHeight, 0.0F, 0.0F, 258, 32, 258, 32);
        }

        this.renderTooltips(context, mouseX, mouseY);
    }

    /**
     * Determine what to do when the user closes a screen.
     */
    @Override
    public void close() {
        if (this.getScreenCategory() == ScreenCategory.BLOCKS_AND_ITEMS) {
            this.client.setScreen(new BlocksAndItemsScreen(this.parent));
        } else if (this.getScreenCategory() == ScreenCategory.TOOLS_AND_ARMOR) {
            this.client.setScreen(new ToolsAndArmorScreen(this.parent));
        } else if (this.getScreenCategory() == ScreenCategory.ORES_AND_WORLDGEN) {
            this.client.setScreen(new OresAndWorldgenScreen(this.parent));
        } else if (this.getScreenCategory() == ScreenCategory.MISCELLANEOUS) {
            this.client.setScreen(new MiscellaneousScreen(this.parent));
        } else if (this.getScreenCategory() == ScreenCategory.DOOM_MODE) {
            this.client.setScreen(new DoomModeScreen(this.parent));
        } else if (this.getScreenCategory() == ScreenCategory.FIRST_TIME_PLAYING) {
            warn("Cannot close!");
        } else {
            this.client.setScreen(new FeaturesScreen(this.parent));
        }
    }

    /**
     * Allows for navigation between pages by using the left and right arrow keys, and to reload the screen based if the user presses "R" on their keyboard.
     */
    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (keyCode == GLFW.GLFW_KEY_R) {
            this.refreshFeatureScreen(getPageNumber(), this.getScreenCategory());
            return true;
        }
        if (keyCode == GLFW.GLFW_KEY_LEFT || keyCode == GLFW.GLFW_KEY_A) {
            if (this.getPageNumber() != 1) {
                this.client.setScreen(this.getPreviousScreen());
            }
            return true;
        } else if (keyCode == GLFW.GLFW_KEY_RIGHT || keyCode == GLFW.GLFW_KEY_D) {
            if (this.getPageNumber() != this.getMaxPages()) {
                this.client.setScreen(this.getNextScreen());
            }
            return true;
        }
        return super.keyPressed(keyCode, scanCode, modifiers);
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
            case BLOCKS_AND_ITEMS -> {
                return this.inBlocksAndItemsFolder(this.linesKey());
            }
            case DOOM_MODE -> {
                return this.inDoomModeFolder(this.linesKey());
            }
            case FIRST_TIME_PLAYING -> {
                return this.inTextsFolder(this.linesKey());
            }
            case MISCELLANEOUS -> {
                return this.inMiscellaneousFolder(this.linesKey());
            }
            case ORES_AND_WORLDGEN -> {
                return this.inOresAndWorldgenFolder(this.linesKey());
            }
            case TOOLS_AND_ARMOR -> {
                return this.inToolsAndArmor(this.linesKey());
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
            case MISCELLANEOUS -> {
                return calculateMaxPages(ScreenCategory.MISCELLANEOUS);
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
            case MISCELLANEOUS -> {
                return determineScreen(pageNumber, ScreenCategory.MISCELLANEOUS);
            }
            case DOOM_MODE -> {
                return determineScreen(pageNumber, ScreenCategory.DOOM_MODE);
            }
            case FIRST_TIME_PLAYING -> {
                return determineScreen(pageNumber, ScreenCategory.FIRST_TIME_PLAYING);
            }
            default -> {
                return new FeaturesScreen(this.parent);
            }
        }
    }

    /**
     * Determine the screen to go to, based on the page number.
     */
    @ChatGPT(Credit.FULL_CREDIT)
    private Screen determineScreen(int pageNumber, ScreenCategory category) {
        for (Function<Screen, AbstractFeatureScreen> featureScreenConstructor : SpeedrunnerModClient.ALL_FEATURE_SCREENS) {
            AbstractFeatureScreen screen = featureScreenConstructor.apply(this.parent);
            if (screen.getPageNumber() == pageNumber && screen.getScreenCategory() == category) {
                return screen;
            }
        }
        return new FeaturesScreen(this.parent);
    }

    /**
     * Calculates the total amount of pages that are in a {@link ScreenCategory}.
     */
    protected int calculateMaxPages(ScreenCategory category) {
        int i = 0;
        for (Function<Screen, AbstractFeatureScreen> featureScreenConstructor : SpeedrunnerModClient.ALL_FEATURE_SCREENS) {
            AbstractFeatureScreen screen = featureScreenConstructor.apply(this.parent);
            if (screen.getScreenCategory() == category) {
                i++;
            }
        }
        return i;
    }

    /**
     * Render custom tooltips on screen.
     */
    protected void renderTooltips(DrawContext context, int mouseX, int mouseY) {
        if (this.getScreenType() == ScreenType.STARTER || this.getScreenType() == ScreenType.NORMAL) {
            if (this.nextButton.isHovered()) {
                this.renderBasicTooltip(ModTexts.NEXT_TOOLTIP, context, mouseX, mouseY);
            }
            if (this.getScreenType() == ScreenType.NORMAL && this.previousButton.isHovered()) {
                this.renderBasicTooltip(ModTexts.PREVIOUS_TOOLTIP, context, mouseX, mouseY);
            }
        } else if (this.getScreenType() == ScreenType.FINAL || this.getScreenType() == ScreenType.END) {
            if (this.previousButton.isHovered()) {
                this.renderBasicTooltip(ModTexts.PREVIOUS_TOOLTIP, context, mouseX, mouseY);
            }
        }
    }

    /**
     * Toggles a feature when pressing the enable/disable button.
     */
    protected void refreshNonRestartableFeature() {
        saveAllChanges();
        this.refreshFeatureScreen(this.getPageNumber(), this.getScreenCategory());
    }

    /**
     * Toggles a feature that requires a restart when pressing the enable/disable button.
     */
    protected void refreshRestartableFeature() {
        RestartRequiredScreen.getCurrentOptions();
        this.client.setScreen(new MainOptionsScreen(this));
    }

    /**
     * Gets the {@code next screen} that should be displayed when clicking the {@code "Next"} button.
     */
    @Nullable
    protected Screen getNextScreen() {
        return this.page(getPageNumber() + 1);
    }

    /**
     * <p>Gets the {@code previous screen}, which goes back to the screen displayed before.</p>
     * <p>On {@link ScreenType#STARTER} pages, there may not be a previous screen.</p>
     */
    @Nullable
    protected Screen getPreviousScreen() {
        return this.page(getPageNumber() - 1);
    }

    /**
     * Helper for referencing file paths in features/blocksanditems directory.
     */
    protected String inBlocksAndItemsFolder(String fileName) {
        return "texts/features/blocksanditems/" + fileName + ".txt";
    }

    /**
     * Helper for referencing file paths in features/blocksanditems directory.
     */
    protected String inDoomModeFolder(String fileName) {
        return "texts/features/doommode/" + fileName + ".txt";
    }

    /**
     * Helper for referencing file paths in features/referencing directory.
     */
    protected String inMiscellaneousFolder(String fileName) {
        return "texts/features/miscellaneous/" + fileName + ".txt";
    }

    /**
     * Helper for referencing file paths in features/oresandworldgen directory.
     */
    protected String inOresAndWorldgenFolder(String fileName) {
        return "texts/features/oresandworldgen/" + fileName + ".txt";
    }

    /**
     * Helper for referencing file paths in features/toolsandarmor directory.
     */
    protected String inToolsAndArmor(String fileName) {
        return "texts/features/toolsandarmor/" + fileName + ".txt";
    }

    /**
     * Returns the page number of a screen.
     */
    public abstract int getPageNumber();

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