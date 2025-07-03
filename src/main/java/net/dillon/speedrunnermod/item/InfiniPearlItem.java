package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.enchantment.ModEnchantments;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.thrown.EnderPearlEntity;
import net.minecraft.item.EnderPearlItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import java.util.function.Consumer;

/**
 * An {@code ender pearl} like item that does not get consumed nor do damage upon use.
 */
public class InfiniPearlItem extends EnderPearlItem  {

    public InfiniPearlItem(Settings settings) {
        super(settings.maxCount(1).maxDamage(571).component(DataComponentTypes.CUSTOM_NAME, Text.translatable("item.speedrunnermod.infini_pearl").formatted(Formatting.AQUA).formatted(Formatting.ITALIC)));
    }

    /**
     * Acts pretty much exactly like an {@code ender pearl,} just removing the item decrement and entity damage.
     */
    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        world.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_ENDER_PEARL_THROW, SoundCategory.NEUTRAL, 0.5F, 0.4F / (world.random.nextFloat() * 0.4F + 0.8F));
        int coolEnchantment = EnchantmentHelper.getEquipmentLevel(ModUtil.enchantment(player, ModEnchantments.COOLDOWN), player);
        int cooldown = coolEnchantment > 3 ? 20 : coolEnchantment == 3 ? 25 : coolEnchantment == 2 ? 30 : coolEnchantment == 1 ? 45 : 60;
        player.getItemCooldownManager().set(this.getDefaultStack(), cooldown);

        if (world instanceof ServerWorld serverWorld) {
            ProjectileEntity.spawnWithVelocity(EnderPearlEntity::new, serverWorld, itemStack, player, 0.0F, 1.5F, 1.0F);
        }

        // Safer boolean check
        if (!itemStack.getComponents().contains(DataComponentTypes.UNBREAKABLE)) {
            itemStack.damage(1, player, EquipmentSlot.MAINHAND);
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
        textConsumer.accept(Text.translatable("item.speedrunnermod.infini_pearl.tooltip.line1"));
        textConsumer.accept(Text.translatable("item.speedrunnermod.infini_pearl.tooltip.line2"));
    }
}