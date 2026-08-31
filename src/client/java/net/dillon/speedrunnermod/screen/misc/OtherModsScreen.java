package net.dillon.speedrunnermod.screen.misc;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.BaseModScreen;
import net.dillon.speedrunnermod.util.ModLinks;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.openLink;

public class OtherModsScreen extends BaseModScreen {
    private Button sodiumButton, lithiumButton, speedrunIGTButton, kryptonButton, simpleKeybindsButton, qualityOfQuesoButton;

    public OtherModsScreen(Screen parent) {
        super(parent, Component.translatable("speedrunnermod.title.resources.mods"));
    }

    @Override
    protected void init() {
        super.init();

        this.sodiumButton = Button.builder(ModTexts.SODIUM, (buttonWidget) -> {
            openLink(this, ModLinks.SODIUM, false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.title.resources.mods.sodium.tooltip"))
        ).build();

        this.lithiumButton = Button.builder(ModTexts.LITHIUM, (buttonWidget) -> {
            openLink(this, ModLinks.LITHIUM, false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.title.resources.mods.lithium.tooltip"))
        ).build();

        this.speedrunIGTButton = Button.builder(ModTexts.SPEEDRUN_IGT, (buttonWidget) -> {
            openLink(this, ModLinks.SPEEDRUNIGT, false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.title.resources.mods.speedrunigt.tooltip"))
        ).build();

        this.kryptonButton = Button.builder(ModTexts.KRYPTON, (buttonWidget) -> {
            openLink(this, ModLinks.KRYPTON, false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.title.resources.mods.krypton.tooltip"))
        ).build();

        this.simpleKeybindsButton = Button.builder(ModTexts.SIMPLE_KEYBINDS, (buttonWidget) -> {
            openLink(this, ModLinks.SIMPLE_KEYBINDS, false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.title.resources.mods.simple_keybinds").withStyle(ChatFormatting.GREEN))
        ).build();

        this.qualityOfQuesoButton = Button.builder(ModTexts.QUALITY_OF_QUESO, (buttonWidget) -> {
            openLink(this, ModLinks.QUALITY_OF_QUESO, false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.title.resources.mods.qoq.tooltip"))
        ).build();

        this.list.addHeader(Component.translatable("speedrunnermod.menu.performance_mods"));
        this.list.addSmall(
                List.of(
                        this.sodiumButton,
                        this.lithiumButton,
                        this.kryptonButton
                )
        );

        this.list.addHeader(Component.translatable("speedrunnermod.menu.qol_mods"));
        this.list.addSmall(
                List.of(
                        this.speedrunIGTButton,
                        this.qualityOfQuesoButton,
                        this.simpleKeybindsButton
                )
        );
    }
}