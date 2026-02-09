package net.dillon.speedrunnermod.item;

import net.dillon.speedrunnermod.advancement.criterion.ModCriterions;
import net.dillon.speedrunnermod.option.ModOptions;
import net.dillon.speedrunnermod.util.ModUtil;
import net.minecraft.component.type.TooltipDisplayComponent;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.boss.dragon.EnderDragonEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Rarity;

import java.util.function.Consumer;

import static net.dillon.speedrunnermod.option.ModOptions.isDoomMode;
import static net.dillon.speedrunnermod.option.ModOptions.isEasyMode;

/**
 * A weapon that {@code one-shots} the {@code ender dragon.}
 */
public class DragonsSwordItem extends Item implements EyeItem {

    public DragonsSwordItem(Item.Settings settings) {
        super(settings.sword(ModToolMaterials.DRAGONS_SWORD, 6, -2.4F).rarity(Rarity.EPIC));
    }

    /**
     * Kills the ender dragon instantly.
     */
    @Override
    public void postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (target instanceof EnderDragonEntity dragon && attacker instanceof PlayerEntity player) {
            if (isEasyMode()) {
                if (dragon.getEntityWorld() instanceof ServerWorld serverWorld) {
                    dragon.setHealth(1.0F);
                    dragon.damage(serverWorld, dragon.getDamageSources().generic(), 100.0F); // Enough damage to kill the dragon
                }
                ModCriterions.TRIGGERED_BY_ITEM.trigger((ServerPlayerEntity)player, stack);
            } else {
                if (isDoomMode()) {
                    attacker.serverDamage(attacker.getDamageSources().mobAttack(attacker), ModUtil.randomFloatInclusive(2.0F, 3.0F));
                    attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, ModUtil.secondsAsTicks(5), 0, false, true, true));
                    attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.BLINDNESS, ModUtil.secondsAsTicks(2), 0, false, true, true));
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
    public void appendTooltip(ItemStack stack, Item.TooltipContext context, TooltipDisplayComponent displayComponent, Consumer<Text> textConsumer, TooltipType type) {
        textConsumer.accept(Text.translatable("item.speedrunnermod.dragons_sword.tooltip")
                .formatted(this.isDisabled() ? Formatting.STRIKETHROUGH : Formatting.WHITE).formatted(Formatting.GRAY));
        if (this.isDisabled()) {
            textConsumer.accept(Text.translatable("item.speedrunnermod.dragons_sword.doom_mode").formatted(Formatting.RED));
        }
        this.addStateOfTheArtItemTooltip(textConsumer);
    }

    @Override
    public ModOptions.Mode[] disabledModes() {
        return new ModOptions.Mode[]{
                ModOptions.Mode.DOOM
        };
    }
}