package net.dillon.speedrunnermod.mixin.main.trades;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.util.math.random.Random;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.TradedItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(TradeOffers.SellEnchantedToolFactory.class)
public class SellEnchantedToolFactoryMixin {
    @Shadow @Final
    private ItemStack tool;
    @Shadow @Final
    int basePrice;
    @Shadow @Final
    private int maxUses;
    @Shadow @Final
    private int experience;
    @Shadow @Final
    private float multiplier;

    /**
     * @author Dillon8775
     * @reason Lowers the cost of {@code enchanted tools} sold from villagers.
     */
    @Overwrite
    public TradeOffer create(Entity entity, Random random) {
        int i = random.nextInt(4) + 30;
        DynamicRegistryManager dynamicRegistryManager = entity.getEntityWorld().getRegistryManager();
        Optional<RegistryEntryList.Named<Enchantment>> optional = dynamicRegistryManager.getOrThrow(RegistryKeys.ENCHANTMENT).getOptional(EnchantmentTags.ON_TRADED_EQUIPMENT);
        ItemStack itemStack = EnchantmentHelper.enchant(random, new ItemStack(this.tool.getItem()), i, dynamicRegistryManager, optional);
        int j = Math.min(this.basePrice, 12);
        TradedItem tradedItem = new TradedItem(Items.EMERALD, j);
        return new TradeOffer(tradedItem, itemStack, this.maxUses, this.experience, this.multiplier);
    }
}