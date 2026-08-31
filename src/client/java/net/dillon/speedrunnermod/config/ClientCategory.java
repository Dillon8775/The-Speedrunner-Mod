package net.dillon.speedrunnermod.config;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.OptionDescription;
import dev.isxander.yacl3.api.OptionGroup;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.TickBoxControllerBuilderImpl;
import net.dillon.speedrunnermod.option.eum.ItemMessages;
import net.dillon.speedrunnermod.option.eum.WorldDifficulty;
import net.dillon.speedrunnermod.option.eum.WorldGameMode;
import net.minecraft.network.chat.Component;

import static net.dillon.speedrunnermod.main.SpeedrunnerModClient.client;

/**
 * The client options category for the {@link ConfigurationScreen}.
 */
public class ClientCategory {

    protected static ConfigCategory create() {
        return ConfigCategory.createBuilder()
                .name(Component.translatable("speedrunnermod.options.title.client"))
                .tooltip(Component.translatable("speedrunnermod.options.client.tooltip"))
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.world_creation_settings"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.world_creation_settings.description")))
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.instant_world_creation"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.instant_world_creation.description")))
                                                .binding(true, () -> client().client.instantWorldCreation, v -> client().client.instantWorldCreation = v)
                                                .controller(BooleanControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<WorldGameMode>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.gamemode"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.gamemode.description")))
                                                .binding(WorldGameMode.SURVIVAL, () -> client().client.worldGameMode, v -> client().client.worldGameMode = v)
                                                .controller(o -> EnumControllerBuilder.create(o)
                                                        .enumClass(WorldGameMode.class)
                                                        .formatValue(v -> Component.literal(v.getSerializedName())))
                                                .build()
                                )
                                .option(
                                        Option.<WorldDifficulty>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.difficulty"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.difficulty.description")))
                                                .binding(WorldDifficulty.EASY, () -> client().client.worldDifficulty, v -> client().client.worldDifficulty = v)
                                                .controller(o -> EnumControllerBuilder.create(o)
                                                        .enumClass(WorldDifficulty.class)
                                                        .formatValue(v -> Component.literal(v.getSerializedName())))
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.allow_commands"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.allow_commands.description")))
                                                .binding(false, () -> client().client.allowCommands, v -> client().client.allowCommands = v)
                                                .controller(BooleanControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<String>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.seed"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.seed.description")))
                                                .binding("", () -> client().client.seed, v -> client().client.seed = v)
                                                .controller(StringControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.vision"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.fog.vision")))
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.fog"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.fog.description")))
                                                .binding(false, () -> client().client.fog, v -> client().client.fog = v)
                                                .controller(BooleanControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.increased_lava_vision"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.increased_lava_vision.description")))
                                                .binding(true, () -> client().client.increasedLavaVision, v -> client().client.increasedLavaVision = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.inventory"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.inventory.description")))
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.icarus_fireworks_inventory_slot"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.icarus_fireworks_inventory_slot.description")))
                                                .binding(1, () -> client().client.iCarusFireworksInventorySlot, v -> client().client.iCarusFireworksInventorySlot = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(1, 36)
                                                        .step(1)
                                                        .formatValue(v -> v < 10 ? Component.literal("Hotbar Slot " + v) : Component.literal("Slot " + v))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.infini_pearl_inventory_slot"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.infini_pearl_inventory_slot.description")))
                                                .binding(1, () -> client().client.infiniPearlInventorySlot, v -> client().client.infiniPearlInventorySlot = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(1, 36)
                                                        .step(1)
                                                        .formatValue(v -> v < 10 ? Component.literal("Hotbar Slot " + v) : Component.literal("Slot " + v))
                                                )
                                                .build()
                                )
                                .build()
                )
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.ui_and_hud"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.ui_and_hud.description")))
                                .option(
                                        Option.<ItemMessages>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.item_messages"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.item_messages.description")))
                                                .binding(ItemMessages.OVERLAY, () -> client().client.itemMessages, v -> client().client.itemMessages = v)
                                                .controller(o -> EnumControllerBuilder.create(o)
                                                        .enumClass(ItemMessages.class)
                                                        .formatValue(v -> Component.literal(v.getSerializedName())))
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.warning_messages"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.warning_messages.description")))
                                                .binding(true, () -> client().client.warningMessages, v -> client().client.warningMessages = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.fullbright_amount"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.fullbright_amount.description")))
                                                .binding(1200, () -> client().client.fullBrightAmount, v -> client().client.fullBrightAmount = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(300, 1200)
                                                        .step(1)
                                                        .formatValue(v -> Component.literal(String.valueOf(v)))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.show_reset_button"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.show_reset_button")))
                                                .binding(true, () -> client().client.showResetButton, v -> client().client.showResetButton = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .build();
    }
}