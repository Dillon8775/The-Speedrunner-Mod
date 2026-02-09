package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.tag.ModItemTags;
import net.dillon.speedrunnermod.util.ModUtil;
import net.dillon.speedrunnermod.util.TaskScheduler;
import net.dillon.speedrunnermod.util.VillagerGlowCountdown;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.WitchEntity;
import net.minecraft.entity.passive.VillagerEntity;
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
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.List;
import java.util.Random;
import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isBalancedMode;

/**
 * An item that kills all nearby {@link RaiderEntity}s.
 */
public class RaidEradicatorItem extends Item implements EyeItem {

    public RaidEradicatorItem(Settings settings) {
        super(settings.rarity(Rarity.EPIC).maxCount(16));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (world.isClient()) {
            return ActionResult.CONSUME;
        } else if (isBalancedMode()) {
            this.playWorldSound(SoundEvents.ENTITY_VINDICATOR_DEATH, 1.0F, world, player);
            player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled").formatted(Formatting.GRAY), false);
            stack.decrement(1);
            player.dropItem((ServerWorld)world, Items.ENDER_PEARL);
            player.dropItem((ServerWorld)world, Items.FIRE_CHARGE);
            player.dropItem((ServerWorld)world, Items.ENCHANTED_GOLDEN_APPLE);
            player.dropItem((ServerWorld)world, ModItems.SPEEDRUNNERS_EYE);
        } else {
            List<RaiderEntity> raiders = world.getEntitiesByClass(RaiderEntity.class, player.getBoundingBox().expand(
                    options().advanced.raidEradicatorSearchRadius.getCurrentValue().getFirst(),
                    options().advanced.raidEradicatorSearchRadius.getCurrentValue().get(1),
                    options().advanced.raidEradicatorSearchRadius.getCurrentValue().get(2)),
                    entity -> true);

            if (raiders.isEmpty()) {
                ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.raid_eradicator.couldnt_find_raiders"));
            } else {
                boolean hasTotemEquipped =
                        (player.getInventory().contains(ModItems.SPEEDRUNNERS_TOTEM.getDefaultStack())
                                || player.getMainHandStack().isIn(ModItemTags.TOTEMS)
                                || player.getOffHandStack().isIn(ModItemTags.TOTEMS))
                        || player.getAbilities().creativeMode;
                if (!hasTotemEquipped) {
                    this.playWorldSound(SoundEvents.ENTITY_WITCH_AMBIENT, 3.0F, 1.0F, world, player);
                    ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.raid_eradicator.no_totem").formatted(Formatting.YELLOW));
                } else {
                    this.playWorldSound(SoundEvents.ENTITY_RAVAGER_ROAR, 3.0F, 1.0F, world, player);
                    player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.minutesAsTicks(5));
                    this.decrementIfPossible(player, stack);
                    ServerPlayerEntity serverPlayer = (ServerPlayerEntity)player;

                    List<VillagerEntity> villagers = world.getEntitiesByClass(VillagerEntity.class, player.getBoundingBox().expand(
                                    options().advanced.raidEradicatorSearchRadius.getCurrentValue().getFirst(),
                                    options().advanced.raidEradicatorSearchRadius.getCurrentValue().get(1),
                                    options().advanced.raidEradicatorSearchRadius.getCurrentValue().get(2)),
                            entity -> true);

                    TaskScheduler.schedule(ModUtil.secondsAsTicks(3), () -> {
                        for (RaiderEntity raider : raiders) {
                            if (!raider.hasCustomName()) {
                                if (!(raider instanceof WitchEntity)) {
                                    raider.kill((ServerWorld)world);
                                } else {
                                    Random random = new Random();
                                    raider.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ModUtil.secondsAsTicks(30), 2, false, true, false));
                                    raider.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, ModUtil.secondsAsTicks(30), 1, false, true, false));
                                    raider.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, ModUtil.minutesAsTicks(2), 0, false, true, false));
                                    raider.teleport(player.getX() + random.nextInt(7) - 3, player.getY() + random.nextDouble() * (2.0 - 0.5) + 0.5, player.getZ() + random.nextInt(7) - 3, false);
                                }
                            }
                        }
                        if (!villagers.isEmpty()) {
                            for (VillagerEntity villager : villagers) {
                                villager.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, ModUtil.secondsAsTicks(30), 1));
                                villager.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, ModUtil.secondsAsTicks(30)));
                                villager.setGlowing(true);
                                ((VillagerGlowCountdown)villager).setGlowingFor(ModUtil.minutesAsTicks(3));
                            }
                        }
                        player.damage((ServerWorld)world, player.getDamageSources().magic(), 10000 /* enough damage to "kill" the player, but prevents death bug*/);

                        ModCriterions.TRIGGERED_BY_ITEM.trigger(serverPlayer, ModItems.RAID_ERADICATOR.getDefaultStack());

                        Text purgedText = Text.translatable("item.speedrunnermod.raid_eradicator.purged").formatted(Formatting.RED);
                        player.sendMessage(purgedText, false);
                        serverPlayer.networkHandler.sendPacket(new TitleS2CPacket(Text.translatable("item.speedrunnermod.raid_eradicator.success", serverPlayer.getName()).formatted(Formatting.AQUA).formatted(Formatting.BOLD)));
                        serverPlayer.networkHandler.sendPacket(new SubtitleS2CPacket(purgedText));
                        this.playWorldSound(SoundEvents.ENTITY_RAVAGER_DEATH, 3.0F, 1.0F, world, player);
                    });
                    player.incrementStat(Stats.USED.getOrCreateStat(this));
                    player.swingHand(hand, true);
                    return ActionResult.SUCCESS;
                }
            }
        }

        return ActionResult.CONSUME;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.raid_eradicator.tooltip")
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