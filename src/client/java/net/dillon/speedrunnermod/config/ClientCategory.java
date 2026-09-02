package net.dillon.speedrunnermod.config;

import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.EnumControllerBuilder;
import dev.isxander.yacl3.api.controller.IntegerSliderControllerBuilder;
import dev.isxander.yacl3.impl.controller.BooleanControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.StringControllerBuilderImpl;
import dev.isxander.yacl3.impl.controller.TickBoxControllerBuilderImpl;
import net.dillon.speedrunnermod.option.eum.ItemMessages;
import net.dillon.speedrunnermod.option.eum.WorldDifficulty;
import net.dillon.speedrunnermod.option.eum.WorldGameMode;
import net.minecraft.ChatFormatting;
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
                                                .binding(true, () -> client().worldCreation().instantWorldCreation, v -> client().worldCreation().instantWorldCreation = v)
                                                .controller(BooleanControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<WorldGameMode>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.gamemode"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.gamemode.description")))
                                                .binding(WorldGameMode.SURVIVAL, () -> client().worldCreation().worldGameMode, v -> client().worldCreation().worldGameMode = v)
                                                .controller(o -> EnumControllerBuilder.create(o)
                                                        .enumClass(WorldGameMode.class)
                                                        .formatValue(v -> Component.literal(v.getSerializedName())))
                                                .build()
                                )
                                .option(
                                        Option.<WorldDifficulty>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.difficulty"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.difficulty.description")))
                                                .binding(WorldDifficulty.EASY, () -> client().worldCreation().worldDifficulty, v -> client().worldCreation().worldDifficulty = v)
                                                .controller(o -> EnumControllerBuilder.create(o)
                                                        .enumClass(WorldDifficulty.class)
                                                        .formatValue(v -> Component.literal(v.getSerializedName())))
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.allow_commands"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.allow_commands.description")))
                                                .binding(false, () -> client().worldCreation().allowCommands, v -> client().worldCreation().allowCommands = v)
                                                .controller(BooleanControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<String>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.seed"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.seed.description")))
                                                .binding("", () -> client().worldCreation().seed, v -> client().worldCreation().seed = v)
                                                .controller(StringControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .group(
                        OptionGroup.createBuilder()
                                .name(Component.translatable("speedrunnermod.options.title.vision"))
                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.vision.description")))
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.fog"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.fog.description")))
                                                .binding(false, () -> client().general().fog, v -> client().general().fog = v)
                                                .controller(BooleanControllerBuilderImpl::new)
                                                .flag(OptionFlag.RELOAD_CHUNKS)
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.increased_lava_vision"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.increased_lava_vision.description")))
                                                .binding(true, () -> client().general().increasedLavaVision, v -> client().general().increasedLavaVision = v)
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
                                                .binding(1, () -> client().general().iCarusFireworksInventorySlot, v -> client().general().iCarusFireworksInventorySlot = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(1, 36)
                                                        .step(1)
                                                        .formatValue(v -> v < 10 ? Component.literal("Hotbar Slot " + v)
                                                                .copy().withStyle(ChatFormatting.AQUA) : Component.literal("Slot " + v)
                                                                .copy().withStyle(ChatFormatting.AQUA)
                                                        )
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.infini_pearl_inventory_slot"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.infini_pearl_inventory_slot.description")))
                                                .binding(1, () -> client().general().infiniPearlInventorySlot, v -> client().general().infiniPearlInventorySlot = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(1, 36)
                                                        .step(1)
                                                        .formatValue(v -> v < 10 ? Component.literal("Hotbar Slot " + v)
                                                                .copy().withStyle(ChatFormatting.AQUA) : Component.literal("Slot " + v)
                                                                .copy().withStyle(ChatFormatting.AQUA)
                                                        )
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
                                                .binding(ItemMessages.OVERLAY, () -> client().general().itemMessages, v -> client().general().itemMessages = v)
                                                .controller(o -> EnumControllerBuilder.create(o)
                                                        .enumClass(ItemMessages.class)
                                                        .formatValue(v -> Component.literal(v.getSerializedName())))
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.warning_messages"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.warning_messages.description")))
                                                .binding(true, () -> client().general().warningMessages, v -> client().general().warningMessages = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .option(
                                        Option.<Integer>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.fullbright_amount"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.fullbright_amount.description")))
                                                .binding(1200, () -> client().general().fullBrightAmount, v -> client().general().fullBrightAmount = v)
                                                .controller(o -> IntegerSliderControllerBuilder.create(o)
                                                        .range(300, 1200)
                                                        .step(10)
                                                        .formatValue(v -> Component.literal(String.valueOf(v)))
                                                )
                                                .build()
                                )
                                .option(
                                        Option.<Boolean>createBuilder()
                                                .name(Component.translatable("speedrunnermod.options.show_reset_button"))
                                                .description(OptionDescription.of(Component.translatable("speedrunnermod.options.show_reset_button.description")))
                                                .binding(true, () -> client().general().showResetButton, v -> client().general().showResetButton = v)
                                                .controller(TickBoxControllerBuilderImpl::new)
                                                .build()
                                )
                                .build()
                )
                .build();
    }
}