package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;

/**
 * An item that forces the {@code ender dragon} to {@code perch.}
 */
public class DragonsPearlItem extends Item implements EyeItem {

    public DragonsPearlItem(Settings settings) {
        super(settings.maxCount(16).rarity(Rarity.EPIC));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (world.isClient()) {
            return ActionResult.CONSUME;
        } else if (this.isDisabled()) {
            this.playWorldSound(SoundEvents.ENTITY_ENDER_EYE_DEATH, world, player);
            stack.decrement(1);
            player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled_twomode").formatted(Formatting.LIGHT_PURPLE), false);
            player.swingHand(hand, true);
            player.dropItem((ServerWorld)world, Items.ENDER_PEARL);
            player.dropItem((ServerWorld)world, Items.BLAZE_POWDER);
            player.dropItem((ServerWorld)world, Items.FIRE_CHARGE);
            player.dropItem((ServerWorld)world, ModItems.SPEEDRUNNERS_EYE);
        } else if (world.getRegistryKey() != World.END) {
            this.playPitchedLaunchSound(5.0F, world, player);
            ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.dragons_pearl.wrong_dimension").formatted(Formatting.LIGHT_PURPLE));
        } else {
            List<EnderDragonEntity> dragons = world.getEntitiesByClass(EnderDragonEntity.class, player.getBoundingBox().expand(
                    options().advanced.dragonsPearlSearchRadius.getCurrentValue().getFirst(),
                    options().advanced.dragonsPearlSearchRadius.getCurrentValue().get(1),
                    options().advanced.dragonsPearlSearchRadius.getCurrentValue().get(2)),
                    entity -> true);

            if (dragons.isEmpty()) {
                this.playWorldSound(SoundEvents.ENTITY_ENDER_EYE_LAUNCH, 3.0F, world, player);
                ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.dragons_pearl.cannot_find_dragon").formatted(Formatting.LIGHT_PURPLE));
            } else {
                EnderDragonEntity enderDragon = dragons.get(0);
                if (!isDragonAlreadyPerchingOrPerched(enderDragon) && !isDragonDead(enderDragon)) {
                    this.playWorldSound(SoundEvents.ENTITY_ENDER_EYE_LAUNCH, 2.0F, 0.3F, world, player);
                    player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.secondsAsTicks(30));

                    ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)player, stack);

                    this.decrementIfPossible(player, stack);

                    TaskScheduler.schedule(ModUtil.secondsAsTicks(2), () -> {
                        enderDragon.getPhaseManager().setPhase(PhaseType.LANDING);
                        this.playWorldSound(SoundEvents.ENTITY_ENDER_DRAGON_GROWL, 3.0F, 0.65F, world, player);
                        ModUtil.completeStepS2C(TutorialStep.USE_DRAGONS_PEARL, player, "speedrunnermod.tutorial_mode.used_dragons_pearl");
                    });

                    player.incrementStat(Stats.USED.getOrCreateStat(this));
                    player.swingHand(hand, true);
                    return ActionResult.SUCCESS;
                } else {
                    if (!isDragonDead(enderDragon)) {
                        if (isDragonSitting(enderDragon)) {
                            ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.dragons_pearl.already_perched").formatted(Formatting.LIGHT_PURPLE));
                        } else {
                            ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.dragons_pearl.already_perching").formatted(Formatting.LIGHT_PURPLE));
                        }
                    } else {
                        ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.dragons_pearl.dragon_dead").formatted(Formatting.LIGHT_PURPLE));
                    }
                    this.playPitchedLaunchSound(5.0F, world, player);
                    player.swingHand(hand, true);
                }
            }
        }

        return ActionResult.CONSUME;
    }

    /**
     * Determines if the ender dragon is sitting or not.
     */
    private static boolean isDragonSitting(EnderDragonEntity enderDragon) {
        return enderDragon.getPhaseManager().getCurrent().getType() == PhaseType.SITTING_SCANNING ||
                enderDragon.getPhaseManager().getCurrent().getType() == PhaseType.SITTING_FLAMING ||
                enderDragon.getPhaseManager().getCurrent().getType() == PhaseType.SITTING_ATTACKING;
    }

    /**
     * Determines if the ender dragon is perching or already perched.
     */
    private static boolean isDragonAlreadyPerchingOrPerched(EnderDragonEntity enderDragon) {
        return enderDragon.getPhaseManager().getCurrent().getType() == PhaseType.LANDING || isDragonSitting(enderDragon);
    }

    /**
     * Checks to see if the ender dragon is dead.
     */
    private static boolean isDragonDead(EnderDragonEntity enderDragon) {
        return enderDragon.getHealth() < 1.0F;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.dragons_pearl.tooltip")
                .formatted(this.isDisabled() ? Formatting.STRIKETHROUGH : Formatting.RESET).formatted(Formatting.GRAY));
        this.addStateOfTheArtItemTooltip(textConsumer);
    }

    @Override
    public ModOptions.Mode[] disabledModes() {
        return new ModOptions.Mode[]{
                ModOptions.Mode.BALANCED
        };
    }
}