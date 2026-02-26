package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.component.ModDataComponentTypes;
import net.dillon.speedrunnermod.option.ModOptions;
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
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.List;
import java.util.function.Consumer;

import static net.dillon.speedrunnermod.main.SpeedrunnerMod.options;
import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;

/**
 * An item that teleports {@code nearby piglin} to the player.
 */
public class PiglinAwakenerItem extends Item implements EyeItem {

    public PiglinAwakenerItem(Settings settings) {
        super(settings
                .component(ModDataComponentTypes.STORED_ITEMSTACK, ItemStack.EMPTY)
                .maxCount(16));
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        player.setCurrentHand(hand);
        if (world.isClient()) {
            return ActionResult.CONSUME;
        } else if (this.isDisabled()) {
            this.playWorldSound(SoundEvents.ENTITY_PIGLIN_AMBIENT, 1.0F, world, player);
            player.sendMessage(Text.translatable("item.speedrunnermod.item_disabled_twomode").formatted(Formatting.GOLD), false);
            player.swingHand(hand, true);
            for (int i = 0; i < 8; i++) {
                player.dropItem((ServerWorld)world, Items.GOLD_INGOT);
            }
            player.dropItem((ServerWorld)world, stack.getOrDefault(ModDataComponentTypes.STORED_ITEMSTACK, ItemStack.EMPTY).getItem());
            stack.decrement(1);
        } else if (world.getRegistryKey() != World.NETHER) {
            ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.piglin_awakener.wrong_dimension"), Formatting.RED, Formatting.WHITE);
        } else {
            List<PiglinEntity> piglins = ModUtil.getEntitiesWithinRange(world, PiglinEntity.class, player, options().advanced.piglinAwakenerSearchRadius.getCurrentValue());

            if (piglins.isEmpty()) {
                ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.piglin_awakener.couldnt_find_piglins"), Formatting.RED, Formatting.WHITE);
            } else {
                boolean isSafe = player.getAbilities().creativeMode;
                boolean hasGold = player.getInventory().contains(new ItemStack(Items.GOLD_INGOT)) || player.getAbilities().creativeMode;
                for (EquipmentSlot armorItem : EquipmentSlot.VALUES) {
                    ItemStack itemStack = player.getEquippedStack(armorItem);
                    if (itemStack.isIn(ItemTags.PIGLIN_SAFE_ARMOR)) {
                        isSafe = true;
                    }
                }

                if (!isSafe) {
                    this.playWorldSound(SoundEvents.ENTITY_PIGLIN_AMBIENT, 3.0F, 1.0F, world, player);
                    this.playWorldSound(SoundEvents.ENTITY_PIGLIN_AMBIENT, world, player);
                    ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.piglin_awakener.unsafe"), Formatting.RED, Formatting.WHITE);
                } else if (!hasGold) {
                    this.playWorldSound(SoundEvents.ENTITY_PIGLIN_AMBIENT, 3.0F, 1.0F, world, player);
                    ModUtil.sendMessageWithActionbarPref(player, Text.translatable("item.speedrunnermod.piglin_awakener.no_gold_ingot"), Formatting.RED, Formatting.WHITE);
                } else {
                    this.playThrowSound(world, player);
                    this.playWorldSound(SoundEvents.ENTITY_PIGLIN_ANGRY, 3.0F, 1.0F, world, player);
                    player.getItemCooldownManager().set(this.getDefaultStack(), ModUtil.minutesAsTicks(1));
                    boolean sneakingWhenClicked = player.isSneaking();

                    ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)player, stack);

                    this.decrementIfPossible(player, stack);

                    int piglinTeleported = 0;
                    for (PiglinEntity piglin : piglins) {
                        if (!piglin.isBaby()) {
                            if (world.random.nextFloat() < 0.50F) {
                                piglin.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, ModUtil.minutesAsTicks(1), 0, false, true, false));
                            }
                            double x = !sneakingWhenClicked ? player.getX() + world.random.nextInt(7) - 3 : player.getX();
                            double y = !sneakingWhenClicked ? player.getY() + world.random.nextDouble() * (2.0 - 0.5) + 0.5 : player.getY();
                            double z = !sneakingWhenClicked ? player.getZ() + world.random.nextInt(7) - 3 : player.getZ();
                            piglin.teleport(x, y, z, false);
                            piglinTeleported++;
                        }
                        if (piglinTeleported >= options().advanced.piglinAwakenerPiglinCount.getCurrentValue() || (isDoomMode() && piglinTeleported >= 3)) {
                            break;
                        }
                    }
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
        textConsumer.accept(Text.translatable("item.speedrunnermod.piglin_awakener.tooltip")
                .formatted(this.isDisabled() ? Formatting.STRIKETHROUGH : Formatting.RESET).formatted(Formatting.GRAY));
        textConsumer.accept(Text.translatable("item.speedrunnermod.piglin_awakener.shift.tooltip")
                .formatted(this.isDisabled() ? Formatting.STRIKETHROUGH : Formatting.RESET).formatted(Formatting.GRAY));
        if (isDoomMode()) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.piglin_awakener.doom_mode").formatted(Formatting.RED));
        }
        this.addStateOfTheArtItemTooltip(textConsumer);
    }

    @Override
    public ModOptions.Mode[] disabledModes() {
        return new ModOptions.Mode[]{
                ModOptions.Mode.BALANCED
        };
    }
}