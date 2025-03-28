package net.dillon.speedrunnermod.client.screen.base;

import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.option.GameOptions;
import net.minecraft.text.Text;

import static net.dillon.speedrunnermod.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

@Environment(EnvType.CLIENT)
public class RestartRequiredScreen extends AbstractModScreen {
    public static boolean currentLeaderboardsMode;
    public static boolean currentBetterBiomes;
    public static ModOptions.PlayingMode currentPlayingMode;
    public static boolean currentBetterVillagerTrades;
    public static boolean currentCustomBiomesAndCustomBiomeFeatures;
    public static boolean currentPanorama;
    public static boolean currentConfirmMessages;
    public static boolean currentModifiedStrongholdGeneration;
    public static boolean currentModifiedStrongholdYGeneration;
    public static boolean currentModifiedNetherFortressGeneration;
    public static boolean currentTerraBlenderSurfaceRuleDataMixin;
    public static boolean currentBackgroundRendererMixin;
    public static boolean currentSimpleOptionMixin;
    public static boolean currentLogoDrawerMixin;
    public static boolean currentRenderLayersMixin;
    public static int currentStrongholdDistance;
    public static int currentStrongholdSpread;
    public static int currentStrongholdCount;
    public static int currentStrongholdPortalRoomCount;
    public static int currentStrongholdLibraryCount;
    public static int currentSpeedrunnersWastelandBiomeWeight;

    public RestartRequiredScreen(Screen parent, GameOptions options) {
        super(parent, options, ModTexts.TITLE_RESTART_REQUIRED);
    }

    @Override
    protected void init() {
        this.addDrawableChild(ButtonWidget.builder(ModTexts.RESTART_NOW, (buttonWidget) -> {
            this.quitWorld();
            info("Closing game! Re-launch to apply changes.");
            this.client.scheduleStop();
        }).dimensions(this.getButtonsLeftSide(), this.getButtonsHeight(), 100, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.REVERT_CHANGES, (buttonWidget) -> {
            revertChanges();
            ModOptions.saveConfig();
            info("Changes reverted.");
            this.client.setScreen(this.parent);
            if (this.parent instanceof AbstractFeatureScreen abstractFeatureScreen) {
                this.refreshFeatureScreen(abstractFeatureScreen.getPageNumber(), abstractFeatureScreen.getScreenCategory());
            }
        }).dimensions(this.getButtonsMiddle(), this.getButtonsHeight(), 100, 20).build());
        this.addDrawableChild(ButtonWidget.builder(ModTexts.NOT_NOW, (buttonWidget) -> {
            this.close();
        }).dimensions(this.getButtonsRightSide(), this.getButtonsHeight(), 100, 20).build());
    }

    @Override
    public void close() {
        ModOptions.saveConfig();
        this.client.setScreen(this.parent);
        if (this.parent instanceof AbstractFeatureScreen abstractFeatureScreen) {
            this.refreshFeatureScreen(abstractFeatureScreen.getPageNumber(), abstractFeatureScreen.getScreenCategory());
        }
    }

