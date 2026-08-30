package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.option.CommonModOptions.isDoomMode;

/**
 * The Speedrunner Mod's {@code general options.}
 */
public class GeneralOptionsScreen extends AbstractModScreen {
    private AbstractWidget mode, blockBreakingMultiplier, fireballExplosionPower, increasedOxygen;

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
        this.increasedOxygen = createOption(ListOptions.increasedOxygen());

        return List.of(
                this.mode,
                createOption(ListOptions.icarusMode()),

                createOption(ListOptions.dragonPerchTime()),
                createOption(ListOptions.infiniPearlMode()),

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
                this.increasedOxygen,

                createOption(ListOptions.betterAnvil()),
                createOption(ListOptions.anvilCostLimit()),

                createOption(ListOptions.higherEnchantmentLevels()),
                createOption(ListOptions.killGhastOnFireball())
        );
    }

    @Override
    protected void init() {
        this.initializeModButtonListWidget();

        this.buttonList.addAll(mainOptions());

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(this.mode, !this.isOnServer(),
                ListOptions.ofRestartable(Component.translatable("speedrunnermod.options.mode.tooltip")),
                Component.translatable("speedrunnermod.options.mode.server.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.blockBreakingMultiplier, common().general.fasterBlockBreaking.getCurrentValue(),
                Component.translatable("speedrunnermod.options.block_breaking_multiplier.tooltip"),
                Component.translatable("speedrunnermod.options.block_breaking_multiplier.inactive"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.fireballExplosionPower, !isDoomMode() && common().general.throwableFireballs.getCurrentValue(),
                Component.translatable("speedrunnermod.options.fireball_explosion_power.tooltip"),
                isDoomMode()
                        ? Component.translatable("speedrunnermod.options.mode_easy_or_balanced_required.tooltip")
                        : Component.translatable("speedrunnermod.options.throwable_fireballs_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.increasedOxygen, !isDoomMode(),
                Component.translatable("speedrunnermod.options.increased_oxygen.tooltip"),
                Component.translatable("speedrunnermod.options.mode_easy_or_balanced_required.tooltip"),
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