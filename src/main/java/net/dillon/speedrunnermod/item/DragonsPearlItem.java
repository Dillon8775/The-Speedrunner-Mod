package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TutorialStep;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.boss.dragon.phase.PhaseType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Consumer;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An item that forces the {@code ender dragon} to {@code perch.}
 */
public class DragonsPearlItem extends Item implements StateOfTheArtItem, TooltipAppender {

    public DragonsPearlItem(Settings settings) {
        super(settings.maxCount(16).rarity(Rarity.EPIC));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (!world.isClient) {
            if (!options().main.playingMode.balanced()) {
                if (world.getRegistryKey() == World.END) {
                    List<EnderDragonEntity> dragons = world.getEntitiesByClass(EnderDragonEntity.class, player.getBoundingBox().expand(options().advanced.dragonsPearlDragonDistanceXYZ[0], options().advanced.dragonsPearlDragonDistanceXYZ[1], options().advanced.dragonsPearlDragonDistanceXYZ[2]), entity -> true);

                    if (!dragons.isEmpty()) {
                        EnderDragonEntity enderDragon = dragons.get(0);
                        if (!isDragonAlreadyPerchingOrPerched(enderDragon) && !isDragonDead(enderDragon)) {
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 2.0F, 0.3F);
                            player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.secondsInTicks(30));
                            if (!player.getAbilities().creativeMode) {
                                itemStack.decrement(1);
                            }
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    enderDragon.getPhaseManager().setPhase(PhaseType.LANDING);
                                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_DRAGON_GROWL, SoundCategory.HOSTILE, 3.0F, 0.65F);
                                    options().tutorialMode.completeStep(TutorialStep.USE_DRAGONS_PEARL, player, "speedrunnermod.tutorial_mode.used_dragons_pearl");
                                }
                            }, ModUtil.millisecondsAsSeconds(2));
                            return ActionResult.SUCCESS;
                        } else {
                            if (!isDragonDead(enderDragon)) {
                                if (isDragonSitting(enderDragon)) {
                                    player.sendMessage(Text.translatable("item.speedrunnermod.dragons_pearl.already_perched").formatted(Formatting.LIGHT_PURPLE), options().client.itemMessages.isActionbar());
                                } else {
                                    player.sendMessage(Text.translatable("item.speedrunnermod.dragons_pearl.already_perching").formatted(Formatting.LIGHT_PURPLE), options().client.itemMessages.isActionbar());
                                }
                            } else {
                                player.sendMessage(Text.translatable("item.speedrunnermod.dragons_pearl.dragon_dead").formatted(Formatting.LIGHT_PURPLE), options().client.itemMessages.isActionbar());
                            }
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 5.0F);
                        }
                    } else {
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 3.0F);
                        player.sendMessage(Text.translatable("item.speedrunnermod.dragons_pearl.cannot_find_dragon").formatted(Formatting.LIGHT_PURPLE), options().client.itemMessages.isActionbar());
                    }
                } else {
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_LAUNCH, SoundCategory.NEUTRAL, 1.0F, 5.0F);
                    player.sendMessage(Text.translatable("item.speedrunnermod.dragons_pearl.wrong_dimension").formatted(Formatting.LIGHT_PURPLE), options().client.itemMessages.isActionbar());
                }
                player.swingHand(hand, true);
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled_twomode").formatted(Formatting.LIGHT_PURPLE), false);
                player.swingHand(hand, true);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_EYE_DEATH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                itemStack.decrement(1);
                player.dropItem((ServerWorld)world, Items.ENDER_PEARL);
                player.dropItem((ServerWorld)world, Items.BLAZE_POWDER);
                player.dropItem((ServerWorld)world, Items.FIRE_CHARGE);
                player.dropItem((ServerWorld)world, ModItems.SPEEDRUNNERS_EYE);
            }
        }

        return ActionResult.SUCCESS;
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
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        if (options().client.itemTooltips) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.dragons_pearl.tooltip"));
            this.addStateOfTheArtItemTooltip(textConsumer);
        }
    }
}