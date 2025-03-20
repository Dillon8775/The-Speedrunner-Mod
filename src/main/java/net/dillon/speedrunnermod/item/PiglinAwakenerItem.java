package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.SpeedrunnerMod;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.dillon.speedrunnermod.util.TutorialMode;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An item that teleports {@code nearby piglin} to the player.
 */
public class PiglinAwakenerItem extends Item implements StateOfTheArtItem, TutorialMode {
    private boolean confirm = !options().client.confirmMessages;

    public PiglinAwakenerItem(Settings settings) {
        super(settings.maxCount(16));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (options().main.playingMode.easy()) {
                if (world.getRegistryKey() == World.NETHER) {
                    List<PiglinEntity> piglins = world.getEntitiesByClass(PiglinEntity.class, player.getBoundingBox().expand(options().advanced.piglinAwakenerPiglinDistanceXYZ[0], options().advanced.piglinAwakenerPiglinDistanceXYZ[1], options().advanced.piglinAwakenerPiglinDistanceXYZ[2]), entity -> true);

                    if (!piglins.isEmpty()) {
                        boolean isSafe = player.getAbilities().creativeMode;
                        boolean hasGold = player.getInventory().contains(new ItemStack(Items.GOLD_INGOT));
                        if (player.getAbilities().creativeMode) {
                            hasGold = true;
                        }
                        for (ItemStack armorItems : player.getArmorItems()) {
                            Item item = armorItems.getItem();
                            if (item instanceof ArmorItem armorItem && armorItem.getDefaultStack().isIn(ItemTags.PIGLIN_SAFE_ARMOR)) {
                                isSafe = true;
                            }
                        }

                        if (isSafe) {
                            if (hasGold) {
                                boolean sneakingWhenClicked = player.isSneaking();
                                if (confirm) {
                                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_ANGRY, SoundCategory.HOSTILE, 3.0F, 1.0F);
                                    player.getItemCooldownManager().set(this.getDefaultStack(), TickCalculator.seconds(60));
                                    if (!player.getAbilities().creativeMode) {
                                        stack.decrement(1);
                                    }
                                    new Timer().schedule(new TimerTask() {
                                        @Override
                                        public void run() {
                                            int maxNumberOfPiglin = 0;
                                            for (PiglinEntity piglin : piglins) {
                                                if (!piglin.isBaby() && !piglin.hasCustomName()) {
                                                    if (world.random.nextFloat() < 0.50F) {
                                                        piglin.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, TickCalculator.minutes(2), 0, false, true, false));
                                                    }
                                                    double x = !sneakingWhenClicked ? player.getX() + world.random.nextInt(7) - 3 : player.getX();
                                                    double y = !sneakingWhenClicked ? player.getY() + world.random.nextDouble() * (2.0 - 0.5) + 0.5 : player.getY();
                                                    double z = !sneakingWhenClicked ? player.getZ() + world.random.nextInt(7) - 3 : player.getZ();
                                                    piglin.teleport(x, y, z, false);
                                                    maxNumberOfPiglin++;
                                                }
                                                if (maxNumberOfPiglin >= SpeedrunnerMod.getMaximumAmountOfPiglinAllowedViaPiglinAwakener()) {
                                                    break;
                                                }
                                            }
                                            if (options().main.tutorialMode && options().tutorialMode.obtainedSpeedrunnerPickaxe && options().tutorialMode.obtainedSpeedrunnerBoat && options().tutorialMode.obtainedInfernoEye && options().tutorialMode.usedInfernoEye && options().tutorialMode.obtainedPiglinAwakener && !options().tutorialMode.usedPiglinAwakener) {
                                                send("speedrunnermod.tutorial_mode.used_piglin_awakener.easy", player);
                                                send("speedrunnermod.tutorial_mode.obtain_blaze_spotter.easy", player);
                                                options().tutorialMode.usedPiglinAwakener = true;
                                                ModOptions.saveConfig();
                                            }
                                        }
                                    }, ModUtil.millisecondsAsSeconds(2));
                                } else {
                                    world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.HOSTILE, 3.0F, 1.0F);
                                    player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.got_piglins").formatted(ModUtil.toFormatting(Formatting.GOLD, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                                    player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.confirm"), false);
                                }
                                if (options().client.confirmMessages) {
                                    confirm = !confirm;
                                }
                                player.swingHand(hand, true);
                                return ActionResult.SUCCESS;
                            } else {
                                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.NEUTRAL, 3.0F, 1.0F);
                                player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.no_gold_ingot").formatted(ModUtil.toFormatting(Formatting.RED, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                            }
                        } else {
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.NEUTRAL, 1.5F, 1.0F);
                            player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.unsafe").formatted(ModUtil.toFormatting(Formatting.RED, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                        }
                    } else {
                        player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.couldnt_find_piglins").formatted(ModUtil.toFormatting(Formatting.RED, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                    }
                } else {
                    player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.wrong_dimension").formatted(ModUtil.toFormatting(Formatting.RED, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(Formatting.GOLD), false);
                player.swingHand(hand, true);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                stack.decrement(1);
                for (int i = 0; i < 8; i++) {
                    player.dropItem((ServerWorld)world, Items.GOLD_INGOT);
                }
                player.dropItem((ServerWorld)world, Items.ENDER_PEARL);
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.piglin_awakener.tooltip"));
            this.addStateOfTheArtItemTooltip(tooltip);
        }
    }
}