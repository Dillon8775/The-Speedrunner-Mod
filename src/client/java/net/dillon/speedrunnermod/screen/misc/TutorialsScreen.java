package net.dillon.speedrunnermod.screen.misc;

import net.dillon.speedrunnermod.screen.BaseModScreen;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

import java.util.List;

import static net.dillon.dillonlib.task.ClientTasks.openLink;

public class TutorialsScreen extends BaseModScreen {

    public TutorialsScreen(Screen parent) {
        super(parent, Component.translatable("speedrunnermod.title.resources.tutorials"));
    }

    @Override
    protected void init() {
        super.init();

        // Routes
        Button treasureBastionButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.treasure"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=np6fc_z9LUY", false);
        }).build();

        Button bridgeBastionButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.bridge"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=Dts81nEnzuQ", false);
        }).build();

        Button stablesBastionButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.stables"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=WIN-ZtUJfIc", false);
        }).build();

        Button housingBastionButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.bastion_routes.housing"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=S0G5asEjrgk", false);
        }).build();

        Button netherFortressesButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.nether_fortresses"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=pmx9LyUvLTk", false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.resources.tutorials.nether_fortresses.tooltip"))
        ).build();

        // Microlensing
        Button microlensingBastionsButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.microlensing.bastion"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=jvTfMLPnMSw", false);
        }).build();

        Button microlensingFortressesButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.microlensing.fortress"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=Kl_-L9XbJko", false);
        }).build();

        // World Navigation
        Button blindTravelButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.blind_travel"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=Ou58P7e-ZY0", false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.resources.tutorials.blind_travel.tooltip"))
        ).build();

        Button buriedTreasuresButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.buried_treasure"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=_dyD8ZwagDg", false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.resources.tutorials.buried_treasure.tooltip"))
        ).build();

        Button pieChartButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.pie_chart"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=ENgEBHIifm8", false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.resources.tutorials.pie_chart.tooltip"))
        ).build();

        Button f3MenuButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.f3_menu"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=-fSr7P5LQJY", false);
        }).build();

        // The End
        Button oneCyclingButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.one_cycling"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=JaVyuTyDxxs", false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.resources.tutorials.one_cycling.tooltip"))
        ).build();

        // Other
        Button comprehensiveGuideButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.comprehensive_guide"), (button) -> {
            openLink(this, "https://youtu.be/iaUF0oaegns?si=L47hwqlAerCAZ-S4", false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.resources.tutorials.comprehensive_guide.tooltip"))
        ).build();

        Button thirtyMinRun = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.thirty_min_run"), (button) -> {
            openLink(this, "https://youtu.be/olakF9Xbisc?si=1pquTzHph5n5S44o", false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.resources.tutorials.thirty_min_run.tooltip"))
        ).build();

        Button otherUsefulTricksButton = Button.builder(Component.translatable("speedrunnermod.menu.resources.tutorials.other_useful_tricks"), (button) -> {
            openLink(this, "https://www.youtube.com/watch?v=TvvApbI6fis", false);
        }).tooltip(
                Tooltip.create(Component.translatable("speedrunnermod.menu.resources.tutorials.other_useful_tricks.tooltip"))
        ).build();

        this.list.addHeader(Component.translatable("speedrunnermod.menu.tutorials.routes"));
        this.list.addSmall(
                List.of(
                        treasureBastionButton,
                        bridgeBastionButton,
                        stablesBastionButton,
                        housingBastionButton,
                        netherFortressesButton
                )
        );

        this.list.addHeader(Component.translatable("speedrunnermod.menu.tutorials.microlensing"));
        this.list.addSmall(
                List.of(
                        microlensingBastionsButton,
                        microlensingFortressesButton
                )
        );

        this.list.addHeader(Component.translatable("speedrunnermod.menu.tutorials.world_navigation"));
        this.list.addSmall(
                List.of(
                        blindTravelButton,
                        buriedTreasuresButton,
                        pieChartButton,
                        f3MenuButton
                )
        );

        this.list.addHeader(Component.translatable("speedrunnermod.menu.tutorials.the_end"));
        this.list.addSmall(
                List.of(
                        oneCyclingButton
                )
        );

        this.list.addHeader(Component.translatable("speedrunnermod.menu.tutorials.other"));
        this.list.addSmall(
                List.of(
                        comprehensiveGuideButton,
                        thirtyMinRun,
                        otherUsefulTricksButton
                )
        );
    }
}