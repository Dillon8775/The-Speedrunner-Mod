package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.server.ServerSyncedClientOptions;
import net.dillon.speedrunnermod.tutorial.TutorialStep;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PiglinEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.tag.ItemTags;
import net.minecraft.server.network.ServerPlayerEntity;
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
import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isPlayingModeEasy;

/**
 * An item that teleports {@code nearby piglin} to the player.
 */
public class PiglinAwakenerItem extends Item implements StateOfTheArtItem {

    public PiglinAwakenerItem(Settings settings) {
        super(settings.component(ModDataComponentTypes.STORED_ITEMSTACK, ItemStack.EMPTY).maxCount(16));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient) {
            if (isPlayingModeEasy()) {
                if (world.getRegistryKey() == World.NETHER) {
                    List<PiglinEntity> piglins = world.getEntitiesByClass(PiglinEntity.class, player.getBoundingBox().expand(options().advanced.piglinAwakenerPiglinDistanceXYZ.getCurrentValue().getFirst(), options().advanced.piglinAwakenerPiglinDistanceXYZ.getCurrentValue().get(1), options().advanced.piglinAwakenerPiglinDistanceXYZ.getCurrentValue().get(2)), entity -> true);

                    if (!piglins.isEmpty()) {
                        boolean isSafe = player.getAbilities().creativeMode;
                        boolean hasGold = player.getInventory().contains(new ItemStack(Items.GOLD_INGOT));
                        if (player.getAbilities().creativeMode) {
                            hasGold = true;
                        }
                        for (EquipmentSlot armorItem : EquipmentSlot.VALUES) {
                            ItemStack itemStack = player.getEquippedStack(armorItem);
                            if (itemStack.isIn(ItemTags.PIGLIN_SAFE_ARMOR)) {
                                isSafe = true;
                            }
                        }

                        if (isSafe) {
                            if (hasGold) {
                                boolean sneakingWhenClicked = player.isSneaking();
                                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_ANGRY, SoundCategory.HOSTILE, 3.0F, 1.0F);
                                player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.minutesInTicks(1));
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
                                                    piglin.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, ModUtil.minutesInTicks(1), 0, false, true, false));
                                                }
                                                double x = !sneakingWhenClicked ? player.getX() + world.random.nextInt(7) - 3 : player.getX();
                                                double y = !sneakingWhenClicked ? player.getY() + world.random.nextDouble() * (2.0 - 0.5) + 0.5 : player.getY();
                                                double z = !sneakingWhenClicked ? player.getZ() + world.random.nextInt(7) - 3 : player.getZ();
                                                piglin.teleport(x, y, z, false);
                                                maxNumberOfPiglin++;
                                            }
                                            if (maxNumberOfPiglin >= options().advanced.piglinAwakenerPiglinCount.getCurrentValue()) {
                                                break;
                                            }
                                        }
                                        ModUtil.completeStepS2C(TutorialStep.USE_PIGLIN_AWAKENER, player,
                                                "speedrunnermod.tutorial_mode.used_piglin_awakener",
                                                "speedrunnermod.tutorial_mode.craft_blaze_spotter");
                                        ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)player, stack);
                                    }
                                }, ModUtil.millisecondsAsSeconds(2));
                                player.swingHand(hand, true);
                                return ActionResult.SUCCESS;
                            } else {
                                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.NEUTRAL, 3.0F, 1.0F);
                                player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.no_gold_ingot").formatted(ModUtil.toFormatting(player.getUuid(), Formatting.RED, Formatting.WHITE)), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
                            }
                        } else {
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.NEUTRAL, 1.5F, 1.0F);
                            player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.unsafe").formatted(ModUtil.toFormatting(player.getUuid(), Formatting.RED, Formatting.WHITE)), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
                        }
                    } else {
                        player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.couldnt_find_piglins").formatted(ModUtil.toFormatting(player.getUuid(), Formatting.RED, Formatting.WHITE)), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
                    }
                } else {
                    player.sendMessage(Text.translatable("item.speedrunnermod.piglin_awakener.wrong_dimension").formatted(ModUtil.toFormatting(player.getUuid(), Formatting.RED, Formatting.WHITE)), ServerSyncedClientOptions.shouldShowInActionbar(player.getUuid()));
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(Formatting.GOLD), false);
                player.swingHand(hand, true);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_PIGLIN_AMBIENT, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                for (int i = 0; i < 8; i++) {
                    player.dropItem((ServerWorld)world, Items.GOLD_INGOT);
                }
                player.dropItem((ServerWorld)world, stack.getOrDefault(ModDataComponentTypes.STORED_ITEMSTACK, ItemStack.EMPTY).getItem());
                stack.decrement(1);
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.piglin_awakener.tooltip"));
        this.addStateOfTheArtItemTooltip(textConsumer);
    }
}