package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.client.screen.base.feature.*;
import net.dillon.speedrunnermod.client.screen.base.options.*;
import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.blocksanditems.*;
import net.dillon.speedrunnermod.client.screen.feature.doommode.*;
import net.dillon.speedrunnermod.client.screen.feature.firsttimeplaying.*;
import net.dillon.speedrunnermod.client.screen.feature.more.*;
import net.dillon.speedrunnermod.client.screen.feature.oresandworldgen.*;
import net.dillon.speedrunnermod.client.screen.feature.toolsandarmor.*;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.ConfirmLinkScreen;
import net.minecraft.client.gui.screen.MessageScreen;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.option.GameOptionsScreen;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;
import net.minecraft.util.Util;

import java.util.List;

/**
 * The base screen for any {@code Speedrunner Mod} screen.
 */
@Environment(EnvType.CLIENT)
public class BaseModScreen extends GameOptionsScreen {
    protected final GameOptions options = MinecraftClient.getInstance().options;

    public BaseModScreen(Screen parent, GameOptions options, Text title) {
        super(parent, options, title);
    }

    /**
     * Quits a world.
     */
    protected void quitWorld() {
        if (this.client.isInSingleplayer()) {
            this.client.world.disconnect();
            this.client.disconnect(new MessageScreen(Text.translatable("menu.savingLevel")));
        } else {
            this.client.disconnect();
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
        }, link, trusted));
    }

    /**
     * A simplified way to render a tooltip.
     */
    protected void renderBasicTooltip(Text text, DrawContext context, int mouseX, int mouseY) {
        context.drawOrderedTooltip(this.textRenderer, this.textRenderer.wrapLines(text, 200), mouseX, mouseY);
    }

    /**
     * Determines the refreshed screen for base screens.
     */
    public Screen determineRefreshedScreen(String pageId) {
        for (AbstractModScreen screen : this.allModScreens()) {
            if (screen.pageId().equals(pageId)) {
                return screen;
            }
        }
        return new MainScreen(this.parent, this.options);
    }

    /**
     * <p>The list of all feature screens.</p>
     * These are not in order, and each time a new feature screen is created, it MUST be added to this list.
     */
    protected List<AbstractFeatureScreen> allFeatureScreens() {
        return List.of(
                new BlazeSpotterScreen(parent, options),
                new DragonsPearlScreen(parent, options),
                new EnderThrusterScreen(parent, options),
                new EyeOfAnnulScreen(parent, options),
                new EyeOfInfernoScreen(parent, options),
                new GoldenFoodItemsScreen(parent, options),
                new IgneousRocksScreen(parent, options),
                new MoreBoatsScreen(parent, options),
                new PiglinAwakenerScreen(parent, options),
                new RaidEradicatorScreen(parent, options),
                new RetiredSpeedrunnerScreen(parent, options),
                new SpeedrunnerBlocksScreen(parent, options),
                new SpeedrunnerBulkScreen(parent, options),
                new SpeedrunnerIngotsScreen(parent, options),
                new SpeedrunnerNuggetsScreen(parent, options),
                new SpeedrunnersEyeScreen(parent, options),
                new SpeedrunnersWorkbenchScreen(parent, options),
                new SpeedrunnerWoodScreen(parent, options),
                new BasicsScreen(parent, options),
                new BossesScreen(parent, options),
                new DoomBlocksScreen(parent, options),
                new GiantScreen(parent, options),
                new OtherThingsToKnowScreen(parent, options),
                new FasterBlockBreakingScreen(parent, options),
                new FogKeyScreen(parent, options),
                new ICarusModeScreen(parent, options),
                new InfiniPearlModeScreen(parent, options),
                new EndScreen(parent, options),
                new NoMorePiglinBrutesScreen(parent, options),
                new BetterPiglinBarteringScreen(parent, options),
                new PiglinPorkScreen(parent, options),
                new ResetKeyScreen(parent, options),
                new TripledDropsScreen(parent, options),
                new CommonOresScreen(parent, options),
                new ExperienceOresScreen(parent, options),
                new FortressesBastionsAndStrongholdsScreen(parent, options),
                new IgneousOresScreen(parent, options),
                new SpeedrunnerOresScreen(parent, options),
                new SpeedrunnersWastelandBiomeScreen(parent, options),
                new StructuresScreen(parent, options),
                new CooldownEnchantmentScreen(parent, options),
                new DashEnchantmentScreen(parent, options),
                new DragonsSwordScreen(parent, options),
                new GoldenSpeedrunnerArmorScreen(parent, options),
                new SpeedrunnerArmorScreen(parent, options),
                new WitherSwordScreen(parent, options),
                new FullbrightKeyScreen(parent, options),
                new SpeedrunnerSafeBootsScreen(parent, options),
                new DeadSpeedrunnerWoodScreen(parent, options),
                new NetherPortalsScreen(parent, options),
                new CookedFleshScreen(parent, options),
                new FireproofItemsScreen(parent, options),
                new BuffedLootTablesScreen(parent, options),
                new BlazeSpawnersInBastionsScreen(parent, options),
                new BlazesDropGoldScreen(parent, options),
                new BetterVillagerTradesScreen(parent, options),
                new ThrowableFireballsScreen(parent, options),
                new BetterDeathScreen(parent, options),
                new BetterAnvilsScreen(parent, options),
                new TotemsWorkInVoidScreen(parent, options),
                new NeverBreakingEnderEyesScreen(parent, options),
                new ReverseCraftingScreen(parent, options),
                new LessFallDamageScreen(parent, options),
                new BetterHotkeysScreen(parent, options),
                new ArrowsExplodeBedsScreen(parent, options),
                new SpeedrunnerEditionScreen(parent, options),
                new MoreExperienceScreen(parent, options),
                new CustomPanoramaScreen(parent, options),
                new SpeedrunnersTotemScreen(parent, options),
                new InfiniPearlScreen(parent, options),
                new EnderMatterScreen(parent, options),
                new FirstTimePlayingScreen(parent, options),
                new KeyPointsScreen(parent, options),
                new PlayingModeOption(parent, options),
                new ReadyToPlayScreen(parent, options),
                new FTPRestartRequiredScreen(parent, options));
    }

    /**
     * A list of all mod screens.
     */
    protected List<AbstractModScreen> allModScreens() {
        return List.of(
                new MainScreen(parent, options),
                new ModCreditsScreen(parent, options),
                new ModOptionsScreen(parent, options),
                new ModsScreen(parent, options),
                new RefreshingScreen(parent, options),
                new ResetOptionsConfirmScreen(parent, options),
                new ResetOptionsScreen(parent, options),
                new ResourcesScreen(parent, options),
                new RestartRequiredScreen(parent, options),
                new SafeBootScreen(parent, options),
                new SpeedrunIGTMissingScreen(parent, options),
                new TutorialsScreen(parent, options),
                new TutorialsScreen.BastionRoutesScreen(parent, options),
                new TutorialsScreen.MicrolensingScreen(parent, options),
                new ExternalScreen(parent, options),
                new LeaderboardsIneligibleOptionsScreen(parent, options),
                new LeaderboardsIneligibleScreen(parent, options),
                new LeaderboardsSafeScreen(parent, options),
                new LeaderboardsSafeScreen(parent, options),
                new BlocksAndItemsScreen(parent, options),
                new DoomModeScreen(parent, options),
                new FeaturesScreen(parent, options),
                new MoreScreen(parent, options),
                new OresAndWorldgenScreen(parent, options),
                new ToolsAndArmorScreen(parent, options),
                new AdvancedOptionsScreen(parent, options),
                new ClientOptionsScreen(parent, options),
                new FastWorldCreationOptionsScreen(parent, options),
                new MainOptionsScreen(parent, options),
                new MixinOptionsScreen(parent, options),
                new StructureSpawnRateOptionsScreen(parent, options)
        );
    }

    /**
     * Needed because this method is abstract in {@link GameOptionsScreen}.
     */
    @Override
    protected void addOptions() {
    }
}