package net.dillon.speedrunnermod.client.screen.base.option;

import net.dillon.speedrunnermod.client.screen.base.AbstractModScreen;
import net.dillon.speedrunnermod.client.screen.feature.AbstractFeatureScreen;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.OptionValue;
import net.dillon.speedrunnermod.util.AI;
import net.dillon.speedrunnermod.util.ModTexts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.info;
import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.clientOptions;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;

@Environment(EnvType.CLIENT)
public class RestartRequiredScreen extends AbstractModScreen {
    private static final List<OptionValue<?>> restartTrackedValues = new ArrayList<>();
    private static final List<Object> initialValues = new ArrayList<>();
    private static final Set<Object> processedObjects = new HashSet<>();

    public RestartRequiredScreen(Screen parent) {
        super(parent, ModTexts.TITLE_RESTART_REQUIRED);
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
        saveAllChanges();
        this.client.setScreen(this.parent);
        if (this.parent instanceof AbstractFeatureScreen abstractFeatureScreen) {
            this.refreshFeatureScreen(abstractFeatureScreen.getPageNumber(), abstractFeatureScreen.getScreenCategory());
        }
        super.close();
    }

    @Override
    public void renderCustomText(DrawContext context) {
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.restart_required.line1"), this.width / 2, 110, Colors.WHITE);
        context.drawCenteredTextWithShadow(this.textRenderer, Text.translatable("speedrunnermod.restart_required.line2"), this.width / 2, 130, Colors.WHITE);
    }

    @Override
    public String pageId() {
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
    public boolean isOptionsScreen() {
        return false;
    }

    @Override
    protected boolean shouldRenderTitleText() {
        return true;
    }

    /**
     * Gets the current options when opening the screen.
     */
    @AI
    public static void getCurrentOptions() {
        restartTrackedValues.clear();
        initialValues.clear();
        processedObjects.clear();

        scanOptions(options());
        scanOptions(clientOptions());
    }

    /**
     * Scans each option in the {@code options class} to determine if it requires a restart.
     */
    @AI
    private static void scanOptions(Object optionsClass) {
        if (optionsClass == null) {
            SpeedrunnerMod.error("Options class is null");
            return;
        }

        if (processedObjects.contains(optionsClass)) {
            return;
        }

        processedObjects.add(optionsClass);

        for (Field field : optionsClass.getClass().getDeclaredFields()) {
            try {
                field.setAccessible(true);
                Object value = field.get(optionsClass);

                if (value == null) {
                    continue;
                }

                if (value instanceof OptionValue<?> optionValue) {
                    if (optionValue.requiresRestart()) {
                        restartTrackedValues.add(optionValue);
                        initialValues.add(optionValue.getCurrentValue());
                    }
                } else if (value != null && !value.getClass().isPrimitive()
                        && value.getClass().getName().startsWith("net.dillon.speedrunnermod")) {
                    scanOptions(value);
                }
            } catch (IllegalAccessException e) {
                SpeedrunnerMod.debug("Failed to access field: " + field.getName() + " - " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * @return {@code true} if the game needs a restart due to a {@code restart required} option.
     */
    @AI
    public static boolean needsRestart() {
        for (int i = 0; i < restartTrackedValues.size(); i++) {
            OptionValue<?> option = restartTrackedValues.get(i);
            Object initialValue = initialValues.get(i);

            if (!option.getCurrentValue().equals(initialValue)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Revert the changes back to it's original value before the screen was opened.
     */
    @AI
    private static void revertChanges() {
        for (int i = 0; i < restartTrackedValues.size(); i++) {
            @SuppressWarnings("unchecked")
            OptionValue<Object> option = (OptionValue<Object>) restartTrackedValues.get(i);
            option.set(initialValues.get(i));
            saveAllChanges();
            getCurrentOptions();
        }
    }
}