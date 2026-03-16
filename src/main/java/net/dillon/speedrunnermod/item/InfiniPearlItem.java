package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.ChatFormatting;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.throwableitemprojectile.ThrownEnderpearl;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;

import java.util.function.Consumer;

/**
 * An {@code ender pearl} like item that does not get consumed nor do damage upon use.
 */
public class InfiniPearlItem extends EnderpearlItem implements EyeItem {

    public InfiniPearlItem(Properties settings) {
        super(settings.rarity(Rarity.RARE).stacksTo(1).durability(571));
    }

    /**
     * Acts pretty much exactly like an {@code ender pearl,} just removing the item decrement and entity damage.
     */
    @Override
    public InteractionResult use(Level world, Player player, InteractionHand hand) {
        ItemStack itemStack = player.getItemInHand(hand);
        this.playWorldSound(SoundEvents.ENDER_PEARL_THROW, 0.5F, 0.4F, world, player);
        int coolEnchantment = EnchantmentHelper.getEnchantmentLevel(ModUtil.enchantment(player, ModEnchantments.COOLDOWN), player);
        int cooldown = coolEnchantment > 3 ? 15 : coolEnchantment == 3 ? 10 : coolEnchantment == 2 ? 20 : coolEnchantment == 1 ? 30 : 40;
        player.getCooldowns().addCooldown(this.getDefaultInstance(), cooldown);

        if (world instanceof ServerLevel serverWorld) {
            Projectile.spawnProjectileFromRotation(ThrownEnderpearl::new, serverWorld, itemStack, player, 0.0F, 2.0F, 1.2F);
        }

        // Safer boolean check
        if (!itemStack.getComponents().has(DataComponents.UNBREAKABLE)) {
            itemStack.hurtAndBreak(1, player, hand.asEquipmentSlot());
        }

        player.awardStat(Stats.ITEM_USED.get(this));

        return InteractionResult.SUCCESS;
    }

    /**
     * The InfiniPearl always has an enchantment glint.
     */
    @Override
    public boolean isFoil(ItemStack stack) {
        return true;
    }

    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, TooltipDisplay displayComponent, Consumer<Component> textConsumer, TooltipFlag type) {
        textConsumer.accept(Component.translatable("item.speedrunnermod.infini_pearl.tooltip.line1").withStyle(ChatFormatting.GRAY));
        textConsumer.accept(Component.translatable("item.speedrunnermod.infini_pearl.tooltip.line2").withStyle(ChatFormatting.GRAY));
    }

    @Override
    public ModOptions.Mode[] disabledModes() {
        return new ModOptions.Mode[]{};
    }
}