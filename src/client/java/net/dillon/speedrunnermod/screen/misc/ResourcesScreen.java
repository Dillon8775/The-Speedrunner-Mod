package net.dillon.speedrunnermod.screen.misc;

import net.dillon.speedrunnermod.helper.ModTexts;
import net.dillon.speedrunnermod.screen.BaseModScreen;
import net.dillon.speedrunnermod.util.ModLinks;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.openLink;

public class ResourcesScreen extends BaseModScreen {

    public ResourcesScreen(Screen parent) {
        super(parent, Component.translatable("speedrunnermod.title.resources"));
    }

    @Override
    protected void init() {
        super.init();

        Button showcaseVideoButton = Button.builder(ModTexts.MOD_SHOWCASE_VIDEO, (buttonWidget) -> {
            openLink(this, ModLinks.SHOWCASE_VIDEO, true);
        }).build();

        Button releaseTrailerButton = Button.builder(ModTexts.MOD_RELEASE_TRAILER, (buttonWidget) -> {
            openLink(this, ModLinks.RELEASE_TRAILER, true);
        }).build();

        Button otherModsButton = Button.builder(ModTexts.MENU_MODS, (button) -> {
            this.minecraft.gui.setScreen(new OtherModsScreen(this));
        }).build();

        Button questionsAndIssuesButton = Button.builder(ModTexts.QUESTIONS_AND_ISSUES, (button) -> {
            openLink(this, ModLinks.QUESTIONS_AND_ISSUES, true);
        }).build();

        Button tutorialsButton = Button.builder(ModTexts.MENU_TUTORIALS, (button) -> {
            this.minecraft.gui.setScreen(new TutorialsScreen(this));
        }).build();

        this.list.addHeader(Component.translatable("speedrunnermod.menu.mod_info"));
        this.list.addSmall(
                List.of(
                        showcaseVideoButton,
                        releaseTrailerButton,
                        otherModsButton
                )
        );

        this.list.addHeader(Component.translatable("speedrunnermod.menu.help"));
        this.list.addSmall(
                List.of(
                        questionsAndIssuesButton,
                        tutorialsButton
                )
        );
    }
}