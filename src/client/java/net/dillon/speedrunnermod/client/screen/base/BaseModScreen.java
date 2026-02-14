package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.blocksanditems.*;
import net.dillon.speedrunnermod.client.screen.feature.doommode.*;
import net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying.*;
import net.dillon.speedrunnermod.client.screen.feature.miscellaneous.*;
import net.dillon.speedrunnermod.client.screen.feature.oresandworldgen.*;
import net.dillon.speedrunnermod.client.screen.feature.secretdoommode.*;
import net.dillon.speedrunnermod.client.screen.feature.toolsandarmor.*;
import net.dillon.speedrunnermod.main.SpeedrunnerModClient;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.util.List;
import java.util.function.Function;

/**
 * The base screen for any {@code Speedrunner Mod} screen.
 */
public class BaseModScreen extends GameOptionsScreen {
    public TextFieldWidget searchField;

    public BaseModScreen(Screen parent, Text title) {
        super(parent, MinecraftClient.getInstance().options, title);
    }

    /**
     * Quits a world.
     */
    protected void quitWorld() {
        if (this.client.isInSingleplayer()) {
            this.client.world.disconnect(Text.translatable("menu.savingLevel"));
            this.client.disconnect(new MessageScreen(Text.translatable("menu.savingLevel")), false, false);
        } else {
            this.client.disconnect(new TitleScreen(), false, false);
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
            text = this.searchField.getText();
            refocus = this.searchField.isFocused();
        }
        super.resize(width, height);
        this.clearAndInit();
        if (this.searchField != null) {
            this.searchField.setText(text);
            this.searchField.setFocused(refocus);
        }
    }

    /**
     * An easier way to open a link.
     */
    protected void openLink(String link, boolean trusted) {
        this.client.setScreen(new ConfirmLinkScreen(openInBrowser -> {
            if (openInBrowser) {
                Util.getOperatingSystem().open(link);
            }
            this.client.setScreen(this);
            this.resize(this.width, this.height);
        }, link, trusted));
    }

    /**
     * A simplified way to render a tooltip.
     */
    protected void renderBasicTooltip(Text text, DrawContext context, int mouseX, int mouseY) {
        context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(text, 200), mouseX, mouseY);
    }

    /**
     * Refreshes a base mod screen.
     */
    public void refreshScreen(String id) {
        this.client.setScreen(new TemporaryScreen(this.parent, ModTexts.REFRESHING));
        this.client.setScreen(this.determineRefreshedScreen(id));
    }

    /**
     * Refreshes a feature screen.
     */
    public void refreshFeatureScreen(int pageNumber, ScreenCategory screenCategory) {
        this.client.setScreen(new TemporaryScreen(this.parent, ModTexts.REFRESHING));
        this.client.setScreen(this.determineRefreshedFeatureScreen(pageNumber, screenCategory));
    }

    /**
     * Determines the refreshed screen for base screens.
     */
    private Screen determineRefreshedScreen(String pageId) {
        for (Function<Screen, AbstractModScreen> modScreenConstructor : SpeedrunnerModClient.ALL_MOD_SCREENS) {
            AbstractModScreen screen = modScreenConstructor.apply(this.parent);
            if (screen.pageId().equals(pageId)) {
                return screen;
            }
        }
        return new MainScreen(this.parent);
    }

    /**
     * Determines the refreshed screen for feature screens.
     */
    private Screen determineRefreshedFeatureScreen(int pageNumber, ScreenCategory screenCategory) {
        for (Function<Screen, AbstractFeatureScreen> featureScreenConstructor : SpeedrunnerModClient.ALL_FEATURE_SCREENS) {
            AbstractFeatureScreen screen = featureScreenConstructor.apply(this.parent);
            if (screen.getPageNumber() == pageNumber && screen.getScreenCategory() == screenCategory) {
                if (this instanceof AbstractFeatureScreen previous) {
                    screen.targetScrollOffset = previous.targetScrollOffset;
                    screen.scrollOffset = previous.scrollOffset;
                }
                return screen;
            }
        }
        return new FirstTimePlayingScreen(this.parent);
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
                SpeedrunnerShieldScreen.class,
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
                BetterVillagerTradesScreen.class,
                RetiredSpeedrunnerScreen.class,
                BetterLootTablesScreen.class,
                ReverseCraftingScreen.class,
                FasterSmeltingScreen.class,
                LessFallDamageScreen.class,
                CookedFleshScreen.class,
                BetterFoodsScreen.class,
                BetterDeathScreen.class,
                BetterAnvilsScreen.class,
                CraftableGodApplesScreen.class,
                CraftableTotemsScreen.class,
                RightClickToRemoveSilkTouchScreen.class,
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
     * Needed because this method is abstract in {@link GameOptionsScreen}.
     */
    @Override
    protected void addOptions() {
    }
}