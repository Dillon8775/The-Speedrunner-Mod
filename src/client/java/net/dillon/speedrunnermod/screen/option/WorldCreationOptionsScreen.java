package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.option.ListOptions;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModTexts;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * The Speedrunner Mod's {@code "fast world creation"} options, which set the settings of each new world created.
 */
public class WorldCreationOptionsScreen extends AbstractModScreen {
    public static boolean SHOULD_INSTANT_CREATE = true;
    private AbstractWidget gameMode, difficulty, allowCheats, seed;

    public WorldCreationOptionsScreen(Screen parent) {
        super(parent, ModTexts.TITLE_FAST_WORLD_CREATION);
    }

    /**
     * All of the {@code fast world creation options.}
     */
    private List<AbstractWidget> fwcOptions() {
        this.gameMode = createOption(ListOptions.gameMode());
        this.difficulty = createOption(ListOptions.difficulty());
        this.allowCheats = createOption(ListOptions.allowCommands());
        this.seed = createSeedField();

        return List.of(
                createOption(ListOptions.instantWorldCreation()),
                this.difficulty,
                this.gameMode,
                this.allowCheats,
                this.seed
        );
    }

    /**
     * Creates the seed text field for {@code fast world creation.}
     */
    private static EditBox createSeedField() {
        EditBox seedField = new EditBox(Minecraft.getInstance().font, 0, 0, 150, 20, Component.empty());
        seedField.setMaxLength(128);
        seedField.setHint(Component.translatable("speedrunnermod.options.seed"));
        seedField.setValue(clientOptions().client.seed.getCurrentValue());
        seedField.setResponder(value -> clientOptions().client.seed.set(value));
        return seedField;
    }

    @Override
    protected void init() {
        this.initializeCustomButtonListWidget();

        this.buttonList.addAll(fwcOptions());

        super.init();
    }

    @Override
    protected void lockOptionsAndRenderTooltips(GuiGraphicsExtractor graphics, int mouseX, int mouseY) {
        this.lockOptionWithTooltip(this.gameMode, clientOptions().client.instantWorldCreation.getCurrentValue(),
                Component.translatable("speedrunnermod.options.gamemode.tooltip"),
                Component.translatable("speedrunnermod.options.fast_world_creation_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.difficulty, clientOptions().client.instantWorldCreation.getCurrentValue() && !isDoomMode() && !clientOptions().client.gameMode.getCurrentValue().hardcore(),
                Component.translatable("speedrunnermod.options.difficulty.tooltip"),
                clientOptions().client.gameMode.getCurrentValue().hardcore() ?
                        Component.translatable("options.worldOptions.game_mode.disabled.tooltip") :
                        clientOptions().client.instantWorldCreation.getCurrentValue() ?
                                Component.translatable("speedrunnermod.options.difficulty_locked.tooltip") :
                                Component.translatable("speedrunnermod.options.fast_world_creation_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.allowCheats, clientOptions().client.instantWorldCreation.getCurrentValue() && !clientOptions().client.gameMode.getCurrentValue().hardcore(),
                Component.translatable("speedrunnermod.options.allow_commands.tooltip"),
                !clientOptions().client.instantWorldCreation.getCurrentValue() ?
                        Component.translatable("speedrunnermod.options.fast_world_creation_must_be_enabled.tooltip") :
                        Component.translatable("options.worldOptions.allow_commands.disabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );

        this.lockOptionWithTooltip(this.seed, clientOptions().client.instantWorldCreation.getCurrentValue(),
                Component.translatable("speedrunnermod.options.seed.tooltip"),
                Component.translatable("speedrunnermod.options.fast_world_creation_must_be_enabled.tooltip"),
                graphics,
                mouseX,
                mouseY
        );
    }

    @Override
    public String pageId() {
        return "gfnipfioad";
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