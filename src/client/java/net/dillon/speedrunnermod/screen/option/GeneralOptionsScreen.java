package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * The Speedrunner Mod's {@code general options.}
 */
public class GeneralOptionsScreen extends AbstractModScreen {
    private AbstractWidget mode, blockBreakingMultiplier, fireballExplosionPower;

    public GeneralOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_OPTIONS_GENERAL);
    }

    /**
     * All of the {@code general options.}
     * <p>These are displayed in order.</p>
     */
    private List<AbstractWidget> mainOptions() {
        this.mode = createOption(ListOptions.mode());
        this.blockBreakingMultiplier = createOption(ListOptions.blockBreakingMultiplier());
        this.fireballExplosionPower = createOption(ListOptions.fireballExplosionPower());

        return List.of(
                this.mode,
                createOption(ListOptions.icarusMode()),

                createOption(ListOptions.dragonPerchTime()),
                createOption(ListOptions.infiniPearlMode()),

                createOption(ListOptions.fireproofItems()),
                createOption(ListOptions.lavaBoats()),

                createOption(ListOptions.throwableFireballs()),
                this.fireballExplosionPower,

                createOption(ListOptions.fasterBlockBreaking()),
                this.blockBreakingMultiplier,

                createOption(ListOptions.fasterSpawners()),
                createOption(ListOptions.fallDamage()),

                createOption(ListOptions.fasterSmelting()),
                createOption(ListOptions.kineticDamage()),

                createOption(ListOptions.fasterBrewing()),
                createOption(ListOptions.showDeathCords()),

                createOption(ListOptions.betterFoods()),
                createOption(ListOptions.higherBreathTime()),

                createOption(ListOptions.betterAnvil()),
                createOption(ListOptions.anvilCostLimit()),

                createOption(ListOptions.higherEnchantmentLevels())
        );
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttonList.addAll(mainOptions());

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(this.mode, !this.isOnServer(),
                Component.translatable("speedrunnermod.options.mode.tooltip"),
                Component.translatable("speedrunnermod.options.mode.server.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.blockBreakingMultiplier, options().general.fasterBlockBreaking.getCurrentValue(),
                Component.translatable("speedrunnermod.options.block_breaking_multiplier.tooltip"),
                Component.translatable("speedrunnermod.options.block_breaking_multiplier.inactive"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.fireballExplosionPower, !isDoomMode() && options().general.throwableFireballs.getCurrentValue(),
                Component.translatable("speedrunnermod.options.fireball_explosion_power.tooltip"),
                isDoomMode()
                        ? Component.translatable("speedrunnermod.options.mode_easy_or_balanced_required.tooltip")
                        : Component.translatable("speedrunnermod.options.throwable_fireballs_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );
    }

    @Override
    public String pageId() {
        return "gf-o909aw";
    }

    @Override
    protected int columns() {
        return 3;
    }

    @Override
    protected boolean shouldRenderVersionText() {
        return true;
    }

    @Override
    public boolean isOptionsScreen() {
        return true;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }
}