    @Override
    public void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.restart_required.line1"), this.width / 2, 110, 16777215);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.restart_required.line2"), this.width / 2, 130, 16777215);
    }

    @Override
    protected String pageId() {
        return "biperwiqew";
    }

    @Override
    protected int getButtonsHeight() {
        return this.height / 6 + 126;
    }

    @Override
    protected int columns() {
        return 3;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return false;
    }

    @Override
    protected boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }

    public static void getCurrentOptions() {
        currentLeaderboardsMode = options().main.leaderboardsMode;
        currentBetterBiomes = options().main.betterBiomes;
        currentPlayingMode = options().main.playingMode;
        currentBetterVillagerTrades = options().main.betterVillagerTrades;
        currentCustomBiomesAndCustomBiomeFeatures = options().main.customBiomesAndCustomBiomeFeatures;
        currentPanorama = options().client.customPanorama;
        currentConfirmMessages = options().client.confirmMessages;
        currentModifiedStrongholdGeneration = options().advanced.modifiedStrongholdGeneration;
        currentModifiedStrongholdYGeneration = options().advanced.modifiedStrongholdYGeneration;
        currentModifiedNetherFortressGeneration = options().advanced.modifiedNetherFortressGeneration;
        currentTerraBlenderSurfaceRuleDataMixin = options().mixins.terraBlenderSurfaceRuleDataMixin;
        currentBackgroundRendererMixin = options().mixins.backgroundRendererMixin;
        currentSimpleOptionMixin = options().mixins.simpleOptionMixin;
        currentLogoDrawerMixin = options().mixins.logoDrawerMixin;
        currentRenderLayersMixin = options().mixins.renderLayersMixin;
        currentStrongholdDistance = options().main.strongholdDistance;
        currentStrongholdSpread = options().main.strongholdSpread;
        currentStrongholdCount = options().main.strongholdCount;
        currentStrongholdPortalRoomCount = options().main.strongholdPortalRoomCount;
        currentStrongholdLibraryCount = options().main.strongholdLibraryCount;
        currentSpeedrunnersWastelandBiomeWeight = options().advanced.speedrunnersWastelandBiomeWeight;
    }

    public static boolean needsRestart() {
        return currentLeaderboardsMode != options().main.leaderboardsMode ||
                currentBetterBiomes != options().main.betterBiomes ||
                currentPlayingMode != options().main.playingMode ||
                currentBetterVillagerTrades != options().main.betterVillagerTrades ||
                currentCustomBiomesAndCustomBiomeFeatures != options().main.customBiomesAndCustomBiomeFeatures ||
                currentPanorama != options().client.customPanorama ||
                currentConfirmMessages != options().client.confirmMessages ||
                currentModifiedStrongholdGeneration != options().advanced.modifiedStrongholdGeneration ||
                currentModifiedStrongholdYGeneration != options().advanced.modifiedStrongholdYGeneration ||
                currentModifiedNetherFortressGeneration != options().advanced.modifiedNetherFortressGeneration ||
                currentTerraBlenderSurfaceRuleDataMixin != options().mixins.terraBlenderSurfaceRuleDataMixin ||
                currentBackgroundRendererMixin != options().mixins.backgroundRendererMixin ||
                currentSimpleOptionMixin != options().mixins.simpleOptionMixin ||
                currentLogoDrawerMixin != options().mixins.logoDrawerMixin ||
                currentRenderLayersMixin != options().mixins.renderLayersMixin ||
                currentStrongholdDistance != options().main.strongholdDistance ||
                currentStrongholdSpread != options().main.strongholdSpread ||
                currentStrongholdCount != options().main.strongholdCount ||
                currentStrongholdPortalRoomCount != options().main.strongholdPortalRoomCount ||
                currentStrongholdLibraryCount != options().main.strongholdLibraryCount ||
                currentSpeedrunnersWastelandBiomeWeight != options().advanced.speedrunnersWastelandBiomeWeight;
    }

    private static void revertChanges() {
        options().main.leaderboardsMode = currentLeaderboardsMode;
        options().main.betterBiomes = currentBetterBiomes;
        options().main.playingMode = currentPlayingMode;
        options().main.betterVillagerTrades = currentBetterVillagerTrades;
        options().main.customBiomesAndCustomBiomeFeatures = currentCustomBiomesAndCustomBiomeFeatures;
        options().client.customPanorama = currentPanorama;
        options().client.confirmMessages = currentConfirmMessages;
        options().advanced.modifiedStrongholdGeneration = currentModifiedStrongholdGeneration;
        options().advanced.modifiedStrongholdYGeneration = currentModifiedStrongholdYGeneration;
        options().advanced.modifiedNetherFortressGeneration = currentModifiedNetherFortressGeneration;
        options().mixins.terraBlenderSurfaceRuleDataMixin = currentTerraBlenderSurfaceRuleDataMixin;
        options().mixins.backgroundRendererMixin = currentBackgroundRendererMixin;
        options().mixins.simpleOptionMixin = currentSimpleOptionMixin;
        options().mixins.logoDrawerMixin = currentLogoDrawerMixin;
        options().mixins.renderLayersMixin = currentRenderLayersMixin;
        options().main.strongholdDistance = currentStrongholdDistance;
        options().main.strongholdSpread = currentStrongholdSpread;
        options().main.strongholdCount = currentStrongholdCount;
        options().main.strongholdPortalRoomCount = currentStrongholdPortalRoomCount;
        options().main.strongholdLibraryCount = currentStrongholdLibraryCount;
        options().advanced.speedrunnersWastelandBiomeWeight = currentSpeedrunnersWastelandBiomeWeight;
    }
}