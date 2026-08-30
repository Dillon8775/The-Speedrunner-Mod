package net.dillon.speedrunnermod.screen.option;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.main.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.OptionValue;
import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.screen.FeatureScreen;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.util.CommonColors;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.common;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.client;
import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.saveAllChanges;

public class RestartRequiredScreen extends AbstractModScreen {
    public static boolean restartRequired = false;
    private static final List<OptionValue<?>> restartTrackedValues = new ArrayList<>();
    private static final List<Object> initialValues = new ArrayList<>();
    private static final Set<Object> processedObjects = new HashSet<>();

    public RestartRequiredScreen(Screen parent) {
        super(parent, ModTexts.TITLE_RESTART_REQUIRED);
    }

    @Override
    protected void init() {
        this.addRenderableWidget(Button.builder(ModTexts.RESTART_NOW, (buttonWidget) -> {
            this.quitWorld();
            SpeedrunnerMod.LOGGER.info("Closing game! Re-launch to apply changes.");
            this.minecraft.stop();
        }).bounds(this.getButtonsLeftSide(), this.getButtonsHeight(), 100, 20).build());
        this.addRenderableWidget(Button.builder(ModTexts.REVERT_CHANGES, (buttonWidget) -> {
            revertChanges();
            SpeedrunnerMod.LOGGER.info("Changes reverted.");
            this.minecraft.gui.setScreen(this.parent);
            if (this.parent instanceof FeatureScreen featureScreen) {
                featureScreen.refreshFeatureScreen(featureScreen.getPageNumber(), featureScreen.featurePage.getCategory());
            }
        }).bounds(this.getButtonsMiddle(), this.getButtonsHeight(), 100, 20).build());
        this.addRenderableWidget(Button.builder(ModTexts.NOT_NOW, (buttonWidget) -> {
            this.onClose();
        }).bounds(this.getButtonsRightSide(), this.getButtonsHeight(), 100, 20).build());
    }

    @Override
    public void onClose() {
        saveAllChanges();
        restartRequired = true;
        this.minecraft.gui.setScreen(this.parent);
        if (this.parent instanceof FeatureScreen featureScreen) {
            featureScreen.refreshFeatureScreen(featureScreen.getPageNumber(), featureScreen.featurePage.getCategory());
        }
        super.onClose();
    }

    @Override
    public void renderCustomText(GuiGraphicsExtractor context) {
        context.centeredText(this.font, Component.translatable("speedrunnermod.restart_required.line1"), this.width / 2, 100, CommonColors.WHITE);
        context.centeredText(this.font, Component.translatable("speedrunnermod.restart_required.line2"), this.width / 2, 120, CommonColors.WHITE);
        context.centeredText(this.font, Component.translatable("speedrunnermod.restart_required.line3"), this.width / 2, 140, CommonColors.WHITE);
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
    public static void getCurrentOptions() {
        restartTrackedValues.clear();
        initialValues.clear();
        processedObjects.clear();

        scanOptions(common());
        scanOptions(client());
    }

    /**
     * Scans each option in the {@code options class} to determine if it requires a restart.
     */
    private static void scanOptions(Object optionsClass) {
        if (optionsClass == null) {
            SpeedrunnerMod.LOGGER.error("Options class is null");
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
                SpeedrunnerMod.LOGGER.debug("Failed to access field: " + field.getName() + " - " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * @return {@code true} if the game needs a restart due to a {@code restart required} option.
     */
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