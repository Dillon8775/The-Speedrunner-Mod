package net.dillon.speedrunnermod.client.screen.feature.miscellaneous;

import net.dillon.speedrunnermod.client.screen.base.option.RestartRequiredScreen;
import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.client.screen.feature.ScreenCategory;
import net.dillon.speedrunnermod.client.screen.feature.ScreenType;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import org.jetbrains.annotations.NotNull;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;


public class BetterVillagerTradesScreen extends AbstractFeatureScreen {

    public BetterVillagerTradesScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FEATURE_BETTER_VILLAGER_TRADES);
    }

    @Override
    protected void init() {
        super.init();

        this.addButtonObject(Button.builder(options().main.betterVillagerTrades.getCurrentValue() ? ModTexts.DISABLE_THIS_FEATURE : ModTexts.ENABLE_THIS_FEATURE, button -> {
            RestartRequiredScreen.getCurrentOptions();
            options().main.betterVillagerTrades.set(!options().main.betterVillagerTrades.getCurrentValue());
            saveAllChanges();
            this.minecraft.setScreen(new RestartRequiredScreen(this));
        }).build());
    }

    @Override
    public @NotNull String linesKey() {
        return "better_villager_trades";
    }

    @Override
    public @NotNull ScreenCategory getScreenCategory() {
        return ScreenCategory.MISCELLANEOUS;
    }

    @Override
    protected @NotNull ScreenType getScreenType() {
        return ScreenType.DEFAULT;
    }
}