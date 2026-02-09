package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;

import java.util.function.Consumer;

/**
 * An {@code ender pearl} like item that does not get consumed nor do damage upon use.
 */
public class InfiniPearlItem extends EnderPearlItem implements EyeItem {

    public InfiniPearlItem(Settings settings) {
        super(settings.rarity(Rarity.RARE).maxCount(1).maxDamage(571));
    }

    /**
     * Acts pretty much exactly like an {@code ender pearl,} just removing the item decrement and entity damage.
     */
    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        this.playWorldSound(SoundEvents.ENTITY_ENDER_PEARL_THROW, 0.5F, 0.4F, world, player);
        int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ModUtil.enchantment(player, ModEnchantments.COOLDOWN), player);
        int cooldown = coolEnchantment > 3 ? 15 : coolEnchantment == 3 ? 10 : coolEnchantment == 2 ? 20 : coolEnchantment == 1 ? 30 : 40;
        player.getItemCooldownManager().set(this.getDefaultStack(), cooldown);

        if (world instanceof ServerWorld serverWorld) {
            ProjectileEntity.spawnWithVelocity(EnderPearlEntity::new, serverWorld, itemStack, player, 0.0F, 2.0F, 1.2F);
        }

        // Safer boolean check
        if (!itemStack.getComponents().contains(DataComponentTypes.UNBREAKABLE)) {
            itemStack.damage(1, player, hand.getEquipmentSlot());
        }

        player.incrementStat(Stats.USED.getOrCreateStat(this));

        return ActionResult.SUCCESS;
    }

    /**
     * The InfiniPearl always has an enchantment glint.
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.infini_pearl.tooltip.line1").formatted(Formatting.GRAY));
        textConsumer.accept(Text.translatable("item.speedrunnermod.infini_pearl.tooltip.line2").formatted(Formatting.GRAY));
    }

    @Override
    public ModOptions.Mode[] disabledModes() {
        return new ModOptions.Mode[]{};
    }
}