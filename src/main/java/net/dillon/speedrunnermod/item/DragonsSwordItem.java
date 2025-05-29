package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.ComponentsAccess;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipAppender;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.SpeedrunnerMod.options;

/**
 * A weapon that {@code one-shots} the {@code ender dragon.}
 */
public class DragonsSwordItem extends Item implements StateOfTheArtItem, TooltipAppender {

    public DragonsSwordItem(Item.Settings settings) {
        super(settings.sword(ModToolMaterials.DRAGONS_SWORD, 9, -2.4F).rarity(Rarity.EPIC));
    }

    /**
     * Kills the ender dragon instantly.
     */
    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof EnderDragonEntity dragon && attacker instanceof PlayerEntity player) {
            if (!options().main.playingMode.doom() && !options().main.playingMode.balanced()) {
                dragon.setHealth(0.0F);
                ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)player, stack);
            } else {
                if (options().main.playingMode.doom()) {
                    attacker.serverDamage(attacker.getDamageSources().mobAttack(attacker), ModUtil.randomFloat(2.0F, 3.0F));
                    attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ModUtil.secondsInTicks(5), 0, false, true, true));
                    attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, ModUtil.secondsInTicks(2), 0, false, true, true));
                    if (attacker instanceof PlayerEntity) {
                        ((PlayerEntity)attacker).sendMessage(Text.translatable("item.speedrunnermod.dragons_sword.failed").formatted(Formatting.LIGHT_PURPLE), false);
                    }
                }
            }
            stack.damage(ModToolMaterials.DRAGONS_SWORD.durability(), attacker, EquipmentSlot.MAINHAND);
        }
        super.postHit(stack, target, attacker);
    }

    /**
     * The Dragon's sword always has an enchantment glint.
     */
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

    @Override
    public void appendTooltip(Item.TooltipContext context, Consumer<Text> textConsumer, TooltipType type, ComponentsAccess components) {
        if (options().client.itemTooltips) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.dragons_sword.tooltip").formatted(options().main.playingMode.doom() ? Formatting.STRIKETHROUGH : Formatting.WHITE));
            if (options().main.playingMode.doom()) {
                textConsumer.accept(Text.translatable("item.speedrunnermod.dragons_sword.doom_mode").formatted(Formatting.RED));
            }
            this.addStateOfTheArtItemTooltip(textConsumer);
        }
    }
}