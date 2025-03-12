package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.util.ItemUtil;
import net.dillon.speedrunnermod.util.TickCalculator;
import net.dillon.speedrunnermod.util.TimeCalculator;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
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

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * An item that kills all nearby {@link RaiderEntity}s.
 */
public class RaidEradicatorItem extends Item {
    private boolean confirm = !options().client.confirmMessages;

    public RaidEradicatorItem(Settings settings) {
        super(settings.rarity(Rarity.EPIC).maxCount(1));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (!world.isClient && world instanceof ServerWorld serverWorld) {
            if (options().main.playingMode.easy()) {
                List<RaiderEntity> raiders = world.getEntitiesByClass(RaiderEntity.class, player.getBoundingBox().expand(options().advanced.raidEradicatorDistanceXYZ[0], options().advanced.raidEradicatorDistanceXYZ[1], options().advanced.raidEradicatorDistanceXYZ[2]), entity -> true);

                if (!raiders.isEmpty()) {
                    boolean hasTotemEquipped = player.getMainHandStack().isOf(Items.TOTEM_OF_UNDYING) || player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING);
                    if (player.getAbilities().creativeMode) {
                        hasTotemEquipped = true;
                    }

                    if (hasTotemEquipped) {
                        if (confirm) {
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_RAVAGER_ROAR, SoundCategory.HOSTILE, 3.0F, 1.0F);
                            player.getItemCooldownManager().set(this.getDefaultStack(), TickCalculator.minutes(5));
                            if (!player.getAbilities().creativeMode) {
                                stack.decrement(1);
                            }
                            new Timer().schedule(new TimerTask() {
                                @Override
                                public void run() {
                                    for (RaiderEntity raider : raiders) {
                                        if (!raider.hasCustomName()) {
                                            if (!(raider instanceof WitchEntity)) {
                                                raider.kill(serverWorld);
                                            } else {
                                                raider.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, TickCalculator.seconds(30), 2, false, true, false));
                                                raider.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, TickCalculator.seconds(30), 1, false, true, false));
                                                raider.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, TickCalculator.minutes(2), 0, false, true, false));
                                                raider.teleport(player.getX() + world.random.nextInt(7) - 3, player.getY() + world.random.nextDouble() * (2.0 - 0.5) + 0.5, player.getZ() + world.random.nextInt(7) - 3, false);
                                            }
                                        }
                                    }
                                    player.damage(serverWorld, player.getDamageSources().generic(), player.getHealth());
                                    player.sendMessage(Text.translatable("item.speedrunnermod.raid_eradicator.success").formatted(Formatting.RED), false);
                                }
                            }, TimeCalculator.secondsToMilliseconds(3));
                        } else {
                            world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_VINDICATOR_AMBIENT, SoundCategory.HOSTILE, 3.0F, 1.0F);
                            player.sendMessage(Text.translatable("item.speedrunnermod.raid_eradicator.found_raiders").formatted(ItemUtil.toFormatting(Formatting.YELLOW, Formatting.WHITE)), options().client.itemMessages.isActionbar());
                            player.sendMessage(Text.translatable("item.speedrunnermod.raid_eradicator.confirm"), false);
                        }
                        if (options().client.confirmMessages) {
                            confirm = !confirm;
                        }
                        player.swingHand(hand, true);
                        return ActionResult.SUCCESS;
                    } else {
                        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_WITCH_AMBIENT, SoundCategory.NEUTRAL, 3.0F, 1.0F);
                        player.sendMessage(Text.translatable("item.speedrunnermod.raid_eradicator.no_totem").formatted(Formatting.YELLOW), options().client.itemMessages.isActionbar());
                    }
                } else {
                    player.sendMessage(Text.translatable("item.speedrunnermod.raid_eradicator.couldnt_find_raiders"), options().client.itemMessages.isActionbar());
                }
            } else {
                player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(Formatting.GRAY), false);
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (options().client.itemTooltips) {
            tooltip.add(Text.translatable("item.speedrunnermod.raid_eradicator.tooltip"));
            ItemUtil.stateOfTheArtItem(tooltip);
        }
    }
}