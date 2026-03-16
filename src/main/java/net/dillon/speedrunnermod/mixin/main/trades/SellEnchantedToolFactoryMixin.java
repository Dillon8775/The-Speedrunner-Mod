package net.dillon.speedrunnermod.mixin.main.trades;

import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Optional;

@Mixin(VillagerTrades.EnchantedItemForEmeralds.class)
public class SellEnchantedToolFactoryMixin {
    @Shadow @Final
    private ItemStack itemStack;
    @Shadow @Final
    int baseEmeraldCost;
    @Shadow @Final
    private int maxUses;
    @Shadow @Final
    private int villagerXp;
    @Shadow @Final
    private float priceMultiplier;

    /**
     * @author Dillon8775
     * @reason Lowers the cost of {@code enchanted tools} sold from villagers.
     */
    @Overwrite
    public MerchantOffer getOffer(ServerLevel world, Entity entity, RandomSource random) {
        int i = random.nextInt(4) + 30;
        RegistryAccess dynamicRegistryManager = entity.level().registryAccess();
        Optional<HolderSet.Named<Enchantment>> optional = dynamicRegistryManager.lookupOrThrow(Registries.ENCHANTMENT).get(EnchantmentTags.ON_TRADED_EQUIPMENT);
        ItemStack itemStack = EnchantmentHelper.enchantItem(random, new ItemStack(this.itemStack.getItem()), i, dynamicRegistryManager, optional);
        int j = Math.min(this.baseEmeraldCost, 12);
        ItemCost tradedItem = new ItemCost(Items.EMERALD, j);
        return new MerchantOffer(tradedItem, itemStack, this.maxUses, this.villagerXp, this.priceMultiplier);
    }
}