package net.dillon.speedrunnermod.screen;

import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.screen.feature.blocksanditems.*;
import net.dillon.speedrunnermod.screen.feature.doommode.*;
import net.dillon.speedrunnermod.screen.feature.firsttimeplaying.*;
import net.dillon.speedrunnermod.screen.feature.miscellaneous.*;
import net.dillon.speedrunnermod.screen.feature.oresandworldgen.*;
import net.dillon.speedrunnermod.screen.feature.secretdoommode.*;
import net.dillon.speedrunnermod.screen.feature.toolsandarmor.*;
import net.dillon.speedrunnermod.util.ModTexts;
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

import java.util.List;
import java.util.function.Function;

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
     * Refreshes a base mod screen.
     */
    public void refreshScreen(String id) {
        this.minecraft.gui.setScreen(new TemporaryScreen(this.lastScreen, ModTexts.REFRESHING));
        this.minecraft.gui.setScreen(this.determineRefreshedScreen(id));
    }

    /**
     * Refreshes a feature screen.
     */
    public void refreshFeatureScreen(int pageNumber, ScreenCategory screenCategory) {
        this.minecraft.gui.setScreen(new TemporaryScreen(this.lastScreen, ModTexts.REFRESHING));
        this.minecraft.gui.setScreen(this.determineRefreshedFeatureScreen(pageNumber, screenCategory));
    }

    /**
     * Determines the refreshed screen for base screens.
     */
    private Screen determineRefreshedScreen(String pageId) {
        for (Function<Screen, AbstractModScreen> modScreenConstructor : SpeedrunnerModClient.ALL_MOD_SCREENS) {
            AbstractModScreen screen = modScreenConstructor.apply(this.lastScreen);
            if (screen.pageId().equals(pageId)) {
                return screen;
            }
        }
        return new MainScreen(this.lastScreen);
    }

    /**
     * Determines the refreshed screen for feature screens.
     */
    private Screen determineRefreshedFeatureScreen(int pageNumber, ScreenCategory screenCategory) {
        for (Function<Screen, AbstractFeatureScreen> featureScreenConstructor : SpeedrunnerModClient.ALL_FEATURE_SCREENS) {
            AbstractFeatureScreen screen = featureScreenConstructor.apply(this.lastScreen);
            if (screen.getPageNumber() == pageNumber && screen.getScreenCategory() == screenCategory) {
                if (this instanceof AbstractFeatureScreen previous) {
                    screen.targetScrollOffset = previous.targetScrollOffset;
                    screen.scrollOffset = previous.scrollOffset;
                }
                return screen;
            }
        }
        return new FirstTimePlayingScreen(this.lastScreen);
    }

    /**
     * @return all {@code first time playing} feature screens, in order of their {@code page number.}
     */
    public List<Class<? extends AbstractFeatureScreen>> firstTimePlayingScreens() {
        return List.of(
                FirstTimePlayingScreen.class,
                KeyFeaturesScreen.class,
                ModeOptionScreen.class,
                ReadyToPlayScreen.class,
                FTPRestartRequiredScreen.class
        );
    }

    /**
     * @return all {@code blocks and items} feature screens, in order of their {@code page number.}
     */
    public List<Class<? extends AbstractFeatureScreen>> blocksAndItemsScreens() {
        return List.of(
                SpeedrunnerIngotsScreen.class,
                SpeedrunnerNuggetsScreen.class,
                SpeedrunnerBlocksScreen.class,
                RawSpeedrunnerScreen.class,
                SpeedrunnerWoodScreen.class,
                DeadSpeedrunnerWoodScreen.class,
                SpeedrunnerPaddleScreen.class,
                FireproofBoatsScreen.class,
                IgneousRocksScreen.class,
                SpeedrunnersEyeScreen.class,
                EyeOfInfernoScreen.class,
                EyeOfAnnulScreen.class,
                EnderThrusterScreen.class,
                EnderMatterScreen.class,
                InfiniPearlScreen.class,
                SpeedrunnersTotemScreen.class,
                DragonsPearlScreen.class,
                DragonsAuraScreen.class,
                DragonsFireballScreen.class,
                RaidEradicatorScreen.class,
                PiglinAwakenerScreen.class,
                BlazeSpotterScreen.class,
                InventoryPreserverScreen.class,
                SpeedrunnerBulkScreen.class,
                SpeedrunnersWorkbenchScreen.class
        );
    }

    /**
     * @return all {@code tools and armor} feature screens, in order of their {@code page number.}
     */
    public List<Class<? extends AbstractFeatureScreen>> toolsAndArmorScreens() {
        return List.of(
                SpeedrunnerArmorScreen.class,
                GoldenSpeedrunnerArmorScreen.class,
                GoldenSmithingTemplateScreen.class,
                SpeedrunnerSafeBootsScreen.class,
                SpeedrunnerNautilusArmorScreen.class,
                SpeedrunnerHarnessScreen.class,
                SpeedrunnerShieldScreen.class,
                GoldenShieldScreen.class,
                SpeedrunnerSpearScreen.class,
                SpeedrunnerBowAndCrossbowScreen.class,
                SpeedrunnerFlintAndSteelScreen.class,
                SpeedrunnerShearsScreen.class,
                DashEnchantmentScreen.class,
                CooldownEnchantmentScreen.class,
                WitheredEnchantmentScreen.class,
                DragonsSwordScreen.class
        );
    }

    /**
     * @return all {@code ores and worldgen} feature screens, in order of their {@code page number.}
     */
    public List<Class<? extends AbstractFeatureScreen>> oresAndWorldGenFeatureScreens() {
        return List.of(
                SpeedrunnersWastelandBiomeScreen.class,
                SpeedrunnerOresScreen.class,
                ExperienceOresScreen.class,
                ExperienceFragmentScreen.class,
                IgneousOresScreen.class,
                CommonOresScreen.class,
                BetterBiomesScreen.class,
                StructuresScreen.class,
                FortressesBastionsAndStrongholdsScreen.class
        );
    }

    /**
     * @return all {@code miscellaneous} feature screens, in order of their {@code page number.}
     */
    public List<Class<? extends AbstractFeatureScreen>> miscellaneousFeatureScreens() {
        return List.of(
                ICarusModeScreen.class,
                InfiniPearlModeScreen.class,

                FasterBlockBreakingScreen.class,
                BetterPiglinBarteringScreen.class,

                PiglinPorkScreen.class,
                NoMorePiglinBrutesScreen.class,

                TripledDropsScreen.class,
                MoreExperienceScreen.class,

                BlazeSpawnersInBastionsScreen.class,
                BlazesDropGoldScreen.class,

                BetterNetherPortalsScreen.class,
                WaterInNetherScreen.class,

                FireproofItemsScreen.class,
                ThrowableFireballsScreen.class,

                BetterHotkeysScreen.class,
                ResetKeyScreen.class,

                FogKeyScreen.class,
                FullbrightKeyScreen.class,

                RetiredSpeedrunnerScreen.class,
                BetterLootTablesScreen.class,

                ReverseCraftingScreen.class,
                FasterSmeltingScreen.class,

                LessFallDamageScreen.class,
                CookedFleshScreen.class,

                BetterFoodsScreen.class,
                BetterAnvilsScreen.class,

                BetterDeathScreen.class,
                CraftableGodApplesScreen.class,

                RightClickToRemoveSilkTouchScreen.class,
                CraftableTotemsScreen.class,

                EnderEyesNeverBreak.class,
                ArrowsExplodeBedsScreen.class,

                SpeedrunnerEditionScreen.class,
                CustomPanoramaScreen.class,

                AndMoreScreen.class
        );
    }

    /**
     * @return all {@code doom mode} feature screens, in order of their {@code page number.}
     */
    public List<Class<? extends AbstractFeatureScreen>> doomModeFeatureScreens() {
        return List.of(
                BasicsScreen.class,
                BossesScreen.class,
                GoliathScreen.class,
                DoomBlocksScreen.class,
                KnockbackStickScreen.class,
                OtherThingsToKnowScreen.class
        );
    }

    /**
     * @return all {@code secret doom mode} feature screens, in order of their {@code page number.}
     */
    public List<Class<? extends AbstractFeatureScreen>> secretDoomModeScreens() {
        return List.of(
                YouArentReadyForThisScreen.class,
                DoYouUnderstandScreen.class,
                ImReadyScreen.class,
                ExpectTheUnexpectedScreen.class,
                UmScreen.class,
                DotDotDotScreen.class,
                DotDotDotDotScreen.class,
                AllSecretsScreen.class,
                EyeFeaturesScreen.class
        );
    }

    /**
     * Needed because this method is abstract in {@link OptionsSubScreen}.
     */
    @Override
    protected void addOptions() {
    }
}