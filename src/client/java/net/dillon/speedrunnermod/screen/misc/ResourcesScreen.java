package net.dillon.speedrunnermod.screen.misc;

import net.dillon.speedrunnermod.screen.AbstractModScreen;
import net.dillon.speedrunnermod.util.ModLinks;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.openLink;
import static net.dillon.dillonlib.task.ClientTasks.openScreen;

public class ResourcesScreen extends AbstractModScreen {

    public ResourcesScreen(Screen parent) {
        super(parent, Component.translatable("speedrunnermod.title.resources"));
    }

    @Override
    protected void init() {
        super.init();

        Button showcaseVideoButton = Button.builder(Component.translatable("speedrunnermod.menu.links.mod_showcase_video").withStyle(ChatFormatting.LIGHT_PURPLE), (buttonWidget) -> {
            openLink(this, ModLinks.SHOWCASE_VIDEO, true);
        }).build();

        Button releaseTrailerButton = Button.builder(Component.translatable("speedrunnermod.menu.links.mod_release_trailer").withStyle(ChatFormatting.AQUA), (buttonWidget) -> {
            openLink(this, ModLinks.RELEASE_TRAILER, true);
        }).build();

        Button otherModsButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.mods").withStyle(ChatFormatting.AQUA), (button) -> {
            openScreen(new OtherModsScreen(this));
        }).build();

        Button questionsAndIssuesButton = Button.builder(Component.translatable("speedrunnermod.questions_and_issues").withStyle(ChatFormatting.BLUE), (button) -> {
            openLink(this, ModLinks.QUESTIONS_AND_ISSUES, true);
        }).build();

        Button tutorialsButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials").withStyle(ChatFormatting.GREEN), (button) -> {
            openScreen(new TutorialsScreen(this));
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