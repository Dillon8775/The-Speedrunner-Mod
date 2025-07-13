package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket;
import net.minecraft.network.packet.s2c.play.TitleS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
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

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;

/**
 * An item that kills all nearby {@link RaiderEntity}s.
 */
public class RaidEradicatorItem extends Item implements EyeItem {

    public RaidEradicatorItem(Settings settings) {
        super(settings.rarity(Rarity.EPIC).maxCount(1));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            if (!isBalancedMode()) {
                List<RaiderEntity> raiders = world.getEntitiesByClass(RaiderEntity.class, player.getBoundingBox().expand(options().advanced.raidEradicatorSearchRadius.getCurrentValue().getFirst(), options().advanced.raidEradicatorSearchRadius.getCurrentValue().get(1), options().advanced.raidEradicatorSearchRadius.getCurrentValue().get(2)), entity -> true);

                if (!raiders.isEmpty()) {
                    boolean hasTotemEquipped = player.getInventory().contains(ModItems.SPEEDRUNNERS_TOTEM.getDefaultStack()) || player.getMainHandStack().isIn(ModItemTags.TOTEMS) || player.getOffHandStack().isIn(ModItemTags.TOTEMS);
                    if (player.getAbilities().creativeMode) {
                        hasTotemEquipped = true;
                    }

                    if (hasTotemEquipped) {
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_RAVAGER_ROAR, SoundCategory.HOSTILE, 3.0F, 1.0F);
                        player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.minutesAsTicks(5));
                        if (!player.getAbilities().creativeMode) {
                            stack.decrement(1);
                        }
                        ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;
                        new Timer().schedule(new TimerTask() {
                            @Override
                            public void run() {
                                for (RaiderEntity raider : raiders) {
                                    if (!raider.hasCustomName()) {
                                        if (!(raider instanceof WitchEntity)) {
                                            raider.kill(serverWorld);
                                        } else {
                                            raider.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ModUtil.secondsAsTicks(30), 2, false, true, false));
                                            raider.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, ModUtil.secondsAsTicks(30), 1, false, true, false));
                                            raider.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, ModUtil.minutesAsTicks(2), 0, false, true, false));
                                            raider.teleport(player.getX() + world.random.nextInt(7) - 3, player.getY() + world.random.nextDouble() * (2.0 - 0.5) + 0.5, player.getZ() + world.random.nextInt(7) - 3, false);
                                        }
                                    }
                                }
                                player.damage(serverWorld, player.getDamageSources().generic(), player.getHealth());

                                ModCriterions.TRIGGERED_BY_ITEM.trigger(serverPlayer, ModItems.RAID_ERADICATOR.getDefaultStack());

                                Text purgedText = Text.translatable("item.speedrunnermod.raid_eradicator.purged").formatted(Formatting.RED);
                                serverPlayer.networkHandler.sendPacket(new TitleS2CPacket(Text.translatable("item.speedrunnermod.raid_eradicator.success", serverPlayer.getName()).formatted(Formatting.AQUA).formatted(Formatting.BOLD)));
                                serverPlayer.networkHandler.sendPacket(new SubtitleS2CPacket(purgedText));
                                player.sendMessage(purgedText, false);
                                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_RAVAGER_DEATH, SoundCategory.HOSTILE, 3.0F, 1.0F);
                            }
                        }, ModUtil.millisecondsAsSeconds(3));
                        player.swingHand(hand, true);
                        return ActionResult.SUCCESS;
                    } else {
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_WITCH_AMBIENT, SoundCategory.NEUTRAL, 3.0F, 1.0F);
                        ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.raid_eradicator.no_totem").formatted(Formatting.YELLOW));
                    }
                } else {
                    ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.raid_eradicator.couldnt_find_raiders"));
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(Formatting.GRAY), false);
                world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_VINDICATOR_DEATH, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                stack.decrement(1);
                player.dropItem((ServerWorld)world, Items.ENDER_PEARL);
                player.dropItem((ServerWorld)world, Items.FIRE_CHARGE);
                player.dropItem((ServerWorld)world, Items.ENCHANTED_GOLDEN_APPLE);
                player.dropItem((ServerWorld)world, ModItems.SPEEDRUNNERS_EYE);
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.raid_eradicator.tooltip"));
        if (isBalancedMode()) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.state_of_the_art_item.disabled").formatted(Formatting.RED).formatted(Formatting.BOLD).formatted(Formatting.ITALIC));
        }
    }
}